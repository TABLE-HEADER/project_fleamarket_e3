package servlet;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.Deal;
import bean.Product;
import bean.User;
import dao.DealDAO;
import dao.ProductDAO;
import dao.UserDAO;
import util.MyFormat;
import util.SendMail;

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
				sendMail.setRecipients(seller.getEmail());
				sendMail.setSubject("購入されたあなたの商品に入金がありました");
				sendMail.setText(
							seller.getUsername() + "様\n\n" +
							"神田フリマです。\n" +
							seller.getUsername() + "様の商品に入金がありました。\n" +
							"---------------入金された商品---------------");

				String text = "【商品名】\t" + deal.getProductname() + "\n" +
							"【個数】\t" + deal.getQuantity() + "\n" +
							"【出品者】\t" + seller.getNickname() + "\n" +
							"【発送元】\t" + dealProduct.getAddress_level1() + "\n" +
							"【発送先】\n" +
							"郵便番号\t" + buyer.getPostal_code() + "\n" +
							"都道府県\t" + buyer.getAddress_level1() + "\n" +
							"市区町村\t" + buyer.getAddress_level2() + "\n" +
							"番地等\t" + buyer.getAddress_line1() + "\n" +
							"建物名・部屋番号\t" + buyer.getAddress_line2() + "\n\n";

				sendMail.setText(text);

				// 現在日時情報で初期化されたインスタンスの生成
				Date dateObj = new Date();
				Calendar calendarDate = Calendar.getInstance();
				calendarDate.setTime(dateObj);

				// 3日先の日付を取得したいとき
				calendarDate.add(Calendar.DAY_OF_MONTH, 3);

				// 3日先にした日付をdateObjに格納
				dateObj = calendarDate.getTime();

				SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
				String limitDate = format.format( dateObj );
				String dispLimitDate = MyFormat.birthdayFormat(limitDate);

				String text2 = "本日から3日以内に発送を行ってください。\n" +
								"発送期限：" + dispLimitDate + "\n\n";

				sendMail.setText(text2);

				String footerText = "--------------------------------------\n" +
									"このメールは神田フリマのホームページから入金処理をした方へ自動送信しております。\n" +
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
				error = "セッション切れの為、購入一覧表示は行えませんでした。";
				cmd = "logout";
				return;
			}

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
			error = "DB接続エラーの為、購入一覧表示は行えませんでした。";
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
