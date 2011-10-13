package com.BlitzBomb;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;
import com.network_connection.HttpConnection;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
public class ProfileInformation extends Activity implements OnItemSelectedListener,OnClickListener{
	TabHost tab;
	Button Save,skip,date,time;
	Spinner Country,State,City,Gender;
	EditText postalCode;
	String selected_state,countryID,State_id,City_id,selected_city,Result,postal_code,stateID,cityID,getGender,zipCode;
	String[] gender={"Select Gender","Male","Female"};
	int User_ID;
	    ArrayList<String> country_id=new ArrayList<String>();
	    ArrayList<String> state=new ArrayList<String>();
	    ArrayList<String> state_id=new ArrayList<String>();
	    ArrayList<String> city_id=new ArrayList<String>();
	    ArrayList<String> city=new ArrayList<String>();
	    ArrayList<Integer> userId=new ArrayList<Integer>();
	    ArrayList<String> country=new ArrayList<String>();
	    private DbHelper myDb = new DbHelper(this);
	    public static final String ROWID = "user_id";
	 
		DateFormat fmtDateAndTime=DateFormat.getDateInstance();
		  TextView dateAndTimeLabel;
		  Calendar dateAndTime=Calendar.getInstance();
	      DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		  public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
	      dateAndTime.set(Calendar.YEAR, year);
		  dateAndTime.set(Calendar.MONTH, monthOfYear);
		  dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
			}
			};
			
		@Override
	    protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.profile_information);
		addEntry();
		Country=(Spinner)findViewById(R.id.sp_invite_profile_Country);
		State=(Spinner)findViewById(R.id.sp_invite_profile_state);
		City=(Spinner)findViewById(R.id.sp_invite_profile_city);
		Gender=(Spinner)findViewById(R.id.sp_invite_profile_gender);
		postalCode=(EditText)findViewById(R.id.et_invite_profile_postalcode);
		Save=(Button)findViewById(R.id.btn_invite_profile_save);
		skip=(Button)findViewById(R.id.btn_invite_profile_skip);
		skip.setOnClickListener(this);
		Save.setOnClickListener(this);
		
        country();
	    user_information();
	    ArrayAdapter<String> relation_adapter=new 
		ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,country);
        relation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Country.setAdapter(relation_adapter);
        int i=country_id.indexOf(countryID);
        System.out.println("countryID"+i);
		Country.setSelection(i);      
		Country.setOnItemSelectedListener(this);
		State.setOnItemSelectedListener(this);
		City.setOnItemSelectedListener(this);
		
		Gender.setOnItemSelectedListener(this);
		ArrayAdapter<String> relation_adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gender);
		relation_adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Gender.setAdapter(relation_adapter3);
		if(getGender.equals("Male")){
	      	Gender.setSelection(1);
	        }else if (getGender.equals("Female")) {
		    Gender.setSelection(2);
	        }
		dateAndTimeLabel=(TextView)findViewById(R.id.dateAndTimeLabel);
		dateAndTimeLabel.setOnClickListener(this);
		
      }
	 @Override
	 public void onClick(View v) 
	 {
		switch (v.getId()) {
		case R.id.btn_invite_profile_save:
			Intent i=new Intent(this,ChangeImage.class );
			startActivity(i);
			break;
        case R.id.btn_invite_profile_skip:
        	TabHost tab=(TabHost)getParent().findViewById(android.R.id.tabhost);
			tab.setCurrentTab(1);
			
			break;
		case R.id.dateAndTimeLabel:
			new DatePickerDialog(ProfileInformation.this,d,dateAndTime.get(Calendar.YEAR),dateAndTime.get(Calendar.MONTH),dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
			break;
		
		default:
			break;
		}
	}
void country(){    
		String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=country";
		HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("Result"+Result);          
	    try {                    
			  JSONArray Countrylist=new JSONArray(Result);
			  for (int i = 0; i < Countrylist.length(); i++) {  
			   country.add(Countrylist.getJSONObject(i).getString("name").toString());
				country_id.add(Countrylist.getJSONObject(i).getString("id").toString());
			 }      
		} catch (Exception e) {                         
		
		}                                                                           
	}
	void state(){
		 String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=state&country_id="+countryID;
		 
		HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("Result"+Result);
	    try {
	    	state.clear();            
			 JSONArray Countrylist=new JSONArray(Result);
			 for (int i = 0; i < Countrylist.length(); i++) {  
				 state.add(Countrylist.getJSONObject(i).getString("name").toString());
				 state_id.add(Countrylist.getJSONObject(i).getString("id").toString());
			 }                  
			ArrayAdapter<String> relation_adapter1=new 
			ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state);
			relation_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			State.setAdapter(relation_adapter1);
			relation_adapter1.notifyDataSetChanged();
			 int i=state_id.indexOf(stateID);
			State.setSelection(i);                 
		} catch (Exception e) {                                         
			
		}                                 
	}     
	
 void city(){
		String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=city&stateid="+State_id;
 		HttpConnection httpconnection=new HttpConnection (link);
 	    String Result=httpconnection.httpConnection();   
 	    System.out.println("Result"+Result);
 	    try {            
 	    	city.clear();
 			 JSONArray Countrylist=new JSONArray(Result);
 			 for (int i = 0; i < Countrylist.length(); i++) {  
 				 city.add(Countrylist.getJSONObject(i).getString("name").toString());
 				 city_id.add(Countrylist.getJSONObject(i).getString("id").toString());
 			 }                             
 			ArrayAdapter<String> relation_adapter2=new 
 			ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,city);
 			relation_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			City.setAdapter(relation_adapter2);
 			relation_adapter2.notifyDataSetChanged();
 			//City.setAdapter(relation_adapter2);
			int i=city_id.indexOf(cityID);
			City.setSelection(i);
 		} catch (Exception e) {
 			
 		}                     
 	}
 
 void user_information(){
		
				String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=profile&id=123";
		        HttpConnection httpconnection=new HttpConnection (link);
	            Result=httpconnection.httpConnection();   
	              System.out.println("Result"+Result);
		
	    try {  
			    	JSONObject userinfo=new JSONObject(Result);
			    	
			    	System.out.println("information"+userinfo);
			    	countryID=userinfo.getString("country");
			    	stateID=userinfo.getString("state");
			    	cityID=userinfo.getString("city");
			    	getGender=userinfo.getString("profile_gender");
			    	postalCode.setText(userinfo.getString("zip_code"));
			    	System.out.println("city_id"+cityID);
			    	System.out.println("state_id"+stateID);
			    	
				    } catch (Exception e) {                
					                  
				    }
		
	}   
	/* Set data from database by select query*/
	public void addEntry() {
	
      SQLiteDatabase db = myDb.getReadableDatabase();
     try{
	      Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ROWID}, null,null, null, null, null);		
			if(cursor.getCount()>0){
				cursor.moveToFirst();
				do{
					userId.add(cursor.getInt(0));
				  
				  
				     User_ID=userId.get(0);
			    System.out.println("id"+User_ID);
					
				} while (cursor.moveToNext());
				cursor.close();
				db.close();  
		        }
		}catch(Exception e){
			e.printStackTrace();
		}
      }
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		switch (arg0.getId()) {
		case R.id.sp_invite_profile_Country:
			 countryID=country_id.get(arg2);
			 state();
			 System.out.println("id"+countryID);
			break;
		case R.id.sp_invite_profile_state:
			
			 State_id=state_id.get(arg2);
			 city();
			 System.out.println("id"+State_id);
			break;
		case R.id.sp_invite_profile_city:
			
			City_id=city_id.get(arg2);
			// city();        
			 System.out.println("id"+City_id);
			break;
		
		
		
		default:
			break;
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	
	}
	private void updateLabel() {
		dateAndTimeLabel.setText(fmtDateAndTime.format(dateAndTime.getTime()));
		} 
}
