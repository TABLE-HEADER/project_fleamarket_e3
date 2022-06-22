package bean;

public class User {

	// 変数宣言
	private int	userid;
	private String	password;
	private String	username;
	private String	nickname;
	private String	postal_code;
	private String	address_level1;
	private String	address_level2;
	private String	address_line1;
	private String	address_line2;
	private String	email;
	private String	credit_number;
	private int	deal_count;
	private boolean	authority;
	private String	created_at;

	// 引数無しコンストラクタ
	public User() {
		this.userid	= 0;
		this.password	= null;
		this.username	= null;
		this.nickname	= null;
		this.postal_code	= null;
		this.address_level1	= null;
		this.address_level2	= null;
		this.address_line1	= null;
		this.address_line2	= null;
		this.email	= null;
		this.credit_number	= null;
		this.deal_count	= 0;
		this.authority	= false;
		this.created_at	= null;
	}

	// 引数有りコンストラクタ
	public User(int userid, String password, String username, String nickname, String postal_code, String address_level1, String address_level2, String address_line1, String address_line2, String email, String credit_number, int deal_count, boolean authority, String created_at) {
		this.userid	= userid;
		this.password	= password;
		this.username	= username;
		this.nickname	= nickname;
		this.postal_code	= postal_code;
		this.address_level1	= address_level1;
		this.address_level2	= address_level2;
		this.address_line1	= address_line1;
		this.address_line2	= address_line2;
		this.email	= email;
		this.credit_number	= credit_number;
		this.deal_count	= deal_count;
		this.authority	= authority;
		this.created_at	= created_at;
	}

	// セッターメソッド
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public void setAddress_level1(String address_level1) {
		this.address_level1 = address_level1;
	}
	public void setAddress_level2(String address_level2) {
		this.address_level2 = address_level2;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCredit_number(String credit_number) {
		this.credit_number = credit_number;
	}
	public void setDeal_count(int deal_count) {
		this.deal_count = deal_count;
	}
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	// ゲッターメソッド
	public int getUserid() {
		return this.userid;
	}
	public String getPassword() {
		return this.password;
	}
	public String getUsername() {
		return this.username;
	}
	public String getNickname() {
		return this.nickname;
	}
	public String getPostal_code() {
		return this.postal_code;
	}
	public String getAddress_level1() {
		return this.address_level1;
	}
	public String getAddress_level2() {
		return this.address_level2;
	}
	public String getAddress_line1() {
		return this.address_line1;
	}
	public String getAddress_line2() {
		return this.address_line2;
	}
	public String getEmail() {
		return this.email;
	}
	public String getCredit_number() {
		return this.credit_number;
	}
	public int getDeal_count() {
		return this.deal_count;
	}
	public boolean getAuthority() {
		return this.authority;
	}
	public String getCreated_at() {
		return this.created_at;
	}

}
