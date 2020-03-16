<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#listForm{
	width:640px;
	height: 500px;
	border: 1px solid red; 
	margin: auto;
	}
	h2 {
		text-align: center;
	}
	table {
		margin: auto;
		width: 550px;
	}
	.tr_top {
		background-color: lime;
	}
	.div_empty {
		background-color:orange;
		text-align: center;
	}
	.td_command {
		text-align: right;
	}
	#todayImageList {
		text-align: center;
	}
	#productImage {
		width: 150px;
		height: 150px;
		border: none;
	}
	#cartImage {
		width: 70px;
		height: 70px;
		border: none;
	}
	#select {
		text-align: right;
	}
	#commandList {
		text-align: center;
	}
	#upImage {
		width: 15px;
	}
	#downImage {
		width: 15px;
	}
</style>
<script type="text/javascript">
	function checkAll(theForm) {
		if (theForm.remove.length == undefined) {
			theForm.remove.checked = theForm.allCheck.checked;
		} else {
			for (var i = 0; i < theForm.remove.length; i++) {
				theForm.remove[i].checked = theForm.allCheck.checked;
			}
		}
	}
	function qtyUp(id) {
		// 수량 증가 버튼 클릭 시 해당 수량 값을 1 증가
		var element = document.getElementById('qty'+id);
		if(element.value >= 99) {
			element.value = 99;		// 최대 99
		} else {
			// 덧셈 연산자는 문자열 연결로 사용됨
			element.value = Number(element.value) + 1;
		}
	}
	function qtyDown(id) {
		var element = document.getElementById('qty'+id);
		if(element.value <= 1) {
			element.value = 1;		// 최소 1	
		} else {
			element.value = element.value - 1;
		}
	}
	function checkQty(qtyInput) {
// 		alert(qty);
		var qty = qtyInput.value;
		if(!(qty >= 1 && qty <= 99)){
			alert('수량을 1 ~ 99 사이의 값으로 입력하세요');
			qtyInput.value = 1;
		}
	}
	function qtyChange(cartId) {
		var element = document.getElementById('qty'+cartId);
		location.href ='DogCartQtyChange.dog?id=' + cartId + '&qty=' + element.value;
	}
</script>
</head>
<body>
<%
	int totalMoney = (Integer)request.getAttribute("totalMoney");
%>
<c:if test="${startMoney != null }">
	<c:set var="startMoney" value="${startMoney }"></c:set>
</c:if>
<c:if test="${endMoney != null }">
	<c:set var="endMoney" value="${endMoney }"></c:set>
</c:if>
<section id="listForm">
	<c:if test="${cartList != null && cartList.size()>0}">
	<h2>장바구니 목록</h2>
	<form method="post">
	<table>
	<tr id="select">
		<td colspan="6">
		<select id="startMoney" name="startMoney">
			<option>=최하=</option>
			<c:choose>
				<c:when test="${startMoney==1000 }">
					<option selected="selected">1000</option>
					<option>2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${startMoney==2000 }">
					<option>1000</option>
					<option selected="selected">2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${startMoney==3000 }">
					<option>1000</option>
					<option>2000</option>
					<option selected="selected">3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${startMoney==4000 }">
					<option>1000</option>
					<option>2000</option>
					<option>3000</option>
					<option selected="selected">4000</option>
				</c:when>
				<c:otherwise>
					<option>1000</option>
					<option>2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:otherwise>
			</c:choose>
		</select>
		<select id="endMoney" name="endMoney">
			<option>=최고=</option>
			<c:choose>
				<c:when test="${endMoney==1000 }">
					<option selected="selected">1000</option>
					<option>2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${endMoney==2000 }">
					<option>1000</option>
					<option selected="selected">2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${endMoney==3000 }">
					<option>1000</option>
					<option>2000</option>
					<option selected="selected">3000</option>
					<option>4000</option>
				</c:when>
				<c:when test="${endMoney==4000 }">
					<option>1000</option>
					<option>2000</option>
					<option>3000</option>
					<option selected="selected">4000</option>
				</c:when>
				<c:otherwise>
					<option>1000</option>
					<option>2000</option>
					<option>3000</option>
					<option>4000</option>
				</c:otherwise>
			</c:choose>
		</select>
		<input type="submit" value="검색" formaction="DogCartSearch.dog" />
		</td>
	</tr>
	<tr class="tr_top">
		<td><input type="checkbox" id="allCheck" name="allCheck" onclick="checkAll(this.form)"></td>
		<td>번호</td>
		<td>상품이미지</td>
		<td>상품명</td>
		<td>가격</td>
		<td>수량</td>
	</tr>
	
	<c:forEach var="cart" items="${cartList }" varStatus="status">
	
	<tr>
		<td>
			<input type="checkbox" id="remove" name="remove" value="${cart.id }" />
		</td>
		<td>
 		${status.index+1 } <%-- 번호값계산 --%>
		</td>
		<td>
			<img id="cartImage" src="images/${cart.image }">
		</td>
		<td>
			${cart.kind }
		</td>
		<td>
			${cart.price }
		</td>
		<td>
		<a href="javascript:qtyUp(${cart.id })">▲</a><br>
		<input type="text" name="qty" id="qty${cart.id }" size="1" value="${cart.qty }" onchange="javascript:checkQty(this)">
		<input type="button" value="변경" onclick="qtyChange(${cart.id})"><br>
		<a href="javascript:qtyDown(${cart.id })">▼</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" style="text-align: center;">
		총금액	: ${totalMoney }원
		</td>
	</tr>	
	<tr>
		<td colspan="5" style="text-align: center;">
			<input type="submit" value="삭제" formaction="DogCartRemove.dog">
		</td>
	</tr>
	</table>
	</form>
	</c:if>
	<c:if test="${cartList == null }">
		<section class="div_empty">
		장바구니가 비었습니다
		</section>
	</c:if>
	<nav id="commandList">
		<a href="DogList.dog">쇼핑계속하기</a>
	</nav>
</section>
</body>
</html>