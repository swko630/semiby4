<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath() %>" />
<c:set var="dt" value="<%=System.currentTimeMillis() %>" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <!-- include libraries(jquery, bootstrap) -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

  <!-- include moment.js -->
  <script src="${contextPath}/resources/moment/moment-with-locales.min.js"></script>

  <title>상세화면</title>

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
  
  #board-title{
  margin-left: 30px;
  width: 990px;
  }
  
  #writer{
  margin-left : 30px;
  width: 250px;
  }  
  #create-dt{
  margin-left : 30px;
  width: 250px;
  }
  
  #contents{
  width: 990px;
  height: 400px;
  margin-left : 30px;
  }
  
  #comment-contents{
  width: 850px;
  }
  
  input{
   background-color: beige;
  }
  
  hr{
    border: 2px solid black;
  }
  
  button{
    margin-top: 10px;
   }
  


</style>

 <div>
 <a href="${contextPath}/main.page"> 메인으로 돌아가기 </a>
 </div>
 
<div id="main-wrap">

 
 <div id="title">
 <h1 class="title">상세화면</h1>
 <hr>
 </div>

  <div>
  
  <div>
  <input type="text" class="form-control" id="board-title" value="${board.title}" readonly>
  </div>
  
  <div>
  <label for="writer" ></label>
  <input type="text" class="form-control" id="writer" value="${board.user.email}" readonly>
  </div>
  
  <div>
  <input type="text" class="form-control" id="create-dt" value="${board.createDt}" readonly>
  </div>

  <div>
  <label for="contents" ></label>
  <input type="text" class="form-control" id="contents" value="${board.contents}" readonly>
  </div>
 
 </div>



  <hr>
  
  <div id="frm-comment">
  <form id="frm-comment">
    <textarea id="comment-contents" class="form-control" name="contents"></textarea>
    <input type="hidden" name="boardNo" value="${board.boardNo}">
    <c:if test="${not empty sessionScope.user}">  
      <input type="hidden" name="userNo" value="${sessionScope.user.userNo}">
    </c:if>
    <button type="button" id="btn-comment-register">댓글등록</button>
  </form>
  </div>
  
  
  <div id="comment-list"></div>
  <div id="paging"></div>
  



</div>

<script>



const fnCheckSignin = () => {
  if('${sessionScope.user}' === '') {
    if(confirm('Sign In 이 필요한 기능입니다. Sign In 할까요?')) {
      location.href = '${contextPath}/user/signin.do';
    }
  }
}

const fnRegisterComment = () => {    
  $('#btn-comment-register').on('click', (evt) => {
    fnCheckSignin();
    $.ajax({
      // 요청
      type: 'POST',
      url: '${contextPath}/board/registerComment.do',
      data: $('#frm-comment').serialize(),  
      // 응답
      dataType: 'json',
      success: (resData) => {  
        if(resData.insertCount === 1) {
          alert('댓글이 등록되었습니다.');
          $('#contents').val('');
          fnCommentList();
        } else {
          alert('댓글 등록이 실패했습니다.');
        }
      },
      error: (jqXHR) => {
        alert(jqXHR.statusText + '(' + jqXHR.status + ')');
      }
    })
    
  })
}


var page = 1;

const fnCommentList = () => {       
  $.ajax({
    type: 'get',
    url: '${contextPath}/board/comment/list.do',
    data: 'boardNo=${board.boardNo}&page=' + page,
    dataType: 'json',
    success: (resData) => {  
    console.log(resData.commentList); 
    let commentList = $('#comment-list');
      let paging = $('#paging');
      commentList.empty();
      paging.empty();
      if(resData.commentList.length === 0) {
        commentList.append('<div>첫 번째 댓글의 주인공이 되어 보세요</div>');
        paging.empty();
        return;
      }
      $.each(resData.commentList, (i, comment) => {
        let str = '';
        
        if(comment.depth === 0) {
          str += '<div>';
        } else {
          str += '<div style="padding-left: 32px;">'
        }

        str += '<span>';
        str += comment.user.email;
        str += '(' + moment(comment.createDt).format('YYYY.MM.DD.') + ')';
        str += '</span>';
        str += '<div>' + comment.contents + '</div>';

        if(comment.depth === 0) {
          str += '<button type="button" class="btn btn-success btn-reply" >답글</button>';
        }

         if(Number('${sessionScope.user.userNo}') === comment.user.userNo) {
           str += '<button type="button" class="btn btn-danger btn-remove" data=comment-no="' + comment.commentNo + '">삭제</button>';
         }
         
         /************************* 답글 입력 화면 *************************/
         if(comment.depth === 0) {    // 첫번째 댓글 후 대댓글을 달 수 없도록 조건을 주는 if 문: depth로 결정한다.
           str += '<div>';
           str +=   '<form class="frm-reply">';
           str +=     '<input type="hidden" name="groupNo" value="' + comment.groupNo + '">';
           str +=     '<input type="hidden" name="boardNo" value="${board.boardNo}">';  
           str +=     '<input type="hidden" name="userNo" value ="${sessionScope.user.userNo}">'; 
           str +=     '<textarea name="contents" placeholder="답글 입력"></textarea>';
           str +=     '<button type="button" class="btn btn-warning btn-register-reply">작성완료</button>';
           str +=   '</form>';         
           str += '</div>'; 
         }
         /******************************************************************/  
        str += '</div>';

        commentList.append(str);
      })

      paging.append(resData.paging);
    },
    error: (jqXHR) => {
      alert(jqXHR.statusText + '(' + jqXHR.status + ')');
    }
  })
}

const fnPaging = (p) => {     
  page = p;
  fnCommentList();
}


$('#contents').on('click', fnCheckSignin);
fnRegisterComment();
fnCommentList();


</script>

</body>
</html>
