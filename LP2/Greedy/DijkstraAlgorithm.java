import java.util.*;

class DjikstraAlgorithm {
    static final int V = 5;

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    void printSolution(int dist[], String[] cities) {
        System.out.println("City \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(cities[i] + " \t\t " + dist[i]);
    }

    void dijkstra(int graph[][], int src, String[] cities) {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        printSolution(dist, cities);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] cities = { "Delhi", "Mumbai", "Kolkata", "Chennai", "Bangalore" };

        int[][] graph = new int[V][V];

        System.out.println("Enter the distances between cities:");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print("Distance from " + cities[i] + " to " + cities[j] + ": ");
                graph[i][j] = scanner.nextInt();
            }
        }

        DjikstraAlgorithm t = new DjikstraAlgorithm();
        t.dijkstra(graph, 0, cities);

        scanner.close();
    }
}
