import java.util.*;

class Solution {
    HashMap<Integer, ArrayList<Pair>> adjList;
    HashMap<Integer, Boolean> visited;
    int nodes;
    int edges;

    Solution() {
        nodes = 0;
        edges = 0;
        adjList = new HashMap<>();
        visited = new HashMap<>();
    }

    void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of nodes: ");
        nodes = scanner.nextInt();
        System.out.print("Enter the total number of edges: ");
        edges = scanner.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter edge " + i + ": ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            System.out.print("Enter weight: ");
            int w = scanner.nextInt();
            if (!adjList.containsKey(u)) {
                adjList.put(u, new ArrayList<>());
            }
            if (!adjList.containsKey(v)) {
                adjList.put(v, new ArrayList<>());
            }
            adjList.get(u).add(new Pair(v, w));
            adjList.get(v).add(new Pair(u, w));
        }
    }

    void print() {
        for (Map.Entry<Integer, ArrayList<Pair>> entry : adjList.entrySet()) {
            System.out.print(entry.getKey() + "---->");
            for (Pair pair : entry.getValue()) {
                System.out.print("[" + pair.node + "," + pair.weight + "] ");
            }
            System.out.println();
        }
    }

    ArrayList<Integer> dijkstras(int source) {
        ArrayList<Integer> distance = new ArrayList<>(Collections.nCopies(nodes, Integer.MAX_VALUE));
        distance.set(source, 0);
        TreeSet<Pair> set = new TreeSet<>();
        set.add(new Pair(0, source));

        while (!set.isEmpty()) {
            Pair top = set.pollFirst();
            int frontNode = top.node;
            int weight = top.weight;

            for (Pair pair : adjList.get(frontNode)) {
                if (weight + pair.weight < distance.get(pair.node)) {
                    set.remove(new Pair(distance.get(pair.node), pair.node));
                    distance.set(pair.node, weight + pair.weight);
                    set.add(new Pair(distance.get(pair.node), pair.node));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.input();
        s.print();
        System.out.println();
        ArrayList<Integer> ans = s.dijkstras(0);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}

class  Pair implements Comparable<Pair> {
    int node;
    int weight;

    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public int compareTo(Pair other) {
        if (this.weight == other.weight) {
            return this.node - other.node;
        }
        return this.weight - other.weight;
    }
}
