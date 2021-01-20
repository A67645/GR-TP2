import agent.snmp.*;

import java.io.*;

public class Main{
	public static void main(String [] args){
		//"C:\\Users\\thech\\Desktop\\MIEI\\GR\\GR-TP2\\data\\logs\\"

		try {
			BufferedReader br = new BufferedReader(new FileReader("default.txt"));

			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("\n");

			FileWriter fw = new FileWriter("default.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println("____");
			pw.println("172.165.4.14");
			pw.println("180");
			pw.println("3");
			pw.println("2020gr");

			BufferedReader b = new BufferedReader(new FileReader("default.txt"));

			String l;
			while ((l = b.readLine()) != null) {
				System.out.println(l);
			}

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}
}
