package servlet;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Product;
import dao.ProductDAO;

public class ProductListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		String error = "";
		String cmd = "";

		try {

			// DAOオブジェクト宣言
			ProductDAO objProductDao = new ProductDAO();
			ArrayList<Product> list;

			String productname = "";
			String category = "";
			request.setCharacterEncoding("UTF-8");

			// search
			if(request.getParameter("productname") != null){
				productname = (String)request.getParameter("productname");
			}

			if(request.getParameter("category") != null){
				category = (String)request.getParameter("category");
			}

			if(productname.equals("") && category.equals("")) {

				// selectAllの実行
				list = objProductDao.selectOn_sale();

			}
			else {

				// searchNameAndCategoryの実行
				list = objProductDao.searchNCOn_sale(productname, category);

			}

			// ArrayListをリクエストスコープへ格納
			request.setAttribute("product_list", list);

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、商品一覧表示は行えませんでした。";
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
				request.getRequestDispatcher("/view/productList.jsp").forward(request, response);
			}
		}

	}
}
