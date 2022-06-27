<!--
プログラム名：buyComplete.jsp
作成者　　　：近藤
作成日　　　：2022/06/24
概要　　　　：購入完了画面
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Deal,bean.Product,bean.User"%>
<%@page import="util.MyFormat"%>

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

		<table>
						<tr>


							<td class="td-first">商品名</td>
							<td><%=deal.getProductname() %></td>

						</tr>



						<tr>
							<td class="td-first">個数</td>
							<td><%=quantity %></td>


						</tr>

						<tr>
							<td class="td-first">価格</td>
							<td><%=format.moneyFormat(price) %>円</td>

						</tr>

						<tr>
							<td class="td-first">お届け先</td>
							 <td><%=userObj.getAddress_level1()%>&nbsp;<%=userObj.getAddress_level2()%></td>
						</tr>

				</table>

				<table>
						<tr>
							<td class="td-first">お支払金額</td>
							<%
							//合計金額格納用変数
							int totalPrice = 0;

							totalPrice = quantity * price;

							%>
							<td><%=format.moneyFormat(totalPrice) %>円</td>
						</tr>

				<br>
				<br>
				</table>


				<br>
				<br>
				<strong>
				商品をご購入いただきありがとうございます。<br>
				またのご利用を心よりお待ち申し上げます。
				</strong>


			<form action="<%=request.getContextPath()%>/productList" method="get">
				<p align="center">
				<input type="submit" value="商品一覧へ戻る">
				</p>
				</form>

			<form action="<%=request.getContextPath()%>/buyList" method="get">
				<p align="center">
				<input type="submit" value="購入一覧を確認する">
				</p>
				</form>


		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
