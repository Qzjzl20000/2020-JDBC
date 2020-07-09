package src.cn.edu.zucc.waimai.model;


public class BeanSj {
	public static final String[] SjtableTitles={"商家id","商家名","商家星级","人均消费","总销量"};

	public static BeanSj currentLoginUser;
	private int Sj_id;
	private String Sj_name;
	private int Sj_xinji;
	private float avg_consume;
	private int total_consume;
	
	public String getCell(int col){
		if(col==0) return ""+this.getSj_id();
		else if(col==1) return ""+this.getSj_name();
		else if(col==2) return ""+this.getSj_xinji();
		else if(col==3) return ""+this.getAvg_consume()+"元";
		else if(col==4) return ""+this.getTotal_consume()+"单";
		else return "";
	}
	
	public int getSj_id() {
		return Sj_id;
	}

	public void setSj_id(int sj_id) {
		Sj_id = sj_id;
	}

	public String getSj_name() {
		return Sj_name;
	}

	public void setSj_name(String sj_name) {
		Sj_name = sj_name;
	}

	public int getSj_xinji() {
		return Sj_xinji;
	}

	public void setSj_xinji(int sj_xinji) {
		Sj_xinji = sj_xinji;
	}

	public float getAvg_consume() {
		return avg_consume;
	}

	public void setAvg_consume(float avg_consume) {
		this.avg_consume = avg_consume;
	}

	public float getTotal_consume() {
		return total_consume;
	}

	public void setTotal_consume(int total_consume) {
		this.total_consume = total_consume;
	}
	
}
