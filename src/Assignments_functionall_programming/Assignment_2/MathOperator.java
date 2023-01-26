package Assignments_functionall_programming.Assignment_2;

public interface MathOperator {
    int operation(int a, int b);
    default MathOperator plus(MathOperator m){
        return (a, b) -> operation(a,b) + m.operation(a,b);
    }
}
