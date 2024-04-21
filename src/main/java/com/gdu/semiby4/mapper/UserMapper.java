package com.gdu.semiby4.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.semiby4.dto.UserDto;

@Mapper
public interface UserMapper {
  UserDto getUserByMap(Map<String, Object> map);
  int insertUser(UserDto user);
  int deleteUser(int userNo);
}
