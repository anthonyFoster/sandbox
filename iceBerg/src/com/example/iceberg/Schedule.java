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
        "L 4 - 5",
        "W 3 - 2",
        "W 6 - 1",
        "HOME",
        "HOME",
        "AWAY",
        "AWAY",
        "AWAY",
        "AWAY",
        "HOME"
    };
 
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] logos = new int[]{
        R.drawable.lincoln,
        R.drawable.omaha,
        R.drawable.tricity,
        R.drawable.desmoines,
        R.drawable.chicago,
        R.drawable.youngstown,
        R.drawable.cedar_rapids,
        R.drawable.fargo,
        R.drawable.waterloo,
        R.drawable.usa
    };
 
    // Array of strings to store currencies
    String[] dateTime = new String[]{
        "FRI, 2/8 10:00PM",
        "SAT, 2/9 7:00PM",
        "SUN, 2/10 7:00PM",
        "MON, 2/11 7:00PM",
        "FRI, 2/12 10:00PM",
        "FRI, 2/13 7:00PM",
        "FRI, 2/8 7:00PM",
        "FRI, 2/8 7:00PM",
        "FRI, 2/8 7:00PM",
        "FRI, 2/8 7:00PM"
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
