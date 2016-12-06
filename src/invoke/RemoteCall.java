package invoke;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.concurrent.TimeUnit;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



public class RemoteCall {

	@SuppressWarnings("static-access")
	public void runScript(String ipaddress, String user_id, String sensor_id, String lat,String lon) throws IOException, InterruptedException, Exception {
		// TODO Auto-generated method stub

		JSch jsch=new JSch();
		
		//change after deploying to ec2
	String currentDirectory;
	/*	currentDirectory = System.getProperty("user.dir")+"\\WebContent\\KEYSENSOR.pem";
		
		*/
		File file = new File(".");
		currentDirectory = file.getAbsolutePath()+"\\WebContent\\sensor.pem";
		
		//jsch.addIdentity(currentDirectory);
		jsch.addIdentity("C:\\Users\\starlord\\workspaces\\MobileSensorCloud\\WebContent\\sensor.pem");
//		jsch.addIdentity("/usr/share/tomcat8/webapps/ROOT/KEYSENSOR.pem");
		jsch.setConfig("StrictHostKeyChecking", "no");
		//enter your own EC2 instance IP here
		Session session=jsch.getSession("ubuntu",ipaddress,22);
		
		

	//	session.setPassword("abc");
		
		int a=0;
		
	while(a<=10)
	{	
		try{
			Thread.sleep(60000);
			System.out.println("before session.connect");
			
			session.connect();
			System.out.println("after session.connect");
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
	//		BufferedReader in11=new BufferedReader(new InputStreamReader(channel.getInputStream()));
			System.out.println("After exec");
			channel.setCommand("./sensor.sh "+user_id+" "+sensor_id+" "+lat+" "+lon+" &");
			System.out.println("after channel.setcommand");
			channel.setErrStream(System.err);
			channel.connect();
			channel.disconnect();
			System.out.println("connected");
			a=11;
		}
		catch(Exception e){
			a++;
			//System.out.println("Reconnecting");
			e.printStackTrace();
			
			
		} 

	}	
		session.disconnect();

		
		
	}

	
	
	/*public static void main(String[] args)
	{
	
		try {
			runScript(null, null, null, null, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
	
}
