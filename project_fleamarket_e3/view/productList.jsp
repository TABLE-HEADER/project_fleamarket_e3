<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.Product, util.ImageConvert" %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
ArrayList<Product> product_list =(ArrayList<Product>)request.getAttribute("product_list");

// searchを行う場合のパラメータ取得
String productname = "";
String category = "";

if(request.getParameter("productname") != null){
	productname = (String)request.getParameter("productname");
}

if(request.getParameter("category") != null){
	category = (String)request.getParameter("category");
}
%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>商品一覧</title>
		<style type="text/css">
			/*
			CSSを書く際はこの中に記述してください
			なお別途CSSファイルを作った方がいいときは言ってください
			 */

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">商品一覧</h2>
			</div>

			<table align="center">
				<tr>
					<td>
						<form action="<%=request.getContextPath()%>/productList">
							カテゴリ：
							<%
							// カテゴリの配列データ
							String[] category_list = {"レディース", "メンズ", "ベビー・キッズ",
							"インテリア・住まい・小物", "本・音楽・ゲーム", "おもちゃ・ホビー・グッズ",
							"コスメ・香水・美容", "家電・スマホ・カメラ", "スポーツ・レジャー",
							"ハンドメイド", "チケット", "自動車・オートバイ", "その他"};
							%>
							<select name="category">
								<option value="" selected>カテゴリを選択</option>
								<%
								for(String c : category_list){
								%>
									<option value="<%=c %>" <%= category.equals(c) ? "selected" : ""%>><%=c %></option>
								<%
								}
								%>
							</select>
							商品名：<input type=text size="30" name="productname" value="<%=productname %>"></input>
							<input type="submit" value="検索"></input>
						</form>
					</td>
					<td>
						<form action="<%=request.getContextPath()%>/productList">
							<input type="submit" value="全件表示"></input>
						</form>
					</td>
				</tr>
			</table>

			<br>

			<table align="center">
				<caption>
					<%if(productname.equals("") && category.equals("")) {%>
						全件表示（<%=product_list != null ? product_list.size() : 0%>件）
					<%}
					else{%>
						<%=!category.equals("") ? "カテゴリ：" + category : "" %>
						<%=!productname.equals("") && !category.equals("") ? "、" : "" %>
						<%=!productname.equals("") ? "商品名：" + productname : ""  %>
						の検索結果（<%=product_list != null ? product_list.size() : 0%>件）
					<% }%>
				</caption>
				<tr>
					<th bgcolor="#6666ff" width="100">商品ID</th>
					<th bgcolor="#6666ff" width="64">画像</th>
					<th bgcolor="#6666ff" width="200">商品カテゴリ</th>
					<th bgcolor="#6666ff" width="200">商品名</th>
					<th bgcolor="#6666ff" width="50">在庫</th>
					<th bgcolor="#6666ff" width="100">単価</th>
				</tr>

				<%
				if(product_list != null){
					for(int i = 0; i < product_list.size(); i++){
						Product product = product_list.get(i);
						%>
						<tr <%=product.getStock() > 0  ? "" : "bgcolor='grey'" %>>
							<td align="center" width="100"><%=product.getProductid() %></td>
							<td align="center" width="64"><%=product.getImage() != null ? "<img src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(product.getImage()), request, response) + "' width='64' height='auto'>" : "-" %></td>
							<td align="center" width="200"><%=product.getCategory() %></td>
							<td align="center" width="200"><a href="<%=request.getContextPath() %>/productDetail?productid=<%=product.getProductid() %>"><%=product.getProductname() %></a></td>
							<td align="center" width="50"><%=product.getStock() %></td>
							<td align="center" width="100"><%=product.getPrice() %></td>
						</tr>
						<%
					}
				}
				%>

			</table>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
