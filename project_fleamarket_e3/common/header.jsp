<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User" %>

<%
User user = (User)session.getAttribute("user");

String authority = "";
String name = "かんちゃん";

if(user != null){
	if(user.getAuthority()){
		authority = "管理者";
	}else{
		authority = "会員";
	}
	name = user.getNickname();
}

if(user == null){
	// セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、画面が表示できませんでした。");
	request.setAttribute("cmd","logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
%>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/header-footerStyle.css">
		<style type="text/css">

		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<header style="position:sticky; top:0px; background-color:white; padding:5px 0px 0px; z-index:1;">
				<% if(authority.equals("")){ %>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/productList">
							<img src="<%= request.getContextPath()%>/image/logo.png"
								height="80px">
						</a>
					</div>

					<div id="menu">
						<ul id="menu_ul">
							<a href="<%= request.getContextPath() %>/productList"><li>商品一覧</li></a>
							<a href="<%= request.getContextPath() %>/view/login.jsp"><li style="color:red">ログイン</li></a>
							<a href="<%= request.getContextPath() %>/view/register.jsp"><li>会員登録</li></a>
						</ul>
					</div>
					<hr class="hr"/>
				<% }else if(authority.equals("会員")){%>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/productList">
							<img src="<%= request.getContextPath()%>/image/logo.png"
								height="80px">
						</a>
					</div>

					<div style="float:left; height:80;">
						<span style="display:block;padding:30px 30px 30px; font-weight:normal">
							<%= name %> <span style="font-weight:bold">さん</span></span>
					</div>

					<div id="menu">
						<ul id="menu_ul">
							<a href="<%= request.getContextPath() %>/view/menu.jsp"><li>メニュー</li></a>
							<a href="<%= request.getContextPath() %>/productList"><li>商品一覧</li></a>
							<a href="<%= request.getContextPath() %>/view/myProductInsert.jsp"><li>出品する</li></a>
							<a href="<%= request.getContextPath() %>/buyList"><li>購入一覧</li></a>
							<a href="<%= request.getContextPath() %>/dealList"><li>取引一覧</li></a>
							<a href="<%= request.getContextPath() %>/myProductList"><li>出品一覧</li></a>
							<a href="<%= request.getContextPath() %>/userDetail"><li>ユーザー情報の変更</li></a>
							<a id="logout"><li style="color:red">ログアウト</li></a>
						</ul>
					</div>
					<hr class="hr"/>
				<% }else{ %>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/productList">
							<img src="<%= request.getContextPath()%>/image/logo.png"
								height="80px">
						</a>
					</div>

					<div id="menu">
						<ul id="admin_menu_ul">
							<a href="<%= request.getContextPath() %>/view/menu.jsp"><li>メニュー</li></a>
							<a href="<%= request.getContextPath() %>/productList"><li>商品一覧</li></a>
							<a href="<%= request.getContextPath() %>/userList"><li>ユーザー一覧</li></a>
							<a href="<%= request.getContextPath() %>/salesList"><li>売上一覧</li></a>
							<a id="logout"><li style="color:red">ログアウト</li></a>
						</ul>
					</div>
					<hr class="admin_hr"/>
				<% } %>
				<script type="text/javascript">
					// ログアウトボタンを(存在する場合)取得
					const logout = document.getElementById("logout");
					if(logout !== null){
						// ボタンを押したら
						document.getElementById('logout').onclick = function() {
							var result = window.confirm("ログアウトします。よろしいですか？");

							if(result){
								window.location.href = "http://localhost:8080/project_fleamarket_e3/logout";
							}
						}
					}
				</script>
			</header>
	</body>
</html>
