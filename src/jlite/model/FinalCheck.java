package jlite.model;

import jlite.JliteConnection;

public interface FinalCheck {
    boolean finalCheckToInsert(JliteConnection conn);
    boolean finalCheckToUpdate(JliteConnection conn);
}
