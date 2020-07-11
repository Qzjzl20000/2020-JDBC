package src.cn.edu.zucc.waimai.model;

import java.sql.Timestamp;

public class BeanUser {
	public static BeanUser currentLoginUser;
	public static final String[] UsertableTitles={"用户id","用户名","用户性别","用户密码",
			"用户电话","用户邮箱","用户城市","注册时间","会员信息"};
	private int User_id;
	private String User_name;
	private int User_sex;
	private String User_pwd;
	private String User_phonenum;
	private String User_email;
	private String user_city;
	private java.sql.Timestamp User_register_time;
	private java.sql.Timestamp User_vip_end_time;
	public String getCell(int col){
		if(col==0) return ""+this.getUser_id();
		else if(col==1) return ""+this.getUser_name();
		else if(col==2) {
			if(this.getUser_sex()==0) {
				return "Femail";
			}
			else return "Mail";	
		}
		else if(col==3) return ""+this.getUser_pwd();
		else if(col==4) return ""+this.getUser_phonenum();
		else if(col==5) return ""+this.getUser_email();
		else if(col==6) return ""+this.getUser_city();
		else if(col==7) return ""+this.getUser_register_time();
		else if(col==8) {
			if(this.getUser_vip_end_time().before(Timestamp.valueOf("2010-01-01 00:00:00"))) {
				return "Not VIP";
			}
			else return "VIP until "+this.getUser_vip_end_time();
		}
		else return "";
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public int getUser_sex() {
		return User_sex;
	}
	public void setUser_sex(int user_sex) {
		User_sex = user_sex;
	}
	public String getUser_pwd() {
		return User_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		User_pwd = user_pwd;
	}
	public String getUser_phonenum() {
		return User_phonenum;
	}
	public void setUser_phonenum(String user_phonenum) {
		User_phonenum = user_phonenum;
	}
	public String getUser_email() {
		return User_email;
	}
	public void setUser_email(String user_email) {
		User_email = user_email;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public java.sql.Timestamp getUser_register_time() {
		return User_register_time;
	}
	public void setUser_register_time(java.sql.Timestamp user_register_time) {
		User_register_time = user_register_time;
	}
	public java.sql.Timestamp getUser_vip_end_time() {
		return User_vip_end_time;
	}
	public void setUser_vip_end_time(java.sql.Timestamp user_vip_end_time) {
		User_vip_end_time = user_vip_end_time;
	}
	
}
