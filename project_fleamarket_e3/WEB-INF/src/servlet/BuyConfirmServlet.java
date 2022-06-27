/* プログラム名:BuyConfirmServlet
 * 作成者      :近藤
 * 作成者      :2022/06/24
 * 概要        :productDetail.jspから購入確認画面buyConfirm.jspへ遷移処理するサーブレット
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Deal;
import bean.Product;
import bean.User;
import dao.ProductDAO;
import dao.UserDAO;

public class BuyConfirmServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			request.setCharacterEncoding("UTF-8");

			/*
			 * セッションから"user"を取得する HttpSession session = request.getSession(); User user =
			 * (User) session.getAttribute("user");
			 *
			 * // セッション切れ if (user == null) { error = "セッション切れの為、購入はできません。"; cmd = "login";
			 * return; }
			 */

			// productDetail.jspからp_idを取得
			int intProductid = 0;
			String productid = request.getParameter("p_id");
			intProductid = Integer.parseInt(productid);

			// Productオブジェクトに格納するメソッド「selectByProductid」を呼び出し、
			ProductDAO objProductDao = new ProductDAO();
			Product product = objProductDao.selectByProductid(intProductid);

			String productname = product.getProductname();
			int price = product.getPrice();
			int stock = product.getStock();

			// dealオブジェクトに格納
			Deal deal = new Deal();
			deal.setProductname(productname);
			deal.setPrice(price);
			deal.setProductid(intProductid);

			// useridの取得
			User user = new User();
			int userid = user.getUserid();

			// selectByUseridを呼び出し住所の特定
			UserDAO userDao = new UserDAO();
			user = userDao.selectByUserid(userid);

			String address_level1 = user.getAddress_level1();
			String address_level2 = user.getAddress_level2();

			// userオブジェクトに住所を格納
			user.setAddress_level1(address_level1);
			user.setAddress_level2(address_level2);

			// buyConfirm.jspへ飛ばすリクエストスコープ
			request.setAttribute("product", product);
			request.setAttribute("deal", deal);
			request.setAttribute("user", user);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} finally {
			// エラーなしならbuyConfirm.jspへフォワード処理
			if (error.equals("")) {
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			} else {
				// エラーありならerror.jspへフォワード処理
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
