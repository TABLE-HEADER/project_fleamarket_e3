//作成日　2022.06.24
//作成者　岩田
//ユーザー情報更新機能

package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDAO;

public class UserDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = "";

		try {
			request.setCharacterEncoding("UTF-8");
			UserDAO userDao = new UserDAO();
			int id = Integer.parseInt(request.getParameter("userid"));
			User user = userDao.selectByUserid(id);
			request.setAttribute("userDetail", user);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザー詳細を表示できませんでした。";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (error.equals("")) {
				// 一覧画面へフォワード
				request.getRequestDispatcher("view/userUpdate.jsp").forward(request, response);
			} else {
				// エラー画面へフォワード
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}

	}
}
