<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User" %>

<%
User user = (User)session.getAttribute("user");

String authority = "";
String name = "かんちゃん";

/*
if(user != null){
	if(user.getAuthority()){
		authority = "会員";
		name = user.getName();
	}else{
		authority = "管理者";
		name = user.getNickname();
	}
}
*/

/*if(user == null){
	// セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
	request.setAttribute("cmd","logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}*/
%>
<html>
	<head>
		<title></title>
		<style type="text/css">
			#wrapper{
				max-width:1200px;
				margin:0px auto 0px;
				text-align:center;
				font-family:"游ゴシック Medium","Yu Gothic Medium";
				font-weight: bold;
				color: #464646;
			}
			button, input, select, textarea, th, td, li {
				font-family : inherit;
			}
			button:hover, a:hover{
				cursor:pointer;
			}
			form{
				margin-block-end:0em;
			}
			#menu{
				float:right;
			}
			#menu_ul{
				display:flex;
				width:700px;
				padding:10px;
				margin:0px;
				flex-direction: row;
				flex-wrap: wrap;
				justify-content:flex-end;
			}
			#menu_ul li{
				list-style: none;
				width:150px;
				padding:5px;
			}
			#menu_ul li a{
				text-decoration: none;
				color:inherit;
			}
			.footer{
				position:sticky;
				bottom:0; top:100vh;
				width:100%;
				font-size:small;
			}
			.copyright{
				font-size:small;
				height:25px;
			}
			.hr, .admin_hr{
				margin:8px auto 0px;
				clear: both;
				height:2px;
				background-color:orange;
			}
			.admin_hr{
				background-color:green;
			}
			.bottom_button_right, .admin_bottom_button_right{
				float:right;
				height:25px;
				width:150px;
				padding:3px 5px 1px;
				margin:10px 0px 0px 1px;
				border-top:2px solid orange;
				border-right:2px solid orange;
				border-left:2px solid orange;
				border-radius: 8px 8px 0 0;

				background-color:#ffff0054;
				color: #464646;
				font-size:medium;
			}
			.footer_a{
				text-decoration: none;
			}
			.bottom_button_right:hover, .admin_bottom_button_right:hover{
				cursor: pointer;
				background-color:#ffb70054;
			}
			.bottom_button_right:active{
				border-top: 2px inset #ff6800;
				border-right: 2px inset #ff6800;
				border-left: 2px inset #ff6800;
			}
			.admin_bottom_button_right{
				border-top:2px solid green;
				border-right:2px solid green;
				border-left:2px solid green;
				background-color:#9bff7f54;
			}
			.admin_bottom_button_right:hover{
				background-color:#9bff7fd1;
			}
			.admin_bottom_button_right:active{
				border-top: 2px inset #005200;
				border-right: 2px inset #005200;
				border-left: 2px inset #005200;
			}
		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<header style="position:sticky; top:0px; background-color:white; padding:5px 0px 0px;">
				<% if(authority.equals("")){ %>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/view/productList.jsp">
							<img src="<%= request.getContextPath()%>/image/logo.png"
								height="80px">
						</a>
					</div>

					<div id="menu">
						<ul id="menu_ul">
							<li style="color:red"><a href="<%= request.getContextPath() %>/view/login.jsp">ログイン</a></li>
							<li><a href="<%= request.getContextPath() %>/view/register.jsp">会員登録</a></li>
						</ul>
					</div>
					<hr class="hr"/>
				<% }else if(authority.equals("会員")){%>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/view/productList.jsp">
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
							<li><a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a></li>
							<li><a href="<%= request.getContextPath() %>/productList">商品一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/view/myProductInsert.jsp">出品する</a></li>
							<li><a href="<%= request.getContextPath() %>/buyList">購入一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/dealList">取引一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/myProductList">出品一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/userDetail">ユーザー情報の変更</a></li>
							<li style="color:red"><a href="<%= request.getContextPath() %>/logout">ログアウト</a></li>
						</ul>
					</div>
					<hr class="hr"/>
				<% }else{ %>
					<div id="logo" style="float:left; padding:5px;">
						<a href="<%= request.getContextPath() %>/view/productList.jsp">
							<img src="<%= request.getContextPath()%>/image/logo.png"
								height="80px">
						</a>
					</div>

					<div id="menu">
						<ul id="menu_ul">
							<li><a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a></li>
							<li><a href="<%= request.getContextPath() %>/productList">商品一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/userList">ユーザー一覧</a></li>
							<li><a href="<%= request.getContextPath() %>/salesList">売上げ一覧</a></li>
							<li style="color:red"><a href="<%= request.getContextPath() %>/logout">ログアウト</a></li>
						</ul>
					</div>
					<hr class="admin_hr"/>
				<% } %>
			</header>
	</body>
</html>
