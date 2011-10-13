package com.BlitzBomb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import com.network_connection.HttpConnection;

public class NewsStream extends ListActivity implements OnClickListener,OnTabChangeListener,OnLongClickListener{
	ImageView user_post,Cross, AddLink;
	TextView user_post_info;
	EditText post_commnet,add_link,share;
	String comment;
	private Geocoder gc;
	TabHost tabs ;
	Button Chillin;
	InputMethodManager inputMgr; 
	private newsStreamAdapter new_stream;
	ArrayList<String>user_Name=new ArrayList<String>();
	private String userName[]={"User 1","User 2","User 3",
            "User 4","User 5","User 6","User 7","User 8","User 9",
            "User 10"};
	
	/*
	 * Wall related array list.
	 */
	    ArrayList<JSONObject>posts=new ArrayList<JSONObject>();
	    List<String> displayName=new ArrayList<String>();
	    List<String> body=new ArrayList<String>();
	    List<String> status=new ArrayList<String>();
	    List<String> like=new ArrayList<String>();
	    List<String> dislike=new ArrayList<String>();
	    ArrayList<String> StatusDate=new ArrayList<String>();
	    ArrayList<Integer> Date=new ArrayList<Integer>();
	/*
	 *
	 */
	    ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		/*Show screen with no default Tittle*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*set orientation portrait mode*/
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.news_stream);
		inputMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	     user_post=(ImageView)findViewById(R.id.iv_News_Stream_user_post);
	   
	    share=(EditText)findViewById(R.id.et_News_Stream_share);
	   
	    share.setOnLongClickListener(this);
	  
	    Chillin=(Button)findViewById(R.id.btn_News_stream_Chillin_location);
	    gc = new Geocoder(this); //create new geocoder instance
	    Chillin.setOnClickListener(this);
	   /*for(int i=0;i< userName.length;i++){
	    	user_Name.add(userName[i]);
	    }*/
	    ListView list=getListView();
	    list.setItemsCanFocus(true);
	    progressDialog = ProgressDialog.show(NewsStream.this, "Getting data", "Loading...");
	    new AddStringTask().execute();
	   
     }
	class AddStringTask extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			getWallPost();
			 return (null);
		}

		@Override
		protected void onProgressUpdate(String... item) {
			
		}

		@Override
		protected void onPostExecute(Void unused) {
			 new_stream = new newsStreamAdapter(NewsStream.this,displayName,like,dislike,body,Date,status);
		        setListAdapter( new_stream);//Set adapter in gird with icons
		        
		        progressDialog.dismiss();
		}    
	}
	private void getWallPost(){
		String link = "http://10.10.10.122/blitzbomb/arn/web_welcome.php?user=38&format=json";
		HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("response"+Result);
	    try {
	    	JSONObject wallPost=new JSONObject(Result);
	    	JSONArray wallPostArray=wallPost.getJSONArray("posts");
	    	System.out.println("wallPostArray"+wallPostArray);
	    	for (int i = 0; i < wallPostArray.length(); i++) {
				
	    		posts.add(wallPostArray.getJSONObject(i).getJSONObject("post"));
	    	}
	    	
	    	for (int i = 0; i <posts.size(); i++) 
	    	{
	    		JSONObject getValue=posts.get(i);
	    		displayName.add(getValue.getString("displayname"));
	    		body.add(getValue.getString("body"));
	    		like.add(getValue.getString("like_count"));
	    		dislike.add(getValue.getString("dislike_count"));
	    		StatusDate.add(getValue.getString("status_date"));
	    		status.add(getValue.getString("status"));
			}
	    	System.out.println("StatusDate"+StatusDate);
	    	
	    	
		} catch (Exception e) {
		
		}
		try {
			
		
		for (int i = 0; i < StatusDate.size(); i++) {
			String[] dateData=StatusDate.get(i).split(" ");
			String getDate=dateData[0].replace("-", "/");
		System.out.println("getDategetDate"+getDate);
    	SimpleDateFormat curFormater = new SimpleDateFormat("yyyy/MM/dd"); 
		Date date= curFormater.parse(getDate); 
		DateTime startDate = new DateTime(date); 
		DateTime endDate = new DateTime(); //current date
		Days day_diff = Days.daysBetween(startDate, endDate);
		int day_list=day_diff.getDays();
		if(day_list>=30){
			Months diff = Months.monthsBetween(startDate, endDate);
			int month=diff.getMonths();
			Date.add(month);
		}else {
			Date.add(day_list);
		}
		
		
		
		System.out.println("DateDateDateDateDate"+Date);
		System.out.println("day_listday_list"+day_list);
		
     }
		} catch (Exception e) {
		
		}
		System.out.println("posts"+posts);
		System.out.println("displayName"+displayName);
		System.out.println("bodybody"+body);
	   }
	
	@Override
	public void onTabChanged(String tabId) {
		
		for(int i=0;i<tabs.getTabWidget().getChildCount();i++)
        {
			tabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FDEEF4"));
        } 
				
		tabs.getTabWidget().getChildAt(tabs.getCurrentTab()).setBackgroundColor(Color.parseColor("#EBDDE2"));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
/*	 case R.id.et_news_stream_postComments_tab:
			 
		Cross.setVisibility(1);
		//  Share.setVisibility(1);
			//AddLink.setVisibility(4);
			break;
		
		case R.id.iv_new_stream_cross:
			comment=post_commnet.getText().toString().trim();
			
			if(!comment.equals("")){
				inputMgr.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				post_commnet.setText(" ");
				post_commnet.setHint("Post Something");
			}else {
				inputMgr.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
		
			post_commnet.setHint("Post Something");
			   
			}
			
				
				//AddLink.setVisibility(4);
			break;
		case R.id.et_news_stream_postComments_add_link:
			
			AddLink.setVisibility(1);
				
				
			break; */
          case R.id.btn_News_stream_Chillin_location:
			
			Intent map= new Intent(this,Chillin.class);
			startActivity(map);
				
				
			break;
         case R.id.et_News_Stream_share:
	   
	 
       break;
           default:
		   break;
		}
		
		
	}
	
	private static class newsStreamAdapter extends ArrayAdapter<String> {
		private Context mContext;
		
		private List<String> userName = new ArrayList<String>();
		private List<String> likes = new ArrayList<String>();
		private List<String> unlike = new ArrayList<String>();
		private List<String> commentBody = new ArrayList<String>();
		private ArrayList<Integer>GetDate=new ArrayList<Integer>();
		private List<String>getStatus=new ArrayList<String>();
		private ArrayList<Boolean> check=new ArrayList<Boolean>();
		public newsStreamAdapter(Context context, List<String> displayName, List<String> like, List<String> dislike, List<String> body, ArrayList<Integer> date, List<String> status) {
			super(context, android.R.layout.simple_list_item_1, displayName);
			this.mContext = context;
			
			userName = displayName;
			likes=like;
			unlike=dislike;
			commentBody=body;
			GetDate=date;
			getStatus=status;
			for(int i = 0; i< this.getCount(); i++)
			{
				check.add(i, false);
			}
		}

		@Override
		public View getView(final int pos, View view, ViewGroup parent) {
			final ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.new_stream_row, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_new_stream_row_user_name);
				
				holder.Like=(TextView)view.findViewById(R.id.tv_new_stream_row_Good);
				holder.Unlike=(TextView)view.findViewById(R.id.tv_new_stream_row_unlike);
				holder.Body=(TextView)view.findViewById(R.id.tv_new_stream_row_body);
				holder.day=(TextView)view.findViewById(R.id.tv_new_stream_row_day);
				holder.status=(TextView)view.findViewById(R.id.tv_new_stream_row_Status);
				holder.Comment = (ImageView) view.findViewById(R.id.imbtn_new_stream_row);
				holder.Comment.setOnClickListener(mOnCommentClickListener);
				holder.Username.setOnClickListener(mOnUsernameClickListener);
				
				//holder.et_comment=(EditText)view.findViewById(R.id.et_new_stream_row_comment);
				holder.rl_comment=(RelativeLayout)view.findViewById(R.id.fl_new_stream_row_comment);
				//holder.btn_comment=(Button)view.findViewById(R.id.btn_new_stream_row_comment);
				//holder.et_comment.setOnClickListener(mOnEtCommentClickListener);
				
				//String s=holder.et_comment.getText().toString();
				//System.out.println("ssssssssssssss"+"           "+s);
			  
				
				/*holder.et_comment.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
							holder.check.isChecked();
							check.set(pos, true);
						
						    //holder.btn_comment.requestFocusFromTouch();
					}
				});*/
				
				/*holder.btn_comment.setOnClickListener(new OnClickListener() {
				
					@Override
					public void onClick(View v) {
					
						holder.et_comment.setCursorVisible(true);
					
					}
				});*/
			    view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
		
			holder.Username.setText(userName.get(pos));
			holder.Like.setText("("+likes.get(pos)+")");
			holder.Unlike.setText("("+unlike.get(pos)+")");
			holder.Body.setText(commentBody.get(pos));
			String s=GetDate.get(pos).toString();
		    holder.day.setText(s +" months ago" );
		    holder.status.setText(getStatus.get(pos));
	        holder.Comment.setId(pos);
		    
		  //we need to update adapter once we finish with editing
		           /*  holder.et_comment.setOnFocusChangeListener(new OnFocusChangeListener() 
		             {
		                   public void onFocusChange(View v, boolean hasFocus) 
		                   {
		                    if (!hasFocus)
		                      {
		   	                        final int position = v.getId();
		   	                        System.out.println("position"+position);
		                            final EditText Caption = (EditText) v;
		    	                    // myItems.get(position).caption = Caption.getText().toString();
		   	                  }
		   	                }
		   	         });*/
		    
			return view;
		}
		private OnClickListener mOnUsernameClickListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	String url = (String) v.getTag();
	        	Intent myIntent = new Intent(v.getContext(), DrunkAss.class);
	        	  
	        	  v.getContext().startActivity(myIntent);
	           System.out.println("pos"+url);
	        }
	    };
	    private OnClickListener mOnCommentClickListener = new OnClickListener() {
	    @Override
	        public void onClick(View v) 
	          {
	        	DrunkStack.drunkCheck=false;
	        	final int position = v.getId();
	        	System.out.println("position"+position);
	        	String user_name=userName.get(position);
	        	String like=likes.get(position);
	        	String Unlike=unlike.get(position);
	        	String CommentText=commentBody.get(position);
	        	String CommentDate=GetDate.get(position).toString();
	        	Bundle commentInfo=new Bundle();
	        	commentInfo.putString("Name", user_name);
	        	commentInfo.putSerializable("Like", like);
	        	commentInfo.putString("UnLike", Unlike);
	        	commentInfo.putString("Comment", CommentText);
	        	commentInfo.putString("Date", CommentDate);
	        	Intent myIntent = new Intent(v.getContext(),WriteComment.class);
	        	myIntent.putExtras(commentInfo);
	        	v.getContext().startActivity(myIntent);
	          }
	    };
		
	      /*private OnClickListener mOnEtCommentClickListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	        	String url = (String) v.getTag();
	        	Intent myIntent = new Intent(v.getContext(), EditUserInfo.class);
	        	  
	        	  v.getContext().startActivity(myIntent);
	           System.out.println("pos"+url);
	       
	        }
	    }; */
		}
	      static class ViewHolder{
			TextView Username,Like,Unlike,Body,day,status;
			ImageView Comment;
		   
		  
            RelativeLayout rl_comment;
            String ID;
	    }
		@Override
		public boolean onLongClick(View v) {
			  Intent i=new Intent(NewsStream.this,Share.class);
		       startActivity(i);
			return false;
		}
	}


