package src.cn.edu.zucc.waimai.model;


public class BeanSjFL {
	public static final String[] leibietableTitles={"分栏编号","分栏项目","商品个数"};
	public static BeanSjFL currentLoginUser;
	private int leibie_id;
	private String leibie_name;
	private int leibie_sp_count;
	private int sj_id;
	public String getCell(int col){
		if(col==0) return ""+this.getLeibie_id();
		else if(col==1) return ""+this.getLeibie_name();
		else if(col==2) return ""+this.getLeibie_sp_count();
		else return "";
	}
	
	public int getSj_id() {
		return sj_id;
	}

	public void setSj_id(int sj_id) {
		this.sj_id = sj_id;
	}

	public int getLeibie_id() {
		return leibie_id;
	}

	public void setLeibie_id(int leibie_id) {
		this.leibie_id = leibie_id;
	}

	public String getLeibie_name() {
		return leibie_name;
	}

	public void setLeibie_name(String leibie_name) {
		this.leibie_name = leibie_name;
	}

	public int getLeibie_sp_count() {
		return leibie_sp_count;
	}

	public void setLeibie_sp_count(int leibie_sp_count) {
		this.leibie_sp_count = leibie_sp_count;
	}

	
}
