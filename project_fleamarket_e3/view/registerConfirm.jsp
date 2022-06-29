<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User, java.util.ArrayList, util.MyFormat"%> <!-- importの必要性が生じた場合この中に記述してください -->

<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->
<%
//HTML上で使うため、先に宣言しておく
String email = "";
String password = "";
String nickname = "";

String username = "";
String birthday = "";

String postal_code = "";
String address_level1 = "";
String address_level2 = "";
String address_line1 = "";
String address_line2 = "";
String credit_number = "";

//セッションからUser型の"user"を取得
User registerUser = (User)session.getAttribute("registerUser");

//userがnullでない時は各値を先ほど宣言した変数に格納
if(registerUser != null){
	email = registerUser.getEmail();
	password = registerUser.getPassword();
	nickname = registerUser.getNickname();

	username = registerUser.getUsername();
	birthday = registerUser.getBirthday();
	postal_code = registerUser.getPostal_code();
	address_level1 = registerUser.getAddress_level1();
	address_level2 = registerUser.getAddress_level2();
	address_line1 = registerUser.getAddress_line1();
	address_line2 = registerUser.getAddress_line2();
	credit_number = registerUser.getCredit_number();
}
%>

<html>
	<head>
		<title>会員登録確認</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/commonStyle.css">
		<style type="text/css">
			/*
			CSSを書く際はこの中に記述してください
			なお別途CSSファイルを作った方がいいときは言ってください
			 */
			table{
				margin:25 auto;
				width:700px;
				border:1px solid black;
				border-collapse:collapse;
			}
			th, td{
				border:1px solid gray;
				padding:5px;
			}
			th{
				width:40%;
				text-align:left;
				padding:10 0 10 10;
				border:1px solid gray;
				background-color:#8080801a;
			}
			td{
				text-align:left;
				width:75%;
			}
		</style>
		<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
		<script type="text/javascript">
			function pushHideButton(){
				const target = document.getElementById('visible_password');
				const button = document.getElementById('buttonEye');

				if (button.className === "fa fa-eye-slash") {
					target.style.visibility = "hidden";
					button.className = "fa fa-eye";
				} else {
					target.style.visibility = "visible";
					button.className = "fa fa-eye-slash";
				}
			}
		</script>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">会員登録確認</h2>
			</div>

			<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
			<input type="hidden" class="p-country-name" value="Japan">
			<table>
				<tr>
					<th>メールアドレス</th>
					<td>
						<%= email %>
					</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td>
						<span id="invisible_password">●●●●
							<span id="visible_password" style="background-color:white;"><%= password %></span>
						</span>
						<span id="buttonEye" class="fa fa-eye" onclick="pushHideButton()"></span>
					</td>
				</tr>
				<tr>
					<th>ニックネーム</th>
					<td>
						<%= nickname %>
					</td>
				</tr>
			</table>


			<table>
				<tr>
					<th>お名前</th>
					<td>
						<%= username %>
					</td>
				</tr>
				<tr>
					<th>生年月日</th>
					<td>
						<%= MyFormat.birthdayFormat(birthday) %>
					</td>
				</tr>
				<tr>
					<th rowspan="5">住所</th>
					<td>
						<span class="jusyo_support">郵便番号</span>
						<%= postal_code %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">都道府県</span>
						<%= address_level1 %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">市区町村</span>
						<%= address_level2 %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">町名・番地等</span>
						<%= address_line1 %>
					</td>
				</tr>
				<tr>
					<td>
						<span class="jusyo_support">建物名・部屋番号</span>
						<%= address_line2 %>
					</td>
				</tr>
				<tr>
					<th>クレジットカード番号</th>
					<td>
						<%= credit_number %>
					</td>
				</tr>
			</table>

			<p style="margin:30 auto">
				よろしければ以下の送信ボタンを押してください。<br>
				<span class="kome">※登録完了時にメールを送信します。</span>
			</p>

			<form action="<%= request.getContextPath()%>/view/register.jsp">
				<input type="submit" value="入力内容を修正する" class="revise_button">
			</form>

			<form action="<%= request.getContextPath() %>/register" method="post">
				<input type="submit" value="登録する" class="send_button">
			</form>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>