/* プログラム名:LoginServlet
 * 作成者      :近藤
 * 作成者      :2022/06/23
 * 概要        :ログインフォームからのパラメータを受け取り、権限によって専用のメニュー画面へフォワード
 */

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラー内容や画面遷移の際に付随する変数
		String error = "";
		String cmd = "";
		boolean authority = false;

		try {
			// パラメータの取得
			String email = (String) request.getParameter("email");
			String password = (String) request.getParameter("password");

			// パラメータの取得チェック
			if (email == null) {
				error = "メールアドレスを入力してください";
				cmd = "login";
				return;
			}

			if (password == null) {
				error = "パスワードを入力してください";
				cmd = "login";
				return;
			}

			// UserDAOクラスのオブジェクトを生成
			UserDAO objUserDao = new UserDAO();

			// selectByUseridメソッドを利用してDBからユーザー情報を取得
			User user = objUserDao.selectByUser(email, password);
			authority = user.getAuthority();

			// ユーザー情報の有無をチェック
			if (user.getEmail() == null) {
				error = "入力データが間違っているためログインできません。";
				cmd = "login";
				return;
			}

			// セッション登録 リクエストの都度ではなくログイン中有効
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// email とpasswordそれぞれクッキー登録(期限：5日間）
			Cookie emailCookie = new Cookie("email", user.getEmail());
			emailCookie.setMaxAge(60 * 60 * 24 * 5);
			response.addCookie(emailCookie);

			Cookie passCookie = new Cookie("password", user.getPassword());
			passCookie.setMaxAge(60 * 60 * 24 * 5);
			response.addCookie(passCookie);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "login";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);

			} else {
				// エラー時
				request.setAttribute("error", error);
				if (cmd.equals("login")) {
					// 入力データが間違っている
					request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				} else {
					request.setAttribute("cmd", cmd);
					request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				}

			}
		}
	}
}