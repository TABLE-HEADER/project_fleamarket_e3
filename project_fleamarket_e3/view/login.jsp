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
		<title>ログイン画面</title>
		<style type="text/css">

	    div{
	        text-align:center;
	    	}
	    table{
	        margin-left:auto;
	        margin-right:auto;
	    	}

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">ログイン画面</h2>
			</div>
			<p><%=error %></p>
			<p><%=message %></p>

			<form action="<%=request.getContextPath()%>/login" method="POST">

				<table>
						<tr>


							<th>メールアドレス</th>
							<td> <input type="text" name="email" value="<%=email%>" required="required" > </td>
						</tr>



						<tr>
							<th>パスワード</th>
							 <td><input type="password" name="password" value="<%=password%>" required="required"></td>
						</tr>


				</table>

				<p align="center">
				<input type="submit" value="ログイン">
				</p>
				</form>


				<!-- 会員登録画面へ遷移 -->
				<form action="<%=request.getContextPath()%>/register" method="POST">
				<p align="center">
				<input type="submit" value="会員登録">
				</p>
				</form>

				<!-- 商品一覧画面へ遷移 -->
				<form action="<%=request.getContextPath()%>/salesList" method="POST">
				<p align="center">
				<input type="submit" value="商品一覧">
				</p>
				</form>

        <!-- footer -->
			<footer class="footer">
				<a href="#"class="footer_a"><div class="bottom_button_right">ページトップへ</div></a>
				<a href="<%= request.getContextPath() %>/view/homePage.jsp" class="footer_a">
					<div class="bottom_button_right">ホームへ</div>
				</a>
				<hr class="hr"/>
				<p class="copyright">Copyright(C)2022 All Rights Reserved.</p>

	</body>
</html>
