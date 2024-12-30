//A.Rome Program 1, FIND IT
//Utilizes Brute Force, Boyer Moore, and KMP search algorithms to find the pattern the user inputs and outputs time in nanoseconds with conclusion of fastest search

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


//Main
public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        String fileName = "prog1input1.txt";
        //turn txt file into LinkedList for each character
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                for (char c : line.toCharArray()) {
                    linkedList.add(c);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        // linkedList.printList();

        // Ask user for search pattern to search for
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the pattern to search for: ");
        String pattern = scanner.nextLine();
        scanner.close();

        // Brute force search
        BruteF bfs = new BruteF(linkedList);
        long startTime = System.nanoTime();
        boolean found = bfs.search(pattern);
        long endTime = System.nanoTime();
        long bruteFDuration = (endTime - startTime);
        System.out.println("Brute Force Search: Pattern '" + pattern + "' found: " + found);
        System.out.println("Time taken: " + bruteFDuration + " nanoseconds");

        // Boyer-Moore search
        BoyerM bms = new BoyerM(linkedList);
        startTime = System.nanoTime();
        found = bms.search(pattern);
        endTime = System.nanoTime();
        long boyerMDuration = (endTime - startTime);
        System.out.println("Boyer-Moore Search: Pattern '" + pattern + "' found: " + found);
        System.out.println("Time taken: " + boyerMDuration + " nanoseconds");

        // KMP search
        KMP kmp = new KMP(linkedList);
        startTime = System.nanoTime();
        found = kmp.search(pattern);
        endTime = System.nanoTime();
        long kmpDuration = (endTime - startTime);
        System.out.println("KMP Search: Pattern '" + pattern + "' found: " + found);
        System.out.println("Time taken: " + kmpDuration + " nanoseconds");

        // Output fastest algorithm
        String fastestAlgorithm;
        long fastestTime = Math.min(bruteFDuration, Math.min(boyerMDuration, kmpDuration));
        if (fastestTime == bruteFDuration) {
            fastestAlgorithm = "Brute Force Search";
        } else if (fastestTime == boyerMDuration) {
            fastestAlgorithm = "Boyer-Moore Search";
        } else {
            fastestAlgorithm = "KMP Search";
        }

        System.out.println("The fastest algorithm was: " + fastestAlgorithm + " with a time of " + fastestTime + " ns");

    }
}
