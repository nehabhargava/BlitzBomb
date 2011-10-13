package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;


public class PlayVideo extends Activity implements OnClickListener,OnRatingBarChangeListener{
	ImageView good,bad,video;
	TextView rating,comments;
	RatingBar video_rating;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.play_video);
		good=(ImageView)findViewById(R.id.iv_play_video_good);
		bad=(ImageView)findViewById(R.id.iv_play_video_bad);
		video=(ImageView)findViewById(R.id.iv_play_video);
		rating=(TextView)findViewById(R.id.tv_play_video_rating);
		comments=(TextView)findViewById(R.id.tv_play_video_comments);
		good.setOnClickListener(this);
		bad.setOnClickListener(this);
	    video.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
       switch (v.getId()) {
	case R.id.tv_play_video_rating:
		
		break;
     case R.id.tv_play_video_comments:
		
		break;
     case R.id.iv_play_video_bad:
	
	break;
     case R.id.iv_play_video_good:
	
	break;
     case R.id.iv_play_video:
    		Intent video=new Intent(this,PlayingVideo.class);
    		startActivity(video);
    		break;
	default:
		break;
	}
		
	}
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) {
		comments.setText("Rating is " + rating);	
		
	}
	
	//create option menu, on click menu button on phone.For select image
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.edit_video, menu);
		return true;
	 }
	  //create option menu view on item clicked in menu.
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		switch ((item.getItemId())) {
		case R.id.ic_edit_video:
			Intent edit_video=new Intent(this,EditVideo.class);
			startActivity(edit_video);
			break;
	
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	 }


}
