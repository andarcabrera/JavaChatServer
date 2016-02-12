package MathWiz;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by andacabrera29 on 2/12/16.
 */
public class StringMathHelper {
    public ArrayList<String> extractNumbers(String equation) {
        String numbers = equation.replaceAll("[^0-9]+", " ").trim();
        ArrayList<String> collectedNumbers = new ArrayList<String>(Arrays.asList(numbers.split(" ")));
        return collectedNumbers;
    }

    public ArrayList<String> extractOperators(String equation) {
        String operators = equation.replaceAll("[^+-/*/]+", " ").trim();
        ArrayList<String> collectedOperators = new ArrayList<String>(Arrays.asList(operators.split(" ")));
        return collectedOperators;
    }


    public int stringOperator(int total, int nextNumber, String operator) {
        int interimResult = 0;
        switch (operator) {
            case "+":
                interimResult = total + nextNumber;
                break;
            case "-":
                interimResult = total - nextNumber;
                break;
            case "*":
                interimResult = total * nextNumber;
                break;
            case "/":
                interimResult = total / nextNumber;
                break;
        }
        return interimResult;
    }
}
