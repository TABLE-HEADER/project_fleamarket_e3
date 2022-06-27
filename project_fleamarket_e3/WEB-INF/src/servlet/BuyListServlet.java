package servlet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.Deal;
import bean.Product;
import bean.User;
import dao.DealDAO;
import dao.ProductDAO;
import dao.UserDAO;

public class BuyListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request ,HttpServletResponse response) throws ServletException ,IOException{

		String error = "";
		String cmd = "";

		try {

			// 変数宣言
			String productname = "";
			String category = "";
			request.setCharacterEncoding("UTF-8");

			// change_state
			if(request.getParameter("changeid") != null){
				DealDAO objDealDao = new DealDAO();
				Deal deal = objDealDao .selectByDealid(Integer.parseInt(request.getParameter("changeid")));
				deal.setState("発送待ち");
				deal.setPaid_at(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
				objDealDao.update(deal);
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
			User user = (User)session.getAttribute("user");

//			// テスト用。後で必ず消すこと！！！
//			UserDAO objUserDao = new UserDAO();
//			User user = objUserDao.selectByUserid(12);

			// DAOオブジェクト宣言
			DealDAO objDealDao = new DealDAO();
			ArrayList<Deal> list;

			if(productname.equals("") && category.equals("")) {

				// selectAllの実行
				list = objDealDao.selectByBuyerid(user.getUserid());

			}
			else {

				// searchNCBの実行
				list = objDealDao.searchNCB(productname, category, user.getUserid());

			}

			// ArrayListをリクエストスコープへ格納
			request.setAttribute("deal_list", list);

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
				request.getRequestDispatcher("/view/buyList.jsp").forward(request, response);
			}
		}

	}
}
