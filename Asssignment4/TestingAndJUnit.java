class ArrOperation {
    
    private static void checkArrayNotEmpty(int[] array) {
        if (array == null || array.length == 0) {
            throw new AssertionError("Array should not be empty!");
        }
    }

    /*
     * Mirror section in an array is a group of contiguous elements such that somewhere in the array, the same group appears in reverse order.
     * @param Integer Array.
     * @return Integer Value.
     */
    public int findMaxMirror(int[] array) {
        int n = array.length;

        checkArrayNotEmpty(array);
        String mirror = "";
        String revMirrorFront = "";
        String revMirrorBack = "";

        for (int i = 0; i < n; i++) {
            mirror += String.valueOf(array[i]);
            revMirrorFront += String.valueOf(array[n - i - 1]);
        }

        int ptr = 0;
        int m = revMirrorFront.length();
        revMirrorBack = revMirrorFront;
        int maxMirrorLength = 0;

        while (!mirror.contains(revMirrorFront) && ptr < m) {
            revMirrorFront = revMirrorFront.substring(1);
            ptr++;
        }

        ptr = 0;
        while (!mirror.contains(revMirrorBack) && ptr < m) {
            revMirrorBack = revMirrorBack.substring(0, m - 1 - ptr);
            ptr++;
        }

        maxMirrorLength = Math.max(revMirrorFront.length(), revMirrorBack.length());
        return maxMirrorLength;
    }

    /*
     * Clump in an array is a series of 2 or more adjacent elements of the same value.
     * @param Integer Array.
     * @return Integer value.
     */
    public int numberOfClumps(int[] input) {
        checkArrayNotEmpty(input);
        int n = input.length;
        int count = 0;
        int clumps = 0;

        for (int i = 0; i < n - 1; i++) {
            if (input[i] == input[i + 1]) {
                count++;
                if (count == 1)
                    clumps++;
            } else
                count = 0;
        }

        return clumps;
    }

    
    private static void checkArrayUnequalXY(int[] array, int x, int y) {
        int countX = 0;
        int countY = 0;

        for (int ele : array) {
            if (ele == x)
                countX++;
            if (ele == y)
                countY++;
        }

        if (countX != countY) {
            throw new AssertionError("Number of x and y should be equals!");
        }
    }

    private static void checkArrayAdjacentsX(int[] array, int x) {
        int n = array.length;
        boolean flag = false;

        for (int i = 0; i < n - 1; i++) {
            if (array[i] == x && array[i] == array[i + 1]) {
                flag = true;
                break;
            }
        }

        if (flag) {
            throw new AssertionError("Array two adjacents X values are there!");
        }
    }

    private static void checkArrayLastIndexX(int[] array, int x) {
        int n = array.length;

        if (array[n - 1] == 4) {
            throw new AssertionError("Array 4 occurs at the last index!");
        }
    }

    private static void swap(int[] arr, int lp, int rp) {
        int temp = arr[lp];
        arr[lp] = arr[rp];
        arr[rp] = temp;
    }

    /*
     * check inside array x is follow y if not follow then swap and follow
     * @param first Integer Array.
     * @param second x is number.
     * @param third y is number.
     * @return Integer Array
     */

    public int[] follow(int[] arr, int x, int y) {
        int n = arr.length;
        checkArrayNotEmpty(arr);
        checkArrayUnequalXY(arr, x, y);
        checkArrayAdjacentsX(arr, x);
        checkArrayLastIndexX(arr, x);
        
        int move1 = 0;
        int move2 = 0;

        while (move1 < n - 1 && move2 < n) {

            if (arr[move2] == x && arr[move2 + 1] == y){
                move2 += 2;
            }

            while (move1 < n - 1 && arr[move1] != x){
                move1++;
            }
            while (move2 < n && arr[move2] != y){
                move2++;
            }

            swap(arr, move1 + 1, move2);
            move1++;
            move2++;
        }
        return arr;
    }

    /* 
     * array sum is split two part of array so return index
     * @parms Integer array
     * @ return integer value
    */
    public int splitMethod(int[] arr) {
        checkArrayNotEmpty(arr);
        int n = arr.length;
        int arrSum = 0;

        for (int x : arr)
            arrSum += x;

        int halfSum = arrSum / 2;
        if (halfSum * 2 != arrSum)
            return -1;

        for (int i = 0; i < n; i++) {
            arrSum -= arr[i];
            if (halfSum == arrSum)
                return i + 1;
        }
        return -1;
    }
}

public class TestingAndJUnit {

    public static void main(String[] args) {

        ArrOperation operation = new ArrOperation();
        
        int input1[] = { 1, 2, 7, 8, 9, 9, 8, 7};
        int mirrorLength = operation.findMaxMirror(input1);
        System.out.println(mirrorLength);

        int[] input2 = { 1, 2, 2, 3, 4, 4 };
        System.out.println(operation.numberOfClumps(input2));

        int[] input3 = { 5, 4, 9, 4, 9, 5 };
        int[] followed = operation.follow(input3, 4, 5);
        for (int i = 0; i < followed.length; i++) {
            System.out.print(followed[i] + " ");
        }
        System.out.println();

        int[] arr = { 10, 10 };
        int idx = operation.splitMethod(arr);
        System.out.println(idx);
    }
}
