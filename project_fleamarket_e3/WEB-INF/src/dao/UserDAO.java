package dao;
import java.sql.*;
import java.util.*;

import bean.User;

public class UserDAO{

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

	// selectByUserid
	public User selectByUserid(int userid) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		User user = new User();

		//SQL文
		String sql = "SELECT * FROM userinfo WHERE userid = " + userid + "";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をUserオブジェクトに格納
			if(rs.next()){
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthday(rs.getString("birthday"));
				user.setPostal_code(rs.getString("postal_code"));
				user.setAddress_level1(rs.getString("address_level1"));
				user.setAddress_level2(rs.getString("address_level2"));
				user.setAddress_line1(rs.getString("address_line1"));
				user.setAddress_line2(rs.getString("address_line2"));
				user.setEmail(rs.getString("email"));
				user.setCredit_number(rs.getString("credit_number"));
				user.setDeal_count(rs.getInt("deal_count"));
				user.setAuthority(rs.getBoolean("authority"));
				String created_at = rs.getString("created_at");
				user.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
		return user;
	}


	// DBのuserinfoテーブルから指定のemailを持つUserを返す
	public User selectByEmail(String email) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		User user = new User();

		//SQL文
		String sql = "SELECT * FROM userinfo WHERE email = '" + email + "'";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をUserオブジェクトに格納
			if(rs.next()){
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthday(rs.getString("birthday"));
				user.setPostal_code(rs.getString("postal_code"));
				user.setAddress_level1(rs.getString("address_level1"));
				user.setAddress_level2(rs.getString("address_level2"));
				user.setAddress_line1(rs.getString("address_line1"));
				user.setAddress_line2(rs.getString("address_line2"));
				user.setEmail(rs.getString("email"));
				user.setCredit_number(rs.getString("credit_number"));
				user.setDeal_count(rs.getInt("deal_count"));
				user.setAuthority(rs.getBoolean("authority"));
				String created_at = rs.getString("created_at");
				user.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
		return user;
	}


	// DBのuserinfoテーブルから指定ユーザーとパスワードの条件に合致する情報を取得するメソッド定義
	public User selectByUser(String email , String password) {

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		User user = new User();

		//SQL文
		String sql = "SELECT * FROM userinfo WHERE email = '" + email + "' AND password = '" + password + "'";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をUserオブジェクトに格納
			if(rs.next()){
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthday(rs.getString("birthday"));
				user.setPostal_code(rs.getString("postal_code"));
				user.setAddress_level1(rs.getString("address_level1"));
				user.setAddress_level2(rs.getString("address_level2"));
				user.setAddress_line1(rs.getString("address_line1"));
				user.setAddress_line2(rs.getString("address_line2"));
				user.setEmail(rs.getString("email"));
				user.setCredit_number(rs.getString("credit_number"));
				user.setDeal_count(rs.getInt("deal_count"));
				user.setAuthority(rs.getBoolean("authority"));
				String created_at = rs.getString("created_at");
				user.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

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
		return user;
	}

	// selectAll
	public ArrayList<User> selectAll(){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		User user;
		ArrayList<User> list = new ArrayList<User>();

		//SQL文
		String sql = "SELECT * FROM userinfo ORDER BY created_at DESC, userid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をUserオブジェクトに格納
			while(rs.next()){
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthday(rs.getString("birthday"));
				user.setPostal_code(rs.getString("postal_code"));
				user.setAddress_level1(rs.getString("address_level1"));
				user.setAddress_level2(rs.getString("address_level2"));
				user.setAddress_line1(rs.getString("address_line1"));
				user.setAddress_line2(rs.getString("address_line2"));
				user.setEmail(rs.getString("email"));
				user.setCredit_number(rs.getString("credit_number"));
				user.setDeal_count(rs.getInt("deal_count"));
				user.setAuthority(rs.getBoolean("authority"));
				String created_at = rs.getString("created_at");
				user.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

				list.add(user);
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


	// データベースに新たなUser情報を登録するメソッド
	public void insert(User user) {

		String sql = "INSERT INTO userinfo VALUES ("
		+ "NULL, "
		+ "'" + user.getPassword() + "'" + ", "
		+ "'" + user.getUsername() + "'" + ", "
		+ "'" + user.getNickname() + "'" + ", "
		+ "'" + user.getBirthday() + "'" + ", "
		+ "'" + user.getPostal_code() + "'" + ", "
		+ "'" + user.getAddress_level1() + "'" + ", "
		+ "'" + user.getAddress_level2() + "'" + ", "
		+ "'" + user.getAddress_line1() + "'" + ", "
		+ "'" + user.getAddress_line2() + "'" + ", "
		+ "'" + user.getEmail() + "'" + ", "
		+ "'" + user.getCredit_number() + "'" + ", "
		+ user.getDeal_count() + ", "
		+ user.getAuthority() + ", "
		+ "NOW()" + ")";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = UserDAO.getConnection();
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

	// データベースからuseridで指定された1件のUser情報の削除を行うメソッド
	public void delete(int userid) {

		String sql = "DELETE FROM userinfo WHERE userid = " + userid + "";

		Connection con = null;
		Statement  smt = null;

		try{

			con = UserDAO.getConnection();
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


	// データベースのUser情報を更新するメソッド
	public void update(User user) {

		String sql = "UPDATE userinfo SET "
		+ "userid = " + user.getUserid() + ", "
		+ "password = '" + user.getPassword() + "'" + ", "
		+ "username = '" + user.getUsername() + "'" + ", "
		+ "nickname = '" + user.getNickname() + "'" + ", "
		+ "birthday = '" + user.getBirthday() + "'" + ", "
		+ "postal_code = '" + user.getPostal_code() + "'" + ", "
		+ "address_level1 = '" + user.getAddress_level1() + "'" + ", "
		+ "address_level2 = '" + user.getAddress_level2() + "'" + ", "
		+ "address_line1 = '" + user.getAddress_line1() + "'" + ", "
		+ "address_line2 = '" + user.getAddress_line2() + "'" + ", "
		+ "email = '" + user.getEmail() + "'" + ", "
		+ "credit_number = '" + user.getCredit_number() + "'" + ", "
		+ "deal_count = " + user.getDeal_count() + ", "
		+ "authority = " + user.getAuthority() + ", "
		+ "created_at = '" + user.getCreated_at() + "'" + " "
		+ "WHERE userid = " + user.getUserid() + "";

		sql = sql.replace("'null'", "NULL");

		Connection con = null;
		Statement  smt = null;

		try{

			con = UserDAO.getConnection();
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

	// searchUsernameAndNickname
	public ArrayList<User> searchUsernameAndNickname(String username, String nickname){

		//変数宣言
		Connection con = null;
		Statement  smt = null;
		User user;
		ArrayList<User> list = new ArrayList<User>();

		//SQL文
		String sql = "SELECT * FROM userinfo WHERE username LIKE '%" + username + "%' "
				+ "AND nickname LIKE '%" + nickname + "%' "
				+ "ORDER BY created_at DESC, userid DESC";

		try{
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			//取得した結果をUserオブジェクトに格納
			while(rs.next()){
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setBirthday(rs.getString("birthday"));
				user.setPostal_code(rs.getString("postal_code"));
				user.setAddress_level1(rs.getString("address_level1"));
				user.setAddress_level2(rs.getString("address_level2"));
				user.setAddress_line1(rs.getString("address_line1"));
				user.setAddress_line2(rs.getString("address_line2"));
				user.setEmail(rs.getString("email"));
				user.setCredit_number(rs.getString("credit_number"));
				user.setDeal_count(rs.getInt("deal_count"));
				user.setAuthority(rs.getBoolean("authority"));
				String created_at = rs.getString("created_at");
				user.setCreated_at(created_at != null ? created_at.split(" ")[0] : null);

				list.add(user);
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

