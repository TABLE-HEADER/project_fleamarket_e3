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
import util.SendMail;

public class DealListServlet extends HttpServlet{
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
				deal.setState("発送中");
				deal.setSent_at(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
				objDealDao.update(deal);

				// メールを送る
				SendMail sendMail = new SendMail();

				// 買い手のUserを作る
				UserDAO objUserDao = new UserDAO();
				User buyer = objUserDao.selectByUserid(deal.getBuyerid());

				// 売り手のUserを作る
				ProductDAO objProductDao = new ProductDAO();
				Product dealProduct = objProductDao.selectByProductid(deal.getProductid());
				User seller = objUserDao.selectByUserid(dealProduct.getSellerid());

				sendMail.setFrom("system.project.team39@kanda-it-school-system.com", "神田フリマ");
				sendMail.setRecipients(buyer.getEmail());
				sendMail.setSubject("発送完了");
				sendMail.setText(
							buyer.getUsername() + "様\n\n" +
							"神田フリマです。\n" +
							buyer.getUsername() + "様が購入された商品の発送が完了いたしました。\n" +
							"---------------発送された商品---------------");

				String text = "【商品名】\t" + deal.getProductname() + "\n" +
							"【個数】\t" + deal.getQuantity() + "\n" +
							"【出品者】\t" + seller.getNickname() + "\n" +
							"【発送元】\t" + dealProduct.getAddress_level1() + "\n" +
							"【お届け先】\n" +
							"郵便番号\t" + buyer.getPostal_code() + "\n" +
							"都道府県\t" + buyer.getAddress_level1() + "\n" +
							"市区町村\t" + buyer.getAddress_level2() + "\n" +
							"番地等\t" + buyer.getAddress_line1() + "\n" +
							"建物名・部屋番号\t" + buyer.getAddress_line2() + "\n\n";

				sendMail.setText(text);

				String text2 = "商品の到着までもうしばらくお待ちください。\n\n";

				sendMail.setText(text2);

				String footerText = "--------------------------------------\n" +
									"このメールは神田フリマのホームページから商品購入をした方へ自動送信しております。\n" +
									"お心当たりのない方は、恐れ入りますが下記へその旨をご連絡いただけますと幸いです。\n" +
									"--------------------------------------\n" +
									"神田フリマ\n" +
									"東京都千代田区神田紺屋町１１ 岩田ビル 3F\n" +
									"TEL ：03-5809-8321\n" +
									"MAIL ：system.project.team39@kanda-it-school-system.com\n" +
									"URL ：http://localhost:8080/project_fleamarket_e3/view/productList.jsp\n" +
									"--------------------------------------\n";

				sendMail.setText(footerText);
				sendMail.sendMail();
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

			// セッション切れの場合ログアウト
			if (user == null) {
				error = "セッション切れの為、取引一覧表示は行えませんでした。";
				cmd = "logout";
				return;
			}

			// DAOオブジェクト宣言
			DealDAO objDealDao = new DealDAO();
			ArrayList<Deal> list;

			if(productname.equals("") && category.equals("")) {

				// selectAllの実行
				list = objDealDao.selectBySellerid(user.getUserid());

			}
			else {

				// searchNCBの実行
				list = objDealDao.searchNCS(productname, category, user.getUserid());

			}

			// ArrayListをリクエストスコープへ格納
			request.setAttribute("deal_list", list);

		}catch (IllegalStateException e) {
			error = "DB接続エラーの為、取引一覧表示は行えませんでした。";
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
				request.getRequestDispatcher("/view/dealList.jsp").forward(request, response);
			}
		}

	}
}
