<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="dao.UserDAO, bean.Deal, bean.User, java.util.ArrayList, util.MyFormat"%> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
ArrayList<Deal> deal_list = (ArrayList<Deal>)request.getAttribute("deal_list");

// searchを行う場合のパラメータ取得
String category = "";
String year = "";
String month = "";

String dispMonth = "";

if(request.getParameter("category") != null){
	category = (String)request.getParameter("category");
}
if(request.getParameter("year") != null){
	year = (String)request.getParameter("year");
}
if(request.getParameter("month") != null && !request.getParameter("month").equals("")){
	month = (String)request.getParameter("month");

	if(Integer.parseInt(month) < 10){
		dispMonth = month.substring(1);
	}else{
		dispMonth = month;
	}
}

// 結果表示のために必要
UserDAO objUserDao = new UserDAO();
int total = 0;
%>

<html>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>売上一覧</title>
		<style type="text/css">
			/*
			CSSを書く際はこの中に記述してください
			なお別途CSSファイルを作った方がいいときは言ってください
			 */
			table{
				margin:20 auto 10;
				text-align:center;
			}
			th, td{
				padding:3px;
			}
			th{
				background-color:#00800075;
			}

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto;">売上一覧</h2>
			</div>

			<div style="width:980px; margin:auto;">

			<table>
				<tr>
					<td>
						<form action="<%=request.getContextPath()%>/salesList">
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
							<select name="year">
								<option value="">年を選択</option>
								<%
								for(int i = 2021; i < 2031; i++){
								%>
									<option value="<%= i %>" <%= year.equals("" + i) ? "selected" : ""%>><%= i %></option>
								<%
								}
								%>
							</select>
							年
							<select name="month">
								<option value="">月を選択</option>
								<%
								for(int i = 1; i < 13; i++){
									if(i < 10){
										String dispI = "0" + i;
								%>
										<option value="<%= dispI %>" <%= month.equals(dispI) ? "selected" : "" %>><%= i %></option>
								<%	}else{	%>
										<option value="<%= i %>" <%= month.equals(i) ? "selected" : "" %>><%= i %></option>
								<%
									}
								}
								%>
							</select>
							月
							<input type="submit" value="検索"></input>
						</form>
					<td>
						<form action="<%=request.getContextPath()%>/salesList">
							<input type="submit" value="全件表示"></input>
						</form>
					</td>
				</tr>
			</table>

			<br>

			<table class="list_table" id="admin_list_table">
				<caption>
					<%if(category.equals("") && year.equals("") && month.equals("")) {%>
						全件表示（<%=deal_list.size() %>件）
					<%}
					else{%>
						<%=!year.equals("") ? year + "年" : ""%>
						<%=!month.equals("") ? dispMonth + "月" : ""%>
						<br>
						<%=!category.equals("") ? "カテゴリ：" + category : "" %>
						の検索結果（<%=deal_list.size() %>件）
					<% }%>
				</caption>
				<tr>
					<th width="150">購入日</th>
					<th width="200">商品名</th>
					<th width="200">商品カテゴリ</th>
					<th width="150">購入者</th>
					<th width="100">単価</th>
					<th width="50">個数</th>
					<th width="100">合計金額</th>
				</tr>
				<%if(deal_list != null) {
					for(int i = 0; i < deal_list.size(); i++){
						Deal deal = deal_list.get(i);
							if(deal.getPaid_at() != null){
				%>
								<tr>
									<td><%= MyFormat.birthdayFormat(deal.getBought_at()) %></td>
									<td><%= deal.getProductname() %></td>
									<td><%= deal.getCategory() %></td>
									<td><%= deal.getNickname() %></td>
									<td><%= MyFormat.moneyFormat(deal.getPrice()) %>円</td>
									<td><%= deal.getQuantity() %>個</td>
									<td><%= MyFormat.moneyFormat(deal.getTotal()) %>円</td>
									<% total += deal.getTotal(); %>
								</tr>
				<%		}
					}
				}%>
			</table>

			<% if(deal_list != null && !deal_list.isEmpty()){ %>
				<hr/>
				<p>合計：<%= MyFormat.moneyFormat(total) %>円</p>
				<p>システム利用料合計：<%= MyFormat.moneyFormat(total * 0.1) %>円</p>
			<%} %>
			</div>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>
