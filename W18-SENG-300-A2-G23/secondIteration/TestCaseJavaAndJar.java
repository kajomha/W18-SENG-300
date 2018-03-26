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
        String path = AllTests.BASEDIR + "\\W18-SENG-300-A2-G23\\TestSuite\\testCases_javaAndJAR";
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
        "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_javaAndJAR"+
        "Type4.Declarationsfound:1;referencesfound:0."+
        "Type3.Declarationsfound:1;referencesfound:0."+
        "Type2.Declarationsfound:1;referencesfound:0."+
        "Type1.Declarationsfound:1;referencesfound:0."+
        "n.Declarationsfound:0;referencesfound:2."+
        "m.Declarationsfound:0;referencesfound:2."+
        "l.Declarationsfound:0;referencesfound:2."+
        "k.Declarationsfound:0;referencesfound:2."+
        "j.Declarationsfound:0;referencesfound:2."+
        "i.Declarationsfound:0;referencesfound:2."+
        "y.Declarationsfound:0;referencesfound:2."+
        "x.Declarationsfound:0;referencesfound:2.",outContent.toString().replaceAll("\\s+",""));
    }

}
