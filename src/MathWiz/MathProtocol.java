package MathWiz;

import MathWiz.StringMathHelper;
import java.util.ArrayList;

public class MathProtocol {
    private StringMathHelper sm = new StringMathHelper();

    public String process(String equation) {
        if ((equation.matches(".*\\d+.*")) && (equation.matches(".*[+-/*]+.*"))) {
            return calculate(equation);
        } else {
            return "You need to give the MATH WIZ some numbers and operators to play with";
        }
    }

    private String calculate(String equation) {
        ArrayList<String> numbers = sm.extractNumbers(equation);
        ArrayList<String> operators = sm.extractOperators(equation);
        return interleave(numbers, operators);
    }

    private String interleave(ArrayList numbers, ArrayList operators) {
        if (lackingOperators(numbers, operators)) {
            return "It looks like you have too many or too few operators";
        }
        int total = Integer.parseInt(numbers.get(0).toString());
        for (int i = 1, j = 0; i < numbers.size(); i++, j++) {
            int nextNumber = Integer.parseInt(numbers.get(i).toString());
            String operator = operators.get(j).toString();
            total = sm.stringOperator(total, nextNumber, operator);
        }
        return Integer.toString(total);
    }

    private boolean lackingOperators(ArrayList numbers, ArrayList operators) {
        int sizeDiference = numbers.size() - operators.size();
        return sizeDiference != 1;
    }

}

