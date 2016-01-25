package org.grameenfoundation.noyawa.noyawaonthego.database;

import android.provider.BaseColumns;

/**
 * Created by mac on 1/25/16.
 */
public class DataClass {


    public DataClass(){

    }

    public static abstract class NoyawaDatabase implements BaseColumns {
        //table names
        public static final String LOGIN_TABLE_NAME="user_table";
        public static final String LOGIN_ACTIVITY_TABLE_NAME="login_activity";
        public static final String USAGE_ACTIVITY_TABLE_NAME="usage_tracking";



        //column names for login table
        public static final String COL_LOGIN_NAME_OF_USER="full_name";
        public static final String COL_USERNAME="username";
        public static final String COL_PASSWORD="password";


        //column names for login activity table
        public static final String 	COL_DATE_LOGGED_IN="date_logged_in";
        public static final String 	COL_TIME_LOGGED_IN="time_logged_in";
        public static final String 	COL_USERNAME_LOGIN_ACTIVITY="username";
        public static final String COL_LOGIN_STATUS="status";
        public static final String COL_LOGIN_UPDATE_STATUS="update_status";

        //column names for usage tracking table
        public static final String COL_USERNAME_USAGE_TRACKING="user_name";
        public static final String COL_MODULE="module";
        public static final String COL_SUBMODULE="sub_module";
        public static final String COL_TYPE="type";
        public static final String COL_ACTION_DATE="action_date";
        public static final String COL_UPDATE_STATUS_TRACKING="update_status";
        //public static final String COL_STOP_TIME="stop_time";
        public static final String COL_DURATION="duration";
        public static final String COL_DURATION_PLAYED="duration_played";
        public static final String COL_STATUS="status";
        public static final String COL_EXTRAS="extras";


    }

    //table create statement  for volunteer info table
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";



    //	table create statement for login table
    public static final String LOGIN_CREATE_TABLE =
            "CREATE TABLE " + NoyawaDatabase.LOGIN_TABLE_NAME + " (" +
                    NoyawaDatabase._ID + " INTEGER PRIMARY KEY," +
                    NoyawaDatabase.COL_LOGIN_NAME_OF_USER + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_USERNAME + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_PASSWORD+ TEXT_TYPE +
                    " )";

    //	table create statement for login activity table
    public static final String LOGIN_ACTIVITY_CREATE_TABLE =
            "CREATE TABLE " + NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME + " (" +
                    NoyawaDatabase._ID + " INTEGER PRIMARY KEY," +
                    NoyawaDatabase.COL_DATE_LOGGED_IN + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_TIME_LOGGED_IN + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_USERNAME_LOGIN_ACTIVITY+ TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_LOGIN_STATUS+ TEXT_TYPE+ COMMA_SEP +
                    NoyawaDatabase.COL_LOGIN_UPDATE_STATUS+ TEXT_TYPE+
                    " )";

    //	table create statement for login activity table
    public static final String USAGE_ACTIVITY_CREATE_TABLE=
            "CREATE TABLE " + NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME + " (" +
                    NoyawaDatabase._ID + " INTEGER PRIMARY KEY," +
                    NoyawaDatabase.COL_USERNAME_USAGE_TRACKING + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_MODULE + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_SUBMODULE + TEXT_TYPE + COMMA_SEP +
                    NoyawaDatabase.COL_TYPE+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_ACTION_DATE+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_DURATION+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_DURATION_PLAYED+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_STATUS+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_EXTRAS+ TEXT_TYPE+	COMMA_SEP+
                    NoyawaDatabase.COL_UPDATE_STATUS_TRACKING+ TEXT_TYPE+
                    " )";


    public static final String LOGIN_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + NoyawaDatabase.LOGIN_TABLE_NAME;

    public static final String LOGIN_ACTIVITY_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + NoyawaDatabase.LOGIN_ACTIVITY_TABLE_NAME;

    public static final String USAGE_ACTIVITY_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + NoyawaDatabase.USAGE_ACTIVITY_TABLE_NAME;
}
