import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DFS_Enhanced_Implementation {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Object of graph is created.
        Graph g = new Graph();

        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader("input.txt");
            Scanner fileScanner = new Scanner(input);

            // Content in text file is in the form of adjacency matrix
            // Every first character of each line is the source vertex and
            // the remaining characters in the line is its adjacent vertex
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] vertices = line.split(" ");
                int source = Integer.parseInt(vertices[0]);
                for (int i = 1; i < vertices.length; i++) {
                    int destination = Integer.parseInt(vertices[i]);
                    g.addEdge(source, destination);
                }
            }

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}