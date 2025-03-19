class ArrOperation {
    // this function check array not empty
    public static void checkArrayNotEmpty(int[] array) {
        if (array == null || array.length == 0) {
            throw new AssertionError("Array should not be empty!");
        }
    }

    // max mirror find 
    int findMaxMirror(int[] array) {
        int n = array.length;

        checkArrayNotEmpty(array);
        String mirror = "";
        String revMirror = "";

        for (int i = 0; i < n; i++) {
            mirror += String.valueOf(array[i]);
            revMirror += String.valueOf(array[n - i - 1]);
        }

        int j = 0;
        int m = revMirror.length();
        String revMirror1 = revMirror;
        int maxMirror = 0;
        // 1214 - 121 - 4
        while (!mirror.contains(revMirror) && j < m) {
            revMirror = revMirror.substring(1);
            j++;
        }

        j = 0;
        while (!mirror.contains(revMirror1) && j < m) {
            revMirror1 = revMirror1.substring(0, m - 1 - j);
            j++;
        }

        maxMirror = Math.max(revMirror.length(), revMirror1.length());
        return maxMirror;
    }

    // count number of clumps - Array two adjacents value count clump
    int numberOfClumps(int[] input) {
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

    
    public static void checkArrayUnequalXY(int[] array) {
        int countX = 0;
        int countY = 0;

        for (int ele : array) {
            if (ele == 4)
                countX++;
            if (ele == 5)
                countY++;
        }

        if (countX != countY) {
            throw new AssertionError("Number of x and y should be equals!");
        }
    }

    public static void checkArrayAdjacentsX(int[] array) {
        int n = array.length;
        boolean flag = false;

        for (int i = 0; i < n - 1; i++) {
            if (array[i] == 4 && array[i] == array[i + 1]) flag = true;
        }

        if (flag) {
            throw new AssertionError("Array two adjacents X values are there!");
        }
    }

    public static void checkArrayLastIndexX(int[] array) {
        int n = array.length;

        if (array[n - 1] == 4) {
            throw new AssertionError("Array 4 occurs at the last index!");
        }
    }

    static void swap(int[] arr, int lp, int rp) {
        int temp = arr[lp];
        arr[lp] = arr[rp];
        arr[rp] = temp;
    }

    // check inside array x is follow y if not follow then swap and follow
    public int[] follow(int[] arr, int x, int y) {// main fix x and y method
        int n = arr.length;
        checkArrayNotEmpty(arr);
        checkArrayUnequalXY(arr);
        checkArrayAdjacentsX(arr);
        checkArrayLastIndexX(arr);
        
        int i = 0;
        int j = 0;

        while (i < n - 1 && j < n) {

            // if(arr[i] == x && arr[i+1] == y) i += 2;
            if (arr[j] == x && arr[j + 1] == y)
                j += 2;

            while (i < n - 1 && arr[i] != x)
                i++;
            while (j < n && arr[j] != y)
                j++;

            swap(arr, i + 1, j);
            i++;
            j++;
        }
        return arr;
    }

    // if array sum is split two part of array so return index
    public int splitMethod(int[] arr) {// main method of split array
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
        // mirror
        int input1[] = { 1, 2, 7, 8, 9, 9, 8, 7 };
        int mirrorLength = operation.findMaxMirror(input1);
        System.out.println(mirrorLength);

        // clump
        int[] input2 = { 1, 2, 2, 3, 4, 4 };
        System.out.println(operation.numberOfClumps(input2));

        // fix x so follow y
        int[] input3 = { 5, 4, 9, 4, 9, 5 };
        int[] followed = operation.follow(input3, 4, 5);
        for (int i = 0; i < followed.length; i++) {
            System.out.print(followed[i] + " ");
        }
        System.out.println();

        // split array
        int[] arr = { 10, 10 };
        int idx = operation.splitMethod(arr);
        System.out.println(idx);
    }
}
