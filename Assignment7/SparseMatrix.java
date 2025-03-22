package Assignment7;

import java.util.HashMap;

final class MatrixOperation {

    private String getKey(int i, int j){
        String key = String.valueOf(i) + String.valueOf(j);
        return key;
    }

    // time complexity : O(n*m)
    int[][] tranposeMatrixMethod(HashMap<String, Integer> sparseMatrix, int rows, int cols) {
        int[][] tranposeMatrix = new int[cols][rows];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                String key = getKey(j,i);

                if (sparseMatrix.containsKey(key)) {
                    tranposeMatrix[i][j] = sparseMatrix.get(key);
                }else {
                    tranposeMatrix[i][j] = 0;
                }
            }
        }

        return tranposeMatrix;
    }

    // time complexity : O(n*m) = O(n^2)
    boolean isSymmetricalMatrix(HashMap<String, Integer> sparseMatrix, int rows, int cols) {
        if (cols != rows){
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <cols; j++) {
                String key = getKey(i, j);

                if (sparseMatrix.containsKey(key) != sparseMatrix.containsKey(key)) {
                    return false;
                }
            }
        }

        return true;
    }

    // time complexity : O(n*m) = O(n^2)
    int[][] addMatrices(HashMap<String, Integer> firstMatrix, HashMap<String, Integer> secondMatrix, int n1, int n2,
            int m1, int m2) {

        if (n1 != n2 || m1 != m2) {
            throw new IllegalArgumentException("Matrix 1 column and matrix 2 row must be same for multiption");
        }
        int[][] addMatrix = new int[n1][m1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                String key = getKey(i, j);

                if(!firstMatrix.containsKey(key) && ! secondMatrix.containsKey(key)){
                    addMatrix[i][j] = 0;
                }else if(firstMatrix.containsKey(key) && ! secondMatrix.containsKey(key)){
                    addMatrix[i][j] = firstMatrix.get(key);
                }else if(!firstMatrix.containsKey(key) && secondMatrix.containsKey(key)){
                    addMatrix[i][j] = secondMatrix.get(key);
                }else{
                    addMatrix[i][j] = firstMatrix.get(key) + secondMatrix.get(key);
                }
            }
        }

        return addMatrix;
    }

    // time complexity : O(n^2*m) = O(n^3)
    int[][] multiplyMatrices(HashMap<String, Integer> sparseMatrix1, HashMap<String, Integer> sparseMatrix2, int n1, int n2,
            int m1, int m2) {

        // base case
        if (n2 != m1) {
            throw new IllegalArgumentException("Matrix 1 column and matrix 2 row must be same for multiption");
        }

        int[][] multiplyMatrix = new int[n1][m2];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m2; j++) {
                for (int k = 0; k < n2; k++) {
                    String key1 = getKey(i, k);
                    String key2 = getKey(k, j);
                    if(!sparseMatrix1.containsKey(key1) && !sparseMatrix2.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(sparseMatrix1.containsKey(key1) && !sparseMatrix2.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(!sparseMatrix1.containsKey(key1) && sparseMatrix2.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else{
                        multiplyMatrix[i][j] += sparseMatrix1.get(key1) * sparseMatrix2.get(key2);
                    }
                }
            }
        }
        return multiplyMatrix;
    }

    void printMethod(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class SparseMatrix {
    
    public static void main(String[] args) {
        final int row1 = 5;
        final int col1 = 6;

        final int row2 = 5;
        final int col2 = 6;

        final HashMap<String, Integer> sparseMatrix1 = new HashMap<>();
        sparseMatrix1.put("04", 9);
        sparseMatrix1.put("11", 8);
        sparseMatrix1.put("20", 4);
        sparseMatrix1.put("23", 2);
        sparseMatrix1.put("35", 5);
        sparseMatrix1.put("42", 2);

        final HashMap<String, Integer> sparseMatrix2 = new HashMap<>();
        sparseMatrix2.put("04", 9);
        sparseMatrix2.put("11", 8);
        sparseMatrix2.put("20", 4);
        sparseMatrix2.put("23", 2);
        sparseMatrix2.put("35", 5);
        sparseMatrix2.put("42", 2);

        MatrixOperation operation = new MatrixOperation();

        int[][] matrix1 = operation.tranposeMatrixMethod(sparseMatrix1, row1, col1);
        operation.printMethod(matrix1);

        System.out.println(operation.isSymmetricalMatrix(sparseMatrix1, row1, col1));

        try {
            int[][] matrix2 = operation.addMatrices(sparseMatrix1, sparseMatrix2, row1, row2, col1, col2);
            operation.printMethod(matrix2);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }

        try {
            int[][] matrix3 = operation.multiplyMatrices(sparseMatrix1, sparseMatrix2, row1, row2, col1, col2);
            operation.printMethod(matrix3);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }
    }
}
