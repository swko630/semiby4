package com.gdu.semiby4.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

public interface ListboardService {
  
  void boardList(Model model);
  void boardListByNo(int boardNo, Model model);
  Map<String, Object> getAttachList(int boardNo);
  int updateHit(int boardNo);
  
}