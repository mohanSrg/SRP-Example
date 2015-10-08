package com.example.srpexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	EditText ed1,ed2,ed3,ed4,ed5;
	   
	   private String url1 = "api.openweathermap.org/data/2.5/weather?q=";
	   private String url2 = "&mode=xml";
	   private HandleXML handleObj;
	   Button b1;
	   
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);
	      b1=(Button)findViewById(R.id.button);
	      
	      ed1=(EditText)findViewById(R.id.editText);
	      ed2=(EditText)findViewById(R.id.editText2);
	      ed3=(EditText)findViewById(R.id.editText3);

	      
	      b1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            String url = ed1.getText().toString();
	            
	            Log.d("country", url);
	            
	            String finalUrl = url1 + url + url2;
	            ed2.setText(finalUrl);
	            
	            handleObj = new HandleXML(finalUrl);
	            handleObj.fetchXML();
	            
	            while(handleObj.parsingComplete);
	            ed2.setText(handleObj.getCountry());
	            ed3.setText(handleObj.getTemperature());
	            
	         }
	      });
	   }
	   
	 
}
