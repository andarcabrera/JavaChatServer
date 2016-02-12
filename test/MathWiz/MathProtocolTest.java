package MathWiz;

import MathWiz.MathProtocol;
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
    public void testNoNumber() {
        String infoNeeded = "You need to give the MATH WIZ some numbers and operators to play with";
        assertEquals(infoNeeded, mp.process(""));
        assertEquals(infoNeeded, mp.process("MATH:No digits +"));
        assertEquals(infoNeeded, mp.process("MATH:No operators 344 4353"));
        assertEquals(infoNeeded, mp.process("MATH:No digits or operators"));
    }

//    @Test
//    public void testOperationWithTwoNumbers() {
//        assertEquals("2", mp.process("MATH:1 + 1"));
//    }


}


