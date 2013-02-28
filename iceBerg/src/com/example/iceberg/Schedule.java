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

public class Schedule extends ListFragment {
	// Array of strings storing country names
    String[] homeAwayResult = new String[] {
        "HOME",
        "HOME",
        "AWAY",
        "AWAY",
        "AWAY",
        "HOME",
        "HOME",
        "AWAY",
        "AWAY",
        "W 8 - 5"
    };
 
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] logos = new int[]{
        R.drawable.sioux_falls,
        R.drawable.green_bay,
        R.drawable.waterloo,
        R.drawable.fargo,
        R.drawable.fargo,
        R.drawable.muskegon,
        R.drawable.sioux_city,
        R.drawable.fargo,
        R.drawable.fargo,
        R.drawable.sioux_city
    };
 
    // Array of strings to store currencies
    String[] dateTime = new String[]{
        "FRI, 3/22 7:05PM",
        "SAT, 3/16 7:05PM",
        "FRI, 3/15 7:05PM",
        "TUE, 3/12 7:05PM",
        "SAT, 3/09 7:05PM",
        "FRI, 3/08 7:05PM",
        "TUE, 3/05 7:05PM",
        "SAT, 3/02 7:05PM",
        "FRI, 3/01 7:35PM",
        "TUE, 2/26 7:05PM"
    };
	
	public Schedule() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i<10;i++){
            HashMap<String, String> dataMap = new HashMap<String,String>();
            dataMap.put("home_away_result", homeAwayResult[i]);
            dataMap.put("date_time", dateTime[i]);
            dataMap.put("logo", Integer.toString(logos[i]) );
            dataList.add(dataMap);
        }
        
		// Inflate the layout for this fragment  
        View view =  inflater.inflate(R.layout.schedule_list_fragment, container, false);   
        
        // Keys used in Hashmap
        String[] from = { "logo","home_away_result","date_time" };
 
        // Ids of views in listview_layout
        int[] to = { R.id.logo,R.id.home_away_result,R.id.date_time };
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), dataList, R.layout.schedule_listview_layout, from, to);
        setListAdapter(adapter);
        //new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2,mListItems));  

        return view;
	}
}
