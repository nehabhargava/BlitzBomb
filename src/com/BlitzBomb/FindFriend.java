package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FindFriend extends Activity implements OnClickListener{
	Button save,skip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.findfriend);
		save=(Button)findViewById(R.id.btn_invite_profile_save);
		skip=(Button)findViewById(R.id.btn_invite_profile_skip);
		save.setOnClickListener(this);
		skip.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	case R.id.btn_invite_profile_save:
		
		break;
    case R.id.btn_invite_profile_skip:
		//Intent profile=new Intent(this,Profile.class);
		//startActivity(profile);
		 //finish();
		 Intent grid= new Intent(this,BlitzBombGrid.class);
		 // Bundle name=getIntent().getExtras();	
		  Bundle name=new Bundle();
		  grid.putExtras(name);
		  startActivity(grid);
		  finish();
		break;
	default:
		break;
	}

		
	}

}
