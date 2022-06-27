<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Product,util.ImageConvert"%> <!-- importの必要性が生じた場合この中に記述してください -->

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
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>商品詳細</title>
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
			<% UserAuthority = user != null ? user.getAuthority() : true; %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">商品詳細</h2>
			</div>
		<br>
		<br>

		選択した商品の詳細は以下の通りです。<br>
		<%if(!UserAuthority){%>
		よろしければ購入画面にお進みください<br>
		<% } %>

			<table align="center">

				<tr>
					<td bgcolor="#6666ff" width="100">商品名</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getProductname() %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">商品画像</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getImage() != null ? "<img src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(Products.getImage()), request, response) + "' width='256' height='auto'>" : "-" %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">出品ユーザー</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getNickname() %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">価格</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getPrice() %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">在庫</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getStock() %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">備考</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getRemark() %></td>
				</tr>
				<tr>
					<td bgcolor="#6666ff" width="100">カテゴリ</td>
					<td bgcolor="#99ffff" width="400"><%=Products.getCategory() %></td>
				</tr>
				<tr>
					<td colspan="2" align="center">&nbsp;</td>
				</tr>
				</table>
				<form action="<%=request.getContextPath()%>/buyConfirm" method="GET">


				<%if(!UserAuthority){%>
				<p align="center">
					<input type="hidden"  name="p_id" value="<%=productid%>">
					<input type="submit"  value="購入画面へ">
				</p>
				</form>
				<% } %>


		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
