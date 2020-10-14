package jlite.model;

import jlite.JliteConnection;

public interface ModelWithCrud {
    Crud getCrudModel();
    boolean finalCheckToInsert(JliteConnection conn);
    boolean finalCheckToUpdate(JliteConnection conn);
}
