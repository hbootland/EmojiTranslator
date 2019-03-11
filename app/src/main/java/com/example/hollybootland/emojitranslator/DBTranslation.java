package com.example.hollybootland.emojitranslator;

import android.provider.BaseColumns;

public class DBTranslation {

    private DBTranslation(){

    }

    public  class Translation implements BaseColumns {
        private static final String TABLE_NAME = "translation";
        private static final String COL1 = "InLang";
        private static final String COL2 = "InText";
        private static final String COL3 = "OutLang";
        private static final String COL4 = "OutText";


        private static final String createTable = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT" +
                COL4 + "TEXT" + ")";
    }
}
