package com.BlitzBomb;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.network_connection.HttpConnection;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserInformation extends Activity implements OnClickListener{
	TextView First_Name,last_Name,date_of_birth,Langauge,Ethnicity,Relation,Current_city,Game,whoWhouldYouLike
	,Sport_i_play,Favorite_sport,Favorite_college_Team,Favorite_professional_Team,Favorite_Athelets
	,Looking_for,Home_Town,Zip_code,About_me,intrested_in,IM_Screen,
	PliticalView,religeousView,fav_Qoute,Intrest,gender,Country,City,State,
	activity,Television,movies,music,Books,High_school,
	Collage,Univercity,Occupation,Employer
	,Email,mobilephone,Otherphone,Address,Website;
	Button change_password;
	Integer User_ID;
	String ID,UserID;
	public static final String ROWID = "user_id";
	ArrayList<Integer> userId=new ArrayList<Integer>();
	private DbHelper myDb = new DbHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinformation);
		//First_Name=(TextView)findViewById(R.id.tv_UserInformation_firstname_selected);
		//last_Name=(TextView)findViewById(R.id.tv_UserInformation_lastname_selected);
		About_me=(TextView)findViewById(R.id.tv_UserInformation_about_me_selected);
		date_of_birth=(TextView)findViewById(R.id.tv_UserInformation_date_of_birth_selected);
		Langauge=(TextView)findViewById(R.id.tv_UserInformation_language_selected);
		gender=(TextView)findViewById(R.id.tv_UserInformation_gender_selected);
		Ethnicity=(TextView)findViewById(R.id.tv_UserInformation_ethnicity_selected);
		Relation=(TextView)findViewById(R.id.tv_UserInformation_relation_selected);
		intrested_in=(TextView)findViewById(R.id.tv_UserInformation_intrestedin_seleted);
		Current_city=(TextView)findViewById(R.id.tv_UserInformation_current_city_selected);
		Home_Town=(TextView)findViewById(R.id.tv_UserInformation_Hometown_selected);
		
		Collage=(TextView)findViewById(R.id.tv_UserInformation_Collage_selected);
		High_school=(TextView)findViewById(R.id.tv_UserInformation_High_school_selected);
		Employer=(TextView)findViewById(R.id.tv_UserInformation_Employer_selected);
		
		music=(TextView)findViewById(R.id.tv_UserInformation_music_selected);
		movies=(TextView)findViewById(R.id.tv_UserInformation_movie_selected);
		Television=(TextView)findViewById(R.id.tv_UserInformation_television_selected);
		Game=(TextView)findViewById(R.id.tv_UserInformation_games_selected);
		Books=(TextView)findViewById(R.id.tv_UserInformation_books_selected);
		
		activity=(TextView)findViewById(R.id.tv_UserInformation_Activity_selected);
		Intrest=(TextView)findViewById(R.id.tv_UserInformation_Intrest_selected);
		whoWhouldYouLike=(TextView)findViewById(R.id.tv_UserInformation_Who_whouldyoulike_selected);
		
		Sport_i_play=(TextView)findViewById(R.id.tv_UserInformation_sports_i_play_selected);
		Favorite_sport=(TextView)findViewById(R.id.tv_UserInformation_favorite_sport_selected);
		Favorite_college_Team=(TextView)findViewById(R.id.tv_UserInformation_favorite_college_selected);
		Favorite_professional_Team=(TextView)findViewById(R.id.tv_UserInformation_favorite_professionaTeam_selected);
		Favorite_Athelets=(TextView)findViewById(R.id.tv_UserInformation_favorite_Athlets_selected);
		
		Zip_code=(TextView)findViewById(R.id.tv_UserInformation_zipcode_selected);
	    PliticalView=(TextView)findViewById(R.id.tv_UserInformation_plitical_selected);
		religeousView=(TextView)findViewById(R.id.tv_UserInformation_regieous_selected);
		fav_Qoute=(TextView)findViewById(R.id.tv_UserInformation_fav_Qoute_selected);
		mobilephone=(TextView)findViewById(R.id.tv_UserInformation_Mobile_no_selected);
		Email=(TextView)findViewById(R.id.tv_UserInformation_email_selected);
		Address=(TextView)findViewById(R.id.tv_UserInformation_address_selected);
		IM_Screen=(TextView)findViewById(R.id.tv_UserInformation_IM_Screen_name_selected);
		Country=(TextView)findViewById(R.id.tv_UserInformation_select_country);
		State=(TextView)findViewById(R.id.tv_UserInformation_select_state);
		City=(TextView)findViewById(R.id.tv_UserInformation_select_city);
		Address=(TextView)findViewById(R.id.tv_UserInformation_address_selected);
		Website=(TextView)findViewById(R.id.tv_UserInformation_website_selected);
		change_password=(Button)findViewById(R.id.btn_edit_user_info_change_password);
		change_password.setOnClickListener(this);
		addEntry();
		user_information();
		
	}
 private void user_information(){
	    String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=profile&id=123";
	    HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("Result"+Result);
	    parsing(Result); 
	    }
 private void parsing(String value){
	 try {
		JSONObject userinfo= new JSONObject(value);
		System.out.println("userinformation"+userinfo);
		//First_Name.setText(userinfo.getString("firstname"));
		//last_Name.setText(userinfo.getString("lastname"));
		ID=userinfo.getString("id");
		UserID=userinfo.getString("user_id");
		System.out.println("ID"+ID);
		date_of_birth.setText(userinfo.getString("profile_birthdate"));
		gender.setText(userinfo.getString("profile_gender"));
		About_me.setText(userinfo.getString("about_me"));
		Langauge.setText(userinfo.getString("languages"));
		System.out.println("aboute me"+About_me);
		Ethnicity.setText(userinfo.getString("profile_ethnicity"));
		Relation.setText(userinfo.getString("relation_name"));
		JSONArray jArr = userinfo.getJSONArray("interested_in");
		System.out.println("jsonarray"+jArr);
		String IntrestedIn=jArr.getString(0);
		intrested_in.setText(IntrestedIn);
		System.out.println("intrested In"+IntrestedIn);
		Current_city.setText(userinfo.getString("current_city"));
		Home_Town.setText(userinfo.getString("hometown"));
		
		Collage.setText(userinfo.getString("university_college"));
		 High_school.setText(userinfo.getString("highschool"));
		    Log.v("fav", "fav");
		 Employer.setText(userinfo.getString("employer"));
		
		 music.setText(userinfo.getString("music"));
	     movies.setText(userinfo.getString("movies"));
	     Television.setText(userinfo.getString("television"));
		 Game.setText(userinfo.getString("games"));
		 Books.setText(userinfo.getString("books"));
		
		 activity.setText(userinfo.getString("activity"));
		 Intrest.setText(userinfo.getString("interest"));
		 whoWhouldYouLike.setText(userinfo.getString("like_to_meet"));
		    Sport_i_play.setText(userinfo.getString("sports_play"));
			Favorite_sport.setText(userinfo.getString("favorite_sports"));
			Favorite_college_Team.setText(userinfo.getString("favorite_college_team"));
			Favorite_professional_Team.setText(userinfo.getString("favorite_professional_team"));
			Favorite_Athelets.setText(userinfo.getString("favorite_athletes"));
			
		PliticalView.setText(userinfo.getString("political_view"));
		religeousView.setText(userinfo.getString("religious_view"));
		fav_Qoute.setText(userinfo.getString("fav_quote"));
		Log.v("fav", "fav");
		
		
		
		 Email.setText(userinfo.getString("email"));
		 mobilephone.setText(userinfo.getString("mobile_phone"));
		
		 Zip_code.setText(userinfo.getString("zip_code"));
		 Country.setText(userinfo.getString("country"));
		 State.setText(userinfo.getString("state"));
		 City.setText(userinfo.getString("city"));
	     Address.setText(userinfo.getString("address"));
	     Website.setText(userinfo.getString("website"));
	   
	   
	} catch (JSONException e) {
	
		e.printStackTrace();
	}
	 
 }
 
 /* Set data from database by select query*/
	public void addEntry() {
		// TODO Auto-generated method stub
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
public void onClick(View v) {
	 switch (v.getId()) {
	case R.id.btn_edit_user_info_change_password:
	    Intent i=new Intent(this, ChangePassword.class);
	    startActivity(i);
		break;

	default:
		break;
	}
	
}
}
