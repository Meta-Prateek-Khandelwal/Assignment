package Assignment5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NQueenJTest {
    @Test
    public void NQueenTest(){
        Queen test = new Queen();
        int[][] board1 = new int[1][1];
        assertTrue(test.queen(board1, 0, board1.length));

        int[][] board2 = new int[2][2];
        assertFalse(test.queen(board2, 0, board2.length));

        int[][] board3 = new int[3][3];
        assertFalse(test.queen(board3, 0, board3.length));
    }
}
