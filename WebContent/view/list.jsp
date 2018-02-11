<%@page import="board.BoardDataBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- list.css -->
<link rel="stylesheet" href ="/MemberBoard/view/list.css">

<%
	/* BoardDBBean connect = new BoardDBBean();	//객체를 생성합니다. BoardDBBean메소드들을 쓰기위하여.
	connect.getConnection();		//연결
	connect.getUsers();				//db에서 꺼내옴. */
	//페이지 5개씩 목록하단 에 나오게 변수 설정 
	int pageSize = 5;
		//가입일자 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//pageNum을 받아서 if pageNum == null이면 1이됨.
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null || pageNum == ""){
			pageNum = "1";}	
		//현재 페이지에 받은 Num을 저장.
		int currentPage = Integer.parseInt(pageNum);
		//시작페이지 현재(1) = (1-1)*pagesize(5) + 1 => 1
		//끝페이지 현재(1) = (1 * 5) => 5
		
		// 현재(2) = (2-1)*5+1 => 6
		//끝페이지 = 2 * 5 = 10
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		/* BoardDBBean connect = new BoardDBBean();
		connect.getConnection();
		articleList = connect.getArticles(); */
		
		 		
%>

<html>
<script>
$(document).ready(function(){
    $(".form-control").popover({title: "Search Here", placement: "top"});
   })
</script>
  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 자바스크립트 -->

 
 <!-- html  -->    
 <div class="container-fluid">
    <div class="panel panel-success">
      <div class="panel-heading">
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-3">
            <h2 class="text-center pull-left" style="padding-left: 30px;"> <span class="glyphicon glyphicon-list-alt"> </span> 회원목록 </h2>
          </div>
          <div class="col-xs-9 col-sm-9 col-md-9">
            <div class="col-xs-12 col-sm-12 col-md-12">
              <div class="col-xs-12 col-md-4">
                <label> Search </label>
                <div class="form-group">
                  <div class="input-group">
                    <input type="text" class="form-control input-md" name="search">
                    <div class="input-group-btn">
                      <button type="button" class="btn btn-md btn-warning"> <span class=" glyphicon glyphicon-search"></span></button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="panel-body table-responsive">
        <table class="table table-hover">
          <thead>
            <tr>
              <th class="text-center"> No </th>
              <th class="text-center"> 이메일 </th>
              <th class="text-center"> 닉네임 </th>
              <th class="text-center"> 비밀번호 </th>
              <th class="text-center"> 등록일자 </th>
 
            </tr>
          </thead>

          <tbody>
          <% for(int i =0; i < articleList.size();i++){
        	  BoardDataBean article = (BoardDataBean)articleList.get(i); %>
        	  <tr class="edit" id="detail">
              <td id="no" class="text-center"> <%= article.getNum()%> </td>
              <td id="email" class="text-center">   <%= article.getEmail() %>  </td>
              <td id="name" class="text-center"> <%= article.getName() %> </td>
              <td id="passwd" class="text-center"> <%= article.getPasswd() %> </td>
              <td id="regdt" class="text-center">  <%= sdf.format(article.getRegdt()) %> </td>
            </tr>  
          <% } %>
          </tbody>
        </table>
      </div>

      <div class="panel-footer">
        <div class="row">
          <div class="col-lg-12">
            <div class="col-md-8">
              </div>
              <div class="col-md-4">
              <p class="muted pull-right"><strong> 토닥토닥 </strong></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>