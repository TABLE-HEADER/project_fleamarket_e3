<!--
プログラム名：buyComplete.jsp
作成者　　　：近藤
作成日　　　：2022/06/24
概要　　　　：購入完了画面
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Deal,bean.Product,bean.User, util.MyFormat"%>

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
//BuyCompleteServletからリクエストスコープを取得する
String productid = request.getParameter("productid");
Deal deal = (Deal)request.getAttribute("deal");
User userObj = (User)request.getAttribute("user");

int quantity = 0;
int price = 0;

quantity = deal.getQuantity();
price = deal.getPrice();

MyFormat format = new MyFormat();
%>
<html>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>購入完了</title>
		<style type="text/css">
			div{
				text-align:center;
			}
			table{
				margin-left:auto;
				margin-right:auto;
			}
			td{
				padding-left: 20px;
			}

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">商品の購入が完了しました</h2>
			</div>

			<br>

			<table class="list_table">
				<tr>
					<th>商品名</th>
					<td><%=deal.getProductname() %></td>
				</tr>
				<tr>
					<th>個数</th>
					<td><%=quantity %></td>
				</tr>
				<tr>
					<th>価格</th>
					<td><%=format.moneyFormat(price) %>円</td>

				</tr>
				<tr>
					<th>お届け先</th>
					<td><%=userObj.getAddress_level1()%>&nbsp;<%=userObj.getAddress_level2()%></td>
				</tr>
			</table>


			<p>お支払金額</p>
			<%
			//合計金額格納用変数
			int totalPrice = 0;

			totalPrice = quantity * price;

			%>
			<p style="color:#ff5722;"><%= MyFormat.moneyFormat(totalPrice) %>円</p>

			<br>
			<strong>
				商品をご購入いただきありがとうございます。<br>
				またのご利用を心よりお待ち申し上げます。
			</strong>


			<form action="<%=request.getContextPath()%>/productList" method="get">
				<input type="submit" value="商品一覧へ戻る" class="revise_button2" style="width:150px;">
			</form>

			<form action="<%=request.getContextPath()%>/buyList" method="get">
				<input type="submit" value="購入一覧を確認する" class="login_button" style="width:180px;">
			</form>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
