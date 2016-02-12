package MathWiz;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StringMathHelperTest {
    private StringMathHelper sm;

    @Before
    public void setUp() throws Exception {
        sm = new StringMathHelper();
    }

    @Test
    public void extractNumbersTest() {
        assertEquals(toArrayList("1", "1"), sm.extractNumbers("MATH:1+1"));
        assertEquals(toArrayList("1", "1", "1"), sm.extractNumbers("MATH:1+1+1"));
        assertEquals(toArrayList("125", "5", "1", "9"), sm.extractNumbers("MATH:  125+5-1asdf*9"));
    }

    @Test
    public void extractOperatorsTest() {
        assertEquals(toArrayList("+", "-"), sm.extractOperators("MATH:  125+5-1"));
        assertEquals(toArrayList("+", "-", "*", "/"), sm.extractOperators("MATH:  125+5-1asdf*9/89"));
    }

    @Test
    public void stringOperatorTest() {
        assertEquals(5, sm.stringOperator(2, 3, "+"));
        assertEquals(-1, sm.stringOperator(2, 3, "-"));
        assertEquals(6, sm.stringOperator(2, 3, "*"));
        assertEquals(6, sm.stringOperator(18, 3, "/"));
    }

    public List<String> toArrayList(String... a) {
        return Arrays.asList(a);
    }



}
