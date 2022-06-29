<%@page contentType="text/html; charset=UTF-8" %>

<%

%>
<html>
	<head>
		<title></title>
		<style type="text/css">

		</style>
	</head>
	<body id="wrapper">
		<!-- footer -->
			<footer class="footer">
				<%if(authority.equals("")) {%>
					<a href="#"class="footer_a"><div class="bottom_button_right">ページトップへ</div></a>
					<a href="<%= request.getContextPath() %>/productList" class="footer_a">
						<div class="bottom_button_right">商品一覧へ</div>
					</a>
					<hr class="hr"/>
				<%} else if(authority.equals("会員")) { %>
					<a href="#"class="footer_a"><div class="bottom_button_right">ページトップへ</div></a>
					<a href="<%= request.getContextPath() %>/view/menu.jsp" class="footer_a">
						<div class="bottom_button_right">メニューへ</div>
					</a>
					<hr class="hr"/>
				<%} else { %>
					<a href="#"class="footer_a"><div class="admin_bottom_button_right">ページトップへ</div></a>
					<a href="<%= request.getContextPath() %>/view/menu.jsp" class="footer_a">
						<div class="admin_bottom_button_right">メニューへ</div>
					</a>
					<hr class="admin_hr"/>
				<%} %>
				<p class="copyright">Copyright(C)2022 All Rights Reserved.</p>
			</footer>
	</body>
</html>