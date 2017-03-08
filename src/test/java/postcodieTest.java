import org.junit.Test;

import static org.testng.Assert.*;

/**
 * Created by cliff on 08/03/2017.
 */
public class postcodieTest {

    @Test
    public void testIsPostcodeNotNull(){
        String[] a = {"cm233er", "cm233ed"};
        postcodie pc = new postcodie();
        pc.main(a);
        String result = pc.getURL();
        assertEquals("http://api.postcodes.io/postcodes/f", result);
    }
}