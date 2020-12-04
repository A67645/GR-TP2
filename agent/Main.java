import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main{
	public void main(){
		System.out.printf("...Wellcome to SNMP JAVA MONITOR!...\nWich configuration would you like to use?\n1 -> Default\n2 -> new\n3 -> new\n");
		Scanner config_scanner = new Scanner(System.in);
		int config_opt = config_scanner.nextInt();
		SNMP_Agent agent =  new SNMP_Agent();

		switch (config_opt) {
			case 1 : // Load default configuration file
				Config default_config  = new Config();
				JSONArray def = (JSONArray) parser.parse(new FileReader("../config/default.json"));
				for(Object o : def){
					JSONObject conf = (JSONObject) o;
					String default_name = (String) conf.get("name");
					String default_ip = (String) conf.get("ip");
					String default_mask = (String) conf.get("mask");
					int default_port = (int) conf.get("port");
					int default_polling = (int) conf.get("polling");

					default_config.setConfig(default_name, default_ip, default_mask, default_port, default_polling);
				}
				agent.setConfig(default_config);
				break;
			case 2 : // Load a custom configuration file previoulsy saved
				File confs = new File("../config");
				String [] filenames = f.list();
				System.out.printf("Wich custom configuration file would you like to load?\n");
				int i = 0;
				for(String file : filenames){
					i++;
					System.out.printf(i + " -> " + file);
				}
				int filenumber = config_scanner.nextInt();
				break;
			case 3 : // Load a new Configuration and possibly save it onto a Json file
				System.out.printf("What is your desired configuration name?\n");
				String new_name = config_scanner.next();
				System.out.printf("What is your IP address?\n");
				String new_ip = config_scanner.next();
				System.out.printf("What is your subnet mask?\n");
				String new_mask = config_scanner.next();
				System.out.printf("What is your port?\n");
				int new_port = config_scanner.nextInt();
				System.out.printf("Would you desire slow, medium or fast polling?\n Select 1 for slow, 2 for medium or 3 for fast.\n");
				int new_polling = config_scanner.nextInt();
				int new_polling_sec;
				switch (new_polling){
					case 1:
						new_polling_sec = 10;
						break;
					case 3:
						new_polling_sec = 1;
						break;
					default:
						new_polling_sec = 5;
						break;
				}
				Config new_config = new Config(new_name, new_ip, new_mask, new_port, new_polling_sec);
				agent.setConfig(new_config);
				System.out.printf("Do you whish to save this new configuration?\ny -> yes\nn -> no\n");
				String opt = config_scanner.next();
				if(opt = "y"){
					
				}
				break;
			default:
				System.out.printf("Invalid Option!\n");
				break;
		}
	}
}
