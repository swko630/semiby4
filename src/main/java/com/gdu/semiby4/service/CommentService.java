package com.gdu.semiby4.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.semiby4.dto.BoardDto;

public interface CommentService {
  ResponseEntity<Map<String, Object>> getBoardList(HttpServletRequest request);
  BoardDto getBoardByNo(int boardNo);
  int registerComment(HttpServletRequest request);
  Map<String, Object> getCommentList(HttpServletRequest request);
}