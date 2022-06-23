<!--
プログラム名：login.jsp
作成者　　　：近藤
作成日　　　：2022/06/23
概要　　　　：エラー内容の表示画面
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
String error = (String)request.getAttribute("error");
if (error == null) {
	error = "";
}
String cmd = (String) request.getAttribute("cmd");
if (cmd == null) {
	cmd = "menu";
}
%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>エラー内容</title>
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
				<h2 style="margin:15px auto 10px;">！！エラーが発生しました！！</h2>
			</div>

		エラー内容は以下の通りです<br>
		<%=error %>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
