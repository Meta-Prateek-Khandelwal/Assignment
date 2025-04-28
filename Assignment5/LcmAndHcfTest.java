package Assignment5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LcmAndHcfTest {
    @Test
    public void testLcmAndHcf(){
        HcfLcm obj = new HcfLcm();
        assertEquals(2, obj.findHcfMethod(2, 4));
        assertEquals(1, obj.findHcfMethod(12, 17));

        assertEquals(4, obj.findLcmMethod(2, 4));
        assertEquals(60, obj.findLcmMethod(12, 15));
    }
}
