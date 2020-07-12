package src.cn.edu.zucc.waimai.model;

public class BeanOrder {
	public static BeanOrder currentLoginUser;
	public static final String[] OrdertableTitles={"订单id","订单状态","商id","用户id","骑手id","原始金额","结算金额",
			"满减id","优惠券id","下单时间","期望送达时间","用户地址id"};
	private int Order_id;
	private int Sj_id;
	private int User_id;
	private int Qs_id;
	private float Order_origin_money;
	private float Order_final_money;
	private int Mj_id;
	private int youhuiquan_id;
	private java.sql.Timestamp Order_set_time;
	private java.sql.Timestamp Order_set_arrive_time;
	private int User_address_id;
	private String Order_state;
	public String getCell(int col){
		if(col==0) return ""+this.getOrder_id();
		else if(col==1) return ""+this.getOrder_state();
		else if(col==2) return ""+this.getSj_id();
		else if(col==3) return ""+this.getUser_id();
		else if(col==4) return ""+this.getQs_id();
		else if(col==5) return ""+this.getOrder_origin_money();
		else if(col==6) return ""+this.getOrder_final_money();
		else if(col==7) return ""+this.getMj_id();
		else if(col==8) return ""+this.getYouhuiquan_id();
		else if(col==9) return ""+this.getOrder_set_time();
		else if(col==10) return ""+this.getOrder_set_arrive_time();
		else if(col==11) return ""+this.getUser_address_id();
		else return "";
	}
	
	
	public int getOrder_id() {
		return Order_id;
	}
	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}
	public int getSj_id() {
		return Sj_id;
	}
	public void setSj_id(int sj_id) {
		Sj_id = sj_id;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	public int getQs_id() {
		return Qs_id;
	}
	public void setQs_id(int qs_id) {
		Qs_id = qs_id;
	}
	public float getOrder_origin_money() {
		return Order_origin_money;
	}
	public void setOrder_origin_money(float order_origin_money) {
		Order_origin_money = order_origin_money;
	}
	public float getOrder_final_money() {
		return Order_final_money;
	}
	public void setOrder_final_money(float order_final_money) {
		Order_final_money = order_final_money;
	}
	public int getMj_id() {
		return Mj_id;
	}
	public void setMj_id(int mj_id) {
		Mj_id = mj_id;
	}
	public int getYouhuiquan_id() {
		return youhuiquan_id;
	}
	public void setYouhuiquan_id(int youhuiquan_id) {
		this.youhuiquan_id = youhuiquan_id;
	}
	public java.sql.Timestamp getOrder_set_time() {
		return Order_set_time;
	}
	public void setOrder_set_time(java.sql.Timestamp timestamp) {
		Order_set_time = timestamp;
	}
	public java.sql.Timestamp getOrder_set_arrive_time() {
		return Order_set_arrive_time;
	}
	public void setOrder_set_arrive_time(java.sql.Timestamp order_set_arrive_time) {
		Order_set_arrive_time = order_set_arrive_time;
	}
	public int getUser_address_id() {
		return User_address_id;
	}
	public void setUser_address_id(int user_address_id) {
		User_address_id = user_address_id;
	}
	public String getOrder_state() {
		return Order_state;
	}
	public void setOrder_state(String order_state) {
		Order_state = order_state;
	}
	
}
