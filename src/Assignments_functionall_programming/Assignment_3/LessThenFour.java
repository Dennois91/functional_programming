package Assignments_functionall_programming.Assignment_3;

import java.util.List;
import java.util.stream.Collectors;

public class LessThenFour {

    public static List<String> transform(List<String> l) {
        return l.stream().filter(s -> s.length() <=3).collect(Collectors.toList());
    }
}
