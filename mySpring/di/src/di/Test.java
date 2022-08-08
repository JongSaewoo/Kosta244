package di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductRepository;
import com.my.service.CustomerService;
import com.my.service.ProductService;

public class Test {

	public static void main(String[] args) {
		Product p1, p2;
//		p1 = new Product();
		//스프링컨테이너(의존성 주입 매개체<딜리버리 역할>) 구동
		String configurationPath = "configuration2.xml";	
		ClassPathXmlApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(configurationPath);
		
		//product객체 찾아 사용하기
		p1 = ctx.getBean("p", Product.class);
		System.out.println(p1.hashCode());
		System.out.println(p1);
		
		p2 = ctx.getBean("p", Product.class);
		System.out.println(p2.hashCode());
		System.out.println(p1 == p2);
		
		CustomerService service = ctx.getBean("customerService", CustomerService.class);
		System.out.println(service.hashCode());
		
		//서비스에 주입된 리포지토리 얻기
		ProductService pService = ctx.getBean("productService", ProductService.class);
		ProductRepository r1 = pService.getRepository();
		//컨테이너로 등록된 리포지토리 직접 찾기
		ProductRepository r2 = ctx.getBean("productRepository",ProductRepository.class);
		
		System.out.println(r1 == r2);
		
		try {
			Product p = r1.selectByProdNo("C0001");
			System.out.println(p);
		} catch (FindException e) {
			e.printStackTrace();
		}
	}

}
