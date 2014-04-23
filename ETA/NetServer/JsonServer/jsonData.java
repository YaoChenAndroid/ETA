package JsonServer;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class jsonData extends AsyncTask<String, Void, String>{

	private final static String TAG = "jsonData";


    private HttpURLConnection BuildConenction()
    {
    	String http = "http://cs9033-homework.appspot.com/";  
    	//String http = "http://www.baidu.com/";

    	HttpURLConnection conn=null;  
    	try {  


    	    URL url = new URL(http);  
    	    conn = (HttpURLConnection) url.openConnection();
    	    
    	    conn.setReadTimeout( 10000 /*milliseconds*/ );
    	    conn.setConnectTimeout( 15000 /* milliseconds */ );
    	    conn.setRequestMethod("POST");
    	    conn.setDoInput(true);
    	    conn.setDoOutput(true);
    	    //conn.setFixedLengthStreamingMode(message.getBytes().length);

    	    //make some HTTP header nicety
    	    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
    	    conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

    	    //open
    	    Log.e(TAG, "open connection_yao");
    	    conn.connect();
    	    

    	} 
    	catch (MalformedURLException e) {  

            Log.e(TAG, e.getMessage());
    	}  
    	catch (IOException e) {  
	
    		Log.e(TAG, e.getMessage());  
	       } 
    	catch(Exception e)
    	{
    		Log.e(TAG, "connect error in " + TAG);
    		Log.e(TAG, e.getMessage());

    	}

		return conn;
    }
    private String ReadTripInfo(HttpURLConnection urlConnection,String tripID)
    {
    	try
    	{
    	    //Create JSONObject here
    	    JSONObject jsonParam = new JSONObject();
    	    jsonParam.put("command", "TRIP_STATUS");
    	    jsonParam.put("trip_id", tripID);
    	    OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
    	    out.write(jsonParam.toString());
    	    out.close();  

    	    int HttpResult =urlConnection.getResponseCode();  
    	    if(HttpResult ==HttpURLConnection.HTTP_OK){  
    	        BufferedReader br = new BufferedReader(new InputStreamReader(  
    	            urlConnection.getInputStream(),"utf-8"));  
    	        return formatResult(br);
    	    }else{  
    	    	Log.i(TAG, urlConnection.getResponseMessage());
    	    	return "";
    	    }  
    	}    	
    	catch (JSONException e) {
 	       // TODO Auto-generated catch block
    		Log.e(TAG, e.getMessage());
    		return "";
     	} catch (IOException e) {
			// TODO Auto-generated catch block
    		Log.e(TAG, e.getMessage());
    		return "";
		}
    	finally{  
 	       if(urlConnection!=null)  
 	    	   urlConnection.disconnect();  
 	   }
    }

	private String formatResult(BufferedReader br) {
		// TODO Auto-generated method stub
        String line = null;  
        StringBuilder sb = new StringBuilder(); 
        
        try {
        	String[] distance = null, time = null, friends = null;

        	if((line = br.readLine()) != null)
        	{
        		br.close(); 
        		return line;
        	}

	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return "";
	}

	@Override
	protected String doInBackground(String... urls) {
		return download(urls[0]);

	}
	private String download(String tripID) {
		// TODO Auto-generated method stub
		HttpURLConnection urlConnection = BuildConenction();
		return ReadTripInfo(urlConnection,tripID);

	}
}
