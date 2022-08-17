package com.example.demo.direction.bi;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@EqualsAndHashCode
public class OrderLinePK implements Serializable{
	private static final long serialVersionUID = 1L;
	private OrderInfo orderInfo;
	private Product orderP;
}
