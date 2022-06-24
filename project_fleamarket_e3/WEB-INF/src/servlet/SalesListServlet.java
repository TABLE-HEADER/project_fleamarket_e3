package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Deal;
import bean.Product;
import dao.DealDAO;

public class SalesListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		// エラー用の各変数を用意
		String error = "";
		String cmd = "logout";

		try {

			// 検索用の情報があるなら受け取る
			String category = "";
			String year = "";
			String month = "";
			request.setCharacterEncoding("UTF-8");

			if(request.getParameter("category") != null){
				category = (String)request.getParameter("category");
			}
			if(request.getParameter("year") != null){
				year = (String)request.getParameter("year");
			}
			if(request.getParameter("month") != null){
				month = (String)request.getParameter("month");
			}

			// DAOオブジェクト宣言
			DealDAO objDealDao = new DealDAO();
			ArrayList<Deal> list;

			if(category.equals("") && year.equals("") && month.equals("")) {

				// selectAllの実行
				list = objDealDao.selectAll();

			}
			else {

				// searchByCategoryAndYearAndMonthの実行
				list = objDealDao.searchByCategoryAndPeriod(category, year, month);

			}

			// ArrayListをリクエストスコープへ登録
			request.setAttribute("deal_list", list);

		}catch(IllegalStateException e) {
			error = "DB接続エラーの為、売上表示ができませんでした。";
			cmd = "logout";
		}catch(Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "logout";
		}finally {
			// errorの内容でフォワード先を分岐
			if(error.equals("")) {
				request.getRequestDispatcher("/view/salesList.jsp").forward(request, response);
			}else {

				// エラーの場合、リクエストスコープにエラー用の変数を登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
