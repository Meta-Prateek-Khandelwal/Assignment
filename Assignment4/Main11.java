package Assignment4;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class Main11 {
    @Test
    public void idTest(){
        Main1 m1 = new Main1();
        assertEquals(10, m1.addSum(10,0));
    }

    @Test
    public void idTest1(){
        Main1 m1 = new Main1();
        assertEquals(2, m1.dived(10, 0));
    }
    
}
