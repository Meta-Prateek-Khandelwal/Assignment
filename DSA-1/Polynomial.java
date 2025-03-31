import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        list.add(new ArrayList<Integer> (Arrays.asList(3,2,4,0)));
        list.add(new ArrayList<Integer> (Arrays.asList(-5,0,0,2)));
        list.add(new ArrayList<Integer> (Arrays.asList(1,1,0,0)));
        list.add(new ArrayList<Integer> (Arrays.asList(-1,0,1,0)));
        list.add(new ArrayList<Integer> (Arrays.asList(20,0,0,0)));

        System.out.println(degree(list));
    }

    private static int degree(ArrayList<ArrayList<Integer>> list) {
        int maxDegree = Integer.MIN_VALUE;

        for(int i = 0; i < list.size(); i++){
            int sumDegree = 0;
            for(int j = 1; j < list.get(i).size(); j++){
                sumDegree += list.get(i).get(j);
            }
            maxDegree = Math.max(sumDegree, maxDegree);
        }
        return maxDegree;
    }
}
