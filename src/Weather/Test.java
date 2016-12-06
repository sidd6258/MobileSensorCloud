package Weather;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


public class Test {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
	
		SendData sd = new SendData();
		
		while(true){
			
//	sd.getdata(args[0],args[1],args[2],args[3],args[4]);
		sd.getdata(11,"i-09588206345c873cd","37.37","-121.83");
		
	//	File f =new File("../WebContent");
		
			try {
				Thread.sleep(600000);
				System.out.println("added");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
