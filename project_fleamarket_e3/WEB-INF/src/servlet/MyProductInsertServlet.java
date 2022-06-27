package servlet;

/*
 * 機能名：出品登録機能
 * 作成者：中西りりな
 * 作成日：2022/06/23
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Product;
import bean.User;
import dao.ProductDAO;

public class MyProductInsertServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<String> error = new ArrayList<String>();
		String cmd = "";
		String errorMessage = "";

		try {
			// セッションオブジェクト生成
			HttpSession session = request.getSession();
			// ユーザーオブジェクトを取得
			User user = (User) session.getAttribute("user");

			// セッション切れの場合ログアウト
			if (user == null) {
				errorMessage = "セッション切れの為、出品登録は出来ません。 ";
				cmd = "logout";
				return;
			}

			// 入力データの文字コードの指定
			request.setCharacterEncoding("UTF-8");

			// ProductDAOオブジェクト生成
			ProductDAO objDao = new ProductDAO();

			// 登録する商品を格納するProductオブジェクト生成
			Product product = new Product();

			// パラメータの取得
			String category = request.getParameter("category");
			String productname = request.getParameter("productname");
			String strStock = request.getParameter("stock");
			String strPrice = request.getParameter("price");
			String remark = request.getParameter("remark");

			// 全データの空白チェック(データが入力されているかどうか)
			if (productname.equals("")) {
				error.add("商品名が未入力です。");
			}

			if (strStock.equals("")) {
				error.add("個数が未入力です。");
			}

			if (strPrice.equals("")) {
				error.add("価格が未入力です。");
			}

			// 個数値チェック（整数かどうか）
			int stock = 0;
			try {
				stock = Integer.parseInt(strStock);
			} catch (NumberFormatException e) {
				error.add("個数の値が不正です。");
			}

			// 価格値チェック（整数かどうか）
			int price = 0;
			try {
				price = Integer.parseInt(strPrice);
			} catch (NumberFormatException e) {
				error.add("価格の値が不正です。");
			}

			if (error.size() != 0) {
				return;
			}

			// 入力情報をProductオブジェクトに格納
			product.setSellerid(user.getUserid());
			product.setCategory(category);
			product.setProductname(productname);
			product.setStock(stock);
			product.setPrice(price);
			product.setRemark(remark);

			// Base64型を受け取り、byte[]データにデコード
			String str = request.getParameter("image");
			byte[] bytes;
			if (!str.equals("")) {
				bytes = Base64.getDecoder().decode(str);
			} else {
				// セッションスコープからuser情報を取得
				bytes = product.getImage();
			}
			product.setImage(bytes);

			// insert()メソッドの呼び出し
			objDao.insert(product);

		} catch (IllegalStateException e) {
			errorMessage = "DB接続エラーの為、出品登録は行えませんでした。";
			cmd = "logout";
		} finally {

			// 未入力がある場合はmyProductInsert.jspにフォワード
			if (error.size() != 0) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/myProductInsert.jsp").forward(request, response);

			}else if (!errorMessage.equals("")) {
				// エラーがある場合はerror.jspにフォワード
				request.setAttribute("error", errorMessage);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("view/error.jsp").forward(request, response);

			}else {
			// エラーがない場合はmyProductListServletにフォワード
			request.getRequestDispatcher("/myProductList").forward(request, response);
			}
		}
	}
}