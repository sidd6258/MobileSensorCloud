package Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseAccess {

	public void insert(String query){
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://sensorcloud.cm8ogkurdd27.us-west-2.rds.amazonaws.com:3306/sensorcloud?useSSL=false", "root","rootroot");
		System.out.println("inside weather package database access");
		Statement st = con.createStatement();
		st.executeUpdate(query);
		
		st.close();
		con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
