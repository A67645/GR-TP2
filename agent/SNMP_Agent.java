public class SNMP_Agent{
	private String ip;
	private String mask;
	private int port;
	private int polling;

	public SNMP_Agent(){
		ip = "";
		port = 80;
		polling = 1;
	}
	public void setConfig(Config conf){
		ip = conf.getIP();
		mask = conf.getMask();
		port = conf.getPort();
		polling = conf.getPolling();
	}
}
