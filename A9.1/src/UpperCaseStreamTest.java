import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

class UpperCaseStreamTest {

    @Test
    @DisplayName("Converts into upper case characters")
    public void test1() throws Exception{
        {
            InputStream input = new UpperCaseStream(new BufferedInputStream(new FileInputStream("uppercase.txt")));
            int i;
            String retVal ="";

            while((i = input.read()) >= 0){
                retVal +=(char)i;
            }
            input.close();
            assertEquals("UPPERCASE",retVal);
        }
    }
}