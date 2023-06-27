import java.util.*;

// Graph ADT
class Graph<T> {
    // Using HashMap to create graph in the form of adjacency list
    private Map<T, List<T>> map = new HashMap<>();

    // Function to add a vertex
    public void addVertex(T vertex) {
        map.put(vertex, new LinkedList<T>());
    }

    // Function to add an edge given the source and destination
    public void addEdge(T source, T destination) {
        // If the source vertex does not exist, add as new vertex
        if (!map.containsKey(source))
            addVertex(source);

        // If the destination vertex does not exist, add as new vertex
        if (!map.containsKey(destination))
            addVertex(destination);

        // Add the destination vertex as the value correspondint to source vertex as key
        // This establishes a relationship from the source to destination vertex
        map.get(source).add(destination);
    }

    // Function to remove a vertex
    public void removeVertex(T v) {
        // Check if the vertex vertex exists
        if (!map.containsKey(v))
            System.out.print(v + "is not found in the graph");
        else {
            map.remove(v);
            System.out.print(v + "is removed");
        }
    }

    // Function to remove an edge
    public void removeEdge(T source, T destination) {
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

    // Function to count the number of vertices present
    public int getNumVertex() {
        int vertex_count = map.keySet().size();
        System.out.println("Number of vertices in the graph: " + vertex_count);
        return vertex_count;
    }

    // Function to count the number of edges present
    public int getNumEdges() {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        System.out.println("Number of edges in the graph: " + count);
        System.out.println("(Each direction of edge is counted as one edge.)");
        return count;
    }

    // Function to determine if a vertex exists in the graph
    public void ifVertexExist(T vertex) {
        if (!map.containsKey(vertex))
            System.out.print(vertex + "is not found in the graph");
        else
            System.out.print(vertex + "is found in the graph.");
    }

    // Function to determine if there is an edge between two vertices
    public void ifEdgeExist(T source, T destination) {
        if (!map.get(source).contains(destination))
            System.out.print("There is no edge from " + source + " to " + destination);
        else
            System.out.print("There exist an edge from " + source + " to " + destination);
    }

    // Function to show adjacency list
    public String AdjacencyList() {
        StringBuilder builder = new StringBuilder();

        for (T v : map.keySet()) {
            builder.append(v.toString() + ": ");
            for (T w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }

    // Function to show adjacency matrix
    // NEED TO REFINE!!!!!!!!!!!!!! @diminecjean
    public void AdjacencyMatrix() {
        int numVertices = getNumVertex();
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        // Create the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            List<T> neighbors = map.get(i + 1);
            for (int j = 0; j < numVertices; j++) {
                if (neighbors != null && neighbors.contains(j + 1)) {
                    adjacencyMatrix[i][j] = 1;
                }
            }
        }

        // Display the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class main {
    public static void main(String args[]) {
        // Object of graph is created.
        Graph<Integer> g = new Graph<Integer>();

        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        g.AdjacencyMatrix();

    }
}