import java.util.Scanner;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.ProductListRepository;

public class ProductManager {

	public static void main(String[] args) {
		ProductListRepository repository;
		repository = new ProductListRepository();
//		try {
//		repository.insert(new Product("D0001", "상풍1", 1000));
//		}catch(AddException e) {
//			
//		}
//		try {
//		repository.insert(new Product("D0002", "상풍2", 1000));
//		}catch(AddException e) {
//			
//		}
//		try {
//		repository.insert(new Product("D0003", "상풍3", 1000));
//		}catch(AddException e) {
//			 
//		}
//		try {
//		repository.insert(new Product("D0002", "상풍4", 4000)); //이미 존재하는 상품입니다
//		}catch(AddException e) {
//			
//		}
		
//		try {
//			List<Product> list = repository.selectAll();
//			for(int i = 0; i < list.size(); i++) {
//				Product p = list.get(i);
//	
//				System.out.println(p); //p.toString() 자동호출됨
//				}//end for
//			}catch(com.my.exception.FindException e) {
//				System.out.println(e.getMessage()); //상품이 없습니다
//----------------------------------------------------------------------------------------------
		Scanner sc = new Scanner(System.in);
		System.out.println("상품추가 테스트");
		System.out.println("상품번호:");
		String prodNo = sc.nextLine(); //입력된 엔터값이전 문자열
		
		System.out.println("상품명:");
		String prodName = sc.nextLine(); 
		
		System.out.println("상품가격:");
		String strPrice = sc.nextLine();
		int prodPrice = Integer.parseInt(strPrice);
		try {
			repository.insert(new Product(prodNo, prodName, prodPrice));
		}catch(AddException ae) {
			
		}
		
		try {
			Product p = repository.selectByProdNo("D0001");
			System.out.println(p); //상품번호는D0001, 상품명은 상품1, 가격은1000
		}catch(FindException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Product p = repository.selectByProdNo("없는상품번호");
			System.out.println(p); 
		}catch(FindException e) {
			System.out.println(e.getMessage()); //상품이 없습니다
		}
	}
}	
