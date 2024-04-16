package com.gdu.semiby4.service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.semiby4.dto.AttachDto;
import com.gdu.semiby4.dto.BoardDto;
import com.gdu.semiby4.dto.UserDto;
import com.gdu.semiby4.mapper.MainboardMapper;
import com.gdu.semiby4.utils.MyFileUtils;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@RequiredArgsConstructor
public class MainboardServiceImpl implements MainboardService {
  
  private final MyFileUtils myFileUtils;
  private final MainboardMapper mainboardMapper;

  @Override
  public boolean registerUpload(MultipartHttpServletRequest multipartRequest) {
    
    // Upload_T 테이블에 추가하기(ATTACH_T 삽입을 위해 이걸 가장 먼저 처리해줌)
    String title = multipartRequest.getParameter("title");
    String contents = multipartRequest.getParameter("contents");
    int userNo = Integer.parseInt(multipartRequest.getParameter("userNo"));
    
    UserDto user = new UserDto();
    user.setUserNo(userNo);
    
    BoardDto board = BoardDto.builder()
                          .title(title)
                          .contents(contents)
                          .user(user)
                        .build();
   
    int insertUploadCount = mainboardMapper.insertBoard(board);
    
    
   
    List<MultipartFile> files = multipartRequest.getFiles("files");  
    
    
    int insertAttachCount;
    if(files.get(0).getSize() == 0) {
      insertAttachCount = 1;         // 첨부가 없으면 files.size() 는 1 이다.
    } else {
      insertAttachCount = 0;         // 0으로 초기화 시켜놓고 있는 파일개수만큼 올라감.
    }
    
    for (MultipartFile multipartFile : files) {
      if(multipartFile != null && !multipartFile.isEmpty()) {     // null 아니고 공백 아니면
        
        String uploadPath = myFileUtils.getUploadPath();
        File dir = new File(uploadPath);
        if(!dir.exists()) {
          dir.mkdirs();
        }
        
        String originalFilename = multipartFile.getOriginalFilename();
        String filesystemName = myFileUtils.getFilesystemName(originalFilename);
        File file = new File(dir, filesystemName);
        
        try {
          multipartFile.transferTo(file); // 여기까지가 저장
          
          // 썸네일 작성
          String contentType = Files.probeContentType(file.toPath()); 
          int hasThumbnail = contentType != null && contentType.startsWith("image") ? 1 : 0;
          if(hasThumbnail == 1) {
            File thumbnail = new File(dir, "s_" + filesystemName); // 썸네일 이름은 smallsize란 뜻의 s_를 원래이름 앞에 붙여줌
            Thumbnails.of(file)            // 원본 이미지 파일
                      .size(96, 64)        // 가로 96px, 세로 64px. 사이즈는 1920/20 1280/20 이렇게 해서 정해줌...1/20 사이즈로 줄여넣음. 원본의 몇 % 이렇게 지정하는 방법도 있음. 사이트 참고~
                      .toFile(thumbnail);  // 썸네일 이미지 파일
          }
         
      
          AttachDto attach = AttachDto.builder()
                               .uploadPath(uploadPath)
                               .filesystemName(filesystemName)
                               .originalFilename(originalFilename)
                               .hasThumbnail(hasThumbnail)
                               .boardNo(board.getBoardNo())    
                               .build();
          
          insertAttachCount += mainboardMapper.insertAttach(attach);  // 여기는 for문 내부임. += 를 해줘야 한다.
          
        } catch (Exception e) {
          e.printStackTrace();
        }
      } // if 끝
    }   // for 끝
    return (insertUploadCount == 1) && (insertAttachCount == files.size());
    // 첨부파일이 없으면 사이즈가 = 1. 그래서 초기화값도 1
  }
    
  

}
