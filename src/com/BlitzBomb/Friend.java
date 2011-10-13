package com.BlitzBomb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Friend extends ListActivity implements OnClickListener{
	private String[]search_category={"Search By name",
			                         "Serach By City",
			                         "Search By school",
			                         "Search By Intrest"};
	ArrayList<String>userprfile=new ArrayList<String>();
	private String userName[]={"User 1","User 2","User 3",
            "User 4","User 5","User 6","User 7","User 8","User 9",
            "User 10"};
	Spinner search_name;
	private friendAdapter friend;
	ImageView option,findFriend;
	  ActionItem comment;
	  ActionItem messgae;
	  ActionItem remove;
//String cooment="add comment";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*set orientation portrait mode*/
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	   setContentView(R.layout.friend);
	   findFriend=(ImageView)findViewById(R.id.iv_friend_find);
	   findFriend.setOnClickListener(this);
	   search_name=(Spinner)findViewById(R.id.sp_friend_category_search);

	   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,search_category);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    search_name.setAdapter(adapter);
		    comment = new ActionItem();
			comment.setTitle("add comment");
			messgae = new ActionItem();
			messgae.setTitle("send Message");
			remove = new ActionItem();
			remove.setTitle("Remove friend");
		    for(int i=0;i< userName.length;i++)
		    {
			  userprfile.add(userName[i]);
		    }
		   
		 comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Friend.this,DrunkAss.class);
				startActivity(i);
			}
		});
		 friend=new friendAdapter(this,userprfile);
		 setListAdapter(friend);//Set adapter set post of user in list
		
		 
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	private static class friendAdapter extends ArrayAdapter<String> {
		private Context mContext;
		ActionItem comment = new ActionItem();
		
		ActionItem messgae = new ActionItem();
		
		ActionItem remove = new ActionItem();
		
		private ArrayList<String> userName = new ArrayList<String>();

		public friendAdapter(Context context, ArrayList<String> mylist) {
			super(context, android.R.layout.simple_list_item_1, mylist);
			this.mContext = context;
			
			this.userName = mylist;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.friendlistrow, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view.findViewById(R.id.tv_friends_username);
				holder.option=(ImageView)view.findViewById(R.id.iv_friend_option_image);
				holder.option.setOnClickListener(mOnrequestClickListener);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			/* */
			holder.Username.setText(userName.get(pos));
			
			
			
			return view;
		}
		private OnClickListener mOnrequestClickListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	        	comment.setTitle("add comment");
	        	messgae.setTitle("send Message");
	        	remove.setTitle("Remove friend");
	        	 QuickAction qa = new QuickAction(v);
	 			
	 			qa.addActionItem(comment);
	 			qa.addActionItem(messgae);
	 			qa.addActionItem(remove);
	 			qa.setAnimStyle(QuickAction.ANIM_REFLECT);
	 			qa.show();
	        }
	    };
		 static class ViewHolder extends Activity{
			TextView Username;
			ImageView option;
			
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
	   
		 /* QuickAction qa = new QuickAction(v);
			
			qa.addActionItem(comment);
			qa.addActionItem(messgae);
			qa.addActionItem(remove);
			qa.setAnimStyle(QuickAction.ANIM_REFLECT);
			qa.show();*/
		super.onListItemClick(l, v, position, id);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_friend_find:
			
			Intent findFriend=new Intent(this,InviteFriend.class);
		      startActivity(findFriend);
			break;

		default:
			break;
		}
		
	}
}
