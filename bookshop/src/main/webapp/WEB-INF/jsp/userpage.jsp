<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.User"%>
<!DOCTYPE html>
<%
User user = (User) request.getAttribute("searchUser");
String mes1 = (String) request.getAttribute("mes1");
String mes2 = (String) request.getAttribute("mes2");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<script>
function searchAddress() {
	  const el = document.getElementById("zipcode");
	  console.log(el);

	  if (!el) {
	    alert("zipcode要素が見つかりません");
	    return;
	  }

	  let zip = el.value;
	  console.log("入力値:", zip);

	  zip = zip.replace(/[０-９]/g, s =>
	    String.fromCharCode(s.charCodeAt(0) - 0xFEE0)
	  );
	  zip = zip.replace(/[^\d]/g, "");

	  console.log("正規化後:", zip);

	  if (zip.length !== 7) {
	    alert("郵便番号は7桁で入力してください");
	    return;
	  }

	  fetch(
			  "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" +
			  encodeURIComponent(zip)
			)
			  .then(res => res.json())
			  .then(data => {
			    console.log(data);
			    if (data.results) {
			      const r = data.results[0];
			      address1.value = r.address1;
			      address2.value = r.address2;
			      address3.value = r.address3;
			    } else {
			      alert(data.message);
			    }
		});
	}

</script>

	<jsp:include page="header.jsp" />
	<div class="parent">
		<div class="box">
			<h1>マイページ</h1>
			<div class="user">
				<form action="${pageContext.request.contextPath}/admin/userpage"
					method="post">
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="id" value="<%=user.getId()%>">
					<div class="form-group">
				<%
				if (mes1 != null) {
				%>
						<div class="mes"><%=mes1%></div>
				<%
				}
				%>
						<label>ユーザネーム</label> <input type="text" name="name"
							value="<%=user.getName()%>">
					</div>

					<div class="form-group">
				<%
				if (mes2 != null) {
				%>
						<div class="mes"><%=mes2%></div>
				<%
				}
				%>
						<label>メールアドレス</label> <input type="text" name="mail"
							value="<%=user.getEmail()%>">
					</div>

					<div class="form-group">
						<label>郵便番号</label>
						<div class="address-area">
							<input type="text" size="7" name="address_number" id="zipcode"
								<%if (user.getAddressNum() != null) {%>
								value="<%=user.getAddressNum()%>" <%}%>>
							<button class="address-btn" type="button"
								onclick="searchAddress()">住所検索</button>

						</div>

					</div>

					<div class="form-group">
						<label>住所１</label> <input type="text" size="50" name="address1"
							id="address1" <%if (user.getAddress1() != null) {%>
							value="<%=user.getAddress1()%>" <%}%>>
					</div>

					<div class="form-group">
						<label>住所２</label> <input type="text" size="50" name="address2"
							id="address2" <%if (user.getAddress2() != null) {%>
							value="<%=user.getAddress2()%>" <%}%>>
					</div>

					<div class="form-group">
						<label>住所３</label> <input type="text" size="50" name="address3"
							id="address3" <%if (user.getAddress3() != null) {%>
							value="<%=user.getAddress3()%>" <%}%>>
					</div>

					<div class="form-group">
						<label>管理者権限</label> <select name="admin_flg" class="select-box">
							<option value="1" <%if (user.getAdminFlg() == 1) {%> selected
								<%}%>>管理者</option>
							<option value="0" <%if (user.getAdminFlg() == 0) {%> selected
								<%}%>>一般</option>
						</select>
					</div>

					<div class="form-actions">
						<input type="hidden" value="update" name="action"> <input
							type="submit" value="変更">
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="parent">
		<div class="box">
			<h1>退会</h1>
			<div class="user">
				<form action="${pageContext.request.contextPath}/admin/userpage"
					method="post">
					<input type="hidden" name="id" value="<%=user.getId()%>">
					<input type="hidden" name="action" value="delete">
					<div class="form-actions">
						<input type="submit" value="アカウント削除">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>