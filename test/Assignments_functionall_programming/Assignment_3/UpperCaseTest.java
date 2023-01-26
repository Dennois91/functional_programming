package Assignments_functionall_programming.Assignment_3;

import java.util.List;
import static java.util.Arrays.asList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
/*
Convert elements of a collection to upper case.
 */
public class UpperCaseTest {
    @Test
    public void transformShouldConvertCollectionElementsToUpperCase() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("MY", "NAME", "IS", "JOHN", "DOE");
        List<String> result = UpperCase.transform(collection);

        assertEquals(result.size(), 5);
        assertEquals(result.get(0), expected.get(0));
        assertEquals(result.get(1), expected.get(1));
        assertEquals(result.get(2), expected.get(2));
        assertEquals(result.get(3), expected.get(3));
        assertEquals(result.get(4), expected.get(4));
    }
}