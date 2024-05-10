import java.util.*;

public class Practice {
    static List<String> visitedList = new ArrayList<String>();
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new NodeComparator());
    static Scanner scanner = new Scanner(System.in);
    static int[][] intialMatrix;
    static int[][] finalMatrix = {
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };

    // Down, Left, Up, Right
    static int[] row = {1, 0, -1, 0};
    static int[] col = {0, -1, 0, 1};

    public static class Node {
        int[][] matrix;
        int f, g, h;
        int x, y;

        Node(int[][] m, int gi, int xi, int yi){
            matrix = m;
            g = gi;
            x = xi;
            y = yi;
            calculateCost();
        }

        void calculateCost(){
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(matrix[i][j] != 0 && matrix[i][j] != finalMatrix[i][j]){
                        count++;
                    }
                }
            }
            h = count;
            f = g + h;
        }
    }

    private static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Practice.Node lhsNode, Practice.Node rhsNode) {
            return (lhsNode.f) > (rhsNode.f) ? 1 : -1;
        }
    }

    public static String convertToString(int[][] matrix){
        String str = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                str += matrix[i][j];
            }
        }
        return str;
    }

    public static void printMatrix(Node node){
        System.out.println();
        System.out.println("------------------------------------------------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(node.matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("F = " + node.f + " G = " + node.g + " H = " + node.h + " X = " + (node.x + 1) + " Y = " + (node.y + 1));
        System.out.println("------------------------------------------------");
        System.out.println();
    }

    public static Node getInitialMatrix(){
        int[][] initialMatrix = new int[3][3];
        int x = 0, y = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int curr = scanner.nextInt();
                if(curr == 0){
                    x = i;
                    y = j;
                }
                initialMatrix[i][j] = curr;
            }
        }
        Node node = new Node(initialMatrix, 0, x, y);
        printMatrix(node);
        return node;
    }

    public static void solve(){
        System.out.println("Enter initial matrix:");
        Node root = getInitialMatrix();
        visitedList.add(convertToString(root.matrix));
        priorityQueue.add(root);
        while(!priorityQueue.isEmpty()){
            Node min  = priorityQueue.poll();
            if(min.h == 0){
                System.out.println("Solution found");
                printMatrix(min);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int newX = min.x + row[i];
                int newY = min.y + col[i];
                if(newX >= 0 && newX < 3 && newY >= 0 && newY < 3){
                    int[][] newMatrix = new int[3][3];
                    
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            newMatrix[j][k] = min.matrix[j][k];
                        }
                    }

                    int temp = newMatrix[newX][newY];
                    newMatrix[newX][newY] = newMatrix[min.x][min.y];
                    newMatrix[min.x][min.y] = temp;
                    if (visitedList.contains(convertToString(newMatrix))) {
                        continue;
                    }
                    visitedList.add(convertToString(newMatrix));
                    Node child = new Node(newMatrix, min.g + 1, newX, newY);
                    priorityQueue.add(child);
                    printMatrix(child);
                }
            }
        }
        System.out.println("Solution not found");
    }

    public static void main(String[] args){
        solve();
    }
}
