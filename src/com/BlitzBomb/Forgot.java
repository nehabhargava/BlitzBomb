package com.BlitzBomb;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class Forgot extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot);
	}

}
