package com.network_connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpConnection {
	private static final String tag = "tag";
	private String Url;
private String line;
public HttpConnection(String Url){
	this.Url=Url;
}
public String httpConnection(){
	StringBuffer sb = new StringBuffer("");
	// OutputStreamWriter request=null;
	BufferedReader bufferreader = null;
	// InputStreamReader inputstreamreader=null;
	try{
	HttpClient client = new DefaultHttpClient();
	
	Log.v("HttpClient","httpclient");
	HttpGet req = new HttpGet(Url);
	// req.setURI(new URI(loginUrl));
	Log.v("HttpResponse",req.getURI().toString());
	HttpResponse response = client.execute(req);
	bufferreader = new BufferedReader(new InputStreamReader(response
			.getEntity().getContent()));

	while ((line = bufferreader.readLine()) != null) {
		sb.append(line);
	}
	Log.v(tag, (String) String.valueOf(sb.toString()));
	if(sb.equals(null)){
		Log.v("Respone", sb.toString());
		return("notvalid");
	}
	bufferreader.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	Log.v("Respone", sb.toString());
	return(sb.toString());
}

}

