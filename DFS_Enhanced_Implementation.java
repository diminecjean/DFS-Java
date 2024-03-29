// DFS - using hashmap for graph and stack for list of edges
// Purpose: Find a route from a source airport to a destination airport within Malaysia
//          (Find a path from the source vertex to destination vertex)
// Disclaimer: The path found is not guaranteed to be the shortest path
// Programmers: Looi Wei En, Lee Ying Hooi, Wong Jia Yi

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;

class Graph {
    // Using HashMap to create graph in the form of adjacency list
    private Map<Integer, List<Integer>> map = new HashMap<>();

    // Edges visited that is included in the path from source to destination vertex
    protected Stack<Integer> verticeList;

    // Edges visited that are not in the final path
    private Stack<Integer> visitedList;

    // Number of times each vertex is visited during DFS
    private Map<Integer, Integer> num = new HashMap<>();

    private int numVertices = 0;

    // Constructor
    public Graph() {
        verticeList = new Stack<>();
        visitedList = new Stack<>();
    }

    // Method to add a vertex
    public void addVertex(int vertex) {
        if (!map.containsKey(vertex)) {
            map.put(vertex, new LinkedList<Integer>());
            System.out.println("Vertex " + vertex + " added.");
        } else
            System.out.println("Vertex " + vertex + " already exists.");
    }

    // Method to add an edge given the source and destination
    public void addEdge(int source, int destination) {
        // If the source vertex does not exist, add as new vertex
        if (!map.containsKey(source))
            addVertex(source);

        // If the destination vertex does not exist, add as new vertex
        if (!map.containsKey(destination))
            addVertex(destination);

        // Add the destination vertex as the value correspondint to source vertex as key
        // This establishes a relationship from the source to destination vertex
        if (map.get(source).contains(destination)) {
            System.out.println("Edge from " + source + " to " + destination + " already exists.");
        } else {
            map.get(source).add(destination);
            System.out.println("Edge from " + source + " to " + destination + " is added.");
        }

    }

    // Method to remove a vertex
    public void removeVertex(int v) {
        // Check if the vertex vertex exists
        if (!map.containsKey(v))
            System.out.print(v + "is not found in the graph");
        else {
            map.remove(v);
            System.out.print(v + "is removed");
        }
    }

    // Method to remove an edge
    public void removeEdge(int source, int destination) {
        if (ifVertexExist(source) && ifVertexExist(destination)) {
            // If edge exists between source and destination,
            // remove the edge (remove value from key)
            if (map.get(source).contains(destination)) {
                map.remove(source, destination);
                System.out.print("Edge is removed.");
            } else
                System.out.print("There is no edge from " + source + " to " + destination);
        } else {
            System.out.println("Please enter a valid value.");
        }
    }

    // Method to count the number of vertices present
    public int getNumVertex() {
        numVertices = map.keySet().size();
        return numVertices;
    }

    // Method to count the number of edges present
    public int getNumEdges() {
        int count = 0;
        for (int v : map.keySet()) {
            count += map.get(v).size();
        }
        System.out.println("Number of edges in the graph: " + count);
        System.out.println("(Each direction of edge is counted as one edge.)");
        return count;
    }

    // Method to determine if a vertex exists in the graph
    public boolean ifVertexExist(int vertex) {
        if (!map.containsKey(vertex))
            return false;
        else
            return true;
    }

    // Method to determine if there is an edge between two vertices
    public boolean ifEdgeExist(int source, int destination) {
        if (!map.get(source).contains(destination))
            return false;
        else
            return true;
    }

    // Method to show adjacency list
    public String AdjacencyList() {
        StringBuilder builder = new StringBuilder();

        for (int v : map.keySet()) {
            builder.append(Integer.toString(v) + ": ");
            for (int w : map.get(v)) {
                builder.append(Integer.toString(w) + " ");
            }
            builder.append("\n");
        }
        return (builder.toString());
    }

    // Method to show adjacency matrix
    public void AdjacencyMatrix() {
        getNumVertex();

        int[][] adjacencyMatrix = new int[(numVertices + 1)][(numVertices + 1)];

        adjacencyMatrix[0][0] = 0;

        // Initialize the header column and row
        for (int i = 1; i < numVertices + 1; i++) {
            adjacencyMatrix[i][0] = (i - 1);
            adjacencyMatrix[0][i] = (i - 1);
        }

        // Create the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            List<Integer> neighbors = map.get(i);
            for (int j = 0; j < numVertices; j++) {
                if (neighbors != null && neighbors.contains(j)) {
                    adjacencyMatrix[i + 1][j + 1] = 1;
                } else
                    adjacencyMatrix[i + 1][j + 1] = 0;
            }
        }

        // Display the adjacency matrix
        for (int i = 0; i < numVertices + 1; i++) {
            for (int j = 0; j < numVertices + 1; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
                if (j == 0)
                    System.out.print("|");
            }
            System.out.println();
            if (i == 0)
                System.out.println("-----------------");
        }
    }

    // Method to find path
    public void FindPathDFS(int v, int d) {
        if (map.containsKey(v) && map.containsKey(d)) {
            for (int vertex : map.keySet()) {
                num.put(vertex, 0);
            }
            verticeList.clear();
            visitedList.clear();

            verticeList.add(v); // add source edge into path

            while (v != d && num.get(v) == 0) {
                DFS(v, d);
            }

            if (num.get(d) == 0) {
                System.out.print("\nThere is no path from " + v + " to " + d);
            }
        }
    }

    // DFS Method
    public int DFS(int v, int d) {
        num.put(v, num.get(v) + 1);
        for (int u : map.get(v)) {
            if ((num.get(u) == 0) && (v != d)) {
                // If the encountered vertex does not have an outgoing edge,
                // and is also not the destination vertex,
                // skip the vertex
                if (map.get(u).isEmpty() && u != d)
                    continue;
                else {
                    verticeList.push(u);
                    return DFS(u, d);
                }

            } else if (v == d) {
                return d;
            }
            // If the encountered vertex, u has been encountered before (num(u)!=0),
            // and u is the last value in the adjacency list of v,
            else if ((num.get(u) != 0) && (u == map.get(v).get(map.get(v).size() - 1))) {
                if (u == d) {
                    verticeList.push(u);
                    return d;
                }
                // visitedList contains vertices that are visited but not
                // considered in the final path
                // if v is included in visitedList, the current iteration of the loop is skipped
                // else, v will be removed from verticeList and added into visitedList,
                // then the recursion continues with the last vertext in the verticeList
                // as the source vertex
                else {
                    if (visitedList.contains(v))
                        continue;
                    else {
                        visitedList.push(verticeList.pop());
                        DFS(verticeList.lastElement(), d);
                    }
                }
            }
        }
        return d;
    }
}

public class DFS_Enhanced_Implementation {
    // Method 1 to read file: reads file into Graph
    public void readFileIntoGraph(String filename, Graph g) {
        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader(filename);
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

    // Method 2 to read file: reads file into hashmap
    public void readFileIntoHashMap(String filename, HashMap<Integer, String> map) {
        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader(filename);
            Scanner fileScanner = new Scanner(input);

            // Content in text file is in the form of adjacency matrix
            // Every first character of each line is the source vertex and
            // the remaining characters in the line is its adjacent vertex
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] vertices = line.split(" ");
                for (int i = 0; i < vertices.length - 1; i++) {
                    int key = Integer.parseInt(vertices[i]);
                    String value = vertices[i + 1];
                    map.put(key, value);
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

    // Method 3 to read file: Reads file and displays exactly as read
    public static void readFileAndDisplay(String filename) {
        try {
            // Creates a reader using the FileReader
            FileReader input = new FileReader(filename);
            Scanner fileScanner = new Scanner(input);

            // Content is displayed exactly the same
            // as the source file
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to display the front page before the main page
    public static void FrontPage() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        String pattern = "\n\n            \t\t        __|__\n" +
                "       \t\t     \t --@--@--(_)--@--@--\n" +
                "\t\t\t\t  +\n";

        System.out.println(pattern);


        // Define the characters for each line of the 3D text
        String[] lines = {
            "\t     __        __                   ",
            "\t     \\ \\      / /__ _  ____  ___   __  __  ___",
            "\t      \\ \\ /\\ / / _ \\ |/  __\\/   \\ /  \\/  \\/ _ \\",
            "\t       \\ V  V /  __/ |__(__| ()  |       |  __/",
            "\t        \\_/\\_/ \\___|_____|_/\\___/|_/\\_/\\__\\___|",
            "                                         "
        };

        // Print each line of the 3D text
        for (String line : lines) {
            System.out.println(line);
        }

        // Print the sentence below the 3D text
        System.out.println("\n");

       
        System.out.println("\t=======================================================");
        System.out.println("\t    This is Malaysia Airlines' Route Checking System    ");
        System.out.println("\t=======================================================");
        System.out.println("                                    ");
        System.out.println("                                    ");

        System.out.println("\t\t\tIn this system," +
        "\nYou will be able to check the routes between airports located in Malaysia");

        System.out.println("\n\nPress Enter to Start checking Routes...\n");
        sc.nextLine();

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        pattern = "\n\n                              _\n" +
                "                              -=\\`\\\n" +
                "                          |\\ ____\\_\\__\n" +
                "Taking Off...              -=\\c`\"\"\"\"\"\"\"\" \"`)\n" +
                "                           `~~~~~/ /~~`\n" +
                "                             -==/ /\n" +
                "                               '-'";

        System.out.println(pattern);
        System.out.print("3");
        Thread.sleep(1000);
        System.out.print("\b\b2");
        Thread.sleep(1000);
        System.out.print("\b\b1");
        Thread.sleep(1000);

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // main method
    public static void main(String args[]) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        Graph g = new Graph(); // Object of graph is created.
        HashMap<Integer, String> codes = new HashMap<>(); // to store the IATA airport codes and their corresponding
                                                          // value
        DFS_Enhanced_Implementation enhancedInstance = new DFS_Enhanced_Implementation();

        enhancedInstance.readFileIntoGraph("domestic_route.txt", g);
        enhancedInstance.readFileIntoHashMap("IATA_airport_codes.txt", codes);

        // Display front page
        FrontPage();

        // Main page
        boolean exit = false;
        while (exit != true) {

            System.out.println("----------------------------------------");
            System.out.println("Here's The List of Airports in Malaysia");
            System.out.println("----------------------------------------");
            readFileAndDisplay("airport_name_list.txt");

            System.out.println("\n========================================================================");
            System.out.println(" Insert your departure and destination airports by entering their");
            System.out.println(" corresponding integer codes to retrieve the route between the airports.");
            System.out.println("\n (eg. enter '0' for Kuala Lumpur International Airport)");
            System.out.println("========================================================================\n");

            System.out.print("Departure Airport: ");
            int v = sc.nextInt();
            System.out.print("Destination Airport: ");
            int d = sc.nextInt();

            g.FindPathDFS(v, d);

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

            List<Integer> path = new ArrayList<>(); // get values of verticeList
            path = g.verticeList;

            if (g.ifVertexExist(v) && g.ifVertexExist(d)) {
                System.out.println("\nShowing Flight Path from " + codes.get(v) + " to " + codes.get(d) + ": ");
                System.out.println("-------------------------------------");
                System.out.print("\n" + codes.get(v));
                for (int i = 1; i < path.size(); i++) {
                    String IATA = codes.get(path.get(i));
                    System.out.print(" -> " + IATA);
                }
            } else {
                if (!g.ifVertexExist(v)) {
                    System.out.println(v + " is not a valid code.");
                }
                if (!g.ifVertexExist(d)) {
                    System.out.println(d + " is not a valid code.");
                }
            }

            System.out.println("\n\n\nDo You Wish to Check Another Route? (y for yes/n for no)");
            char option = sc.next().charAt(0);
            if (option == 'y' || option == 'Y')
                exit = false;
            else
                exit = true;

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();

        }

        System.out.println("\n\n==============================================");
        System.out.println("\n   Thank you for visiting Malaysia Airlines! ");
        System.out.println("        ~~~~ See you next time ~~~~ ");
        System.out.println("\n==============================================");

        String pattern = "\n\n                    __|__\n" +
        "             --@--@--(_)--@--@--\n" +
        "\t\t      +\n";

System.out.println(pattern);

        System.out.println("\n\nExiting program...\n");

        Thread.sleep(1000);

        // sc.close();
    }
}