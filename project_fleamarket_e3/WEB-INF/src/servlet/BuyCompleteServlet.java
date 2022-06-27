/* プログラム名:BuyCompleteServlet
 * 作成者      :近藤
 * 作成者      :2022/06/24
 * 概要        :buyConfirm.jspから購入完了画面buyComplete.jspへ遷移処理するサーブレット
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
import dao.DealDAO;
import dao.ProductDAO;
import dao.UserDAO;

public class BuyCompleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {
			request.setCharacterEncoding("UTF-8");


			// セッションから"user"を取得する
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			// セッション切れ
			if (user == null) {
				error = "セッション切れの為、購入はできません。"; cmd = "login";
				return;
			}


			// buyConfirm.jspからフォーム送信した 商品の購入数量を取得
			String quantity = request.getParameter("quantity");
			int intQuantity = Integer.parseInt(quantity);

			// 商品の購入数量が在庫数を上回る際にはerror.jspに遷移し、以下の処理をスキップ
			String stock = request.getParameter("stock");
			int intStock = Integer.parseInt(stock);
			if (intQuantity > intStock) {
				error = "申し訳ございません。在庫数を上回る注文の為、購入を完了できませんでした。" + "商品の在庫数をご確認の上、もう一度購入個数をご入力ください。";
				return;
			}

			// 商品の購入数量が0以下の場合error.jspに遷移し、以下の処理をスキップ
			if (intQuantity <= 0) {
				error = "購入数量は1以上をご入力ください";
			}

			// buyConfirm.jspからproductidを取得
			int intProductid = 0;
			String productid = request.getParameter("pro_id");
			intProductid = Integer.parseInt(productid);

			// Productオブジェクトに格納するメソッド「selectByProductid」を呼び出し、
			ProductDAO objProductDao = new ProductDAO();
			Product product = objProductDao.selectByProductid(intProductid);

			String productname = product.getProductname();
			int price = product.getPrice();

			// Dealオブジェクトに格納
			Deal deal = new Deal();
			deal.setProductname(productname);
			deal.setPrice(price);
			deal.setQuantity(intQuantity);

			/*
			 * 在庫数を購入数分減少させる 残量格納用変数
			 */
			int updateStock = intStock - intQuantity;

			product.setStock(updateStock);
			objProductDao.update(product);

			// buyComplete.jspへ飛ばすリクエストスコープ
			request.setAttribute("deal", deal);
			request.setAttribute("user", user);


			//DealDaoのinsertメソッドを呼び出し、各種取引情報の更新
			DealDAO dealObjDao = new DealDAO();

			deal.setProductid(intProductid);
			int buyerid = user.getUserid();
			deal.setBuyerid(buyerid);
			int total = intQuantity * price;
			deal.setTotal(total);
			String state = "入金待ち";
			deal.setState(state);
			deal.setQuantity(intQuantity);

			dealObjDao.insert(deal);




		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} finally {
			// エラーなしならbuyComplete.jspへフォワード処理
			if (error.equals("")) {
				request.getRequestDispatcher("/view/buyComplete.jsp").forward(request, response);
			} else {
				// エラーありならerror.jspへフォワード処理
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}
	}
}
