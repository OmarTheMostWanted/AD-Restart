package DivideAndConquer;

import java.util.Stack;

public class DepthFirstSearch {
    static class BinaryTree {

        private int key;

        private BinaryTree left, right;

        /**
         * Simple constructor.
         *
         * @param key to set as key.
         */
        public BinaryTree(int key) {
            this.key = key;
        }

        /**
         * Extended constructor.
         *
         * @param key to set as key.
         * @param left to set as left child.
         * @param right to set as right child.
         */
        public BinaryTree(int key, BinaryTree left, BinaryTree right) {
            this.key = key;
            setLeft(left);
            setRight(right);
        }

        public int getKey() {
            return key;
        }

        /** @return the left child. */
        public BinaryTree getLeft() {
            return left;
        }

        /** @return the right child. */
        public BinaryTree getRight() {
            return right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        /** @param left to set */
        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        /** @param right to set */
        public void setRight(BinaryTree right) {
            this.right = right;
        }
    }

    int count;


    /**
     * Recursively searches for the element. Returns true if element can be found, else false.
     *
     * @param tree - tree that you need to look in.
     * @param element - the element that you are looking for.
     * @return true if found, else false.
     */

    public boolean search(BinaryTree tree, int element) {
        if(tree == null) return false;
        if(tree.getKey() == element) return true;
        var l = tree.hasLeft() && search(tree.getLeft() , element);
        var r = tree.hasRight() && search(tree.getRight()  , element);
        return l || r;
    }

    public boolean searchStack(BinaryTree tree, int element) {
        if(tree == null) return false;
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()){
            var t = stack.pop();

            if(t.getKey() == element) return true;
            if(t.hasLeft()) {stack.push(t.getLeft()); count++; };
            if(t.hasRight()) {stack.push(t.getRight()); count++; };
        }
        return false;
    }

}
