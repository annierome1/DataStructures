public class Node {
    public boolean color;
    String title;
    Node left;
    Node right;
    int height;


    public Node(String title) {
        this.title = title;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
