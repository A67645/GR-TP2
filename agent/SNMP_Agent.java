public class SNMP_Agent{
	private String ip;
	private int port;
	private int polling;

	public SNMP_Agent(){
		ip = "";
		port = 80;
		polling = 1;
	}
	public void setConfig(Config conf){
		ip = conf.getIP();
		port = conf.getPort();
		polling = conf.getPolling();
	}
}
