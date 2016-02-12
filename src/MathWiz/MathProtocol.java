package MathWiz;

import MathWiz.StringMathHelper;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> numbers = sm.extractNumbers(equation);
        ArrayList<String> operators = sm.extractOperators(equation);
        return interleave(numbers, operators);
    }

    private String interleave(ArrayList numbers, ArrayList operators) {
        int total = Integer.parseInt(numbers.get(0).toString());
        for (int i = 1, j = 0; i < numbers.size(); i++, j++) {
            int nextNumber = Integer.parseInt(numbers.get(i).toString());
            String operator = operators.get(j).toString();
            total = sm.stringOperator(total, nextNumber, operator);
        }
        return Integer.toString(total);
    }

}

