package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;
import util.MyFormat;
import util.SendMail;

public class RegisterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		// エラー用の各変数を用意
		String error = "";
		String cmd = "logout";

		try {
			// セッションを生成。"registerUser"のセッションを取得する
			HttpSession session = request.getSession();
			User registerUser = (User)session.getAttribute("registerUser");

			// DAOのオブジェクト化
			UserDAO objUserDao = new UserDAO();

			// insertメソッドでDB上にregisterUserを登録
			objUserDao.insert(registerUser);

			// メールを送る
			SendMail sendMail = new SendMail();

			sendMail.setFrom("system.project.team39@kanda-it-school-system.com", "神田フリマ");
			sendMail.setRecipients(registerUser.getEmail());
			sendMail.setSubject("会員登録完了");
			sendMail.setText(
						registerUser.getUsername() + "様\n\n" +
						"神田フリマです。\n" +
						"以下の情報で会員登録が完了いたしました。\n" +
						"---------------登録内容---------------");

			String text = "【メールアドレス】\t" + "このメールをお送りしているメールアドレス\n" +
						"【パスワード】\t" + "●●●●\n" +
						"【ニックネーム】\t" + registerUser.getNickname() + "\n\n" +
						"【お名前】\t" + registerUser.getUsername() + "\n" +
						"【生年月日】\t" + MyFormat.birthdayFormat(registerUser.getBirthday()) + "\n" +
						"【住所】\n" +
						"郵便番号\t" + registerUser.getPostal_code() + "\n" +
						"都道府県\t" + registerUser.getAddress_level1() + "\n" +
						"市区町村\t" + registerUser.getAddress_level2() + "\n" +
						"番地等\t" + registerUser.getAddress_line1() + "\n" +
						"建物名・部屋番号\t" + registerUser.getAddress_line2() + "\n" +
						"【クレジットカード】\t" + "●●●●\n";

			sendMail.setText(text);

			String footerText = "--------------------------------------\n" +
								"このメールは神田フリマのホームページから会員登録をした方へ自動送信しております。\n" +
								"お心当たりのない方は、恐れ入りますが下記へその旨をご連絡いただけますと幸いです。\n" +
								"--------------------------------------\n" +
								"神田フリマ\n" +
								"東京都千代田区神田紺屋町１１ 岩田ビル 3F\n" +
								"TEL ：03-5809-8321\n" +
								"MAIL ：system.project.team39@kanda-it-school-system.com\n" +
								"URL ：http://localhost:8080/project_fleamarket_e3/view/productList.jsp\n" +
								"--------------------------------------\n";

			sendMail.setText(footerText);
			sendMail.sendMail();


		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、会員登録ができませんでした。";
			cmd = "logout";
		}catch(Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "logout";
		}finally {
			// errorの内容でフォワード先を分岐
			if(error.equals("")) {
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			}else {

				// エラーの場合、リクエストスコープにエラー用の変数を登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
