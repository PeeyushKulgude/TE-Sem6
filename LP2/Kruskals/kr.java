package Kruskals;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class KruskalAlgorithm {
    private int V; // Number of vertices
    private List<Edge> edges; // List of edges in the graph

    public KruskalAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // Find function for disjoint sets
    private int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    // Union function for disjoint sets
    private void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;
        else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public void kruskalMST() {
        Edge[] result = new Edge[V];
        int[] parent = new int[V];
        int[] rank = new int[V];

        // Initialize disjoint sets
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // Sort all edges in non-decreasing order of their weight
        Collections.sort(edges);

        int e = 0; // Index variable used for result[]
        int i = 0; // Index variable used for sorted edges

        while (e < V - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);

            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            // If including this edge does not cause cycle, include it in result
            if (x != y) {
                result[e++] = nextEdge;
                union(parent, rank, x, y);
            }
        }

        // Print the result
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }

    public static void main(String[] args) {
        int V = 4;
        KruskalAlgorithm graph = new KruskalAlgorithm(V);

        // Adding edges to the graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Find Minimum Spanning Tree
        graph.kruskalMST();
    }
}
