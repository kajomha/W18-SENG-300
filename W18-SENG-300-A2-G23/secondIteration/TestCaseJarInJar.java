import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class TestCaseJarInJar {
    
    @Test
    public void TestCaseJava() {
        
        CountJavaTypes counter = new CountJavaTypes();
        String path = AllTests.BASEDIR + "\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JARInJAR";
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
        assertEquals("Youhaveselectedthefollowingdirectory:"+
        "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JARInJAR"+
        "Thisdirectorydoesnotcontainanyfileswiththeextension:'.java'",outContent.toString().replaceAll("\\s+",""));
    }

}
