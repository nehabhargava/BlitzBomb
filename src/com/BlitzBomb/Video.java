package com.BlitzBomb;



import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class Video extends TabActivity implements OnTabChangeListener{
TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        /*hide title bar*/
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.video);
        
        /* TabHost will have Tabs */
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
      
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TextView my_video=new TextView(this);
        my_video.setGravity(android.view.Gravity.CENTER);
        my_video.setText("My Video");
        my_video.setTextSize(14.0f);
        firstTabSpec.setIndicator(my_video);
        firstTabSpec.setContent(new Intent(this,MyVideo.class));
        tabHost.addTab(firstTabSpec);
        
        
        TabSpec secondTabSpec = tabHost.newTabSpec("tid2");
        TextView browse_all=new TextView(this);
        browse_all.setGravity(android.view.Gravity.CENTER);
        browse_all.setText("Browse All");
        browse_all.setTextSize(14.0f);
       
        secondTabSpec.setIndicator(browse_all);
        secondTabSpec.setContent(new Intent(this,BrowseAllVideo.class));
        tabHost.addTab(secondTabSpec);
        
        TabSpec trirdTabSpec=tabHost.newTabSpec("tid3");
        TextView Create_new=new TextView(this);
        Create_new.setGravity(android.view.Gravity.CENTER);
        Create_new.setText("Create New");
        Create_new.setTextSize(14.0f);
        trirdTabSpec.setIndicator(Create_new);
        trirdTabSpec.setContent(new Intent(this,CreateNewVideo.class));
        tabHost.addTab(trirdTabSpec);
        
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#E6E6DC"));
        }
        
        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#E6E6DC"));
     /* Add tabSpec to the TabHost to display. */
       
   }
    @Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#515151"));
			
        } 
				
		 tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#515151"));
	}
}
