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

	public void setConfig(String n, String i, String m, int p, int po){
		name = n;
		ip = i;
		mask = m;
		port = p;
		polling = po;
	}
}

