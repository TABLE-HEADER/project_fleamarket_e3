<%--
機能名：出品登録機能
作成者：中西りりな
作成日：2022/06/23
--%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>

<%
	ArrayList<String> error = (ArrayList<String>) request.getAttribute("error");
%>

<head>
<title>出品登録</title>
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

.footer {
	position: absolute;
	bottom: 10px;
	width: 970px;
	font-size: small;
}

.copyright {
	font-size: small;
	height: 25px;
}

.hr {
	clear: both;
	height: 2px;
	background-color: orange;
}

.bottom_button_right, .bottom_button_left {
	float: right;
	height: 25px;
	width: 150px;
	padding: 3px 5px 1px;
	margin: 10px 0px 0px 1px;
	border-top: 2px solid orange;
	border-right: 2px solid orange;
	border-left: 2px solid orange;
	border-radius: 8px 8px 0 0;
	background-color: #ffff0054;
	color: #464646;
	font-size: medium;
}

.footer_a {
	text-decoration: none;
}

.bottom_button_right:hover, .bottom_button_left:hover {
	cursor: pointer;
	background-color: #ffb70054;
}

.bottom_button_right:active, .bottom_button_left:active {
	border-top: 2px inset #ff6800;
	border-right: 2px inset #ff6800;
	border-left: 2px inset #ff6800;
}
.
</style>
</head>
<body id="wrapper">
	<!-- header -->
	<header>
		<div style="font-size: 2rem;">神田フリマ</div>
		<hr class="hr" />
	</header>

	<br>
	<h2 align="center">出品登録</h2>
	<!-- contents -->

	<%
		if (error != null) {
			for (int i = 0; i < error.size(); i++) {
	%>
	<font size="3" color="#ff0000"><%=error.get(i)%></font>
	<br>
	<%
		}
		}
	%>
	<form action="<%=request.getContextPath()%>/myProductInsert">
		<table border=0 align="center" summary="出品画面">
			<tr>
				<th style="background-color: #99FF66; width: 100">カテゴリ<font
					size="1" color="#ff0000">必須</font></th>
				<td><input type="text" name="category" required="required"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">商品名<font
					size="1" color="#ff0000">必須</font></th>
				<td><input type="text" name="productname" required="required"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">個数<font
					size="1" color="#ff0000">必須</font></th>
				<td><input type="text" name="stock" required="required"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">価格(単価)<font
					size="1" color="#ff0000">必須</font></th>
				<td><input type="text" name="price" required="required"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 180">出品地域(都道府県)<font
					size="1" color="#ff0000">必須</font></th>
				<td><input type="text" name="address_level1" required="required"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">備考</th>
				<td><textarea name="remark" rows="5" cols="23"></textarea></td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center"><input type="submit"
					value="登録"></td>
			</tr>

		</table>

	</form>


	<!-- footer -->
	<footer class="footer">
		<a href="#" class="footer_a"><div class="bottom_button_right">ページトップへ</div></a>
		<a href="<%=request.getContextPath()%>/view/homePage.jsp"
			class="footer_a">
			<div class="bottom_button_right">ホームへ</div>
		</a>
		<hr class="hr" />
		<p class="copyright">Copyright(C)2022 All Rights Reserved.</p>
	</footer>
</body>
</html>