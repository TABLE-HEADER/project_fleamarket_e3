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
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<style type="text/css">
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
		</style>
		<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
		<script type="text/javascript">
			function pushHideButton(){
				const target = document.getElementById('visible_password');
				const target2 = document.getElementById('invisible_password');
				const button = document.getElementById('buttonEye');

				if (button.className === "fa fa-eye-slash") {
					target2.style.visibility = "visible";
					target.style.visibility = "hidden";
					button.className = "fa fa-eye";
				} else {
					target2.style.visibility = "hidden";
					target.style.visibility = "visible";
					button.className = "fa fa-eye-slash";
				}
			}
			function pushHideButton2(button, target) {
				var txtPass = document.getElementById(target);
				var btnEye = document.getElementById(button);
				if (txtPass.type === "text") {
					txtPass.type = "password";
					btnEye.className = "fa fa-eye";
				} else {
					txtPass.type = "text";
					btnEye.className = "fa fa-eye-slash";
				}
			}
		</script>
	</head>

	<body id="wrapper">

		<!-- header -->
			<%@include file="../common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">ユーザー情報更新</h2>
			</div>

			<% if(error != null){ %>
				<div style="text-align:center; border:2px solid red; width:800px; margin:auto;">
					<% for(String e : error){ %>
					<p style="color:red"><%= e %></p>
					<%} %>
				</div>
			<%} %>
			<p style="color:blue;">現在の情報</p>
			<table class="list_table">
				<tr>
					<th>メールアドレス<br><span class="kome">※ログイン時に必要になります</span></th>
					<td><%= user.getEmail() %></td>
				</tr>
				<tr>
					<th>パスワード<br><span class="kome">※ログイン時に必要になります</span></th>
					<td>
						<span id="invisible_password">●●●●
							<span id="visible_password"><%= user.getPassword() %></span>
						</span>
						<span id="buttonEye" class="fa fa-eye" onclick="pushHideButton()"></span>
					</td>
				</tr>
				<tr>
					<th>ニックネーム<br><span class="kome">※他ユーザーに公開されます</span></th>
					<td><%= user.getNickname() %></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><%= user.getUsername() %></td>
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
						<span class="jusyo_support">町名・番地等</span>
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
					<th>クレジットカード番号</th>
					<td>
						<%= user.getCredit_number() %>
					</td>
				</tr>
			</table>
			<p style="font-size:1.6rem">▼</p>


			<p style="color:red;">更新情報を入力</p>

			<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
			<form action="<%=request.getContextPath()%>/userUpdate" method="post">
				<table class="list_table">
					<tr>
						<th>メールアドレス<br><span class="kome">※ログイン時に必要になります</span></th>
						<td>
							<input type="email" name="email"  value="<%= user.getEmail() %>" id="username"
							autocomplete="email" required="required" size="25">
						</td>
					</tr>
					<tr>
						<th>パスワード<br><span class="kome">※ログイン時に必要になります</span></th>
						<td>
							<input type="password" name="password" value="<%= user.getPassword() %>"
								 required="required" id="newPassword" size="25">
							<span id="buttonEye2" class="fa fa-eye" onclick="pushHideButton2('buttonEye2', 'newPassword')"></span>
						</td>
					</tr>
					<tr>
						<th>ニックネーム<br><span class="kome">※他ユーザーに公開されます</span></th>
						<td><input type="text" name="nickname" value="<%= user.getNickname() %>"
								required="required" size="25"></td>
					</tr>
					<tr>
						<th>お名前</th>
						<td><input type="text" name="username" value="<%= user.getUsername() %>"
							 required="required" size="25"></td>
					</tr>
					<tr>
						<th rowspan="5">住所</th>
						<td>
							<span class="jusyo_support">郵便番号</span>
							<input type="text" name="postal-code" value="<%= user.getPostal_code() %>"
							 required="required"
							maxlength="8" autocomplete="postal-code" class="p-postal-code">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">都道府県</span>
							<input type="text" name="address-level1" value="<%= user.getAddress_level1() %>" required="required"
							autocomplete="address-level1" class="p-region">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">市区町村</span>
							<input type="text" name="address-level2" value="<%= user.getAddress_level2() %>" required="required"
							autocomplete="address-level2" class="p-locality">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">町名・番地等</span>
							<input type="text" name="address-line1" value="<%= user.getAddress_line1() %>" required="required"
							autocomplete="address-line1" class="p-street-address">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">建物名・部屋番号</span>
							<input type="text" name="address-line2" value="<%= user.getAddress_line2() %>"
							autocomplete="address-line2" class="p-extended-address">
						</td>
					</tr>
					<tr>
						<th>クレジットカード番号</th>
						<td>
							<input type="text" name="creditNumber" value="<%= user.getCredit_number() %>" required="required"
							minlength="16" maxlength="16" pattern="[0-9]{16}"
							autocomplete="cc-number">
						</td>
					</tr>
				</table>

				<input type="reset" value="リセット" class="revise_button2">
				<br>
				<input type="submit" value="更新" class="login_button">
			</form>
			<br>

		<!-- footer -->
			<%@include file="../common/footer.jsp" %>
	</body>
</html>