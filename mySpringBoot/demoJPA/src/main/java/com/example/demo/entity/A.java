package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.core.sym.Name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "a_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@ToString
public class A {
	@Id
	@Column(name = "a1_c", length = 5)
	private String a1;
	
	@Column(name = "a2_c", precision = 5, scale = 2, nullable = true)
	private BigDecimal a2; //int a2 (X)
	
//	@CreationTimestamp //엔티티가 테이블에 INSERT되는 시점의 날짜데이터를 자동기록
	@UpdateTimestamp //엔티티가 테이블에 UPDATE되는 시점의 날짜데이터를 자동기록
	private Date a3;
	
	@Column(name = "a4_c", length = 10)
	@ColumnDefault(value = "1") //기본값 value
	private String a4; 
		
	@Transient //맵핑에서 제외 
	private String a5;
}
