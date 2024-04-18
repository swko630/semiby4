package com.gdu.semiby4.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.semiby4.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {
  
  private final CommentService commentService;
  
  @GetMapping("/detail.do")
  public String detail(@RequestParam int boardNo, Model model) {
    model.addAttribute("board", commentService.getBoardByNo(boardNo));
    return "board/detail";
  }
  
  @PostMapping(value="/registerComment.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> registerComment(HttpServletRequest request) {
    return ResponseEntity.ok(Map.of("insertCount", commentService.registerComment(request)));
  }
  
  @GetMapping(value="/comment/list.do", produces="application/json")
  public ResponseEntity<Map<String, Object>> commentList(HttpServletRequest request) {
    return ResponseEntity.ok(commentService.getCommentList(request));
  }
  
}