package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.A;

public interface ARepository extends CrudRepository<A, String> {
	//CrudRepository<A, String> 
	// -> 첫번째인자는 엔티티가 될 클래스, 두번째인자는 그 클래스의 pk키의 리턴타입을 적는다
	//<String이 복합키일경우는 어떻게하나??>
	
	/**
	 * 쿼리메서드
	 * @param a4
	 * @return a4멤버변수와 매핑된 DB 한 행의 여러 컬럼들의 값
	 */
	List<A> findByA4(String a4); 
	//findByA4라는 메소드명을 카멜케이스로 잘 지켜야됨
	//A4는 a4멤버변수를 가리키지만 카멜케이스를 지켜야하므로 A4라고 써야함
	//쿼리메서드는 find~~, 뿐 아니라 다른것도 많음
	//쿼리메서드의 자동매핑은 join이 사용된 테이블에선 사용 불가능하므로 JPQL을 공부 
	//쿼리메서드 유형은 책261p 참조
	// 조인문에서는 JPQL을 이용. JPQL이란 쿼리어노테이션(ex:@Query)사용.
	// JPQL문법은 SQL하고 조금다름, *도 못쓰며 테이블을 READ하는게 아니라
	// 클래스엔터티를 READ하므로 책275p에 @Query안에 select절을 참고
	// ★부분 프로퍼티는 사용 하려면 (SELECT a1, a4 ... )
	//   메소드 호출부분에서 public testA(); 가 아니라 public Object[] testA();
	// 하지만 이 방법은 복잡하고 이를 더 권장하는 방법은 nativeQuery를 쓰는것이다
	// ex : @Query(테이블 READ SQL문, nativeQuery = true)
	
	/**
	 * 쿼리메서드
	 * @param word
	 * @return
	 */
	List<A> findByA4Like(String word);
}
