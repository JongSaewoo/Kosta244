package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
class TCPReceiveThread extends Thread{
	private Socket s;
	private DataInputStream dis = null;
	private String clientIP = null;
	public TCPReceiveThread(Socket s, String clientIP) throws IOException{
		this.s = s;
		this.clientIP = clientIP;
		dis = new DataInputStream(s.getInputStream());
	}
	public void run() {
		try {
			String receiveData = null;
			while(!(receiveData = dis.readUTF()).equals("quit")) {
				System.out.println(clientIP + "가 보내준 내용:" + receiveData);
			}	
		} catch(SocketException e) {
		} catch(IOException e) {
		} finally {
			if(s != null) {
				try {
					s.close();
				}catch(IOException e) {
				}
			}
			System.out.print(clientIP==null?"클라이언트":clientIP);
			System.out.println("와 연결을 종료합니다");
		}
	}
}
class TCPSendThread extends Thread{
	private Socket s;
	private DataOutputStream dos = null;
	private String clientIP = null;
	public TCPSendThread(Socket s, String clientIP) throws IOException{
		this.s = s;
		this.clientIP = clientIP;
		dos = new DataOutputStream(s.getOutputStream());
	}
	public void run() {
		try {
			
			for(int i=1; i<1000; i++) {
				dos.writeUTF("서버가 보내는 메시지:" + i++);
				Thread.sleep((long)(Math.random()*5000));
			}
		} catch(InterruptedException e) {
		} catch(SocketException e) {
		} catch(IOException e) {
		} finally {
			if(s != null) {
				try {
					s.close();
				}catch(IOException e) {
				}
			}
		}
	}
}
public class TCPMultiServerTest {
	public static void main(String[] args) {
		int port = 5432;
		ServerSocket ss = null;
		
		try {
			//1. port열기
			ss = new ServerSocket(port);
			while(true){
				Socket s = null;
				String clientIP = null;
				try {
					//2. 클라이언트기다리기, 소켓생성
					s = ss.accept();
					InetAddress client = s.getInetAddress();
					clientIP = client.getHostAddress();
					// 새로운 스레드 생성 시작
					TCPSendThread t1 = new TCPSendThread(s, clientIP);
					TCPReceiveThread t2 = new TCPReceiveThread(s, clientIP); 
					t1.start();
					t2.start();
					System.out.println(clientIP + "가 접속했습니다");
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
