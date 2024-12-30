public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(String title) {
        this.root = insert(this.root, title);
    }

    protected Node insert(Node curr, String title) {
        if (curr == null) {
            return new Node(title);
        }
        if (title.compareTo(curr.title) < 0) {
            curr.left = insert(curr.left, title);
        } else if (title.compareTo(curr.title) > 0) {
            curr.right = insert(curr.right, title);
        }
        return curr;
    }

    public boolean search(String title) {
        return search(this.root, title);
    }

    private boolean search(Node curr, String title) {
        if (curr == null) {
            return false;
        }
        if (title.equals(curr.title)) {
            return true;
        }
        return title.compareTo(curr.title) < 0
                ? search(curr.left, title)
                : search(curr.right, title);
    }

    public void remove(String title) {
        this.root = remove(this.root, title);
    }

    protected Node remove(Node curr, String title) {
        if (curr == null) {
            return null;
        }
        if (title.equals(curr.title)) {
            if (curr.left == null && curr.right == null) {
                return null;
            }
            if (curr.left == null) {
                return curr.right;
            }
            if (curr.right == null) {
                return curr.left;
            }
            Node smallestValue = getMin(curr.right);
            curr.title = smallestValue.title;
            curr.right = remove(curr.right, smallestValue.title);
            return curr;
        }
        if (title.compareTo(curr.title) < 0) {
            curr.left = remove(curr.left, title);
            return curr;
        }
        curr.right = remove(curr.right, title);
        return curr;
    }

    protected Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
    }

    protected Node getMax(Node node) {
        return node.right == null ? node : getMax(node.right);
    }

    public Node getMin() {
        return getMin(root);
    }

    public Node getMax() {
        return getMax(root);
    }
}
