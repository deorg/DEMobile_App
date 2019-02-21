package com.domestic.dmobile.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.domestic.dmobile.dbms.model.User;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBDMobile";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("ake", "onCreate DB");
        String CREATE_TABLE_USER = String.format("CREATE TABLE %s (" +
                        "%s INTEGER," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INTEGER)",
                User.Model.TABLE_NAME,
                User.Model.COLUMN_CUST_NO,
                User.Model.COLUMN_CUST_NAME,
                User.Model.COLUMN_CITIZEN_NO,
                User.Model.COLUMN_PHONE_NO,
                User.Model.COLUMN_DEVICEID,
                User.Model.COLUMN_PINCODE);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("ake", "onUpgrade DB");
        String DROP_USER_TABLE = String.format("DROP TABLE IF EXISTS %s", User.Model.TABLE_NAME);
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }


    //region #----- Query ----------
    public boolean isRegister() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user", null);
        if (cursor.getCount() <= 0) {
            Log.v("ake", "No Data");
            cursor.close();
            return false;
        }

        Log.v("ake", "isRegister");
        cursor.close();
        return true;
    }

    public User getUser() {
        final User user = new User();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user", null);

        if (cursor != null) cursor.moveToFirst();
        user.setCustNo(cursor.getInt(cursor.getColumnIndex(User.Model.COLUMN_CUST_NO)));
        user.setCustName(cursor.getString(cursor.getColumnIndex(User.Model.COLUMN_CUST_NAME)));
        user.setCitizenNo(cursor.getString(cursor.getColumnIndex(User.Model.COLUMN_CITIZEN_NO)));
        user.setPhoneNo(cursor.getString(cursor.getColumnIndex(User.Model.COLUMN_PHONE_NO)));
        user.setDeviceId(cursor.getString(cursor.getColumnIndex(User.Model.COLUMN_DEVICEID)));
        user.setPinCode(cursor.getString(cursor.getColumnIndex(User.Model.COLUMN_PINCODE)));
        cursor.moveToNext();
        sqLiteDatabase.close();

        return user;
    }
    //endregion




    //region #----- Create/ Insert/ Delete/ Update ----------
    public void addCustomer(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.Model.COLUMN_CUST_NO, user.getCustNo());
        values.put(User.Model.COLUMN_CUST_NAME, user.getCustName());
        values.put(User.Model.COLUMN_CITIZEN_NO, user.getCitizenNo());
        values.put(User.Model.COLUMN_PHONE_NO, user.getPhoneNo());
        values.put(User.Model.COLUMN_DEVICEID, user.getDeviceId());
        values.put(User.Model.COLUMN_PINCODE, user.getPinCode());
        long i = sqLiteDatabase.insert(User.Model.TABLE_NAME, null, values);
        sqLiteDatabase.close();

        Log.v("ake", "addCustomer : " + i);
    }

    public void resetAll() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + User.Model.TABLE_NAME);
    }
    //endregion
}
