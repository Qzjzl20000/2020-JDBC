package src.cn.edu.zucc.waimai.model;


public class BeanSjMJ {
	public static final String[] SjMJtableTitles={"满减编号","满减金额","折扣金额","能否与优惠券叠加"};

	private int sj_id;
	private int mj_id;
	private float mj_top_money;
	private float mj_discount_money;
	private int if_add_youhuiquan;
	
	public String getCell(int col){
		if(col==0) return ""+this.getMj_id();
		else if(col==1) return ""+this.getMj_top_money()+"元";
		else if(col==2) return ""+this.getMj_discount_money()+"元";
		else if(col==3) {
			if(this.getIf_add_youhuiquan()==1)return "是";
			else return "否";
		}			
		else return "";
	}

	public int getSj_id() {
		return sj_id;
	}

	public void setSj_id(int sj_id) {
		this.sj_id = sj_id;
	}

	public int getMj_id() {
		return mj_id;
	}

	public void setMj_id(int mj_id) {
		this.mj_id = mj_id;
	}

	public float getMj_top_money() {
		return mj_top_money;
	}

	public void setMj_top_money(float mj_top_money) {
		this.mj_top_money = mj_top_money;
	}

	public float getMj_discount_money() {
		return mj_discount_money;
	}

	public void setMj_discount_money(float mj_discount_money) {
		this.mj_discount_money = mj_discount_money;
	}

	public int getIf_add_youhuiquan() {
		return if_add_youhuiquan;
	}

	public void setIf_add_youhuiquan(int if_add_youhuiquan) {
		this.if_add_youhuiquan = if_add_youhuiquan;
	}

	
	
}
