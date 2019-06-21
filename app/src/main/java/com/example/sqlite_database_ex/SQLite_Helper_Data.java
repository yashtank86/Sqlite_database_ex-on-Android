package com.example.sqlite_database_ex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLite_Helper_Data {

    Make_database make_database;

    SQLite_Helper_Data(Context context) {

        make_database = new Make_database(context);
    }

    public long insert_data(String u_name, String u_pass) {

        SQLiteDatabase sqlite_database = make_database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Make_database.U_NAME, u_name);
        contentValues.put(Make_database.U_PASSWORD, u_pass);
        long id = sqlite_database.insert(Make_database.TABLE_NAME, null, contentValues);
        return id;
    }

    public String readAllDAta() {
        SQLiteDatabase sqlite_database = make_database.getWritableDatabase();
        String[] column = {Make_database.UID, Make_database.U_NAME, Make_database.U_PASSWORD};
        Cursor cursor = sqlite_database.query(Make_database.TABLE_NAME, column, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            String user_name = cursor.getString(1);
            String user_pass = cursor.getString(2);
            stringBuffer.append(uid).append(" ").append(user_name).append(" ").append(user_pass).append("\n");
        }
        return stringBuffer.toString();
    }

    public String readSearchDAta(String search_name, String search_pass) {
        SQLiteDatabase sqlite_database = make_database.getWritableDatabase();
        String[] column = {Make_database.UID};
        String[] selection_args = {search_name, search_pass};
        Cursor cursor = sqlite_database.query(Make_database.TABLE_NAME, column, Make_database.U_NAME + " = ? AND " + Make_database.U_PASSWORD + " = ?", selection_args, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(Make_database.UID);
            String uid = cursor.getString(index1);
            stringBuffer.append(uid + "\n");

           /*int index1= cursor.getColumnIndex(Make_database.U_NAME);
           int index2= cursor.getColumnIndex(Make_database.U_PASSWORD);
            String user_name = cursor.getString(1);
            String user_pass = cursor.getString(2);
            stringBuffer.append(user_name + " " + user_pass + "\n");*/
        }
        return stringBuffer.toString();
    }

    public int data_update(String old_name, String updated_name) {
        SQLiteDatabase sqlite_database = make_database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Make_database.U_NAME, updated_name);
        String[] whereArgs = {old_name};
        int count = sqlite_database.update(Make_database.TABLE_NAME, contentValues, Make_database.U_NAME + " =? ", whereArgs);
        return count;
    }

    public int data_delete() {
        SQLiteDatabase sqlite_database = make_database.getWritableDatabase();
        String[] whereArgs = {"yash"};
        int count = sqlite_database.delete(Make_database.TABLE_NAME, Make_database.U_NAME + " =? ", whereArgs);
        return count;
    }

    class Make_database extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "DataBase_One";
        private static final String TABLE_NAME = "Insert_UN_nd_UP";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_UID";
        private static final String U_NAME = "_UNAME";
        private static final String U_PASSWORD = "_UPassword";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + U_NAME + " VARCHAR(255)," + U_PASSWORD + " VARCHAR(255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private final Context context;

        Make_database(Context context) {

            super(context, TABLE_NAME, null, DATABASE_VERSION);

            this.context = context;
            Message.message(context, "constructor_called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "onCreate_called");
            } catch (SQLException e) {
                Message.message(context, "error on table_creation" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Message.message(context, "onUpgrade_called");
            } catch (SQLException e) {
                Message.message(context, "Problem on drop table" + e);
            }
        }
    }
}
