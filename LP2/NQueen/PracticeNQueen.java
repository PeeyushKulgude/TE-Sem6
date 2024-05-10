import java.util.*;

public class PracticeNQueen {
    static Scanner scanner = new Scanner(System.in);
    static int n;

    public static void printMatrix(int[][] board) {
        System.out.println("-------------------------------------------");
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("-------------------------------------------");
    }

    public static boolean isSafe(int row, int col, int[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }

        i = row;
        j = col;
        while (i >= 0 && j < n) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

    public static boolean solveNQueens(int row, int[][] board) {
        if (row == n) {
            printMatrix(board);
            return true;
        }

        boolean res = false;
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 1;
                res = solveNQueens(row + 1, board) || res;
                board[row][col] = 0;
            }
        }
        return res;
    }

    public static void solve() {
        if (!solveNQueens(0, new int[n][n])) {
            System.out.println("No solution found");
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the dimensions of the board (n x n) that you want:");
        n = scanner.nextInt();
        solve();
    }
}
