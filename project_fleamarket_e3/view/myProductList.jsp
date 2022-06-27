<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.Product" %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
ArrayList<Product> product_list =(ArrayList<Product>)request.getAttribute("product_list");

// searchを行う場合のパラメータ取得
String productname = "";
String category = "";

if(request.getAttribute("productname") != null){
	productname = (String)request.getAttribute("productname");
}

if(request.getAttribute("category") != null){
	category = (String)request.getAttribute("category");
}
%>

<html>
	<head>
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>マイ出品一覧</title>
		<style type="text/css">
			/*
			CSSを書く際はこの中に記述してください
			なお別途CSSファイルを作った方がいいときは言ってください
			 */

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">マイ出品一覧</h2>
			</div>

			<table align="center">
				<tr>
					<td>
						<form action="<%=request.getContextPath()%>/myProductList">
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
						<form action="<%=request.getContextPath()%>/myProductList">
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
					<th bgcolor="#6666ff" width="50" colspan="2">&nbsp;</th>
					<th bgcolor="#6666ff" width="50">商品ID</th>
					<th bgcolor="#6666ff" width="200">商品カテゴリ</th>
					<th bgcolor="#6666ff" width="200">商品名</th>
					<th bgcolor="#6666ff" width="50">在庫</th>
					<th bgcolor="#6666ff" width="100">単価</th>
					<th bgcolor="#6666ff" width="100">登録日</th>
					<th bgcolor="#6666ff" width="150" colspan="2">出品状況</th>
				</tr>

				<%
				if(product_list != null){
					for(int i = 0; i < product_list.size(); i++){
						Product product = product_list.get(i);
						%>
						<tr>
							<td align="center" width="50"><a href="<%=request.getContextPath()%>/myProductDetail?productid=<%=product.getProductid() %>">[編集]</a></td>
							<td align="center" width="50"><a href="#" onclick=
							"if(window.confirm('「<%=product.getProductname() %>」を削除してもよろしいですか？')){
								location.href = '<%=request.getContextPath()%>/myProductList?deleteid=<%=product.getProductid() %>'
							}">[削除]</a></td>
							<td align="center" width="50"><%=product.getProductid() %></td>
							<td align="center" width="200"><%=product.getCategory() %></td>
							<td align="center" width="200"><%=product.getProductname() %></td>
							<td align="center" width="50"><%=product.getStock() %></td>
							<td align="center" width="100"><%=product.getPrice() %></td>
							<td align="center" width="100"><%=product.getCreated_at() %></td>
							<%if(product.getOn_sale()) { %>
								<td bgcolor="skyblue" width="100">出品中<td>
								<button onclick="location.href='<%=request.getContextPath()%>/myProductList?changeid=<%=product.getProductid() %>'">出品取消</button>
							<%}
							else {%>
								<td bgcolor="wheat" width="100">出品待ち<td>
								<button onclick="location.href='<%=request.getContextPath()%>/myProductList?changeid=<%=product.getProductid() %>'">出品</button>
							<%} %>
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
