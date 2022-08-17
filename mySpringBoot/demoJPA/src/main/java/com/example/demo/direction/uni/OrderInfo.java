package com.example.demo.direction.uni;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name="order_info_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class OrderInfo {
	@Id
	@Column(name="order_info_no")
	private Long orderNo;
	private Date orderDt;
	private String orderId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//cascade속성덕분에 select와 영속성전이(상태변이)를 한꺼번(fetch)에 함 
	// fetchType.LAZY는 한 테이블 내에서만 조회할때 효과적임 
	@JoinColumn(name="order_line_no")
	private List<OrderLine>lines;
}