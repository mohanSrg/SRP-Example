package com.example.srpexample;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HandleXML {

	   private String country = "county";
	   private String temperature = "temperature";
	   private String urlString = null;
	   private XmlPullParserFactory xmlFactoryObject;
	   public  boolean parsingComplete = true;
	   
	   public HandleXML(String url){
	      this.urlString = url;
	   }
	   
	   public String getCountry(){
	      return country;
	   }
	   
	   public String getTemperature(){
	      return temperature;
	   }
	   
	   public void parseXMLAndStoreIt(XmlPullParser myParser) {
	      int event;
	      String text=null;
	      
	      try {
	         event = myParser.getEventType();
	         
	         while (event != XmlPullParser.END_DOCUMENT) {
	            String name=myParser.getName();
	         
	            switch (event){
	               case XmlPullParser.START_TAG:
	               break;
	            
	               case XmlPullParser.TEXT:
	               text = myParser.getText();
	               break;
	            
	               case XmlPullParser.END_TAG:
	               if(name.equals("country")){
	                  country = text;
	               }
	            
	               else if(name.equals("temperature")){
	                  temperature = myParser.getAttributeValue(null,"value");
	               }
	            
	               else{
	               }
	               break;
	            }
	            event = myParser.next();
	         }
	         parsingComplete = false;
	      }
	      
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   
	   public void fetchXML(){
	      Thread thread = new Thread(new Runnable(){
	         @Override
	         public void run() {
	            try {
	               URL url = new URL(urlString);
	               HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            
	               conn.setReadTimeout(10000);
	               conn.setConnectTimeout(15000);
	               conn.setRequestMethod("GET");
	               conn.setDoInput(true);
	               conn.connect();
	            
	               InputStream stream = conn.getInputStream();
	               xmlFactoryObject = XmlPullParserFactory.newInstance();
	               XmlPullParser myparser = xmlFactoryObject.newPullParser();
	              
	               myparser.setInput(stream, null);
	               
	               parseXMLAndStoreIt(myparser);
	               stream.close();
	            }
	            catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	      thread.start();
	   }
}
