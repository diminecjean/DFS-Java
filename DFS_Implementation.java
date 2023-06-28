import java.util.*;

// Graph ADT
class Graph {
    // Using HashMap to create graph in the form of adjacency list
    private Map<Integer, List<Integer>> map = new HashMap<>();

    // List of edges visited in DFS
    private List<Integer> edgeList;

    // Number of times each vertex is visited during DFS
    private Map<Integer, Integer> num = new HashMap<>();

    private int i;
    private int numVertices = 0;

    // private class Edge{
    // private int u;
    // private int v;

    // public Edge(int u, int v) {
    // this.u = u;
    // this.v = v;
    // }

    // @Override
    // public String toString() {
    // return u + " - " + v;
    // }
    // }

    // Constructor
    public Graph() {
        edgeList = new ArrayList<>();
        i = 0;
    }

    // Method to add a vertex
    public void addVertex(int vertex) {
        map.put(vertex, new LinkedList<Integer>());
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
        map.get(source).add(destination);
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
    public void getNumVertex() {
        int vertex_count = map.keySet().size();
        System.out.println("Number of vertices in the graph: " + vertex_count);
        numVertices = vertex_count;
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
            System.out.print(vertex + "is not found in the graph");
        else
            System.out.print(vertex + "is found in the graph.");
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
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        // Create the adjacency matrix
        for (int i = 0; i < numVertices; i++) {
            List<Integer> neighbors = map.get(i);
            for (int j = 0; j < numVertices; j++) {
                if (neighbors != null && neighbors.contains(j)) {
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

    // Method to find path
    public void FindPathDFS(int v, int d) {
        for (int vertex : map.keySet()) {
            num.put(vertex, 0);
        }
        edgeList.clear();
        i = 0;

        // while (v != d && num.get(v) == 0) {
        DFS(v, d);
        // }

        // Output the edges
        if (!edgeList.isEmpty()) {
            System.out.print("\n" + v);
            for (int i = 0; i < edgeList.size(); i++) {
                System.out.print(" -> " + edgeList.get(i));
            }
        } else
            System.out.print("There is no path from " + v + " to " + d);

    }

    // DFS Method
    public int DFS(int v, int d) {
        num.put(v, ++i);
        for (int u : map.get(v)) {
            if ((num.get(u) == 0) && (v != d)) {
                // If the encountered vertex does not have an outgoing edge,
                // and is also not the destination vertex,
                // skip the vertex
                if (map.get(u).isEmpty() && u != d)
                    continue;
                else
                    edgeList.add(u);
                return DFS(u, d);
            } else {
                break;
            }
        }
        return d;
    }

}

public class DFS_Implementation {
    public static void main(String args[]) {
        // Object of graph is created.
        Graph g = new Graph();

        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.

        g.addEdge(0, 4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        // g.getNumEdges();
        // g.AdjacencyMatrix();
        System.out.print(g.AdjacencyList());

        g.FindPathDFS(4, 0);
        g.FindPathDFS(2, 4);
        g.FindPathDFS(0, 4);

    }
}