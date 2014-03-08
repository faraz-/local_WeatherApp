package com.example.vercingetorix_sampleapp.datamodel;

public class WeatherDetail {
	private String name;
	private String country;
	private double temp;
	private double min;
	private double max;
	private double pressure;
	private double humidity;
	private String main;
	private String description;
	private String icon;
	
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
	
	public Double gettemp(){
		return temp;
	}
	
	public void settemp(Double temp){
		this.temp=temp;
	}
	
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
	
//	public String getdescription(){
//		return description;
//	}
//	
//	public void setdescription(String description){
//		this.description=description;
//	}
	
	public String geticon(){
		return icon;
	}
	
	public void seticon(String icon){
		this.icon=icon;
	}
}
