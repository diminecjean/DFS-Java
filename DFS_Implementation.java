import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

// Graph ADT
class Graph {
    // Using HashMap to create graph in the form of adjacency list
    private Map<Integer, List<Integer>> map = new HashMap<>();

    // Edges visited that is included in the path from source to destination vertex
    private Stack<Integer> edgeList;

    // Edges visited that are not in the final path
    private Stack<Integer> visitedList;

    // Number of times each vertex is visited during DFS
    private Map<Integer, Integer> num = new HashMap<>();

    private int i;
    private int numVertices = 0;

    // Constructor
    public Graph() {
        edgeList = new Stack<>();
        visitedList = new Stack<>();
        i = 0;
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
        // Check if destination vertex exists
        ifVertexExist(source);

        // Check if destination vertex exists
        ifVertexExist(destination);

        // If edge exists between source and destination,
        // remove the edge (remove value from key)
        if (map.get(source).contains(destination)) {
            map.remove(source, destination);
            System.out.print("Edge is removed.");
        } else
            System.out.print("There is no edge from " + source + " to " + destination);

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
    public void ifVertexExist(int vertex) {
        if (!map.containsKey(vertex))
            System.out.println("Vertex " + vertex + " is not found in the graph");
        else
            System.out.println("Vertex" + vertex + " is found in the graph.");
    }

    // Method to determine if there is an edge between two vertices
    public void ifEdgeExist(int source, int destination) {
        if (!map.get(source).contains(destination))
            System.out.print("There is no edge from " + source + " to " + destination);
        else
            System.out.print("There exist an edge from " + source + " to " + destination);
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
            edgeList.clear();
            visitedList.clear();
            i = 0;

            while (v != d && num.get(v) == 0) {
                DFS(v, d);
            }

            // Output the edges
            if (num.get(d) != 0) {
                System.out.print("\nPath from " + v + " to " + d + ": ");
                System.out.print("\n" + v);
                for (int i = 0; i < edgeList.size(); i++) {
                    System.out.print(" -> " + edgeList.get(i));
                }
            } else {
                System.out.print("\nThere is no path from " + v + " to " + d);
            }
        } else {
            System.out.print("\n");
            ifVertexExist(v);
            ifVertexExist(d);
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
                System.out.println(map.get(u));
                int x = map.get(u).get(map.get(u).size() - 1);
                System.out.println("u: " + u + ", last item in list: " + x);
                if (map.get(u).isEmpty() && u != d)
                    continue;
                else {
                    System.out.println(u);
                    edgeList.push(u);
                    return DFS(u, d);
                }

            } else if ((num.get(u) != 0) && (u == map.get(v).get(map.get(v).size() - 1))) {
                if (u == d) {
                    edgeList.push(u);
                    return d;
                } else {
                    if (visitedList.contains(v))
                        continue;
                    else {
                        System.out.println("in else if");
                        visitedList.push(edgeList.pop());
                        DFS(edgeList.lastElement(), d);
                    }
                }
            }
        }
        return d;
    }
}

public class DFS_Implementation {
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

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Object of graph is created.
        Graph g = new Graph();

        // System.out.print("Number of edges in the graph: ");
        // int num = sc.nextInt();

        // Add edges into the graph
        // for (int i = 0; i < num; i++) {
        // System.out.println("\nEdge " + (i + 1));
        // System.out.print("Source vertex for edge " + (i + 1) + ": ");
        // int source = sc.nextInt();
        // System.out.print("Source vertex for edge " + (i + 1) + ": ");
        // int destination = sc.nextInt();

        // g.addEdge(source, destination);
        // }

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

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

        sc.close();
    }
}