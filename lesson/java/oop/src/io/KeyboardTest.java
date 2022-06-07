package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class KeyboardTest {
	public static void main(String[] args) {
		InputStream is =System.in; //노드스트림
//		try {
//			int readValue = is.read(); //byte단위 읽기
//			System.out.println(readValue + "=" + (char)readValue);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Reader r = new InputStreamReader(is); //필터스트림
		try {
			int readValue = -1;
			while((readValue = r.read()) != -1){ //char단위로 읽기
				System.out.println(readValue + "=" + (char)readValue);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
