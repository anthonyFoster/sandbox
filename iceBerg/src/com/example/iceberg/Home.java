package com.example.iceberg;

import java.io.File;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

public class Home extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current tab position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		File database = getApplicationContext().getDatabasePath("iceberg.db");

		if (!database.exists()) {
		    // Database does not exist so copy it from assets here
		    Log.i("af", "Not Found");
		    DatabaseHandler db = new DatabaseHandler(this);
	        db.close();
		} else {
		    Log.i("af", "Found");
		    
		}

		// Set up the action bar to show tabs.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.title_schedule).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_news).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_twitter).setTabListener(this));
		
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		ActionBar actionBar = getActionBar();
		Bundle args = new Bundle();
		Fragment frag;
		
		switch(tab.getPosition()){
			case 0:  actionBar.setTitle(R.string.title_schedule);
					 frag = new Schedule();
					 break;
			case 1:  actionBar.setTitle(R.string.title_news);
					 frag = new News();
					 break;
			case 2:  actionBar.setTitle(R.string.title_twitter);
					 frag = new Twitter();
					 break;
			default: actionBar.setTitle(R.string.title_schedule);
					 frag = new Schedule();
					 break;
		}
		
		frag.setArguments(args);
		fragmentTransaction.replace(R.id.container, frag);
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
}
