package com.nyu.cs9033.eta.controllers;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import DatabaseHelper.TripDatabaseHelper; 

import com.nyu.cs9033.eta.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import JsonServer.jsonData;

public class MainActivity extends Activity {
	private final static String TAG = "MainActivity";
	//private Trip m_trip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// TODO - fill in here	
		Button buttonCreate = (Button) findViewById(R.id.buttonCreat);
		Button buttonView = (Button)findViewById(R.id.buttonView);
		
		buttonCreate.setOnClickListener(new View.OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startTripCreator();
			}
		}) ;
		buttonView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub;
				startTripViewer();
			}
		});
		TripDatabaseHelper dbHelper = new TripDatabaseHelper(this);
    	if(this.IsNetworkConnect())
    	{
    		String res;
			try {
				res = new jsonData().execute("3645686546").get();
	    		
	    		String[] source = res.split("\":");
	    		String[] friends = getResArray(source[3]);
	    		String[] distance = getResArray(source[1]);
	    		String[] time = getResArray(source[2]);
	    		int nLen = time.length;

	    		String temp;
	    		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	    		for(int i = 0; i < nLen; i++) {
	    		    Map<String, String> datum = new HashMap<String, String>(2);
	    		    datum.put("title", friends[i]);
	    		    temp = "distance_left: " + distance[i] + "\ntime_left: " + time[i] + "\n";
	    		    datum.put("date", temp);
	    		    data.add(datum);
	    		}
	    		
	    		ListView listV = (ListView)findViewById(R.id.listViewCurTrip);
	    		SimpleAdapter adapter = new SimpleAdapter(this, data,
                        android.R.layout.simple_list_item_2,
                        new String[] {"title", "date"},
                        new int[] {android.R.id.text1,
                                   android.R.id.text2});
	    		listV.setAdapter(adapter);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}
    	else
    	{
    		Log.e(TAG, "No netWork");
    	}
	}
	/**
	 * Receive result from CreateTripActivity here.
	 * Can be used to save instance of Trip object
	 * which can be viewed in the ViewTripActivity.
	 */
	
	
	private String[] getResArray(String source) {
		// TODO Auto-generated method stub
		String temp = source.substring(2,source.indexOf("]"));
		return temp.split(",");
	}
	/**
	 * This method should start the
	 * Activity responsible for creating
	 * a Trip.
	 */
	//generate the "CreateTripActivity"
	public void startTripCreator() {
		
		// TODO - fill in here
		Intent intent = new Intent(this, CreateTripActivity.class);

		startActivity(intent);  
	}
	
	/**
	 * This method should start the
	 * Activity responsible for viewing
	 * a Trip.
	 */
	//generate the "TripHistoryActivity"
	public void startTripViewer() {
		
		// TODO - fill in here
		Intent intent = new Intent(this, TripHistoryActivity.class);
		
		startActivity(intent);
	}
    //check whether can access network.
    private boolean IsNetworkConnect()
    {
    	ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

    	if(networkInfo != null && networkInfo.isConnected()){
    		return true;
    	}else{
    		return false;
    	}
    }
	
}
