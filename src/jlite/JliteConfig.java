package jlite;

public class JliteConfig {
    private static String pathDb;

    public static String getPathDb() {
        return "jdbc:sqlite:" + pathDb;
    }

    public static void setPathDb(String pathDb) {
        JliteConfig.pathDb = pathDb;
    }
}