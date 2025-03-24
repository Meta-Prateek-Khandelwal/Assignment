package Assignment6;

final class IntSet {
    private int[] universalSet;
    private int[] set;
    private int size = 1001;

    IntSet(int[] arr) {
        set = new int[size];
        universalSet = new int[size];

        for (int x : arr) {
            set[x]++;
        }

        for(int i = 0; i < size; i++){
            universalSet[i]++;
        }
    }

    public int[] getIntSet(){
        return set;
    }

    public boolean isMember(int x) {
        if (set[x] > 0) {
            return true;
        }

        return false;
    }

    public int size() {
        int countSize = 0;
        for (int i = 0; i < 1001; i++) {
            if (set[i] > 0) {
                countSize += set[i];
            }
        }
        return countSize;
    }

    boolean isSubSet(IntSet s) {
        for (int i = 0; i < s.set.length; i++) {
            if (set[i] == 0 && s.set[i] > 0) {
                return false;
            }
        }
        return true;
    }

    IntSet getComplement() {
        IntSet complement = new IntSet(new int[1001]);

        for (int i = 0; i < set.length; i++) {
            if (!isMember(i)) {
                complement.set[i]++;
            }
        }
        return complement;
    }

    IntSet getUnion(IntSet s){
        IntSet union = new IntSet(set);

        for (int i = 0; i < s.set.length; i++) {
            if (s.set[i] > 0 || set[i] > 0) {
                union.set[i]++;
            }
        }
        return union;
    }
}

public class Immutable {
    public static void main(String[] args) {
        int[] arr1 = { 2, 4, 6, 8, 10 };
        int[] arr2 = { 1, 2, 3, 4, 5 };

        IntSet set1 = new IntSet(arr1);
        System.out.println(set1.isMember(3));
        System.out.println(set1.size());

        IntSet set2 = new IntSet(arr2);
        System.out.println(set1.isSubSet(set2));

        IntSet set3 = set1.getComplement();
        for (int i = 1; i < 1001; i++) {
            if (set3.getIntSet()[i] > 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();

        IntSet set4 = set1.getUnion(set2);
        for (int i = 1; i < 1001; i++) {
            if (set4.getIntSet()[i] > 0){
                System.out.print(i + " ");
            }
        }

    }
}
