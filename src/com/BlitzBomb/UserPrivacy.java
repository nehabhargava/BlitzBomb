package com.BlitzBomb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserPrivacy extends Activity{
	ListView checkedList;
	TextView block_member;
	UserPrivacyAdapter privacy;
	private String[] Recent_Activity={"New photo albums","Updating my profile","Posting to someone's profile"
			,"Posting a status update to your own profile","Changing your photo","Updating my status","New videos"
			,"Chilling At","Checkin At","Take a Hit"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
		setContentView(R.layout.user_privacy);
		checkedList=(ListView)findViewById(R.id.iv_userPrivacy_checkedList);
		block_member=(TextView)findViewById(R.id.tv_user_privacy_Adding_person);
		block_member.setMovementMethod(ScrollingMovementMethod.getInstance());
		privacy=new UserPrivacyAdapter(this, Recent_Activity);
		checkedList.setAdapter(privacy);
	}
	//adapter class bind the row layout into a list.
	private static class UserPrivacyAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		//private ArrayList<String> userName = new ArrayList<String>();
          private String[] getNotification;
		  public UserPrivacyAdapter(Context context, String[] notofication) {
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
