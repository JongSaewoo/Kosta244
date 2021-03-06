package com.my.repository;

import com.my.dto.Product;
import com.my.exception.AddException;
/**
 * 배열저장소
 * @author meoal
 *
 */
public class ProductArrayRepository {
	private Product[] products; //저장소
	private int cnt; //저장소에 저장된 상품수
	public ProductArrayRepository(){
		products = new Product[5];
	}
	public ProductArrayRepository(int size) {
		products = new Product[size];
	}
	
	/**
	 * 저장소에 상품을 추가한다
	 * @param product 상품
	 */
	
	public void insert(Product product) throws AddException { //throws ArrayIndexOutOfBoundsException {
		try {
			products[cnt] = product; //GoodCode(안정성이 있는 코드)
			cnt++;
//			this.products[cnt++] = product; //BadCode
			System.out.println("상품종류개수: " + cnt);
		}catch(ArrayIndexOutOfBoundsException e) {
//			System.out.println("저장소가 꽉찼습니다. 현재 상품 종류개수: " + cnt);
			throw new AddException("저장소가 꽉찼습니다. 현재 상품 종류개수: " + cnt);
		}
	}
	/**
	 * 저장된 상품들을 반환한다
	 * @return
	 */
	public Product[] selectAll() {
		//return this.products;
		Product[]result = new Product[cnt];
		for(int i = 0; i < cnt; i++) {
			result[i] = products[i];
		}
		return result;
	}
}
