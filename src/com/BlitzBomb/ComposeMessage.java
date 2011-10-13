package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ComposeMessage extends Activity implements OnClickListener {
	Button SentMsg,Inbox,Cancel,Send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compose_message);
    SentMsg=(Button)findViewById(R.id.btn_compose_message_sent_messages);
	Inbox=(Button)findViewById(R.id.btn_compose_message_inbox);
	SentMsg.setOnClickListener(this);
	Inbox.setOnClickListener(this);
	Cancel=(Button)findViewById(R.id.btn_compose_message_cancel);
	Cancel.setOnClickListener(this);
	Send=(Button)findViewById(R.id.btn_compose_message_send);
	Send.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_compose_message_inbox:
			Intent inbox=new Intent(this, Message.class);
			startActivity(inbox);
			break;
		case R.id.btn_compose_message_sent_messages: 
			Intent sent=new Intent(this, SentMessage.class);
			startActivity(sent);
			break;
		case R.id.btn_compose_message_cancel: 
			Intent cancel=new Intent(this, Message.class);
			startActivity(cancel);
			break;
		case R.id.btn_compose_message_send:
			
			break;
		default:
			break;
		}
	}
}