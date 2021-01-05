package agent.snmp;

public class Config{
	private String name;
	private String ip;
	private String mask;
	private int port;
	private String snmpVersion;
	private String commString;
	private int polling;

	public Config(){
		name = "default";
		ip = "192.168.0.0";
		mask = "255.255.255.0";
		port = 80;
		snmpVersion = "2c";
		commString = "GR2020";
		polling = 5;
	}

	public Config(String custom_name, String custom_ip, String custom_mask, int custom_port, String custom_snmpVersion, String custom_commString, int custom_polling){
		name = custom_name;
		ip = custom_ip;
		mask = custom_mask;
		port = custom_port;
		snmpVersion = custom_snmpVersion;
		commString = custom_commString;
		polling = custom_polling;
	}

	public Config(Config new_conf){
		name = new_conf.getName();
		ip = new_conf.getIP();
		mask = new_conf.getMask();
		port = new_conf.getPort();
		snmpVersion = new_conf.getSnmpVersion();
		commString = new_conf.getCommString();
		polling = new_conf.getPolling();
	}

	public String getName(){return name;}

	public String getCommString(){
		return commString;
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

	public String getSnmpVersion(){ return snmpVersion; }

	public int getPolling(){
		return polling;
	}

	public void setCommString(String n){
		commString = n;
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
}

