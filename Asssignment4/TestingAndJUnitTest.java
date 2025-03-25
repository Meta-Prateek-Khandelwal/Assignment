import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestingAndJUnitTest {
    ArrOperation test = new ArrOperation();
    @Test
    public void mirrorTest(){
        int[] input1 = {1, 2, 3, 8, 9, 3, 2, 1};
        
        assertEquals(3, test.findMaxMirror(input1));
        
        int[] input2 = {7, 1, 4, 9, 7, 4, 1};
        assertEquals(2, test.findMaxMirror(input2));

        int[] input3 = {1, 2, 1, 4};
        assertEquals(3, test.findMaxMirror(input3));

        int[] input4 = {1, 4, 5, 3, 5, 4, 1};
        assertEquals(7, test.findMaxMirror(input4));

        int[] input5 = {1, 2, 3, 4, 5};
        assertEquals(1, test.findMaxMirror(input5));
    }

    @Test
    public void testCountClumps(){
        int[] input1 = {1, 2, 2, 3, 4, 4};
        assertEquals(2, test.numberOfClumps(input1));

        int[] input2 = {1, 1, 2, 1, 1};
        assertEquals(2, test.numberOfClumps(input2));

        int[] input3 = {1, 1, 1, 1, 1};
        assertEquals(1, test.numberOfClumps(input3));

        int[] input4 = {1, 2, 3, 4, 5};
        assertEquals(0, test.numberOfClumps(input4));
    }

    @Test
    public void testFixXY(){
        int[] input1 = {5, 4, 9, 4, 9, 5};
        int[] output1 = {9, 4, 5, 4, 5, 9};
        assertArrayEquals(output1, test.follow(input1, 4, 5));
        
        int[] input2 = {1, 4, 1, 5};
        int[] output2 = {1, 4, 5, 1};
        assertArrayEquals(output2, test.follow(input2, 4, 5));

        int[] input3 = {1, 4, 1, 5, 5, 4, 1};
        int[] output3 = {1, 4, 5, 1, 1, 4, 5};
        assertArrayEquals(output3, test.follow(input3, 4, 5));

        int[] input4 = {1, 2, 3, 4, 5};
        int[] output4 = {1, 2, 3, 4, 5};
        assertArrayEquals(output4, test.follow(input4, 4, 5));
    }

    @Test
    public void testSplitArray(){
        int[] input1 = {1, 1, 1, 2, 1};
        assertEquals(3, test.splitMethod(input1));

        int[] input2 = {2, 1, 1, 2, 1};
        assertEquals(-1, test.splitMethod(input2));

        int[] input3 = {10, 10};
        assertEquals(1, test.splitMethod(input3));

        int[] input4 = {1, 2, 3, 4, 5};
        assertEquals(-1, test.splitMethod(input4));
    }
}
