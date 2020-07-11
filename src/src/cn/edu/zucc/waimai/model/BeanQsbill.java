package src.cn.edu.zucc.waimai.model;

import java.sql.Timestamp;

public class BeanQsbill {
	public static BeanQsbill currentLoginQS;
	public static final String[] QSbilltableTitles={"骑手id","订单编号","骑手入账时间","骑手入账金额","骑手评价星级"};
	private int qs_id;
	private int order_id;
	private Timestamp qs_getmoney_time;
	private float qs_getmoney;
	private int sp_evaluate_qsxinji;
	
	public String getCell(int col){
		if(col==0) return ""+this.getQs_id();
		else if(col==1) return ""+this.getOrder_id();
		else if(col==2) return ""+this.getQs_getmoney_time();
		else if(col==3) return ""+this.getQs_getmoney();
		else if(col==4) return ""+this.getSp_evaluate_qsxinji();
		else return "";
	}
	
	public int getQs_id() {
		return qs_id;
	}

	public void setQs_id(int qs_id) {
		this.qs_id = qs_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Timestamp getQs_getmoney_time() {
		return qs_getmoney_time;
	}

	public void setQs_getmoney_time(Timestamp qs_getmoney_time) {
		this.qs_getmoney_time = qs_getmoney_time;
	}

	public float getQs_getmoney() {
		return qs_getmoney;
	}

	public void setQs_getmoney(float qs_getmoney) {
		this.qs_getmoney = qs_getmoney;
	}

	public int getSp_evaluate_qsxinji() {
		return sp_evaluate_qsxinji;
	}

	public void setSp_evaluate_qsxinji(int sp_evaluate_qsxinji) {
		this.sp_evaluate_qsxinji = sp_evaluate_qsxinji;
	}

	

}
