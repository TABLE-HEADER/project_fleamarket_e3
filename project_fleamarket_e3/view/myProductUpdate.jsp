<%--
機能名：出品内容更新機能
作成者：中西りりな
作成日：2022/06/23
--%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,bean.Product,util.MyFormat,util.ImageConvert"%>

<%
	Product oldProduct = (Product) request.getAttribute("oldProduct");
	MyFormat format = new MyFormat();
	ArrayList<String> error = (ArrayList<String>) request.getAttribute("error");
%>

<html>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<title>出品内容変更</title>
		<style type="text/css">
			/*
			CSSを書く際はこの中に記述してください
			なお別途CSSファイルを作った方がいいときは言ってください
			 */
			table{
				margin:25 auto;
				width:700px;
				border-collapse:collapse;
			}
			th, td{
				padding:5px;
				border:1px solid #8080804a;
			}
			th{
				width:160px;
				text-align:left;
				padding:10 0 10 10;
				border:1px solid gray;
				background-color: #99FF66
			}
			td{
				background-color:#ccff6657;
				text-align:left;
			}
			tr:hover{
				background-color:#dfdfdf;
				transition    : 0.15s;
			}
		</style>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/header.jsp"%>

			<h2 style="margin:15px auto 10px;">出品内容変更</h2>

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
			<form action="<%=request.getContextPath()%>/myProductUpdate" method="post">
				<table border=0 align="center" summary="出品内容変更画面">
					<tr>
						<td style="border:none; background-color:white;">&nbsp;</td>
						<td style="border:none; text-align:center; color:blue; background-color:white;">
							＜＜変更前情報＞＞</td>
						<td style="border:none; text-align:center; color:red; background-color:white; width:200px;">
							＜＜変更後情報＞＞</td>
					</tr>
					<tr>
						<th>カテゴリ</th>
						<td><%=oldProduct.getCategory()%></td>
						<%
							// カテゴリの配列データ
							String[] category_list = {"レディース", "メンズ", "ベビー・キッズ",
							"インテリア・住まい・小物", "本・音楽・ゲーム", "おもちゃ・ホビー・グッズ",
							"コスメ・香水・美容", "家電・スマホ・カメラ", "スポーツ・レジャー",
							"ハンドメイド", "チケット", "自動車・オートバイ", "その他"};
						%>
						<td>
							<select name="category">
								<option value="" selected>カテゴリを選択</option>
								<%
								for(String c : category_list){
								%>
									<option value="<%=c %>" <%= oldProduct.getCategory().equals(c)  ? "selected" : ""%>><%=c %></option>
								<%
								}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<th>商品名</th>
						<td><%=oldProduct.getProductname()%></td>
						<td><input type="text" name="productname" value="<%=oldProduct.getProductname()%>"></td>
					</tr>
					<tr>
						<th>個数</th>
						<td><%=oldProduct.getStock()%>個</td>
						<td><input type="number" name="stock" min="0" value="<%=oldProduct.getStock()%>">個</td>
					</tr>
					<tr>
						<th>価格</th>
						<td><%=MyFormat.moneyFormat(oldProduct.getPrice()) %>円</td>
						<td><input type="text" name="price" value="<%=oldProduct.getPrice()%>">円</td>
					</tr>
					<tr>
						<th>商品画像</th>
						<td valign="top">
							<%byte[] image = oldProduct.getImage(); %>
							<img id='thumbnail' <%= image != null ? "src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(image), request, response) + "' width='64' height='64' align=top" : "" %> alt='イメージ'>
						</td>
						<td>
							<input type="file" id="uploadImg" accept=".png, .jpg, .jpeg, .bmp"><br>
							<font color="grey" size="2">画像は64*64に縮小表示されます。</font>
							<input type="hidden" id="image" name="image" value="image">
						</td>
					</tr>
					<tr>
						<th>備考</th>
						<td><%=oldProduct.getRemark()%></td>
						<td><textarea name="remark" rows="5" cols="23"><%=oldProduct.getRemark()%></textarea></td>
					</tr>
				</table>
				<input type = "hidden" name="productid" value=<%=oldProduct.getProductid() %>>
				<input type="submit" value="変更完了" class="login_button">
			</form>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>

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