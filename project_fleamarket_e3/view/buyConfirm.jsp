<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Deal,bean.Product,bean.User"%>

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
//BuyConfirmServletからリクエストスコープを取得する
Deal deal = (Deal)request.getAttribute("deal");
int productid = deal.getProductid();
User userObj = (User)request.getAttribute("user");



//数量格納用変数
String quantity="";

%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>購入品確認</title>
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
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">購入品確認</h2>
			</div>
			<form action="<%=request.getContextPath()%>/buyComplete" method="GET">

			<table>
						<tr>


							<td class="td-first">商品名</td>
							<td><%=deal.getProductname() %></td>

						</tr>



						<tr>
							<td class="td-first">個数</td>
							 <td> <input type="text" name="quantity" value="<%=quantity%>" required="required" ></td>
						</tr>

						<tr>
							<td class="td-first">価格</td>
							 <td><%=deal.getPrice() %> </td>
						</tr>

						<tr>
							<td class="td-first">お届け先</td>
							 <td><%=userObj.getAddress_level1()%></td>
							 <td><%=userObj.getAddress_level2()%></td>
						</tr>


</table>
				<br>
				<br>
				<br>
				<strong>
				上記の商品を購入でよろしいですか？<br>
				個数を入力の上<br>
				よろしければ、購入決定ボタンを、<br>
				内容に変更がある場合は修正ボタンを押してください。
				</strong>



				<p align="center">
				<input type="hidden"  name="pro_id" value="<%=productid%>">
				<input type="submit" value="購入決定">
				</p>
				</form>

			<form action="<%=request.getContextPath()%>/productDetail" method=""GET">
				<p align="center">
				<input type="hidden"  name="p_id" value="<%=productid%>">
				<input type="submit" value="修正">
				</p>
				</form>

			<p><%= productid %></p>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
