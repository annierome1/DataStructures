//KMP Search Class
//https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

class KMP {
    private LinkedList list;

    public KMP(LinkedList list) {
        this.list = list;
    }

    // Function to preprocess the pattern and find the LPS array
    private int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m]; // LPS array to hold the longest prefix suffix values
        int len = 0;
        int i = 1;

        lps[0] = 0; // LPS of the first character is set to 0

        //Fill LPS array
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // Function to search the pattern in the linked list using KMP algorithm
    public boolean search(String pattern) {
        int[] lps = computeLPSArray(pattern);
        int m = pattern.length();
        int i = 0; // Index for pattern
        Node current = list.getHead(); // Index for text
        int index = 0; // Starting index for the pattern match

        while (current != null) {
            if (pattern.charAt(i) == current.data) {
                i++;
                current = current.next;
                if (i == m) {
                    return true;
                }
            } else {
                if (i != 0) {
                    i = lps[i - 1];
                } else {
                    current = current.next;
                }
            }
            index++;
        }
        return false;
    }
}
