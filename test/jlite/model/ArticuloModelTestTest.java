package jlite.model;

import jlite.JliteConfig;
import jlite.JliteConnection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticuloModelTestTest {
    private final JliteConnection conn;
    private final ArticuloModelTest articuloModel;

    ArticuloModelTestTest() {
        JliteConfig.setPathDb("./testres/db/test.db");
        conn = new JliteConnection();
        articuloModel = new ArticuloModelTest("articulo", conn);
    }

    @Test
    void getCrudModel() {
        assertNotNull(articuloModel.getCrudModel());
        conn.close();
    }

    @Test
    void finalCheckToInsert() {

    }

    @Test
    void finalCheckToUpdate() {
    }
}