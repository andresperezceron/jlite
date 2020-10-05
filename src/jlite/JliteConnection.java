package jlite;

import java.sql.*;

public class JliteConnection {
    private Connection connection;

    public JliteConnection() {
        try{ connection = DriverManager.getConnection(JliteConfig.getPathDb());
        }catch(SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public Object simpleQuery(String query) {
        try{ return connection.prepareStatement(query).executeQuery().getObject(1); }
        catch(SQLException e) { return null; }
    }

    public ResultSet getResultSet(String query) {
        try{ return connection.prepareStatement(query).executeQuery(); }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement getStatement(String query) {
        try{ return connection.prepareStatement(query); }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try { connection.close();
        }catch(SQLException e) { e.printStackTrace(); }
    }

    public boolean connIsClosed() {
        try{ return connection.isClosed();
        }catch(SQLException throwables) { throwables.printStackTrace(); }
        return true;
    }
}