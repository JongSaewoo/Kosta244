package egovframework.msa.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	int cnt=0;
	@GetMapping("/{customerId}")
	public String getCustomerDetail(@PathVariable String customerId) {

//1) exception test
		throw new RuntimeException("I/O Exception");
//2) timeout test : 기본 timeout값은 1000 (1초)
//		long milli = 3*1000;
//		try {
//			Thread.sleep(milli);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("request customerId :" + customerId);
//		return "[Customer id = " + customerId + " at " + System.currentTimeMillis() + "]";

//3)circuit open test
//		System.out.println("처리횟수:" + (++cnt));
//		throw new RuntimeException("I/O Exception");
	}
}
