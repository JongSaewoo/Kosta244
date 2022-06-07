package com.my.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;

/*
파일이름 : products.txt    /    products.dat     /      products.ser
txt : 어떠한 편집기에서도 보임.  '
	  D0001:아메리카노:10000
	  G0001:텀블러A:5000
	  F0001:샌드위치:3000
dat : 자료형끼리 저장 가능. 다른사람이 볼 수 없음
	  StringStringint
	  xxx@@@%%YY
	  YYY###$$
ser : 객체 단위로 저장 가능
	  객체1객체2객체3

-상품 추가시 (추가기능) 2 직접해보기 
  products.txt 파일이 없다면 현재 project 폴더에 파일 생성 fileoutputStream exist java.io.File. 
  파일 있다면 파일의 끝에 상품정보 (상품번호:상품명:가격) 
  insert구현 
  append기능도 되는지 
  1234 모두 되게 구현 
  
-상품 조회시 (전체조회) 1 
  products.txt 파일이 없거나 파일있지만 상품이 없으면 상품없습니다 예외 발생. 전체 조회 기반 
  products.txt에 상품이 있으면 상품들정보 출력 

- 상품번호로 조회	
- 검색어로 조회 
 */

public class ProductFileRepository implements ProductRepository{
	private String fileName = "products.txt";
												//Reader뿐만 아니라 Scanner도 같이 사용하여 진행
	public void insert(Product product) throws AddException{
		//file로 파일 생성. exist써서 false면 createNewFile() true면 
		//스캐너르 입력된 값 받아서 저장하기 
		//input으로 스캐너로 받아서 진행 
		//상품명, 상품번호, 상품가격 
		File prodFile = new File(fileName);
		FileWriter fw;
		String prdAll = product.toStringTwo();
		try {
			if (prodFile.exists() == true) {
			}else{
				prodFile.createNewFile();
			} 
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			fw = new FileWriter(prodFile, true);
			fw.write(prdAll);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public List<Product> selectAll() throws FindException{
		Scanner sc = null; 				//finally에 sc를 사용위해 sc를 try밖에서 선언해줌 
		try {
			sc = new Scanner(new FileInputStream(fileName));  //buffer도 가능 reader도 가능 
			List<Product> all = new ArrayList<>();  //하위 ArrayList의 도움을 받음  반환받은 Product를 리스트로 넣어야하기에 선언해줌 
			while(sc.hasNextLine()) {   //읽어올 줄이 존재한다면 (존재하면 true)
				String line = sc.nextLine();
				String [] arr = line.split(":", 3);			//잘라서 배열로 반환해라 
				String prodNo = arr[0]; 
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				Product p = new Product(prodNo, prodName, prodPrice);  //selectAll의 반환이 list<Product>이기에 Product로 반환 
				all.add(p);
			}
			if(all.size() == 0) {			//파일은 있는데 내용이 없는 경우 
				throw new FindException("상품이 없습니다");
			}
			return all;		//리턴되기 직전에 finally수행하고 돌아와서 return수행함 . 즉 all이 finally보다 늦게 나옴 
							//정상처리시 순서 : 1. try 구문 return 직전까지 2. finally 3. return all 
							//비정상처리시 순서 : 1. catch 구문의 e.printStackTrace 2. finally 3. throw new FindException 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FindException("상품이 없습니다");
		} finally {
			if(sc != null) {		//NullPointerException 잡아야됨 
				sc.close(); //스캐너를 클로즈해줌  반드시 써야함
			}
		}
	}

	public Product selectByProdNo(String prodNo) throws FindException{
		//입력된 prodNo를 파일에 저장된 prodNo와 비교해야함 
		//입력된 줄을 write로  가져와서 스플릿해서 배열에 넣고 prodNo따와서 비교ㅕ 
		//맞으면 해당 줄 인쇄 
		Scanner sc = null;
		try {
		sc = new Scanner(new FileInputStream(fileName));
			Product p = null;
			while(sc.hasNextLine()) {   //읽어올 줄이 존재한다면 (존재하면 true)
				String line = sc.nextLine();
				String [] arr = line.split(":", 3);			//잘라서 배열로 반환해라 
				String prodNum = arr[0]; 
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				p = new Product(prodNum, prodName, prodPrice);  //selectAll의 반환이 list<Product>이기에 Product로 반환 
				for(int i = 0; i < p.getProdNo().length(); i ++) {
					if(p.getProdNo().equals(prodNo)) {
						return p;
					}
				}
			}
			return p;
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
			throw new FindException("파일이 없습니다");
		} finally {
			if(sc != null) {		//NullPointerException 잡아야됨 
				sc.close(); //스캐너를 클로즈해줌  반드시 써야함
			}
		}
	}
	public List<Product> selectByProdNoOrProdName(String word) throws FindException{
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(fileName));
			List<Product> all = new ArrayList<>();  
			Product p = null;
			while(sc.hasNextLine()) {   //읽어올 줄이 존재한다면 (존재하면 true)
				String line = sc.nextLine();
				String [] arr = line.split(":", 3);			//잘라서 배열로 반환해라 
				String prodNum = arr[0]; 
				String prodName = arr[1];
				int prodPrice = Integer.parseInt(arr[2]);
				if(prodNum.contains(word) ||  prodName.contains(word)) {
					p = new Product(prodNum, prodName, prodPrice);
					all.add(p);
				}
			}
			if(all.size() == 0) {			//파일은 있는데 내용이 없는 경우 
				throw new FindException("상품이 없습니다");
			}
			return all;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FindException("상품이 없습니다");
		} finally {
			if(sc != null) {		
				sc.close(); 
			}
		}
	}	
}
