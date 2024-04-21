package com.gdu.semiby4.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gdu.semiby4.dto.UserDto;

public interface UserService {

  // 가입 및 탈퇴
  void signup(HttpServletRequest request, HttpServletResponse response);  
  void leave(HttpServletRequest request, HttpServletResponse response);

  // 로그인 및 로그아웃
  String getRedirectURLAfterSignin(HttpServletRequest request);  
  void signin(HttpServletRequest request, HttpServletResponse response);
  void signout(HttpServletRequest request, HttpServletResponse response);  
}
