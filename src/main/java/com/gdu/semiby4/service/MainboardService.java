package com.gdu.semiby4.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface MainboardService {

  public boolean registerUpload(MultipartHttpServletRequest multipartRequest);
  
}
