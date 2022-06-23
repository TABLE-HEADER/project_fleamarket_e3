//作成日　2022.06.23
//作成者　岩田
//ユーザー一覧表示機能

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

public class UserListServlet extends HttpServlet {
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";

		try {

			request.setCharacterEncoding("UTF-8");
			UserDAO userDao = new UserDAO();

			// userDAOからリスト取得
			ArrayList<User> userList = userDao.selectAll();

			// リクエストスコープ
			request.setAttribute("userList", userList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザー一覧を表示できませんでした。";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/userList.jsp").forward(request, response);
			} else {
				// エラー画面へフォワード
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
