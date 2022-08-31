package egovframework.msa.sample.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import egovframework.msa.sample.service.CustomerApiService;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "getCustomerDetailFallback"
//	,commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500") }
//	,commandProperties = {@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="1"),
//					      @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50")}
	      
	) //@HystrixCommand을 통해 오류가 발생할시 getCustomerDetailFallback메소드로
	  // 응답되도록 예외를 가로채기함
	public String getCustomerDetail(String customerId) {
		return restTemplate.getForObject("http://localhost:8082/customers/" + customerId, String.class);
	}
	
	public String getCustomerDetailFallback(String customerId, Throwable ex) {
		System.out.println("Error:" + ex.getMessage());
		return "고객정보 조회가 지연되고 있습니다.";
	}
}