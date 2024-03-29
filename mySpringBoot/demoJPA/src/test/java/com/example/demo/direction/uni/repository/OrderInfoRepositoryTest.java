package com.example.demo.direction.uni.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.direction.uni.OrderInfo;
import com.example.demo.direction.uni.OrderLine;
import com.example.demo.direction.uni.Product;
@SpringBootTest
class OrderInfoRepositoryTest {
	@Autowired
	private OrderInfoRepository repository;
	
	@Test
	void testInsert() {
		OrderInfo info = new OrderInfo();
		info.setOrderNo(3L); //3
		info.setOrderId("id1");
		info.setOrderDt(new Date());
		List<OrderLine> lines = new ArrayList<>();
		
			Product p = new Product();
			p.setProdNo("C0001");
			OrderLine line = new OrderLine();
			line.setOrderNo(info.getOrderNo());//3
			line.setOrderQuantity(1);
			line.setOrderP(p);
			lines.add(line);
		
		info.setLines(lines);
		repository.save(info);
	}

	@Test
	void testFindById() {
		Optional<OrderInfo> optInfo = repository.findById(3L);
		assertTrue(optInfo.isPresent());
		OrderInfo info = optInfo.get();
		String id = info.getOrderId();
		
		List<OrderLine> lines = info.getLines();
		OrderLine line = lines.get(0);
		Product p = line.getOrderP();
		int quantity = line.getOrderQuantity();
		assertEquals("id1", id);
		assertEquals("C1", p.getProdName());
		assertEquals(1, quantity);
	}
}
