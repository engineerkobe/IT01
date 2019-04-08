package EspServerSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ServerThread extends Thread {
	String resturnBook = "http://192.168.43.166:8080/DemoWeb/ReturnBook?rfid=";
	ServerSocket serversocket = null;
	Socket socket = null;
	long serverThreadTime; 
	InputStreamReader inputstreamreader = null;
	OutputStream outputStream= null;
	
	ServerThread(Socket socket) {
		this.socket = socket;
		serverThreadTime = System.currentTimeMillis();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			String esp8266ip = socket.getInetAddress().getHostAddress();

			System.out.println(esp8266ip + ":已經連線");
			inputstreamreader = new InputStreamReader(socket.getInputStream());
			outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(inputstreamreader);
//	Writer wr = new Writer(outputstreamreader);
//		esp8266(bw,"ok");
//			sb.append(esp8266ip);
//	String esp8266Content = esp8266ip;
			// 接收字符串
//	socket.is 
//	socket.sendUrgentData(0xff);
//	while(true) {
//		if (socket.isConnected())
//	}
			boolean key = false;
			for (String tmp = ""; (tmp = br.readLine()) != null;) {
				// 把/r /n 替換成空白
				tmp = tmp.replace("\r|\n", "");
				if(tmp.equals("i\'m find")) {
					serverThreadTime = System.currentTimeMillis();
//					System.out.println("我還活著");
				}else {
					System.out.println(esp8266ip+" : "+tmp);
				}
////				if (tmp.indexOf("id") != -1) {
//					if ((key=!key)) {
//						System.out.println("還書失敗");
//						bw.write("NO");
//						bw.flush();
//					} else {
//						System.out.println("還書成功");
//						bw.write("YES");
//						bw.flush();
//					}
//				}
				if (tmp.indexOf("id") != -1) {
					Map<String, String> map = new HashMap<>();
					for (String values : tmp.split(",")) {
						String value[] = values.split("=");
						map.put(value[0], value[1]);
					}
					String rfid = map.get("id").toUpperCase();
					String book_case_name = map.get("book_case_name");
					if (returnBook(rfid, book_case_name)) {
						System.out.println("還書成功");
						outputStream.write("YES".getBytes());
						outputStream.flush();
					} else {
						System.out.println("還書失敗");
						outputStream.write("NO".getBytes());
						outputStream.flush();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isConnection() {
		return System.currentTimeMillis() - serverThreadTime > 30000;
	}
	
	public boolean returnBook(String rfid,String book_case_name) throws IOException {
		rfid =  resturnBook + rfid +"&book_case_name="+book_case_name;
		System.out.println(rfid);
		URL url = new URL(rfid);
		InputStream is = url.openConnection().getInputStream();
		ByteArrayOutputStream baso = new ByteArrayOutputStream();
		byte [] bytes = new  byte[1024];
		for(int tmp=0; (tmp=is.read(bytes)) !=-1;)
			baso.write(bytes,0,tmp);
		String getRespones = new String(baso.toByteArray());
//		System.out.println(getRespones);
		if(getRespones.equals("OK"))
			return true;
		
		return false;
	}
}
