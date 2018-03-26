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
        assertEquals(" You have selected the following directory:\n" + 
                "    C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive\n" + 
                "\n" + 
                "Type2. Declarations found: 2; references found: 0.\n" + 
                "Type1. Declarations found: 2; references found: 0.\n" + 
                "n. Declarations found: 0; references found: 2.\n" + 
                "m. Declarations found: 0; references found: 2.\n" + 
                "l. Declarations found: 0; references found: 2.\n" + 
                "k. Declarations found: 0; references found: 2.\n" + 
                "j. Declarations found: 0; references found: 2.\n" + 
                "i. Declarations found: 0; references found: 2.\n" + 
                "y. Declarations found: 0; references found: 2.\n" + 
                "x. Declarations found: 0; references found: 2.", outContent.toString());
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
        assertEquals("You have selected the following directory:\n" + 
                "    C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive2\n" + 
                "\n" + 
                "Type4. Declarations found: 1; references found: 0.\n" + 
                "Type3. Declarations found: 1; references found: 0.\n" + 
                "Type2. Declarations found: 2; references found: 0.\n" + 
                "Type1. Declarations found: 2; references found: 0.\n" + 
                "n. Declarations found: 0; references found: 3.\n" + 
                "m. Declarations found: 0; references found: 3.\n" + 
                "l. Declarations found: 0; references found: 3.\n" + 
                "k. Declarations found: 0; references found: 3.\n" + 
                "j. Declarations found: 0; references found: 3.\n" + 
                "i. Declarations found: 0; references found: 3.\n" + 
                "y. Declarations found: 0; references found: 3.\n" + 
                "x. Declarations found: 0; references found: 3.", outContent.toString());
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
        assertEquals("You have selected the following directory:\n" + 
                "    C:\\Users\\Ryan\\Documents\\W18-SENG-300\\W18-SENG-300-A2-G23\\TestSuite\\testCases_JAR_recursive3\n" + 
                "\n" + 
                "Type2. Declarations found: 1; references found: 0.\n" + 
                "Type1. Declarations found: 1; references found: 0.\n" + 
                "n. Declarations found: 0; references found: 1.\n" + 
                "m. Declarations found: 0; references found: 1.\n" + 
                "l. Declarations found: 0; references found: 1.\n" + 
                "k. Declarations found: 0; references found: 1.\n" + 
                "j. Declarations found: 0; references found: 1.\n" + 
                "i. Declarations found: 0; references found: 1.\n" + 
                "y. Declarations found: 0; references found: 1.\n" + 
                "x. Declarations found: 0; references found: 1.", outContent.toString());
    }

}
