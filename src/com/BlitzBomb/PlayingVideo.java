package com.BlitzBomb;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayingVideo extends Activity{
	VideoView video;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.playing_video);
		

		video=(VideoView)findViewById(R.id.videoView);
		MediaController mc=new MediaController(this);
		video.setMediaController(mc);
		
		//Set the path of Video or URI
        video.setVideoURI(Uri.parse("/sdcard/do-beer-not-drugs.3gp"));
		
       //Set the focus
        video.start();
	}

}
