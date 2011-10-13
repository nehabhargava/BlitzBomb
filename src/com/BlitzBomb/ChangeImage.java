package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

public class ChangeImage extends Activity implements OnClickListener{
	Button save,skip,back;
	ImageView member_image;
	String image;
	private static final int SELECT_PICTURE = 0;
	private static final int CAMERA_PIC_REQUEST=1;
	private static final int SELECT_PHOTO = 0;
	
	String filePath = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		/*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	/*set orientation portrait mode*/
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.change_image);
	
		save=(Button)findViewById(R.id.btn_changeImage_save_continue);
		member_image=(ImageView)findViewById(R.id.iv_changeimage_big_image);
		
	    skip=(Button)findViewById(R.id.btn_changeImage_skip);
		back=(Button)findViewById(R.id.btn_changeImage_back);
		back.setOnClickListener(this);
		skip.setOnClickListener(this);
		save.setOnClickListener(this);
		member_image.setOnClickListener(this);
		
		//if(!image.contains(""));
		//image=ImageGallery.image1;
		//((ImageView)member_image).setImageURI(Uri.parse(image));	 
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_changeImage_save_continue:
			Intent i = new Intent(this,FindFriend.class );
			startActivity(i);
			break;
        case R.id.btn_changeImage_skip:
	       //change tab by click button
			TabHost tab=(TabHost)getParent().findViewById(android.R.id.tabhost);
			tab.setCurrentTab(2);
			break;
        case R.id.btn_changeImage_back:
        	TabHost tab1=(TabHost)getParent().findViewById(android.R.id.tabhost);
			tab1.setCurrentTab(0);
	
	 break;
	
case R.id.iv_changeimage_big_image:
	openOptionsMenu();
	break;
		default:
			break;
		}
		
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
			//Intent gallery=new Intent(this,ImageGallery.class);
			//startActivity(gallery);
		      Intent intent = new Intent();
		   	     intent.setType("image/*");
		         intent.setAction(Intent.ACTION_GET_CONTENT);
		         startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
			
			/* Intent intent = new Intent(Intent.ACTION_PICK, 
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						  startActivityForResult(intent, 0);*/
				
			
			break;
		case R.id.ic_menu_camera:
			//Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
			 //startActivityForResult(Intent.createChooser(cameraIntent,"Select Picture"), CAMERA_PIC_REQUEST);
		      startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	 }
	/*  protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        if (requestCode == CAMERA_PIC_REQUEST) {  
	            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	            member_image.setImageBitmap(photo);
	        }  
	    } */
	 /* @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			//switch (requestCode) {
			if (requestCode == SELECT_PICTURE) {  
		        // do something  
		    
				if (resultCode == Activity.RESULT_OK) {
					Uri selectedImageUri = data.getData();
					System.out.println("filemanagerstring"+selectedImageUri);
				 

					try {
						// OI FILE Manager
						String filemanagerstring = selectedImageUri.getPath();

						// MEDIA GALLERY
						String selectedImagePath = getPath(selectedImageUri);

						//String filePath;
						if (selectedImagePath != null) {
							filePath = selectedImagePath;
							System.out.println("filePath"+filePath);
							LodeImage(filePath);
						} else if (filemanagerstring != null) {
							filePath = filemanagerstring;
							System.out.println("filemanagerstring"+filePath);
						} else {
							Toast.makeText(getApplicationContext(), "Unknown path",
									Toast.LENGTH_LONG).show();
							Log.e("Bitmap", "Unknown path");
						}

						if (filePath != null) {
							decodeFile(filePath);
						} else {
							bm = null;
						}
					} catch (Exception e) {
						Toast.makeText(this, "Internal error",Toast.LENGTH_LONG).show();
					}
				}  else if(requestCode == CAMERA_PIC_REQUEST){
				    //Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
				    
				    //System.out.println("thumbnail"+thumbnail);
				}
					}
				
			
				
			//}
			
		}
 */private String selectedImagePath;
	 @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	  super.onActivityResult(requestCode, resultCode, data);
       System.out.println("Imagedatadata"+data);
       switch (requestCode) {
	case CAMERA_PIC_REQUEST:
		if(requestCode == CAMERA_PIC_REQUEST){
			
			   Bundle extras = data.getExtras();
			   Bitmap bmp = (Bitmap) extras.get("data");
			   member_image.setImageBitmap(bmp); 
		}
		break;
	case SELECT_PICTURE:
		if(requestCode == SELECT_PICTURE){
			
			  Uri selectedImageUri = data.getData();
		       selectedImagePath = getPath(selectedImageUri);
	  	      System.out.println("Image Path : " + selectedImagePath);
	  	    member_image.setImageURI(selectedImageUri);
		}
		break;
	default:
		break;
	}
       
	 
	  } 
	 public String getPath(Uri uri) {
	      String[] projection = { MediaStore.Images.Media.DATA };
		       Cursor cursor = managedQuery(uri, projection, null, null, null);
		        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		        cursor.moveToFirst();
			        return cursor.getString(column_index);
		   }
	
}  
