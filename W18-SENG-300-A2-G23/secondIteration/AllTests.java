

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCaseJava.class, TestCaseJavaAndJar.class, TestCaseJavaAndJarRecursive.class, TestCaseJarInJar.class })
public class AllTests {
    // Please point to the testFiles dir on your machine
    public static String BASEDIR = "C:\\Users\\Ryan\\Documents\\W18-SENG-300";
}

