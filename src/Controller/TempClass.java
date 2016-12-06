package Controller;

import invoke.InvokeInstance;

import java.net.MalformedURLException;
import java.text.ParseException;

import Weather.SendData;
import database.DatabaseAccess;
//import invoke.InvokeInstance;

public class TempClass {

	public void createinstance(final int user_id, final String sensor_name, final String lat, final String lon) throws InterruptedException
	{
		
		Thread t1 = new Thread(new Runnable() {
		     public void run() {
		    	 
		    	 InvokeInstance i=new InvokeInstance();
		          // code goes here.
	
		    		//SendData sd = new SendData();
		    		
		    	//	while(true){
		    			
		    //	sd.getdata(args[0],args[1],args[2],args[3],args[4]); 
		    			
		    	//	sd.getdata("11","i-09588206345c873cd","37.37","-121.83");
		    		
		    	//	File f =new File("../WebContent");
		    		
	    			try {
	    				i.createSensor(user_id, sensor_name, lat, lon);
//		    				sd.getdata(user_id, sensor_name, lat, lon);
//		    				Thread.sleep(600000);
//		    				System.out.println("added");
   			} catch (InterruptedException e) {
		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
//		    			} catch (MalformedURLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
		    		}
		    		
		    	 
		    	// SendData sd = new SendData();
		    	
		     
		    	 
		    	 
		     }
		});  
		t1.start();
		
	
	
	}

}
