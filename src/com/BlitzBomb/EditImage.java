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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
public class EditImage extends Activity implements OnClickListener{
	private static final int SELECT_PICTURE = 0;
	private static final int CAMERA_PIC_REQUEST=1;
	ImageView memberPhoto;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	   super.onCreate(savedInstanceState);
	    /*hide title bar*/
   	requestWindowFeature(Window.FEATURE_NO_TITLE);
   	
   	/*set orientation portrait mode*/
   	  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.edit_image);
		memberPhoto=(ImageView)findViewById(R.id.iv_EditImage_Image);
		memberPhoto.setOnClickListener(this);
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
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_EditImage_Image:
			openOptionsMenu();
			break;
          default:
			break;
		}
		
	}
	private String selectedImagePath;
	 @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	  super.onActivityResult(requestCode, resultCode, data);
      System.out.println("Imagedatadata"+data);
      switch (requestCode) {
	case CAMERA_PIC_REQUEST:
		if(requestCode == CAMERA_PIC_REQUEST){
			
			   Bundle extras = data.getExtras();
			   Bitmap bmp = (Bitmap) extras.get("data");
			   memberPhoto.setImageBitmap(bmp); 
		}
		break;
	case SELECT_PICTURE:
		if(requestCode == SELECT_PICTURE){
			
			  Uri selectedImageUri = data.getData();
		      selectedImagePath = getPath(selectedImageUri);
	  	      //System.out.println("Image Path : " + selectedImagePath);
	  	      memberPhoto.setImageURI(selectedImageUri);
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
