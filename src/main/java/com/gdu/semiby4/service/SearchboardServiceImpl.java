package com.gdu.semiby4.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.semiby4.mapper.SearchboardMapper;
import com.gdu.semiby4.utils.MyPageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchboardServiceImpl implements SearchboardService {

  private final SearchboardMapper searchboardMapper;
  private final MyPageUtils myPageUtils;
  
  @Override
  public void loadboardSearchList(HttpServletRequest request, Model model) {
    
    String column = request.getParameter("column");
    String query = request.getParameter("query");
    
    // 검색 데이터 개수를 구할 때 사용할 Map
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("column", column);
    map.put("query", query);
    
    int total = searchboardMapper.getSearchCount(map);
    
    int display = 20;
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    myPageUtils.setPaging(total, display, page);
  }

}
