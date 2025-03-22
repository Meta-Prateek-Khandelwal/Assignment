package Assignment7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class SparseMatrixTest {
    @SuppressWarnings("deprecation")
    @Test
    public void testSparseMatrix(){
        MatrixOperation operation = new MatrixOperation();
        int n1 = 5;
        int m1 = 5;

        int n2 = 5;
        int m2 = 5;
        HashMap<String, Integer> input11 = new HashMap<>();
        input11.put("04", 9);
        input11.put("11", 8);
        input11.put("20", 4);
        input11.put("23", 2);
        input11.put("42", 2);

        HashMap<String, Integer> input12 = new HashMap<>();
        input12.put("04", 9);
        input12.put("11", 8);
        input12.put("20", 4);
        input12.put("23", 2);
        input12.put("42", 2);
        
        int[][] output11  = {{0,0,4,0,0},{0,8,0,0,0},{0,0,0,0,2}, {0,0,2,0,0}, {9,0,0,0,0}};
        int[][] output12  = {{0,0,0,0,18},{0,16,0,0,0},{8,0,0,4,0},{0,0,0,0,0},{0,0,4,0,0}};
        int[][] output13  = {{0,0,18,0,0},{0,64,0,0,0},{0,0,0,0,36}, {0,0,0,0,0}, {8,0,0,4,0}};

        assertEquals(output11, operation.tranposeMatrixMethod(input11, n1, m1));
        assertTrue(operation.isSymmetricalMatrix(input11, n1, m1));
        assertEquals(output12, operation.addMatrices(input11, input12, n1, n2, m1, m2));
        assertEquals(output13, operation.multiplyMatrices(input11, input12, n1, n2, m1, m2));
    }    
}
