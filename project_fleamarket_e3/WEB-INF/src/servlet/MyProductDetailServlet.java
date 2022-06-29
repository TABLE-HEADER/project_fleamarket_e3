package servlet;

/*
 * 機能名：出品詳細取得機能
 * 作成者：中西りりな
 * 作成日：2022/06/23
 */
import java.io.*;
import javax.servlet.http.*;

import bean.Product;
import bean.User;
import dao.ProductDAO;

import javax.servlet.*;

public class MyProductDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

		// セッションオブジェクト生成
		HttpSession session = request.getSession();
		// ユーザーオブジェクトを取得
		User user = (User) session.getAttribute("user");

		// 入力データの文字コードの指定
		request.setCharacterEncoding("UTF-8");

		// ProductDAOオブジェクト生成
		ProductDAO objDao = new ProductDAO();

		// 出品詳細情報を格納するProductオブジェクト生成
		Product product = new Product();

		// パラメータの取得
		String strProductid = request.getParameter("productid");
		cmd = request.getParameter("cmd");

		int productid = Integer.parseInt(strProductid);

		// 出品情報を呼び出す
		product = objDao.selectByProductid(productid);

		request.setAttribute("oldProduct", product);

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、変更画面は表示できませんでした。";
			cmd = "logout";
	} finally {
		//エラーがない場合myProductUpdate.jspにフォワード
		if (error.equals("")) {
				request.getRequestDispatcher("/view/myProductUpdate.jsp").forward(request, response);
			}else {
			// エラーがない場合はerror.jspにフォワードする
			request.setAttribute("error", error);
			request.setAttribute("cmd", cmd);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);

		}
	}

}
}
