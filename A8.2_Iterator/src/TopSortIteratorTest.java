import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopSortIteratorTest {

    ArrayList<String> list;
    ArrayList<String> sequence;
    String[][] dependency1;
    String[][] dependency2;
    TopSortIterator t1;
    TopSortIterator t2;

    @BeforeEach
    void setUp(){
        dependency1 = new String[][]{{"A", "B"},{"B","C"},{"C","D"},{"E","C"}};
        dependency2 = new String[][]{{"A", "B"},{"B","C"},{"C","D"},{"C","E"}};
        t1 =  new TopSortIterator(dependency1);
        t2 =  new TopSortIterator(dependency2);
        list = new ArrayList<String>();

        sequence = new ArrayList<String>();
        sequence.add("A");
        sequence.add("B");
        sequence.add("E");
        sequence.add("C");
        sequence.add("D");
    }

    @Test
    void test1() {
        for (TopSortIterator it = t1; it.hasNext(); ) {
            String str = it.next();
            list.add(str);
        }
        assertEquals(list,sequence);
    }

    @Test
    void test2() {
        for (TopSortIterator it = t2; it.hasNext(); ) {
            String str = it.next();
            list.add(str);
        }
        assertNotEquals(list,sequence);
    }
}