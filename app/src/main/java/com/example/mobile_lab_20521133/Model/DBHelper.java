package com.example.mobile_lab_20521133.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "lab3database";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "person";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String PHONE_COL = "phone";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_COL + " TEXT)";


        db.execSQL(query);
    }


    public boolean addNewPerson(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, name);
        values.put(PHONE_COL, phone);

        long result = db.insert(TABLE_NAME, null, values);

        db.close();

        return result > 0;
    }
    public ArrayList<Person> showPerson(){
        ArrayList<Person>  studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Person student = new Person(cursor.getString(1), cursor.getString(2));
            studentList.add(student);
            cursor.moveToNext();
        }

        return studentList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
