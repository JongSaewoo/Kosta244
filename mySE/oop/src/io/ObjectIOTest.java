package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

class A implements Serializable{ // Serializable interface를 적용해야 객체직렬화가 가능해짐
	int i;
	public A(int i) {
		this.i = i;
	}
	
}

public class ObjectIOTest {

	public static void write (String fileName) {
		/*
		 * destination : fileName
		 * nod stream : FileOutputStream
		 * filter stream : ObjectOutputStream
		 */
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(new Date()); // 객체직렬화
			oos.writeObject(new A(8)); // 객체직렬화
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void read(String fileName) {
		/*
		 * resource : fileName
		 * nod stream : FileInputStream
		 * filter stream : ObjectInputStream
		 */	
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			Object object = ois.readObject();  //객체逆직렬화
			System.out.println(object);
			A a = (A) ois.readObject(); //객체逆직렬화
			System.out.println(a.i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	public static void main(String[] args) {
		String fileName = "object_io_test.ser";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. 객체쓰기 / 2. 객체읽기 / 9. 종료");
			int opt = scanner.nextInt();
			switch (opt) {
			case 1:
				write(fileName);
				break;
			case 2:
				read(fileName);
				break;
			case 9:
				System.exit(0);
			}
		}
	}
}

class Product implements Serializable{ // Serializable interface로 객체직렬화 가능
	// field
	private String productNo;
	private String productName;
	transient private int productPrice; // 객체직렬화에서 productPrice 변수는 제외됨
	private String productInfo;
	private Date productMfd;
}