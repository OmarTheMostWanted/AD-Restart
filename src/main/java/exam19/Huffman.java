package exam19;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {
    static class Node {

        char symbol;

        double frequency;

        Node parent;

        Node leftChild;

        Node rightChild;

        public Node(char symbol, double frequency) {
            this.symbol = symbol;
            this.frequency = frequency;
        }

        public Node(char symbol, double frequency, Node parent) {
            this(symbol, frequency);
            this.parent = parent;
        }

        public Node(char symbol, double frequency, Node leftChild, Node rightChild) {
            this(symbol, frequency);
            this.leftChild = leftChild;
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
    }

    /**
     * You should implement this method.
     *
     * @param node A Node in the Huffman encoding tree
     * @return the encoded string representing the character in this node.
     */
    public static String encode(Node node) {
        if (node.parent != null) {
            var parent = node.parent;
            if (node == parent.leftChild) return encode(parent) + "0";
            else return encode(parent) + "1";
        }
        return "";
    }

    class Symbol implements Comparable<Symbol> {
        char c;
        double f;

        public Symbol(char c, double f) {
            this.c = c;
            this.f = f;
        }


        @Override
        public int compareTo(Symbol o) {
            return Double.compare(this.f, o.f);
        }

        @Override
        public String toString() {
            return "Symbol{" + "c=" + c + ", f=" + f + '}';
        }
    }

    /**
     * You should implement this method.
     *
     * @param n           the number of characters that need to be encoded.
     * @param characters  The characters c_1 through c_n. Note you should use only characters[1] up to and including characters[n]!
     * @param frequencies The frequencies f_1 through f_n. Note you should use only frequencies[1] up to and including frequencies[n]!
     * @return The rootnode of an optimal Huffman tree that represents the encoding of the characters given.
     */
    public static Node buildHuffman(int n, char[] characters, double[] frequencies) {
        if (n == 0) return null;
        if (n == 1) return new Node(characters[1], frequencies[1]);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.getFrequency(), o2.getFrequency());
            }
        });
        for (int i = 1; i <= n; i++) {
            priorityQueue.add(new Node(characters[i], frequencies[i]));
        }

//        if (priorityQueue.size() % 2 != 0) priorityQueue.add(new Node((char) 0, 0));
        Node root = null;
        while (priorityQueue.size() > 1) {
            var left = priorityQueue.poll();
            var right = priorityQueue.poll();
            var parent = new Node((char) 0, left.getFrequency() + right.getFrequency());
            parent.setLeftChild(left);
            parent.setRightChild(right);
            left.setParent(left);
            right.setParent(parent);
            priorityQueue.offer(parent);
            root = parent;
        }
        return root;
    }
}
