package agent.snmp;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import java.util.ArrayList;

public class SNMP_Agent{
	Config conf;

	public SNMP_Agent(){
		conf = new Config();
	}
	public void setConfig(Config new_conf){
		conf = new Config(new_conf);
	}

	public void loadConfig(String config_filename) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			File json = new File(config_filename);
			conf = mapper.readValue(json, Config.class);
		} catch (JsonGenerationException ge) {
			ge.printStackTrace();
		} catch (JsonMappingException me) {
			me.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void saveConfig(){
		ObjectMapper mapper = new ObjectMapper();

		try{
			File json = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\config\\" + conf.getName() + ".json");
			mapper.writeValue(json, conf);
		}
		catch (JsonGenerationException ge) {
			ge.printStackTrace();
		}
		catch (JsonMappingException me) {
			me.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void requestConfig(){

		System.out.println("...Wellcome to SNMP JAVA MONITOR!...\nWich configuration would you like to use?\n1 -> Default\n2 -> previous\n3 -> new");

		Scanner config_scanner = new Scanner(System.in);
		int config_opt = config_scanner.nextInt();
		switch (config_opt) {
			case 1 : // Load default configuration file
				loadConfig("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\config\\default.json");
				break;
			case 2 : // Load a custom configuration file previoulsy saved
				File confs = new File("C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\config\\");
				String [] filenames = confs.list();
				if(filenames != null) {
					System.out.println("Wich custom configuration file would you like to load?");
					int i = 0;
					for (String file : filenames) {
						i++;
						System.out.println(i + " -> " + file);
					}
					int filenumber = config_scanner.nextInt();
					String custom_file = filenames[filenumber - 1];
					String filename = "C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\config\\" + custom_file;
					loadConfig(filename);
				}
				break;
			default : // Load a new Configuration and possibly save it onto a Json file
				System.out.println("What is your desired configuration name?");
				String new_name = config_scanner.next();
				System.out.println("What is your IP address?");
				String new_ip = config_scanner.next();
				System.out.println("What is your subnet mask?");
				String new_mask = config_scanner.next();
				System.out.println("What is your port?\n");
				int new_port = config_scanner.nextInt();
				System.out.println("Would you desire slow, medium or fast polling?\n Select 1 for slow, 2 for medium or 3 for fast.");
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

				conf =  new Config(new_name, new_ip, new_mask, new_port, new_polling_sec);
				System.out.println("Do you whish to save this new configuration?\ny -> yes\nn -> no");
				String opt = config_scanner.next();
				if(opt.equals("y")){
					saveConfig();
				}
		}
	}
	public void start(){
		requestConfig();
		System.out.println(conf.getName());
		ArrayList<Snapshot> aux = new ArrayList<>();
		aux.add(new Snapshot(LocalDateTime.now().toString(), 10, 10));
		aux.add(new Snapshot(LocalDateTime.now().toString(), 20, 20));
		Processo p = new Processo("p1", 1, aux);

		System.out.println(p.getPName());
		System.out.println(p.getPID());
		for(Snapshot s : p.getUsages()){
			System.out.println(s.toString());
		}
		p.saveProcesso();
		p.loadProcesso(p.getPName());
		System.out.println(p.getPName());
	}
}