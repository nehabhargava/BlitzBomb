package com.BlitzBomb;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class Home extends Activity implements OnClickListener{
	public static final String ROWID = "user_id";
	public static final String EMAIL = "email";
	private DbHelper myDb = new DbHelper(this);
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	/*hide title bar*/
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	
	    	
	    	/*set orientation portrait mode*/
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.home);
	        addEntry();
	       ImageButton Login=(ImageButton)findViewById(R.id.bt_Home_Login);
	       ImageButton register=(ImageButton)findViewById(R.id.bt_Home_Register);
	        Login.setOnClickListener(this);
	        register.setOnClickListener(this);
	        
}

	@Override
	public void onClick(View v) 
	{
	   switch (v.getId()) {
		case R.id.bt_Home_Login:
			Intent login = new Intent(this,Login.class);
			startActivity(login);
			finish();
			
			break;
         case R.id.bt_Home_Register:
       	    Intent register = new Intent(this,Registration.class);
			startActivity(register);
			finish();
			break;
		   default:
			break;
		}
	}
	public void addEntry() {
		
       SQLiteDatabase db = myDb.getReadableDatabase();
      try{
			Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ROWID,EMAIL}, null,null, null, null, null);		
			if(cursor.getCount()>0){
				cursor.moveToFirst();
				do{
					Intent login=new Intent(this,BlitzBombGrid.class); 
					startActivity(login);
					finish();
					
				} while (cursor.moveToNext());
				
		        }
			cursor.close();
			db.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	finish();
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}