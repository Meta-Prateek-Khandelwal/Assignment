import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

interface SelfStack {
    void push(int item);

    int pop();

    int peek();

    boolean isEmpty();

    void display();
}

class StackImplement implements SelfStack {
    private ArrayList<Integer> token;
    private int top;

    StackImplement() {
        token = new ArrayList<>();
        top = -1;
    }

    @Override
    public void push(int item) {
        ++top;
        token.add(item);
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty.");
            return -1;
        }
        int val = token.remove(top);
        top--;
        return val;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty.");
            return -1;
        }
        int val = token.get(top);
        return val;
    }

    @Override
    public boolean isEmpty() {
        if (token.size() == 0)
            return true;
        return false;
    }

    @Override
    public void display() {
        for (int i = 0; i < token.size(); i++) {
            System.out.print(token.get(i) + " ");
        }
    }
}

class InfixSolver {
    HashMap<String, Integer> preference;

    InfixSolver() {
        preference = new HashMap<>();

        preference.put("*", 12);
        preference.put("/", 12);
        preference.put(">=", 12);
        preference.put("<=", 8);
        preference.put("<", 8);
        preference.put(">", 8);
        preference.put("==", 8);
        preference.put("!=", 8);
        preference.put("+", 11);
        preference.put("-", 11);
        preference.put("||", 3);
        preference.put("&&", 4);
        preference.put("(", -1);
        preference.put(")", -1);
    }

    int checkPrecedence(String operator) {
        return preference.get(operator);
    }

    int calculation(int value1, int value2, String operator) {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
            case "||":
                return (value1 != 0 || value2 != 0) ? 1 : 0;
            case "&&":
                return (value1 != 0 && value2 != 0) ? 1 : 0;
            case "<=":
                return (value1 <= value2) ? 1 : 0;
            case ">=":
                return (value1 >= value2) ? 1 : 0;
            case ">":
                return (value1 > value2) ? 1 : 0;
            case "<":
                return (value1 < value2) ? 1 : 0;
            case "!=":
                return (value1 != value2) ? 1 : 0;
            case "==":
                return (value1 == value2) ? 1 : 0;
        }
        return 0;
    }

    int infixEvaluate(String expression) {
        String[] expressionArray = expression.split(" ");
        Stack<String> operators = new Stack<>();
        StackImplement oprands = new StackImplement();

        for (String exp : expressionArray) {
            if (exp.matches("\\d+")) {
                oprands.push(Integer.parseInt(exp));
            } else if (exp.equals("(")) {
                operators.push(exp);
                System.out.println(operators);
            } else if (exp.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    int first = oprands.pop();
                    int second = oprands.pop();
                    String operator = operators.pop();
                    int value = calculation(second, first, operator);
                    oprands.push(value);
                }
                operators.pop();
            }else {
                while (!operators.isEmpty() && checkPrecedence(operators.peek()) >= checkPrecedence(exp)) {
                    int first = oprands.pop();
                    int second = oprands.pop();
                    String operator = operators.pop();
                    int value = calculation(second, first, operator);
                    oprands.push(value);
                }
                operators.push(exp);
            }
        }

        while (!operators.isEmpty()) {
            int first = oprands.pop();
            int second = oprands.pop();
            String operator = operators.pop();
            int value = calculation(second, first, operator);
            oprands.push(value);
        }

        return oprands.pop();
    }
}

public class Infix {
    public static void main(String[] args) {
        StackImplement stack = new StackImplement();
        InfixSolver infix = new InfixSolver();
        String expression = "( 9 + 3 ) * ( 9 / 3 ) + 13 || 21 + ( 2 && 3 ) + ( 21 != 21 ) && ( 22 == 22 )";

        System.out.println(infix.infixEvaluate(expression));
    }
}
