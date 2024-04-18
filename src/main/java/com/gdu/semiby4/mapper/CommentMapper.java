package com.gdu.semiby4.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.semiby4.dto.BoardDto;
import com.gdu.semiby4.dto.CommentDto;

@Mapper
public interface CommentMapper {
  int getBoardCount();
  List<BoardDto> getBoardList(Map<String, Object> map);
  BoardDto getBoardByNo(int boardNo);
  int insertComment(CommentDto comment);
  int getCommentCount(int boardNo);
  List<CommentDto> getCommentList(Map<String, Object> map);
}