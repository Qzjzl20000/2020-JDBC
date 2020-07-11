package src.cn.edu.zucc.waimai.model;


public class BeanCMD {
	public static BeanCMD currentLoginCMD;
	public static final String[] CMDtableTitles={"管理员id","管理员名","管理员密码"};
	private int CMD_id;
	private String CMD_name;
	private String CMD_pwd;
	
	public String getCell(int col){
		if(col==0) return ""+this.getCMD_id();
		else if(col==1) return ""+this.getCMD_name();
		else if(col==2) return ""+this.getCMD_pwd();
		else return "";
	}

	public int getCMD_id() {
		return CMD_id;
	}

	public void setCMD_id(int cMD_id) {
		CMD_id = cMD_id;
	}

	public String getCMD_name() {
		return CMD_name;
	}

	public void setCMD_name(String cMD_name) {
		CMD_name = cMD_name;
	}

	public String getCMD_pwd() {
		return CMD_pwd;
	}

	public void setCMD_pwd(String CMD_pwd) {
		this.CMD_pwd = CMD_pwd;
	}
	
	
}
