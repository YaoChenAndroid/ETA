package com.nyu.cs9033.eta.controllers;
import DatabaseHelper.TripDatabaseHelper; 
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
public class MainActivity extends Activity {
	private Trip m_trip;
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

	
}
