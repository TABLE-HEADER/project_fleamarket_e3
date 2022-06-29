<!--
作成日　2022.06.23
作成者　岩田
ユーザー一覧表示機能
 -->

<%@page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.User, java.util.ArrayList, servlet.UserListServlet, util.MyFormat"%>

<%
ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");

//searchを行う場合のパラメータ取得
String username = "";
String nickname = "";

if(request.getParameter("username") != null){
	username = (String)request.getParameter("username");
}

if(request.getParameter("nickname") != null){
	nickname = (String)request.getParameter("nickname");
}
%>

<html>
	<head>
		<title>会員情報一覧</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<style type="text/css">

		</style>
	</head>

	<body id="wrapper">
		<!-- header -->
			<%@include file="../common/header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">ユーザー一覧</h2>
			</div>

			<table align="center">
				<tr>
					<td>
						<form class="search_form" action="<%=request.getContextPath()%>/userList">
							本名：<input type=text size="30" name="username" value="<%=username %>"></input>
							ニックネーム：<input type=text size="30" name="nickname" value="<%=nickname %>"></input>
							<input type="submit" value="検索"></input>
						</form>
					</td>
					<td>
						<form action="<%=request.getContextPath()%>/userList">
							<input type="submit" value="全件表示"></input>
						</form>
					</td>
				</tr>
			</table>

			<br>

			<table class="list_table" id="admin_list_table">
				<caption>
					<%if(username.equals("") && nickname.equals("")) {%>
						全件表示（<%=userList != null ? userList.size() : 0%>件）
					<%}
					else{%>
						<%=!username.equals("") ? "本名：" + username : "" %>
						<%=!username.equals("") && !nickname.equals("") ? "、" : "" %>
						<%=!nickname.equals("") ? "ニックネーム：" + nickname : ""  %>
						の検索結果（<%=userList != null ? userList.size() : 0%>件）
					<% }%>
				</caption>
				<tr>
					<th>本名 </th>
					<th>ニックネーム</th>
					<th>生年月日</th>
					<th>メールアドレス</th>
					<th>都道府県</th>
					<th>取引実績</th>
				</tr>


				<%for(int i=0;i<userList.size();i++){
					User User =userList.get(i);%>
					<tr>
						<td align="center"><%=User.getUsername() %></td>
						<td align="center"><%=User.getNickname() %></td>
						<td align="center"><%=MyFormat.birthdayFormat(User.getBirthday()) %></td>
						<td align="center"><%=User.getEmail() %></td>
						<td align="center"><%=User.getAddress_level1() %></td>
						<td align="center"><%=User.getDeal_count() %>回</td>
					</tr>
				<%} %>
			</table>

		<!-- footer -->
			<%@include file="../common/footer.jsp" %>
	</body>
</html>