<!--
プログラム名：buyConfirm.jsp
作成者　　　：近藤
作成日　　　：2022/06/24
概要　　　　：購入品内容の表示と購入完了前画面（ユーザーに購入数を送信してもらう）
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.Deal,bean.Product,bean.User"%>
<%@page import="util.MyFormat"%>



<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%

//BuyConfirmServletからリクエストスコープを取得する
Product Products = (Product) request.getAttribute("product");
Deal deal = (Deal)request.getAttribute("deal");
int productid = deal.getProductid();
MyFormat format = new MyFormat();

//在庫数
int stock = Products.getStock() ;
%>

<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<head>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<!-- "title"タグ内には画面名をつけてください(ブラウザのタブに表示されます) -->
		<title>購入品確認</title>
		<style type="text/css">
			div{
				text-align:center;
			}
			table{
				margin:30px auto;
			}
			td{
				padding-left: 20px;
				width:250px;
			}

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">購入品確認</h2>
			</div>

			<form action="<%=request.getContextPath()%>/buyComplete" method="GET">

				<table class="list_table" style="margin:30px auto;">
					<tr>
						<th class="td-first">商品名</th>
						<td><%=deal.getProductname() %></td>
					</tr>
					<tr>
						<th class="td-first">単価</th>
						<td><%=format.moneyFormat(deal.getPrice()) %>円 </td>
					</tr>
					<tr>
						<th class="td-first">個数</th>
						<td><input id="quantity" type="number" name="quantity" value="1" min="1"
							 max="<%=stock%>" style="width:100px" required> /在庫数：<%=stock%></td>
					</tr>
					<tr>
						<th class="td-first">合計金額</th>
						<td id="total"><%=MyFormat.moneyFormat(deal.getPrice()) %>円</td>
					</tr>
					<tr>
						<th class="td-first">お届け先</th>
						<td><%=user.getAddress_level1()%>&nbsp;<%=user.getAddress_level2()%></td>
					</tr>
				</table>

				<!--
				<strong>
				上記の商品を購入でよろしいですか？<br>
				個数を入力の上<br>
				よろしければ、購入決定ボタンを、<br>
				内容に変更がある場合は修正ボタンを押してください。
				</strong>
				-->

				<!-- ユーザー権限の場合のみ購入画面への遷移が可能 -->
				<%if(user.getAuthority() == false){ %>
					<input type="hidden"  name="pro_id" value="<%=productid%>">
					<input type="hidden"  name="stock" value="<%=stock%>">
					<input type="submit" value="購入決定" class="register_button">
				</form>
				<%} %>

			<form action="<%=request.getContextPath()%>/productDetail" method="GET">
				<input type="hidden"  name="productid" value="<%=productid%>">
				<input type="submit" value="商品詳細画面へ戻る" class="login_button" style="width:200px;">

			</form>



		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
			<script>
				$("#quantity").change(function(){
					$("#total").text(($("#quantity").val() * <%=deal.getPrice()%>).toLocaleString() + "円");
				});
			</script>
	</body>
</html>
