package jlite.model;

import jlite.JliteConnection;

public class ArticuloModelTest extends Model implements ModelWithCrud {

    public ArticuloModelTest(String schema, JliteConnection conn) {
        super(schema, conn);
    }

    @Override
    public Crud getCrudModel() {
        return getCrud();
    }

    @Override
    public boolean finalCheckToInsert(JliteConnection conn) {
        return false;
    }

    @Override
    public boolean finalCheckToUpdate(JliteConnection conn) {
        return false;
    }
}
