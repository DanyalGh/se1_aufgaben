import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortIFTest {
    String[][] dependencies1;
    String[][] dependencies2;
    String[][] dependencies3;
    String[] sequence0;
    String[] sequence1;
    String[] sequence2;
    String[] sequence3;
    String[] sequence4;
    String[] sequence5;
    String[] sequence6;
    Sort sort = new Sort();

    @BeforeEach
     void setUp(){
        dependencies1 = new String[][]{{"A", "B"}};
        dependencies2 = new String[][]{{"A", "B"}, {"C", "D"}};
        sequence0 = new String[]{};
        sequence1 = new String[]{"A","B"};
        sequence2 = new String[]{"B","A"};
        sequence3 = new String[]{"A","B","C","D"};
        sequence4 = new String[]{"A","C","B","D"};
        sequence5 = new String[]{"A","B","D","C"};
        sequence6 = new String[]{"A","D","B","C"};
    }

    @Test
    @DisplayName("Test, der eine Exception erwartet, wenn eine leere Sequenz gesendet wird")
    void test1_isWellSorted() {
        assertThrows( IndexOutOfBoundsException.class, () -> {
            sort.isWellSorted(sequence0);
        } , "An IndexOutOfBoundsException was expected to be thrown, but nothing happened");
    }

    @Test
    @DisplayName("Einfacher Test, um falsche und richtige Sequenzen bei einer einzigen Regel zu erkennen")
    void test2_isWellSorted() {
        sort.readDependencies(dependencies1);
        boolean result1 = sort.isWellSorted(sequence1);
        boolean result2 = sort.isWellSorted(sequence2);
        assertTrue(result1,"Eine richtige Sequenz wurde als falsch erkannt");
        assertFalse(result2,"Eine falsche Sequenz wurde als richtig erkannt");
    }

    @Test
    @DisplayName("Test der eine Sequenz auf zwei Unabhängige Dependencies prüft")
    void test3_isWellSorted() {
        sort.readDependencies(dependencies2);
        boolean result1 = sort.isWellSorted(sequence3);
        boolean result2 = sort.isWellSorted(sequence4);
        boolean result3 = sort.isWellSorted(sequence5);
        boolean result4 = sort.isWellSorted(sequence6);
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);
    }


}