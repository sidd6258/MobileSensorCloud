package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VO.UDVO;
public class UserDAO{

	public List<UDVO> display(int i){
		List<UDVO> ls = new ArrayList<UDVO>();
		try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://sensorcloud.cm8ogkurdd27.us-west-2.rds.amazonaws.com:3306/sensorcloud?useSSL=false", "root","rootroot");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT s.sensor_id,s.humidity_data,temp,windSpeed,pressure,c.sensor_tag_value,c.sensor_status, s.timestamp"
						+ " FROM humidity_data s "
						+ " INNER JOIN ("
						+ " SELECT sensor_id, MAX(timestamp) timestamp"
						+ " FROM humidity_data"
						+ " GROUP BY sensor_id"
						+ "	) b ON s.sensor_id = b.sensor_id AND s.timestamp = b.timestamp"
						+ " and s.user_id="+i
						+ " INNER JOIN ("
						+ " SELECT sensor_id,user_id,sensor_status,sensor_tag_value from sensor_master) c "
						+ " ON c.sensor_id=s.sensor_id AND c.user_id=s.user_id");
				
				while(rs.next()){
					UDVO udvo = new UDVO();
					udvo.setSensor_id(rs.getString("sensor_id"));
					System.out.println(rs.getString("sensor_id"));
					udvo.setTemp(rs.getInt("humidity_data"));
					System.out.println(rs.getInt("humidity_data"));
					udvo.setHumidity(rs.getString("temp"));
					System.out.println(rs.getString("temp"));
					udvo.setWindSpeed(rs.getString("windSpeed"));
					System.out.println(rs.getInt("windSpeed"));
					udvo.setPressure(rs.getString("pressure"));
					System.out.println(rs.getInt("pressure"));
					udvo.setSensor_tag_value(rs.getString("sensor_tag_value"));
					System.out.println(rs.getString("sensor_tag_value"));
					udvo.setSensor_status(rs.getString("sensor_status"));
					System.out.println(rs.getString("sensor_status"));
					ls.add(udvo);
				}
		}
		catch(Exception e){
				e.printStackTrace();
		}
		return ls;		
	}
}
	
		
	



