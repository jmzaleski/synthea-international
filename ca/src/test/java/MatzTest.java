import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mitre.synthea.TestHelper;
import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.helpers.Config;

// run this by gradlew test --tests MatzTest

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatzTest {
  private static String testStateDefault;
  private static String testTownDefault;
  private static String testStateAlternative;
  private static String testTownAlternative;

  /**
   * Configure settings across these tests.
   * @throws Exception on test configuration loading errors.
   */
  @BeforeClass
  public static void testSetup() throws Exception {
    TestHelper.loadTestProperties();
	//    testStateDefault = Config.get("test_state.default", "Massachusetts");
    testStateDefault = Config.get("test_state.default", "Ontario");
    testTownDefault = Config.get("test_town.default", "Toronto");
    testStateAlternative = Config.get("test_state.alternative", "Ontario"); //matz guess
    testTownAlternative = Config.get("test_town.alternative", "Toronto"); //matz guess
    Generator.DEFAULT_STATE = testStateDefault;
  }

  @Test
  public void matzTest() throws Exception {
	  System.out.println("hello from MatzTest");
    TestHelper.exportOff();
    String[] args = {"-s", "0", "-p", "100", "Ontario", "Toronto"};

    for (String s: args) {           
        System.out.println(s); 
    }
    
    final PrintStream original = System.out;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final PrintStream print = new PrintStream(out, true);
    System.out.println("about to call App.main");
    System.setOut(print);
    out.flush();
    App.main(args);
    System.out.println("returned from App.main..");
    out.flush();
    String output = out.toString();
    System.out.println(output);
    Assert.assertTrue(output.contains("Running with options:"));
    Assert.assertTrue(output.contains("Population:"));
    Assert.assertTrue(output.contains("Seed:"));
    Assert.assertTrue(output.contains("Location:"));
    Assert.assertTrue(output.contains("alive=3"));
    Assert.assertTrue(output.contains("dead="));
    String locationString = "Location: " + testTownDefault + ", " + testStateDefault;
    Assert.assertTrue(output.contains(locationString));
    System.setOut(original);
    System.out.println(output);
  }

}
