/**
 * 
 */
package com.BlitzBomb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "login.db";
	private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "loginevent";
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
        "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
        "user_id TEXT NOT NULL,name TEXT NOT NULL,password TEXT NOT NULL,email TEXT NOT NULL);";
	
	            
	//private static final String DB = "INSERT INTO "+TABLE_NAME+" values(1,first_name,last_name,password,email,confirmed_password);";
	private static final String DB = "INSERT INTO "+TABLE_NAME+" values(1,user_id,name,password,email);";
	private static final String DB1 = "DROP TABLE "+TABLE_NAME+");";
	
	public DbHelper(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
@Override
	public void onCreate(SQLiteDatabase db) {
		
		try{
			//Create Database
			db.execSQL(TABLE_CREATE);
			db.execSQL(DB);
			db.execSQL(DB1);
			}catch(Exception e){
			e.printStackTrace();
		}
	}
  @Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		

	}
  
 }
