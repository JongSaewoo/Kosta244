package com.my.service;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.repository.CustomerOracleRepository;
import com.my.repository.CustomerRepository;

public class CustomerService {
	private CustomerRepository repository;
	public CustomerService() {//??
		this.repository = new CustomerOracleRepository();
	}
	
	public Customer login(String id, String pwd) throws FindException {
		Customer customer = repository.selectById(id);
		if(!customer.getPwd().equals(pwd)) {
			throw new FindException();
		}
		return customer;
	}
	
	public void signup(Customer customer) throws AddException {
		repository.insert(customer);
	}
	
	public Customer iddupchk(String id) throws FindException {
		return repository.selectById(id);
	}
	
	//로그인스테이터스와 로그아웃은 Repository와 DB와의 직접적으로 할 일이 
	// 없기때문에 서비스에 만들필요가 없음
//	public Customer loginstatus() throws FindException {
//		
//	}
//
//	public Customer logout() throws FindException {
//		
//	}
}