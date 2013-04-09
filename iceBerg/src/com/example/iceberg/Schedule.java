package com.example.iceberg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class Schedule extends ListFragment {
	
	SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MM/dd hh:mm");
	
	public Schedule() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
        DatabaseHandler db = new DatabaseHandler(getActivity());
        List<Game> schedule = db.getAllGames();
        db.close();
 
        for(Game gm : schedule){
            HashMap<String, String> dataMap = new HashMap<String,String>();
            Date date = null;
            if(gm.getResult() == null){
            	dataMap.put("home_away_result", gm.getHomeAway());
            }
            else{
            	dataMap.put("home_away_result", gm.getResult());
            }
            try {
            	date = FORMATTER.parse(gm.getDate());
				dataMap.put("date_time", dateFormat.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            dataMap.put("logo", gm.getOpponentImage());
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
        new UpdateScores(getActivity()).execute("http://www.lincolnstars.com/leagues/print_schedule.cfm?leagueID=16793&clientID=4806&teamID=343151&mixed=1");
        return view;
	}
}
