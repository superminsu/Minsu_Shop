package kr.inhatc.spring.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.inhatc.spring.item.dto.ItemFormDto;

@Controller
public class ItemController {

    @GetMapping("/admin/item/new")
    public String itemForm(Model model) {
        
        model.addAttribute("itemFormDto", new ItemFormDto());
        
        return "/item/itemForm";
    }
}
