package member.vo;

public class MemberBean {
	private String name;
	private String id;
	private String pass;
	private String email;
	
	public MemberBean() {
		super();
	}
	
	public MemberBean(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}

	public MemberBean(String name, String id, String pass, String email) {
		super();
		this.name = name;
		this.id = id;
		this.pass = pass;
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emal) {
		this.email = emal;
	}
	
	
}
