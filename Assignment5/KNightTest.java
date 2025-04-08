package Assignment5;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class KNightTest {
    @Test
    void testKNight(){
        KNightSolve obj = new KNightSolve();
        int[][] grid = {{0,3,6},{5,8,1},{2,7,4}};
        assertFalse(obj.checkValidGrid(grid));
    }
}
