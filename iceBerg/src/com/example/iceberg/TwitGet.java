package com.example.iceberg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class TwitGet extends AsyncTask<String,Void,String>{
	
	protected String doInBackground(String... urls) {
		URI uri = null;
		try {
			uri = new URI(urls[0]);
		} catch (URISyntaxException e1) {
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		HttpResponse response;
		try {
		  response = httpclient.execute(httpget);
		  HttpEntity entity = response.getEntity();
		  StringBuilder str = new StringBuilder();
		  String line = null;
		  if (entity != null) {
		    InputStream instream = entity.getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));		
			while((line = reader.readLine()) != null)
			{
			    str.append(line);
			}
		    instream.close();
		    
		  }
		  return str.toString();
		} catch (ClientProtocolException e) {
	
		} catch (IOException e){
		  //Log.e("aferror", "ERROR: Failed to open GET_REQUEST "+ e);
		}
		return null;
	}

	protected void OnPostExecute(String result){
		
	}
	
}
