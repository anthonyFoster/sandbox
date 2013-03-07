package com.example.iceberg;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class News extends ListFragment {

	private List<Blog> blogs;
	
	public News() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view =  inflater.inflate(R.layout.news_list_fragment, container, false);
		loadFeed();
		return view;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent viewMessage = new Intent(Intent.ACTION_VIEW, Uri.parse(blogs.get(position).getLink().toExternalForm()));
		this.startActivity(viewMessage);
	}

	private void loadFeed(){
		List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
    	try{
	    	FeedParser parser = new RssFeedParser("http://www.lincolnstarsblog.com/feeds/posts/default?alt=rss");
	    	blogs = new FeedGet().execute(parser).get();
	    	String xml = writeXml();
	    	//Log.i("Blog",xml);
	    	for (Blog blg : blogs){
	    		HashMap<String, String> dataMap = new HashMap<String,String>();
	            dataMap.put("title", blg.getTitle());
	            dataMap.put("date", blg.getDate());
	            dataList.add(dataMap);
	    	}

	    	String[] from = { "title","date" };
	    	int[] to = { R.id.title,R.id.date };
	         
	    	SimpleAdapter adapter = new SimpleAdapter(getActivity(), dataList, R.layout.news_listview_layout, from, to);
	        setListAdapter(adapter);

    	} catch (Throwable t){
    		//Log.e("AndroidNews",t.getMessage(),t);
    	}
    }
    
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
	
}
