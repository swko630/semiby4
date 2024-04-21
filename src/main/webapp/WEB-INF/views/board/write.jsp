<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="dt" value="<%=System.currentTimeMillis()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 

<title>
게시글 작성화면
</title>


<!-- include libraries(jquery, bootstrap) -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

<!-- cdn 변경 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

  <style>
  #main-wrap{
  display: flex;
  flex-direction: column;
  align-items: center;
  }
  
  
  #title{
  width: 990px;
  margin-left : 30px;
  margin-bottom: 10px;
  }
  
  #contents{
  width: 990px;
  height: 400px;
  margin-left : 30px;
  }
  
  #files{
  width: 300px;
  margin-left : 30px;
  }
  
  #writer{
  width: 300px;
  margin-left : 30px;
  }
  
  #files-wrap{
  margin-top : 20px;
  margin-botton: 100px;
  }
  
  hr {
    border: 2px solid black;
}

  label {
    padding-left: 25px;
    margin-top: 20px;
  }
  
  #buttons {
    margin-left: 25px;
    margin-top: 10px;
  }
  
  #file-list {
    width: 300px;
    margin-left : 30px;
  }
  
  </style>
  



 <div class="gnb-wrap">
 
 <a href="${contextPath}/main.page"> 메인으로 돌아가기 </a>
 </div>
     
 <div id="main-wrap">
 <div id="title">
 <h1 class="title">글작성</h1>
 <hr>
 </div>
 
 
 <form id="frm-board-register"
       method="POST"
       enctype="multipart/form-data"
       action = "${contextPath}/board/register.do">
       
 <div>
  <label for="writer" >작성자</label>
  <input type="text" class="form-control" id="writer" value="${sessionScope.user.email}" readonly>
 </div>
 
 <div>
  <label for="title">제목</label>
  <input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력하세요">
 </div>      
 
 <div>
  <textarea id="contents" class="form-control" name="contents" placeholder="내용을 입력하세요"></textarea>
 </div>
 
 <div id="files-wrap">
  <label for="files">첨부</label>
  <input class="form-control" type="file" name="files" id="files" multiple>
 </div>
 
 <label for="attach-list">첨부파일목록</label>
 <div id="attach-list"></div>
 
 <div id="buttons">
 <input type="hidden" name="userNo" value="${sessionScope.user.userNo}">
 <button type="submit">작성완료</button>
 <a href="${contextPath}/board/list.do"><button type="button">작성취소</button></a>
 </div>
 
 </form>
 
</div> 
 
<script>

//제목 필수 입력 스크립트

const fnRegisterBoard = () => {

  document.getElementById('frm-board-register').addEventListener('submit', (evt) => {
    if(document.getElementById('title').value === '') {
      alert('제목은 필수입니다.');
      evt.preventDefault();
      return;
    }
  })
}
 
// 크기 제한 스크립트 + 첨부 목록 출력 스크립트
const fnAttachCheck = () => {
  document.getElementById('files').addEventListener('change', (evt) => {
    const limitPerSize = 1024 * 1024 * 10;
    const limitTotalSize = 1024 * 1024 * 100;
    let totalSize = 0;
    const files = evt.target.files;
    const attachList = document.getElementById('attach-list');
    attachList.innerHTML = '';
    for(let i = 0; i < files.length; i++){
      if(files[i].size > limitPerSize){
        alert('각 첨부 파일의 최대 크기는 10MB입니다.');
        evt.target.value = '';
        attachList.innerHTML = '';
        return;
      }
      totalSize += files[i].size;
      if(totalSize > limitTotalSize){
        alert('전체 첨부 파일의 최대 크기는 100MB입니다.');
        evt.target.value = '';
        attachList.innerHTML = '';
        return;
      }
      attachList.innerHTML += '<input type="text" class="form-control" id="file-list" value="'+ files[i].name + '" >';
    }
  })
}

fnAttachCheck();
fnRegisterBoard();
</script>
 

 



        
</body>
</html>
