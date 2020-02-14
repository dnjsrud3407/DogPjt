<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 id값이 있으면 index.jsp 페이지로 돌려보냄
	if(session.getAttribute("sid") != null){
		// 메세지를 띄우는 경우
	%>
		<script>
			alert("잘못된 접근입니다");
			location.href = "index.jsp";
		</script>
	<%
		// 메세지를 안 띄우는 경우
		// response.sendRedirect("");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#loginFormArea {
	margin: auto;
	width: 400px;
	height: 200px;
	border: 2px double purple;
	border-radius: 10px;
	text-align: center; 	
}
fieldset {
	text-align: center;
	border: none;
}
#selectButton {
	margin-top: 10px;
}
table {
 	width:380px;
 	margin: auto; 
}
.td_left {
	width: 180px;
}
.td_right {
	width: 200px;
}
</style>
</head>
<body>
	<section id="loginFormArea">
		<h1>로그인</h1>
		<form action="MemberLoginPro.me" method="post">
			<fieldset>
				<table>
					<tr>
						<td class="td_left">
							<label for="id">아이디 : </label>
						</td>
						<td class="td_right">
							<input type="text" name="id" id="id" required="required"/>
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="pass">비밀번호 : </label>
						</td>
						<td class="td_right">
							<input type="password" name="pass" id="pass" required="required">
						</td>
					</tr>
				</table>
				
				<input type="submit" value="로그인" id="selectButton">
			</fieldset>
		</form>
	</section>
</body>
</html>

























