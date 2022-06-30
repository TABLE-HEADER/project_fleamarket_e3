<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.ArrayList,bean.Product, util.ImageConvert, util.MyFormat" %> <!-- importの必要性が生じた場合この中に記述してください -->

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
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>商品一覧</title>
		<style type="text/css">

			.detailCheckbox{
				display: none;
			}
			.detailOpen{
				margin-left: 10px;
				font-size: small;
				display: block;
				position: relative;
			}
			.detailOpen:hover{
				cursor: pointer;
			}
			.detailOpen:after{
				content: '∨';
				display: block;
				position: absolute;
				right: -15px;
				top: 0px;
			}
			.searchInDetail{
				border: 1px solid #333;
				display: block;
				height: 0;
				padding-left: 20px;
				opacity: 0;
				transition: .5s;
			}
			.detailCheckbox:checked + table .detailOpen:after{
				content: '∧';
			}
			.detailCheckbox:checked + table .searchInDetail{
				height: <%=request.getSession().getAttribute("user") != null && !((User)request.getSession().getAttribute("user")).getAuthority() ? "190" : "150"%>px;
				opacity: 1;
				visibility: visible;
			}

		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>
			<%
			String prefecture = "";
			if(user != null) {
				prefecture = user.getAddress_level1();
			}
			%>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">商品一覧</h2>
			</div>
			<p>気になる商品名をクリック！</p>

			<input id="detailCheckbox" class="detailCheckbox" type="checkbox">
			<table align="center">
				<tr>
					<td>
						<form action="<%=request.getContextPath()%>/productList" id="search">
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
					<td>
						<label class="detailOpen" for="detailCheckbox">詳細検索</label>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="searchInDetail">
								<p><input id="checkPrice" type="checkbox">&nbsp;単価：<input id="minprice" type="number" min="1" value="500" name="minprice">円&nbsp;～&nbsp;<input id="maxprice" type="number" min="1" value="1000" name="maxprice">円</p>
								<p><input id="checkRegion" type="checkbox">&nbsp;出品地域：
								<%
								// カテゴリの配列データ
								String[] prefecture_list = {"北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県",
										"茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県",
										"新潟県","富山県","石川県","福井県","山梨県","長野県","岐阜県",
										"静岡県","愛知県","三重県","滋賀県","京都府","大阪府","兵庫県",
										"奈良県","和歌山県","鳥取県","島根県","岡山県","広島県","山口県",
										"徳島県","香川県","愛媛県","高知県","福岡県","佐賀県","長崎県",
										"熊本県","大分県","宮崎県","鹿児島県","沖縄県"};
								%>
								<select id="region" name="region">
									<option value="" selected>カテゴリを選択</option>
									<%
									for(String p : prefecture_list){
									%>
										<option value="<%=p %>" <%= prefecture.equals(p) ? "selected" : ""%>><%=p %></option>
									<%
									}
									%>
								</select></p>
								<p><input id="in_stock" type="checkbox" name="in_stock" form="search">&nbsp;在庫あり商品のみ表示</p>
								<%if(user != null && !user.getAuthority()) {%>
									<p><input id="self_item" type="checkbox" name="self_item" form="search">&nbsp;自身の出品を非表示</p>
								<%} %>
						</div>
					</td>
				</tr>
			</table>

			<br>

			<% if(!authority.equals("管理者")){  %>
				<table align="center" class="list_table">
			<% }else{ %>
				<table align="center" class="list_table" id="admin_list_table">
			<% } %>
				<caption>
					<%if(request.getParameter("minprice") != null || request.getParameter("region") != null || request.getParameter("in_stock") != null || request.getParameter("self_item") != null) {%>
						詳細検索（<%=product_list != null ? product_list.size() : 0%>件）
					<%}
					else if(productname.equals("") && category.equals("")) {%>
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
					<th bgcolor="#6666ff" width="64">画像</th>
					<th bgcolor="#6666ff" width="200">商品カテゴリ</th>
					<th bgcolor="#6666ff" width="200">商品名</th>
					<th bgcolor="#6666ff" width="50">在庫</th>
					<th bgcolor="#6666ff" width="80">単価</th>
					<th bgcolor="#6666ff" width="80">出品地域</th>
					<th bgcolor="#6666ff" width="120">登録日</th>
				</tr>

				<%
				if(product_list != null){
					for(int i = 0; i < product_list.size(); i++){
						Product product = product_list.get(i);
						%>
						<tr <%=product.getStock() > 0  ? "" : "bgcolor='silver'" %>>
							<td align="center"><%=product.getImage() != null ? "<img src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(product.getImage()), request, response) + "' width='64' height='auto'>" : "-" %></td>
							<td align="center"><%=product.getCategory() %></td>
							<td align="center"><a href="<%=request.getContextPath() %>/productDetail?productid=<%=product.getProductid() %>"><%=product.getProductname() %></a></td>
							<td align="center"><%=product.getStock() %>個</td>
							<td align="center"><%=MyFormat.moneyFormat(product.getPrice()) %>円</td>
							<td align="center"><%=product.getAddress_level1() %></td>
							<td align="center"><%=MyFormat.birthdayFormat(product.getCreated_at()) %></td>
						</tr>
						<%
					}
				}
				%>

			</table>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
			<script>

				$("#checkPrice").click(function(){
					if (checkPrice.checked) {
						$("#minprice").attr("form", "search");
						console.log("aaa");
						$("#maxprice").attr("form", "search");
					}
					else {
						$("#minprice").removeAttr("form");
						console.log("bbb");
						$("#maxprice").removeAttr("form");
					}
				});

				$("#checkRegion").click(function(){
					if (checkRegion.checked) {
						$("#region").attr("form", "search");
					}
					else {
						$("#region").removeAttr("form");
					}
				});

			</script>
	</body>
</html>
