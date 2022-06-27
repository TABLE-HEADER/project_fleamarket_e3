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

import bean.Product;
import bean.User;
import dao.ProductDAO;
import dao.UserDAO;

public class UserListServlet extends HttpServlet {
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";

		try {

			String username = "";
			String nickname = "";
			request.setCharacterEncoding("UTF-8");

			// search
			if(request.getParameter("username") != null){
				username = (String)request.getParameter("username");
			}
			if(request.getParameter("nickname") != null){
				nickname = (String)request.getParameter("nickname");
			}
			request.setCharacterEncoding("UTF-8");
			UserDAO userDao = new UserDAO();
			ArrayList<User> userList;

			//
			if(username.equals("") && nickname.equals("")) {

				// userDAOからリスト取得
				userList = userDao.selectAll();

			}
			else {

				// ユーザーネームで検索
				userList = userDao.searchUsernameAndNickname(username, nickname);

			}

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
