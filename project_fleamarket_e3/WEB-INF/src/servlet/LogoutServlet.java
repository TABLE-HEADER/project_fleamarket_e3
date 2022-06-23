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

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = "ログアウトしました。";
		String cmd = "login";

		try {
			// セッションのクリア
			HttpSession session = request.getSession();

			if (session != null) {
				session.invalidate();
			}

		} catch (IllegalStateException e) {
			message = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "login";
		} finally {
			// ログイン画面に戻す
			request.setAttribute("message", message);
			request.getRequestDispatcher("/view/login.jsp").forward(request, response);

		}
	}
}