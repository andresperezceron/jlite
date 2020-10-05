package jlite.model;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrudTest {
    private final JliteConnection conn;

    CrudTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
    }

    @Test
    void setAndGetByColumnIndex() {
        Object actual = "set1";
        Crud crud = new Crud("forma", conn);
        assertNotEquals(crud.get(1), actual);
        crud.set(1, actual);
        assertEquals(crud.get(1), actual);
        conn.close();
    }

    @Test
    void setAndGetByColumnName() {
        int actual = 666;
        Crud crud = new Crud("articulo", conn);
        assertNotEquals(crud.get("codigo"), actual);
        crud.set("codigo", actual);
        assertEquals(crud.get("codigo"), actual);
        conn.close();
    }

    @Test
    void load() {
        Crud crud = new Crud("articulo", conn);
        crud.load(5, conn);
        assertEquals(crud.get("id_color"), 5);
        assertEquals(crud.get("id_forma"), 1);
        assertEquals(crud.get(3), 1005);
        assertEquals(crud.get(4), "circulo negro");
        conn.close();
    }

    @Test
    void insertAndDelete() {
        assertEquals(0, (int) conn.simpleQuery("SELECT COUNT(*) FROM color WHERE nombre='rosa';"));
        Crud crud = new Crud("color", conn);
        crud.set("nombre", "rosa");
        crud.insert(conn);
        assertEquals(1, (int) conn.simpleQuery("SELECT COUNT(*) FROM color WHERE nombre='rosa';"));
        crud.load((int) conn.simpleQuery("SELECT id FROM color WHERE nombre = 'rosa';"), conn);
        crud.delete(conn);
        assertEquals(0, (int) conn.simpleQuery("SELECT COUNT(*) FROM color WHERE nombre='rosa';"));
        conn.close();
    }

    @Test
    void update() {
        Crud crud = new Crud("forma", conn);
        crud.set("nombre", "formatest");
        crud.insert(conn);
        assertEquals(1, (int) conn.simpleQuery("SELECT COUNT(*) FROM forma WHERE nombre = 'formatest';"));
        int id = (int) conn.simpleQuery("SELECT id FROM forma WHERE nombre ='formatest';");
        crud.load(id, conn);
        crud.set("nombre", "cambiado");
        crud.update(conn);
        assertEquals(1, (int) conn.simpleQuery("SELECT COUNT(*) FROM forma WHERE nombre = 'cambiado';"));
        crud.load(id, conn);
        crud.delete(conn);
        conn.close();
    }
}