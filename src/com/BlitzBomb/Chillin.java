package com.BlitzBomb;


import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.network_connection.HttpConnection;


public class Chillin extends MapActivity implements OnClickListener{
	 private MapView mapView;
	 EditText Search;
	 ImageView search;
	 String Date,Time,Result;
	 Button date,Search_city;
	 private Geocoder gc;
	 GeoPoint p;
	 double lat;
		double lon;
		MotionEvent event = null;
	 InputMethodManager inputMgr; 
	    double latitudeE6;
	    double longitudeE6;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        
	        //set screen full with out default title.
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);/*set orientation portrait mode*/
	        setContentView(R.layout.chillin);
	                                
	           
	        inputMgr =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	        date=(Button)findViewById(R.id.btn_chillin_selected_date);
	        search=(ImageView)findViewById(R.id.iv_chillin_search);
	        Search=(EditText)findViewById(R.id.et_chillin_serach_location);
	        search.setOnClickListener(this);
	        Search_city=(Button)findViewById(R.id.btn_chillin_serach);
	        Search_city.setOnClickListener(this); 
	        date.setText(DateTimePicker.stringDate);
	        date.setOnClickListener(this);
	        mapView = (MapView) findViewById(R.id.mapView); 
	        mapView.setOnClickListener(this);
	        mapView.setBuiltInZoomControls(true);
	        
	        List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
	        DrawableMapOverlay itemizedOverlay = 
	             new DrawableMapOverlay(drawable, this);
	        
	        /*GeoPoint point = new GeoPoint(latitudeE6, longitudeE6);
	        OverlayItem overlayitem = 
	             new OverlayItem(point, "Hello", "I'm in Athens, Greece!");
	        
	        itemizedOverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedOverlay);
	       
	        MapController mapController = mapView.getController();
	        
	       // mapController.animateTo(point);
	        mapController.setZoom(6);*/
	      
	       /* MapOverlay mapOverlay = new MapOverlay();
	        List<Overlay> listOfOverlays = mapView.getOverlays();
	        listOfOverlays.clear();
	        listOfOverlays.add(mapOverlay);       
	        mapView.invalidate();                 */     
			                     
	    }

	    @Override
	    protected boolean isRouteDisplayed() {
	        return false;
	    }
	    class MapOverlay extends com.google.android.maps.Overlay
	    {
	        @Override
	        public boolean draw(Canvas canvas, MapView mapView, 
	        boolean shadow, long when) 
	        {
	            super.draw(canvas, mapView, shadow);                   
	 
	            //---translate the GeoPoint to screen pixels---
	            Point screenPts = new Point();
	            mapView.getProjection().toPixels(p, screenPts);
	 
	            //---add the marker---
	            Bitmap bmp = BitmapFactory.decodeResource(
	                getResources(), R.drawable.icon);            
	            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
	            return true;
	        }
	        @Override
	        public boolean onTouchEvent(MotionEvent event, MapView mapView) 
	        {   
	            //---when user lifts his finger---
	            if (event.getAction() == 1) {                
	                GeoPoint p = mapView.getProjection().fromPixels(
	                    (int) event.getX(),
	                    (int) event.getY());
	 
	                Geocoder geoCoder = new Geocoder(
	                    getBaseContext(), Locale.getDefault());
	                try {
	                    List<Address> addresses = geoCoder.getFromLocation(
	                        p.getLatitudeE6()  / 1E6, 
	                        p.getLongitudeE6() / 1E6, 1);
	 
	                    String add = "";
	                    if (addresses.size() > 0) 
	                    {
	                        for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
	                             i++)
	                           add += addresses.get(0).getAddressLine(i) + "\n";
	                    }
	 
	                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
	                }
	                catch (IOException e) {                
	                    e.printStackTrace();
	                }   
	                return true;
	            }
	            else                
	                return false;
	        }        
	    
	    }
	    
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.ic_map_menu_satellite:
			mapView.setSatellite(true); // Set satellite view
			return true;
		case R.id.ic_map_menu_hybrid:
			mapView.setStreetView(true);
			break;
		case R.id.ic_map_menu_map:
			mapView.setTraffic(true);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_chillin_selected_date:
	    Intent date1=new Intent(getApplicationContext(),DateTimePicker.class);
		startActivity(date1);
			
			break;
case R.id.iv_chillin_search:
	  Search.setVisibility(1);
	  Search_city.setVisibility(1);
	  search.setVisibility(4);
			break;
case R.id.btn_chillin_serach:
	String addressInput = Search.getText().toString(); //Get input text
	 System.out.println("foundAdresses"+addressInput);
	 String link= "http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&city="+addressInput;
		 HttpConnection httpconnection=new HttpConnection (link);
	    Result=httpconnection.httpConnection();
	    System.out.println("response"+Result);
	    parsing(Result);
    /*try {
                                   
      List<Address> foundAdresses = gc.getFromLocationName(addressInput, 5); //Search addresses
       System.out.println("foundAdresses"+foundAdresses.get(0));
       
      if (foundAdresses.size() < 0) { //if no address found, display an error
        Dialog locationError = new AlertDialog.Builder(this)
          .setIcon(0)
          .setTitle("Error")
          .setPositiveButton("ok", null)        
          .setMessage("Sorry, your address doesn't exist.")
          .create();
        locationError.show();
      }
      else
      { //else display address on map           
         
		for (int i = 0; i <foundAdresses.size(); ++i) {
          //Save results as Longitude and Latitude
         //@todo: if more than one result, then show a select-list
			Log.v("loop", "loop");
          Address x = foundAdresses.get(i);
          System.out.println("xxx"+x);        
          lat = x.getLatitude();
          System.out.println("latitude"+lat);
          lon = x.getLongitude();
          System.out.println("longtitude"+lon);
        }
      navigateToLocation((lat * 1000000), (lon * 1000000),mapView); //display the found address
      }
    }                                      
    catch (Exception e) {
    	Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show();
    }*/
	   break;
		 default:
			break;
		}
	 
	}

	private void parsing(String value) {
		// TODO Auto-generated method stub
		try{
			//System.out.println("value"+ListEvent);
			 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(value));
				Document doc = db.parse(is);
				//System.out.println("doc"+doc);
				doc.getDocumentElement().normalize();
				
	            NodeList nodeList = doc.getElementsByTagName("Result");
	           
	          
	                                        
				for (int i = 0; i < nodeList.getLength(); i++) {
					Log.v("loop","loop");
	               Node node = nodeList.item(i);
	               Element fstElmnt = (Element) node;
					NodeList codeList = fstElmnt.getElementsByTagName("Latitude");
					Element nameElement = (Element) codeList.item(0);
					codeList = nameElement.getChildNodes();
					Log.v("codeList","codeList");
					latitudeE6=Double.parseDouble(((Node) codeList.item(0)).getNodeValue());          
					                             
					System.out.println("EventName"+latitudeE6);
					NodeList textList = fstElmnt.getElementsByTagName("Longitude");
					Element websiteElement = (Element) textList.item(0);
					textList = websiteElement.getChildNodes();
	 				//EventDistance=((Node) codeList.item(0)).getNodeValue();
					longitudeE6=Double.parseDouble(((Node) codeList.item(0)).getNodeValue());
					System.out.println("Distance"+longitudeE6);
			}
				/*for(int i=0;i<latitudeE6.length;i++){
					//EventName1.add(EventName[i]);
					//EventDistance1.add(EventDistance[i]);
					//eventId.add(EventId[i]);
					//check_in.add(Checked_In[i]);
				}*/
				navigateToLocation( latitudeE6, longitudeE6,mapView);
				System.out.println("latitudeE6"+latitudeE6);
			} catch (Exception e) {
				System.out.println("XML Pasing Excpetion = " + e);
			}
	}      
	private void navigateToLocation(double latitude, double longitude, MapView mv) {
	    p = new GeoPoint((int) latitude, (int) longitude); //new GeoPoint
	    /*p = mapView.getProjection().fromPixels(
                (int) latitude,
                (int) longitude);*/                
	    System.out.println("geopoint"+p);
	    mv.displayZoomControls(true); //display Zoom (seems that it doesn't work yet)
	    MapController mc = mv.getController();
	    mc.animateTo(p); //move map to the given point
	                       
	    mc.setZoom(6); //zoom
	    mv.setSatellite(true); //display only "normal" mapview   
		                                         
	}
}
