package MathWiz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringMathHelperTest {
    private StringMathHelper sm;

    @Before
    public void setUp() throws Exception {
        sm = new StringMathHelper();
    }

    @Test
    public void testTrim() {
        assertEquals("1+1", sm.trim("MATH:1+1"));
        assertEquals("1+1+1", sm.trim("MATH:1+1+1"));
        assertEquals("125+5-1", sm.trim("MATH:  125+5-1"));
        assertEquals("125+5-1*9", sm.trim("MATH:  125+5-1asdf*9"));
        assertEquals("125+5-1*9/89", sm.trim("MATH:  125+5-1asdf*9/89"));
    }

}
