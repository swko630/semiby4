package com.gdu.semiby4.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.semiby4.dto.BoardDto;
import com.gdu.semiby4.dto.CommentDto;
import com.gdu.semiby4.dto.UserDto;
import com.gdu.semiby4.mapper.CommentMapper;
import com.gdu.semiby4.utils.MyPageUtils;
import com.gdu.semiby4.utils.MySecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentMapper commentMapper;
  private final MyPageUtils myPageUtils;
  
  @Override
  public ResponseEntity<Map<String, Object>> getBoardList(HttpServletRequest request) {
    
    int total = commentMapper.getBoardCount();
    int display = 10;
    int page = Integer.parseInt(request.getParameter("page"));
    myPageUtils.setPaging(total, display, page);
    Map<String, Object> map = Map.of("begin", myPageUtils.getBegin()
                                   , "end", myPageUtils.getEnd());
    
    return new ResponseEntity<>(Map.of("commentList", commentMapper.getBoardList(map)
                                     , "totalPage", myPageUtils.getTotalPage())
                              , HttpStatus.OK);
  }

  @Override
  public BoardDto getBoardByNo(int boardNo) {
    return commentMapper.getBoardByNo(boardNo);
  }

  @Override
  public int registerComment(HttpServletRequest request) {
    String contents = MySecurityUtils.getPreventXss(request.getParameter("contents"));
    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
    int userNo = Integer.parseInt(request.getParameter("userNo"));
    
    UserDto user = new UserDto();
    user.setUserNo(userNo);
    
    CommentDto comment = CommentDto.builder()
                             .contents(contents)
                             .user(user)
                             .boardNo(boardNo)
                          .build();
    
    return commentMapper.insertComment(comment);
  }

  @Override
  public Map<String, Object> getCommentList(HttpServletRequest request) {
    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
    int page = Integer.parseInt(request.getParameter("page"));
    
    int total = commentMapper.getCommentCount(boardNo);
    int display = 10;
    
    myPageUtils.setPaging(total, display, page);
    Map<String, Object> map = Map.of("boardNo", boardNo
                                   , "begin", myPageUtils.getBegin()
                                   , "end", myPageUtils.getEnd());
    return Map.of("commentList", commentMapper.getCommentList(map)
                , "paging", myPageUtils.getAsyncPaging());
  }

}
