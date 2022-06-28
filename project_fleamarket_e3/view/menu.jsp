<!--
作成日　2022.06.27
作成者　豊山
メニュー機能
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.Date, java.text.SimpleDateFormat"%> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
String message = "";

Date nowDate = new Date();
SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
String now = format.format(nowDate);

int nowHour = Integer.parseInt(now.substring(11, 13));

int random = (int)(Math.random()*100);

if(0 <= random && random <= 70){
	if(0 <= nowHour && nowHour <= 6){
		message = "遅くまでおつかれさまです。";
	}else if(7 <= nowHour && nowHour <= 10){
		message = "おはようございます。";
	}else if(11 <= nowHour && nowHour <= 16){
		message = "こんにちは。";
	}else if(17 <= nowHour && nowHour <= 23){
		message = "こんばんは。";
	}
}else if(random <= 80){
	message = "今日もおつかれさまです。";
}else if(random <= 90){
	message = "いつもご利用ありがとうございます。";
}else if(random <= 98){
	message = "口コミで広めてね。";
}else if(random <= 99){
	message = "今日は良い日になるかも！";
}
%>

<html>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>メニュー</title>
		<style type="text/css">
		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
			<p style="margin:30px;"><%= message %> <%= user.getNickname() %>さん</p>
			<div>
				<h2>メニュー</h2>
			</div>

			<!-- コンテンツ(本文) -->
				<%if(user.getAuthority() == true) {%>
				<div id="admin_login_form">
					<ul class="admin_main_menu_ul" >
						<a href="<%=request.getContextPath()%>/productList" ><li>商品一覧</li></a>
						<a href="<%=request.getContextPath()%>/userList"><li>ユーザー一覧</li></a>
						<a href="<%=request.getContextPath()%>/salesList"><li>売上一覧</li></a>
						<a href="<%=request.getContextPath()%>/logout" id="logout_link"><li style="color:red;">【ログアウト】</li></a>

				<% }else { %>
				<div id="login_form">
					<ul class="main_menu_ul">
						<a href="<%=request.getContextPath()%>/productList" ><li>商品一覧</li></a>
						<a href="<%=request.getContextPath()%>/buyList"><li>購入一覧</li></a>
						<a href="<%=request.getContextPath()%>/dealList"><li>取引一覧</li></a>
						<a href="<%=request.getContextPath()%>/myProductList"><li>出品一覧</li></a>
						<a href="<%=request.getContextPath()%>/view/myProductInsert.jsp"><li>出品する</li></a>
						<a href="<%=request.getContextPath()%>/userDetail"><li>ユーザー情報の変更</li></a>
						<a href="<%=request.getContextPath()%>/logout" id="logout_link"><li style="color:red;">【ログアウト】</li></a>

				<% } %>
					</ul>
				</div>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
			<script type="text/javascript">
				// ボタンを押したら
				document.getElementById('logout_link').onclick = function() {
					var result = window.confirm("ログアウトします。よろしいですか？");

					if(result){
						window.location.href = "http://localhost:8080/project_fleamarket_e3/logout";
					}else{
						// このページにとどまるので何も書いていない
					}
				}
			</script>
	</body>
</html>
