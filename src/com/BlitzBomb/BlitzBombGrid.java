package com.BlitzBomb;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONObject;
import com.network_connection.HttpConnection;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BlitzBombGrid extends Activity implements OnClickListener{
	GridView gridview;
	TextView Index,user_name;
	ImageView member_image;
	String username;
	String url;//http://10.10.10.67/blitzbomb/index.php?
	String index,Profile,User_id;
	String Image;
	static int pos;
	public static String ID = "user_id";
	public static String USERNAME = "name";
	DbHelper myDb;
	RelativeLayout User_header;
	private GridAdapter ImageList;
	ArrayList<String> iconName=new ArrayList<String>();
	ArrayList<Integer> iconImage=new  ArrayList<Integer>(); 

    /** Called when the activity is first created. */
	 private Integer[] Image_icon = {
	            R.drawable.newsstream, R.drawable.profile,
	            R.drawable.notification, R.drawable.friends,
	            R.drawable.messages, R.drawable.photos,
	            R.drawable.chillin, R.drawable.search,
	            R.drawable.quotes, R.drawable.drunkas,
	            R.drawable.creepers, R.drawable.drunkstack,
	            R.drawable.highnotes, R.drawable.crazydrunk,
	            R.drawable.smackbomb, R.drawable.wtf,
	            R.drawable.fail, R.drawable.priceless
	         };      
	 	 
  private String heading[]={"News Stream","Profile","Notification",
          "Friends","message","Photo","Chillin","serach","Quotes",
          "Drunk Ass","Creepers","Drunk Stack","High Notes",
          "Crazy Drunk","smak Bomb","WTF","Fail","Fanny shit"};
 
 @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        /*Show screen with no default Tittle*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.grid);
        myDb = new DbHelper(this);     
        url=getString(R.string.url);
		index=getString(R.string.Index);
		Profile=getString(R.string.profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);/*set orientation portrait mode*/
        Index=(TextView)findViewById(R.id.tvTitleTitleBarExternal);
          //hide=(RelativeLayout)findViewById(R.id.rr_gride_hide_bar);
        addButton();
       /*
        Bundle name=getIntent().getExtras();
        username=name.getString("user_name");
        User_id=name.getString("user_ID");
        */
        User_header=(RelativeLayout)findViewById(R.id.rr_grid_titleBar);
        User_header.setOnClickListener(this);
        user_name=(TextView)findViewById(R.id.tv_Grid_tittle);
        member_image=(ImageView)findViewById(R.id.iv_grid_user_small);
        user_name.setOnClickListener(this);
        user_name.setText(username);
        image();
        
        gridview=(GridView)findViewById(R.id.gv_Grid);
        for(int i=0;i<heading.length;i++){
        	iconName.add(heading[i]);
        }
        for(int i=0;i<Image_icon.length;i++){
        	iconImage.add(Image_icon[i]);
        }
        ImageList = new GridAdapter(this, iconName, iconImage);
        gridview.setAdapter( ImageList);//Set adapter in gird with icons
        
        //set item click in grid view.
        gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,long arg3) {
				parent.getAdapter().getItem(0);
				pos=position;
				switch (position) {
				 case 0:
					Intent newsStream=new Intent(BlitzBombGrid.this,NewsStream.class);
					startActivity(newsStream);
					break;
				 case 1:
					Intent profile=new Intent(BlitzBombGrid.this,Profile.class);
					startActivity(profile);
					break;
				 case 2:
					Intent notification=new Intent(BlitzBombGrid.this,Notification.class);
					startActivity(notification);
					break;
				 case 3:
					Intent friend=new Intent(BlitzBombGrid.this,Friend.class);
					startActivity(friend);
					break;
				 case 4:
				 	Intent message=new Intent(BlitzBombGrid.this,Message.class);
					startActivity(message);
					break;
				 case 5:
					Intent photo=new Intent(BlitzBombGrid.this,Photo.class);
					startActivity(photo);
					break;
				 case 6:
					Intent chillin=new Intent(BlitzBombGrid.this,Chillin.class);           
					startActivity(chillin);
					break;
				 case 7:
					Intent search=new Intent(BlitzBombGrid.this,Search.class);
					startActivity(search);
					break;
				 case 8:
					Intent Quotes=new Intent(BlitzBombGrid.this,Setting.class);
					startActivity(Quotes);
					break;
				 case 9:
					Intent drunk_Ass=new Intent(BlitzBombGrid.this,DrunkAss.class);
					startActivity(drunk_Ass);
					break;
				 case 10:
					Intent creepers=new Intent(BlitzBombGrid.this,Creepers.class);
					startActivity(creepers);
					break;
				 case 11:
					Intent drunk_stack=new Intent(BlitzBombGrid.this,DrunkStack.class);
					startActivity(drunk_stack);
					break;
				 case 12:
					Intent high_notes=new Intent(BlitzBombGrid.this,HighNotes.class);
					startActivity(high_notes);
					break;
				 case 13:
					Intent crazy_drunk=new Intent(BlitzBombGrid.this,CrazyDrunk.class);
					startActivity(crazy_drunk);
					break;
				 case 14:
					Intent smak_bomb=new Intent(BlitzBombGrid.this,SmakBomb.class);
					startActivity(smak_bomb);
					break;
				 case 15:
					Intent wtf=new Intent(BlitzBombGrid.this,WTF.class);
					startActivity(wtf);
					break;
				 case 16:
					Intent fail=new Intent(BlitzBombGrid.this,Fail.class);
					startActivity(fail);
					break;
				 case 17:
					Intent priceless=new Intent(BlitzBombGrid.this,FunnyShit.class);
					startActivity(priceless);
					break;
					
				 default:
					break;
				}
			}       
		});
		}

 void image(){
	String link=url+Profile+"&action=image&userid="+User_id;
	 HttpConnection httpconnection=new HttpConnection (link);
	  String Result=httpconnection.httpConnection();   
	     System.out.println("response"+Result);
	     try {
			JSONObject image=new JSONObject(Result);
		   Image=image.getString("profile_image");
			System.out.println("image"+Image);
			
		} catch (Exception e) {
		
		}
	 URL myFileUrl =null;
		try {
		myFileUrl= new URL(Image);
		} catch (MalformedURLException e) {
		    
		e.printStackTrace();
		}
		try 
		{
		HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
		conn.setDoInput(true);
		conn.connect();
	
		InputStream is = conn.getInputStream();

		Bitmap bmImg = BitmapFactory.decodeStream(is);
		System.out.println("image"+bmImg);
		member_image.setImageBitmap(bmImg);
		} catch (IOException e) {
		
		e.printStackTrace();
		}
   
 }

//This class fill the grid with icons.
private static class GridAdapter extends BaseAdapter {
		/*Using ArrayAdapter we create  an view of a row in a list*/
	 private Context mContext;
     ArrayList<String> iconName1=new ArrayList<String>();
 	 ArrayList<Integer> iconImage1=new  ArrayList<Integer>(); 
     public GridAdapter(Context c, ArrayList<String> iconName, ArrayList<Integer> iconImage) 
     {
         mContext = c;
         this.iconName1=iconName;
         this.iconImage1=iconImage;
     }
     public int getCount() {
         return iconImage1.size();
     }

     public Object getItem(int position) {
         return null;
     }

     public long getItemId(int position) {
         return 0;
     }

     @Override
		public View getView(int pos, View view, ViewGroup parent) {
			ViewHolder holder;
			if (view == null) { // if it's not recycled, initialize some attributes
				LayoutInflater inflater = LayoutInflater.from(mContext);
				view = inflater.inflate(R.layout.grid_list_view, parent,false);
                holder = new ViewHolder();
				holder.Tsak_Name = (TextView)view.findViewById(R.id.tv_grid_name);
				view.setTag(holder);
				
				holder.imageView= (ImageView)view.findViewById(R.id.iv_grid_image);
				view.setTag(holder);
				
			  }                  
			else 
			 {
				holder = (ViewHolder) view.getTag();
			 }
			holder.imageView.setImageResource(iconImage1.get(pos));
			holder.Tsak_Name.setText(iconName1.get(pos));
		return view;
		}
        static class ViewHolder {
			TextView Tsak_Name;
			ImageView imageView;
	}                 
	}

 /*On click header open new view*/

@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.rr_grid_titleBar:
		Intent user_info=new Intent(this,UserInfo.class);
		startActivity(user_info);
		break;
		
	
	default:
		break;
	}
	
}
/*Set data from database by select query*/
public void addButton() {

  SQLiteDatabase db = myDb.getReadableDatabase();
 try{
	 
      Cursor cursor=db.query(DbHelper.TABLE_NAME, new String[] {ID,USERNAME}, null,null, null, null, null);		
		if(cursor.getCount()>0){
			cursor.moveToFirst();           
			do{
			                                                 
				 User_id=cursor.getString(0);                                                                                                 
				 username=cursor.getString(1);
			     
			} while (cursor.moveToNext());
			cursor.close();
			db.close();               
	        }                                                                                           
	}catch(Exception e){
		e.printStackTrace();                                      
	}   
	onDestroy();
  }
//Close database class.                                               
public void onDestroy(){
	super.onDestroy();
	
	myDb.close();
}	
}