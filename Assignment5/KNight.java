package Assignment5;

class KNightSolve {

    public boolean isValid(int[][] grid, int row, int col, int n, int expectedValue) {
        if (row < 0 || col < 0 || row >= n || col >= n || grid[row][col] != expectedValue) {
            return false;
        }

        if (expectedValue == (n * n - 1)) {
            return true;
        }
        // 8 possible move
        boolean possible1 = isValid(grid, row - 2, col + 1, n, expectedValue + 1);
        boolean possible2 = isValid(grid, row - 1, col + 2, n, expectedValue + 1);
        boolean possible3 = isValid(grid, row + 1, col + 2, n, expectedValue + 1);
        boolean possible4 = isValid(grid, row + 2, col + 1, n, expectedValue + 1);
        boolean possible5 = isValid(grid, row + 2, col - 1, n, expectedValue + 1);
        boolean possible6 = isValid(grid, row + 1, col - 2, n, expectedValue + 1);
        boolean possible7 = isValid(grid, row - 1, col - 2, n, expectedValue + 1);
        boolean possible8 = isValid(grid, row - 2, col - 1, n, expectedValue + 1);

        return possible1 || possible2 || possible3 || possible4 || possible5 || possible6 || possible7 || possible8;
    }

    public boolean checkValidGrid(int[][] grid) {
        return isValid(grid, 0, 0, grid.length, 0);
    }

}

public class KNight {
    public static void main(String[] args) {
        int[][] grid = { { 0, 11, 16, 5, 20 }, { 17, 4, 19, 10, 15 }, { 12, 1, 8, 21, 6 }, { 3, 18, 23, 14, 9 },
                { 24, 13, 2, 7, 22 } };

        KNightSolve possible = new KNightSolve();
        System.out.println(possible.checkValidGrid(grid));
    }
}
