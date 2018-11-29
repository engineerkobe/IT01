package EspServerSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	static String ReturnBooksUrl="http://192.168.43.166:8080/DemoWeb/Returningbooks";
	/**
	 * 
	 * @param args
	 */
	public static void main(String []args) {
		ServerSocket serversocket = null;
		Socket socket = null;
		InputStreamReader inputstreamreader= null;
		OutputStreamWriter outputstreamreader= null;
		StringBuffer sb = new StringBuffer();
//		URL url = new URL("");
		try {
				serversocket = new ServerSocket(3030);
				socket = serversocket.accept();
				String esp8266ip = socket.getInetAddress().getHostAddress();
				System.out.println(esp8266ip+":已經連線");
				inputstreamreader = new InputStreamReader(socket.getInputStream());
				outputstreamreader = new OutputStreamWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(inputstreamreader);
				BufferedWriter bw = new BufferedWriter(outputstreamreader);
				
				sb.append(esp8266ip);
				for(String tmp = "";(tmp = br.readLine()) != null;) {
					sb.append(tmp.replace("\r|\n",""));
				System.out.println(sb);
				bw.write("IDok");
				bw.flush();
					//for(String tmp2:sb.toString().split(":"))
						//System.out.println(tmp2);
					if(sb.toString().equals("exit"))	{
						inputstreamreader.close();
						outputstreamreader.close();
						System.out.println(socket.getInetAddress().getHostAddress()+"關閉連線");
						socket.close();
						break;
					}
					sb.setLength(0);
					sb.append(esp8266ip);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
