package com.BlitzBomb;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ImageGallery extends Activity  {

    ImageView img;
	
	ArrayList<String> image=new ArrayList<String>();

	private int pos;

	private Cursor cursor;

	static String image1="";
    
	/** Called when the activity is first created. */
 /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery);
        GridView gridView=(GridView)findViewById(R.id.gridView);
       gridView.setOnItemClickListener(this);
      //  gridView.setAdapter(new ImageAdapter(this));

	  //   String selection = MediaStore.Images.Thumbnails.KIND + "="  + // Select only mini's
     //   MediaStore.Images.Thumbnails.MINI_KIND;

    //    Cursor cursor=managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, null, selection, null, null);
       
      //  cursor.moveToFirst();
     // gridAdapter gAdapter=new gridAdapter(getApplicationContext(), cursor);
       // gridView.setAdapter(gAdapter);
    
     // To open up a gallery browser 
       Intent intent = new Intent(); 
        intent.setType("image/*"); 
        intent.setAction(Intent.ACTION_GET_CONTENT); 
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1); 
        // To handle when an image is selected from the browser, add the following to your Activity 
}
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {           
        	if (resultCode == RESULT_OK) {                
        		if (requestCode == 1) {                         
        			// currImageURI is the global variable I'm using to hold the content:
        			// URI of the image                        
        			 Uri currImageURI = data.getData();                
        			}        
} } 
   // And to convert the image URI to the direct file system path of the image file
   
        public String getRealPathFromURI(Uri contentUri) {       
	   // can post image        
	   String [] proj={MediaStore.Images.Media.DATA};         
	   Cursor cursor = managedQuery( contentUri,                        
			   proj, // Which columns to return                         
			   null,       // WHERE clause; which rows to return (all rows)                         
			   null,       // WHERE clause selection arguments (none)                         
			   null); // Order-by clause (ascending by name)         
	   int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);        
	   cursor.moveToFirst();          
	   return cursor.getString(column_index);
	   }
 
  
            @Override
    	public void onItemClick(AdapterView<?> parent, View arg1, int position,long arg3) {
    		parent.getAdapter().getItem(0);
    		pos=position;
    		switch (position) {
    		 case 0:
    			
    			Intent glass=new Intent(this,ChangeImage.class);
    			startActivity(glass);
    			break;
    		 case 1:
    			Intent profile=new Intent(this,Profile.class);
    			startActivity(profile);
    			break;
    		 
    default :
    	break;
    		}

        } 
    	/*	class gridAdapter extends BaseAdapter{
        Context context;
        Cursor cursor;
        LayoutInflater inflater;
		private ArrayList<String> image;
		private ImageView img;
        
      public gridAdapter(Context context,Cursor cursor){
                this.context=context;
                this.cursor=cursor;
                inflater=LayoutInflater.from(context);
        }
        
                @Override
                public int getCount() {
                        return cursor.getCount();
                }

                @Override
                public Object getItem(int arg0) {
                        return cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
                }

                @Override
                public long getItemId(int arg0) {
                        return arg0;
                }

				

               @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                        
                        cursor.moveToPosition(position);
                        image.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA)));
                        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
                        if(convertView==null){
                                img=new ImageView(context);
                                img.setLayoutParams(new GridView.LayoutParams(90, 90));

                                convertView=img;
                                String pathStr=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
                                System.out.println("image path"+pathStr);
                                ((ImageView)convertView).setImageURI(Uri.parse(pathStr));
                        }else{
                                String pathStr=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
                                ((ImageView)convertView).setImageURI(Uri.parse(pathStr));
                        }
                        System.out.println("image all"+image);
                       
                        return convertView;
                }
          

    		}
    */

	/*@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int i=arg2+1;
		image1=image.get(i);
		TabHost tab=(TabHost)getParent().findViewById(android.R.id.tabhost);
		tab.setCurrentTab(1);
		Intent image=new Intent(this,ChangeImage.class);
	    startActivity(image);
	}*/

	
       
      /*  	 public class ImageAdapter extends BaseAdapter 
        	    {
        	        private Context context;
					private int[] imageIDs;
				

        	        public ImageAdapter(Context c) 
        	        {
        	            context = c;
        	        }

        	        //---returns the number of images---
        	                	        //---returns the ID of an item--- 
        	        public Object getItem(int position) {
        	            return position;
        	        }

        	        public long getItemId(int position) {
        	            return position;
        	        }

        	        //---returns an ImageView view---
        	        public View getView(int position, View convertView, ViewGroup parent) 
        	        {
        	            ImageView imageView;
        	            if (convertView == null) {
        	                imageView = new ImageView(context);
        	                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
        	                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        	                imageView.setPadding(5, 5, 5, 5);
        	            } else {
        	                imageView = (ImageView) convertView;
        	            }
        	           
        	            imageView.setImageResource(imageIDs[position]);
        	            return imageView;
        	        }

					@Override
					public int getCount() {
						// TODO Auto-generated method stub
						return 0;
					}
        	    }    
} */
	 String mSelectedImagePath;
	 	    private static final int SELECT_IMAGE = 1;
	 	    Button b;
	 	    @Override
	 	    public void onCreate(Bundle savedInstanceState) {
	 	    super.onCreate(savedInstanceState);
	         setContentView(R.layout.image_gallery);
	 	     
	 	        Intent intent = new Intent();
	 	        intent.setType("image/*");
	 	        intent.setAction(Intent.ACTION_GET_CONTENT);
	 	        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_IMAGE);
	 	    }
	 	 
	 	    /* After opening gallery control comes here */
	 	      public void onActivityResult(int requestCode, int resultCode, Intent data) {
	 	          super.onActivityResult(requestCode, resultCode, data);
	 	          if (resultCode == RESULT_OK) {
	 	            switch(requestCode) {
	 	                case SELECT_IMAGE:
	 	                    mSelectedImagePath = getPath(data.getData());
	 	                    System.out.println("mSelectedImagePath : " + mSelectedImagePath);
	 	                    try {
	 	                        File sd = Environment.getExternalStorageDirectory();
	 	                        if (sd.canWrite()) {
	 	                            System.out.println("(sd.canWrite()) = " + (sd.canWrite()));
	 	                            String destinationImagePath= "/file.jpg";   // this is the destination image path.
	 	                            File source = new File(mSelectedImagePath );
	 	                            File destination= new File(sd, destinationImagePath);
	 	                            if (source.exists()) {
	 	                                FileChannel src = new FileInputStream(source).getChannel();
	 	                                FileChannel dst = new FileOutputStream(destination).getChannel();
	 	                                dst.transferFrom(src, 0, src.size());       // copy the first file to second.....
	 	                                src.close();
	 	                                dst.close();
	 	                               Intent i=new Intent(this, ChangeImage.class);
	 	                               startActivity(i);
	 	                               // Toast.makeText(getApplicationContext(), "Check the copy of the image in the same path as the gallery image. File is name file.jpg", Toast.LENGTH_LONG).show();
	 	                            }
	 	                        }else{
	 	                            Toast.makeText(getApplicationContext(), "SDCARD Not writable.", Toast.LENGTH_LONG).show();
	 	                        }
	 	                    }catch (Exception e) {
	 	                        System.out.println("Error :" + e.getMessage());
	 	                    }
	 	                    break;
	 	          }
	 	        }
	 	    }
	 	 
	 	    public String getPath(Uri uri) {
	 	        String[] projection = { MediaStore.Images.Media.DATA };
	 	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	 	        startManagingCursor(cursor);
	 	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	 	        cursor.moveToFirst();
	 	        return cursor.getString(column_index);
	 	    }
	 	}


		
        

	  
	 
 
	


        


