package src.cn.edu.zucc.waimai.model;

public class BeanOrderContent {
	public static BeanOrderContent currentLoginUser;
	public static final String[] OrderContenttableTitles={"订单id","商家id","商品id","单品数量","单品金额","单品优惠"};
	private int Order_id;
	private int Sj_id;
	private int Sp_id;
	private int Order_one_count;
	private float Order_one_money;
	private float Order_one_discount;

	public String getCell(int col){
		if(col==0) return ""+this.getOrder_id();
		else if(col==1) return ""+this.getSj_id();
		else if(col==2) return ""+this.getSp_id();
		else if(col==3) return ""+this.getOrder_one_count();
		else if(col==4) return ""+this.getOrder_one_money();
		else if(col==5) return ""+this.getOrder_one_discount();
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

	public int getSp_id() {
		return Sp_id;
	}

	public void setSp_id(int sp_id) {
		Sp_id = sp_id;
	}

	public int getOrder_one_count() {
		return Order_one_count;
	}

	public void setOrder_one_count(int order_one_count) {
		Order_one_count = order_one_count;
	}

	public float getOrder_one_money() {
		return Order_one_money;
	}

	public void setOrder_one_money(float order_one_money) {
		Order_one_money = order_one_money;
	}

	public float getOrder_one_discount() {
		return Order_one_discount;
	}

	public void setOrder_one_discount(float order_one_discount) {
		Order_one_discount = order_one_discount;
	}
	
	
}
