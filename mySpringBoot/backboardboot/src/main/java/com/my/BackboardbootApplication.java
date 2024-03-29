package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan 
//pom.xml에서 
	//<groupId>com.my</groupId>
	//<artifactId>backboardboot</artifactId>
	//<version>0.0.1-SNAPSHOT</version>
	//<packaging>war</packaging>
// 스프링부트에선 이 부분이 com.my패키지내에 클래스들을 자동 ComponentScan 해줌
// 즉, 다른패키지에서 따로 더 ComponentScan이 필요할경우 그 클래스에 @ComponentScan 활용
public class BackboardbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackboardbootApplication.class, args);
	}

}
