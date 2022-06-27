/* プログラム名:ProductDetailServlet
 * 作成者      :近藤
 * 作成者      :2022/06/23
 * 概要        :商品一覧から商品詳細画面へフォワードする処理を行う
 */

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import bean.User;
import dao.ProductDAO;
import dao.UserDAO;

public class ProductDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エラー内容や画面遷移の際に付随する変数
		String error = "";
		String cmd = "";

//		// セッションから"user"を取得する
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");

		try {

//			// セッション切れ
//			if (user == null) {
//				error = "セッション切れの為、購入はできません。";
//				cmd = "login";
//				return;
//			}

			request.setCharacterEncoding("UTF-8");

			// productList.jspから受け取るパラメータ
			String productid = request.getParameter("productid");
			int intProductid = Integer.parseInt(productid);
			cmd = request.getParameter("cmd");

			ProductDAO objProductDao = new ProductDAO();

			// 受け取ったproductidをもとに商品情報を検索し、戻り値としてProductオブジェクトを取得する
			Product product = objProductDao.selectByProductid(intProductid);
			if (product.getProductid() == 0) {
				if (cmd.equals("detail")) {
					error = "表示対象の商品在しない為、詳細情報は表示できませんでした。";
				}
				return;
			}

			// リクエストスコープにProductという名前で登録
			request.setAttribute("Products", product);

		} catch (IllegalStateException e) {
			if (cmd.equals("detail")) {
				error = "DB接続エラーの為、詳細詳細は表示できませんでした。";
			} /*
				 * else if (cmd.equals("update")) { error = "DB接続エラーの為、変更画面は表示できませんでした。"; } cmd
				 * = "menu";
				 */
			return;

		} finally {
			if (error.equals("")) {
				// エラーなしならproductDetail.jspへ遷移
				request.getRequestDispatcher("/view/productDetail.jsp").forward(request, response);
			} else {
				// エラー時
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}