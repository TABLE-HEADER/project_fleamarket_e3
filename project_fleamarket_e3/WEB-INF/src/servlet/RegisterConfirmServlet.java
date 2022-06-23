package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class RegisterConfirmServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		// エラー用の変数を用意
		String error = "";
		String cmd = "list";

		// 値が不正な場合のメッセージを格納するArrayList<String>
		ArrayList<String> messageList = new ArrayList<String>();

		try {

			// 文字エンコーディングの設定
			request.setCharacterEncoding("UTF-8");

			// 入力された値を取得
			String email = request.getParameter("email");

			// 同じemailアドレスか判定
			UserDAO objUserDao = new UserDAO();
			if(email.equals(objUserDao.selectByEmail(email).getEmail())) {
				messageList.add("入力されたメールアドレスで既にユーザーが登録されています。");
			}

			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");

			String username = request.getParameter("username");
			String birthday = request.getParameter("birthday");
			String postal_code = request.getParameter("postal_code");
			String address_level1 = request.getParameter("address_level1");
			String address_level2 = request.getParameter("address_level2");
			String address_line1 = request.getParameter("address_line1");
			String address_line2 = request.getParameter("address_line2");
			String credit_number = request.getParameter("credit_number");

			// できあがったmessageListをリクエストスコープに登録
			request.setAttribute("messageList", messageList);

			// 入力された情報でUserを作り、セッションスコープに登録
			User registerUser = new User(0, password, username, nickname, birthday, postal_code,
								address_level1, address_level2, address_line1, address_line2,
								email, credit_number, 0, false, null);

			HttpSession session = request.getSession();
			session.setAttribute("registerUser", registerUser);

		}catch(Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "logout";
		}finally {

			// errorの内容でフォワード先を分岐
			if(error.equals("")) {

				// messageListの中身が空ではない場合
				if(!messageList.isEmpty()) {
					request.getRequestDispatcher("/view/register.jsp").forward(request, response);

				// messageListの中身が空の場合(正常送信)
				}else {
					request.getRequestDispatcher("/view/registerConfirm.jsp").forward(request, response);
				}

			}else {

				// エラーの場合、リクエストスコープにエラー用の変数を登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
