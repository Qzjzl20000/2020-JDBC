package src.cn.edu.zucc.waimai.model;

import java.sql.Timestamp;

public class BeanUserYHQ {
	public static final String[] UserYHQtableTitles={"商家编号","优惠券编号","优惠金额","优惠券数量","结束日期"};
	private int user_id;
	private int youhuiquan_id;
	private int sj_id;
	private float youhui_money;
	private int youhuiquan_count;
	private Timestamp youhuiquan_end_time;
	
	public String getCell(int col){
		if(col==0) return ""+this.getSj_id();
		else if(col==1) return ""+this.getYouhuiquan_id();
		else if(col==2) return ""+this.getYouhui_money()+"元";
		else if(col==3) return ""+this.getYouhuiquan_count()+"张";
		else if(col==4) return ""+this.getYouhuiquan_end_time();
		else return "";
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getYouhuiquan_id() {
		return youhuiquan_id;
	}

	public void setYouhuiquan_id(int youhuiquan_id) {
		this.youhuiquan_id = youhuiquan_id;
	}

	public int getSj_id() {
		return sj_id;
	}

	public void setSj_id(int sj_id) {
		this.sj_id = sj_id;
	}

	public float getYouhui_money() {
		return youhui_money;
	}

	public void setYouhui_money(float youhui_money) {
		this.youhui_money = youhui_money;
	}

	public int getYouhuiquan_count() {
		return youhuiquan_count;
	}

	public void setYouhuiquan_count(int youhuiquan_count) {
		this.youhuiquan_count = youhuiquan_count;
	}

	public Timestamp getYouhuiquan_end_time() {
		return youhuiquan_end_time;
	}

	public void setYouhuiquan_end_time(Timestamp youhuiquan_end_time) {
		this.youhuiquan_end_time = youhuiquan_end_time;
	}
	
	
}
