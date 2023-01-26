package Assignments_functionall_programming.Assignment_3;

import java.util.List;
import java.util.stream.Collectors;

public class UpperCase {
    public static List<String> transform(List<String> collection) {
        return collection.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
}
