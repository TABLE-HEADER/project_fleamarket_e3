package bean;

public class Product {

	// 変数宣言
	private int	productid;
	private int	sellerid;
	private String	nickname;
	private String	address_level1;
	private int	deal_count;
	private String	productname;
	private String	category;
	private int	stock;
	private int	price;
	private boolean	on_sale;
	private String	remark;
	private String	image;
	private String	created_at;

	// 引数無しコンストラクタ
	public Product() {
		this.productid	= 0;
		this.sellerid	= 0;
		this.nickname	= null;
		this.address_level1	= null;
		this.deal_count	= 0;
		this.productname	= null;
		this.category	= null;
		this.stock	= 0;
		this.price	= 0;
		this.on_sale	= false;
		this.remark	= null;
		this.image	= null;
		this.created_at	= null;
	}

	// 引数有りコンストラクタ
	public Product(int productid, int sellerid, String nickname, String address_level1, int deal_count, String productname, String category, int stock, int price, boolean on_sale, String remark, String image, String created_at) {
		this.productid	= productid;
		this.sellerid	= sellerid;
		this.nickname	= nickname;
		this.address_level1	= address_level1;
		this.deal_count	= deal_count;
		this.productname	= productname;
		this.category	= category;
		this.stock	= stock;
		this.price	= price;
		this.on_sale	= on_sale;
		this.remark	= remark;
		this.image	= image;
		this.created_at	= created_at;
	}

	// セッターメソッド
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setAddress_level1(String address_level1) {
		this.address_level1 = address_level1;
	}
	public void setDeal_count(int deal_count) {
		this.deal_count = deal_count;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setOn_sale(boolean on_sale) {
		this.on_sale = on_sale;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	// ゲッターメソッド
	public int getProductid() {
		return this.productid;
	}
	public int getSellerid() {
		return this.sellerid;
	}
	public String getNickname() {
		return this.nickname;
	}
	public String getAddress_level1() {
		return this.address_level1;
	}
	public int getDeal_count() {
		return this.deal_count;
	}
	public String getProductname() {
		return this.productname;
	}
	public String getCategory() {
		return this.category;
	}
	public int getStock() {
		return this.stock;
	}
	public int getPrice() {
		return this.price;
	}
	public boolean getOn_sale() {
		return this.on_sale;
	}
	public String getRemark() {
		return this.remark;
	}
	public String getImage() {
		return this.image;
	}
	public String getCreated_at() {
		return this.created_at;
	}

}
