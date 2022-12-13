package exam2021;


import java.util.Comparator;
import java.util.PriorityQueue;

public class TernaryHuffman {
    /**
     * NOTE: You should ensure that if a Node is a part of a tree, then all nodes in the tree have their
     * `parent`, `leftChild`, `middleChild`, and `rightChild` set appropriately! You may add methods to
     * this class, provided you do not change the names of existing methods or fields!
     */

    static class Node {

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
        StringBuilder sb = new StringBuilder();
        var pointer = root;
        for (int i = 0; i < encrypted.length(); i++) {
            var bit = encrypted.charAt(i);
            switch (bit) {
                case '0':
                    if (pointer.leftChild != null) {
                        pointer = pointer.leftChild;
                    }
                    break;

                case '1':
                    if (pointer.middleChild != null) {
                        pointer = pointer.middleChild;
                    }
                    break;

                case '2':
                    if (pointer.rightChild != null) {
                        pointer = pointer.rightChild;
                    }
                    break;
            }
            if (pointer.leftChild == null && pointer.rightChild == null && pointer.middleChild == null) {
                sb.append(pointer.symbol);
                pointer = root;
            }
        }
        return sb.toString();
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
        PriorityQueue<Node> nodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Double.compare(o1.frequency, o2.frequency);
            }
        });
        for (int i = 1; i <= n; i++) {
            nodes.add(new Node(characters[i], frequencies[i]));
        }

        if(nodes.size() % 2 == 0) nodes.add(new Node((char) 0 , 0));

        while (nodes.size() > 1) {
            var left = nodes.poll();
            var middle = nodes.poll();
            var right = nodes.poll();

            var parent = new Node((char) 0, left.getFrequency() + middle.getFrequency() + right.frequency, left, middle, right);
            left.setParent(parent);
            middle.setParent(parent);
            right.setParent(parent);
            nodes.add(parent);
        }
        return nodes.poll();
    }
}



