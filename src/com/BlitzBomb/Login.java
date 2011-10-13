package com.BlitzBomb;


import java.util.ArrayList;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.network_connection.CheckConnection;
import com.network_connection.HttpConnection;

public class Login extends Activity implements OnClickListener{
	EditText Email,Password;   
	
	ImageButton Ok,forgot;
	String email,password,user_id;
    static String user_name;
	String url;//http://10.10.10.67/blitzbomb/index.php?
	String index;
	//Check pattern of Email.
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("([\\w+|\\.?]+)\\w+@([\\w+|\\.?]+)\\.(\\w{2,8}\\w?)");
                /*"[a-zA-Z0-9+._%-+]{1,256}" +
	              "@" +
	              "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
	              "(" +
	              "." +
	              "[a-zA-Z0-9][a-zA-Z0-9-]{0,3}" +
	              ")+"*/
	public static final String active="1";
	private Thread login1;
	private ProgressDialog dialog;
	ArrayList<String> response_list=new ArrayList<String>();
	int i=1;
    String Result,result;
    DbHelper myDb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.login);
		url=getString(R.string.url);
		index=getString(R.string.Index);
		Email=(EditText)findViewById(R.id.et_login_email);
		Password=(EditText)findViewById(R.id.et_login_password);
		forgot=(ImageButton)findViewById(R.id.ivBtn_login_forgot);
		Ok=(ImageButton)findViewById(R.id.btn_login_ok);
		forgot.setOnClickListener(this);
		//Cancel=(Button)findViewById(R.id.btn_login_cancel);
		Ok.setOnClickListener(this);
		//Cancel.setOnClickListener(this);
		myDb = new DbHelper(this);
	}
                            
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login_ok:
			
			if (Validation())
			{
				if(checkEmail(email)){
					
				loginuser()	;
					//Login1();
			        }else {
				showMessage("fill valid email...!"); 
			  }
			
			} 
	        
			break;
		case R.id.ivBtn_login_forgot:
			Intent forgotPassword=new Intent(this,Forgot.class);
			startActivity(forgotPassword);
		default:
			break;
		}
		
	}
	
	 private boolean checkEmail(String email) 
	 {
	     return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	 }
	private void loginuser() {

		Log.v("loginuser call", "in loginuser();");
		new ProgressDialog(this);
		dialog = ProgressDialog.show(this, "Connecting",
				"Wait While Connecting");
		login1 = new Thread() {
			@Override
			public void run() {
				Log.v("thred run", "thread started Login()");
				Login1();
			}
		};
		login1.start();
	}
	
	private void Login1() {   
		String Check_response="";
		//http://10.10.10.122/blitzbomb/android/index.php?mode=android&action=login&login_email=sanjaysaraswat@blitzbomb.com&password=2011@sipl
	    String link = url+index+"&action=login&login_email="+email+"&password="+password;
		HttpConnection httpconnection=new HttpConnection (link);
	    Result=httpconnection.httpConnection();   
	    System.out.println("response"+Result);
	    try {
		// JSONobject parse for json response from server. e.g({"user":"valid"})
		JSONObject jArray = new JSONObject(Result);
		System.out.println("response array"+jArray);
		Check_response = jArray.getString("active");
		if(Check_response.equals("yes"))
		 {
			 user_id=jArray.getString("user_id");
			 user_name=jArray.getString("user_displayname");
		    
		 }
	   } catch (JSONException e) {
		
		e.printStackTrace();             
	}
	   final Message msg = new Message();
		msg.obj = Check_response;
		Log.v("message", msg.obj.toString());
		Log.v("handler call", "sending msg to handler");
		handler.sendMessage(msg);
}	
     
                                      
	private boolean Validation(){
		email=Email.getText().toString().trim();
		password=Password.getText().toString().trim();
		if(email.equals(""))
		{
	         showMessage("Please fill Email.....!");
		      return false;
		}else if (password.equals("")) {
			 showMessage("Please fill Password.....!");
			  return false;
		}
		if (CheckConnection.isNetworkAvailable(this)) {
			Log.v("network", "available");
			
		return true;
		} else {
			Log.v("network check", "network not available");
			showMessage("network not available");
			return false;
		}
		
		
	}

	private void showMessage(String msg) {
		 Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
		
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			result = (String) msg.obj;
			System.out.println("session"+result);
		    if(result.equals("yes")){
		    	dialog.dismiss();
				addEntry(user_id,user_name,email,password);
				/*Bundle name=new Bundle();
				name.putString("user_name", user_name);
				name.putString("user_ID", user_id);*/
				Intent grid= new Intent(Login.this,BlitzBombGrid.class);
				//grid.putExtras(name);
				startActivity(grid);
				finish();
		    	
			}                               
		else  
			{
			 showMessage("user invalid");
			 dialog.dismiss();
			 login1.stop();	
				
			}
			login1.stop();
        
		}
	};
	private void addEntry(String user_id,String user_name,String email,String password) {
	SQLiteDatabase db = myDb.getWritableDatabase();
            ContentValues values = new ContentValues();
			values.put("user_id",user_id);
			values.put("name",user_name);
			values.put("password",password);
			values.put("email",email);
		    try{
	            db.insert(DbHelper.TABLE_NAME, null, values);
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
	

	
	
