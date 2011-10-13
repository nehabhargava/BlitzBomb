package com.BlitzBomb;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyVideo extends ListActivity {
	ArrayList<String>videoCategory=new ArrayList<String>();
	private String video_category[]={"Education","Comedy","Horror"};
	private VideoAdapter video;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_video);
		for(int i=0;i<video_category.length;i++){
			videoCategory.add(video_category[i]);
		}
		video=new VideoAdapter(this,videoCategory);
		setListAdapter(video);
	}

	private static class VideoAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		private ArrayList<String> userName = new ArrayList<String>();

		public VideoAdapter(Context context, ArrayList<String> mylist) {
			super(context, android.R.layout.simple_list_item_1, mylist);
			this.mContext = context;
			
			this.userName = mylist;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.my_video_row, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_my_video_video_name);
				
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		 Intent Play_video=new Intent(this,PlayVideo.class);
	       startActivity(Play_video);
	   
		 
		super.onListItemClick(l, v, position, id);
	}

	
	
}
