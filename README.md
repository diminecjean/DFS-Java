# DFS Application with Java

## Pseudocode for Depth-First Search from question
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

## Pseudocode of DFS from Lecture
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