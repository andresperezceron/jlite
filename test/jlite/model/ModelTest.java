package jlite.model;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private final JliteConnection conn;

    ModelTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
    }

    @Test
    void getCrud() {
        assertNotNull(new Model("articulo", conn).getCrud());
        conn.close();
    }

    @Test
    void getIdBy() {
        Model model = new Model("articulo", conn);
        assertEquals(8, model.getIdBy("codigo", 1008, conn));
        model = new Model("forma", conn);
        assertEquals(3, model.getIdBy("nombre", "rectangulo", conn));
        conn.close();
    }

    @Test
    void exist() {
        Model model = new Model("articulo", conn);
        assertTrue(model.exist("codigo", 1008, conn));
        assertFalse(model.exist("codigo", 1111, conn));
        conn.close();
    }

    @Test
    void getBy() {
        Model model = new Model("articulo", conn);
        Object[] expected = new Object[]{8, 3, 2, 1008, "cuadrado azul"};
        assertArrayEquals(expected, model.getBy("codigo", 1008, conn));
        conn.close();
    }
}