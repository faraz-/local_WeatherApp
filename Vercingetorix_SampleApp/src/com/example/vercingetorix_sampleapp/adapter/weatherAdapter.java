package com.example.vercingetorix_sampleapp.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.example.vercingetorix_sampleapp.R;
import com.example.vercingetorix_sampleapp.adapter.LazyAdapter.ViewHolder;
import com.example.vercingetorix_sampleapp.datamodel.ForecastDetail;
import com.example.vercingetorix_sampleapp.datamodel.WeatherDetail;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class weatherAdapter extends BaseAdapter{
	private Context activity;
	private static LayoutInflater inflater = null;
	//Weather detail object's list
	List messList;
	Calendar c;
	int d;
	String formatted_time,day,formatted_date;
//	Time today;
	
	public weatherAdapter(){
		
	}
	
	public weatherAdapter(Context a,List messages){
//		today=new Time(Time.getCurrentTimezone());
//		today.setToNow();
		c=Calendar.getInstance();
		d=c.get(Calendar.DAY_OF_WEEK);
		switch(d){
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
		SimpleDateFormat df=new SimpleDateFormat("HH:mm");
		SimpleDateFormat df2=new SimpleDateFormat("dd-MM-yyyy");
		formatted_time=df.format(c.getTime());
		formatted_date=df2.format(c.getTime());
		activity=a;
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
			vi=inflater.inflate(R.layout.list_row2, null);
			holder.city=(TextView)vi.findViewById(R.id.cityNameValue2);
			holder.country=(TextView)vi.findViewById(R.id.countryNameValue2);
			holder.temp=(TextView)vi.findViewById(R.id.temp2);
			holder.time=(TextView)vi.findViewById(R.id.time);
			holder.max_temp=(TextView)vi.findViewById(R.id.temp4);
			holder.min_temp=(TextView)vi.findViewById(R.id.temp3);
			holder.day=(TextView)vi.findViewById(R.id.day);
			holder.date=(TextView)vi.findViewById(R.id.date);
			holder.pressure=(TextView)vi.findViewById(R.id.pres);
			holder.humidity=(TextView)vi.findViewById(R.id.hum);
			holder.main=(TextView)vi.findViewById(R.id.main);
			holder.img=(ImageView)vi.findViewById(R.id.image);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();	//Retrieving back from tag
		}
		WeatherDetail wGet = (WeatherDetail)messList.get(position);
		holder.city.setText(wGet.getname());
		holder.country.setText(wGet.getcountry());
		holder.temp.setText(wGet.gettemp().toString());
		holder.min_temp.setText((wGet.getmin()).toString());
		holder.max_temp.setText((wGet.getmax()).toString());
		holder.time.setText(formatted_time);
		holder.day.setText(day);
		holder.date.setText(formatted_date);
		holder.pressure.setText((wGet.getpressure()).toString());
		holder.humidity.setText((wGet.gethumidity()).toString());
		holder.main.setText(wGet.getmain());
		UrlImageViewHelper.setUrlDrawable(holder.img, "http://openweathermap.org/img/w/"+wGet.geticon());

		return vi;
	}
		static class ViewHolder {
			TextView city;
			TextView country;
			TextView temp;
			TextView time;
			TextView min_temp;
			TextView max_temp;
			TextView day;
			TextView date;
			TextView pressure;
			TextView humidity;
			TextView main;
			ImageView img;
		}


}
