/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.32
 * Generated at: 2022-06-23 06:48:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import bean.User;
import java.util.ArrayList;
import bean.User;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/common/header.jsp", Long.valueOf(1655965003420L));
    _jspx_dependants.put("/common/footer.jsp", Long.valueOf(1655880923746L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("bean.User");
    _jspx_imports_classes.add("java.util.ArrayList");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write(" <!-- importの必要性が生じた場合この中に記述してください -->\r\n");
      out.write("\r\n");
      out.write("<!-- あらかじめ作動させる必要があるプログラムは以下に記述 -->\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>会員登録</title>\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t\t/*\r\n");
      out.write("\t\t\tCSSを書く際はこの中に記述してください\r\n");
      out.write("\t\t\tなお別途CSSファイルを作った方がいいときは言ってください\r\n");
      out.write("\t\t\t */\r\n");
      out.write("\t\t\ttable{\r\n");
      out.write("\t\t\t\tmargin:25 auto;\r\n");
      out.write("\t\t\t\twidth:700px;\r\n");
      out.write("\t\t\t\tborder:1px solid black;\r\n");
      out.write("\t\t\t\tborder-collapse:collapse;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tth, td{\r\n");
      out.write("\t\t\t\tborder:1px solid gray;\r\n");
      out.write("\t\t\t\tpadding:5px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tth{\r\n");
      out.write("\t\t\t\twidth:40%;\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\tpadding:10 0 10 10;\r\n");
      out.write("\t\t\t\tborder:1px solid gray;\r\n");
      out.write("\t\t\t\tbackground-color:#8080801a;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.message_th{\r\n");
      out.write("\t\t\t\twidth:40%;\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\tpadding:10 0 10 10;\r\n");
      out.write("\t\t\t\tborder:2px solid #dc143c;\r\n");
      out.write("\t\t\t\tbackground-color:#ffb6c1;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\ttd{\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\twidth:75%;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.jusyo_support{\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\tdisplay:inline-block;\r\n");
      out.write("\t\t\t\twhite-space: nowrap;\r\n");
      out.write("\t\t\t\twidth:40%;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tinput{\r\n");
      out.write("\t\t\t\tpadding:5px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.submit{\r\n");
      out.write("\t\t\t\tpadding:10px;\r\n");
      out.write("\t\t\t\tmargin:20px;\r\n");
      out.write("\t\t\t\tborder:2px solid orange;\r\n");
      out.write("\t\t\t\tborder-radius:15px;\r\n");
      out.write("\t\t\t\tbackground-color:#ffff0054;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.submit:hover{\r\n");
      out.write("\t\t\t\tbackground-color:#bdbd0057;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.submit:active{\r\n");
      out.write("\t\t\t\tborder: 2px inset #ff6800;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.attention{\r\n");
      out.write("\t\t\t\twidth:656px;\r\n");
      out.write("\t\t\t\tpadding:20px;\r\n");
      out.write("\t\t\t\tborder:1px solid #E0E0E0;\r\n");
      out.write("\t\t\t\tmargin:30 auto 30;\r\n");
      out.write("\t\t\t\tfont-size:small;\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t.send_button, .revise_button{\r\n");
      out.write("\t\t\t\tcolor:black;\r\n");
      out.write("\t\t\t\tfont-size:large;\r\n");
      out.write("\t\t\t\tfont-weight: bold;\r\n");
      out.write("\t\t\t\tborder:2px solid #ffa500;\r\n");
      out.write("\t\t\t\tborder-radius:500px;\r\n");
      out.write("\t\t\t\tbackground-color:#ffffab;\r\n");
      out.write("\t\t\t\twidth:300px;\r\n");
      out.write("\t\t\t\theight:50px;\r\n");
      out.write("\t\t\t\tmargin:10 auto 30;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.revise_button{\r\n");
      out.write("\t\t\t\tfont-size:medium;\r\n");
      out.write("\t\t\t\tbackground-color:#f2f2f2;\r\n");
      out.write("\t\t\t\tborder:2px solid #9f9c9c;\r\n");
      out.write("\t\t\t\twidth:200px;\r\n");
      out.write("\t\t\t\theight:30px;\r\n");
      out.write("\t\t\t\tmargin:10 auto 10;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.send_button:hover{\r\n");
      out.write("\t\t\t\tcursor: pointer;\r\n");
      out.write("\t\t\t\tbackground-color:#ffb70054;\r\n");
      out.write("\t\t\t\ttransition: all .5s;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.send_button:active{\r\n");
      out.write("\t\t\t\tborder: 2px inset #ff6800;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.revise_button:hover{\r\n");
      out.write("\t\t\t\tcursor: pointer;\r\n");
      out.write("\t\t\t\tbackground-color:#d7cece;\r\n");
      out.write("\t\t\t\ttransition: all .5s;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t.message{\r\n");
      out.write("\t\t\t\twidth:656px;\r\n");
      out.write("\t\t\t\tpadding:20px;\r\n");
      out.write("\t\t\t\tborder:2px solid #dc143c;\r\n");
      out.write("\t\t\t\tmargin:20 auto 30;\r\n");
      out.write("\t\t\t\ttext-align:left;\r\n");
      out.write("\t\t\t\tbackground-color:#ffb6c1;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body id=\"wrapper\">\r\n");
      out.write("\t\t<!-- header -->\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

User user = (User)session.getAttribute("user");

String authority = "";
String name = "かんちゃん";

/*
if(user != null){
	if(user.getAuthority()){
		authority = "会員";
		name = user.getNickname();
	}else{
		authority = "管理者";
		name = user.getNickname();
	}
}
*/

/*
if(user == null){
	// セッション切れならerror.jspへフォワード
	request.setAttribute("error","セッション切れの為、画面が表示できませんでした。");
	request.setAttribute("cmd","logout");
	request.getRequestDispatcher("/view/error.jsp").forward(request, response);
	return;
}
*/

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title></title>\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t\t#wrapper{\r\n");
      out.write("\t\t\t\tmax-width:1200px;\r\n");
      out.write("\t\t\t\tmargin:0px auto 0px;\r\n");
      out.write("\t\t\t\ttext-align:center;\r\n");
      out.write("\t\t\t\tfont-family:\"游ゴシック Medium\",\"Yu Gothic Medium\";\r\n");
      out.write("\t\t\t\tfont-weight: bold;\r\n");
      out.write("\t\t\t\tcolor: #464646;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tbutton, input, select, textarea, th, td, li {\r\n");
      out.write("\t\t\t\tfont-family : inherit;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tbutton:hover, a:hover{\r\n");
      out.write("\t\t\t\tcursor:pointer;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tform{\r\n");
      out.write("\t\t\t\tmargin-block-end:0em;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.kome{\r\n");
      out.write("\t\t\t\tfont-size:small;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t#menu{\r\n");
      out.write("\t\t\t\tfloat:right;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t#menu_ul{\r\n");
      out.write("\t\t\t\tdisplay:flex;\r\n");
      out.write("\t\t\t\twidth:700px;\r\n");
      out.write("\t\t\t\tpadding:10px;\r\n");
      out.write("\t\t\t\tmargin:0px;\r\n");
      out.write("\t\t\t\tflex-direction: row;\r\n");
      out.write("\t\t\t\tflex-wrap: wrap;\r\n");
      out.write("\t\t\t\tjustify-content:flex-end;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t#menu_ul li{\r\n");
      out.write("\t\t\t\tlist-style: none;\r\n");
      out.write("\t\t\t\twidth:150px;\r\n");
      out.write("\t\t\t\tpadding:5px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t#menu_ul li a{\r\n");
      out.write("\t\t\t\ttext-decoration: none;\r\n");
      out.write("\t\t\t\tcolor:inherit;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.footer{\r\n");
      out.write("\t\t\t\tposition:sticky;\r\n");
      out.write("\t\t\t\tbottom:0; top:100vh;\r\n");
      out.write("\t\t\t\twidth:100%;\r\n");
      out.write("\t\t\t\tfont-size:small;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.copyright{\r\n");
      out.write("\t\t\t\tfont-size:small;\r\n");
      out.write("\t\t\t\theight:25px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.hr, .admin_hr{\r\n");
      out.write("\t\t\t\tmargin:8px auto 0px;\r\n");
      out.write("\t\t\t\tclear: both;\r\n");
      out.write("\t\t\t\theight:2px;\r\n");
      out.write("\t\t\t\tbackground-color:orange;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.admin_hr{\r\n");
      out.write("\t\t\t\tbackground-color:green;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.bottom_button_right, .admin_bottom_button_right{\r\n");
      out.write("\t\t\t\tfloat:right;\r\n");
      out.write("\t\t\t\theight:25px;\r\n");
      out.write("\t\t\t\twidth:150px;\r\n");
      out.write("\t\t\t\tpadding:3px 5px 1px;\r\n");
      out.write("\t\t\t\tmargin:10px 0px 0px 1px;\r\n");
      out.write("\t\t\t\tborder-top:2px solid orange;\r\n");
      out.write("\t\t\t\tborder-right:2px solid orange;\r\n");
      out.write("\t\t\t\tborder-left:2px solid orange;\r\n");
      out.write("\t\t\t\tborder-radius: 8px 8px 0 0;\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tbackground-color:#ffff0054;\r\n");
      out.write("\t\t\t\tcolor: #464646;\r\n");
      out.write("\t\t\t\tfont-size:medium;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.footer_a{\r\n");
      out.write("\t\t\t\ttext-decoration: none;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.bottom_button_right:hover, .admin_bottom_button_right:hover{\r\n");
      out.write("\t\t\t\tcursor: pointer;\r\n");
      out.write("\t\t\t\tbackground-color:#ffb70054;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.bottom_button_right:active{\r\n");
      out.write("\t\t\t\tborder-top: 2px inset #ff6800;\r\n");
      out.write("\t\t\t\tborder-right: 2px inset #ff6800;\r\n");
      out.write("\t\t\t\tborder-left: 2px inset #ff6800;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.admin_bottom_button_right{\r\n");
      out.write("\t\t\t\tborder-top:2px solid green;\r\n");
      out.write("\t\t\t\tborder-right:2px solid green;\r\n");
      out.write("\t\t\t\tborder-left:2px solid green;\r\n");
      out.write("\t\t\t\tbackground-color:#9bff7f54;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.admin_bottom_button_right:hover{\r\n");
      out.write("\t\t\t\tbackground-color:#9bff7fd1;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.admin_bottom_button_right:active{\r\n");
      out.write("\t\t\t\tborder-top: 2px inset #005200;\r\n");
      out.write("\t\t\t\tborder-right: 2px inset #005200;\r\n");
      out.write("\t\t\t\tborder-left: 2px inset #005200;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body id=\"wrapper\">\r\n");
      out.write("\t\t<!-- header -->\r\n");
      out.write("\t\t\t<header style=\"position:sticky; top:0px; background-color:white; padding:5px 0px 0px;\">\r\n");
      out.write("\t\t\t\t");
 if(authority.equals("")){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"logo\" style=\"float:left; padding:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/productList.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"");
      out.print( request.getContextPath());
      out.write("/image/logo.png\"\r\n");
      out.write("\t\t\t\t\t\t\t\theight=\"80px\">\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"menu\">\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"menu_ul\">\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/productList\">商品一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"color:red\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/login.jsp\">ログイン</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/register.jsp\">会員登録</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<hr class=\"hr\"/>\r\n");
      out.write("\t\t\t\t");
 }else if(authority.equals("会員")){
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"logo\" style=\"float:left; padding:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/productList.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"");
      out.print( request.getContextPath());
      out.write("/image/logo.png\"\r\n");
      out.write("\t\t\t\t\t\t\t\theight=\"80px\">\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div style=\"float:left; height:80;\">\r\n");
      out.write("\t\t\t\t\t\t<span style=\"display:block;padding:30px 30px 30px; font-weight:normal\">\r\n");
      out.write("\t\t\t\t\t\t\t");
      out.print( name );
      out.write(" <span style=\"font-weight:bold\">さん</span></span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"menu\">\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"menu_ul\">\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/menu.jsp\">メニュー</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/productList\">商品一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/myProductInsert.jsp\">出品する</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/buyList\">購入一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/dealList\">取引一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/myProductList\">出品一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/userDetail\">ユーザー情報の変更</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"color:red\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/logout\">ログアウト</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<hr class=\"hr\"/>\r\n");
      out.write("\t\t\t\t");
 }else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"logo\" style=\"float:left; padding:5px;\">\r\n");
      out.write("\t\t\t\t\t\t<a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/productList.jsp\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"");
      out.print( request.getContextPath());
      out.write("/image/logo.png\"\r\n");
      out.write("\t\t\t\t\t\t\t\theight=\"80px\">\r\n");
      out.write("\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<div id=\"menu\">\r\n");
      out.write("\t\t\t\t\t\t<ul id=\"menu_ul\">\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/view/menu.jsp\">メニュー</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/productList\">商品一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/userList\">ユーザー一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"");
      out.print( request.getContextPath() );
      out.write("/salesList\">売上げ一覧</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li style=\"color:red\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/logout\">ログアウト</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<hr class=\"admin_hr\"/>\r\n");
      out.write("\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t</header>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- contents -->\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<h2 style=\"margin:15px auto 10px;\">会員登録</h2>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");
 if(messageList != null && !messageList.isEmpty()){ 
      out.write("\r\n");
      out.write("\t\t\t\t<p class=\"message\">\r\n");
      out.write("\t\t\t\t\t");
 for(String message : messageList) { 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t・");
      out.print( message );
      out.write("<br>\r\n");
      out.write("\t\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("\t\t\t");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<script src=\"https://yubinbango.github.io/yubinbango/yubinbango.js\" charset=\"UTF-8\"></script>\r\n");
      out.write("\t\t\t<form action=\"");
      out.print( request.getContextPath() );
      out.write("/registerConfirm\" method=\"post\" class=\"h-adr\">\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" class=\"p-country-name\" value=\"Japan\">\r\n");
      out.write("\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t");
 if(emailMessage.equals("a")){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<th class=\"message_th\">メールアドレス</th>\r\n");
      out.write("\t\t\t\t\t\t");
 }else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<th>メールアドレス\r\n");
      out.write("\t\t\t\t\t\t\t\t<br><span class=\"kome\">※ログイン時に必要になります</span></th>\r\n");
      out.write("\t\t\t\t\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"email\" name=\"email\"  value=\"");
      out.print( email );
      out.write("\" size=\"25\"\r\n");
      out.write("\t\t\t\t\t\t\tpattern=\".+\\.[a-zA-Z0-9][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"knada_english@kanda.com\" autocomplete=\"email\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>パスワード\r\n");
      out.write("\t\t\t\t\t\t\t<br><span class=\"kome\">※ログイン時に必要になります</span></th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"password\"   size=\"25\"\r\n");
      out.write("\t\t\t\t\t\t\tautocomplete=\"new-password\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>ニックネーム\r\n");
      out.write("\t\t\t\t\t\t\t<br><span class=\"kome\">※他ユーザーに公開されます</span></th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"nickname\"  value=\"");
      out.print( nickname );
      out.write("\" size=\"25\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"かんちゃん\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>お名前</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"username\" value=\"");
      out.print( username );
      out.write("\" size=\"25\" maxlength=\"30\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"神田太郎\" autocomplete=\"name\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>生年月日</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"date\" name=\"birthday\" value=\"");
      out.print( birthday );
      out.write("\" required=\"required\"\r\n");
      out.write("\t\t\t\t\t\t\tautocomplete=\"bday\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th rowspan=\"5\">住所</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"jusyo_support\">郵便番号</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"postal_code\" value=\"");
      out.print( postal_code );
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tmaxlength=\"8\" placeholder=\"101-0035\" autocomplete=\"postal-code\" class=\"p-postal-code\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"jusyo_support\">都道府県</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"address_level1\" value=\"");
      out.print( address_level1 );
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"東京都\" autocomplete=\"address-level1\" class=\"p-region\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"jusyo_support\">市区町村</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"address_level2\" value=\"");
      out.print( address_level2 );
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"千代田区神田紺屋町\" autocomplete=\"address-level2\" class=\"p-locality\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"jusyo_support\">番地等</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"address_line1\" value=\"");
      out.print( address_line1 );
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"11番地\" autocomplete=\"address-line1\" class=\"p-street-address\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<span class=\"jusyo_support\">建物名・部屋番号</span>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"address_line2\" value=\"");
      out.print( address_line2 );
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"岩田ビル 3F\" autocomplete=\"address-line2\" class=\"p-extended-address\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>クレジットカード番号</th>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"credit_number\"  value=\"");
      out.print( credit_number );
      out.write("\" size=\"25\"\r\n");
      out.write("\t\t\t\t\t\t\tminlength=\"16\" maxlength=\"16\" pattern=\"^[0-9]+$\"\r\n");
      out.write("\t\t\t\t\t\t\tplaceholder=\"8888888888888888\" autocomplete=\"cc-number\" required=\"required\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<p class=\"attention\">\r\n");
      out.write("\t\t\t\t■個人情報の取り扱いについて(事前同意事項)<br><br>\r\n");
      out.write("\t\t\t\t弊社の<a href=\"https://policies.google.com/privacy\" target=\"_blank\">プライバシーポリシー</a>と\r\n");
      out.write("\t\t\t\t<a href=\"https://policies.google.com/terms\" target=\"_blank\">利用規約</a>が適用されます。\r\n");
      out.write("\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t<br>\r\n");
      out.write("\t\t\t\tまた、ご入力いただきましたお客さまの個人情報は、\r\n");
      out.write("\t\t\t\t弊社がお客さまのご要望やお問い合わせに対応させていただく目的で、\r\n");
      out.write("\t\t\t\tご連絡・ご案内のために利用させていただくことがあります。</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<input class=\"send_button\" type=\"submit\" value=\"同意して入力内容を確認する\">\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- footer -->\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\r\n");



      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title></title>\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("\t\t</style>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body id=\"wrapper\">\r\n");
      out.write("\t\t<!-- footer -->\r\n");
      out.write("\t\t\t<footer class=\"footer\">\r\n");
      out.write("\t\t\t\t");
if(authority.equals("") || authority.equals("会員")){ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href=\"#\"class=\"footer_a\"><div class=\"bottom_button_right\">ページトップへ</div></a>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print( request.getContextPath() );
      out.write("/productList\" class=\"footer_a\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"bottom_button_right\">ホームへ</div>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<hr class=\"hr\"/>\r\n");
      out.write("\t\t\t\t\t<p class=\"copyright\">Copyright(C)2022 All Rights Reserved.</p>\r\n");
      out.write("\t\t\t\t");
}else{ 
      out.write("\r\n");
      out.write("\t\t\t\t\t<a href=\"#\"class=\"footer_a\"><div class=\"admin_bottom_button_right\">ページトップへ</div></a>\r\n");
      out.write("\t\t\t\t\t<a href=\"");
      out.print( request.getContextPath() );
      out.write("/productList\" class=\"footer_a\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"admin_bottom_button_right\">ホームへ</div>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<hr class=\"admin_hr\"/>\r\n");
      out.write("\t\t\t\t\t<p class=\"copyright\">Copyright(C)2022 All Rights Reserved.</p>\r\n");
      out.write("\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t</footer>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
