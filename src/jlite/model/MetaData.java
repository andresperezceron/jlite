package jlite.model;

import jlite.connection.JliteConnection;

public class MetaData {
    private final String[] columNames;
    private final String[] columTypes;

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

        columNames = new String[totalCampos];
        columTypes = new String[totalCampos];

        for(int i=0, j=0, k=0; i<esqSplit.length; i++)
            if(esqSplit[i].equals("INTEGER") || esqSplit[i].equals("TEXT") || esqSplit[i].equals("REAL")) {
                columTypes[k++] = esqSplit[i];
                columNames[j++] = esqSplit[i-1];
            }
    }

    public String[] getColumNames() {
        return columNames;
    }

    public String[] getColumTypes() {
        return columTypes;
    }

    public int getColumCount() {
        return columNames.length;
    }
}