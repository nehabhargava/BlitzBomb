package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Message extends Activity implements OnClickListener{
	Button sentMsg,composeMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
	sentMsg=(Button)findViewById(R.id.btn_message_sent_messages);
	composeMsg=(Button)findViewById(R.id.btn_message_compose_message);
	sentMsg.setOnClickListener(this);
	composeMsg.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	case R.id.btn_message_sent_messages:
		Intent sent=new Intent(this, SentMessage.class);
		startActivity(sent);
		break;
 case R.id.btn_message_compose_message:
	 Intent compose=new Intent(this, ComposeMessage.class);
		startActivity(compose);
		break;
	default:
		break;
	}
    
		
	}

}
