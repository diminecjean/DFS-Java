# DFS Application with Java

## Specifications 
You are to implement the finding a path application program in Java using depth-first search algorithm. We can find a path from a source vertex v to a destination vertex d by starting a depth first search at vertex v and terminating the search as soon as we reach vertex d. To construct a path (a series of edges) we need to remember the edges used to move from one vertex to the next. For the path problem, the needed set of edges is implicitly stored in the depth-first recursion, so it is easier to develop a path-finding code by using the depth-first strategy. As the recursion unfolds following the labelling of vertex d, the path is constructed backward from d to s. The pseudocode for DepthFirstSearch (from lecture) is modified as follows:

DFS is modified so that it discontinues the search for reachable vertices as soon as the destination is reached. Notice that DFS is invoked only when v!=d. Also notice that the algorithm does not necessarily find a shortest path (i.e., a path with the fewest number of edges to the destination). 
### Pseudocode for Depth-First Search from question
```
FindPathDFS (v,d)
    for all vertices v
    num(v) = 0;
    edges = null;
    i=0;
    while v!=d && there is a vertex v such that num(v)is 0
        DFS(v,d);
    If v==d
        output edges; // there is a path from v to d
    else
        No path from v to d
    DFS(v,d)
        num(v) = i++;
        for all vertices u adjacent to s
            if num(u) is 0 and v!=d
                attach edge(uv) to edges;
                DFS(u,d);

```

### Pseudocode of DFS from Lecture
```
DFS(v)
    num(v) = i++;
    for all vertices u adjacent to v
        if num(u) is 0
            attach edge(uv) to edges;
            DFS(u);

depthFirstSearch()
    for all vertices v
        num(v) = 0;
    edges = null;
    i = 0;
    while there is a vertex v such that num(v) is 0
        DFS(v);
    output edges;
```
You have to further enhance the program in (i) above by making your program more user-friendly and incorporating an application or more such as transportation systems, and road networks.