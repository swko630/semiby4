package com.gdu.semiby4.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.semiby4.service.SearchboardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class SearchboardController {

    private final SearchboardService searchboardService;
    
    @GetMapping("/search.do")
    public String search(HttpServletRequest request, Model model) {
      searchboardService.loadboardSearchList(request, model);
      return "board/list";
    }
  
}

