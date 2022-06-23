package dao;
import java.sql.*;
import java.util.*;

import bean.Product;

public class ProductDAO{

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

	// selectByProductid
	public Product selectByProductid(int productid) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product = new Product();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE productid = " + productid + "";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			if(rs.next()){
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0]);

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
		return product;
	}

	// selectBySellerid
	public ArrayList<Product> selectBySellerid(int sellerid) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE sellerid = " + sellerid + " "
				+ "ORDER BY p.created_at DESC, productid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			while(rs.next()){
				product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0].split(" ")[0]);

				list.add(product);

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

	// selectAll
	public ArrayList<Product> selectAll(){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
		+ "INNER JOIN userinfo u ON p.sellerid = u.userid ORDER BY p.created_at DESC, productid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			while(rs.next()){
				product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0]);

				list.add(product);
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


	// データベースに新たなProduct情報を登録するメソッド
	public void insert(Product product) {

		String sql = "INSERT INTO productinfo VALUES ("
		+ "NULL, "
		+ product.getSellerid() + ", "
		+ "'" + product.getProductname() + "'" + ", "
		+ "'" + product.getCategory() + "'" + ", "
		+ product.getStock() + ", "
		+ product.getPrice() + ", "
		+ product.getOn_sale() + ", "
		+ "'" + product.getRemark() + "'" + ", "
		+ "NULL" + ", "
		+ "NOW()" + ")";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = ProductDAO.getConnection();
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

	// データベースからproductidで指定された1件のProduct情報の削除を行うメソッド
	public void delete(int productid) {

		String sql = "DELETE FROM productinfo WHERE productid = " + productid + "";

		Connection con = null;
		Statement  smt = null;

		try{

			con = ProductDAO.getConnection();
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


	// データベースのProduct情報を更新するメソッド
	public void update(Product product) {

		String sql = "UPDATE productinfo SET "
		+ "productid = " + product.getProductid() + ", "
		+ "sellerid = " + product.getSellerid() + ", "
		+ "productname = '" + product.getProductname() + "'" + ", "
		+ "category = '" + product.getCategory() + "'" + ", "
		+ "stock = " + product.getStock() + ", "
		+ "price = " + product.getPrice() + ", "
		+ "on_sale = " + product.getOn_sale() + ", "
		+ "remark = '" + product.getRemark() + "'" + ", "
		+ "image = NULL" + ", "
		+ "created_at = '" + product.getCreated_at() + "'" + " "
		+ "WHERE productid = " + product.getProductid() + "";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = ProductDAO.getConnection();
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

	// search
	public ArrayList<Product> search(String productname){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE productname LIKE '%" + productname + "%' "
				+ "ORDER BY p.created_at DESC, productid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			while(rs.next()){
				product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0]);

				list.add(product);
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

	// search
	public ArrayList<Product> searchNameAndCategory(String productname, String category){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE productname LIKE '%" + productname + "%' "
				+ "AND category LIKE '%" + category + "%' "
				+ "ORDER BY p.created_at DESC, productid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			while(rs.next()){
				product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0]);

				list.add(product);
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

	// searchNCS
	public ArrayList<Product> searchNCS(String productname, String category, int sellerid){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE productname LIKE '%" + productname + "%' "
				+ "AND category LIKE '%" + category + "%' "
				+ "AND sellerid = " + sellerid + " "
				+ "ORDER BY p.created_at DESC, productid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をProductオブジェクトに格納
			while(rs.next()){
				product = new Product();
				product.setProductid(rs.getInt("productid"));
				product.setSellerid(rs.getInt("sellerid"));
				product.setNickname(rs.getString("nickname"));
				product.setAddress_level1(rs.getString("address_level1"));
				product.setDeal_count(rs.getInt("deal_count"));
				product.setProductname(rs.getString("productname"));
				product.setCategory(rs.getString("category"));
				product.setStock(rs.getInt("stock"));
				product.setPrice(rs.getInt("price"));
				product.setOn_sale(rs.getBoolean("on_sale"));
				product.setRemark(rs.getString("remark"));
				product.setImage(null);
				product.setCreated_at(rs.getString("created_at").split(" ")[0]);

				list.add(product);
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

