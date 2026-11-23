package part1_textstats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Part 1. Text File Statistics
public class TextFileStats {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Part 1.1 Input path
        System.out.print("Enter file path: ");
        String path = sc.nextLine().trim();

        int totalLines = 0;
        int nonEmpty = 0;
        int chars = 0;

        // Part 1.2 Read file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalLines++;
                chars += line.length(); // spaces too

                // skip blank lines
                if (!line.trim().isEmpty()) {
                    nonEmpty++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Part 1.3 Print stats
        System.out.println("Total lines: " + totalLines);
        System.out.println("Non-empty lines: " + nonEmpty);
        System.out.println("Total characters: " + chars);
    }
}
