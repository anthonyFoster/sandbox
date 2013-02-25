package com.example.iceberg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;





public class Twitter extends ListFragment {
//stores player twitter handles	
    String[] twitterHandle = new String[] {
            "Luke Johnson",
            "Justin Wood",
            "Dominik Shine",
            "Mike Mckee",
    };
//stores actual tweets
    String[] tweets = new String[]{
            "had a huge fight last night #go stars",
            "first goal ever! #starsFlyTogether",
            "goalie fight #winning",
            "crowd was insane last night #stars",
        };
    	
	public Twitter() {
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i< twitterHandle.length;i++){
            HashMap<String, String> dataMap = new HashMap<String,String>();
            dataMap.put("twitterHandle", twitterHandle[i]);
            dataMap.put("tweets", tweets[i]);
            dataList.add(dataMap);
        }
        
		// Inflate the layout for this fragment  
        View view =  inflater.inflate(R.layout.twitter_list_fragment, container, false);   
        
        // Keys used in Hashmap
        String[] from = {"twitterHandle","tweets" };
 
        // Ids of views in listview_layout
        int[] to = {R.id.twitterHandle,R.id.tweets};
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), dataList, R.layout.twitter_listview_layout, from, to);
        setListAdapter(adapter);
        //new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2,mListItems));  

        return view;
	}
}
