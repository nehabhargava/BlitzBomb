package com.BlitzBomb;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WriteComment extends Activity{
	String UserName,Comment,days,Favorite,Date,Good,Bad;
	TextView user_Name,comments,day,favorite,date,good,bad;
	ListView user_comments;
	albumAdapter lists;
	private String userName[]={"User 1","User 2","User 3",
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.write_comment);
		user_Name=(TextView)findViewById(R.id.tv_write_comment_userName);
		comments=(TextView)findViewById(R.id.tv_write_comment_comment);
		favorite=(TextView)findViewById(R.id.tv_write_comment_favorite);
		day=(TextView)findViewById(R.id.tv_write_comment_day);
		good=(TextView)findViewById(R.id.tv_write_comment_Good);
		bad=(TextView)findViewById(R.id.tv_write_comment_unlike);
		if(DrunkStack.drunkCheck){
			
		}else {
		
        Bundle Information=getIntent().getExtras();
        user_Name.setText(Information.getString("Name"));
        day.setText(Information.getString("Date")+" months ago");
        good.setText(Information.getString("Like"));
        bad.setText(Information.getString("UnLike"));
        comments.setText(Information.getString("Comment"));
		}
        user_comments=(ListView)findViewById(R.id.lv_write_comment_list);
        lists=new albumAdapter(this, userName);
        user_comments.setAdapter(lists);
     }
	//adapter class bind the row layout into a list.
	private static class albumAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		//private ArrayList<String> userName = new ArrayList<String>();
  private String[]userName;
		public albumAdapter(Context context, String[] userName2) {
			super(context, android.R.layout.simple_list_item_1, userName2);
			this.mContext = context;
			
			this.userName = userName2;
		}

		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.user_comment_list, parent,false);
				holder = new ViewHolder();
				holder.Username = (TextView) view.findViewById(R.id.tv_user_comment_list_name);
			    view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			
			holder.Username.setText(userName[pos]);
			return view;
		}

		static class ViewHolder 
		{
			TextView Username;
		}
	}
}
