package com.my.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Product;
import com.my.exception.FindException;

//스프링컨테이너(ApplicationContext)구동
@RunWith(SpringRunner.class)
//Spring 컨테이너용 XML파일 설정
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ProductOracleRepositoryTest {
	@Autowired
	private ProductRepository repository;
	
	@Test
	public void testSelectByProdNo() throws FindException {
		//fail("Not yet implemented");
		String prodNo = "C0001";
		String expectedProdName = "아메리카노";
		int expectedProdPrice = 4000;
		Product p = repository.selectByProdNo(prodNo);
		
		assertNotNull(p);
		assertEquals(expectedProdName, p.getProdName());
		//expectedProdName과 p.getProdName()값을 단정짓는다(성공이뜨면 값이 똑같다)
		assertEquals(expectedProdPrice, p.getProdPrice());
		assertTrue(expectedProdPrice == p.getProdPrice());
	}
	
	@Test
	public void testSelectAll() throws FindException {
		int expectedSize = 4;
		List<Product> list = repository.selectAll();
		assertTrue(expectedSize == list.size());
	}
}
