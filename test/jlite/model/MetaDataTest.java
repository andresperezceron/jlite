package jlite.model;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetaDataTest {
    private final JliteConnection conn;

    private MetaDataTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
    }

    @Test
    void getColumNames() {
        assertArrayEquals(new String[]{"id", "nombre"}, new MetaData("forma", conn).getColumnNames());
        String[] expected = {"id", "id_color", "id_forma", "codigo", "descripcion"};
        assertArrayEquals(expected, new MetaData("articulo", conn).getColumnNames());
        conn.close();
    }

    @Test
    void getColumTypes() {
        assertArrayEquals(new String[]{"INTEGER", "TEXT"}, new MetaData("forma", conn).getColumnTypes());
        String[] expected = {"INTEGER", "INTEGER", "INTEGER", "INTEGER", "TEXT"};
        assertArrayEquals(expected, new MetaData("articulo", conn).getColumnTypes());
        conn.close();
    }

    @Test
    void getColumCount() {
        assertEquals(2, new MetaData("color", conn).getColumnCount());
        assertEquals(5, new MetaData("articulo", conn).getColumnCount());
        conn.close();
    }
}