package com.nyu.cs9033.eta.controllers;
import java.net.HttpURLConnection;

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
import android.widget.Button;
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
    		new jsonData().execute("");
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
