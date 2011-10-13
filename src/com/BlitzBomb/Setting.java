package com.BlitzBomb;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Setting extends Activity{
	GridView SettingGrid;
	static int pos;
	 private Integer[] Image_icon = 
	         {
	            R.drawable.icon, R.drawable.icon,
	            R.drawable.icon, R.drawable.icon,
	            R.drawable.icon, R.drawable.icon,
	         };
	 GridAdapter SettingList; 
	 ArrayList<String> iconName=new ArrayList<String>();
	 ArrayList<Integer> iconImage=new ArrayList<Integer>();
	private String heading[]={"General Setting","Privacy","Notification",
	                          "Change Password","Change Picture","Deactivate Account"
	                          };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		SettingGrid=(GridView)findViewById(R.id.gv_Setting_Grid);
		  for(int i=0;i<heading.length;i++)
		    {
	         iconName.add(heading[i]);
	        }
	        for(int i=0;i<Image_icon.length;i++){
	          iconImage.add(Image_icon[i]);
	        }
	  SettingList = new GridAdapter(this, iconName, iconImage);
	  SettingGrid.setAdapter(SettingList);//Set adapter in gird with icons
	  SettingGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position,long arg3) {
				parent.getAdapter().getItem(0);
				pos=position;
				switch (position) {
				 case 0:
					Intent newsStream=new Intent(Setting.this,GeneralSetting.class);
					startActivity(newsStream);
					break;
				 case 1:
					Intent Privacy=new Intent(Setting.this,UserPrivacy.class);
					startActivity(Privacy);
					break;
				 case 2:
					Intent notification=new Intent(Setting.this,Notification.class);
					startActivity(notification);
					break;
				 case 3:
					Intent friend=new Intent(Setting.this,ChangePassword.class);
					startActivity(friend);
					break;
				 case 4:
				 	Intent message=new Intent(Setting.this,EditImage.class);
					startActivity(message);
					break;
				 case 5:
					//Intent photo=new Intent(BlitzBombGrid.this,Photo.class);
					//startActivity(photo);
					break;
				 default:
					break;
				}
			}       
		});
		
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
}
