package src.cn.edu.zucc.waimai.model;


public class BeanSp {
	public static final String[] SptableTitles={"商品id","商品名","商品价格","剩余数量"};

	public static BeanSp currentLoginUser;
	private int Sj_id;
	private int Sp_id;
	private int Fl_id;
	private String Sp_name;
	private float Sp_price;
	private int Sp_left_count;
	
	public String getCell(int col){
		if(col==0) return ""+this.getSp_id();
		else if(col==1) return ""+this.getSp_name();
		else if(col==2) return ""+this.getSp_price()+"元";
		else if(col==3) return ""+this.getSp_left_count()+"份";
		else return "";
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

	public int getFl_id() {
		return Fl_id;
	}

	public void setFl_id(int fl_id) {
		Fl_id = fl_id;
	}

	public String getSp_name() {
		return Sp_name;
	}

	public void setSp_name(String sp_name) {
		Sp_name = sp_name;
	}

	public float getSp_price() {
		return Sp_price;
	}

	public void setSp_price(float sp_price) {
		Sp_price = sp_price;
	}

	public int getSp_left_count() {
		return Sp_left_count;
	}

	public void setSp_left_count(int sp_left_count) {
		Sp_left_count = sp_left_count;
	}
	
}