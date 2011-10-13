package com.BlitzBomb;


import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Notification extends ListActivity{
	String[] notofication={"When people comment on things I post.","When people like things I post.",
			"When people dislike things I post.","When someone accepts my friend request","When I receive a friend request"
			,"When I receive a message","When people reply to the Group discussion topics I have created"
			,"When I am invited to join a Group","When people reply to the Event discussion topics I have created"
			,"When I am invited an Event","When people reply to the Party discussion topics I have created"
			,"When I am invited an Party","When someone comments on my Music Album","When someone like my Music Album"
			,"When someone dislike my Music Album","When someone comments on my Video","When someone like my Video"
			,"When someone dislike my Video","When someone comments on my Album","When someone like my Album","When someone dislike my Album"
			,"When someone comments on my Album Photo","When someone like my Album Photo","When someone dislike my Album Photo"};
	
	private NotificationAdapter notification;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		notification=new NotificationAdapter(this, notofication);
		setListAdapter(notification);
	}
	//adapter class bind the row layout into a list.
	private static class NotificationAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		//private ArrayList<String> userName = new ArrayList<String>();
          private String[] getNotification;
		  public NotificationAdapter(Context context, String[] notofication) {
			super(context, android.R.layout.simple_list_item_multiple_choice, notofication);
			this.mContext = context;
		    getNotification = notofication;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.notification_row, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_notification_label);
				
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			
			holder.Username.setText(getNotification[pos]);
			return view;
		}

		static class ViewHolder {
			TextView Username;
		}
	}

}
