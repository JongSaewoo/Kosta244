package net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPEchoClientTest {
	public static void main(String[] args) {
		String serverIP = "127.0.0.1";
		int serverPORT = 5432;
		Socket s = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		Scanner sc = new Scanner(System.in);
		try {
			s = new Socket(serverIP, serverPORT);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
			String sendData = null;
			do {
				sendData = sc.nextLine();
				System.out.println(sendData);
				dos.writeUTF(sendData);
				String receiveData = dis.readUTF();
				System.out.println(receiveData);
			}while(!sendData.equals("quit"));
		
		} catch (UnknownHostException e) {
			System.out.println("IP가 잘못되었거나 호스트명이 잘못되었습니다");
		} catch(ConnectException e) {
			System.out.println("서버와의 연결이 실패되었습니다");
		} catch(SocketException se) {
			System.out.println("소켓이 끊겼습니다. 서버장애인가 확인하세요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
