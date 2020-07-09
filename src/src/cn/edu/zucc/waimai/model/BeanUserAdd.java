package src.cn.edu.zucc.waimai.model;


public class BeanUserAdd {
	public static BeanUserAdd currentLoginUserAdd;
	public static final String[] UserAddtableTitles={"用户id","省","市","区","地址","收件人","收件电话"};
	private int User_add_id;
	private int User_id;
	private String User_province;
	private String User_city;
	private String User_area;
	private String user_add_detail;
	private String User_add_name;
	private String user_add_phonenum;
	public String getCell(int col){
		if(col==0) return ""+this.getUser_id();
		else if(col==1) return ""+this.getUser_province();
		else if(col==2) return ""+this.getUser_city();
		else if(col==3) return ""+this.getUser_area();
		else if(col==4) return ""+this.getUser_add_detail();
		else if(col==5) return ""+this.getUser_add_name();
		else if(col==6) return ""+this.getUser_add_phonenum();
		else return "";
	}
	public String getUser_add_detail() {
		return user_add_detail;
	}
	public void setUser_add_detail(String user_add_detail) {
		this.user_add_detail = user_add_detail;
	}
	
	public int getUser_add_id() {
		return User_add_id;
	}
	public void setUser_add_id(int user_add_id) {
		User_add_id = user_add_id;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUser_province() {
		return User_province;
	}
	public void setUser_province(String user_province) {
		User_province = user_province;
	}
	public String getUser_city() {
		return User_city;
	}
	public void setUser_city(String user_city) {
		User_city = user_city;
	}
	public String getUser_area() {
		return User_area;
	}
	public void setUser_area(String user_area) {
		User_area = user_area;
	}
	public String getUser_add_name() {
		return User_add_name;
	}
	public void setUser_add_name(String user_add_name) {
		User_add_name = user_add_name;
	}
	public String getUser_add_phonenum() {
		return user_add_phonenum;
	}
	public void setUser_add_phonenum(String user_add_phonenum) {
		this.user_add_phonenum = user_add_phonenum;
	}

}
