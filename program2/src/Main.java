//ChatGPT helped with formatting this Main program

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();
        AVLTree avlTree = new AVLTree();
        RBTree rbTree = new RBTree();

        System.out.println("Choose the tree to analyze:");
        System.out.println("1. Binary Tree");
        System.out.println("2. AVL Tree");
        System.out.println("3. Red-Black Tree");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String booksFile = "SciFiLiSorted.txt";

        // Place titles from txt file into array
        String[] titles = readTitlesFromFile(booksFile);

        if (titles == null) {
            System.err.println("Failed to read titles from file.");
            return;
        }
        measureTree(titles, choice, binaryTree, avlTree, rbTree);
    }

    //Function that reads titles from file and places into string array
    //Some help from chatGPT
    private static String[] readTitlesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().map(String::trim).toArray(String[]::new);
        } catch (IOException e) {
            System.err.println("An error occurred while reading " + fileName + ": " + e.getMessage());
            return null;
        }
    }

    private static void measureTree(String[] titles, int choice, BinaryTree binaryTree, AVLTree avlTree, RBTree rbTree) {
        long startTime, endTime, duration;

        // Measure tree building time
        startTime = System.nanoTime();
        switch (choice) {
            case 1:
                for (String title : titles) {
                    binaryTree.insert(title);
                }
                break;
            case 2:
                for (String title : titles) {
                    avlTree.insert(title);
                }
                break;
            case 3:
                for (String title : titles) {
                    rbTree.insert(title);
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime); // Time in nanoseconds
        System.out.println("Time to build the tree: " + duration + " ns");

        // Measure search time
        String searchTitle = "ACOTAR";
        startTime = System.nanoTime();
        boolean found = false;
        switch (choice) {
            case 1:
                found = binaryTree.search(searchTitle);
                break;
            case 2:
                found = avlTree.search(searchTitle);
                break;
            case 3:
                found = rbTree.search(searchTitle);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime); // Time in nanoseconds
        System.out.println("Searching for '" + searchTitle + "': " + found + " (Time: " + duration + " ns)");

        // Remove "Dune Chronicles" from tree
        String removeTitle = "Dune Chronicles";
        startTime = System.nanoTime();
        switch (choice) {
            case 1:
                binaryTree.remove(removeTitle);
                break;
            case 2:
                avlTree.remove(removeTitle);
                break;
            case 3:
                // Couldn't get this one
                System.out.println("Removing from RBTree is tough :(");
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime); // Time in nanoseconds
        System.out.println("Removing '" + removeTitle + "' (Time: " + duration + " ns)");

        // Verify removal by searching again
        switch (choice) {
            case 1:
                found = binaryTree.search(removeTitle);
                break;
            case 2:
                found = avlTree.search(removeTitle);
                break;
            case 3:
                found = rbTree.search(removeTitle);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        System.out.println("Searching for '" + removeTitle + "' after removal: " + found);
    }
}
