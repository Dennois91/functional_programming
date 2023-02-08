package Assignments_functionall_programming.Assignment_3;

import Weeks_With_JAVA.Assignments_functionall_programming.Assignment_3.LessThenFour;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LessThenFourTest {
/*
Filter collection so that only elements with less then 4 characters are returned.
 */

    @Test
    public void transformShouldFilterCollection() {

        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        List<String> result = LessThenFour.transform(collection);

        assertEquals(result.size(), 3);
        assertEquals(result.get(0), expected.get(0));
        assertEquals(result.get(1), expected.get(1));
        assertEquals(result.get(2), expected.get(2));

    }
}


