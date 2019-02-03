package com.iti.mad.firstlab.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayman on 2019-01-29.
 */

public class DatabaseAdapter {


    Context context;
    DataBaseHelper dataBaseHelper;
    public DatabaseAdapter( Context context) {
        this.context=context;
        dataBaseHelper =new  DataBaseHelper( context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mobile.db";
    public static final String TABLE_NAME = "mobile";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_MESSAGE = "message";


    public void createRow(int id , String mobile , String msg){

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_MESSAGE, msg);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public String  RetrieveRow(){
        String result = "";
        String query = "Select mobile , message FROM " + TABLE_NAME;
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            result = result_0 + " " + result_1;// + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    class DataBaseHelper extends SQLiteOpenHelper {

        public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                    " INTEGER PRIMARYKEY , " + COLUMN_MOBILE + " TEXT , "+COLUMN_MESSAGE + " TEXT )";
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }
        public class Mobile {
            private String mobile;
            private String message;

            public Mobile() {
            }

            public Mobile(String mobile, String message) {
                this.mobile = mobile;
                this.message = message;
            }

            public String getMobile() {
                return mobile;
            }

            public String getMessage() {
                return message;
            }
        }
    }

