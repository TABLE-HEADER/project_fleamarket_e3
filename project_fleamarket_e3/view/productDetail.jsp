<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Product,util.ImageConvert, util.MyFormat"%> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
//ProductDetailServletから受け取るパラメータの取得
Product Products = (Product) request.getAttribute("Products");
String productid = request.getParameter("productid");
//Productオブジェクトの生成
Product product = new Product();
boolean UserAuthority = false;
%>

<html>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>商品詳細</title>
		<style type="text/css">
			div{
				text-align:center;
			}
			table{
				border-collapse:collapse;
				border:1px solid gray;
				margin:5px auto 30px;
			}
			table th{
				width:100;
			}
			table td{
				width:400;
			}
		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>
			<% UserAuthority = user != null ? user.getAuthority() : true; %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">商品詳細</h2>
			</div>

			<!-- 選択した商品の詳細は以下の通りです。<br> -->
			<br>
			<%if(!UserAuthority && Products.getStock() > 0){%>
			購入する場合は「購入画面へ」をクリック！<br>
			<% } %>

			<table align="center" class="list_table">

				<tr>
					<th>商品名</th>
					<td><%=Products.getProductname() %></td>
				</tr>
				<tr>
					<th>商品画像</th>
					<td><%=Products.getImage() != null ? "<img src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(Products.getImage()), request, response) + "' width='256' height='auto'>" : "-" %></td>
				</tr>
				<tr>
					<th>出品ユーザー</th>
					<td><%=Products.getNickname() %></td>
				</tr>
				<tr>
					<th>価格</th>
					<td><%= MyFormat.moneyFormat(Products.getPrice()) %>円</td>
				</tr>
				<tr>
					<th>在庫</th>
					<td><%=Products.getStock() %>個</td>
				</tr>
				<tr>
					<th>備考</th>
					<td><%=Products.getRemark() %></td>
				</tr>
				<tr>
					<th>カテゴリ</th>
					<td><%=Products.getCategory() %></td>
				</tr>
				</table>
				<form action="<%=request.getContextPath()%>/buyConfirm" method="GET">


				<%if(!UserAuthority && Products.getStock() > 0){%>
					<p align="center">
						<input type="hidden"  name="p_id" value="<%=productid%>">
						<input type="submit"  value="購入画面へ" class="login_button">
					</p>
					</form>
				<% } %>


		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
