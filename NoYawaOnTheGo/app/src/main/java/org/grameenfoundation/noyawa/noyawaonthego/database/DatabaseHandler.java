package org.grameenfoundation.noyawa.noyawaonthego.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.model.UsageTracking;
import org.grameenfoundation.noyawa.noyawaonthego.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mac on 1/25/16.
 */
public class DatabaseHandler {


    Context context;
DatabaseHelper mDbHelper = new DatabaseHelper(context);
private SQLiteDatabase database;

public DatabaseHandler(Context context) {
        mDbHelper = new DatabaseHelper(context);

        }



public void insertUserTable(String volunteer_name,String username,String password,String chps_zone,String community_resident){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataClass.NoyawaDatabase.COL_LOGIN_NAME_OF_USER,volunteer_name);
        values.put(DataClass.NoyawaDatabase.COL_USERNAME,username);
        values.put(DataClass.NoyawaDatabase.COL_PASSWORD,password);

        long newRowId;
        newRowId = db.insert(
        DataClass.NoyawaDatabase.LOGIN_TABLE_NAME, null, values);
        }

public void insertLoginActivity(String date,String time,String username,String status,String update_status){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataClass.NoyawaDatabase.COL_DATE_LOGGED_IN,date);
        values.put(DataClass.NoyawaDatabase.COL_TIME_LOGGED_IN,time);
        values.put(DataClass.NoyawaDatabase.COL_USERNAME,username);
        values.put(DataClass.NoyawaDatabase.COL_LOGIN_STATUS,status);
        values.put(DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS,update_status);

        long newRowId;
        newRowId = db.insert(
        DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME, null, values);
        }

public void insertUsageActivity(String username,String module,String submodule, String type,String action_date,String duration,String duration_played,String status,String extras,String update_status){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataClass.NoyawaDatabase.COL_USERNAME_USAGE_TRACKING,username);
        values.put(DataClass.NoyawaDatabase.COL_MODULE,module);
        values.put(DataClass.NoyawaDatabase.COL_SUBMODULE,submodule);
        values.put(DataClass.NoyawaDatabase.COL_TYPE,type);
        values.put(DataClass.NoyawaDatabase.COL_ACTION_DATE,action_date);
        values.put(DataClass.NoyawaDatabase.COL_DURATION,duration);
        values.put(DataClass.NoyawaDatabase.COL_DURATION_PLAYED,duration_played);
        values.put(DataClass.NoyawaDatabase.COL_STATUS,status);
        values.put(DataClass.NoyawaDatabase.COL_EXTRAS, extras);
        values.put(DataClass.NoyawaDatabase.COL_UPDATE_STATUS_TRACKING, update_status);


        long newRowId;
        newRowId = db.insert(
        DataClass.NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME, null, values);
        }

public ArrayList<String> verifyLogin(String username, String password){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<String> list=new ArrayList<String>();
        String strQuery="select "+DataClass.NoyawaDatabase._ID
        +","+DataClass.NoyawaDatabase.COL_USERNAME
        +"," +DataClass.NoyawaDatabase.COL_PASSWORD
        +","+DataClass.NoyawaDatabase.COL_LOGIN_NAME_OF_USER
        +" from "+DataClass.NoyawaDatabase.LOGIN_TABLE_NAME
        +" where "+DataClass.NoyawaDatabase.COL_USERNAME+" = \""+username+"\""
        + " and "+DataClass.NoyawaDatabase.COL_PASSWORD+ " = \""+password+"\"";

        System.out.println(strQuery);
        Cursor c=db.rawQuery(strQuery, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
        list.add(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_USERNAME)));
        list.add(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_PASSWORD)));
        list.add(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_LOGIN_NAME_OF_USER)));
        c.moveToNext();
        }
        c.close();
        return list;
        }



public int getAllLoginActivity(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count=0;
        String strQuery="select * "
        +" from "+DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME
        +" where "+ DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS
        +" = '"+"new_record"+"'";
        System.out.println(strQuery);
        Cursor c=db.rawQuery(strQuery, null);
        count=c.getCount();
        c.close();
        return count;

        }
public ArrayList<User> getLoginActivity(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<User> list=new ArrayList<User>();
        String strQuery="select * "
        +" from "+DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME
        +" where "+ DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS
        +" = '"+ Noyawa.SYNC_STATUS_NEW+"'";
        System.out.println(strQuery);
        Cursor c=db.rawQuery(strQuery, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){

        User u = new User();
        u.setUserId(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase._ID)));
        u.setUsername(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_USERNAME_LOGIN_ACTIVITY)));
        u.setLoginDate(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_DATE_LOGGED_IN)));
        u.setLoginTime(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_TIME_LOGGED_IN)));
        u.setStatus(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_STATUS)));
        list.add(u);

        c.moveToNext();
        }
        c.close();
        return list;

        }
public ArrayList<UsageTracking> getUsageActivity(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<UsageTracking> list=new ArrayList<UsageTracking>();
        String strQuery="select *"
        +" from "+DataClass.NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME
        +" where "+ DataClass.NoyawaDatabase.COL_UPDATE_STATUS_TRACKING
        +" = '"+Noyawa.SYNC_STATUS_NEW+"'";
        System.out.println(strQuery);
        Cursor c=db.rawQuery(strQuery, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
        UsageTracking usage = new UsageTracking();
        usage.setUsageId(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase._ID)));
        usage.setUsername(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_USERNAME_USAGE_TRACKING)));
        usage.setDuration(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_DURATION)));
        usage.setDurationPlayed(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_DURATION_PLAYED)));
        usage.setActionDate(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_ACTION_DATE)));
        usage.setExtras(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_EXTRAS)));
        usage.setModule(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_MODULE)));
        usage.setSubModule(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_SUBMODULE)));
        usage.setType(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_TYPE)));
        usage.setStatus(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_STATUS)));
        list.add(usage);
        c.moveToNext();
        }
        c.close();
        return list;

        }


public int getRowLoginCount() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DataClass.NoyawaDatabase.LOGIN_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
        }
public int getRowLoginActivityCount() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
        }

public int getRowUsageActivityCount() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DataClass.NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
        }

public int LoginActivitySyncCount(){
        int count = 0;
        String selectQuery = "SELECT  * FROM "+ DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME+" where "
        +DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS+ " = '"+"new_record"+"'";
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        count = cursor.getCount();
        database.close();
        return count;
        }

public void updateLoginActivitySyncStatus(String status){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        String updateQuery = "Update "+DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME+ " set "+
        DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS+" = '"+ status +"'";
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
        }


public void updateUsageActivitySyncStatus(String status,int id){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        String updateQuery = "Update "+DataClass.NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME+ " set "+
        DataClass.NoyawaDatabase.COL_UPDATE_STATUS_TRACKING+" = '"+ status +"'"+
        " where "+ DataClass.NoyawaDatabase._ID+" = "+id;
        Log.d("query",updateQuery);
        database.execSQL(updateQuery);
        database.close();
        }


public ArrayList<User> getLoginActivityUpdatedStatus(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<User> list=new ArrayList<User>();
        String selectQuery = "SELECT  "+	DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS+
        " FROM "+ DataClass.NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME+" where "
        +DataClass.NoyawaDatabase.COL_LOGIN_UPDATE_STATUS+ " = '"+Noyawa.SYNC_STATUS_NEW+"'";
        Cursor c=db.rawQuery(selectQuery, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
        User u = new User();
        u.setUserId(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase._ID)));
        u.setLoginDate(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_DATE_LOGGED_IN)));
        u.setLoginTime(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_TIME_LOGGED_IN)));
        u.setStatus(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_STATUS)));
        u.setUsername(c.getString(c.getColumnIndex(DataClass.NoyawaDatabase.COL_USERNAME_LOGIN_ACTIVITY)));
        list.add(u);

        c.moveToNext();
        }
        c.close();
        return list;

        }


public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
        "dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
        }
        }
