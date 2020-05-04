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
    String[][] dependencies4;
    String[][] dependencies5;
    String[] sequence0;
    String[] sequence1;
    String[] sequence2;
    String[] sequence3;
    String[] sequence4;
    String[] sequence5;
    String[] sequence6;
    String[] sequence7;
    String[] sequence8;
    String[] sequence9;
    String[] sequence10;
    String[] sequence11;
    String[] sequence12;
    Sort sort = new Sort();

    @BeforeEach
     void setUp(){
        dependencies1 = new String[][]{{"A", "B"}};
        dependencies2 = new String[][]{{"A", "B"}, {"C", "D"}};
        dependencies3 = new String[][]{{"A", "B"},{"B","C"}};
        dependencies4 = new String[][]{{"A", "B"},{"B","C"},{"D","C"},{"E","C"}};
        dependencies5 = new String[][]{{"A", "B"},{"B","C"},{"D","C"},{"E","C"},{"A","D"},{"A","E"},{"D","E"}};
        sequence0 = new String[]{};
        sequence1 = new String[]{"A","B"};
        sequence2 = new String[]{"B","A"};
        sequence3 = new String[]{"A","B","C","D"};
        sequence4 = new String[]{"A","C","B","D"};
        sequence5 = new String[]{"A","B","D","C"};
        sequence6 = new String[]{"A","D","B","C"};
        sequence7 = new String[]{"C","A"};
        sequence8 = new String[]{"A","B","D","E","C"};
        sequence9 = new String[]{"A","D","E","B","C"};
        sequence10 = new String[]{"A","B","C","D","E"};
        sequence11 = new String[]{"A","B","D","E","C"};
        sequence12 = new String[]{"A","B","E","D","C"};
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

    @Test
    @DisplayName("Einfacher Test für transitive Abhängigkeiten")
    void test4_isWellSorted(){
        sort.readDependencies(dependencies3);
        boolean result1 = sort.isWellSorted(sequence7);
        assertFalse(result1,"C ist nicht erlaubt, da B zuerst kommt. A muss also vorher gemacht werden");
    }

    @Test
    @DisplayName("Weiterer Test für transitive Abhängigkeiten")
    void test5_isWellSorted(){
        sort.readDependencies(dependencies4);
        boolean result1 = sort.isWellSorted(sequence8);
        boolean result2 = sort.isWellSorted(sequence9);
        boolean result3 = sort.isWellSorted(sequence10);
        assertTrue(result1,"Diese Sequenz ist korrekt");
        assertTrue(result2,"Diese Sequenz ist korrekt");
        assertFalse(result3,"Nach transitivität muss D und E vor C passieren, daher false!");
    }

    @Test
    @DisplayName("Weiterer Test für transitive Abhängigkeiten")
    void test6_isWellSorted(){
        sort.readDependencies(dependencies4);
        boolean result1 = sort.isWellSorted(sequence11);
        boolean result2 = sort.isWellSorted(sequence12);
        assertTrue(result1,"Diese Sequenz ist korrekt");
        assertFalse(result2,"Diese Sequenz muss falsch sein wegen Transitivität von D und E");

    }


}