<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 세션 id값이 있으면 index.jsp 페이지로 돌려보냄
	if(session.getAttribute("sid") != null){
	%>
		<script>
			alert("잘못된 접근입니다");
			location.href = "index.jsp";
		</script>
	<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="./js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		// 버튼을 클릭했을 때 이벤트 발생
		// 이벤트 : string1.jsp에서 데이터를 가져와서 div태그에 덮어서 쓰기
		$('#idCheck').click(function () {
			var idValue = $('#id').val();
			$.ajax('./js/idCheck.jsp',{
				data: {id:idValue},
				success: function (data){
					$('#id_area').html(data);
				}
			});
		});
	});
	
	// 정규표현식(아이디, 패스워드)
	function checkId(id) {
		// 4 ~ 12 자리 아이디 영문, 숫자 유효성 검사
		// 1. 정규표현식 지정 - 숫자로 시작할 수 없음
		var regex = /^[A-Za-z][A-Za-z0-9]{3,11}$/;
		
		// 2. 체크 후 표시할 공간의 id 값 가져오기
		var element = document.getElementById('checkIdResult');
		
		// 3. 정규표현식을 통한 유효성 검사 수행
		if(regex.exec(id.value)){	// 유효성 검사를 통과했을 경우
// 			alert('유효성 검사를 통과');
			element.innerHTML = "사용 가능한 아이디";
			
		} else {					// 유효성 검사를 통과하지 못한 경우
// 			alert('유효성 검사를 탈락');
			element.innerHTML = "사용 불가능한 아이디";
		}
	}
	
	function checkPass(pass) {
		// 8 ~ 16 자리 아이디 영문, 숫자, 특수문자(!@#$%^_) 유효성 검사
		// 1. 정규표현식 지정 
		var lengthRegex = /^[A-Za-z0-9!@#$%^_]{8,16}$/;
		// - 대문자 체크
		var upperCaseRegex = /[A-Z]/;
		// - 소문자 체크
		var lowerCaseRegex = /[a-z]/;
		// - 숫자 체크
		var digitRegex = /[0-9]/;
		// - 특수문자 체크
		var specCharRegex = /[!@#$%^_]/;
		
		// 2. 체크 후 표시할 공간의 id 값 가져오기
		var element = document.getElementById('checkPassResult');
		
		// 3. 정규표현식을 통한 유효성 검사 수행
		if(lengthRegex.exec(pass.value) && upperCaseRegex.exec(pass.value) 
				&& lowerCaseRegex.exec(pass.value) && digitRegex.exec(pass.value) 
				&& specCharRegex.exec(pass.value)){	// 유효성 검사를 통과했을 경우
// 			alert('유효성 검사를 통과');
			element.innerHTML = "적합한 패스워드";
			
		} else {					// 유효성 검사를 통과하지 못한 경우
// 			alert('유효성 검사를 탈락');
			element.innerHTML = "적합하지 않은 패스워드";
		}
	}
	
</script>
<style type="text/css">
#joinFormArea {
	margin: auto;
	width: 600px;
	height: 300px;
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
	<section id="joinFormArea">
		<h1>로그인</h1>
		<form action="MemberJoinPro.me" method="post" onsubmit="return checkForm()">
			<fieldset>
				<table>
					<tr>
						<td class="td_left">
							<label for="id">아이디 : </label>
						</td>
						<td>
							<!-- onkeyup : 키보드를 눌렀다 뗄 경우 checkId(입력된 값) 호출-->
							<input type="text" name="id" id="id" required="required" 
							placeholder="4~12자리 영문, 숫자 조합" onkeyup="checkId(this)"/><br>
							<span id="checkIdResult"><!-- 정규표현식 검사 결과 출력 공간 --></span>
						</td>
						<td>
							<input type="button" name="idCheck" id="idCheck" value="중복체크"/>
						</td>
					</tr>
					<div id="id_area"></div>
					<tr>
						<td class="td_left">
							<label for="pass">비밀번호 : </label>
						</td>
						<td class="td_right">
							<input type="password" name="pass" id="pass" required="required" 
							placeholder="8~16자리 영문, 숫자, 특수문자 조합" onkeyup="checkPass(this)"><br>
							<span id="checkPassResult"><!-- 정규표현식 검사 결과 출력 공간 --></span>
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="name">이름 : </label>
						</td>
						<td class="td_right">
							<input type="text" name="name" id="name" required="required">
						</td>
					</tr>
					<tr>
						<td class="td_left">
							<label for="email">이메일 : </label>
						</td>
						<td class="td_right">
							<input type="email" name="email" id="email" required="required">
						</td>
					</tr>
				</table>
				
				<input type="submit" value="회원가입" id="selectButton">
				
			</fieldset>
		</form>
	</section>
</body>
</html>