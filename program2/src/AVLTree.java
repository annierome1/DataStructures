//https://www.javatpoint.com/method-overriding-in-java
//https://www.geeksforgeeks.org/super-keyword/
// https://medium.com/@AlexanderObregon/avl-trees-an-in-depth-look-9a0e0481487a

public class AVLTree extends BinaryTree {

    @Override
    public void insert(String title) {
        root = insert(root, title);
    }

    @Override
    public void remove(String title) {
        root = remove(root, title);
    }

    @Override
    protected Node insert(Node node, String title) {
        if (node == null) {
            return new Node(title);
        }
        if (title.compareTo(node.title) < 0) {
            node.left = insert(node.left, title);
        } else if (title.compareTo(node.title) > 0) {
            node.right = insert(node.right, title);
        } else {
            return node;
        }
        //update and balance tree once book is inserted
        updateHeight(node);
        return balance(node);
    }

    @Override
    protected Node remove(Node node, String title) {
        if (node == null) {
            return null;
        }
        //remove from left subtree
        if (title.compareTo(node.title) < 0) {
            node.left = remove(node.left, title);
            //remove from right subtree
        } else if (title.compareTo(node.title) > 0) {
            node.right = remove(node.right, title);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node temp = findMin(node.right);
                node.title = temp.title;
                node.right = remove(node.right, temp.title);
            }
        }

        if (node == null) {
            return null;
        }

        updateHeight(node);
        return balance(node);
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }

        // Right heavy
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
