package com.gdu.semiby4.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.semiby4.dto.BoardDto;

@Mapper
public interface SearchboardMapper {
  
  int getSearchCount(Map<String, Object> map);
  List<BoardDto> getSearchList(Map<String, Object> map);
  
}






