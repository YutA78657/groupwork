<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<%  List<OrderSet> orderSets = (List<OrderSet>)request.getAttribute("orderSets");%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="parent">
		<div class="box">
			<div class="title-box"><h1>注文履歴</h1></div>
			<div class="order-box">
			<%
			for(OrderSet orderSet :orderSets){
			%>
				<div class="order">
					<a><h1>注文番号：<%=orderSet.getOrder().getId() %></h1></a>
					<%
					List<OrderItem> orderItems = orderSet.getOrderItem(); 
					int totalPrice = 0;
					for(OrderItem orderItem:orderItems){
						totalPrice += orderItem.getPrice(); 
					%>
					<h2><%=orderItem.getTitle()%></h2>
					<%
					}
					%>
					<h2><%=totalPrice%></h2>
					
				</div>
			<%
			}
			%>
			
			</div>
		
		</div>
	
	</div>
</body>
</html>