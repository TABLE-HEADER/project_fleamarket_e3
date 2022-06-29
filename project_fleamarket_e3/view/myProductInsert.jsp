<%--
機能名：出品登録機能
作成者：中西りりな
作成日：2022/06/23
--%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,util.ImageConvert"%>

<%
	ArrayList<String> error = (ArrayList<String>) request.getAttribute("error");

	String category = request.getParameter("category") != null ? request.getParameter("category") : "";
	String productname = request.getParameter("productname") != null ? request.getParameter("productname") : "";
	String stock = request.getParameter("stock") != null ? request.getParameter("stock") : "";
	String price = request.getParameter("price") != null ? request.getParameter("price") : "";
	String image = request.getParameter("image") != null ? request.getParameter("image") : "";
	String remark = request.getParameter("remark") != null ? request.getParameter("remark") : "";
%>

<head>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
	<title>出品登録</title>
	<style type="text/css">

	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp"%>
			<h2 align="center">出品登録</h2>

		<!-- contents -->

			<%
			if (error != null) {
				for (int i = 0; i < error.size(); i++) {
			%>
					<font size="3" color="#ff0000"><%=error.get(i)%></font>
					<br>
			<%
				}
			}
			%>
			<form action="<%=request.getContextPath()%>/myProductInsert" method="post"
				id="login_form" style="background-color:#5bff0029;">
				<table border=0 align="center" summary="出品画面">
					<tr>
						<th style="background-color: #99FF66; width: 100">カテゴリ<font
							size="1" color="#ff0000">必須</font></th>

						<td>
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
									<option value="<%=c %>" <%= category.equals(c)  ? "selected" : ""%>><%=c %></option>
								<%
								}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th style="background-color: #99FF66; width: 100">商品名<font
							size="1" color="#ff0000">必須</font></th>
						<td><input type="text" name="productname" size="23" value=<%=productname %>></td>
					</tr>
					<tr>
						<th style="background-color: #99FF66; width: 100">個数<font
							size="1" color="#ff0000">必須</font></th>
						<td><input type="text" name="stock" size="23" value=<%=stock %>></td>
					</tr>
					<tr>
						<th style="background-color: #99FF66; width: 100">価格(単価)<font
							size="1" color="#ff0000">必須</font></th>
						<td><input type="text" name="price" size="21" value=<%=price %>>円</td>
					</tr>
					<tr>
						<th style="background-color: #99FF66; width: 100">商品画像</th>
						<td valign="top">
							<img id="thumbnail" src="" alt="イメージ"><br>
							<input type="file" id="uploadImg" accept=".png, .jpg, .jpeg, .bmp"><br>
							<font color="grey" size="2">画像は64*64に縮小表示されます。</font>
							<input type="hidden" id="image" name="image" value=<%=image %>>
						</td>
					</tr>
					<tr>
						<th style="background-color: #99FF66; width: 100">備考</th>
						<td><textarea name="remark" rows="5" cols="25"><%=remark %></textarea></td>
					</tr>

					<tr>
						<td colspan=2 style="text-align: center">
						<input type="submit" value="登録" class="login_button"></td>
					</tr>

				</table>

			</form>


		<!-- footer -->
			<%@include file="/common/footer.jsp"%>

		<script type="text/javascript">
			const uploadImg = document.getElementById("uploadImg");

			if(uploadImg !== null){
				uploadImg.addEventListener('change', function(){
					if (this.files.length > 0) {
						// 選択されたファイル情報を取得
						var file = this.files[0];

						// readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
						var reader = new FileReader();
						reader.readAsDataURL(file);

						reader.onload = function() {
							$('#thumbnail').attr('src', reader.result);
							$('#image').attr('value', reader.result.split(',')[1]);
						}
					}
				});
			}
		</script>
	</body>
</html>