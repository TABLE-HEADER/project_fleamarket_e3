<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.Deal, util.MyFormat" %> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
ArrayList<Deal> deal_list =(ArrayList<Deal>)request.getAttribute("deal_list");

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
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>取引一覧</title>
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
				<h2 style="margin:15px auto 10px;">取引一覧</h2>
			</div>

			<table align="center" style="margin:30px auto 5px;">
				<tr>
					<td>
						<form action="<%=request.getContextPath()%>/dealList">
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
						<form action="<%=request.getContextPath()%>/dealList">
							<input type="submit" value="全件表示"></input>
						</form>
					</td>
				</tr>
			</table>

			<br>

			<table align="center" class="list_table">
				<caption>
					<%if(productname.equals("") && category.equals("")) {%>
						全件表示（<%=deal_list != null ? deal_list.size() : 0%>件）
					<%}
					else{%>
						<%=!category.equals("") ? "カテゴリ：" + category : "" %>
						<%=!productname.equals("") && !category.equals("") ? "、" : "" %>
						<%=!productname.equals("") ? "商品名：" + productname : ""  %>
						の検索結果（<%=deal_list != null ? deal_list.size() : 0%>件）
					<% }%>
				</caption>
				<tr>
					<th bgcolor="#6666ff" width="50">商品ID</th>
					<th bgcolor="#6666ff" width="200">商品カテゴリ</th>
					<th bgcolor="#6666ff" width="145">商品名</th>
					<th bgcolor="#6666ff" width="145">取引相手</th>
					<th bgcolor="#6666ff" width="50">個数</th>
					<th bgcolor="#6666ff" width="100">合計金額</th>
					<th bgcolor="#6666ff" width="120">入金日</th>
					<th bgcolor="#6666ff" width="120">発送日</th>
					<th bgcolor="#6666ff" width="220" colspan="2">発送状況</th>
				</tr>

				<%
				if(deal_list != null){
					for(int i = 0; i < deal_list.size(); i++){
						Deal deal = deal_list.get(i);
						%>
						<tr>
							<td align="center"><%=deal.getProductid() %></td>
							<td align="center"><%=deal.getCategory() %></td>
							<td align="center"><%=deal.getProductname() %></td>
							<td align="center"><%=deal.getNickname() %></td>
							<td align="center"><%=deal.getQuantity() %></td>
							<td align="center"><%=MyFormat.moneyFormat(deal.getTotal()) %>円</td>
							<td align="center"><%=deal.getPaid_at() != null ? MyFormat.birthdayFormat(deal.getPaid_at()) : "&nbsp;"%></td>
							<td align="center"><%=deal.getSent_at() != null ? MyFormat.birthdayFormat(deal.getSent_at()) : "&nbsp;"%></td>
							<%switch(deal.getState()) {
							case "入金待ち":
								%>
									<td style="background-color:wheat; width:80;">入金待ち<td>
									<button disabled>発送完了</button>
								<%
								break;
							case "発送待ち":
								%>
									<td style="background-color:lightpink; width:80;">発送待ち<td>
									<button onclick="location.href='<%=request.getContextPath()%>/dealList?changeid=<%=deal.getDealid() %>'">発送完了</button>
								<%
								break;
							case "発送中":
								%>
									<td style="background-color:skyblue; width:80;">発送中<td>
									<button disabled>発送完了</button>
								<%
								break;
							}%>
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
