package com.my.dto;

import java.util.Date;
import java.util.List;
/**
 * 주문기본정보
 * @author sitsu
 */
public class OrderInfo {
	private int orderNo;	//주문번호
	private String orderId;	//주문자아이디
	private Date orderDt;	//주문일자
	private List<OrderLine> lines;
	
	public OrderInfo() {
		super();
	}
	
	public OrderInfo(String orderId, List<OrderLine> lines) {
		super();
		this.orderId = orderId;
		this.lines = lines;
	}

	public OrderInfo(int orderNo, String orderId, Date orderDt, List<OrderLine> lines) {
		super();
		this.orderNo = orderNo;
		this.orderId = orderId;
		this.orderDt = orderDt;
		this.lines = lines;
	}	
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDt() {
		return orderDt;
	}
	public void setOrderDt(Date orderDt) {
		this.orderDt = orderDt;
	}
	public List<OrderLine> getLines() {
		return lines;
	}
	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderNo=" + orderNo + ", orderId=" + orderId + ", orderDt=" + orderDt + ", lines=" + lines
				+ "]";
	}
	
}
