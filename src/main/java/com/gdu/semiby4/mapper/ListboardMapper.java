package com.gdu.semiby4.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.semiby4.dto.AttachDto;
import com.gdu.semiby4.dto.BoardDto;

@Mapper
public interface ListboardMapper {
  
  int getBoardCount();
  List<BoardDto> getBoardList(Map<String, Object> map);
  BoardDto getBoardByNo(int boardNo);
  List<AttachDto> getAttachList(int uploadNo);
  int updateHit(int boardNo);
  
}