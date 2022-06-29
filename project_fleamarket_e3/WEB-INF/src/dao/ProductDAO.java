package dao;
import java.sql.*;
import java.util.*;

import bean.Product;
import util.ImageConvert;

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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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

	// on_sale==trueのもののみ選択
	public ArrayList<Product> selectOn_sale() {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE on_sale = TRUE "
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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
		+ "?" + ", "
		+ "NOW()" + ")";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		PreparedStatement  smt = null;

		try{

			con = UserDAO.getConnection();
			smt = con.prepareStatement(sql);
			smt.setBytes(1, product.getImage());

			smt.executeUpdate();

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
		+ "image = ?" + ", "
		+ "created_at = '" + product.getCreated_at() + "'" + " "
		+ "WHERE productid = " + product.getProductid() + "";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		PreparedStatement  smt = null;

		try{

			con = UserDAO.getConnection();
			smt = con.prepareStatement(sql);
			smt.setBytes(1, product.getImage());

			smt.executeUpdate();

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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
	public ArrayList<Product> searchNCOn_sale(String productname, String category){

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
				+ "AND on_sale = TRUE "
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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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

	// searchInDetail
	public ArrayList<Product> searchInDetail(String productname, String category, int minprice, int maxprice, String region, boolean in_stock, int userid, boolean self_item) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		Product product;
		ArrayList<Product> list = new ArrayList<Product>();

		//SQL文
		String sql = "SELECT * FROM productinfo p "
				+ "INNER JOIN userinfo u ON p.sellerid = u.userid "
				+ "WHERE productname LIKE '%" + productname + "%' "
				+ "AND category LIKE '%" + category + "%' ";

		if(minprice != 0) {
			sql += "AND price BETWEEN " + minprice + " AND " + maxprice + " ";
		}
		if(region != "") {
			sql += "AND address_level1 = '" + region + "' ";
		}
		if(in_stock) {
			sql += "AND stock > 0 ";
		}
		if(self_item) {
			sql += "AND sellerid <> " + userid + " ";
		}

		sql += "AND on_sale = TRUE "
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
				product.setImage(ImageConvert.getByteFromResult(rs, "image"));
				String created_at = rs.getString("p.created_at");
				product.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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

