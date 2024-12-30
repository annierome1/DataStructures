// https://www.geeksforgeeks.org/insertion-in-red-black-tree/
// https://www.cs.dartmouth.edu/~thc/cs10/lectures/0519/RBTree.java

public class RBTree extends BinaryTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    //Red-black tree node that inherits from the Node class
    private class RBNode extends Node {
        boolean color;
        RBNode parent;

        RBNode(String title) {
            super(title);
            this.color = RED; //new nodes are red
            this.parent = null;
        }
    }

    @Override
    public void insert(String title) {
        RBNode newNode = new RBNode(title);
        if (root == null) {
            root = newNode;
            root.color = BLACK; //root starts as black
            return;
        }
        RBNode temp = (RBNode) root;
        RBNode parent = null;
        while (temp != null) {
            parent = temp;
            if (title.compareTo(temp.title) < 0) {
                temp = (RBNode) temp.left;
            } else {
                temp = (RBNode) temp.right;
            }
        }
        newNode.parent = parent;
        if (title.compareTo(parent.title) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        fixInsert(newNode);
    }

    private void fixInsert(RBNode node) {
        RBNode parent, grandparent, uncle;
        while (node != root && node.parent != null && node.parent.color == RED) {
            parent = node.parent;
            grandparent = parent.parent;
            if (grandparent == null) break;
            // Parent is left child of the grandparent
            if (parent == grandparent.left) {
                uncle = (RBNode) grandparent.right; //sibling of curr parent node
                //fix insert for right nodes
                // Both parent and sibling are red
                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    //node is right grandchild, left rotate
                    if (node == parent.right) {
                        node = parent;
                        leftRotate(node);
                    }
                    //node is left grandchild, right rotate
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rightRotate(grandparent);
                }
            } else {
                // Parent is right child of the grandparent
                uncle = (RBNode) grandparent.left;
                if (uncle != null && uncle.color == RED) {

                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grandparent.color = RED;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rightRotate(node);
                    }
                    parent.color = BLACK;
                    grandparent.color = RED;
                    leftRotate(grandparent);
                }
            }
        }
        root.color = BLACK;
    }

    private void leftRotate(RBNode x) {
        RBNode y = (RBNode) x.right;
        // Turn y's left subtree into x's right subtree
        if (y == null) return;
        x.right = y.left;
        if (y.left != null) {
            ((RBNode) y.left).parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBNode x) {
        RBNode y = (RBNode) x.left;
        // Turn y's right subtree into x's left subtree
        if (y == null) return;
        x.left = y.right;
        if (y.right != null) {
            ((RBNode) y.right).parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
}
