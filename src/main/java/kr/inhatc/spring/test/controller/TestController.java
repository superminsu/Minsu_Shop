package kr.inhatc.spring.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.test.dto.TestDto;

@RestController
public class TestController {

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello World 123";
	}
	
	//웹으로 객체(TestDto) 반환 시 json(key:value)형태로 전달됨
	@GetMapping(value = "/test")
	public TestDto test() {
		TestDto dto = new TestDto();
		dto.setName("장민수");
		dto.setAge(25);
		return dto;
	}
}
