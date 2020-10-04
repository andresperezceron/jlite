package jlite.model;

import jlite.JliteConfig;
import jlite.connection.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private final JliteConnection conn;

    QueryBuilderTest() {
        JliteConfig.setPathDb(getClass().getResource("/db/test.db").toString());
        conn = new JliteConnection();
    }

    @Test
    void createQueryInsert() {
        MetaData meta = new MetaData("color", conn);
        QueryBuilder query = new QueryBuilder("color", meta.getColumNames());
        String expected = "insert into color(nombre)values(?);";
        assertEquals(expected, query.createQueryInsert());

        meta = new MetaData("articulo", conn);
        query = new QueryBuilder("articulo", meta.getColumNames());
        expected = "insert into articulo(id_color,id_forma,codigo,descripcion)values(?,?,?,?);";
        assertEquals(expected, query.createQueryInsert());
    }

    @Test
    void createQueryUpdate() {
        MetaData meta = new MetaData("forma", conn);
        QueryBuilder query = new QueryBuilder("forma", meta.getColumNames());
        String expected = "update forma set nombre=? where id=?;";
        assertEquals(expected, query.createQueryUpdate());

        meta = new MetaData("articulo", conn);
        query = new QueryBuilder("articulo", meta.getColumNames());
        expected = "update articulo set id_color=?,id_forma=?,codigo=?,descripcion=? where id=?;";
        assertEquals(expected, query.createQueryUpdate());
    }

    @Test
    void createQueryDelete() {
        MetaData meta = new MetaData("articulo", conn);
        QueryBuilder query = new QueryBuilder("articulo", meta.getColumNames());
        String expected = "delete from articulo where id=";
        assertEquals(expected, query.createQueryDelete());
    }
}