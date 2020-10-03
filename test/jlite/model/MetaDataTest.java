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
        String[] expected, actual;
        MetaData meta = new MetaData("forma", conn);
        actual = meta.getColumNames();
        expected = new String[]{"id", "nombre"};
        assertArrayEquals(expected, actual);
        meta = new MetaData("articulo", conn);
        expected = new String[]{"id", "id_color", "id_forma", "codigo", "descripcion"};
        actual = meta.getColumNames();
        assertArrayEquals(expected, actual);
        conn.close();
    }

    @Test
    void getColumTypes() {
    }

    @Test
    void getColumCount() {
    }
}