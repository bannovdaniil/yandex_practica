package ru.yandex.cars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Sber {
    static class Node {
        String name;
        ArrayList<Node> nodes;

        public Node(String name) {
            this.name = name;
            this.nodes = new ArrayList<>();
        }

        public void addChild(String nodeName) {
            if (findChild(nodeName) == null) {
                this.nodes.add(new Node(nodeName));
            }
        }

        public Node findChild(String nodeName) {
            for (Node node : nodes) {
                if (node.name.equals(nodeName)) {
                    return node;
                }
            }
            return null;
        }

        public Node findNode(Node nodeRoot, String nodeName) {
            Node find = null;
            if(nodeRoot.name.equals(nodeName)){
                return nodeRoot;
            }
            for (Node node : nodeRoot.nodes) {
                if (node.name.equals(nodeName)) {
                    return node;
                } else {
                    Node temp = findNode(node, nodeName);
                    if (temp != null) {
                        return temp;
                    }
                }
            }
            return find;
        }

        @Override
        public String toString() {
            String result = name + " => ";
            for (var node : nodes) {
                result += node.name + ", ";
            }
            return result;
        }
    }

    public static int getResult(List<String> deal) {
        HashSet<String> roots = new HashSet<>();
        Node root = null;
        Node node = null;
        for (var first : deal) {
            node = root;
            if (first.contains("-")) {
                String[] company = first.split("-");
                roots.add(company[0]);
                if (node == null) {
                    node = new Node(company[0]);
                    if (root == null) {
                        root = node;
                    }
                } else {
                    node = node.findNode(root, company[0]);
                }
                for (var second : company[1].split("")) {
                    if (node == null) {
                        node = root.findNode(root, second);
                        node.addChild(company[0]);
                    }
                    node.addChild(second);
                }
            } else {
                roots.add(first);
            }
        }


        int countLinks = 0;
        node = root;
        for (var top : roots) {
            countLinks += getPath(node, top, 0);
            node = node.findNode(root, top);
        }
        System.out.println(countLinks);
        return countLinks;
    }

    private static int getPath(Node node, String name, int count) {
        int inner = 0;
        if (node == null)
            return inner;
 //       System.out.println(node);
        for (var child : node.nodes) {
            if (child.name.equals(name)) {
                inner++;
            }
            inner += getPath(child, name, count++);
        }
        return inner;
    }

    public static void main(String[] args) {
               System.out.println(1 == getResult(List.of("a-b", "b-c", "c-a")));
             System.out.println(1 == getResult(List.of("a-b", "b-c", "c-ad", "d")));
           System.out.println(0 == getResult(List.of("a-b", "b", "c-b")));
        System.out.println(2 == getResult(List.of("a-b", "b-cd", "c-a", "d-c")));

        System.out.println(5 == getResult(List.of("a-b", "b-c", "c-da", "d-ea", "e-fa", "f-ac")));

    }
}
