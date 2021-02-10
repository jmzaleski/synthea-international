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
  
  /*
   * this is what my run printed on console. it took about 10sec on i6 mac pro
   * 
Running with options:
Population: 10
Seed: 0
Provider Seed:1612973945954
Reference Time: 1612973945954
Location: Toronto, Ontario
Min Age: 0
Max Age: 140
4 -- Javier97 Abshire638 (2 y/o M) Toronto, Ontario 
9 -- Sol312 Bergnaum523 (0 y/o M) Toronto, Ontario 
6 -- Brenna468 Heathcote539 (10 y/o F) Toronto, Ontario 
3 -- Brain142 Doyle959 (14 y/o M) Toronto, Ontario 
7 -- Cristopher265 Bogisich202 (22 y/o M) Toronto, Ontario 
5 -- Sunni15 Windler79 (52 y/o F) Toronto, Ontario 
8 -- Damon455 Macejkovic424 (58 y/o M) Toronto, Ontario 
10 -- Kenneth671 Halvorson124 (51 y/o M) Toronto, Ontario 
1 -- Dessie725 Langosh790 (57 y/o F) Toronto, Ontario 
2 -- Valene773 Corkery305 (55 y/o F) Toronto, Ontario 
Records: total=10, alive=10, dead=0
returned from App.main..
   */

  //@Test
  public void matzTestSeed0Pop10() throws Exception {
	System.out.println("hello from MatzTestSeedPop100");
    TestHelper.exportOff();
    String[] args = {"-s", "0", "-p", "10", "Ontario", "Toronto"};

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
    Assert.assertTrue(output.contains("Population: 10"));
    Assert.assertTrue(output.contains("Seed: 0"));
    Assert.assertTrue(output.contains("Location: Toronto, Ontario"));
    Assert.assertTrue(output.contains("alive=10"));
    Assert.assertTrue(output.contains("dead=0"));
    Assert.assertTrue(output.contains("4 -- Javier97 Abshire638 (2 y/o M) Toronto, Ontario")); 
    //String locationString = "Location: " + testTownDefault + ", " + testStateDefault;
    //Assert.assertTrue(output.contains(locationString));
    System.setOut(original);
    System.out.println(output);
  }
  
  /*
   * 
Seed: 0
Provider Seed:1612976554694
Reference Time: 1612976554694
Location: Toronto, Ontario
Min Age: 0
Max Age: 140
4 -- Javier97 Abshire638 (2 y/o M) Toronto, Ontario 
9 -- Sol312 Bergnaum523 (0 y/o M) Toronto, Ontario 
6 -- Brenna468 Heathcote539 (10 y/o F) Toronto, Ontario 
3 -- Brain142 Doyle959 (14 y/o M) Toronto, Ontario 
7 -- Cristopher265 Bogisich202 (22 y/o M) Toronto, Ontario 
5 -- Sunni15 Windler79 (52 y/o F) Toronto, Ontario 
10 -- Kenneth671 Halvorson124 (51 y/o M) Toronto, Ontario 
8 -- Damon455 Macejkovic424 (58 y/o M) Toronto, Ontario 
15 -- Delmar187 Grant908 (3 y/o M) Toronto, Ontario 
12 -- Liana375 Fay398 (41 y/o F) Toronto, Ontario 
18 -- Sylvester827 Frami345 (0 y/o M) Toronto, Ontario 
1 -- Dessie725 Langosh790 (57 y/o F) Toronto, Ontario 
2 -- Valene773 Corkery305 (55 y/o F) Toronto, Ontario 
11 -- Edmundo94 Dietrich576 (54 y/o M) Toronto, Ontario DECEASED
17 -- Shelly431 Baumbach677 (21 y/o F) Toronto, Ontario 
14 -- Cameron381 Howell947 (31 y/o M) Toronto, Ontario 
19 -- Reuben327 Harris789 (45 y/o M) Toronto, Ontario 
13 -- Marva153 Shanahan202 (82 y/o F) Toronto, Ontario DECEASED
22 -- Felisha640 Welch179 (34 y/o F) Toronto, Ontario 
11 -- Elvis145 Windler79 (55 y/o M) Toronto, Ontario 
21 -- Dong972 Hane680 (68 y/o F) Toronto, Ontario 
24 -- Kacie297 Kub800 (34 y/o F) Toronto, Ontario 
20 -- Valentina258 O'Reilly797 (74 y/o F) Toronto, Ontario 
23 -- Birgit199 Brakus656 (62 y/o F) Toronto, Ontario 
28 -- Roman389 Fritsch593 (9 y/o M) Toronto, Ontario 
25 -- Vertie683 Stoltenberg489 (34 y/o F) Toronto, Ontario 
16 -- Torrie697 Konopelski743 (83 y/o F) Toronto, Ontario DECEASED
29 -- Magdalene960 Nikolaus26 (11 y/o F) Toronto, Ontario 
27 -- Elbert916 Jast432 (40 y/o M) Toronto, Ontario 
26 -- Minerva230 Dickinson688 (43 y/o F) Toronto, Ontario 
33 -- Winford225 Kilback373 (27 y/o M) Toronto, Ontario 
36 -- Anitra287 Rempel203 (27 y/o F) Toronto, Ontario 
31 -- Hazel720 Sanford861 (58 y/o F) Toronto, Ontario 
30 -- Shandra823 Lehner980 (63 y/o F) Toronto, Ontario 
38 -- Ivette731 Turcotte120 (1 y/o F) Toronto, Ontario 
34 -- Hung902 Mraz590 (58 y/o M) Toronto, Ontario 
32 -- Kathryn101 Quitzon246 (70 y/o F) Toronto, Ontario 
16 -- Velvet616 Oberbrunner298 (58 y/o F) Toronto, Ontario DECEASED
13 -- Eddie505 Predovic534 (102 y/o F) Toronto, Ontario 
37 -- George991 Corwin846 (9 y/o F) Toronto, Ontario 
39 -- Britt177 Hintz995 (37 y/o M) Toronto, Ontario 
42 -- Cedric746 Goyette777 (25 y/o M) Toronto, Ontario 
35 -- Myles862 Nienow652 (66 y/o M) Toronto, Ontario 
41 -- Merissa718 Gislason620 (32 y/o F) Toronto, Ontario DECEASED
16 -- Kate239 Erdman779 (34 y/o F) Toronto, Ontario DECEASED
45 -- Johna806 Jaskolski867 (13 y/o F) Toronto, Ontario 
44 -- Hans694 Miller503 (30 y/o M) Toronto, Ontario 
43 -- Leonel449 Rodriguez71 (43 y/o M) Toronto, Ontario 
40 -- Mikki421 Bruen238 (52 y/o F) Toronto, Ontario 
46 -- Tesha491 McGlynn426 (27 y/o F) Toronto, Ontario 
50 -- Huey641 Luettgen772 (14 y/o M) Toronto, Ontario 
47 -- Leland44 Dicki44 (37 y/o M) Toronto, Ontario 
41 -- Ona426 Kris249 (34 y/o F) Toronto, Ontario 
52 -- Shayna64 Wehner319 (19 y/o F) Toronto, Ontario 
49 -- Elisha578 Leuschke194 (59 y/o M) Toronto, Ontario 
48 -- Tanika746 Lebsack687 (48 y/o F) Toronto, Ontario 
55 -- Eric487 Carter549 (51 y/o M) Toronto, Ontario 
57 -- Elwood28 Ullrich385 (23 y/o M) Toronto, Ontario 
16 -- Annabelle638 Lakin515 (84 y/o F) Toronto, Ontario 
51 -- Eva64 Cabrera242 (71 y/o F) Toronto, Ontario 
54 -- Raisa16 Huel628 (75 y/o F) Toronto, Ontario 
56 -- Joya53 Lesch175 (61 y/o F) Toronto, Ontario 
53 -- Blanch844 Turner526 (73 y/o F) Toronto, Ontario 
58 -- Maura912 Abbott774 (56 y/o F) Toronto, Ontario 
67 -- Savannah232 Marvin195 (0 y/o F) Toronto, Ontario 
59 -- Leeann571 Bosco882 (52 y/o F) Toronto, Ontario 
64 -- Jude172 Gorczany269 (47 y/o M) Toronto, Ontario 
60 -- Les282 Strosin214 (63 y/o M) Toronto, Ontario 
69 -- Eliz744 Bogan287 (28 y/o F) Toronto, Ontario 
63 -- Enoch803 Kautzer186 (73 y/o M) Toronto, Ontario DECEASED
66 -- Remona896 Graham902 (65 y/o F) Toronto, Ontario 
68 -- Conrad619 Rodriguez71 (48 y/o M) Toronto, Ontario 
62 -- Oliva247 Hilll811 (84 y/o F) Toronto, Ontario 
65 -- Sol312 Wisoky380 (72 y/o M) Toronto, Ontario 
70 -- Shane235 Towne435 (59 y/o M) Toronto, Ontario 
74 -- Damion480 Brekke496 (25 y/o M) Toronto, Ontario 
72 -- Booker670 Kuhlman484 (60 y/o M) Toronto, Ontario 
77 -- Elizabet136 Runte676 (13 y/o F) Toronto, Ontario 
76 -- Kasie673 Dietrich576 (41 y/o F) Toronto, Ontario 
73 -- Odis959 Larkin917 (64 y/o M) Toronto, Ontario 
71 -- Versie644 Hudson301 (67 y/o F) Toronto, Ontario 
78 -- Jennifer579 Miller503 (31 y/o F) Toronto, Ontario 
83 -- Carmen818 Wiza601 (7 y/o M) Toronto, Ontario 
84 -- Regan713 Konopelski743 (10 y/o F) Toronto, Ontario 
75 -- Ahmed109 Cruickshank494 (77 y/o M) Toronto, Ontario DECEASED
80 -- Ulrike696 Franecki195 (36 y/o F) Toronto, Ontario DECEASED
79 -- Michal596 Durgan499 (49 y/o M) Toronto, Ontario 
63 -- Raleigh478 Oberbrunner298 (110 y/o M) Toronto, Ontario 
82 -- Aurora248 Aufderhar910 (31 y/o F) Toronto, Ontario 
81 -- Geraldo282 Osinski784 (50 y/o M) Toronto, Ontario 
86 -- Joseph689 Gerhold939 (23 y/o M) Toronto, Ontario 
91 -- Stanley702 Walker122 (2 y/o M) Toronto, Ontario 
85 -- Tod265 Sanford861 (36 y/o M) Toronto, Ontario 
92 -- Roseanna881 Ledner144 (26 y/o F) Toronto, Ontario 
94 -- Tyler508 Farrell962 (4 y/o M) Toronto, Ontario 
90 -- Myles862 Pacocha935 (41 y/o M) Toronto, Ontario 
89 -- Daryl568 Dickinson688 (45 y/o M) Toronto, Ontario 
80 -- Gia583 Murray856 (51 y/o F) Toronto, Ontario DECEASED
87 -- Mel236 Kuphal363 (53 y/o M) Toronto, Ontario 
88 -- Martin117 Morissette863 (45 y/o M) Toronto, Ontario 
75 -- Hans694 Williamson769 (78 y/o M) Toronto, Ontario 
93 -- Antonia30 Ankunding277 (44 y/o F) Toronto, Ontario 
99 -- Jere230 Hermiston71 (26 y/o M) Toronto, Ontario 
98 -- Ernesto186 Thiel172 (41 y/o M) Toronto, Ontario 
96 -- Cody889 Mills423 (49 y/o M) Toronto, Ontario 
95 -- Megan108 Senger904 (63 y/o F) Toronto, Ontario 
100 -- Fredda152 Kautzer186 (44 y/o F) Toronto, Ontario 
80 -- Maribel82 Spinka232 (78 y/o F) Toronto, Ontario 
Records: total=108, alive=98, dead=10
returned from App.main..   
   *
   */
  @Test
  public void matzTestSeed0Pop100() throws Exception {
	  System.out.println("hello from MatzTestSeedPop100");
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
    Assert.assertTrue(output.contains("Population: 100"));
    Assert.assertTrue(output.contains("Seed: 0"));
    Assert.assertTrue(output.contains("Location: Toronto, Ontario"));
    Assert.assertTrue(output.contains("alive=98"));
    Assert.assertTrue(output.contains("dead=10"));
    Assert.assertTrue(output.contains("80 -- Maribel82 Spinka232 (78 y/o F) Toronto, Ontario")); 
    System.setOut(original);
    System.out.println(output);
  }

}
