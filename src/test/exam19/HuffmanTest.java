package exam19;

import exam19.Huffman.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class HuffmanTest {
    @Test
    @Timeout(100)
    public void exampleEncoding() {
        Node root = new Node((char) 0, 1);
        Node eNode = new Node('e', 0.6, root);
        Node xNode = new Node('x', 0.4, root);
        root.setLeftChild(eNode);
        root.setRightChild(xNode);
        Assertions.assertEquals("0", Huffman.encode(eNode));
        Assertions.assertEquals("1", Huffman.encode(xNode));
    }

    /**
     * Tree looks like this:
     * root
     * combined         combined
     * e          x     f          g
     */
    @Test
    @Timeout(100)
    public void exampleEncoding2() {
        Node root = new Node((char) 0, 1);
        Node leftRoot = new Node((char) 0, 0.5, root);
        root.setLeftChild(leftRoot);
        Node eNode = new Node('e', 0.25, leftRoot);
        leftRoot.setLeftChild(eNode);
        Node xNode = new Node('x', 0.25, leftRoot);
        leftRoot.setRightChild(xNode);
        Node rightRoot = new Node((char) 0, 0.5, root);
        root.setRightChild(rightRoot);
        Node fNode = new Node('f', 0.25, rightRoot);
        rightRoot.setLeftChild(fNode);
        Node gNode = new Node('g', 0.25, rightRoot);
        rightRoot.setRightChild(gNode);
        Assertions.assertEquals("00", Huffman.encode(eNode));
        Assertions.assertEquals("01", Huffman.encode(xNode));
        Assertions.assertEquals("10", Huffman.encode(fNode));
        Assertions.assertEquals("11", Huffman.encode(gNode));
    }

    @Test
    @Timeout(100)
    public void exampleBuildTree() {
        int n = 2;
        char[] chars = { 0, 'a', 'b' };
        double[] freq = { 0, 0.7, 0.3 };
        Node tree = Huffman.buildHuffman(n, chars, freq);
        Assertions.assertNotNull(tree);
        Assertions.assertNotNull(tree.leftChild);
        Assertions.assertNotNull(tree.rightChild);
        if (tree.leftChild.symbol != 'a') {
            Assertions.assertEquals('a', tree.rightChild.symbol);
            Assertions.assertEquals('b', tree.leftChild.symbol);
        } else {
            Assertions.assertEquals('a', tree.leftChild.symbol);
            Assertions.assertEquals('b', tree.rightChild.symbol);
        }
    }
}