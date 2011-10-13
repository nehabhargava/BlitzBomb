package com.BlitzBomb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TimePicker;


public class DateTimePicker extends Activity {
    /** Called when the activity is first created. */
	DatePicker dpSetDateTime;
	TimePicker tpSetDateTime;
	Button btSetDateTimeSet;
	Button btSetDateTimeCancel;
    static String stringDate;
    static String stringTime;
	static String stringDateTime="Schedule At:";
	int year;
	int month;
	int day;
	int hour;
	int minute;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.datetimepicker);
        dpSetDateTime=(DatePicker)findViewById(R.id.dpSetDateTime);
        tpSetDateTime=(TimePicker)findViewById(R.id.tpSetDateTime);
        btSetDateTimeSet=(Button)findViewById(R.id.btSetDateTimeSet);
        btSetDateTimeCancel=(Button)findViewById(R.id.btSetDateTimeCancel);
        
        btSetDateTimeSet.getBackground().setColorFilter(0x990099ff, Mode.MULTIPLY);
        btSetDateTimeCancel.getBackground().setColorFilter(0x990099ff, Mode.MULTIPLY);
      
        btSetDateTimeSet.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) {
				 year= dpSetDateTime.getYear();
				 month=dpSetDateTime.getMonth()+1;
				 day=dpSetDateTime.getDayOfMonth();
				 stringDate=year+"-"+month+"-"+day;
				 hour=tpSetDateTime.getCurrentHour();
				 minute=tpSetDateTime.getCurrentMinute();
				 stringTime=hour+":"+minute+":"+"00";
			    stringDateTime=stringDate+" "+stringTime;
			     System.out.println("date time"+ stringTime);
				ShowDateTime();
			}
		});
        btSetDateTimeCancel.setOnClickListener(new View.OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
				setDate();
			}
	
		});
                        
    }
    private void setDate() {
        stringDate="";
        Intent intent=new Intent(this,Chillin.class);
		startActivity(intent);
	      finish();
		
	}
    public void ShowDateTime()
    {
    	System.out.println("stringDate"+stringDate);
    	
    	if(stringDate.equals(""))
    	{
    		Intent intent=new Intent(this,Chillin.class);
    		startActivity(intent);
    		
    		finish();
    	}
    	else
    	{
    		Intent intent=new Intent(this,Chillin.class);
    		startActivity(intent);
    		
        	finish();
    	}
    }
   }