package com.example.iceberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class News extends ListFragment {

	private static List<Blog> blogs;
	//private FeedGet feed;
	private static List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
	private static SimpleAdapter adapter;
	
	public News() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String[] from = { "title","date" };
    	int[] to = { R.id.title,R.id.date };       
    	adapter = new SimpleAdapter(getActivity(), dataList, R.layout.news_listview_layout, from, to);
        setListAdapter(adapter);
        
		FeedParser parser = new RssFeedParser(getString(R.string.newsURL));
		new FeedGet1(getActivity()).execute(parser);
		View view =  inflater.inflate(R.layout.news_list_fragment, container, false);
		//loadFeed();
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, Uri.parse(blogs.get(position).getLink().toExternalForm()));
		this.startActivity(viewMessage);
	}
/*
	private void loadFeed(){
		
    	try{
	    	//FeedParser parser = new RssFeedParser("http://www.lincolnstarsblog.com/feeds/posts/default?alt=rss");
	    	//blogs = feed.get();
	    	//String xml = writeXml();
	    	//Log.i("Blog",xml);
	    	for (Blog blg : blogs){
	    		HashMap<String, String> dataMap = new HashMap<String,String>();
	            dataMap.put("title", blg.getTitle());
	            dataMap.put("date", blg.getDate());
	            dataList.add(dataMap);
	    	}

	    	

    	} catch (Throwable t){
    		//Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    */
    
	private static class FeedGet1 extends AsyncTask<FeedParser,Void,String>{
		
		Context context;
		private ProgressDialog loadingNews;
		//List<Blog> blogs;
		
		public FeedGet1(Context context){
			this.context = context;
			loadingNews = new ProgressDialog(context);
		}
		
		protected void onPreExecute(){
			this.loadingNews.setMessage("Loading News");
	        this.loadingNews.show();
		}
		
		@Override
		protected String doInBackground(FeedParser... parser) {
			blogs = parser[0].parse();
			return "done";
		}

		protected void onPostExecute(String result){
			loadingNews.dismiss();
			
			if(!dataList.isEmpty())
				dataList.clear();
			
			for (Blog blg : blogs){
	    		HashMap<String, String> dataMap = new HashMap<String,String>();
	            dataMap.put("title", blg.getTitle());
	            dataMap.put("date", blg.getDate());
	            dataList.add(dataMap);
	    	}
			adapter.notifyDataSetChanged();
		}
	}
	/*	
	private String writeXml(){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "messages");
			serializer.attribute("", "number", String.valueOf(blogs.size()));
			for (Blog blg: blogs){
				serializer.startTag("", "message");
				serializer.attribute("", "date", blg.getDate());
				serializer.startTag("", "title");
				serializer.text(blg.getTitle());
				serializer.endTag("", "title");
				serializer.startTag("", "url");
				serializer.text(blg.getLink().toExternalForm());
				serializer.endTag("", "url");
				serializer.startTag("", "body");
				serializer.text(blg.getDescription());
				serializer.endTag("", "body");
				serializer.endTag("", "message");
			}
			serializer.endTag("", "messages");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	*/
}
