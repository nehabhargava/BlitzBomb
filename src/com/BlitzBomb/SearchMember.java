package com.BlitzBomb;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.BlitzBomb.ImageThreadLoader.ImageLoadedListener;
import com.network_connection.HttpConnection;

public class SearchMember extends ListActivity{
	
	String url,Result,user_id,user_name;
	String index;
	static String id;
	//private memberAdapter friend;
	private Thread browse;
	private ProgressDialog dialog;
	ArrayList<String> displayname=new ArrayList<String>();
	static ArrayList<String> age=new ArrayList<String>();
	ArrayList<String> userid=new ArrayList<String>();
	ArrayList<String> gender=new ArrayList<String>();
	 ArrayList<String> getImage=new ArrayList<String>();
	ArrayList<String> Photoid=new ArrayList<String>();
	ArrayList<Bitmap> Image_url_data=new ArrayList<Bitmap>();
	Bitmap bmImg;
	
	
	@Override            
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.search_member);
		
		
		url=getString(R.string.url);
		index=getString(R.string.Index);
       // System.out.println("url"+url);
       // System.out.println("getImage"+getImage);
		new AsynchMember().execute();
		 
        // browse_member();
		
        //friend=new memberAdapter(this,displayname,age,userid,gender,getImage);
			//setListAdapter(friend);
      
        //setListAdapter(new MediaItemAdapter(this, R.layout.search_member_row, getImage,displayname,age,userid,gender));
	}
     /*private void browsemember() {
	 new ProgressDialog(this);
		dialog = ProgressDialog.show(this, "Connecting",
				"Wait While Connecting");
		dialog.setIcon(R.drawable.waiting);
		browse = new Thread() {
			@Override
			public void run() {
				Log.v("thred run", "thread started Login()");
				  // browse_member();
					
				browseImage();
				
			}     
		};  
		browse.start();                          
} */
private void browse_member()  {

	//http://10.10.10.122/blitzbomb/arn/index.php?mode=android&action=browsemember
	  String link = url+index+"&action=browsemember";
	//String link="http://10.10.10.32/blitzbombb/and/testing.php?user=87&format=json";
		HttpConnection httpconnection=new HttpConnection (link);
			Result=httpconnection.httpConnection();
		    System.out.println("response"+Result);
	     try 
	     {       
		     JSONArray jArray = new JSONArray(Result);
              System.out.println("response array"+jArray);
                          
	          for (int i = 0; i < jArray.length(); i++) {        
		
		
		    displayname.add(jArray.getJSONObject(i).getString("displayname").toString());
		    age.add(jArray.getJSONObject(i).getString("age").toString());
		    userid.add(jArray.getJSONObject(i).getString("userId").toString());
		    Photoid.add(jArray.getJSONObject(i).getString("photo_id").toString());
	        gender.add(jArray.getJSONObject(i).getString("gender").toString());
		
	                     }
	     } catch (JSONException e) {
	
	       e.printStackTrace();
         }
	    
	 // browsemember();  
	  browseImage();
  }
private void browseImage(){
	
	for(int i=0;i<Photoid.size();i++){
		String link="http://10.10.10.122/blitzbomb/arn/profile.php?mode=android&action=image&photoid="+Photoid.get(i);
		HttpConnection httpconnection=new HttpConnection (link);
		Result=httpconnection.httpConnection();
	     //System.out.println("response"+Result);
		  try{
			  JSONObject image=new JSONObject(Result);
			  getImage.add(image.getString("profile_image"));
			  System.out.println("image"+getImage);
		} catch (Exception e) {
		
		}         
		    
	}
	
	System.out.println("list image"+getImage);
	
} 


	//adapter class bind the row layout into a list.
/* private static class memberAdapter extends ArrayAdapter<String> {
		 private final static String TAG = "MediaItemAdapter"; 
		private Context mContext;
		     private Activity activity;
		 private static LayoutInflater inflater=null;
		private ArrayList<String> userName = new ArrayList<String>();
		private ArrayList<String> Age = new ArrayList<String>();
		private ArrayList<String> User_id = new ArrayList<String>();
		private ArrayList<String> image=new java.util.ArrayList<String>();
		private ArrayList<String> Gender=new java.util.ArrayList<String>();
		public ImageLoader imageLoader; 
		
		public memberAdapter(Context context, ArrayList<String> mylist, ArrayList<String> age, ArrayList<String> userid, ArrayList<String> gender, ArrayList<String> getImage ) {
			 
			
			super(context, android.R.layout.simple_list_item_1, mylist);
			this.mContext = context;
			this.Age=age;
			this.User_id=userid;
			this.userName = mylist;
			this.image=getImage;
			this.Gender=gender;
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     
			imageLoader = new ImageLoader(context.getApplicationContext());
		} 
		
        //Function create the view of row that bind into list.And it inflate the xml layout. 
		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			final ViewHolder holder;
			try{
			
				if (view == null) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.search_member_row, parent,
						false);
				holder = new ViewHolder();
				holder.Username = (TextView) view
						.findViewById(R.id.tv_search_member_row_username);
				holder.age = (TextView) view
				.findViewById(R.id.tv_search_member_row_age);
				holder.member_image=(ImageView)view.findViewById(R.id.iv_search_member_row_user_image);
				holder.friend = (TextView) view.findViewById(R.id.tv_search_member_row_send_request);
				holder.gender=(TextView)view.findViewById(R.id.tv_search_member_row_gender);
				holder.cancelRequest=(TextView)view.findViewById(R.id.tv_search_member_row_cancel_request);
				holder.friend.setOnClickListener(mOnrequestClickListener);
				view.setTag(holder);
			}  
			else 
			{   
				holder = (ViewHolder) view.getTag();
			}             
		    holder.Username.setText(userName.get(pos));
			holder.age.setText(Age.get(pos));
			holder.gender.setText(Gender.get(pos));
			holder.friend.setTag(User_id.get(pos));//used for set
			Log.v("get view", "get vie");
			
			holder.member_image.setTag(image.get(pos));
			Log.v("get view", "get vie");
			System.out.println("image"+image.get(pos));
		// imageLoader.DisplayImage(image.get(pos), mContext, holder.member_image);
		
			}
			catch (ArrayIndexOutOfBoundsException e) {
				e.getStackTrace();
				// TODO: handle exception
			}
			return view;        	
		}  */
 
 
 public class MediaItemAdapter extends ArrayAdapter<String> {
	   private final static String TAG = "MediaItemAdapter";
	   private int resourceId = 0;
	   private LayoutInflater inflater;
	   private Context context;
	   private ImageThreadLoader imageLoader = new ImageThreadLoader();
		private ArrayList<String> userName;
		private ArrayList<String> age;
		private ArrayList<String> userid;
		private ArrayList<String> gender;
	 public MediaItemAdapter(Context context, int resourceId, List<String> mediaItems,ArrayList<String> userName,ArrayList<String> age, ArrayList<String> userid,
					ArrayList<String> gender) {
		    super(context, 0, mediaItems);
	 	    this.resourceId = resourceId;
		    inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 	    this.context = context;
		    this.userName=userName;
	        this.age=age;
	 	    this.userid=userid;
	 	    this.gender=gender;
	 	  }
	 
	 	@Override
		  public View getView(int position, View convertView, ViewGroup parent) {
	 		 final ViewHolder holder;
		    View view;
	   
		 
		    view = inflater.inflate(resourceId, parent, false);
		    holder = new ViewHolder();
		    try {
		     // textTitle = (TextView)view.findViewById(R.id.text);
		      holder.member_image = (ImageView)view.findViewById(R.id.iv_search_member_row_user_image);
	          holder.Username=(TextView)view.findViewById(R.id.tv_search_member_row_username);
		      holder.gender=(TextView)view.findViewById(R.id.tv_search_member_row_gender);
		      holder.age=(TextView)view.findViewById(R.id.tv_search_member_row_age);
		      holder.friend = (TextView) view.findViewById(R.id.tv_search_member_row_send_request);
		      holder.friend.setOnClickListener(mOnrequestClickListener);
		      // Log.e(TAG, "Your layout must provide an image and a text view with ID's icon and text.", e);
		    // throw e;
		    }
		    catch (Exception e) {
				
			}
		 
	 	    String item = getItem(position);
		    Bitmap cachedImage = null;
		    try {
		    cachedImage = imageLoader.loadImage(item, new ImageLoadedListener() {
	        public void imageLoaded(Bitmap imageBitmap) {
	    	  holder.member_image.setImageBitmap(imageBitmap);
	 	     
	    	  notifyDataSetChanged();                }
		      });
	 	    } catch (MalformedURLException e) {
		      Log.e(TAG, "Bad remote image URL: " + item, e);
		    }
		 
	 	 holder.Username.setText(userName.get(position));
		 holder.age.setText(age.get(position));
		 holder.gender.setText(gender.get(position));
		    if( cachedImage != null ) {
		    	 holder.member_image.setImageBitmap(cachedImage);
		    }
	 	 
		    return view;
	 	  }
	 
	                             
		private OnClickListener mOnrequestClickListener = new OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	            id = (String) v.getTag();
               System.out.println("id"+id);
	        //ArrayList<String> url = User_id;                              
	        	//System.out.println("pos"+url);
	        	final AlertDialog builder = new AlertDialog.Builder(v.getContext()).create();
				builder.setTitle("Message");
	    		builder.setMessage("Are you Sure you want to add this member as a friend");
	    		builder.setButton("YES", new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int row) {
  	    	  //  System.out.println("row"+id);
  	    	    
  	    	        sendrequest();
  	    	     
	    		    } });
	    		 builder.setButton2("No",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						builder.cancel();
					}                         
				});
				builder.show();
	        }
	    };
	class ViewHolder {
	TextView Username,friend,age,gender,cancelRequest;
	ImageView member_image;
		
		}
	}  
	private static void sendrequest(){
		String link="http://10.10.10.122/blitzbomb/arn/friend.php?mode=android&action=add_frnd&userid=87&friendid="+id;
		System.out.println("link"+link);
		HttpConnection httpconnection=new HttpConnection (link);
	    String Result=httpconnection.httpConnection();   
	    System.out.println("response"+Result);
	    try {
			JSONObject request=new JSONObject(Result);
			System.out.println("reqjuest"+request);
			
			
		} catch (Exception e) {
			
		}
	}	
	
 class AsynchMember extends AsyncTask<Void , Void, Void>{
   
	 @Override
	 protected void onPostExecute(Void result) {
	
		 setListAdapter(new MediaItemAdapter(SearchMember.this, R.layout.search_member_row, getImage,displayname,age,userid,gender));
	 }
	@Override
	protected Void doInBackground(Void... params) {
		browse_member();
		
		return null;
	}
	 
 }
	
}