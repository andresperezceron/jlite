package jlite.model;

public class QueryBuilder {
    private final String schema;
    private final String[] columnNames;

    public QueryBuilder(String schema, String[] columnNames) {
        this.schema = schema;
        this.columnNames = columnNames;
    }

    public String createQueryInsert() {
        StringBuilder sql1 = new StringBuilder("insert into " + schema + "(");
        StringBuilder sql2 = new StringBuilder("values(");
        for(int i=1; i<columnNames.length; i++) {
            sql1.append((i + 1 == columnNames.length) ? columnNames[i] + ")" : columnNames[i] + ",");
            sql2.append((i + 1 == columnNames.length) ? "?);" : "?,");
        }
        return sql1 + sql2.toString();
    }

    public String createQueryUpdate() {
        StringBuilder sql = new StringBuilder("update " + schema + " set ");
        for(int i=1; i<columnNames.length; i++)
            sql.append((i + 1 == columnNames.length) ? columnNames[i] + "=? where id=?;" : columnNames[i] + "=?,");
        return sql.toString();
    }

    public String createQueryDelete() {
        return "delete from " + schema + " where id=";
    }
}