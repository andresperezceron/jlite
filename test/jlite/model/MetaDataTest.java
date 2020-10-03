package jlite.model;

import jlite.JliteConfig;
import jlite.connection.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataTest {
    private final JliteConnection conn;

    private MetaDataTest() {
        JliteConfig.setPathDb(getClass().getResource("/db/test.db").toString());
        conn = new JliteConnection();
    }

    @Test
    void getColumNames() {
        assertArrayEquals(new String[]{"id", "nombre"}, new MetaData("forma", conn).getColumNames());
        String[] expected = {"id", "id_color", "id_forma", "codigo", "descripcion"};
        assertArrayEquals(expected, new MetaData("articulo", conn).getColumNames());
        conn.close();
    }

    @Test
    void getColumTypes() {
        assertArrayEquals(new String[]{"INTEGER", "TEXT"}, new MetaData("forma", conn).getColumTypes());
        String[] expected = {"INTEGER", "INTEGER", "INTEGER", "INTEGER", "TEXT"};
        assertArrayEquals(expected, new MetaData("articulo", conn).getColumTypes());
        conn.close();
    }

    @Test
    void getColumCount() {
        assertEquals(2, new MetaData("color", conn).getColumCount());
        assertEquals(5, new MetaData("articulo", conn).getColumCount());
        conn.close();
    }
}