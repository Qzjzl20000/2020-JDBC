package src.cn.edu.zucc.waimai.model;


public class BeanUserJD {
	public static final String[] YHJDtableTitles={"优惠券编号","商家编号","集单要求数","已购次数"};

	private int user_id;
	private int youhuiquan_id;
	private int sj_id;
	private int jidan_least_count;
	private int order_count;
	
	public String getCell(int col){
		if(col==0) return ""+this.getYouhuiquan_id();
		else if(col==1) return ""+this.getSj_id();
		else if(col==2) return ""+this.getJidan_least_count();
		else if(col==3) return ""+this.getOrder_count();
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

	public int getJidan_least_count() {
		return jidan_least_count;
	}

	public void setJidan_least_count(int jidan_least_count) {
		this.jidan_least_count = jidan_least_count;
	}

	public int getOrder_count() {
		return order_count;
	}

	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	
	
}
