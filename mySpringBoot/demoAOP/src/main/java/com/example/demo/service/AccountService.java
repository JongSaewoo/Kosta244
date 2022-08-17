package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.exception.ModifyException;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	public void open(Account a) {
		repository.save(a);
	}
	/**
	 * 입금한다
	 * @param no 계좌번호
	 * @param amount 입금액
	 * @throws ModifyException
	 */
	public void deposit(String no, int amount) throws ModifyException{
		Optional<Account> optA = repository.findById(no);
		optA.orElseThrow(()->new ModifyException(no+"계좌가 없습니다"));
//		optA.orElseThrow(()->new IllegalArgumentException(no+"계좌가 없습니다"));
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		a.setAccountBalance(aBalance + amount );
		repository.save(a);
		// JPARepository.save에서 정상처리된 경우 자동 commit()
		//                       실패한경우 자동 rollback() 
	}
	/**
	 * 출금한다
	 * @param no 계좌번호
	 * @param amount 출금액
	 * @throws ModifyException 
	 */
	public void withdraw(String no, int amount) throws ModifyException {
		Optional<Account> optA = repository.findById(no);
		Account a = optA.get();
		int aBalance = a.getAccountBalance(); 
		if(amount > aBalance ) {
//			throw new ModifyException("잔액이 부족합니다");
			throw new IllegalArgumentException("잔액이 부족합니다");
		}
		a.setAccountBalance(aBalance - amount );
		repository.save(a); 
		// JPARepository.save에서 정상처리된 경우 자동 commit()
		//                       실패한경우 자동 rollback()    
	}
	/**
	 * 계좌이체한다
	 * @param from
	 * @param to
	 * @param amount
	 * @throws ModifyException
	 */
	@Transactional(rollbackFor = ModifyException.class)
	//rollbackFor속성은 IllegalArgumentException같은 uncheckedException이 아닌
	// ModifyException(Exception을 상속받은 우리가만든 Exception)같은
	// checkedException속성을 이용하여 Transactional을 사용하고싶다면
	// rollbackFor속성을 이용한다.
	public void transfer(String from, String to, int amount) throws ModifyException{
		withdraw(from, amount);
		deposit(to, amount);
	}
}