package com.example.iceberg;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class FeedGet extends AsyncTask<FeedParser,Void,List<Blog>>{
	
	Context context;
	private ProgressDialog dialog;
	
	public FeedGet(Context context){
		this.context = context;
		dialog = new ProgressDialog(context);
	}
	
	protected void onPreExecute(){
		this.dialog.setMessage("Loading News");
        this.dialog.show();
	}
	
	@Override
	protected List<Blog> doInBackground(FeedParser... parser) {
		return parser[0].parse();
	}

	protected void OnPostExecute(List<Blog> blogs){
		if (dialog.isShowing()) {
            dialog.dismiss();
        }
	}
	
}

