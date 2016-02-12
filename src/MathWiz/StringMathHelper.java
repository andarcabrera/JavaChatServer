package MathWiz;

/**
 * Created by andacabrera29 on 2/12/16.
 */
public class StringMathHelper {
    public String trim(String equation) {
        return equation.replaceAll("[^0-9+-/*/]+", "");
    }
}
