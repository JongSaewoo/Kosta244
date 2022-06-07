package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
class TCPBroadThread extends Thread{
	private Socket s;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private String clientIP = null;
	private List<TCPBroadThread> list;
	public TCPBroadThread(Socket s, List<TCPBroadThread> list) throws IOException{
		this.s = s;
		this.list = list;
		InetAddress client = s.getInetAddress();
		clientIP = client.getHostAddress();
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		broadcast(clientIP + "가 접속했습니다");
	}
	public void broadcast(String msg){
		for(int i=0; i<list.size(); i++) {
			TCPBroadThread t = list.get(i);
			try {
				t.dos.writeUTF(msg);
			}catch(Exception e) {
				
			}
		}
	}
	public void run() {
		try {
			String receiveData = null;
			while(!(receiveData = dis.readUTF()).equals("quit")) {
				broadcast(clientIP + ">" + receiveData);
			}	
		} catch(SocketException e) {
		} catch(IOException e) {
		} finally {
			list.remove(this);
			if(s != null) {
				try {
					s.close();
				}catch(IOException e) {
				}
			}
			broadcast(clientIP + "와 연결을 종료합니다");
		}
	}
}
public class TCPBroadServerTest {
	public static void main(String[] args) {
		int port = 5432;
		ServerSocket ss = null;
		
		try {
			//1. port열기
			ss = new ServerSocket(port);
			//List생성
			List<TCPBroadThread> list = new ArrayList<>();
			while(true){
				Socket s = null;
				
				try {
					//2. 클라이언트기다리기, 소켓생성
					s = ss.accept();
					
					//새로운 스레드 생성 
					TCPBroadThread t = new TCPBroadThread(s, list);
					list.add(t);
					//스레드 시작
					t.start();
				}catch(IOException e) {
					
				}
				
			}//end while(true)
		} catch (BindException e) {
			System.out.println(port+"포트가 이미 사용중입니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
