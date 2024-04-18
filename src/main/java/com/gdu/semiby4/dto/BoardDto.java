package com.gdu.semiby4.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.gdu.semiby4.dto.UserDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDto {
	int boardNo, hit, attachCount;
	String title, contents;
	Timestamp createDt, modifyDt;
	UserDto user;
}
