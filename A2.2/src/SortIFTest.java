import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortIFTest {
    String[][] dependencies;
    String[] sequence1;
    String[] sequence2;
    Sort sort = new Sort();

    @BeforeEach
    void setUp(){
        dependencies = new String[][]{{"A", "C"}, {"C", "D"},  {"B", "C"}};
        sequence1 = new String[]{"A","B","C","D"};
        sequence2 = new String[]{"D","C","B","A"};
    }

    @Test
    @DisplayName("Test, bei dem eine richtige Sequenz als richtig erkannt werden soll")
    void test1_isWellSorted() {
        sort.readDependencies(dependencies);
        boolean result1 = sort.isWellSorted(sequence1);
        assertTrue(result1);
    }

    @Test
    @DisplayName("Test, bei dem eine falsche Sequenz als falsch erkannt werden soll")
    void test2_isWellSorted() {
        sort.readDependencies(dependencies);
        boolean result2 = sort.isWellSorted(sequence2);
        assertFalse(result2);
    }


}