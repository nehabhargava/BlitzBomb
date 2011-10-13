package com.BlitzBomb;

import java.util.ArrayList;
import android.app.ListActivity;import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Profile extends ListActivity implements OnClickListener{
	ImageView user,beer_mug,search,edit_profile;
	TextView Chillin_location,user_info,friends,Album,video,signout,user_name;
	private profileAdapter profile;
	ArrayList<String>userprfile=new ArrayList<String>();
	private String userName[]={"User 1","User 2","User 3",
            "User 4","User 5","User 6","User 7","User 8","User 9",
            "User 10"};
	private DbHelper myDb = new DbHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 
	 	super.onCreate(savedInstanceState);
	      /*Show screen with no default Tittle*/
         requestWindowFeature(Window.FEATURE_NO_TITLE);
          /*set orientation portrait mode*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.profile);
		Chillin_location=(TextView)findViewById(R.id.tv_profile_chillin_selected_location);
		user_info=(TextView)findViewById(R.id.tv_profile_info);
		friends=(TextView)findViewById(R.id.tv_profile_Friends);
		user_name=(TextView)findViewById(R.id.tv_profile_user_name);
		user_name.setText(Login.user_name);
		Album=(TextView)findViewById(R.id.tv_profile_Albums);
		video=(TextView)findViewById(R.id.tv_profile_Vedio);            
		signout=(TextView)findViewById(R.id.tv_profile_signout);
		edit_profile=(ImageView)findViewById(R.id.iv_profile_edit);
		edit_profile.setOnClickListener(this);
		signout.setOnClickListener(this);
		Album.setOnClickListener(this);
		video.setOnClickListener(this);
		friends.setOnClickListener(this);
		user_info.setOnClickListener(this);
		Chillin_location.setOnClickListener(this);
		
		  for(int i=0;i< userName.length;i++){
			  userprfile.add(userName[i]);
		    }
		   
	      
		profile=new profileAdapter(this, userprfile);
		 setListAdapter(profile);//Set adapter set post of user in list
	}
	
	private static class profileAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		private ArrayList<String> userName = new ArrayList<String>();

		public profileAdapter(Context context, ArrayList<String> mylist) {
			super(context, android.R.layout.simple_list_item_1, mylist);
			this.mContext = context;
			
			this.userName = mylist;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.profile_row_list , parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_new_stream_row_user_name);
				
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			
			holder.Username.setText(userName.get(pos));
			return view;
		}

		static class ViewHolder {
			TextView Username;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_profile_chillin_selected_location:
			Intent chillin=new Intent(this,Chillin.class);
			startActivity(chillin);
			
			break;
		case R.id.tv_profile_info:
			Intent userInformation=new Intent(this,UserInformation.class);
			startActivity(userInformation);
			
			break;
		case R.id.tv_profile_Friends:
			Intent friends=new Intent(this,Friend.class);
			startActivity(friends);
			
			break;
		case R.id.tv_profile_Albums:
			Intent photo=new Intent(this,Photo.class);
			startActivity(photo);
			
			break;
		case R.id.tv_profile_Vedio:
			Intent video=new Intent(this,Video.class);
			startActivity(video);
			
			break;
		case R.id.iv_profile_edit:
			Intent edit=new Intent(this,EditUserInfo.class);
			startActivity(edit);
			
			break;
		case R.id.tv_profile_signout:
			SQLiteDatabase db = myDb.getReadableDatabase();
			try{
				db.delete(DbHelper.TABLE_NAME, null, null);
				Intent logout=new Intent(this,Home.class);
				startActivity(logout);
				finish();
				}catch(Exception e){
				e.printStackTrace();
			}
			db.close();
			
			break;
		default:
			break;
		}
		
	}

}
