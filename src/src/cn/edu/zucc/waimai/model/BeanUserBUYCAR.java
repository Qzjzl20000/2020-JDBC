package src.cn.edu.zucc.waimai.model;


public class BeanUserBUYCAR {
	public static BeanUserBUYCAR currentLoginUser;
	public static final String[] BUYCARtableTitles= {"商家id","商品id","单品数量","单品价格"};
	private int sj_id;
	private int sp_id;
	private int user_id;
	private int sp_count;
	private float sp_one_money;

	public String getCell(int col){
		if(col==0) return ""+this.getSj_id();
		else if(col==1) return ""+this.getSp_id();
		else if(col==2) return ""+this.getSp_count();
		else if(col==3) return ""+this.getSp_one_money();
		else return "";
	}

	public int getSj_id() {
		return sj_id;
	}

	public void setSj_id(int sj_id) {
		this.sj_id = sj_id;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSp_count() {
		return sp_count;
	}

	public void setSp_count(int sp_count) {
		this.sp_count = sp_count;
	}

	public float getSp_one_money() {
		return sp_one_money;
	}

	public void setSp_one_money(float sp_one_money) {
		this.sp_one_money = sp_one_money;
	}
	
	
}
