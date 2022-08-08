package com.my.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.dto.Customer;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("signup")
	@ResponseBody
	public Map signup(Customer c) {
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "가입실패");
		
		//비지니스로직 호출
		try {
			service.signup(c);
			map.put("status", 1);
			map.put("msg", "가입성공");
		} catch (AddException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@PostMapping("login")
	@ResponseBody
	public Map login(String id, String pwd, HttpSession session) {
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		session.removeAttribute("loginInfo");

		//비지니스로직 호출		
		try {
			Customer c = service.login(id, pwd);
			map.put("status", 1);
			session.setAttribute("loginInfo", id);
			//session에 id뿐아니라 모든 고객정보를 저장하고싶다면
			//session.setAttribute("loginInfo", c);
		} catch (FindException e) {
		}

		return map;
	}
	
	
	@PostMapping("iddupchk")
	@ResponseBody
	public Map iddupchk(String id) {
		Map<String, Object>map = new HashMap<>();
		map.put("status", 0);
		map.put("msg", "이미 사용중인 아이디입니다");

		try {
			Customer c = service.iddupchk(id);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", 1);
			map.put("msg", "사용가능한 아이디입니다");
		}

		return map;
	}
	
	@PostMapping("logout")
	@ResponseBody
	public String logout(HttpSession session) {
		session.removeAttribute("loginInfo"); 

		return null;
	}
}
