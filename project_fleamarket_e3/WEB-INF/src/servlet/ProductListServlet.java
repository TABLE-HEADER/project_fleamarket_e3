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

			request.setCharacterEncoding("UTF-8");

			String productname = "";
			String category = "";
			int minprice = 0;
			int maxprice = 0;
			String region = "";
			Boolean in_stock = request.getParameter("in_stock") != null;

			// search
			if(request.getParameter("productname") != null){
				productname = (String)request.getParameter("productname");
			}
			if(request.getParameter("category") != null){
				category = (String)request.getParameter("category");
			}
			if(request.getParameter("minprice") != null){
				minprice = Integer.parseInt((String)request.getParameter("minprice"));
			}
			if(request.getParameter("maxprice") != null){
				maxprice = Integer.parseInt((String)request.getParameter("maxprice"));
			}
			if(request.getParameter("region") != null){
				region = (String)request.getParameter("region");
			}

			if(minprice == 0 && maxprice == 0 && region == "" && !in_stock) {

				if(productname.equals("") && category.equals("")) {

					// selectAllの実行
					list = objProductDao.selectOn_sale();

				}
				else {

					// searchNameAndCategoryの実行
					list = objProductDao.searchNCOn_sale(productname, category);

				}

			}
			else {

				if(minprice > maxprice) {
					int temp = maxprice;
					maxprice = minprice;
					minprice = temp;
				}

				// 詳細検索
				list = objProductDao.searchInDetail(productname, category, minprice, maxprice, region, in_stock);

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
