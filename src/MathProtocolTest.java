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
        assertEquals("You need to give the MATH WIZ some numbers to play with", mp.process(""));
    }


}


