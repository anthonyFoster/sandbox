package com.example.iceberg;

import java.util.List;
import android.os.AsyncTask;

public class FeedGet extends AsyncTask<FeedParser,Void,List<Blog>>{
	
	@Override
	protected List<Blog> doInBackground(FeedParser... parser) {
		return parser[0].parse();
	}

	protected void OnPostExecute(List<Blog> blogs){
		
	}
	
}

