package com.gdu.semiby4.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface SearchboardService {

  void loadboardSearchList(HttpServletRequest request, Model model);

}
