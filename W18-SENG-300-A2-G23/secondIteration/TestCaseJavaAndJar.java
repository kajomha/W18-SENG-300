import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class TestCaseJavaAndJar {
    
    @Test
    public void TestCaseJava() {
        
        CountJavaTypes counter = new CountJavaTypes();
        String path = "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_javaAndJAR";
        String[] args = new String[] {path};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try {
            counter.main(args);
        } catch (FileNotFoundException e) {
            fail("A");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            fail("B");
        }
        assertEquals("You have selected the following directory:\n" + 
                "    C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_javaAndJAR\n" + 
                "\n" + 
                "Type4. Declarations found: 1; references found: 0.\n" + 
                "Type3. Declarations found: 1; references found: 0.\n" + 
                "Type2. Declarations found: 1; references found: 0.\n" + 
                "Type1. Declarations found: 1; references found: 0.\n" + 
                "n. Declarations found: 0; references found: 2.\n" + 
                "m. Declarations found: 0; references found: 2.\n" + 
                "l. Declarations found: 0; references found: 2.\n" + 
                "k. Declarations found: 0; references found: 2.\n" + 
                "j. Declarations found: 0; references found: 2.\n" + 
                "i. Declarations found: 0; references found: 2.\n" + 
                "y. Declarations found: 0; references found: 2.\n" + 
                "x. Declarations found: 0; references found: 2.", outContent.toString());
    }

}