package com.example.vercingetorix_sampleapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.example.vercingetorix_sampleapp.datamodel.ForecastDetail;
import com.example.vercingetorix_sampleapp.datamodel.WeatherDetail;

public class MainActivity2 extends Activity {
	LazyAdapter adap_User;
	ListView list_User;
	List messages;
	Calendar c3;
	SimpleDateFormat df3;
	int d2;
	String fore_city,fore_country,day;
	ProgressDialog pd;
	static InputStream is = null;
	static String json = "";
	static JSONObject jObj = null;
	private static String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=14&units=metric&q=";
//	private static String IMG_URL = "http://openweathermap.org/img/w/";
	private static String URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        c3=Calendar.getInstance();
        df3=new SimpleDateFormat("dd-MM-yyyy");
//        d2=c3.get(Calendar.DAY_OF_WEEK);
        Bundle b2=new Bundle();
        b2=getIntent().getExtras();
        fore_city=b2.getString("forecast_city");
        fore_country=b2.getString("forecast_country");
        pd = new ProgressDialog(this);
        list_User = (ListView)findViewById(R.id.list);
        messages = new ArrayList<WeatherDetail>();
        GetJson gj = new GetJson();
		if (ifNet()) {
			gj.execute(null, null, null);
		}else
			Toast.makeText(this, "No Internet Detected", Toast.LENGTH_LONG).show();
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
    			getJSONFromUrl(fore_city,fore_country);

    			return null;
    		}

    		@Override
    		protected void onPostExecute(Void result) {
    			// TODO Auto-generated method stub
    			super.onPostExecute(result);
    			FirstActivity.edit1.setText("");
    			adap_User = new LazyAdapter(MainActivity2.this, messages);
    			list_User.setAdapter(adap_User);
    			pd.dismiss();
    		}
    	}
    /**
     * Method for parsing JSon from the given key value pair
     * **/
    	private void parseJson() {
    		// TODO Auto-generated method stub

    		try {
//    			WeatherDetail wD = new WeatherDetail();
//    			JSONObject jObj_cityname=jObj.getJSONObject("city");
//    			wD.setname(jObj_cityname.getString("name"));
//    			wD.setcountry(jObj_cityname.getString("country"));
    			JSONArray jArry = jObj.getJSONArray("list");
    			for (int i = 0; i < jArry.length(); i++) {
    				// put in loop for all elements
    				c3.add(Calendar.DATE, i+1);
    				d2=c3.get(Calendar.DAY_OF_WEEK);
    				switch(d2){
    				case 1:
    					day="Sun";
    					break;
    				case 2:
    					day="Mon";
    					break;
    				case 3:
    					day="Tues";
    					break;
    				case 4:
    					day="Wed";
    					break;
    				case 5:
    					day="Thurs";
    					break;
    				case 6:
    					day="Fri";
    					break;
    				case 7:
    					day="Sat";
    					break;
    				}
    				ForecastDetail fD = new ForecastDetail();
        			JSONObject jObj_cityname=jObj.getJSONObject("city");
        			fD.setday(day);
        			fD.setdate(df3.format(c3.getTime()));
        			fD.setname(jObj_cityname.getString("name"));
        			fD.setcountry(jObj_cityname.getString("country"));
    				JSONObject json_MSG = jArry.getJSONObject(i);
    				JSONObject temp_MSG = json_MSG.getJSONObject("temp");
    				fD.setmin(temp_MSG.getDouble("min"));
    				fD.setmax(temp_MSG.getDouble("max"));
    				fD.setmorn(temp_MSG.getDouble("morn"));
    				fD.seteve(temp_MSG.getDouble("eve"));
    				fD.setpressure(json_MSG.getDouble("pressure"));
    				fD.sethumidity(json_MSG.getDouble("humidity"));
    				JSONArray jArry2 = json_MSG.getJSONArray("weather");
    				JSONObject json_MSG2=jArry2.getJSONObject(0);
    				fD.setmain(json_MSG2.getString("main"));
    				fD.setdescription(json_MSG2.getString("description"));
    				fD.seticon(json_MSG2.getString("icon"));
    				c3.add(Calendar.DATE, -(i+1));
    				messages.add(fD);
    				//
    			}
    		} catch (JSONException e) {
    			// TODO: handle exception
    		} catch (Exception e) {
    			// TODO: handle exception

    		}

    	}

    /**
     * Downloading JSon
     * **/
    public void getJSONFromUrl(String location,String loc_country) {
		HttpResponse httpResponse = null;
		// Making HTTP request
		try {
			// defaultHttpClient
			HttpClient httpClient = new DefaultHttpClient();
			URL=(BASE_URL + location+","+loc_country);
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
