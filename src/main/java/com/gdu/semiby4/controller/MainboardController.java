package com.gdu.semiby4.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.semiby4.service.MainboardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class MainboardController {
	
  private final MainboardService mainboardService;


	@GetMapping("/write.page") // 내꺼
	public String writePage() {
		return "board/write";
	}

	@PostMapping("/register.do")  // 내꺼
	public String register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    return null;
  }
  
	
}
