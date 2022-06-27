<!--
作成日　2022.06.23
作成者　岩田
ユーザー一覧表示機能
 -->

<%@ page import="bean.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="servlet.UserListServlet"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
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
<link rel="stylesheet">
<!--
<meta http-equiv="Content-Type" >
<meta http-equiv="Content-Style-Type" content="text/css">
-->
<style type="text/css">
#wrapper {
	max-width: 970px;
	margin: 10px auto 0px;
	text-align: center;
	font-family: "游ゴシック Medium", "Yu Gothic Medium";
	font-weight: bold;
	color: #464646;
}
</style>
</head>

<body id="wrapper">
<%@include file="../common/header.jsp" %>
<h1>ユーザー一覧</h1>

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

<br>
<br>
<table align="center" widch="850">

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
		<th bgcolor="#6666ff">ユーザーID</th>
		<th bgcolor="#6666ff">本名 </th>
		<th bgcolor="#6666ff">ニックネーム</th>
		<th bgcolor="#6666ff">生年月日</th>
		<th bgcolor="#6666ff">メールアドレス</th>
		<th bgcolor="#6666ff">都道府県</th>
		<th bgcolor="#6666ff">取引実績</th>
	</tr>


<%for(int i=0;i<userList.size();i++){
	User User =userList.get(i);%>
	<tr>
		<td align="center"><%=User.getUserid() %></td>
		<td align="center"><%=User.getUsername() %></td>
		<td align="center"><%=User.getNickname() %></td>
		<td align="center"><%=User.getBirthday() %></td>
		<td align="center"><%=User.getEmail() %></td>
		<td align="center"><%=User.getAddress_level1() %></td>
		<td align="center"><%=User.getDeal_count() %></td>
	</tr>

<%} %>

</table>
<%@include file="../common/footer.jsp" %>
</body>

</html>