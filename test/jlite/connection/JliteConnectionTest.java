package jlite.connection;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class JliteConnectionTest {
    private final JliteConnection conn;

    private JliteConnectionTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
    }

    @Test
    void simpleQuery() {
        int expected = 25;
        int actual = (int) conn.simpleQuery("select count(*) from articulo;");
        assertEquals(expected, actual);
        conn.close();
    }

    @Test
    void getResultSet() {
        String sql = "SELECT a.codigo FROM  articulo a " +
                "INNER JOIN color c ON c.id = a.id_color " +
                "WHERE c.nombre = 'rojo';";
        ResultSet resultset = conn.getResultSet(sql);
        int[] actual = new int[5];
        try {
            for(int i=0; resultset.next(); i++)
                actual[i] = resultset.getInt(1);
            resultset.close();
        }catch(SQLException e) { e.printStackTrace(); }
        assertArrayEquals(new int[]{1001, 1006, 1011, 1016, 1021}, actual);
        conn.close();
    }

    @Test
    void getStatement() {
        int actual = (int) conn.simpleQuery("SELECT COUNT(*) FROM color WHERE nombre = 'marron';");
        assertEquals(0, actual);
        String sql = "INSERT INTO color(nombre) VALUES('marron')";
        try{ conn.getStatement(sql).execute();
        }catch (SQLException throwables) { throwables.printStackTrace(); }
        actual = (int) conn.simpleQuery("SELECT COUNT(*) FROM color WHERE nombre = 'marron';");
        assertEquals(1, actual);
    }

    @Test
    void close() {
        assertNotNull(conn);
        assertFalse(conn.connIsClosed());
        conn.close();
        assertTrue(conn.connIsClosed());
    }
}