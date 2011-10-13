package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SentMessage extends Activity implements OnClickListener{
	Button Inbox,ComposeMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sent_message);
Inbox=(Button)findViewById(R.id.btn_sent_message_inbox);
ComposeMsg=(Button)findViewById(R.id.btn_sent_message_compose_message);
Inbox.setOnClickListener(this);
ComposeMsg.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_sent_message_compose_message:
			Intent compose=new Intent(this, ComposeMessage.class);
			startActivity(compose);
			break;
		case R.id.btn_sent_message_inbox:
			Intent inbox=new Intent(this, Message.class);
			startActivity(inbox);
			
			break;
		default:
			break;
		}
		
	}
}