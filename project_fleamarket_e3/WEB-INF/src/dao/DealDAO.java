package dao;
import java.sql.*;
import java.util.*;

import bean.Deal;

public class DealDAO{

	private static String RDB_DRIVE="com.mysql.jdbc.Driver";
	private static String URL="jdbc:mysql://localhost/kandafurima_db";
	private static String USER="root";
	private static String PASSWD="root123";

	// データベース接続メソッド
	public static Connection getConnection()
	{
		try{
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		}catch(Exception e){
			throw new IllegalStateException(e);
		}
	}

	// selectByDealid
	public Deal selectByDealid(int dealid) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Deal deal = new Deal();

		//SQL文
		String sql = "SELECT * FROM dealinfo d "
				+ "INNER JOIN productinfo p ON d.productid = p.productid "
				+ "INNER JOIN userinfo u ON d.buyerid = u.userid "
				+ "WHERE dealid = " + dealid + "";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDealオブジェクトに格納
			if(rs.next()){
				deal.setDealid(rs.getInt("dealid"));
				deal.setProductid(rs.getInt("productid"));
				deal.setBuyerid(rs.getInt("buyerid"));
				deal.setProductname(rs.getString("productname"));
				deal.setNickname(rs.getString("nickname"));
				deal.setPrice(rs.getInt("price"));
				deal.setQuantity(rs.getInt("quantity"));
				deal.setTotal(rs.getInt("total"));
				deal.setState(rs.getString("state"));
				deal.setBought_at(rs.getString("bought_at"));
				deal.setPaid_at(rs.getString("paid_at"));
				deal.setSent_at(rs.getString("sent_at"));

			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return deal;
	}

	// selectAll
	public ArrayList<Deal> selectAll(){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Deal deal;
		ArrayList<Deal> list = new ArrayList<Deal>();

		//SQL文
		String sql = "SELECT * FROM dealinfo d "
				+ "INNER JOIN productinfo p ON d.productid = p.productid "
				+ "INNER JOIN userinfo u ON d.buyerid = u.userid";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDealオブジェクトに格納
			while(rs.next()){
				deal = new Deal();
				deal.setDealid(rs.getInt("dealid"));
				deal.setProductid(rs.getInt("productid"));
				deal.setBuyerid(rs.getInt("buyerid"));
				deal.setProductname(rs.getString("productname"));
				deal.setNickname(rs.getString("nickname"));
				deal.setPrice(rs.getInt("price"));
				deal.setQuantity(rs.getInt("quantity"));
				deal.setTotal(rs.getInt("total"));
				deal.setState(rs.getString("state"));
				deal.setBought_at(rs.getString("bought_at"));
				deal.setPaid_at(rs.getString("paid_at"));
				deal.setSent_at(rs.getString("sent_at"));

				list.add(deal);
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}


	// データベースに新たなDeal情報を登録するメソッド
	public void insert(Deal deal) {

		String sql = "INSERT INTO dealinfo VALUES ("
		+ "NULL, "
		+ deal.getProductid() + ", "
		+ deal.getBuyerid() + ", "
		+ deal.getQuantity() + ", "
		+ deal.getTotal() + ", "
		+ "'" + deal.getState() + "'" + ", "
		+ "NOW()" + ", "
		+ "'" + deal.getPaid_at() + "'" + ", "
		+ "'" + deal.getSent_at() + "'" + ")";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = DealDAO.getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		}
		catch(Exception e){
			throw new IllegalStateException(e);
		}
		finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

	}

	// データベースからdealidで指定された1件のDeal情報の削除を行うメソッド
	public void delete(int dealid) {

		String sql = "DELETE FROM dealinfo WHERE dealid = " + dealid + "";

		Connection con = null;
		Statement  smt = null;

		try{

			con = DealDAO.getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		}
		catch(Exception e){
			throw new IllegalStateException(e);
		}
		finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

	}


	// データベースのDeal情報を更新するメソッド
	public void update(Deal deal) {

		String sql = "UPDATE dealinfo SET "
		+ "dealid = " + deal.getDealid() + ", "
		+ "productid = " + deal.getProductid() + ", "
		+ "buyerid = " + deal.getBuyerid() + ", "
		+ "quantity = " + deal.getQuantity() + ", "
		+ "total = " + deal.getTotal() + ", "
		+ "state = '" + deal.getState() + "'" + ", "
		+ "bought_at = NOW()" + ", "
		+ "paid_at = '" + deal.getPaid_at() + "'" + ", "
		+ "sent_at = '" + deal.getSent_at() + "'" + " "
		+ "WHERE dealid = " + deal.getDealid() + "";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = DealDAO.getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		}
		catch(Exception e){
			throw new IllegalStateException(e);
		}
		finally{
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}

	}

	// searchByNickname
	public ArrayList<Deal> searchByNickname(String nickname){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Deal deal;
		ArrayList<Deal> list = new ArrayList<Deal>();

		//SQL文
		String sql = "SELECT * FROM dealinfo d "
				+ "INNER JOIN productinfo p ON d.productid = p.productid "
				+ "INNER JOIN userinfo u ON d.buyerid = u.userid "
				+ "WHERE nickname LIKE '%" + nickname + "%'";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDealオブジェクトに格納
			while(rs.next()){
				deal = new Deal();
				deal.setDealid(rs.getInt("dealid"));
				deal.setProductid(rs.getInt("productid"));
				deal.setBuyerid(rs.getInt("buyerid"));
				deal.setProductname(rs.getString("productname"));
				deal.setNickname(rs.getString("nickname"));
				deal.setPrice(rs.getInt("price"));
				deal.setQuantity(rs.getInt("quantity"));
				deal.setTotal(rs.getInt("total"));
				deal.setState(rs.getString("state"));
				deal.setBought_at(rs.getString("bought_at"));
				deal.setPaid_at(rs.getString("paid_at"));
				deal.setSent_at(rs.getString("sent_at"));

				list.add(deal);
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}

	// searchByProductname
	public ArrayList<Deal> searchByProductname(String productname){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Deal deal;
		ArrayList<Deal> list = new ArrayList<Deal>();

		//SQL文
		String sql = "SELECT * FROM dealinfo d "
				+ "INNER JOIN productinfo p ON d.productid = p.productid "
				+ "INNER JOIN userinfo u ON d.buyerid = u.userid "
				+ "WHERE productname LIKE '%" + productname + "%'";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をDealオブジェクトに格納
			while(rs.next()){
				deal = new Deal();
				deal.setDealid(rs.getInt("dealid"));
				deal.setProductid(rs.getInt("productid"));
				deal.setBuyerid(rs.getInt("buyerid"));
				deal.setProductname(rs.getString("productname"));
				deal.setNickname(rs.getString("nickname"));
				deal.setPrice(rs.getInt("price"));
				deal.setQuantity(rs.getInt("quantity"));
				deal.setTotal(rs.getInt("total"));
				deal.setState(rs.getString("state"));
				deal.setBought_at(rs.getString("bought_at"));
				deal.setPaid_at(rs.getString("paid_at"));
				deal.setSent_at(rs.getString("sent_at"));

				list.add(deal);
			}

		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//リソースの開放
			if(smt != null){
				try{smt.close();}catch(SQLException ignore){}
			}
			if(con != null){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return list;
	}


}

