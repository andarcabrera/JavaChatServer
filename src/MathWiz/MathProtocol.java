package MathWiz;

import MathWiz.StringMathHelper;

public class MathProtocol {
    StringMathHelper sm = new StringMathHelper();

    public String process(String equation) {
        if ((equation.matches(".*\\d+.*")) && (equation.matches(".*[+-/*]+.*"))) {
            return calculate(equation);
        } else {
            return "You need to give the MATH WIZ some numbers and operators to play with";
        }
    }

    private String calculate(String equation) {
        String trimmedEq = sm.trim(equation);
        return trimmedEq;
    }

}

