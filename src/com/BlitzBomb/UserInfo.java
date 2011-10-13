package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserInfo extends Activity implements OnClickListener{
	Button back;
	TextView user_name;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_info);
		user_name=(TextView)findViewById(R.id.tv_user_info_User_name);
		user_name.setText(Login.user_name);
		name=Login.user_name;
		back=(Button)findViewById(R.id.btn_grid_hide_back);
		back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_grid_hide_back:
			/*Bundle user=new Bundle();
			user.putString("user_name", name);*/
			Intent grid=new Intent(this,BlitzBombGrid.class);
			//grid.putExtras(user);
			startActivity(grid);
		    break;

	    	default:
			break;
	}
		
	}

}
