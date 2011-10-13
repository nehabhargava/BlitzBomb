package com.BlitzBomb;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class AlbumDetail extends Activity implements OnItemClickListener{
	GridView album;
	private AlbumAdapter album_view;
	ArrayList<Integer> iconImage=new  ArrayList<Integer>(); 
	private Integer[] Image_icon = {
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon,
            R.drawable.icon, R.drawable.icon
           };
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.albumdetail);
	album=(GridView)findViewById(R.id.gv_Grid_album_detail);
	 for(int i=0;i<Image_icon.length;i++)
	 {
     	iconImage.add(Image_icon[i]);
     }
     album_view = new AlbumAdapter(this, iconImage);
     album.setAdapter(album_view);//Set adapter in gird with icons
     album.setOnItemClickListener(this);
    
  
}
 //create option menu, on click menu button on phone.For select image
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.photo_menu, menu);
	return true;
 }
  //create option menu view on item clicked in menu.
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
	switch ((item.getItemId())) {
	case R.id.ic_menu_gallery:
		
		break;
	case R.id.ic_menu_camera:
		//Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
		//startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
		break;
	default:
		break;
	}

	return super.onOptionsItemSelected(item);
 }

//This class fill the grid with icons.
private static class AlbumAdapter extends BaseAdapter {
		/*Using ArrayAdapter we create  an view of a row in a list*/
	 private Context mContext;
    
 	 ArrayList<Integer> iconImage1=new  ArrayList<Integer>(); 
     public AlbumAdapter(Context c, ArrayList<Integer> iconImage) 
     {
         mContext = c;
         
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
				view = inflater.inflate(R.layout.album_detail_row, parent,false);
                holder = new ViewHolder();
				
				holder.imageView= (ImageView)view.findViewById(R.id.iv_album_detail_images);
				view.setTag(holder);
				
			} 
			else 
			{
				holder = (ViewHolder) view.getTag();
			}
			holder.imageView.setImageResource(iconImage1.get(pos));
			
		return view;
		}
        static class ViewHolder {
			
			ImageView imageView;
	}
	}
@Override
    public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
	
	Intent photo_deatil=new Intent(this,PhotoDetail.class);
	startActivity(photo_deatil);
	
}

}
