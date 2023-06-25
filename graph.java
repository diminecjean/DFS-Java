import java.util.*;

import org.w3c.dom.Node;

// Graph ADT
class Graph<T> {
    // Using HashMap to create graph in the form of adjacency list
    private Map<T, List<T>> map = new HashMap<>();

    // Function to add a vertex
    public void addVertex(T vertex) {
        map.put(vertex, new LinkedList<T>());
    }

    // Function to add an edge given the source and destination
    public void addEdge(T source, T destination, boolean bidirection) {
        // If the source vertex does not exist, add as new vertex
        if (!map.containsKey(source))
            addVertex(source);

        // If the destination vertex does not exist, add as new vertex
        if (!map.containsKey(destination))
            addVertex(destination);

        // Add the destination vertex as the value correspondint to source vertex as key
        // This establishes a relationship from the source to destination vertex
        map.get(source).add(destination);

        // If the edge goes both ways between the source and destination vertex,
        // Establish another relationship from the destination to the source vertex.
        if (bidirection == true) {
            map.get(destination).add(source);
        }
    }

    // Function to remove a vertex

    // Function to remove an edge

    // Function to count the number of vertices present

    // Function to count the number of edges present

    // Function to determine if an edge exists in the graph

    // Function to determine if there is an edge between two vertices

    // Function to show adjacency list

    // Function to show adjacency matrix

}

// driver code - builds the graph using the class declared above.
public class graph {
    public static void main(String args[]) {

    }
}