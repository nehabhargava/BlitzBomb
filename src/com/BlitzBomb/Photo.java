package com.BlitzBomb;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Photo extends ListActivity implements OnClickListener{
	private albumAdapter album;
	ImageView alblum;
	ArrayList<String>album_photo=new ArrayList<String>();
	private String userName[]={"Album 1","Album 2","Album 3",
           };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		  /*hide title bar*/
	   	requestWindowFeature(Window.FEATURE_NO_TITLE);
	   	
	   	/*set orientation portrait mode*/
	   	  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.photo);
		alblum=(ImageView)findViewById(R.id.iv_Photo_AddAlbum);
		alblum.setOnClickListener(this);
		 for(int i=0;i< userName.length;i++){
			 album_photo.add(userName[i]);
		    }
		   album=new albumAdapter(this, album_photo);
		   setListAdapter(album);//Set adapter set post of user in list
	}
	
	
	//adapter class bind the row layout into a list.
	private static class albumAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		private ArrayList<String> userName = new ArrayList<String>();

		public albumAdapter(Context context, ArrayList<String> mylist) {
			super(context, android.R.layout.simple_list_item_1, mylist);
			this.mContext = context;
			
			this.userName = mylist;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.photorow, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_album_name_of_album);
				
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
	//Set action on list item click.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
       Intent album=new Intent(this,AlbumDetail.class);
       startActivity(album);
		super.onListItemClick(l, v, position, id);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.iv_Photo_AddAlbum:
			Intent Album=new Intent(this,CreateAlbum.class);
			startActivity(Album);
			break;
			
		default:
			break;
		}
		
	}
}
