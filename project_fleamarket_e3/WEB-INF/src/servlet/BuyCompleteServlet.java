/* プログラム名:BuyCompleteServlet
 * 作成者      :近藤
 * 作成者      :2022/06/24
 * 概要        :buyConfirm.jspから購入完了画面buyComplete.jspへ遷移処理するサーブレット
 */

package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import util.MyFormat;
import util.SendMail;

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

			// 以下はメールを送るためのプログラム
			SendMail sendMail = new SendMail();

			// 買い手のUserを作る(既にuserが買い手としているので、buyerという変数はそれを参照するようにする。)
			User buyer = user;

			// 売り手のUserを作る(該当ProductからSelleridを取ってきて、それをもとにUserDAOのselectByをかける)
			UserDAO objUserDao = new UserDAO();
			User seller = objUserDao.selectByUserid(product.getSellerid());

			// メールその1(buyer側に送信される)
			sendMail.setFrom("system.project.team39@kanda-it-school-system.com", "神田フリマ");
			sendMail.setRecipients(buyer.getEmail());
			sendMail.setSubject("購入完了");
			sendMail.setText(
						buyer.getUsername() + "様\n\n" +
						"神田フリマです。\n" +
						buyer.getUsername() + "様の購入処理が完了いたしました。\n" +
						"---------------購入した商品---------------");

			String text = "【商品名】\t" + deal.getProductname() + "\n" +
						"【個数】\t" + deal.getQuantity() + "\n" +
						"【出品者】\t" + seller.getNickname() + "\n" +
						"【発送元】\t" + product.getAddress_level1() + "\n" +
						"【お届け先】\n" +
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
			String dispLimitDate = MyFormat.datetimeFormat(limitDate);

			String text2 = "本日から3日以内に入金をお願いいたします。\n" +
							"入金期限：" + dispLimitDate + "\n\n";

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


			// メールその2(buyer側に送信される)
			SendMail sendMail2 = new SendMail();

			sendMail2.setFrom("system.project.team39@kanda-it-school-system.com", "神田フリマ");
			sendMail2.setRecipients(seller.getEmail());
			sendMail2.setSubject("あなたの出品した商品が購入されました");
			sendMail2.setText(
						seller.getUsername() + "様\n\n" +
						"神田フリマです。\n" +
						seller.getUsername() + "様の出品された商品が購入されました。\n" +
						"---------------該当商品---------------");

			String text3 = "【商品名】\t" + deal.getProductname() + "\n" +
						"【個数】\t" + deal.getQuantity() + "\n" +
						"【出品者】\t" + seller.getNickname() + "\n" +
						"【発送元】\t" + product.getAddress_level1() + "\n" +
						"【発送先】\n" +
						"郵便番号\t" + buyer.getPostal_code() + "\n" +
						"都道府県\t" + buyer.getAddress_level1() + "\n" +
						"市区町村\t" + buyer.getAddress_level2() + "\n" +
						"番地等\t" + buyer.getAddress_line1() + "\n" +
						"建物名・部屋番号\t" + buyer.getAddress_line2() + "\n\n";

			sendMail2.setText(text3);

			String text4 = "購入者からの入金があるまでしばらくお待ちください。\n" +
							"入金期限：" + dispLimitDate + "\n\n";

			sendMail2.setText(text4);

			sendMail2.setText(footerText);
			sendMail2.sendMail();

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} catch(Exception e) {
			error = "予期せぬエラーが発生しました。";
			cmd = "logout";
		}finally {
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
