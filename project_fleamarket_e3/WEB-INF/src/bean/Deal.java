package bean;

public class Deal {

	// 変数宣言
	private int	dealid;
	private int	productid;
	private int	buyerid;
	private String	productname;
	private String	nickname;
	private int	price;
	private int	quantity;
	private int	total;
	private String	state;
	private String	bought_at;
	private String	paid_at;
	private String	sent_at;

	// 引数無しコンストラクタ
	public Deal() {
		this.dealid	= 0;
		this.productid	= 0;
		this.buyerid	= 0;
		this.productname	= null;
		this.nickname	= null;
		this.price	= 0;
		this.quantity	= 0;
		this.total	= 0;
		this.state	= null;
		this.bought_at	= null;
		this.paid_at	= null;
		this.sent_at	= null;
	}

	// 引数有りコンストラクタ
	public Deal(int dealid, int productid, int buyerid, String productname, String nickname, int price, int quantity, int total, String state, String bought_at, String paid_at, String sent_at) {
		this.dealid	= dealid;
		this.productid	= productid;
		this.buyerid	= buyerid;
		this.productname	= productname;
		this.nickname	= nickname;
		this.price	= price;
		this.quantity	= quantity;
		this.total	= total;
		this.state	= state;
		this.bought_at	= bought_at;
		this.paid_at	= paid_at;
		this.sent_at	= sent_at;
	}

	// セッターメソッド
	public void setDealid(int dealid) {
		this.dealid = dealid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setBought_at(String bought_at) {
		this.bought_at = bought_at;
	}
	public void setPaid_at(String paid_at) {
		this.paid_at = paid_at;
	}
	public void setSent_at(String sent_at) {
		this.sent_at = sent_at;
	}

	// ゲッターメソッド
	public int getDealid() {
		return this.dealid;
	}
	public int getProductid() {
		return this.productid;
	}
	public int getBuyerid() {
		return this.buyerid;
	}
	public String getProductname() {
		return this.productname;
	}
	public String getNickname() {
		return this.nickname;
	}
	public int getPrice() {
		return this.price;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public int getTotal() {
		return this.total;
	}
	public String getState() {
		return this.state;
	}
	public String getBought_at() {
		return this.bought_at;
	}
	public String getPaid_at() {
		return this.paid_at;
	}
	public String getSent_at() {
		return this.sent_at;
	}

}
