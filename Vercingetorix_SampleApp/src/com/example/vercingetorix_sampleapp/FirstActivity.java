package com.example.vercingetorix_sampleapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vercingetorix_sampleapp.MainActivity.GetJson;

public class FirstActivity extends Activity{
	String entered_location, formatted_loc;
	ArrayList<String> cities=new ArrayList<String>();
	static int count=0;
	TextView t2,t3,t4,t5,t6,t7;
	static EditText edit1;
	Button btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t2=(TextView)findViewById(R.id.text2);
        t3=(TextView)findViewById(R.id.text3);
        t4=(TextView)findViewById(R.id.text4);
        t5=(TextView)findViewById(R.id.text5);
        t6=(TextView)findViewById(R.id.text6);
        t7=(TextView)findViewById(R.id.text7);
		edit1=(EditText)findViewById(R.id.edit_text);
        btn=(Button)findViewById(R.id.button1);
        edit1.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction()==KeyEvent.ACTION_DOWN){
					switch(keyCode){
					case KeyEvent.KEYCODE_ENTER:
						if(count<=5 && edit1.getText()!=null)
						count++;
						if(count==1){
//						t2=(TextView)findViewById(R.id.text2);
						t2.setText(edit1.getText());
//						cities.add(t2.getText().toString());
						edit1.setText(null);
						}
						if(count==2){
//						t3=(TextView)findViewById(R.id.text3);
						t3.setText(edit1.getText());
//						cities.add(t3.getText().toString());
						edit1.setText(null);
						}
						if(count==3){
//						t4=(TextView)findViewById(R.id.text4);
						t4.setText(edit1.getText());
//						cities.add(t4.getText().toString());
						edit1.setText(null);
						}
						if(count==4){
//						t5=(TextView)findViewById(R.id.text5);
						t5.setText(edit1.getText());
//						cities.add(t5.getText().toString());
						edit1.setText(null);
						}
						
						if(count==5){
//						t6=(TextView)findViewById(R.id.text6);
						t6.setText(edit1.getText());
//						cities.add(t6.getText().toString());
						edit1.setText(null);
						}
						if(count==6){
//						t7=(TextView)findViewById(R.id.text7);
						t7.setText(edit1.getText());
//						cities.add(t7.getText().toString());
						edit1.setText(null);
						}
						if(count==6){
							edit1.setText(null);
							Toast.makeText(getApplicationContext(), "Cannot enter more than 6 names", Toast.LENGTH_LONG).show();
						}
						break;
					}
				}
				// TODO Auto-generated method stub
				return false;
			}
			});
        btn.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(t2.getText().length()<1){
					Toast.makeText(getApplicationContext(), "Enter atleast one name and press done", Toast.LENGTH_LONG).show();
				}
				else{
				for(int j=1;j<=count;j++){
					entered_location=new String();
					formatted_loc=new String();
					if(j==1)
						entered_location=t2.getText().toString();
					if(j==2)
						entered_location=t3.getText().toString();
					if(j==3)
						entered_location=t4.getText().toString();
					if(j==4)
						entered_location=t5.getText().toString();
					if(j==5)
						entered_location=t6.getText().toString();
					if(j==6)
						entered_location=t7.getText().toString();
					for(int i=0;i<entered_location.length();i++)
					{
						if(Character.isWhitespace(entered_location.charAt(i))){
							formatted_loc=formatted_loc+"%20";
						}
							
						else {
							formatted_loc=formatted_loc+entered_location.charAt(i);
						}
					}
					cities.add(formatted_loc);
				}
				Intent in = new Intent(FirstActivity.this, MainActivity.class);
				in.putExtra("name", cities);
				t2.setText(null);
				t3.setText(null);
				t4.setText(null);
				t5.setText(null);
				t6.setText(null);
				t7.setText(null);
//				Log.d("array list value", ""+cities.get(0)+cities.get(1)+cities.get(2)+cities.get(3)+cities.get(4)+cities.get(5) );
				Log.d("", "Value of entered location" + formatted_loc);
				startActivity(in);
				}
			}
		});
	}
	
	@Override
	protected void onPause(){
		super.onPause();
	}
	@Override
	protected void onResume(){
		super.onResume();
	}
	@Override
	protected void onStop(){
		super.onStop();
		count=0;
	}
	@Override
	protected void onRestart(){
		super.onRestart();
//		t2.setText(null);
//		t3.setText(null);
//		t4.setText(null);
//		t5.setText(null);
//		t6.setText(null);
//		t7.setText(null);
	}
}