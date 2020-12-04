import java.util.Scanner;

public class Main{
	public void main(){
		System.out.printf("...Wellcome to SNMP JAVA MONITOR!...\nWich configuration would you like to use?\n1 -> Default\n2 -> Custom\n");
		Scanner config_scanner = new Scanner(System.in);
		int config_opt = config_scanner.nextInt();
		SNMP_Agent agent =  new SNMP_Agent();

		switch (config_opt) {
			case 1 :
				Config default_config  = new Config();
				agent.setConfig(default_config);
				break;
			case 2 :
				System.out.printf("What is your IP address?\n");
				String custom_ip = config_scanner.next();
				System.out.printf("What is your port?\n");
				int custom_port = config_scanner.nextInt();
				System.out.printf("Would you desire slow, medium or fast polling?\n Select 1 for slow, 2 for medium or 3 for fast.\n");
				int custom_polling = config_scanner.nextInt();
				int custom_polling_sec;
				switch (custom_polling){
					case 1:
						custom_polling_sec = 10;
						break;
					case 2:
						custom_polling_sec = 5;
						break;
					default:
						custom_polling_sec = 1;
						break;
				}
				Config custom_config = new Config(custom_ip, custom_port, custom_polling_sec);
				agent.setConfig(custom_config);
				break;
			default:
				System.out.printf("Invalid Option!\n");
				break;
		}
	}
}
