package jlite.model;

import jlite.JliteConnection;

public class MetaData {
    private final String[] columnNames;
    private final String[] columnTypes;

    public MetaData(String schema, JliteConnection conn) {
        String query = "SELECT sql FROM sqlite_master WHERE name = '" + schema + "';";
        String master = conn.simpleQuery(query).toString();
        master = master.replaceAll("\\(", " ")
                .replaceAll("\\)", " ")
                .replaceAll(",", " ")
                .replaceAll("\n", "")
                .replaceAll("\r", "");
        String[] esqSplit = master.split(" ");

        int totalCampos = 0;
        for(String anEsqSplit : esqSplit)
            if(anEsqSplit.equals("INTEGER") || anEsqSplit.equals("TEXT") || anEsqSplit.equals("REAL"))
                totalCampos++;

        columnNames = new String[totalCampos];
        columnTypes = new String[totalCampos];

        for(int i=0, j=0, k=0; i<esqSplit.length; i++)
            if(esqSplit[i].equals("INTEGER") || esqSplit[i].equals("TEXT") || esqSplit[i].equals("REAL")) {
                columnTypes[k++] = esqSplit[i];
                columnNames[j++] = esqSplit[i-1];
            }
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String[] getColumnTypes() {
        return columnTypes;
    }

    public int getColumnCount() {
        return columnNames.length;
    }
}