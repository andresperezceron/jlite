package jlite.model;

public class QueryBuilder {
    private final String schema;
    private final String[] columNames;

    public QueryBuilder(String schema, String[] columNames) {
        this.schema = schema;
        this.columNames = columNames;
    }

    public String createQueryInsert() {
        StringBuilder sql1 = new StringBuilder("insert into " + schema + "(");
        StringBuilder sql2 = new StringBuilder("values(");
        for(int i=1; i<columNames.length; i++) {
            sql1.append((i + 1 == columNames.length) ? columNames[i] + ")" : columNames[i] + ",");
            sql2.append((i + 1 == columNames.length) ? "?);" : "?,");
        }
        return sql1 + sql2.toString();
    }

    public String createQueryUpdate() {
        StringBuilder sql = new StringBuilder("update " + schema + " set ");
        for(int i=1; i<columNames.length; i++)
            sql.append((i + 1 == columNames.length) ? columNames[i] + "=? where id=?;" : columNames[i] + "=?,");
        return sql.toString();
    }

    public String createQueryDelete() {
        return "delete from " + schema + " where id=";
    }
}