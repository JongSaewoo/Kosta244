package egovframework.msa.sample.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import egovframework.msa.sample.service.CustomerApiService;

@Service
public class CustomerApiServiceImpl implements CustomerApiService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "getCustomerDetailFallback"
//	,commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500") }
	,commandProperties = {@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="1"),
					      @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50")}
	      
	)
	public String getCustomerDetail(String customerId) {
		//return restTemplate.getForObject("http://localhost:8082/customers/" + customerId, String.class);
		return restTemplate.getForObject("http://customer/customers/" + customerId, String.class);
	}
	
	public String getCustomerDetailFallback(String customerId, Throwable ex) {
		System.out.println("Error:" + ex);
		return "고객(" + customerId + ")정보 조회가 지연되고 있습니다.";
	}
}
