package src.cn.edu.zucc.waimai.model;

import java.sql.Timestamp;

public class BeanSjYHQ {
	public static final String[] SjYHQtableTitles={"优惠券编号","优惠金额","集单要求数","开始日期","结束日期"};

	private int Sj_id;
	private int youhuiquan_id;
	private float youhui_money;
	private int jidan_least_count;
	private Timestamp youhuiquan_begin_time;
	private Timestamp youhuiquan_end_time;
	
	public String getCell(int col){
		if(col==0) return ""+this.getYouhuiquan_id();
		else if(col==1) return ""+this.getYouhui_money()+"元";
		else if(col==2) return ""+this.getJidan_least_count()+"单";
		else if(col==3) return ""+this.getYouhuiquan_begin_time();
		else if(col==4) return ""+this.getYouhuiquan_end_time();
		else return "";
	}
	
	public int getSj_id() {
		return Sj_id;
	}

	public void setSj_id(int sj_id) {
		Sj_id = sj_id;
	}

	public int getYouhuiquan_id() {
		return youhuiquan_id;
	}

	public void setYouhuiquan_id(int youhuiquan_id) {
		this.youhuiquan_id = youhuiquan_id;
	}

	public float getYouhui_money() {
		return youhui_money;
	}

	public void setYouhui_money(float youhui_money) {
		this.youhui_money = youhui_money;
	}

	public int getJidan_least_count() {
		return jidan_least_count;
	}

	public void setJidan_least_count(int jidan_least_count) {
		this.jidan_least_count = jidan_least_count;
	}

	public Timestamp getYouhuiquan_begin_time() {
		return youhuiquan_begin_time;
	}

	public void setYouhuiquan_begin_time(Timestamp youhuiquan_begin_time) {
		this.youhuiquan_begin_time = youhuiquan_begin_time;
	}

	public Timestamp getYouhuiquan_end_time() {
		return youhuiquan_end_time;
	}

	public void setYouhuiquan_end_time(Timestamp youhuiquan_end_time) {
		this.youhuiquan_end_time = youhuiquan_end_time;
	}

	
	
}
