import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class TestCaseJavaAndJarRecursive {
    @Test
    public void TestCaseJavaAndJarRecursive1() {
        
        CountJavaTypes counter = new CountJavaTypes();
        String path = AllTests.BASEDIR + "\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive";
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
        String testString = "Youhaveselectedthefollowingdirectory:"+
        "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive"+
        "Type2.Declarationsfound:2;referencesfound:0."+
        "Type1.Declarationsfound:2;referencesfound:0."+
        "n.Declarationsfound:0;referencesfound:2."+
        "m.Declarationsfound:0;referencesfound:2."+
        "l.Declarationsfound:0;referencesfound:2."+
        "k.Declarationsfound:0;referencesfound:2."+
        "j.Declarationsfound:0;referencesfound:2."+
        "i.Declarationsfound:0;referencesfound:2."+
        "y.Declarationsfound:0;referencesfound:2."+
        "x.Declarationsfound:0;referencesfound:2.";
        assertEquals(testString, outContent.toString().replaceAll("\\s+",""));
    }
@Test
public void TestCaseJavaAndJarRecursive2() {
        
        CountJavaTypes counter = new CountJavaTypes();
        String path = AllTests.BASEDIR + "\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive2";
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
        String testString =
        "Youhaveselectedthefollowingdirectory:"
        + "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive2"
        + "Type2.Declarationsfound:3;referencesfound:0."
        + "Type1.Declarationsfound:3;referencesfound:0."
        + "n.Declarationsfound:0;referencesfound:3."
        + "m.Declarationsfound:0;referencesfound:3."
        + "l.Declarationsfound:0;referencesfound:3."
        + "k.Declarationsfound:0;referencesfound:3."
        + "j.Declarationsfound:0;referencesfound:3."
        + "i.Declarationsfound:0;referencesfound:3."
        + "y.Declarationsfound:0;referencesfound:3."
        + "x.Declarationsfound:0;referencesfound:3.";
        assertEquals(testString,outContent.toString().replaceAll("\\s+",""));
    }

@Test
public void TestCaseJavaAndJarRecursive3() {
        
        CountJavaTypes counter = new CountJavaTypes();
        String path = AllTests.BASEDIR + "\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive3";
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
        String testString = "Youhaveselectedthefollowingdirectory:"+
        "C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive3"+
        "Type2.Declarationsfound:4;referencesfound:0."+
        "Type1.Declarationsfound:4;referencesfound:0."+
        "n.Declarationsfound:0;referencesfound:4."+
        "m.Declarationsfound:0;referencesfound:4."+
        "l.Declarationsfound:0;referencesfound:4."+
        "k.Declarationsfound:0;referencesfound:4."+
        "j.Declarationsfound:0;referencesfound:4."+
        "i.Declarationsfound:0;referencesfound:4."+
        "y.Declarationsfound:0;referencesfound:4."+
        "x.Declarationsfound:0;referencesfound:4.";
        assertEquals(testString,outContent.toString().replaceAll("\\s+",""));
    }

}
