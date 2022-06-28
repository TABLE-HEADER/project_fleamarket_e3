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
<title>出品内容変更</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body id="wrapper">
	<!-- header -->
	<header>
		<%@include file="/common/header.jsp"%>
	</header>

	<br>
	<h2 align="center">出品内容変更</h2>

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
				<td></td>
				<td>＜＜変更前情報＞＞</td>
				<td>＜＜変更後情報＞＞</td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">カテゴリ</th>
				<td style="background-color: #CCFF66; width: 200"><%=oldProduct.getCategory()%></td>
				<td><select name="category">
				<option value="レディース">レディース</option>
				<option value="メンズ">メンズ</option>
				<option value="ベビー・キッズ">ベビー・キッズ</option>
				<option value="インテリア・住まい・小物">インテリア・住まい・小物</option>
				<option value="本・音楽・ゲーム">本・音楽・ゲーム</option>
				<option value="おもちゃ・ホビー・グッズ">おもちゃ・ホビー・グッズ</option>
				<option value="コスメ・香水・美容">コスメ・香水・美容</option>
				<option value="家電・スマホ・カメラ">家電・スマホ・カメラ</option>
				<option value="スポーツ・レジャー">スポーツ・レジャー</option>
				<option value="ハンドメイド">ハンドメイド</option>
				<option value="チケット">チケット</option>
				<option value="自動車・オートバイ">自動車・オートバイ</option>
				<option value="その他">その他</option>
				</select></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">商品名</th>
				<td style="background-color: #CCFF66; width: 100"><%=oldProduct.getProductname()%></td>
				<td><input type="text" name="productname" value="<%=oldProduct.getProductname()%>"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">個数</th>
				<td style="background-color: #CCFF66; width: 100"><%=oldProduct.getStock()%></td>
				<td><input type="number" name="stock" min="0" value="<%=oldProduct.getStock()%>"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">価格</th>
				<td style="background-color: #CCFF66; width: 100"><%=oldProduct.getPrice()%></td>
				<td><input type="text" name="price" value="<%=oldProduct.getPrice()%>"></td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">商品画像</th>
				<td valign="top">
					<%byte[] image = oldProduct.getImage(); %>
					<img id='thumbnail' <%= image != null ? "src='data:image/png;base64," + ImageConvert.writeImage(ImageConvert.byteToImage(image), request, response) + "' width='64' height='64' align=top" : "" %> alt='イメージ'>
					<br>
					<input type="file" id="uploadImg" accept=".png, .jpg, .jpeg, .bmp"><br>
					<font color="grey" size="2">画像は64*64に縮小表示されます。</font>
					<input type="hidden" id="image" name="image" value="image">
				</td>
			</tr>
			<tr>
				<th style="background-color: #99FF66; width: 100">備考</th>
				<td style="background-color: #CCFF66; width: 100"><%=oldProduct.getRemark()%></td>
				<td><textarea name="remark" rows="5" cols="23"><%=oldProduct.getRemark()%></textarea></td>
			</tr>

			<tr>
				<td colspan=3 style="text-align: center">
				<input type = "hidden" name="productid" value=<%=oldProduct.getProductid() %>>
						<input type="submit" value="変更完了">
				</td>
				</form>
			</tr>

		</table>


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