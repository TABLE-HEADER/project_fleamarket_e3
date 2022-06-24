//作成日　2022.06.24
//作成者　岩田
//ユーザー情報更新機能

package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDAO;

public class UserUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<String> error = new ArrayList<String>();
		UserDAO userDao = new UserDAO();
		User user = null;
		String errorMessage = "";

		try {

			request.setCharacterEncoding("UTF-8");

			// 各種パラメーター取得
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			String nickname = request.getParameter("nickname");
			String password = request.getParameter("password");
			String postal_code = request.getParameter("postal-code");
			String address_level1 = request.getParameter("address-level1");
			String address_level2 = request.getParameter("address-level2");
			String address_line1 = request.getParameter("address-line1");
			String address_line2 = request.getParameter("address-line2");
			String email = request.getParameter("email");
			String credit_number = request.getParameter("creditNumber");

			// 名前エラー
			if (username.equals("")) {
				error.add("名前が未入力です。");
			}

			// ニックネームエラー
			user = (User) session.getAttribute("user");
			if (nickname.equals("")) {
				error.add("ニックネームが未入力です。");
			}
			// パスワードエラー
			if (password.equals("")) {
				error.add("パスワードが未入力です。");
			}

			// 住所エラー
			if (postal_code.equals("")) {
				error.add("郵便番号が未入力です。");
			} else if (address_level1.equals("")) {
				error.add("都道府県が未入力です。");
			} else if (address_level2.equals("")) {
				error.add("市区町村が未入力です。");
			} else if (address_line1.equals("")) {
				error.add("番地等が未入力です。");

			}

			// メールエラー
			if(email.equals("")) {
				error.add("メールアドレスが未入力です。");
			} else if ((Pattern.matches("^[0-9a-zA-Z@._]+$", email)) == false) {
				error.add("正しいメールアドレスを入力してください。");
			} else if (userDao.selectByEmail(email).getUsername() != null &&
						userDao.selectByEmail(email).getUsername() != user.getUsername()) {
				error.add("既に登録されたメールアドレスです。他のメールアドレスを登録してください。");
			}

			// クレジットカードエラー
			if (credit_number.equals("")) {
				error.add("	クレジットカード番号が未入力です。");
			} else if (credit_number.length() != 16 && !credit_number.equals("")) {
				error.add("クレジットカード番号は16文字で設定してください");

			}
			// エラーがあった場合処理終了
			if (error.size() != 0) {
				return;
			}

			// データのセット
			user.setUsername(username);
			user.setNickname(nickname);
			user.setPassword(password);
			user.setPostal_code(postal_code);
			user.setAddress_level1(address_level1);
			user.setAddress_level2(address_level2);
			user.setAddress_line1(address_line1);
			user.setAddress_line2(address_line2);
			user.setEmail(email);
			user.setCredit_number(credit_number);

			userDao.update(user);

			session.setAttribute("user", user);

		} catch (IllegalStateException e) {
			errorMessage = "DB接続エラーの為、ユーザー情報の更新は行えませんでした。";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (error.size() != 0) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/userUpdate.jsp").forward(request, response);
			} else if (!errorMessage.equals("")) {
				request.setAttribute("error", errorMessage);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else {

				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			}
		}
	}
}
