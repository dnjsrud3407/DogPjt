package dog.vo;

public class CartBean {
	private int id;	// 상품번호
	private String image;
	private String kind;
	private int price;
	private int qty;	// 수량
	
	public CartBean() {
		super();
	}
	
	public CartBean(int id, String image, String kind, int price, int qty) {
		super();
		this.id = id;
		this.image = image;
		this.kind = kind;
		this.price = price;
		this.qty = qty;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
}
