class Search{
    int idx = -1;

    int linerSearch(int[] arr, int key){
        idx++;
        if(idx == arr.length) return -1;
        return arr[idx] == key ? idx : linerSearch(arr, key);
    }

    int st = 0;
    int end = 0;
    int count = 0;
    int binarySearch(int[] arr, int key){
        if(count == 0){
            end = arr.length;
            count++;
        }

        if(st > end) return -1;
        int mid = (st + end)/2;
        if(arr[mid] == key) return mid;
        else if(arr[mid] > key) {
            end = mid - 1;
            binarySearch(arr, key);
        }else{
            st = mid+1;
            binarySearch(arr, key);
        }
        return -1;
    }
}

public class SearchingAlgo {    
    public static void main(String[] args) {
        Search search = new Search();
        int[] arr = {1,3,5,7,9};
        System.out.println(search.linerSearch(arr, 5));
        System.out.println(search.binarySearch(arr, 5));
    }
}
