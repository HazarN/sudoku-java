public class Sudoku {
    private int[][] board;

    public Sudoku(int[][] board) {
        this.board = board;
    }

    public boolean isSafe(int row, int col, int n) {
        // Check if n is in the row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == n) {
                return false;
            }
        }

        // Check if n is in the column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == n) {
                return false;
            }
        }

        // Check if n is in the 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == n) {
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.println("-------------------------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            // Find the first empty cell
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    // Try all numbers from 1 to 9
                    for (int n = 1; n <= 9; n++) {
                        if (isSafe(row, col, n)) {
                            board[row][col] = n;
                            if (solve()) return true;
                            else         board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public void calculateAlgorithmRuntime() {
        long startTime = System.nanoTime();
        solve();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println("Algorithm runtime: " + duration + " ns");
    }
}
