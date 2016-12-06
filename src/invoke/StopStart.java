package invoke;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.jcraft.jsch.JSchException;

import Weather.SendData;
import database.DatabaseAccess;

public class StopStart {

	public void startSensor(String user_id, String sensor_id) throws InterruptedException {
		// TODO Auto-generated method stub

		
		List<String> instancesToStop = new ArrayList<String>();
		instancesToStop.add(sensor_id);
		AmazonEC2 amazonEC2Client = new AmazonEC2Client(new BasicAWSCredentials("AKIAIJHJNQUEYNCX5X6A", "Z8IkgB6VDgG8eV+lRwfnNoe6Cx3VGr7+ew1Sd+jV"));
		amazonEC2Client.setEndpoint("ec2.us-west-2.amazonaws.com");
		 
		DescribeInstancesRequest ir=new DescribeInstancesRequest();
		ir.withInstanceIds(instancesToStop);
		DescribeInstancesResult ires=amazonEC2Client.describeInstances(ir);
		
		String state=ires.getReservations().get(0).getInstances().get(0).getState().getName().toString();

		System.out.println("state is: "+state);

		if(state.equals("running"))
		{
			System.out.println("Already Running");
		
		}
		else
		{
		
			StartInstancesRequest startIR = new StartInstancesRequest();
			startIR.setInstanceIds(instancesToStop);
			amazonEC2Client.startInstances(startIR);
		
			String state1=ires.getReservations().get(0).getInstances().get(0).getState().getName().toString();

			System.out.println("state is: "+state1);

		
		
				while(!state1.equals("running"))
				{
					ires=amazonEC2Client.describeInstances(ir);
					state1=ires.getReservations().get(0).getInstances().get(0).getState().getName().toString();
					System.out.println("Running state is: "+state1);
					Thread.sleep(5000);
				}
		
				
				String publicip = ires.getReservations().get(0).getInstances().get(0).getPublicIpAddress().toString();
				
				String query1 = "SELECT latitude,longitude from sensor_master where user_id='"+user_id+"' and sensor_id='"+sensor_id+"'";
				DatabaseAccess db = new DatabaseAccess();
				ArrayList<String> latlon = db.getlLatLong(user_id, sensor_id, query1);
				
				String query = "UPDATE `sensorcloud`.`sensor_master` SET `sensor_status`='Running' WHERE `sensor_id`='"+sensor_id+"';";
				
				DatabaseAccess db1 = new DatabaseAccess();
				db1.insert(query);
				
				SendData sd = new SendData();
				while(true){
						try {
							//i.createSensor(user_id, sensor_name, lat, lon);
								try {
									sd.getdata(Integer.parseInt(user_id), sensor_id, latlon.get(0), latlon.get(1));
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Thread.sleep(600000);
								System.out.println("added");
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						} //catch (ParseException e) {
								// TODO Auto-generated catch block
							//	e.printStackTrace();
					//}
						}
				
				
				

				
				
		}
		
		
		
	}

}
