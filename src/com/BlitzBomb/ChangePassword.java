package com.BlitzBomb;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity  implements OnClickListener{
	Button change; 
	EditText newpass,oldpass,confirmpass;
	String Newpass,Oldpass,Confirmpass,password,User_id;
	InputMethodManager inputMgr; 
	public static String ID = "user_id";
	public static String PASSWORD = "password";
	private SQLiteDatabase newDB;
	private DbHelper myDb;
	private boolean invalid=false;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    /*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.change_password);
        inputMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.toggleSoftInput(0, 0);
	  newpass=(EditText)findViewById(R.id.et_change_password_new_password);
	  oldpass=(EditText)findViewById(R.id.et_change_password_old_password);
	  oldpass.requestFocus();
	  confirmpass=(EditText)findViewById(R.id.et_change_password_confirm_password);
	  change=(Button)findViewById(R.id.btn_change_password_change);
	  change.setOnClickListener(this);
	  myDb = new DbHelper(this);
	  addButton();
	  oldpass.setText(password);
	  int i=oldpass.getText().length();
	  oldpass.setSelection(i);
}
@Override
	  public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_change_password_change:
		if (Validation()) {
			Toast.makeText(this, "All entry fill", Toast.LENGTH_SHORT).show();
		}
		
		
		break;

		default:
			break;
		}
		
	}
private boolean Validation(){
	Newpass=newpass.getText().toString().trim();
	Oldpass=oldpass.getText().toString().trim();
	Confirmpass=confirmpass.getText().toString().trim();
	
		if(Newpass.equals(""))
		{
			showMessage("Please fill required fields");
		      return false;
		}else if (Confirmpass.equals("")) {
			showMessage("Please fill required fields");
			  return false;
		}else if (!Newpass.equals(Confirmpass)) {
			showMessage("Password and Confirm Password are not same!");
			  return false;
		}
		return true;
		
		
	}
	
		
private void showMessage(String msg) 
{        
	    
		Toast toast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
	}
	
	
	
/*Set data from database by select query*/
public void addButton() {

  SQLiteDatabase db = myDb.getReadableDatabase();
 try{
	 
      Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ID,PASSWORD}, null,null, null, null, null);		
		if(cursor.getCount()>0){
			cursor.moveToFirst();           
			do{
			                                                 
				 User_id=cursor.getString(0);                                                                                                 
				 password=cursor.getString(1);
			     
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




