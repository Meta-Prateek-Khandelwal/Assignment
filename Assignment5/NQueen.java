package Assignment5;

class Queen {
    private static boolean isSafe(int[][] board, int row, int col, int size) {
        // vertical check
        for (int newRow = 0; newRow < row; newRow++) {
            if (board[newRow][col] == 1) return false;
        }

        // left diagonal check
        int newRow1 = row;
        int newCol1 = col;
        while (newRow1 >= 0 && newCol1 >= 0) {
            if (board[newRow1][newCol1] == 1) return false;

            newRow1--;
            newCol1--;
        }

        // right diagonal check
        int newRow2 = row;
        int newCol2 = col;
        while (newRow2 >= 0 && newCol2 < size) {
            if (board[newRow2][newCol2] == 1) return false;

            newRow2--;
            newCol2++;
        }

        return true;
    }

    boolean queen(int[][] board, int row, int size) {
        if(row == size) return true;

        for (int col = 0; col < size; col++) {
            if (isSafe(board, row, col, size)) {
                board[row][col] = 1;
                if (queen(board, row+1, size)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }
}

public class NQueen {

    public static void main(String[] args) {
        int[][] board = new int[2][2];
        Queen q = new Queen();
        boolean isExit = q.queen(board, 0, board.length);
        System.out.println(isExit);
    }
}
