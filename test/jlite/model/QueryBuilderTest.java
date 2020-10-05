package jlite.model;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {
    private final JliteConnection conn;

    QueryBuilderTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
    }

    @Test
    void createQueryInsert() {
        MetaData meta = new MetaData("color", conn);
        QueryBuilder query = new QueryBuilder("color", meta.getColumnNames());
        String expected = "insert into color(nombre)values(?);";
        assertEquals(expected, query.createQueryInsert());

        meta = new MetaData("articulo", conn);
        query = new QueryBuilder("articulo", meta.getColumnNames());
        expected = "insert into articulo(id_color,id_forma,codigo,descripcion)values(?,?,?,?);";
        assertEquals(expected, query.createQueryInsert());
    }

    @Test
    void createQueryUpdate() {
        MetaData meta = new MetaData("forma", conn);
        QueryBuilder query = new QueryBuilder("forma", meta.getColumnNames());
        String expected = "update forma set nombre=? where id=?;";
        assertEquals(expected, query.createQueryUpdate());

        meta = new MetaData("articulo", conn);
        query = new QueryBuilder("articulo", meta.getColumnNames());
        expected = "update articulo set id_color=?,id_forma=?,codigo=?,descripcion=? where id=?;";
        assertEquals(expected, query.createQueryUpdate());
    }

    @Test
    void createQueryDelete() {
        MetaData meta = new MetaData("articulo", conn);
        QueryBuilder query = new QueryBuilder("articulo", meta.getColumnNames());
        String expected = "delete from articulo where id=";
        assertEquals(expected, query.createQueryDelete());
    }
}