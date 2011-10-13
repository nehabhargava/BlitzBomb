package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Share extends Activity implements OnClickListener{
	Button share;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
	share=(Button)findViewById(R.id.btn_share);
	share.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_share:
			Intent i=new Intent(this, NewsStream.class);
			startActivity(i);
				break;

		default:
			break;
		}
		
	}

}
