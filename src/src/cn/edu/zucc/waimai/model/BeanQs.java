package src.cn.edu.zucc.waimai.model;

import java.sql.Timestamp;

public class BeanQs {
	public static BeanQs currentLoginQS;
	public static final String[] QStableTitles={"骑手id","骑手名","骑手等级","骑手注册时间"};
	private int qs_id;
	private String qs_name;
	private Timestamp qs_join_date;
	private String qs_grade;

	public String getCell(int col){
		if(col==0) return ""+this.getQs_id();
		else if(col==1) return ""+this.getQs_name();
		else if(col==3) return ""+this.getQs_join_date();
		else if(col==2) return ""+this.getQs_grade();
		else return "";
	}	
	
	public int getQs_id() {
		return qs_id;
	}

	public void setQs_id(int qs_id) {
		this.qs_id = qs_id;
	}


	public String getQs_name() {
		return qs_name;
	}


	public void setQs_name(String qs_name) {
		this.qs_name = qs_name;
	}


	public Timestamp getQs_join_date() {
		return qs_join_date;
	}


	public void setQs_join_date(Timestamp qs_join_date) {
		this.qs_join_date = qs_join_date;
	}


	public String getQs_grade() {
		return qs_grade;
	}


	public void setQs_grade(String qs_grade) {
		this.qs_grade = qs_grade;
	}

}
