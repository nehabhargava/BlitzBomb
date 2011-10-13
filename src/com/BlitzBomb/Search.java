package com.BlitzBomb;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Search extends Activity implements OnClickListener{
	EditText name,email;
	Spinner gender,country,state,city;
	String Name,Email,username;
	Button search,cancel;
	 static final String pattern_Email = "([\\w+|\\.?]+)\\w+@([\\w+|\\.?]+)\\.(\\w{2,8}\\w?)";
	String[] gender_arr={"male","female"};
	String[] countryarray={"Select Country","Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", 
	           };
	private String[] state_arr={""};
	private String[] city_arr={"Indore","Bhopal"}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.search);
		username=Login.user_name;
		name=(EditText)findViewById(R.id.et_search_name);
		email=(EditText)findViewById(R.id.et_search_Email);
		gender=(Spinner)findViewById(R.id.sp_search_gender);
		country=(Spinner)findViewById(R.id.sp_search_country);
		state=(Spinner)findViewById(R.id.sp_search_state);
		city=(Spinner)findViewById(R.id.sp_search_city);
		search=(Button)findViewById(R.id.btn_search_Serach);
		cancel=(Button)findViewById(R.id.btn_search_cancel);
		
		search.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
		// set list of gender in spinner through adapter.
		ArrayAdapter<String> privacy_adapter=new 
		                                     ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gender_arr);
		privacy_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		gender.setAdapter(privacy_adapter);
		
		
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, countryarray);
        country.setAdapter(adapter);
        ArrayAdapter state = new ArrayAdapter(this,android.R.layout.simple_spinner_item, state_arr);
        this.state.setAdapter(state);
        ArrayAdapter city = new ArrayAdapter(this,android.R.layout.simple_spinner_item, city_arr);
        this.city.setAdapter(city);
       
		
	}
	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	case R.id.btn_search_Serach:
		Intent member=new Intent(this,SearchMember.class);
		startActivity(member);
		/*if (validation() && Pattern.matches(pattern_Email, Email)) {
			Intent member=new Intent(this,SearchMember.class);
			startActivity(member);
		}*/
		break;
    case R.id.btn_search_cancel:
    	Bundle user=new Bundle();
		user.putString("user_name", username);
		Intent back=new Intent(this,BlitzBombGrid.class);
		back.putExtras(user);
		startActivity(back);
		finish();
		break;
	
    default:
		break;
	}
	}
	private boolean validation() {
		Email=email.getText().toString().trim();
		
		if(!Email.equals("")){
			Toast.makeText(this, "Fill correct email address", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
