package jlite.model;

import jlite.JliteConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private final Crud crud;
    private final String schema;

    public Model(String schema, JliteConnection conn) {
        this.schema = schema;
        crud = new Crud(schema, conn);
    }

    protected Crud getCrud() {
        return crud;
    }

    protected int getIdBy(String strUniqueKey, Object value, JliteConnection conn) {
        String query = "SELECT id FROM " + schema + " WHERE " + strUniqueKey + "='" + value + "'";
        return (int) conn.simpleQuery(query);
    }

    protected boolean exist(String strPriOrUniKey, Object value, JliteConnection conn) {
        String query = "Select count(*) from " + schema + " where " + strPriOrUniKey + "='" + value + "';";
        return (int) conn.simpleQuery(query) > 0;
    }

    protected Object[] getBy(String strPriOrUniKey, Object value, JliteConnection conn) {
        try{
            String query = "select * from " + schema + " where " + strPriOrUniKey + "='" + value + "'";
            ResultSet resultSet = conn.getResultSet(query);
            resultSet.next();
            Object[] reg = new Object[resultSet.getMetaData().getColumnCount()];
            for(int i=0; i<reg.length; i++)
                reg[i] = resultSet.getObject(i+1);
            resultSet.close();
            return reg;
        }catch(SQLException e) { return null; }
    }
}