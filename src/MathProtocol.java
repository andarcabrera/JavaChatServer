import java.util.ArrayList;
import java.util.Arrays;

public class MathProtocol {
    private String digits = "0123456789";
    private String operations = "+-*/";

    public String process(String messageFromUser) {
        int result = 0;
        String trimmedEquation = messageFromUser.substring(4);
        String number = "";
        String operator = "";

        for (int i = 0; i < trimmedEquation.length(); i++) {
            char currentChar = trimmedEquation.charAt(i);
            if (digits.contains(String.valueOf(currentChar))) {
                number += String.valueOf(currentChar);
            } else if (operations.contains(String.valueOf(currentChar))) {
                operator = String.valueOf(currentChar);
                if (result == 0) {
                    result = Integer.parseInt(number);
                    number = "";
                } else {
                    result = calculate(result, number, operator);
                    operator = String.valueOf(currentChar);
                    number = "";
                }
            }
        }
        int final_result = calculate(result, number, operator);
        return Integer.toString(final_result);
    }


    protected int calculate(int result, String number, String sign) {
        int interimResult = 0;
        switch (sign) {
            case "+":
                interimResult = result + Integer.parseInt(number);
                break;
            case "-":
                interimResult = result - Integer.parseInt(number);
                break;
            case "*":
                interimResult = result * Integer.parseInt(number);
                break;
            case "/":
                interimResult = result / Integer.parseInt(number);
                break;
        }
        return interimResult;
    }
}
