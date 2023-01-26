package Assignments_functionall_programming.Assignment_2;

public class Main {


    public static void main(String[] args) {


        MathOperator addition = Integer::sum;
        MathOperator subtraction = (a, b) -> a - b;
        MathOperator multiplication = (a, b) -> a * b;
        MathOperator division = (a, b) -> a / b;
        MathOperator combined = addition.plus(multiplication);
        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
        System.out.println("(5 + 5) + (5 * 5) = " + combined.operation(5, 5));


        SingleDigitOperation squareOf = (a) -> a * a;
        System.out.println("Square of 5 = " + operation(5,squareOf));
        SingleDigitOperation checkPositive = a -> {
            if (a >= 0) return 1;
            else return 0;
        };

        System.out.println(squareOf.operate(4));
        System.out.println(squareOf.operate(-2));
        System.out.println(checkPositive.operate(13));
        System.out.println(checkPositive.operate(-2));
    }

    private static int operate(int a, int b, MathOperator mathOperation) {

        return mathOperation.operation(a, b);
    }
    private static int operation(int a, SingleDigitOperation singleDigitOperation){

        return singleDigitOperation.operate(a);
    }
}