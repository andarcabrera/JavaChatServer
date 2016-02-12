import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathProtocolTest {
    private MathProtocol mp;

    @Before
    public void setUp() throws Exception {
        mp = new MathProtocol();
    }


    @Test
    public void testCaseStatement() {
        assertEquals(3, mp.calculate(1, "2", "+"));
        assertEquals(6, mp.calculate(2, "3", "*"));
        assertEquals(7, mp.calculate(9, "2", "-"));
        assertEquals(3, mp.calculate(6, "2", "/"));
    }

    @Test
    public void testProcess() {
        assertEquals("3", mp.process("MATH:1+2"));
        assertEquals("12", mp.process("MATH:10+2"));
        assertEquals("12", mp.process("MATH:3*4"));
        assertEquals("24", mp.process("MATH:3*4*2"));
        assertEquals("2", mp.process("MATH:4-2"));
        assertEquals("3", mp.process("MATH:6/2"));
    }
}


