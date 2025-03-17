package Assignment7;

import java.util.HashMap;

final class MatrixOperation {

    int[][] tranposeMatrixMethod(HashMap<Pair, Integer> sparseMatrix, int n, int m) {
        int[][] tranposeMatrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // t[i][j] = t[j][i]
                Pair key = new Pair(j, i);
                if (sparseMatrix.containsKey(key)) {
                    tranposeMatrix[i][j] = sparseMatrix.get(key);
                }else {
                    tranposeMatrix[i][j] = 0;
                }
            }
        }

        return tranposeMatrix;
    }

    boolean SymmetricalMatrix(HashMap<Pair, Integer> sparseMatrix1, int n, int m) {
        if (n != m){
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Pair pair = new Pair(i, j);
                if (sparseMatrix1.containsKey(pair) != sparseMatrix1.containsKey(pair)) {
                    return false;
                }
            }
        }

        return true;
    }

    int[][] addMatricesMethod(HashMap<Pair, Integer> firstMatrix, HashMap<Pair, Integer> secondMatrix, int n1, int n2,
            int m1, int m2) {

        if (n1 != n2 || m1 != m2) {
            throw new IllegalArgumentException("Matrix 1 column and matrix 2 row must be same for multiption");
        }
        int[][] addMatrix = new int[n1][m1];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m1; j++) {
                Pair key = new Pair(i, j);
                if(!firstMatrix.containsKey(key) && ! secondMatrix.containsKey(key)){
                    addMatrix[i][j] = 0;
                }else if(firstMatrix.containsKey(key) && ! secondMatrix.containsKey(key)){
                    addMatrix[i][j] = firstMatrix.get(new Pair(i, j));
                }else if(!firstMatrix.containsKey(key) && secondMatrix.containsKey(key)){
                    addMatrix[i][j] = secondMatrix.get(new Pair(i, j));
                }else{
                    addMatrix[i][j] = firstMatrix.get(key) + secondMatrix.get(key);
                }
            }
        }

        return addMatrix;
    }

    int[][] multiplyMatrices(HashMap<Pair, Integer> sparseMatrix1, HashMap<Pair, Integer> sparseMatrix2, int n1, int n2,
            int m1, int m2) {

        // base case
        if (n2 != m1) {
            throw new IllegalArgumentException("Matrix 1 column and matrix 2 row must be same for multiption");
        }

        int[][] multiplyMatrix = new int[n1][m2];

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m2; j++) {
                for (int k = 0; k < n2; k++) {
                    Pair pair1 = new Pair(i, k);
                    Pair pair2 = new Pair(k, j);
                    if(!sparseMatrix1.containsKey(pair1) && !sparseMatrix2.containsKey(pair2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(sparseMatrix1.containsKey(pair1) && !sparseMatrix2.containsKey(pair2)){
                        multiplyMatrix[i][j] += 0;
                    }else if(!sparseMatrix1.containsKey(pair1) && sparseMatrix2.containsKey(pair2)){
                        multiplyMatrix[i][j] += 0;
                    }else{
                        multiplyMatrix[i][j] += sparseMatrix1.get(pair1) * sparseMatrix2.get(pair2);
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

class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Pair pair = (Pair) obj;
        return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode(){
        return 31 * row + col;
    }
}

public class SparseMatrix {
    
    public static void main(String[] args) {
        int n1 = 5;
        int m1 = 6;

        int n2 = 5;
        int m2 = 6;

        HashMap<Pair, Integer> sparseMatrix1 = new HashMap<>();
        sparseMatrix1.put(new Pair(0, 4), 9);
        sparseMatrix1.put(new Pair(1, 1), 8);
        sparseMatrix1.put(new Pair(2, 0), 4);
        sparseMatrix1.put(new Pair(2, 3), 2);
        sparseMatrix1.put(new Pair(3, 5), 5);
        sparseMatrix1.put(new Pair(4, 2), 2);

        HashMap<Pair, Integer> sparseMatrix2 = new HashMap<>();
        sparseMatrix2.put(new Pair(0, 4), 9);
        sparseMatrix2.put(new Pair(1, 1), 8);
        sparseMatrix2.put(new Pair(2, 0), 4);
        sparseMatrix2.put(new Pair(2, 3), 2);
        sparseMatrix2.put(new Pair(3, 5), 5);
        sparseMatrix2.put(new Pair(4, 2), 2);

        MatrixOperation operation = new MatrixOperation();

        int[][] matrix1 = operation.tranposeMatrixMethod(sparseMatrix1, n1, m1);
        operation.printMethod(matrix1);

        System.out.println(operation.SymmetricalMatrix(sparseMatrix1, n1, m1));

        try {
            int[][] matrix2 = operation.addMatricesMethod(sparseMatrix1, sparseMatrix2, n1, n2, m1, m2);
            operation.printMethod(matrix2);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }

        try {
            int[][] matrix3 = operation.multiplyMatrices(sparseMatrix1, sparseMatrix2, n1, n2, m1, m2);
            operation.printMethod(matrix3);
        } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
        }
    }
}
