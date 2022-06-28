<!--
作成日　2022.06.27
作成者　豊山
メニュー機能
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%

%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>メニュー</title>
<style type="text/css">
#wrapper {
	max-width: 970px;
	margin: 10px auto 0px;
	text-align: center;
	font-family: "游ゴシック Medium", "Yu Gothic Medium";
	font-weight: bold;
	color: #464646;
}
</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
					<div id="page_title">
						<h2>MENU</h2>
					</div>

			<!-- コンテンツ(本文) -->
					<%if(user.getAuthority() == true) {

						%>

					<a href="<%=request.getContextPath()%>/productList" >商品一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/userList">ユーザー一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/salesList">売上一覧</a><br><br><br>
					<a href="<%=request.getContextPath()%>/logout" id="logout_link">【ログアウト】</a><br><br>

					<% }else { %>

					<a href="<%=request.getContextPath()%>/productList" >商品一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/buyList">購入一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/dealList">取引一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/myProductList">出品一覧</a><br><br>
					<a href="<%=request.getContextPath()%>/view/myProductInsert.jsp">出品する</a><br><br><br>
					<a href="<%=request.getContextPath()%>/userDetail">ユーザー情報の変更</a><br><br>
					<a href="<%=request.getContextPath()%>/logout" id="logout_link">【ログアウト】</a><br><br>

					<% } %>
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
