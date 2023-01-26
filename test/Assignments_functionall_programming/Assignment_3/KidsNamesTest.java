package Assignments_functionall_programming.Assignment_3;
import org.junit.jupiter.api.Test;
import java.util.List;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Get names of all kids (under age of 18)
 */

public class KidsNamesTest {

    @Test
    public void getKidNameShouldReturnNamesOfAllKids() {

        KidsNames.Person sara = new KidsNames.Person("Sara", 4);
        KidsNames.Person viktor = new KidsNames.Person("Viktor", 40);
        KidsNames.Person eva = new KidsNames.Person("Eva", 42);
        KidsNames.Person anna = new KidsNames.Person("Anna", 5);
        List<KidsNames.Person> collection = asList(sara, eva, viktor, anna);

        List<String> result = KidsNames.getKidNames(collection);

        assertEquals(result.size(), 2);
        assertTrue(result.contains("Sara"));
        assertTrue(result.contains("Anna"));
        assertFalse(result.contains("Viktor"));
        assertFalse(result.contains("Eva"));

    }
}