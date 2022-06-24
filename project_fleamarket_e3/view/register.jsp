<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="bean.User, java.util.ArrayList"%> <!-- importの必要性が生じた場合この中に記述してください -->

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

//リクエストスコープからmessageたちを受け取る
ArrayList<String> messageList = (ArrayList<String>)request.getAttribute("messageList");

//メッセージがあるかないか判定するための変数
String emailMessage = "";

//初めてこのページに来たときはmessageListはnullなので、このようなif文の条件になっている
if(messageList != null && !messageList.isEmpty()){
	for(String message : messageList) {
		if(message.equals("入力されたメールアドレスで既にユーザーが登録されています。")){
			emailMessage = "a";
			email = "";
		}
	}
}
%>

<html>
	<head>
		<title>会員登録</title>
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
			.message_th{
				width:40%;
				text-align:left;
				padding:10 0 10 10;
				border:2px solid #dc143c;
				background-color:#ffb6c1;
			}
			td{
				text-align:left;
				width:75%;
			}
			.jusyo_support{
				text-align:left;
				display:inline-block;
				white-space: nowrap;
				width:40%;
			}
			input{
				padding:5px;
			}
			.submit{
				padding:10px;
				margin:20px;
				border:2px solid orange;
				border-radius:15px;
				background-color:#ffff0054;
			}
			.submit:hover{
				background-color:#bdbd0057;
			}
			.submit:active{
				border: 2px inset #ff6800;
			}
			.attention{
				width:656px;
				padding:20px;
				border:1px solid #E0E0E0;
				margin:30 auto 30;
				font-size:small;
				text-align:left;
			}

			.send_button, .revise_button{
				color:black;
				font-size:large;
				font-weight: bold;
				border:2px solid #ffa500;
				border-radius:500px;
				background-color:#ffffab;
				width:300px;
				height:50px;
				margin:10 auto 30;
			}
			.revise_button{
				font-size:medium;
				background-color:#f2f2f2;
				border:2px solid #9f9c9c;
				width:200px;
				height:30px;
				margin:10 auto 10;
			}
			.send_button:hover{
				cursor: pointer;
				background-color:#ffb70054;
				transition: all .5s;
			}
			.send_button:active{
				border: 2px inset #ff6800;
			}
			.revise_button:hover{
				cursor: pointer;
				background-color:#d7cece;
				transition: all .5s;
			}

			.message{
				width:656px;
				padding:20px;
				border:2px solid #dc143c;
				margin:20 auto 30;
				text-align:left;
				background-color:#ffb6c1;
			}
		</style>
	</head>
	<body id="wrapper">
		<!-- header -->
			<%@include file="/common/public_header.jsp" %>

		<!-- contents -->
			<div>
				<h2 style="margin:15px auto 10px;">会員登録</h2>
			</div>

			<% if(messageList != null && !messageList.isEmpty()){ %>
				<p class="message">
					<% for(String message : messageList) { %>
						・<%= message %><br>
					<% } %>
				</p>
			<% } %>

			<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
			<form action="<%= request.getContextPath() %>/registerConfirm" method="post" class="h-adr">
				<input type="hidden" class="p-country-name" value="Japan">
				<table>
					<tr>
						<% if(emailMessage.equals("a")){ %>
							<th class="message_th">メールアドレス</th>
						<% }else{ %>
							<th>メールアドレス
								<br><span class="kome">※ログイン時に必要になります</span></th>
						<% } %>
						<td>
							<input type="email" name="email"  value="<%= email %>" size="25"
							pattern=".+\.[a-zA-Z0-9][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]"
							placeholder="knada_english@kanda.com" autocomplete="email" required="required">
						</td>
					</tr>
					<tr>
						<th>パスワード
							<br><span class="kome">※ログイン時に必要になります</span></th>
						<td>
							<input type="password" name="password"   size="25"
							autocomplete="new-password" required="required">
						</td>
					</tr>
					<tr>
						<th>ニックネーム
							<br><span class="kome">※他ユーザーに公開されます</span></th>
						<td>
							<input type="text" name="nickname"  value="<%= nickname %>" size="25"
							placeholder="かんちゃん" required="required">
						</td>
					</tr>
				</table>


				<table>
					<tr>
						<th>お名前</th>
						<td>
							<input type="text" name="username" value="<%= username %>" size="25" maxlength="30"
							placeholder="神田太郎" autocomplete="name" required="required">
						</td>
					</tr>
					<tr>
						<th>生年月日</th>
						<td>
							<input type="date" name="birthday" value="<%= birthday %>" required="required"
							autocomplete="bday">
						</td>
					</tr>
					<tr>
						<th rowspan="5">住所</th>
						<td>
							<span class="jusyo_support">郵便番号</span>
							<input type="text" name="postal_code" value="<%= postal_code %>"
							maxlength="8" placeholder="101-0035" autocomplete="postal-code" class="p-postal-code" required="required">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">都道府県</span>
							<input type="text" name="address_level1" value="<%= address_level1 %>"
							placeholder="東京都" autocomplete="address-level1" class="p-region" required="required">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">市区町村</span>
							<input type="text" name="address_level2" value="<%= address_level2 %>"
							placeholder="千代田区神田紺屋町" autocomplete="address-level2" class="p-locality" required="required">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">番地等</span>
							<input type="text" name="address_line1" value="<%= address_line1 %>"
							placeholder="11番地" autocomplete="address-line1" class="p-street-address" required="required">
						</td>
					</tr>
					<tr>
						<td>
							<span class="jusyo_support">建物名・部屋番号</span>
							<input type="text" name="address_line2" value="<%= address_line2 %>"
							placeholder="岩田ビル 3F" autocomplete="address-line2" class="p-extended-address">
						</td>
					</tr>
					<tr>
						<th>クレジットカード番号</th>
						<td>
							<input type="text" name="credit_number"  value="<%= credit_number %>" size="25"
							minlength="16" maxlength="16" pattern="^[0-9]+$"
							placeholder="8888888888888888" autocomplete="cc-number" required="required">
						</td>
					</tr>
				</table>

				<p class="attention">
				■個人情報の取り扱いについて(事前同意事項)<br><br>
				弊社の<a href="https://policies.google.com/privacy" target="_blank">プライバシーポリシー</a>と
				<a href="https://policies.google.com/terms" target="_blank">利用規約</a>が適用されます。
				<br>
				<br>
				また、ご入力いただきましたお客さまの個人情報は、
				弊社がお客さまのご要望やお問い合わせに対応させていただく目的で、
				ご連絡・ご案内のために利用させていただくことがあります。</p>

				<input class="send_button" type="submit" value="同意して入力内容を確認する">
			</form>

		<!-- footer -->
			<%@include file="/common/footer.jsp" %>
	</body>
</html>