package EspServerSocket;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	static String ReturnBooksUrl = "http://192.168.43.166:8080/DemoWeb/Returningbooks";

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serversocket = null;
		try {
			serversocket = new ServerSocket(3030);
			List<ServerThread> tharray = new ArrayList<>();
//			serversocket.setSoTimeout(3000);
			while (true) {
				ServerThread st = new ServerThread(serversocket.accept());
				tharray.add(st);
				st.start();
				System.out.println("-------------");
				
				new Thread(new Runnable() {
					/*檢測連線是否停止的線程
					 * (non-Javadoc)
					 * @see java.lang.Runnable#run()
					 */
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (true)
							for (int i = tharray.size() - 1; i >= 0; i--) {
								ServerThread tmpTh = tharray.get(i);
								if (tmpTh.isConnection()) {
									tmpTh.interrupt();
									System.out.println(tmpTh.getName() + "已經斷線");
									tharray.remove(i);
								}
							}
					}
				}).start();
			}
			// 取得esp8266ip 地址
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
