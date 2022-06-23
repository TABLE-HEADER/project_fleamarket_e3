package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import bean.User;
import dao.ProductDAO;
import dao.UserDAO;

public class MyProductListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		String error = "";
		String cmd = "";

		try {

			// 変数宣言
			String productname = "";
			String category = "";
			request.setCharacterEncoding("UTF-8");

			// change_on_sale
			if(request.getParameter("changeid") != null){
				ProductDAO objProductDao = new ProductDAO();
				Product product = objProductDao .selectByProductid(Integer.parseInt(request.getParameter("changeid")));
				product.setOn_sale(!product.getOn_sale());
				objProductDao.update(product);
			}


			if(request.getParameter("productname") != null){
				productname = (String)request.getParameter("productname");
			}

			if(request.getParameter("category") != null){
				category = (String)request.getParameter("category");
			}

			//セッションオブジェクトの取得
			HttpSession session = request.getSession();

			// セッション情報からユーザーの取得
			// User user = (User)session.getAttribute("user");

			// テスト用。後で必ず消すこと！！！
			UserDAO objUserDao = new UserDAO();
			User user = objUserDao.selectByUserid(24);

			if(user == null) {
				// セッション切れならerror.jspへフォワード
				request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
				request.setAttribute("cmd","logout");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			}

			// DAOオブジェクト宣言
			ProductDAO objProductDao = new ProductDAO();
			ArrayList<Product> list;

			if(productname.equals("") && category.equals("")) {

				// selectAllの実行
				list = objProductDao.selectBySellerid(user.getUserid());

			}
			else {

				// searchNameAndCategoryの実行
				list = objProductDao.searchNCS(productname, category, user.getUserid());

			}

			// ArrayListをリクエストスコープへ格納
			request.setAttribute("product_list", list);

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		}catch(Exception e){
			error ="予期せぬエラーが発生しました。<br>"+e;
			cmd = "logout";
		}finally {

			// jspへのフォワード
			if(!error.equals("")) {
 				request.setAttribute("error", error);
 				request.setAttribute("cmd", cmd);
 				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/view/myProductList.jsp").forward(request, response);
			}
		}

	}
}
