package main.java.commons;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Common {

    public static class BaseTestHelperClass {
        public static String ENVIRONMENT = null;
        public static String BROWSERNAME = null;
        public static int SLEEPTIME;
        public static List<String> sheetsName = new ArrayList<String>();
    }

    public static class ExtentTestCount {
        public static int PASSCOUNT;
        public static int FAILCOUNT;
        public static int SKIPCOUNT;
    }

}
