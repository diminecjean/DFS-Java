import java.io.FileReader;
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

        boolean exit = false;

        while (!exit) {
            // User Menu
            System.out.println(" Menu");
            System.out.println("-------");
            System.out.println("[1] Print Adjacency List");
            System.out.println("[2] Print Adjacency Matrix");
            System.out.println("[3] Add a Vertex");
            System.out.println("[4] Add an Edge");
            System.out.println("[5] Get the Number of Vertices");
            System.out.println("[6] Get the Number of Edges");
            System.out.println("[7] Find a Path for Two Vertices");
            System.out.println("[8] Exit");
            System.out.print("\nSelect an option by entering the corresponding number: ");
            int option = sc.nextInt();

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            switch (option) {
                case 1: // Print Adjacency List
                {
                    System.out.println("Adjacency List of Graph G");
                    System.out.println("-----------------------------");
                    System.out.print(g.AdjacencyList());
                }

                    break;
                case 2: // Print Adjacency Matrix
                {
                    System.out.println("Adjacency Matrix of Graph G");
                    System.out.println("-----------------------------");
                    g.AdjacencyMatrix();
                }
                    break;
                case 3: // Add a vertex
                {
                    System.out.print("Vertex to be added: ");
                    int v = sc.nextInt();
                    g.addVertex(v);
                }
                    break;
                case 4: // Add an Edge
                {
                    System.out.print("Source Vertex: ");
                    int s = sc.nextInt();
                    System.out.print("Destination Vertex: ");
                    int d = sc.nextInt();
                    System.out.print("\n");
                    g.addEdge(s, d);

                }
                    break;
                case 5: // Get the number of Vertices
                {
                    int numVertex = g.getNumVertex();
                    System.out.println("Number of Vertices in the graph: " + numVertex);
                }
                    break;
                case 6: // Get the number of Edges
                {
                    g.getNumEdges();
                }
                    break;
                case 7: // Find a path for the 2 vertices
                {
                    System.out.println("Find a path with DFS");
                    System.out.println("---------------------");
                    System.out.print("Source Vertex: ");
                    int v = sc.nextInt();
                    System.out.print("Destination Vertex: ");
                    int d = sc.nextInt();

                    g.FindPathDFS(v, d);
                }
                    break;
                case 8: // Exit the loop
                    exit = true;
                    break;
                default:
                    System.out.println("Please enter an option within the given range.");
            }

            System.out.print("\n\nContinue? (y/n): ");
            char c = sc.next().charAt(0);

            if (c != 'y' || c != 'y')
                break;

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }
}