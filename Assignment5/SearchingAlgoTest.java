package Assignment5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchingAlgoTest {
    @Test
    public void SearchingTest(){
        Search find1 = new Search();

        int[] arr1 = {1, 2, 3, 4, 5, 6};
        assertEquals(3, find1.linerSearch(arr1, 4));
        assertEquals(3, find1.binarySearch(arr1, 4));
    }
}
