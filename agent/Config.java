public class Config{
	private String ip;
	private int port;
	private int polling;

	public Config(){
		ip = "0.0.0.0";
		port = 80;
		polling = 1;
	}

	public Config(String custom_ip, int custom_port, int custom_polling){
		ip = custom_ip;
		port = custom_port;
		polling = custom_polling;
	}

	public String getIP(){
		String res = ip;
		return res;
	}

	public int getPort(){
		return port;
	}

	public int getPolling(){
		return polling;
	}
}

