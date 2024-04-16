package com.gdu.semiby4.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.semiby4.dto.AttachDto;
import com.gdu.semiby4.dto.BoardDto;

@Mapper
public interface MainboardMapper {

  int insertBoard(BoardDto board);
  int insertAttach(AttachDto attach);
  
}
