package com.gdu.semiby4.service;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.semiby4.mapper.ListboardMapper;
import com.gdu.semiby4.utils.MyFileUtils;
import com.gdu.semiby4.utils.MyPageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class ListboardServiceImpl implements ListboardService {
  
  private final ListboardMapper listboardMapper;
  private final MyPageUtils myPageUtils;
  private final MyFileUtils myFileUtils;

  @Override
  public void boardList(Model model) {
    Map<String, Object> modelMap = model.asMap();
    HttpServletRequest request = (HttpServletRequest) modelMap.get("request");
    
    int total = listboardMapper.getBoardCount();
    
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("10"));
    
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    myPageUtils.setPaging(total, display, page);
    
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    Map<String, Object> map = Map.of("begin", myPageUtils.getBegin()
                                   , "end", myPageUtils.getEnd()
                                   , "sort", sort);
    
    model.addAttribute("beginNo", total - (page - 1) * display);
    model.addAttribute("boardList", listboardMapper.getBoardList(map));
    model.addAttribute("paging", myPageUtils.getPaging(request.getContextPath() + "/board/list.do", sort, display));
    model.addAttribute("display", display);
    model.addAttribute("sort", sort);
    model.addAttribute("page", page);
  }

  @Override
  public void boardListByNo(int boardNo, Model model) {
    
    model.addAttribute("board", listboardMapper.getBoardByNo(boardNo));
    model.addAttribute("attachList", listboardMapper.getAttachList(boardNo));
    
  }
  
  @Override
  public Map<String, Object> getAttachList(int boardNo) {
      return Map.of("attachList", listboardMapper.getAttachList(boardNo));
  }
}
