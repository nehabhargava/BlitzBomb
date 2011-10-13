package com.BlitzBomb;


import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class InviteFriend extends TabActivity implements OnTabChangeListener{
	TabHost tabHost;
	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        
	        /*hide title bar*/
	    	requestWindowFeature(Window.FEATURE_NO_TITLE);
	    	
	    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	        setContentView(R.layout.invite_friend);
	        
	        /* TabHost will have Tabs */
	        tabHost = (TabHost)findViewById(android.R.id.tabhost);
	      
	        
	        /* TabSpec used to create a new tab. 
	         * By using TabSpec only we can able to setContent to the tab.
	         * By using TabSpec setIndicator() we can set name to tab.*/
	        /* tid1 is firstTabSpec Id. Its used to access outside.*/
	        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
	        TextView my_video=new TextView(this);
	        my_video.setGravity(android.view.Gravity.CENTER);
	        my_video.setText("Step 1" +
	        		"     Profile Information");
	        my_video.setTextSize(10.0f);
	        firstTabSpec.setIndicator(my_video);
	        firstTabSpec.setContent(new Intent(this,ProfileInformation.class));
	        tabHost.addTab(firstTabSpec);
	        
	        TabSpec secondTabSpec = tabHost.newTabSpec("tid2");
	        TextView browse_all=new TextView(this);
	        browse_all.setGravity(android.view.Gravity.CENTER);
	        browse_all.setText("Step 2");
	        browse_all.setTextSize(14.0f);
	       
	        secondTabSpec.setIndicator(browse_all);
	        secondTabSpec.setContent(new Intent(this,ChangeImage.class));
	        tabHost.addTab(secondTabSpec);
	        
	        TabSpec trirdTabSpec=tabHost.newTabSpec("tid3");
	        TextView Create_new=new TextView(this);
	        Create_new.setGravity(android.view.Gravity.CENTER);
	        Create_new.setText("Step3");
	        Create_new.setTextSize(14.0f);
	        trirdTabSpec.setIndicator(Create_new);
	        trirdTabSpec.setContent(new Intent(this,FindFriend.class));
	        tabHost.addTab(trirdTabSpec);
	        
	        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
	        {
	        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#4C7D7E"));
	        }
	        
	        tabHost.getTabWidget().setCurrentTab(0);
	        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#4C7D7E"));
	        /* Add tabSpec to the TabHost to display. */
	       
	   }
	    @Override
		public void onTabChanged(String tabId) {
			
			for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
	        {
				tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
				
	        } 
					
			 tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#ffffff"));
		}
	}
