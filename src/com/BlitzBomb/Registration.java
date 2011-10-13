package com.BlitzBomb;

import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.network_connection.CheckConnection;
import com.network_connection.HttpConnection;

public class Registration extends Activity implements OnClickListener{
	EditText first_name,last_name,email,password,confirm_password;
	String First_name,Last_name,Email,Password,Confirm_password;
	Button save,cancel;
	String url;//http://10.10.10.67/blitzbomb/index.php?
	String index;
	String Result,Check_response="",result;
	private Thread login1;
	private ProgressDialog dialog;
	
	//Check pattern of Email.
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
	              "@" +
	              "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
	              "(" +
	              "." +
	              "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
	              ")+"
	          );
	//private DbHelper myDb = new DbHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);/*set orientation portrait mode*/
    	
		setContentView(R.layout.registration);
		url=getString(R.string.url);
		index=getString(R.string.Index);
		first_name=(EditText)findViewById(R.id.et_registration_first_name);
		last_name=(EditText)findViewById(R.id.et_registration_last_name);
		email=(EditText)findViewById(R.id.et_registration_Email);
		password=(EditText)findViewById(R.id.et_registration_Password);
		confirm_password=(EditText)findViewById(R.id.et_registration_confirm_Password);
		save=(Button)findViewById(R.id.btn_register_save);
		cancel=(Button)findViewById(R.id.btn_register_cancel);
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
		//myDb = new DbHelper(this);
		
	}
	private boolean Validation(){
		First_name=first_name.getText().toString().trim();
		Last_name=last_name.getText().toString().trim();
		Email=email.getText().toString().trim();
		Password=password.getText().toString().trim();
		Confirm_password=confirm_password.getText().toString().trim();
		
		
		if(First_name.equals(""))
		{
	       showMessage("Please fill First name.....!");
		    return false;
		}else if (Last_name.equals("")) {
			 showMessage("Please fill Last name.....!");
			 return false;
		}else if (Password.equals("")) {
			 showMessage("Please fill Password.....!");
			 return false;
		}else if (Confirm_password.equals("")) {
			 showMessage("Please fill Confirm password.....!");
			 return false;
		}else if (Email.equals("")) {
			 showMessage("Please fill Email.....!");
			 return false;
		}else if (!Password.equals(Confirm_password)) {
			 showMessage("Please fill correct comfirm password.....!");
			 return false;
		}
		if (CheckConnection.isNetworkAvailable(this)) {
			return true;
		} else {
			
			showMessage("network not available");
			return false;
		}	
	    
		
	}
	private void showMessage(String msg) {
		Toast toast=Toast.makeText(Registration.this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
	}
	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.btn_register_cancel:
		/*Intent register=new Intent(this,EditUserInfo.class);
		startActivity(register);*/
		
		first_name.setText("");
		last_name.setText("");
		email.setText("");
		password.setText("");
		confirm_password.setText("");
		
		break;
    case R.id.btn_register_save:
		if (Validation()) 
		{
			if(checkEmail(Email)){
				 registeruser();
			 }else {
				showMessage("fill valid email...!"); 
			}
		}
		break;
	default:
		break;
	}
		
	}
	
	 private boolean checkEmail(String email) 
	 {
	     return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	 }
	 
	private void registeruser() {
    
		new ProgressDialog(this);
		dialog = ProgressDialog.show(this, "Connecting","Wait While Connecting");
		login1 = new Thread() {
			@Override      
			public void run() {
				
				register();
			}
		};
		login1.start();
	}       
	// Function which call webservise for user registration.
	private void register() {
		//String link= url+"/index.php?mode=android_signup&email="+Email+"&firstname="+First_name+"&lastname="+Last_name+"&password="+Password;
		String link=url+index+"&action=signup&email="+Email+"&firstname="+First_name+"&lastname="+Last_name+"&password="+Password;
		HttpConnection httpconnection=new HttpConnection (link);
	    Result=httpconnection.httpConnection();
	    System.out.println("response"+Result);
	    try {
	    	//Initialize JSONObject object for parsing json response from server.
	    	JSONObject jArray = new JSONObject(Result);
			System.out.println("response array"+jArray);
			// these 2 are strings 
			 Check_response = jArray.getString("message"); 
		     System.out.println("id"+Check_response);
		     } 
	    catch (JSONException e) 
	    {
			e.printStackTrace();
		}
	    final Message msg = new Message();
		msg.obj = Check_response;
		Log.v("message", msg.obj.toString());
		Log.v("handler call", "sending msg to handler");
		handler.sendMessage(msg);
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			result = (String) msg.obj;
			System.out.println("session"+result);
			
			Log.v("Session", result);
			
		
			 if(result.equals("Email Already Exist")){
				 showMessage("Email Already Exist");
				 dialog.dismiss();
				 login1.stop();
			}
		else if (result.equals("Successfully Registered"))
			{
				dialog.dismiss();
				showMessage("Successfully Registered");
				//addEntry(First_name,Last_name,Email,Password,Confirm_password);
				Intent grid= new Intent(Registration.this,InviteFriend.class);
				startActivity(grid);
				finish();
				
			}
			login1.stop();

		}
	};
	//Close database class.                                               
	/*public void onDestroy(){
		super.onDestroy();
		
		myDb.close();
	}	*/
	/*private void addEntry(String firstname,String lastname,String email,String password, String confirm_password) {
		SQLiteDatabase db = myDb.getWritableDatabase();
	
				ContentValues values = new ContentValues();
				values.put("first_name",firstname);
				values.put("last_name",lastname);
				values.put("password",password);
				values.put("email",email);
				values.put("confirmed_password",confirm_password);
			   
				
				
				try{
		             db.insert(DbHelper.TABLE_NAME, null, values);
					
				}catch(Exception e){
					e.printStackTrace();
				}
		
	}*/
}


