import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class ExtensibleCalculatorIFTest {
    ExtensibleCalculatorIFImpl calc = new ExtensibleCalculatorIFImpl();

    @Test
    @DisplayName("Test if a new operation is added and executed properly")
    void test_addOperation(){
        calc.addOperation("+" , (x,y)-> x+y);
        int result = calc.calc("+",5,3);

        assertSame(8,result);
    }

    @Test
    @DisplayName("Test if the operation String is correct")
    void test_operations(){
        calc.addOperation("+" , (x,y)-> x+y);
        calc.addOperation("-" , (x,y)-> x-y);
        calc.addOperation("*" , (x,y)-> x*y);

        String[] operations = calc.operations();

        assertEquals("*",operations[0]);
        assertEquals("+",operations[1]);
        assertEquals("-",operations[2]);
    }

    @Test
    @DisplayName("Test several operations at once")
    void test_severalOperations(){
        calc.addOperation("+",(x,y)-> x+y);
        calc.addOperation("-",(x,y)-> x-y);
        calc.addOperation("*",(x,y)-> x*y);
        calc.addOperation("/",(x,y)-> x/y);
        calc.addOperation("%",(x,y)-> x%y);


        int result1 = calc.calc("+", 5,5);
        int result2 = calc.calc("-", 5,3);
        int result3 = calc.calc("*", 5,5);
        int result4 = calc.calc("/", 10,2);
        int result5 = calc.calc("%", 11,2);

        assertSame(10,result1);
        assertSame(2,result2);
        assertSame(25,result3);
        assertSame(5,result4);
        assertSame(1,result5);
    }
}