//Double Linked List so that prev can be used for BoyerM search

class LinkedList  {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    public void add(char data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }

    public Node getHead() {
        return head;
    }

    public int length() {
        return length;
    }
}

class Node {
    char data;
    Node next;
    Node prev;

    public Node(char data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
