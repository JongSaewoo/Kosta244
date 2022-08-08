package com.my.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReturnTest {
	@GetMapping("a1")
	public ModelAndView a() {
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("greeting", "HELLO");
		mnv.setViewName("/WEB-INF/jsp/a.jsp");
		return mnv;
	}
	@GetMapping("b1")
	public String b(Model model) {
		model.addAttribute("greeting", "안녕하세요");
		return "/WEB-INF/jsp/a.jsp";
	}
	@GetMapping("c1") //설정파일에 view-resolvers을 등록하였기에 자동으로 c1.jsp요청경로로 정해짐
	public void c() {
		
	}
	@GetMapping(value = "d1", produces = "text/plain;charset=UTF-8")
	@ResponseBody  //d()안에 내용을 그대로 리턴하고싶을때, 즉 리턴값을 그대로 응답하고싶을때 쓰임	
	public String d() {
		String responseData = "응답내용입니다";
		return responseData;  //뷰 이름으로 응답내용입니다를 반환
	}
	
}
