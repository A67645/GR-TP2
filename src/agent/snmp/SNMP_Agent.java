package agent.snmp;

import agent.visual.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import java.util.Scanner;

import java.time.LocalDateTime;

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
			File json = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/config/"+ config_filename);
			conf = mapper.readValue(json, Config.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveConfig(){
		ObjectMapper mapper = new ObjectMapper();

		try{
			File json = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/config/" + conf.getName() + ".json");
			mapper.writeValue(json, conf);
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestConfig(){

		System.out.println("...Wellcome to SNMP JAVA MONITOR!...\nWich configuration would you like to use?\n1 -> Default\n2 -> previous\n3 -> new");

		Scanner config_scanner = new Scanner(System.in);
		int config_opt = config_scanner.nextInt();
		switch (config_opt) {
			case 1 : // Load default configuration file
				loadConfig("default.json");
				break;
			case 2 : // Load a custom configuration file previoulsy saved
				File confs = new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/config/");
				String [] filenames = confs.list();
				if(filenames != null) {
					System.out.println("Wich custom configuration file would you like to load?");
					int i = 0;
					for (String file : filenames) {
						i++;
						System.out.println(i + " -> " + file);
					}
					int filenumber = config_scanner.nextInt();
					String filename = filenames[filenumber - 1];
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
				System.out.println("What is your desired SNMP version?");
				String new_snmpVersion = config_scanner.next();
				System.out.println("What is your desired community string?");
				String new_commString = config_scanner.next();
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

				conf =  new Config(new_name, new_ip, new_mask, new_port, new_snmpVersion, new_commString, new_polling_sec);
				System.out.println("Do you whish to save this new configuration?\ny -> yes\nn -> no");
				String opt = config_scanner.next();
				if(opt.equals("y")){
					saveConfig();
				}
		}
	}

	public void start(){
		requestConfig();
		System.out.println("ip = " + conf.getIP());
		System.out.println("mask = " + conf.getMask());
		System.out.println("port = " + conf.getPort());
		System.out.println("snmp version = " + conf.getSnmpVersion());
		System.out.println("community String = " + conf.getCommString());
		System.out.println("polling frequency (sec) = " + conf.getPolling());

		System.out.println("__________________________________________________");

		Processo p1 = new Processo("p1", 1, new ArrayList<Snapshot>());
		if(new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/"+ p1.getPName() + "_" + p1.getPID() + ".json").exists() == false) p1.saveProcesso();
		p1.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 10.0, 30.0));
		p1.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 21.0, 10.0));
		p1.saveProcesso();

		Processo p2 = new Processo("p2", 2, new ArrayList<Snapshot>());
		if(new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/"+ p2.getPName() + "_" + p2.getPID() + ".json").exists() == false) p2.saveProcesso();
		p2.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 19.0, 18.0));
		p2.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 10.0, 19.0));
		p2.saveProcesso();

		Processo p3 = new Processo("p3", 3, new ArrayList<Snapshot>());
		if(new File("/home/a67645/Área\\ de\\ Trabalho/MIEI/GR/GR-TP2/data/logs/"+ p3.getPName() + "_" + p3.getPID() + ".json").exists() == false) p3.saveProcesso();
		p3.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 9.0, 15.0));
		p3.addSnapshot(new Snapshot(LocalDateTime.now().toString(), 22.0, 23.0));
		p3.saveProcesso();

		Queries q = new Queries();
		q.topNCPU(2, LocalDateTime.parse("2020-01-04T18:51:46.879"), LocalDateTime.parse("2021-12-04T18:51:46.879"));
	}
}
