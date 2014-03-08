package com.example.vercingetorix_sampleapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.vercingetorix_sampleapp.adapter.LazyAdapter;
import com.example.vercingetorix_sampleapp.adapter.weatherAdapter;
import com.example.vercingetorix_sampleapp.datamodel.ForecastDetail;
import com.example.vercingetorix_sampleapp.datamodel.WeatherDetail;

public class MainActivity extends Activity {
	int i;
	String location;
	WeatherDetail wD;
	weatherAdapter adap_User2;
	ListView list_User2;
	List messages2;
	ArrayList<String> city_name;
	ProgressDialog pd;
	static InputStream is = null;
	static String json = "";
	static JSONObject jObj = null;
//	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=14&units=metric&q=";
	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=";
//	private static String IMG_URL = "http://openweathermap.org/img/w/";
	private static String URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview2);
        Bundle b=new Bundle();
        b=getIntent().getExtras();
        city_name=b.getStringArrayList("name");
        pd = new ProgressDialog(this);
        list_User2 = (ListView)findViewById(R.id.list2);
        messages2 = new ArrayList<WeatherDetail>();
//        for(i=0;i<=city_name.size();i++)
//		{
//        	location=new String();
//			location=city_name.get(i);
//			Log.d("value of"+i, location);
//		}  
        Log.d("Value of size", ""+city_name.size());
        GetJson gj = new GetJson();
		if (ifNet()) {
				gj.execute(null, null, null);
		}else
			Toast.makeText(this, "No Internet Detected", Toast.LENGTH_LONG).show();
		list_User2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, arg2 + " ",
//						Toast.LENGTH_SHORT).show();
				Intent in2 = new Intent(MainActivity.this, MainActivity2.class);
				WeatherDetail w = (WeatherDetail)messages2.get(arg2);
				in2.putExtra("forecast_city", w.getname());
				in2.putExtra("forecast_country", w.getcountry());
				startActivity(in2);
			}
		});
    }
    /**
     * Method to check Internet Connectivity
     * **/
    	private boolean ifNet() {
    		
    		ConnectivityManager connectivity = (ConnectivityManager) this
    				.getSystemService(Context.CONNECTIVITY_SERVICE);
    		if (connectivity != null) {
    			NetworkInfo[] info = connectivity.getAllNetworkInfo();
    			if (info != null)
    				for (int i = 0; i < info.length; i++)
    					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
    						return true;
    					}

    		}
    		return false;
    	}

    	/**
    	 * Class for downloading, parsing JSon and setting listview  
    	 * **/
    	
    	class GetJson extends AsyncTask<Void, Void, Void> {

    		@Override
    		protected void onPreExecute() {
    			// TODO Auto-generated method stub
    			super.onPreExecute();
    			pd.setTitle("Fetching");
    			pd.setMessage("#########");
    			pd.show();
    		}

    		@Override
    		protected Void doInBackground(Void... params) {
    			// TODO Auto-generated method stub
    			for(i=0;i<city_name.size();i++)
    			{
    				location=city_name.get(i);
    				Log.d("value of"+i, location);
    				getJSONFromUrl(location);
//    				location=null;
    			}    			

    			return null;
    		}

    		@Override
    		protected void onPostExecute(Void result) {
    			// TODO Auto-generated method stub
    			if(i==city_name.size()){
    			super.onPostExecute(result);
    			FirstActivity.count=0;
    			adap_User2 = new weatherAdapter(MainActivity.this, messages2);
    			list_User2.setAdapter(adap_User2);
    			pd.dismiss();
    			}
    		}
    	}
    /**
     * Method for parsing JSon from the given key value pair
     * **/
    	private void parseJson() {
    		// TODO Auto-generated method stub

    		try {
//    			WeatherDetail fD = new WeatherDetail();
//    			JSONObject jObj_cityname=jObj.getJSONObject("city");
//    			fD.setname(jObj_cityname.getString("name"));
//    			fD.setcountry(jObj_cityname.getString("country"));
    			wD = new WeatherDetail();
    			wD.setname(jObj.getString("name"));
    			JSONObject jObj_sys=jObj.getJSONObject("sys");
    			wD.setcountry(jObj_sys.getString("country"));
    			JSONObject jObj_main=jObj.getJSONObject("main");
    			wD.settemp(jObj_main.getDouble("temp"));
    			wD.setmin(jObj_main.getDouble("temp_min"));
    			wD.setmax(jObj_main.getDouble("temp_max"));
    			wD.setpressure(jObj_main.getDouble("pressure"));
    			wD.sethumidity(jObj_main.getDouble("humidity"));
    			JSONArray jArry_weather=jObj.getJSONArray("weather");
    			JSONObject jOjb_Arry=jArry_weather.getJSONObject(0);
    			wD.setmain(jOjb_Arry.getString("main"));
    			wD.seticon(jOjb_Arry.getString("icon"));
    			messages2.add(wD);
//    			JSONArray jArry = jObj.getJSONArray("list");
//    			for (int i = 0; i < jArry.length(); i++) {
//    				// put in loop for all elements
//    				ForecastDetail fD = new ForecastDetail();
//        			JSONObject jObj_cityname=jObj.getJSONObject("city");
//        			fD.setname(jObj_cityname.getString("name"));
//        			fD.setcountry(jObj_cityname.getString("country"));
//    				JSONObject json_MSG = jArry.getJSONObject(i);
//    				JSONObject temp_MSG = json_MSG.getJSONObject("temp");
//    				fD.setmin(temp_MSG.getDouble("min"));
//    				fD.setmax(temp_MSG.getDouble("max"));
//    				fD.setmorn(temp_MSG.getDouble("morn"));
//    				fD.seteve(temp_MSG.getDouble("eve"));
//    				fD.setpressure(json_MSG.getDouble("pressure"));
//    				fD.sethumidity(json_MSG.getDouble("humidity"));
//    				JSONArray jArry2 = json_MSG.getJSONArray("weather");
//    				JSONObject json_MSG2=jArry2.getJSONObject(0);
//    				fD.setmain(json_MSG2.getString("main"));
//    				fD.setdescription(json_MSG2.getString("description"));
//    				fD.seticon(json_MSG2.getString("icon"));
//    				messages.add(fD);
    				//
//    			}
    		} catch (JSONException e) {
    			// TODO: handle exception
    		} catch (Exception e) {
    			// TODO: handle exception

    		}

    	}

    /**
     * Downloading JSon
     * **/
    public void getJSONFromUrl(String loc) {
		HttpResponse httpResponse = null;
		// Making HTTP request
		try {
			// defaultHttpClient
			HttpClient httpClient = new DefaultHttpClient();
			URL=(BASE_URL + loc);
			HttpGet httpget = new HttpGet(URL);
			httpget.setHeader("Content-type", "application/json");
			httpResponse = httpClient.execute(httpget);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StatusLine statusLine = httpResponse.getStatusLine();

		if (statusLine.getStatusCode() == 200) {
			try {
				HttpEntity httpEntity = httpResponse.getEntity();

				is = httpEntity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();
				Log.d("Debug", sb.toString());
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}
			// try parse the string to a JSON object
			try {
				jObj = new JSONObject(json);
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}
		}
		// return JSON String
		parseJson();

	}
    
}
