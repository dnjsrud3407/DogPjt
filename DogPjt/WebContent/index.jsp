<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sid = null;
	
	// 세션 id 가 존재할 경우 세션 id 저장
	if(session.getAttribute("sid") != null){
		sid = (String)session.getAttribute("sid");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header {
	text-align: right;
}
</style>
</head>
<body>
	<header>
		<%
		if(sid == null){
		%>
			<a href="MemberloginForm.me">로그인</a>	| 
			<a href="MemberJoinForm.me">회원가입</a>	
		<%
		} else {
		%>
			<%=sid %>님 | <a href="MemberLogOut.me">로그아웃</a>	
		<%
		}
		%>
	</header>
	<h1><a href="DogList.dog">강아지 목록</a></h1>
</body>
</html>