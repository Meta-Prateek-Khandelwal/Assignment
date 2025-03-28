package Assignment7;

import java.util.HashMap;

final class MatrixOperation {
    private final int rows;
    private final int cols;
    private final HashMap<String, Integer> sparseMatrix;

    MatrixOperation(int rows, int cols, HashMap<String, Integer> elements){
        this.rows = rows;
        this.cols = cols;
        this.sparseMatrix = elements;
        HashMap<String, Integer> sparseMatrix = new HashMap<>();

        for(HashMap.Entry<String, Integer> e: elements.entrySet()){
            String key = e.getKey();
            int val = e.getValue();
            sparseMatrix.put(key, val);
        }
    }

    private String getKey(int i, int j){
        String key = String.valueOf(i) + String.valueOf(j);
        return key;
    }

    // time complexity : O(n*m)
    int[][] tranposeMatrixMethod() {
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
    boolean isSymmetricalMatrix() {
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
    int[][] addMatrices(MatrixOperation operation1) {
        int n1 = rows;
        int m1 = cols;
        int n2 = operation1.rows;
        int m2 = operation1.cols;

        if (n1 != n2 || m1 != m2) {
            throw new IllegalArgumentException("Matrix 1 column and matrix 2 row must be same for multiption");
        }
        int[][] addMatrix = new int[n1][m1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                String key = getKey(i, j);

                if(!sparseMatrix.containsKey(key) && ! operation1.sparseMatrix.containsKey(key)){
                    addMatrix[i][j] = 0;
                }else if(sparseMatrix.containsKey(key) && ! operation1.sparseMatrix.containsKey(key)){
                    addMatrix[i][j] = sparseMatrix.get(key);
                }else if(!sparseMatrix.containsKey(key) && operation1.sparseMatrix.containsKey(key)){
                    addMatrix[i][j] = operation1.sparseMatrix.get(key);
                }else{
                    addMatrix[i][j] = sparseMatrix.get(key) + operation1.sparseMatrix.get(key);
                }
            }
        }

        return addMatrix;
    }

    // time complexity : O(n^2*m) = O(n^3)
    int[][] multiplyMatrices(MatrixOperation operation1) {
        int n1 = rows;
        int m1 = cols;
        int n2 = operation1.rows;
        int m2 = operation1.cols;

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
                    if(!sparseMatrix.containsKey(key1) && !operation1.sparseMatrix.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(sparseMatrix.containsKey(key1) && !operation1.sparseMatrix.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(!sparseMatrix.containsKey(key1) && operation1.sparseMatrix.containsKey(key2)){
                        multiplyMatrix[i][j] += 0;
                    }else{
                        multiplyMatrix[i][j] += sparseMatrix.get(key1) * operation1.sparseMatrix.get(key2);
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

        MatrixOperation operation = new MatrixOperation(row1, col1, sparseMatrix1);
        MatrixOperation operation1 = new MatrixOperation(row2, col2, sparseMatrix2);

        int[][] matrix1 = operation.tranposeMatrixMethod();
        operation.printMethod(matrix1);

        System.out.println(operation.isSymmetricalMatrix());

        try {
            int[][] matrix2 = operation.addMatrices(operation1);
            operation.printMethod(matrix2);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }

        try {
            int[][] matrix3 = operation.multiplyMatrices(operation1);
            operation.printMethod(matrix3);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }
    }
}
