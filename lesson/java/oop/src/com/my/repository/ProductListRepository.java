//사용자
package com.my.repository;

import java.util.ArrayList;
import java.util.List;

import com.my.dto.Product;
import com.my.exception.AddException;
import com.my.exception.FindException;
//import com.my.exception.ModifyException;

public class ProductListRepository {
	private List<Product> products;
	public ProductListRepository() {
		products = new ArrayList<>(); //10개의 index가 제공
	}
	public ProductListRepository(int size) {
		products = new ArrayList<>(size); //size개수의 index가 제공
	}
	public void insert(Product product) throws AddException{
		//String prodNo = product.getProdNo();
		int i=0;
		for(; i<products.size(); i++) {
//			Product p = products.get(i);
//			if(prodNo.equals(p.getProdNo())) {
//				break;
//			}
			if(product.equals(products.get(i))){
				break;
			}
		}
		if(i == products.size()) {
			products.add(product);
		}else {
//			System.out.println("이미 존재하는 상품입니다");
			throw new AddException("이미 존재하는 상품입니다");
		}
		//product의 prodNo와 
		//products저장소 각요소의 prodNo가 같으면
		//   syso("이미 존재하는 상품입니다");
		//                              같지않으면
		//   products.add(product);
		
	}
	/**
	 * 상품을 모두 검색한다
	 * @return 상품들
	 * @throws FindException 상품이 없으면 "상품이 없습니다"상세메시지를 갖는 예외가 발생한다
	 */
	public List<Product> selectAll() throws FindException{
		if(products.size() == 0) {
			throw new FindException("상품이 없습니다");
		}
		return products;
	}
	/**
	 * 상품번호로 상품검색한다
	 * @param prodNo 상품번호
	 * @return 상품객체
	 * @throws FindException 상품번호에 해당하는 상품이 없으면 "상품이 없습니다"상세메시지를 갖는 예외가 발생한다
	 */
	public Product selectByProdNo(String prodNo) 
			throws FindException{
//		for(int i=0; i<products.size(); i++) {
//			Product p = products.get(i);
		for(Product p: products) {
			if(p.getProdNo().equals(prodNo)) {
				return p;
			}
		}
		throw new FindException("상품이 없습니다");
	}
	
	/**
	 * 상품번호나 상품명에 검색어를 포함한 상품들을 반환한다
	 * @param word 검색어
	 * @return
	 * @throws FindException 검색어를 포함한 상품들이 없으면 "검색어를 포함한 상품이 없습니다"라는 상세메시지를 갖는 예외가 발생한다 
	 */
	public List<Product> selectByProdNoOrProdName(String word) throws FindException{
		List<Product> resultList = new ArrayList<>();
		for(int i=0; i<products.size(); i++) {
			Product p = products.get(i);
			String prodNo = p.getProdNo();
			String prodName = p.getProdName();
			if(prodNo.contains(word) ||  prodName.contains(word)) {
				resultList.add(p);
			}
		}
		if(resultList.size() == 0) {
			throw new FindException("상품이 없습니다");
		}
		return resultList;
	}

}