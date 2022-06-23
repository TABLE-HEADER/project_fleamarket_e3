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
<form class="search_form" action="<%=request.getContextPath()%>/userList">

</form>

<br>
<br>
<table align="center" widch="850">

	<tr>
		<th>ユーザーID</th>
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
		<td><%=User.getUserid() %></td>
		<td><%=User.getUsername() %></td>
		<td><%=User.getNickname() %></td>
		<td><%=User.getBirthday() %></td>
		<td><%=User.getEmail() %></td>
		<td><%=User.getAddress_level1() %></td>
		<td><%=User.getDeal_count() %></td>
	</tr>

<%} %>

</table>
<%@include file="../common/footer.jsp" %>
</body>

</html>