package jlite.model;

import jlite.JliteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud {
    private final String schema;
    private final Object[] row;
    private final MetaData metaData;
    private final QueryBuilder queryBuilder;
    private final int columnCount;

    public Crud(String schema, JliteConnection conn) {
        this.schema = schema;
        metaData = new MetaData(schema, conn);
        queryBuilder = new QueryBuilder(schema, metaData.getColumnNames());
        columnCount = metaData.getColumnCount();
        row = new Object[columnCount];
    }

    public void set(int columnIndex, Object value) {
        row[columnIndex] = value;
    }

    public void set(String columName, Object value) {
        int index = -1;
        String[] columNames = metaData.getColumnNames();
        for(int i = 0; i < columnCount; i++)
            if(columNames[i].equals(columName))
                index = i;
        row[index] = value;
    }

    public Object get(int indexColum) {
        return row[indexColum];
    }

    public Object get(String nombreColum) {
        int index = -1;
        for(int i=0; i < columnCount; i++)
            if(metaData.getColumnNames()[i].equals(nombreColum))
                index = i;
        return row[index];
    }

    public void load(int id, JliteConnection conn) {
        try{
            ResultSet rs = conn.getResultSet("SELECT * FROM " + schema + " WHERE id = " + id);
            for(int i=0; i < columnCount; i++)
                row[i] = rs.getObject(i+1);
            rs.close();
        }catch(SQLException e) { e.printStackTrace(); }
    }

    public void insert(JliteConnection conn) {
        try{
            PreparedStatement statement = getStatementToInsertOrUpate(true, conn);
            statement.execute();
            statement.close();
        }catch(Exception e) { e.printStackTrace(); }
    }

    public void update(JliteConnection conn) {
        try {
            PreparedStatement statement = getStatementToInsertOrUpate(false, conn);
            statement.execute();
            statement.close();
        }catch(Exception e) { e.printStackTrace(); }
    }

    public void delete(JliteConnection conn) {
        try {
            PreparedStatement statement = conn.getStatement(queryBuilder.createQueryDelete() + get("id"));
            statement.execute();
            statement.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private PreparedStatement getStatementToInsertOrUpate(boolean isInsert, JliteConnection conn) throws SQLException {
        PreparedStatement statement = isInsert
                ? conn.getStatement(queryBuilder.createQueryInsert())
                : conn.getStatement(queryBuilder.createQueryUpdate());
        for(int i=1; i < columnCount; i++)
            switch(metaData.getColumnTypes()[i]) {
            case "INTEGER" : statement.setInt(i, (int) row[i]); break;
            case "TEXT" : statement.setString(i, (String) row[i]); break;
            case "REAL" : statement.setDouble(i, (double) row[i]);
        }
        if(!isInsert) statement.setInt(columnCount, (int) row[0]);
        return statement;
    }
}