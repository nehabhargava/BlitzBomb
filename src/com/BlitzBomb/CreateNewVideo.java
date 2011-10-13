package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateNewVideo extends Activity implements OnClickListener{
	Spinner Category,Privacy,Comment_Privacy;
	TextView privacy;
	private String[]category={"Select category","Auto & Vehicles","Comdey","Education","Entertainment",
			                  "Film & Animation","gaming","Howto & Style","Music","News & Policies",
			                  "Nonprofits & Activism","People & Blogs","Pets & Animals","Science & Technology",
			                  "Sports","Travels & Events"};
	private String[]Privicy={"Every one","Friends & Network","Friends Of Frind","Frind Only","Just Me"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.create_new_video);
		
		Category=(Spinner)findViewById(R.id.sp_create_video_category);
		Privacy=(Spinner)findViewById(R.id.sp_create_video_privacy);
		Comment_Privacy=(Spinner)findViewById(R.id.sp_create_video_comment_privacy);
		privacy=(TextView)findViewById(R.id.tv_create_new_video_comment_privacy);
		privacy.setOnClickListener(this);
		 ArrayAdapter<String> category_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
		 category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			Category.setAdapter(category_adapter);
			ArrayAdapter<String> privacy_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Privicy);
			privacy_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			Privacy.setAdapter(privacy_adapter);
			Comment_Privacy.setAdapter(privacy_adapter);
	}
	//create option menu, on click menu button on phone.For select image
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.record_video, menu);
		return true;
	 }
	  //create option menu view on item clicked in menu.
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		switch ((item.getItemId())) {
		
		case R.id.ic_menu_camera:
		Intent recording=new Intent(this,RecoredVideo.class);
		startActivity(recording);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	 }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_create_new_video_comment_privacy:
			
			break;

		default:
			break;
		}
	}

}
