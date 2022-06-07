package net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoServerTest {
	public static void main(String[] args) {
		int port = 5432;
		ServerSocket ss = null;
		
		try {
			//1. port열기
			ss = new ServerSocket(port);
			while(true){
				Socket s = null;
				DataInputStream dis = null;
				DataOutputStream dos = null;
				String clientIP = null;
				try {
					//2. 클라이언트기다리기, 소켓생성
					s = ss.accept();
					InetAddress client = s.getInetAddress();
					clientIP = client.getHostAddress();
					dis = new DataInputStream(s.getInputStream());
					dos = new DataOutputStream(s.getOutputStream());
					String receiveData = null;
					while(!(receiveData = dis.readUTF()).equals("quit")) {
//						System.out.println(clientIP + "가 보내준 내용:" + receiveData);
						dos.writeUTF(receiveData);
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
