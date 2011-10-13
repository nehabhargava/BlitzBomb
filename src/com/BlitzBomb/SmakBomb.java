package com.BlitzBomb;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SmakBomb extends ListActivity implements OnClickListener{
	SmackAdapter smack;
	ActionItem first,newest,oldest,mostviewed,best,mostpopular,random,favorite;
	ActionItem second,today,week,month,year;
	TextView video,type,dayPost;
	String[] userName={"User 1","User 2","User 3","User 4","User 5","User 6"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smak_bomb);
		video=(TextView)findViewById(R.id.tv_smack_bomb_video);
		type=(TextView)findViewById(R.id.tv_smack_bomb_newest);
		dayPost=(TextView)findViewById(R.id.tv_smack_bomb_Todays);
	     first = new ActionItem();
		first.setTitle("Video");
		second = new ActionItem();
		second.setTitle("Photo");
		
		newest=new ActionItem();
		newest.setTitle("Newest to oldest");
		oldest=new ActionItem();
		oldest.setTitle("Oldest to Newest");
		mostviewed=new ActionItem();
		mostviewed.setTitle("Most viewed");
		best=new ActionItem();
		best.setTitle("best");
		mostpopular=new ActionItem();
		mostpopular.setTitle("Most Popular");
		random=new ActionItem();
		random.setTitle("Random");
		favorite=new ActionItem();
		favorite.setTitle("Favorite");
		
		today=new ActionItem();
		today.setTitle("Today's Post");
		week=new ActionItem();
		week.setTitle("This week post");
		month=new ActionItem();
		month.setTitle("This month's post");
		year=new ActionItem();
		year.setTitle("This year's post");
		
		type.setOnClickListener(this);
		video.setOnClickListener(this);
		dayPost.setOnClickListener(this);
		smack=new SmackAdapter(this, userName);
		setListAdapter(smack);
	}
	//adapter class bind the row layout into a list.
	private static class SmackAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		//private ArrayList<String> userName = new ArrayList<String>();
        private String[] userName;
		public SmackAdapter(Context context, String[] userName2) {
			super(context, android.R.layout.simple_list_item_1, userName2);
			this.mContext = context;
		    this.userName = userName2;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.wall_row, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view.findViewById(R.id.tv_wall_row_userName);
				holder.wallComment=(ImageView)view.findViewById(R.id.imbtn_wall_row);
				view.setTag(holder);
			  } 
			   else 
			  {
				holder = (ViewHolder) view.getTag();
			  }
			   holder.wallComment.setOnClickListener(new OnClickListener() {
			   @Override
			   public void onClick(View v) {
					DrunkStack.drunkCheck=true;
					Intent drunkAss=new Intent(v.getContext(),WriteComment.class);
					v.getContext().startActivity(drunkAss);
				}
			});
			holder.Username.setText(userName[pos]);
			return view;
		 }
        static class ViewHolder 
        {
			TextView Username;
			ImageView wallComment;
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_smack_bomb_newest:
			QuickAction qa1 = new QuickAction(v);
			qa1.addActionItem(newest);
			qa1.addActionItem(oldest);
			qa1.addActionItem(mostviewed);
			qa1.addActionItem(best);
			qa1.addActionItem(mostpopular);
			qa1.addActionItem(favorite);
			qa1.setAnimStyle(QuickAction.ANIM_REFLECT);
			qa1.show();
			break;
        case R.id.tv_smack_bomb_Todays:
        	QuickAction qa2 = new QuickAction(v);
			qa2.addActionItem(today);
			qa2.addActionItem(week);
			qa2.addActionItem(month);
			qa2.addActionItem(year);
			qa2.setAnimStyle(QuickAction.ANIM_REFLECT);
			qa2.show();
			break;
        case R.id.tv_smack_bomb_video:
        	QuickAction qa = new QuickAction(v);
			qa.addActionItem(first);
			qa.addActionItem(second);
			qa.setAnimStyle(QuickAction.ANIM_REFLECT);
			qa.show();
	        break;
		default:
			break;
		}
		
	}
}
