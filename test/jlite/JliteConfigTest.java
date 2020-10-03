package jlite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JliteConfigTest {
    private JliteConfigTest() {
        JliteConfig.setPathDb(getClass().getResource("/db/test.db").toString());
    }

    @Test
    void getPathDb() {
        String expected = "jdbc:sqlite:file:/D:/Programacion/Java/ij/jlite/out/test/jlite/db/test.db";
        assertEquals(expected, JliteConfig.getPathDb());
    }
}