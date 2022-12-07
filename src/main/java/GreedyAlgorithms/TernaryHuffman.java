package GreedyAlgorithms;

import java.util.*;

/**
 * WARNING: The spec tests are not necessarily equal to your grade! You can use them help you test
 * for the correctness of your algorithm, but the final grade is determined by a manual inspection
 * of your implementation.
 */
public class TernaryHuffman {

    public /**
     * NOTE: You should ensure that if a Node is a part of a tree, then all nodes in the tree have their
     * `parent`, `leftChild`, `middleChild`, and `rightChild` set appropriately! You may add methods to
     * this class, provided you do not change the names of existing methods or fields!
     */ static class Node {

        public Node(Node child1, Node child2, Node child3) {
            this.symbol = 0;
            this.frequency = child1.frequency + child2.frequency + child3.frequency;
            this.leftChild = child1;
            this.middleChild = child2;
            this.rightChild = child3;
            this.parent = null;
        }

        char symbol;

        double frequency;

        Node parent;

        Node leftChild;

        Node rightChild;

        Node middleChild;

        public Node(char symbol, double frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
        }

        public Node(char symbol, double frequency, Node parent) {
            this(symbol, frequency);
            this.parent = parent;
        }

        public Node(char symbol, double frequency, Node leftChild, Node middleChild, Node rightChild) {
            this(symbol, frequency);
            this.leftChild = leftChild;
            this.middleChild = middleChild;
            this.rightChild = rightChild;
        }

        public char getSymbol() {
            return symbol;
        }

        public double getFrequency() {
            return frequency;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getMiddleChild() {
            return middleChild;
        }

        public void setMiddleChild(Node middleChild) {
            this.middleChild = middleChild;
        }
    }

    /**
     * You should implement this method.
     *
     * @param encrypted the encrypted message to decipher (a string of '0's, '1's, and '2's)
     * @param root      the root of the Ternary Huffman tree
     * @return the unencrypted message
     */
    public static String decode(String encrypted, Node root) {
        Node pointer = root;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {

            if (isLeaf(pointer)) {
                sb.append(pointer.symbol);
                pointer = root;
                continue;
            }

            switch (encrypted.charAt(i)) {
                case '0':
                    if (isLeaf(pointer.leftChild)) {
                        sb.append(pointer.leftChild.symbol);
                        pointer = root;
                    } else pointer = pointer.leftChild;
                    continue;

                case '1':
                    if (isLeaf(pointer.middleChild)) {
                        sb.append(pointer.middleChild.symbol);
                        pointer = root;
                    } else pointer = pointer.middleChild;
                    continue;
                case '2':
                    if (isLeaf(pointer.rightChild)) {
                        sb.append(pointer.rightChild.symbol);
                        pointer = root;
                    } else pointer = pointer.rightChild;
                    continue;
            }
        }

        return sb.toString();
    }

    private static boolean isLeaf(Node node) {
        return node.rightChild == null && node.middleChild == null && node.leftChild == null;
    }


    /**
     * You should implement this method. Remember to look at the even/oddness of the number
     * characters!
     *
     * @param n           the number of characters that need to be encoded.
     * @param characters  The characters c_1 through c_n. Note you should use only characters[1] up
     *                    to and including characters[n]!
     * @param frequencies The frequencies f_1 through f_n. Note you should use only frequencies[1]
     *                    up to and including frequencies[n]!
     * @return The rootnode of an optimal Ternary Huffman tree that represents the encoding of the
     * characters given.
     */
    public static Node buildHuffman(int n, char[] characters, double[] frequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Double.compare(node.frequency, t1.frequency);
            }
        });
        for (int i = 1; i <= n; i++) {
            priorityQueue.add(new Node(characters[i], frequencies[i]));
        }

        if(n % 2 == 0){
            priorityQueue.add(new Node((char) 0 , 0));
        }

        while (true){
            var smallest = priorityQueue.poll();
            if(priorityQueue.isEmpty()) return smallest;
            else {
                var second = priorityQueue.poll();
                var third = priorityQueue.poll();
                var parent = new Node((char) 0, smallest.frequency + second.frequency + third.frequency , smallest , second , third);
                smallest.setParent(parent);
                second.setParent(parent);
                third.setParent(parent);
                priorityQueue.add(parent);
            }
        }
    }
    public static Node properTreeBuilding(int n, char[] characters, double[] frequencies) {
        PriorityQueue<Node> nodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return Double.compare(node.frequency, t1.frequency);
            }
        });
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(characters[i], frequencies[i]));
        }
        if (n % 2 == 0) {
            nodes.add(new Node((char) 0, 0));
        }
        while (true) {
            Node smallestFrequency = nodes.poll();
            if (nodes.isEmpty()) {
                return smallestFrequency;
            } else {
                Node secondSmallest = nodes.poll();
                Node thirdSmallest = nodes.poll();
                Node newNode = new Node(smallestFrequency, secondSmallest, thirdSmallest);
                thirdSmallest.setParent(newNode);
                smallestFrequency.setParent(newNode);
                secondSmallest.setParent(newNode);
                nodes.offer(newNode);
            }
        }
    }
}


