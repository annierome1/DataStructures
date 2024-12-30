//Boyer Moore Class
//https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
//Help from Dr. Lori and ChatGPT

class BoyerM {
    private LinkedList list;

    public BoyerM(LinkedList list) {
        this.list = list;
    }

    // function to preprocess the pattern for the bad character heuristic
    private int[] preprocessBadCharacterHeuristic(String pattern) {
        int[] badCharTable = new int[256];
        int m = pattern.length();

        // initialize table
        for (int i = 0; i < 256; i++) {
            badCharTable[i] = -1;
        }


        for (int i = 0; i < m; i++) {
            badCharTable[pattern.charAt(i)] = i;
        }

        return badCharTable;
    }

    // Search Function
    public boolean search(String pattern) {
        int[] badCharTable = preprocessBadCharacterHeuristic(pattern);
        int m = pattern.length();

        Node head = list.getHead();
        Node current = head;
        Node patternEnd = head;

        for (int i = 0; i < m - 1 && patternEnd != null; i++) {
            patternEnd = patternEnd.next;
        }

        int index = 0;

        while (patternEnd != null) {
            Node scan = patternEnd;
            int j = m - 1;

            // Compare the pattern from end to start
            while (j >= 0 && scan != null && scan.data == pattern.charAt(j)) {
                scan = scan.prev;
                j--;
            }

            if (j < 0) {
                return true;
            } else {
                int badCharShift = badCharTable[pattern.charAt(j)];
                int shift = Math.max(1, j - badCharShift);

                // Update current and end pointers
                for (int i = 0; i < shift && patternEnd != null; i++) {
                    patternEnd = patternEnd.next;
                    current = current.next;
                    index++;
                }
            }
        }
        return false;
    }
}