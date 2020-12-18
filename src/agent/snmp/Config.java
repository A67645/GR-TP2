package agent.snmp;

import java.io.FileReader;
import java.io.Reader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class Config{
	private String name;
	private String ip;
	private String mask;
	private int port;
	private int polling;

	public Config(){
		name = "default";
		ip = "192.168.0.0";
		mask = "255.255.255.0";
		port = 80;
		polling = 5;
	}

	public Config(String custom_name, String custom_ip, String custom_mask, int custom_port, int custom_polling){
		name = custom_name;
		ip = custom_ip;
		mask = custom_mask;
		port = custom_port;
		polling = custom_polling;
	}

	public Config(Config new_conf){
		name = new_conf.getName();
		ip = new_conf.getIP();
		mask = new_conf.getMask();
		port = new_conf.getPort();
		polling = new_conf.getPolling();
	}

	public String getName(){
		return name;
	}

	public String getIP(){
		return ip;
	}

	public String getMask(){
		return mask;
	}

	public int getPort(){
		return port;
	}

	public int getPolling(){
		return polling;
	}
	public void setName(String n){
		name = n;
	}

	public void setIp(String i){
		ip = i;
	}

	public void setMask(String m){
		mask = m;
	}

	public void setPort(int p){
		port = p;
	}

	public void setPolling(int pl){
		polling = pl;
	}

	public void loadConfig(String config_filename){
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("../../../data/config/" + config_filename + ".json")) {

			JSONObject jsonObject = (JSONObject) parser.parse(reader);

			name = (String) jsonObject.get("name");
			ip = (String) jsonObject.get("ip");
			mask = (String) jsonObject.get("mask");
			port = (int) jsonObject.get("port");
			polling = (int) jsonObject.get("polling");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void setConfig(String n, String i, String m, int p, int po){
		name = n;
		ip = i;
		mask = m;
		port = p;
		polling = po;
	}

	// saves this Config to a new json file on data/config
	public void saveConfig(){
		JSONObject obj = new JSONObject();

		obj.put("name", name);
		obj.put("ip", ip);
		obj.put("mask", mask);
		obj.put("port", port);
		obj.put("polling", polling);

		try (FileWriter file = new FileWriter("../../../data/config/" + name + ".json")) {
			file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestConfig(){

		Scanner config_scanner = new Scanner(System.in);
		int config_opt = config_scanner.nextInt();

		System.out.printf("...Wellcome to SNMP JAVA MONITOR!...\nWich configuration would you like to use?\n1 -> Default\n2 -> new\n3 -> new\n");

		switch (config_opt) {
			case 1 : // Load default configuration file
				loadConfig("../../../data/config/default.json");
				break;
			case 2 : // Load a custom configuration file previoulsy saved
				File confs = new File("../../../data/config/");
				String [] filenames = confs.list();
				System.out.printf("Wich custom configuration file would you like to load?\n");
				int i = 0;
				for(String file : filenames){
					i++;
					System.out.printf(i + " -> " + file);
				}
				int filenumber = config_scanner.nextInt();
				String custom_file = filenames[filenumber-1];
				String filename = "../../../data/config/" + custom_file + ".json";
				loadConfig(filename);
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

				setConfig(new_name, new_ip, new_mask, new_port, new_polling_sec);
				System.out.printf("Do you whish to save this new configuration?\ny -> yes\nn -> no\n");
				String opt = config_scanner.next();
				if(opt.equals("y")){
					saveConfig();
				}
			default:
				System.out.printf("Invalid Option!\n");
				break;
		}
	}
}

