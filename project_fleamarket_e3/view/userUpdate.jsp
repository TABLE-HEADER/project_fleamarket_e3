<!-- 作成日　2022.06.24
作成者　岩田
ユーザー情報更新機能
-->

<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="java.util.ArrayList"%>

<%
ArrayList<String> error = (ArrayList<String>) request.getAttribute("error");
%>
<html>
<head>

<title>ユーザー情報更新</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<style type="text/css">
#wrapper {
	max-width: 970px;
	margin: 10px auto 0px;
	text-align: center;
	font-family: "游ゴシック Medium", "Yu Gothic Medium";
	font-weight: bold;
	color: #464646;
}
table {
	margin: 10px auto;
	border: 2px solid black;
	border-collapse: collapse;
}
th, td {
	border: 1px solid gray;
	padding: 5px;
}
td {
	text-align: left;
	width: 75%;
}
.jusyo_support {
	text-align: left;
	display: inline-block;
	white-space: nowrap;
	width: 40%;
}
.submit, .revise {
	padding: 15px;
	margin: 20px;
	border: 2px solid orange;
	border-radius: 15px;
	background-color: #ffff0054;
}
.revise {
	padding: 7px;
	background-color: #80808052;
}
.submit:hover {
	background-color: #bdbd0057;
}
.submit:active {
	border: 2px inset #ff6800;
}
.revise:hover {
	background-color: #80808078;
}
.revise:active {
	border: 2px inset #ff6800;
}
</style>
</head>

<body id="wrapper">

<!-- header -->
<%@include file="../common/header.jsp" %>

<!-- contents -->
<h1 align="center">ユーザー情報更新</h1>

			<% if(error != null){ %>
				<div style="text-align:center; border:2px solid red; width:800px; margin:auto;">
					<% for(String e : error){ %>
					<p style="color:red"><%= e %></p>
					<%} %>
				</div>
			<%} %>
			<p>現在の情報</p>
			<table>
				<tr>
					<th>お名前</th>
					<td><%= user.getUsername() %></td>
				</tr>
				<tr>
					<th>ニックネーム<br><span style="font-size:0.5rem;">※他ユーザーに公開されます</span></th>
					<td><%= user.getNickname() %></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><%= user.getPassword() %></td>
				</tr>
				<tr>
					<th rowspan="5">住所</th>
					<td>
						<span class="jusyo_support">郵便番号</span>
						<%= user.getPostal_code() %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">都道府県</span>
						<%= user.getAddress_level1() %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">市区町村</span>
						<%= user.getAddress_level2() %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">番地等</span>
						<%= user.getAddress_line1() %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">建物名・部屋番号</span>
						<%= user.getAddress_line2() %>
					</td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td>
						<%= user.getEmail() %>
					</td>
				</tr>
				<tr>
					<th>クレジットカード番号</th>
					<td>
						<%= user.getCredit_number() %>
					</td>
				</tr>
			</table>
		<p style="font-size:2rem">▼</p>


	<p>更新情報を入力</p>

	<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
	<form action="<%=request.getContextPath()%>/userUpdate" method="post">
		<table>
					<tr>
						<th>お名前</th>
						<td><input type="text" name="username" value="" required="required"></td>
					</tr>
					<tr>
						<th>ニックネーム<br><span style="font-size:0.5rem;">※他ユーザーに公開されます</span></th>
						<td><input type="text" name="nickname" value="" required="required"></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="password" value="" required="required"></td>
					</tr>
					<tr>
						<th rowspan="5">住所</th>
						<td>
							<span class="jusyo_support">郵便番号</span>
							<input type="text" name="postal-code" value="" required="required"
							maxlength="8" placeholder="<%= user.getPostal_code() %>" autocomplete="postal-code" class="p-postal-code">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">都道府県</span>
							<input type="text" name="address-level1" value="" required="required"
							placeholder="<%= user.getAddress_level1() %>" autocomplete="address-level1" class="p-region">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">市区町村</span>
							<input type="text" name="address-level2" value="" required="required"
							placeholder="<%= user.getAddress_level2() %>" autocomplete="address-level2" class="p-locality">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">番地等</span>
							<input type="text" name="address-line1" value="" required="required"
							placeholder="<%= user.getAddress_line1() %>" autocomplete="address-line1" class="p-street-address">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">建物名・部屋番号</span>
							<input type="text" name="address-line2" value=""
							placeholder="<%= user.getAddress_line2() %>" autocomplete="address-line2" class="p-extended-address">
						</td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td>
							<input type="email" name="email"  value=""
							placeholder="<%= user.getEmail() %>" autocomplete="email" required="required">
						</td>
					</tr>
					<tr>
						<th>クレジットカード番号</th>
						<td>
							<input type="text" name="creditNumber"  value=""
							minlength="16" maxlength="16" pattern="[0-9]{16}"
							placeholder="<%= user.getCredit_number() %>" autocomplete="cc-number">
						</td>
					</tr>
				</table>

			<input type="submit" value="更新">
		    <input type="reset" value="やり直し">
</form>
 <!-- footer -->
 <%@include file="../common/footer.jsp" %>

</body>
</html>