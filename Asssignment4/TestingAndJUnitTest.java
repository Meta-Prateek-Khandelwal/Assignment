import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    }

    @Test
    public void testCountClumps(){
        int[] input1 = {1, 2, 2, 3, 4, 4};
        assertEquals(2, test.numberOfClumps(input1));

        int[] input2 = {1, 1, 2, 1, 1};
        assertEquals(2, test.numberOfClumps(input2));

        int[] input3 = {1, 1, 1, 1, 1};
        assertEquals(1, test.numberOfClumps(input3));
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
    }

    @Test
    public void testSplitArray(){
        int[] input1 = {1, 1, 1, 2, 1};
        assertEquals(3, test.splitMethod(input1));

        int[] input2 = {2, 1, 1, 2, 1};
        assertEquals(-1, test.splitMethod(input2));

        int[] input3 = {10, 10};
        assertEquals(1, test.splitMethod(input3));
    }

    @Test
    public void testEmptyArrayThrowAssertionError(){
        int[] input = {};

        assertThrows(AssertionError.class, () -> {
            if(input.length == 0) throw new AssertionError("Array is empty!");
        });
    }

    @Test
    public void testUnequalXAndYThrowAssertionError(){
        int[] input = {1,4,5,4,2};
        assertThrows(AssertionError.class, () -> {
            int countX = 0;
            int countY = 0;

            for(int num : input){
                if(num == 4) countX++;
                if(num == 5) countY++;
            }

            if(countX != countY) throw new AssertionError("UnEqual number of 4s and 5s in the array.");
        });
    }

    @Test
    public void testkArrayAdjacentsXThrowAssertionError(){
        int[] input = {1,4,4,5,2};

        assertThrows(AssertionError.class, () ->{
            for(int i = 0; i < input.length; i++){
                if(input[i] == 4 && input[i+1] == 4) throw new AssertionError("adjacents 4s found in the array!");
            }
        });
    }

    @Test
    public void testLastIndexXThrowAssertionError(){
        int[] input = {1,4,5,3,5,4};
        int n = input.length;

        assertThrows(AssertionError.class, () -> {
            if(input[n-1] == 4) throw new AssertionError("Array 4 occurs at the last index!");
        });
    }
}
