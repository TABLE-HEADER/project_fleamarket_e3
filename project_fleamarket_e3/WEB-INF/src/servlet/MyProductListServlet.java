package servlet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		String productname = "";
		String category = "";

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("productname") != null){
			productname = (String)request.getParameter("productname");
			request.setAttribute("productname", productname);
		}

		if(request.getParameter("category") != null){
			category = (String)request.getParameter("category");
			request.setAttribute("category", category);
		}

		commonProcess(request, response, productname, category);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		// 登録後はポスト送信で訪れる
		commonProcess(request, response, "", "");
	}

	private void commonProcess(HttpServletRequest request, HttpServletResponse response, String productname, String category) throws ServletException, IOException {

		String error = "";
		String cmd = "";

		try {

			// delete
			if(request.getParameter("deleteid") != null) {
				ProductDAO objProductDao = new ProductDAO();
				objProductDao.delete(Integer.parseInt(request.getParameter("deleteid")));
			}

			// change_on_sale
			if(request.getParameter("changeid") != null){
				ProductDAO objProductDao = new ProductDAO();
				Product product = objProductDao.selectByProductid(Integer.parseInt(request.getParameter("changeid")));
				product.setOn_sale(!product.getOn_sale());
				objProductDao.update(product);
			}

			//セッションオブジェクトの取得
			HttpSession session = request.getSession();

			// セッション情報からユーザーの取得
			User user = (User)session.getAttribute("user");

			// セッション切れならerror.jspへフォワード
			if(user == null) {
				error = "セッション切れの為、出品一覧表示は行えませんでした。";
				cmd = "logout";
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
			error = "DB接続エラーの為、出品一覧表示は行えませんでした。";
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
