//Brute Force search class

public class BruteF {
    private LinkedList list;

    public BruteF(LinkedList list) {
        this.list = list;
    }

    public boolean search(String pattern) {
        Node current = list.getHead();
        int patternLength = pattern.length();
        while (current != null) {
            Node start = current;
            int i;
            for (i = 0; i < patternLength; i++) {
                if (start == null || start.data != pattern.charAt(i)) {
                    break;
                }
                start = start.next;
            }
            if (i == patternLength) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

