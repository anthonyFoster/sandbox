package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class DummySectionFragment extends ListFragment {

	//private String[] mListItems = { "Apple", "Orange", "Pear", "Peach", "Banana" };
	
	// Array of strings storing country names
    String[] countries = new String[] {
        "HOME",
        "AWAY",
        "AWAY",
        "HOME",
        "HOME",
        "AWAY",
        "AWAY",
        "AWAY",
        "AWAY",
        "HOME"
    };
 
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher,
        R.drawable.ic_launcher
    };
 
    // Array of strings to store currencies
    String[] currency = new String[]{
        "FRI 2/8 10:00",
        "SAT 2/9 7:00",
        "SUN 2/10 7:00",
        "MON 2/11 7:00",
        "FRI 2/12 10:00",
        "FRI 2/13 7:00",
        "FRI 2/8 7:00",
        "FRI 2/8 7:00",
        "FRI 2/8 7:00",
        "FRI 2/8 7:00"
    };
	
	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Create a new TextView and set its text to the fragment's section
		// number argument value.
		//TextView textView = new TextView(getActivity());
		//textView.setGravity(Gravity.CENTER);
		//textView.setText("Here");
		//return textView;
		
		// Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("cur", currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
        
		// Inflate the layout for this fragment  
        View view =  inflater.inflate(R.layout.my_list_fragment, container, false);   
        
        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };
 
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur };
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_layout, from, to);
        setListAdapter(adapter);
        //new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_2,mListItems));  

        return view;
	}
}
