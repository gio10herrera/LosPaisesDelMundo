package com.example.gio10.lospaisesdelmundo.model;

import android.provider.BaseColumns;

public final class DaoPais {

    private DaoPais(){

    }

    public static class PaisEntry implements BaseColumns {
        public static final String TABLE_NAME = "Pais";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_CAPITAL = "capital";
        public static final String COLUMN_NAME_NHABITANTES = "habitantes";
        public static final String COLUMN_NAME_CONTINENTE = "continente";
        public static final String COLUMN_NAME_IDIOMA = "idioma";
        public static final String COLUMN_NAME_MONEDA = "moneda";
        public static final String COLUMN_NAME_TGOBIERNO = "gobierno";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PaisEntry.TABLE_NAME + " (" +
                    PaisEntry._ID + " INTEGER PRIMARY KEY," +
                    PaisEntry.COLUMN_NAME_NOMBRE + " TEXT," +
                    PaisEntry.COLUMN_NAME_CAPITAL + " TEXT," +
                    PaisEntry.COLUMN_NAME_NHABITANTES + " INTEGER," +
                    PaisEntry.COLUMN_NAME_CONTINENTE + " TEXT," +
                    PaisEntry.COLUMN_NAME_IDIOMA + " TEXT," +
                    PaisEntry.COLUMN_NAME_MONEDA + " TEXT," +
                    PaisEntry.COLUMN_NAME_TGOBIERNO + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PaisEntry.TABLE_NAME;
}

