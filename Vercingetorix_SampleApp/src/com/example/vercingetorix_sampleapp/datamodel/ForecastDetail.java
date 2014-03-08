package com.example.vercingetorix_sampleapp.datamodel;

import android.R.dimen;

public class ForecastDetail {
	private String date;
	private String day;
	private String name;
//	private float lon;
//	private float lat;
	private String country;
//	private float day;
	private double min;
	private double max;
//	private double night;
	private double eve;
	private double morn;
	private double pressure;
	private double humidity;
	private String main;
	private String description;
	private String icon;
	
	public String getdate(){
		return date;
	}
	
	public void setdate(String date){
		this.date=date;
	}
	
	public String getday(){
		return day;
	}
	
	public void setday(String day){
		this.day=day;
	}
	
	public String getname(){
		return name;
	}
	
	public void setname(String name){
		this.name=name;
	}
	
	public String getcountry(){
		return country;
	}
	
	public void setcountry(String country){
		this.country=country;
	}
	
//	public float getday(){
//		return day;
//	}
//	
//	public void setday(float day){
//		this.day=day;
//	}
	
	public Double getmin(){
		return min;
	}
	
	public void setmin(Double min){
		this.min=min;
	}
	
	public Double getmax(){
		return max;
	}
	
	public void setmax(Double max){
		this.max=max;
	}
	
//	public double getnight(){
//		return night;
//	}
	
//	public void setnight(Double night){
//		this.night=night;
//	}
	
	public Double geteve(){
		return eve;
	}
	
	public void seteve(Double eve){
		this.eve=eve;
	}
	
	public Double getmorn(){
		return morn;
	}
	
	public void setmorn(Double morn){
		this.morn=morn;
	}
	
	public Double getpressure(){
		return pressure;
	}
	
	public void setpressure(Double pressure){
		this.pressure=pressure;
	}
	
	public Double gethumidity(){
		return humidity;
	}
	
	public void sethumidity(Double humidity){
		this.humidity=humidity;
	}
	
	public String getmain(){
		return  main;
	}
	
	public void setmain(String main){
		this.main=main;
	}
	
	public String getdescription(){
		return description;
	}
	
	public void setdescription(String description){
		this.description=description;
	}
	
	public String geticon(){
		return icon;
	}
	
	public void seticon(String icon){
		this.icon=icon;
	}
}
