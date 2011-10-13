package com.BlitzBomb;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.network_connection.HttpConnection;

public class EditUserInfo extends Activity implements OnItemSelectedListener,OnClickListener{
	EditText firstName,lastName,About_me,date_of_birth,
	Home_Town,Zip_code,language,current_city,Collage,
	High_school,Employer,        
    PliticalView,ReligeousView,fav_Qoute,Intrest,Activity,WhoWhouldmeet,Sport_i_play,
    favorite_sport,favoriteCollegeTeam,professionalTeam,favoriteAthelets,Television,
    movies,music,Books,Games,Email,phone,Address,Website;
	
	String F_name,L_name,gender,Date,looking_for,home_town,zip_code,about_me,
	ploitical,religeous,qoute,intrest,activity_select,television,movie,Music,books,
	high_school,collage,univercity,occupation,employer,email,Mobile_phone,otherphone,
	sportPlay,favoritesport,collegeTeam,ProfessionalTeam,athelets,fav_qoute,website,City_id,
	address,Looking_for,id,State_id,selected_state,selected_city,Country_id,Language,CurrentCity,games,likeToPlay;
	public static CheckBox men,women;
	String genderName;
	Button change_picture,change_password;
    Spinner Relation,Gender,Country,State,City,Ethenicity;
    ArrayList<String> fist_name=new ArrayList<String>();
    ArrayList<String>intgrested_in=new ArrayList<String>();
    ArrayList<String> country_id=new ArrayList<String>();
    ArrayList<String> state=new ArrayList<String>();
    ArrayList<String> state_id=new ArrayList<String>();
    ArrayList<String> city_id=new ArrayList<String>();
    ArrayList<String> city=new ArrayList<String>();
    //ArrayList<Integer> userId=new ArrayList<Integer>();
    ArrayList<String> country=new ArrayList<String>();
    Button save;
   
    //final String myString="112";
    int User_ID,userID;
   
    String[] gender_arr={"Select Gender","male","female"};
    public static final String ROWID = "user_id";
    private DbHelper myDb = new DbHelper(this);
    // array list for select relation from sppiner.
    String[] select_relation={"Select relation","Single",
    		                  "In a relation","Engaged","married","Its Complicated",
    		                  "In an Open relationship","widowed","Separated","Divorced"};
	@Override       
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);        
	    /*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.edit_user_info);        
		addEntry();    
		initilizeEditText();
		Gender=(Spinner)findViewById(R.id.sp_Edituserinformation_gender_selected);
		Relation=(Spinner)findViewById(R.id.sp_Edituserinformation_relation_selected);
		Country=(Spinner)findViewById(R.id.sp_Edituserinformation_select_country);
		State=(Spinner)findViewById(R.id.sp_Edituserinformation_select_state);
		City=(Spinner)findViewById(R.id.sp_Edituserinformation_select_city);
		save=(Button)findViewById(R.id.btn_Edituserinformation_save);
		change_picture=(Button)findViewById(R.id.btn_edit_user_info_change_picture);
		change_password=(Button)findViewById(R.id.btn_edit_user_info_change_password);
		men=(CheckBox)findViewById(R.id.cb_Edituserinformation_men);
		women=(CheckBox)findViewById(R.id.cb_Edituserinformation_women);
		//men.setOnCheckedChangeListener(this);
		//Women.setOnCheckedChangeListener(this);
		change_password.setOnClickListener(this);
		change_picture.setOnClickListener(this);
		save.setOnClickListener(this);
		country();
		user_information(); 
		System.out.println("intgrested_inintgrested_in"+intgrested_in);
		for (int i = 0; i < intgrested_in.size(); i++) {
			System.out.println("intgrested_in"+intgrested_in.get(i));
			System.out.println("intgrested_in"+intgrested_in.get(i));
			String str=intgrested_in.get(i);
			System.out.println("strstrstrstrstrstr"+str);
			if(str.equals("Women")){
				women.setChecked(true);
			}
			if (str.equals("Men")) {
				men.setChecked(true);
			}
		
		}
	    
		System.out.println("gender"+gender);
		ArrayAdapter<String> privacy_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gender_arr);
		privacy_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Gender.setAdapter(privacy_adapter);
		String s= gender;
		System.out.println("s"+s);
        if(s.equals("Male")){
      	Gender.setSelection(1);
        }else if (s.equals("Female")) {
	    Gender.setSelection(2);
        }
        ArrayAdapter<String> relation=new 
		ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,select_relation);
        relation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Relation.setAdapter(relation);
    
		ArrayAdapter<String> relation_adapter=new 
		ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,country);
        relation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                	      
		//int spinnerPosition = relation_adapter.getPosition(myString);
		//Country.setSelection(spinnerPosition);      
	    Country.setAdapter(relation_adapter);
	    int i=country_id.indexOf(Country_id);
		Country.setSelection(i);
		Country .setOnItemSelectedListener(this); 
		State.setOnItemSelectedListener(this);                  
		City.setOnItemSelectedListener(this); 
		 getvalues();
		 //System.out.println("iddddddd"+id1);
	}  
	/*Initilize All EditText Boxes*/
	void initilizeEditText(){
		firstName=(EditText)findViewById(R.id.et_Edituserinformation_firstName_selected);
		lastName=(EditText)findViewById(R.id.et_Edituserinformation_lastName_selected);
		About_me=(EditText)findViewById(R.id.et_Edituserinformation_about_me_selected);
		language=(EditText)findViewById(R.id.et_Edituserinformation_language_selected);
		date_of_birth=(EditText)findViewById(R.id.et_Edituserinformation_date_of_birth_selected);
		current_city=(EditText)findViewById(R.id.et_Edituserinformation_current_city);
		Home_Town=(EditText)findViewById(R.id.et_Edituserinformation_Hometown);
		PliticalView=(EditText)findViewById(R.id.et_Edituserinformation_plitical_selected);
	    ReligeousView=(EditText)findViewById(R.id.et_Edituserinformation_regieous_selected);
	    fav_Qoute=(EditText)findViewById(R.id.et_Edituserinformation_fav_Qoute_selected);
	    Intrest=(EditText)findViewById(R.id.et_Edituserinformation_Intrest_selected);
		Activity=(EditText)findViewById(R.id.et_Edituserinformation_Activity_selected);
		WhoWhouldmeet=(EditText)findViewById(R.id.et_Edituserinformation_Who_whouldyoulike_selected);
		Sport_i_play=(EditText)findViewById(R.id.et_Edituserinformation_sports_i_play_selected);
		favorite_sport=(EditText)findViewById(R.id.et_Edituserinformation_favorite_sport_selected);
		favoriteCollegeTeam=(EditText)findViewById(R.id.et_Edituserinformation_favorite_college_selected);
		professionalTeam=(EditText)findViewById(R.id.et_Edituserinformation_favorite_professionaTeam_selected);
		favoriteAthelets=(EditText)findViewById(R.id.et_Edituserinformation_favorite_Athlets_selected);
		Email=(EditText)findViewById(R.id.et_Edituserinformation_email_selected);
		Television=(EditText)findViewById(R.id.et_Edituserinformation_television_selected);
	    movies=(EditText)findViewById(R.id.et_Edituserinformation_movie_selected);
		music=(EditText)findViewById(R.id.et_Edituserinformation_music_selected);
		Books=(EditText)findViewById(R.id.et_Edituserinformation_books_selected);
		High_school=(EditText)findViewById(R.id.et_Edituserinformation_High_school_selected);
		Collage=(EditText)findViewById(R.id.et_Edituserinformation_Collage_selected);
		Address=(EditText)findViewById(R.id.et_Edituserinformation_address_selected);
		Games=(EditText)findViewById(R.id.et_Edituserinformation_games_selected);
		Employer=(EditText)findViewById(R.id.et_Edituserinformation_Employer_selected);
		phone=(EditText)findViewById(R.id.et_Edituserinformation_Mobile_no_selected);
		Website=(EditText)findViewById(R.id.et_Edituserinformation_website_selected);
		Zip_code=(EditText)findViewById(R.id.et_Edituserinformation_zipcode_selected);
	}
	void state(){
		String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=state&country_id="+id;
 		HttpConnection httpconnection=new HttpConnection (link);
 	    String Result=httpconnection.httpConnection();   
 	    System.out.println("Result"+Result);
 	    try {                  
 			 JSONArray Countrylist=new JSONArray(Result);
 			 state.clear();
 			 for (int i = 0; i < Countrylist.length(); i++) {  
 				 state.add(Countrylist.getJSONObject(i).getString("name").toString());
 				 state_id.add(Countrylist.getJSONObject(i).getString("id").toString());
 			 }                  
 			ArrayAdapter<String> relation_adapter1=new 
 			
 			ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state);
 			System.out.println("statestatestatestate"+"  "+state);
 			relation_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			State.setAdapter(relation_adapter1);
 			relation_adapter1.notifyDataSetChanged();
 			if(!selected_state.equals("0")){
 			 int i=state_id.indexOf(selected_state);
 			State.setSelection(i);  
 			}else {
 					}
 		} catch (Exception e) {                                         
 			
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
	/* {"id":"70","user_id":"70","firstname":"sandeep","lastname":"va","profile_gender":"Male",
	 * "profile_birthdate":["27","06","0000"],"interested_in":["Women"],"lookingfor":[""],"hometown":"",
	 * "country":"112","state":"1490","city":"674754","zip_code":"666666666","about_me":"","political_view":"",
	 * "religious_view":"","fav_quote":"","interest":"","activity":"","television":"","movies":"","music":"",
	 * "books":"","highschool":"yy","college":"","university_college":"h","occupation":"","employer":"",
	 * "email":"test.test@test.com","mobile_phone":"1111111111","other_phone":"","address":"yy",
	 * "website":"http:\/\/www.blitzbomb.com\/profile.php","languages":"","current_city":"",
	 * "profile_ethnicity":"","games":"","like_to_meet":"","sports_play":"","favorite_sports":"",
	 * "favorite_college_team":"","favorite_professional_team":"","favorite_athletes":"","relation_name":"",
	 * "relation_image":""}
	 * */
	void user_information(){
		String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=profile&id=123";
		HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("Result"+Result);
	    try {  
	    	JSONObject userinfo=new JSONObject(Result);
	    	System.out.println("information"+userinfo);
	    	JSONArray Intrestedin=userinfo.getJSONArray("interested_in");
			
	    	userID=userinfo.getInt("id");
	    	date_of_birth.setText(userinfo.getString("profile_birthdate"));
	    	/* System.out.println("interested_in"+Intrestedin);
			 for (int i = 0; i < Intrestedin.length(); i++) {    
				
			intgrested_in=Intrestedin.getJSONObject(i).getString("Women").toString();
			}
			System.out.println("intrest"+intgrested_in);*/
	    	firstName.setText(userinfo.getString("firstname"));
	    	lastName.setText(userinfo.getString("lastname"));
			gender=userinfo.getString("profile_gender");
			Country_id=userinfo.getString("country");
		    selected_state=userinfo.getString("state");
		    selected_city=userinfo.getString("city");
			Home_Town.setText(userinfo.getString("hometown"));
			Zip_code.setText(userinfo.getString("zip_code"));
			About_me.setText(userinfo.getString("about_me"));
			PliticalView.setText(userinfo.getString("political_view"));
			ReligeousView.setText(userinfo.getString("religious_view"));
			fav_Qoute.setText(userinfo.getString("fav_quote"));
			Intrest.setText(userinfo.getString("interest"));
			Activity.setText(userinfo.getString("activity"));
			Television.setText(userinfo.getString("television"));
			movies.setText(userinfo.getString("movies"));
			music.setText(userinfo.getString("music")); 
			Games.setText(userinfo.getString("games"));
		    Books.setText(userinfo.getString("books"));
		    High_school.setText(userinfo.getString("highschool"));
		    Collage.setText(userinfo.getString("university_college"));
		    language.setText(userinfo.getString("languages"));
		    current_city.setText(userinfo.getString("current_city"));
		    Website.setText(userinfo.getString("website"));
		    Employer.setText(userinfo.getString("employer"));
		    Email.setText(userinfo.getString("email"));
		    phone.setText(userinfo.getString("mobile_phone"));
		    WhoWhouldmeet.setText(userinfo.getString("like_to_meet"));
		    Sport_i_play.setText(userinfo.getString("sports_play"));
		    favorite_sport.setText(userinfo.getString("favorite_sports"));
		    Log.v("dfsdf", "sdfsdf");
		    favoriteCollegeTeam.setText(userinfo.getString("favorite_college_team"));
		    professionalTeam.setText(userinfo.getString("favorite_professional_team"));
		     favoriteAthelets.setText(userinfo.getString("favorite_athletes"));
		    Address.setText(userinfo.getString("address"));
		    Log.v("dfsdf", "sdfsdf");
			JSONArray userlookin=userinfo.getJSONArray("interested_in");
             System.out.println("userlookin"+userlookin);
             for(int i=0;i< userlookin .length();i++)
             {
			  intgrested_in.add(userlookin.getString(i));
             } 
			 System.out.println("intgrested_in"+intgrested_in);
		   	   /*for(int i=0;i< information .length();i++)
	    	{
	    		fist_name.add(information.getJSONObject(i).getString("firstname").toString());
	    		
	    	}             */
	    	 //System.out.println("firstname"+information);
		    } catch (Exception e) {                
	   }  
    }   
	void getvalues(){
		F_name=firstName.getText().toString().trim();
		L_name=lastName.getText().toString().trim();
		about_me=About_me.getText().toString().trim();
		Language=language.getText().toString().trim();
		CurrentCity=current_city.getText().toString().trim();
		home_town=Home_Town.getText().toString().trim();
		collage=Collage.getText().toString().trim();
		high_school=High_school.getText().toString().trim();
		employer=Employer.getText().toString().trim();
		Music=music.getText().toString().trim();
		movie=movies.getText().toString().trim();
	    television=Television.getText().toString().trim();
		games=Games.getText().toString().trim();
		books=Books.getText().toString().trim();
		activity_select=Activity.getText().toString().trim();
		intrest=Intrest.getText().toString().trim();
		likeToPlay=WhoWhouldmeet.getText().toString().trim();
	    sportPlay=Sport_i_play.getText().toString().trim();
	    collegeTeam=favoriteCollegeTeam.getText().toString().trim();
	    ProfessionalTeam=professionalTeam.getText().toString().trim();
	    athelets=favoriteAthelets.getText().toString().trim();
	    ploitical=PliticalView.getText().toString().trim();
	   	religeous=ReligeousView.getText().toString().trim();
	    fav_qoute=fav_Qoute.getText().toString().trim();
	    email=Email.getText().toString().trim();
	    Mobile_phone=phone.getText().toString().trim();
	    address=Address.getText().toString().trim();              
	    website=Website.getText().toString().trim();
	}
	/* Set data from database by select query*/
	public void addEntry() {
	
      SQLiteDatabase db = myDb.getReadableDatabase();
     try{
	      Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ROWID}, null,null, null, null, null);		
			if(cursor.getCount()>0){
				cursor.moveToFirst();
				do{
					//userId.add(cursor.getInt(0));
				  
				  
				     User_ID=cursor.getInt(0);
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
		case R.id.sp_Edituserinformation_select_country:
			 id=country_id.get(arg2);
			 state();
			 System.out.println("id"+id);
			break;
		case R.id.sp_Edituserinformation_select_state:
			
			 State_id=state_id.get(arg2);
			 city();
			 System.out.println("id"+id);
			break;
		case R.id.sp_Edituserinformation_select_city:
			
			 City_id=city_id.get(arg2);
			// city();
			 System.out.println("id"+id);
			break;
		default:
			break;
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	   
		              
	}
	private void city(){
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
			int i=city_id.indexOf(selected_city);
			City.setSelection(i);
 		} catch (Exception e) {
 			
 		}                     
	}
	@Override
	public void onClick(View v) {         
	switch (v.getId()) {
	 
    case R.id.btn_Edituserinformation_save:
	getvalues();
    String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=update&id="+userID+
    		      "&userid=70&firstname=sam"+"&lastname=sam"+"&profile_gender=male&interested_in=1"+
				  "&occupation=ser&profile_birthdate=27,01,0000&hometown="+home_town+"&country="+id+
				  "&state="+State_id+"&city="+City_id +"&zip_code="+zip_code+"&about_me="+about_me+
				  "&political_view="+ploitical+"&religious_view="+religeous+"&fav_quote="+fav_qoute+
				  "&interest="+intrest+"&activity="+activity_select+"&television="+television+
				  "&movies="+movie+"&music="+Music+"&books="+books+"&highschool="+high_school+
				  "&college="+collage+"&university_college=text&employer="+employer+"&email="+email+
				  "&mobile_phone="+Mobile_phone+"&other_phone=text&address="+address+
				  "&website="+website+"&languages="+Language+"&current_city="+CurrentCity+
				  "&profile_ethnicity=text&games="+games+"&like_to_meet=text" + "&sports_play="+sportPlay+
				  "&favorite_sports="+favoritesport+"&favorite_college_team="+collegeTeam+
				  "&favorite_professional_team="+ProfessionalTeam+"&"+"favorite_athletes="+athelets+
				  "&relation_name=text&relation_image=text";
		HttpConnection httpconnection=new HttpConnection (link);                  
 	    String Result=httpconnection.httpConnection();   
 	    System.out.println("Result"+Result);      
		try {
			JSONObject edituser=new JSONObject();
			String message=edituser.getString("message");                   
			System.out.println("message"+message);   
		} catch (Exception e) {
		
		}
		break;
       case R.id.btn_edit_user_info_change_password:
    	  Intent Change_Password=new Intent(this,ChangePassword.class);
    	  startActivity(Change_Password);
    	  
    	  break;
       case R.id.btn_edit_user_info_change_picture:
     	  Intent Change_Picture=new Intent(this,EditImage.class);
     	  startActivity(Change_Picture);
     	  
     	  break;
	default:
		break;
	}
		
	}
	
	/*@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked) {
		switch (v.getId()) {
		case R.id.cb_Edituserinformation_men:
			if (isChecked) {
				genderName="Men";
				women.setChecked(false);
			}
			
			break;
        case R.id.cb_Edituserinformation_women:
        	if(isChecked){
        		men.setChecked(false);
        		genderName="Women";
        		
        	}
        	
			
			break;
		default:
			break;
		}
		
	}*/
}
