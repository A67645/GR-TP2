package agent.snmp;

public class SNMP_Agent{
	Config conf;

	public SNMP_Agent(){
		conf = new Config();
	}
	public void setConfig(Config new_conf){
		conf = new Config(new_conf);
	}

	public void loadConfig(String conf_filename){
		conf.loadConfig(conf_filename);
	}



	public void start(){
		conf.requestConfig();
	}
}
