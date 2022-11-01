package kr.inhatc.spring.thymeleaf.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.spring.item.dto.ItemDto;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/thymeleaf")
@Log4j2
public class ThymeleafController {

	@GetMapping(value="/ex1")
	public String ex1(Model model) {
		model.addAttribute("data", "왜 플로그인 안되");
		return "thymeleaf/ex1";
	}
	
	@GetMapping(value="/ex2")
	public String ex2(Model model) {
		ItemDto itemDto = new ItemDto();
		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setItemName("테스트 상품1");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		
		return "thymeleaf/ex2";
	}
	
	@GetMapping(value={"/ex3", "/ex4"})
	public void ex3(Model model) {
		
		List<ItemDto> list = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			ItemDto itemDto = new ItemDto();
			itemDto.setItemDetail("상품 상세 설명" + i);
			itemDto.setItemName("테스트 상품" + i);
			itemDto.setPrice(10000 + i);
			itemDto.setRegTime(LocalDateTime.now());
			list.add(itemDto);
		}
		
		model.addAttribute("list", list);
		
		//return "thymeleaf/ex3";
	}
	
	@GetMapping(value="/ex5")
	public String ex5(Model model,@Param("para1") String para1, String para2) {
		
		log.info("=========================>" + para1 + "," + para2);
		
		model.addAttribute("para1", para1);
		model.addAttribute("para2", para2);
		
		return "thymeleaf/ex5";
	}
	
	@GetMapping(value={"/ex6","/ex7"})
    public void ex6(Model model) {
        
    }
}
