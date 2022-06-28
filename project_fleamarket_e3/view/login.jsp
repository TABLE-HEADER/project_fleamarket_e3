<!--
プログラム名：login.jsp
作成者　　　：近藤
作成日　　　：2022/06/23
概要　　　　：画面フォームからログインをするjspファイル
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
//クッキーやリクエストスコープ取得用変数の宣言
String email = "";
String password = "";

String error = (String)request.getAttribute("error");
String message = (String)request.getAttribute("message");

//クッキーの取得
Cookie[]cookieList = request.getCookies();

//クッキーの有無
if(cookieList!=null){
	for(Cookie cookie:cookieList){
		//ユーザー情報の取得
		if(cookie.getName().equals("email")){
			email = cookie.getValue();
		}
		//パスワード情報の取得
		if(cookie.getName().equals("password")){
			password = cookie.getValue();
		}
	}
}

if(error == null){
	error = "";
}
if(message == null){
	message = "";
}

%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<title>ログイン</title>
		<style type="text/css">

		div{
			text-align:center;
		}
		table{
			margin-left:auto;
			margin-right:auto;
		}

		</style>
		<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">ログイン</h2>
			</div>
			<p><%=error %></p>
			<p><%=message %></p>

			<form action="<%=request.getContextPath()%>/login" method="POST" id="login_form">

				<table>
					<tr>
						<th>メールアドレス</th>
						<td> <input type="text" name="email" value="<%=email%>" required="required" > </td>
					</tr>



					<tr>
						<th>パスワード</th>
						<td><input type="password" name="password" value="<%=password%>"
							required="required" id="input_password">
							<span id="buttonEye" class="fa fa-eye" onclick="pushHideButton()"></span>
						</td>
					</tr>
				</table>

				<br>
				<input type="submit" value="ログイン" class="login_button">
			</form>


			<!-- 会員登録画面へ遷移 -->
				<div style="margin:30px auto 0px;">アカウントをお持ちでない方</div>
				<form action="<%=request.getContextPath()%>/view/register.jsp" method="POST">
					<input type="submit" value="会員登録" class="register_button">
				</form>

			<!--  ごめんなさい、この部分は消しておきますm(__)m -->
			<!-- 商品一覧画面へ遷移 -->
			<!--
			<form action="<%=request.getContextPath()%>/salesList" method="GET">
				<p align="center">
				<input type="submit" value="商品一覧">
				</p>
			</form> -->


			<script  type="text/javascript">
				function pushHideButton(){
					var txtPass = document.getElementById("input_password");
					var btnEye = document.getElementById("buttonEye");

					if (txtPass.type === "text") {
						txtPass.type = "password";
						btnEye.className = "fa fa-eye";
					} else {
						txtPass.type = "text";
						btnEye.className = "fa fa-eye-slash";
					}
				}
			</script>

		<!-- footer -->
			<%@include file="/common/footer.jsp"  %>

	</body>
</html>
