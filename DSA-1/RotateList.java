import java.util.LinkedList;

class RotateList{
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        int l = 2;
        int r = 5;
        int n = 2;
        rotate(list, l, r, n);
    }

    private static void rotate(LinkedList<Integer> list, int l, int r, int n) {

        while(n > 0){
            list.add(l, list.remove(r));
            l++;
            r--;
            n--;
        }
        
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}