package Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Random;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SendData {

	public void getdata(int user_id, String sensor_id, String lat, String lon) throws MalformedURLException, ParseException {
		// TODO Auto-generated method stub
		
		
		
	
		String api = "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=metric&APPID=4f88e378dc3e203973a3f74872dc0a23";
		URL url = new URL(api);
		
		try {
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
			
			System.out.println(conn.getErrorStream());
			
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
		
				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				StringBuffer buffer = new StringBuffer();
				String output;
				System.out.println("Output from Server .... \n");
				
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					buffer.append(output);
				}

				ObjectMapper mapper = new ObjectMapper();
				WeatherData wd = null;
				wd = mapper.readValue(buffer.toString(), WeatherData.class);
				String humidity= wd.getMain().getHumidity();
				String temp= wd.getMain().getTemp();
				String wind=wd.getWind().getSpeed();
				String pressure=wd.getMain().getPressure();

				System.out.println("Humidity  "+ humidity);
				System.out.println("Temp   "+ temp);
				System.out.println("Wind Speed "+ wind);
				System.out.println("Pressure  "+ pressure);

				TimeZone.setDefault(TimeZone.getTimeZone("PST"));
				java.util.Date utilDate = new java.util.Date();
				System.out.println("date  " + utilDate );
				java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
				System.out.println("timestamp  "+sq);
				String sq1=sq.toString();
				
				String query = "insert into humidity_data values('"+sensor_id+"','"+user_id+"','"+humidity+"','"+temp+"','"+wind+"','"+pressure+"','"+sq1+"' )";
				DatabaseAccess db = new DatabaseAccess();
				db.insert(query);
			
				
				conn.disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	

		
		
	}
		
	

public String getCPUtilization(){
	
	/*Random r = new Random();
	int Low = 5;
	int High = 16;
	int Result = r.nextInt(High-Low) + Low;
	
	int Low1 = 10;
	int High1 = 99;
	int Result1 = r.nextInt(High1-Low1) + Low1;
	
	String cpu = String.valueOf(Result)+"."+String.valueOf(Result1);
	
	return cpu;*/
	
	
		  OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		  Object value=null;
		  for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
		    method.setAccessible(true);
		    if (method.getName().startsWith("get") 
		        && Modifier.isPublic(method.getModifiers())) {
		            
		        try {
		            value = method.invoke(operatingSystemMXBean);
		        } catch (Exception e) {
		            value = e;
		        } // try
		        System.out.println(method.getName() + " = " + value);
		    } // if
		  } // for
		
		  return (String) value;
	
	
}



}
