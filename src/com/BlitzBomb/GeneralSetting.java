package com.BlitzBomb;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class GeneralSetting extends Activity{
	public static String ID = "user_id";
	public static String EMAIL = "email";
	public static String NAME = "name";
	private DbHelper myDb;
	String User_id,user_Email,user_Name;
	EditText Email,Name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_setting);
		Email=(EditText)findViewById(R.id.et_GeneralSetting_selected);
		Name=(EditText)findViewById(R.id.et_GeneralSetting_UserName_selected);
		myDb = new DbHelper(this);
		GetEmail();
		Email.setText(user_Email);
		 int i=Email.getText().length();
		  Email.setSelection(i);
		Name.setText(user_Name);
		 int j=Name.getText().length();
		  Name.setSelection(j);
	}
	/*Set data from database by select query*/
	public void GetEmail() {

	  SQLiteDatabase db = myDb.getReadableDatabase();
	 try{
		 
	      Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ID,EMAIL,NAME}, null,null, null, null, null);		
			if(cursor.getCount()>0){
				cursor.moveToFirst();           
				do{
				                                                 
					 User_id=cursor.getString(0);                                                                                                 
					 user_Email=cursor.getString(1);
					 user_Name=cursor.getString(2);
				} while (cursor.moveToNext());
				cursor.close();
				db.close();               
		        }                                                                                           
		}catch(Exception e){
			e.printStackTrace();                                      
		}   
		onDestroy();
	  }
	//Close database class.                                               
	public void onDestroy(){
		super.onDestroy();
		
		myDb.close();
	}	
}
