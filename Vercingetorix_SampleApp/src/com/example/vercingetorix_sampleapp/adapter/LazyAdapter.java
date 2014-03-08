package com.example.vercingetorix_sampleapp.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.w3c.dom.Text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vercingetorix_sampleapp.R;
import com.example.vercingetorix_sampleapp.datamodel.ForecastDetail;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;


public class LazyAdapter extends BaseAdapter {
	private Context activity;
	private static LayoutInflater inflater = null;
	//Weather detail object's list
	List messList;
	public LazyAdapter() {
		
	}

	public LazyAdapter(Context a, List messages) {
		// TODO Auto-generated constructor stub
		activity = a;
		// 
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				messList = messages;
				
				
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (vi == null) {	//If view is not recycled initialize
			holder = new ViewHolder();
			vi=inflater.inflate(R.layout.list_row, null);
			holder.day=(TextView)vi.findViewById(R.id.day2);
			holder.date=(TextView)vi.findViewById(R.id.date2);
			holder.city=(TextView)vi.findViewById(R.id.cityNameValue);
			holder.country=(TextView)vi.findViewById(R.id.countryNameValue);
			holder.min_temp=(TextView)vi.findViewById(R.id.minTempValue);
			holder.max_temp=(TextView)vi.findViewById(R.id.maxTempValue);
			holder.morn_temp=(TextView)vi.findViewById(R.id.mornTempValue);
			holder.eve_temp=(TextView)vi.findViewById(R.id.eveTempValue);
			holder.pressure=(TextView)vi.findViewById(R.id.pressureValue);
			holder.humidity=(TextView)vi.findViewById(R.id.humidityValue);
			holder.main=(TextView)vi.findViewById(R.id.mainWeatherValue);
			holder.description=(TextView)vi.findViewById(R.id.descValue);
			holder.img=(ImageView)vi.findViewById(R.id.weather_icon);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();	//Retrieving back from tag
		}
		ForecastDetail uGet = (ForecastDetail)messList.get(position);
		holder.day.setText(uGet.getday());
		holder.date.setText(uGet.getdate());
		holder.city.setText(uGet.getname());
		holder.country.setText(uGet.getcountry());
		holder.min_temp.setText((uGet.getmin()).toString());
		holder.max_temp.setText((uGet.getmax()).toString());
		holder.morn_temp.setText((uGet.getmorn()).toString());
		holder.eve_temp.setText((uGet.geteve()).toString());
		holder.pressure.setText((uGet.getpressure()).toString());
		holder.humidity.setText((uGet.gethumidity()).toString());
		holder.main.setText(uGet.getmain());
		holder.description.setText(uGet.getdescription());
		UrlImageViewHelper.setUrlDrawable(holder.img, "http://openweathermap.org/img/w/"+uGet.geticon());

		return vi;
}
	/**
	 * This class is a caching mechanism for the adapter, its static
	 * so we do not need a reference to use it.
	 * **/
		static class ViewHolder {
			TextView day;
			TextView date;
			TextView city;
			TextView country;
			TextView min_temp;
			TextView max_temp;
			TextView morn_temp;
			TextView eve_temp;
			TextView pressure;
			TextView humidity;
			TextView main;
			TextView description;
			ImageView img;
		}

	}
