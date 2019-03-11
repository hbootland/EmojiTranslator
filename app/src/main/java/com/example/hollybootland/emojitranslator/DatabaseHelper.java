package com.example.hollybootland.emojitranslator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.concurrent.atomic.AtomicReference;

import static android.os.Build.ID;


public final class DatabaseHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "translation_database";
    private static final String TAG = "DatabaseHelper";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(DatabaseHelper.Translation.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

//        db.execSQL("DROP TABLE IF EXISTS " + DBTranslation.Translation.CREATE_TABLE);
        onCreate(db);
    }


    private void saveToDB() {
        SQLiteDatabase database =  this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        Translations trans = new Translations();
//        values.put(DBTranslation.Translation.COL1, Translation.getInLang());
//        values.put(DBTranslation.Translation.COL2, trans.getInText());
//        values.put(DBTranslation.Translation.COL3, trans.getOutLang());
//        values.put(DBTranslation.Translation.COL4, trans.getOutText());


    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(col2, item);
//        Log.d(TAG , "addData: Adding " + item + " to " + TABLE_NAME);
//        long result = db.insert(TABLE_NAME,null, contentValues);

//        // if inserted incorrectly returns -1
//        if (result == -1){
//            return false;
//        }else{
            return true;
//        }
    }
}
