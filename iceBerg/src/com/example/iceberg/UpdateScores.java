package com.example.iceberg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class UpdateScores extends AsyncTask<String,Void,String>{

	DatabaseHandler db = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	HashMap<String, String> imageMap = new HashMap<String, String>();
	
	public enum Month {
		Jan,
		Feb,
		Mar,
		Apr,
		May,
		Jun,
		Jul,
		Aug,
		Sep,
		Oct,
		Nov,
		Dec
	}
	
	public UpdateScores(Context context){
		db = new DatabaseHandler(context);
	}

	protected String doInBackground(String... urls) {
		URI uri = null;
		try {
			uri = new URI(urls[0]);
		} 
		catch (URISyntaxException e1) {
		}
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(uri);
		
		List<Game> gamesWithNoResults = db.getGamesWithoutResult();
		Date today = new Date(); //dateFormat.format(new Date());
		
		try{
			HttpResponse response = client.execute(request);
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String htmlLine = null;
			String line = null;
			Game gameToUpdate = null;
			String mydate = null;
			String score = null;
			String finalScore = null;
			boolean capture = false;
			int j = 0;
			boolean dateIsAfterToday = false;
			
			while((htmlLine = reader.readLine()) != null && !dateIsAfterToday)
			{
				line = android.text.Html.fromHtml(htmlLine).toString();
				if(capture && !line.equals("")){
					switch(j){
						case 0: //date
							mydate = line.split("\\s+")[1];	
							int month = Month.valueOf(mydate.split("-")[1]).ordinal() + 1;
							mydate = "20" + mydate.split("-")[2] + "-" + month + "-" + mydate.split("-")[0];
							j++;
							break;
						case 1: //time
							if(dateFormat.parse(mydate).before(dateFormat.parse(dateFormat.format(today)))){
								for(Game gm : gamesWithNoResults){	
									if(gm.getDate().split("\\s+")[0].equalsIgnoreCase(mydate)){
										gameToUpdate = gm;
										capture = true;
										break;
									}
									else{
										capture = false;
									}
								}
							}
							else{
								capture = false;
								dateIsAfterToday = true;
							}
							j++;
							break;
						case 2: //away team
							j++;
							break;
						case 3://away team score
							line = line.replaceAll("\\s", "");
							if(!line.equals("")){
								score = line;
							}
							j++;
							break;
						case 4: //home team
							j++;
							break;
						case 5://home team score
							line = line.replaceAll("\\s", "");
							String ot = "";
							if(score != null){
								if(line.contains("OT") || score.contains("OT")){
									line = line.replaceAll("OT", "");
									score = score.replaceAll("OT", ""); 
									ot = " OT";
								}
								
								if(Integer.parseInt(score) < Integer.parseInt(line)){
									if(gameToUpdate.getHomeAway().equals("AWAY")){
										finalScore = "L " + score + " - " + line + ot;
									}
									else{
										finalScore = "W " + score + " - " + line + ot;
									}
								}
								else{
									if(gameToUpdate.getHomeAway().equals("AWAY")){
										finalScore = "W " + score + " - " + line + ot;
									}
									else{
										finalScore = "L " + score + " - " + line + ot;
									}
								}
								gameToUpdate.setResult(finalScore);
							}
							else{
								finalScore = null;
							}
							j++;
							break;
						case 6:
							if(gameToUpdate != null){
								db.updateGame(gameToUpdate);
							}
							score = null;
							mydate = null;
							gameToUpdate = null;
							j = 0;
							capture = false;
						default:
							break;
					}
				}
				
				if(line.equals("EX") || line.equals("RS")){
					capture = true;
					j = 0;
				}
			}
			in.close();
			return null;			
		}
		catch(Exception e){
			//Log.i("af",e.toString());
			return null;
		}
	}
	
	protected void OnPostExecute(String result){
		
	}
}
