package src.cn.edu.zucc.waimai;

import src.cn.edu.zucc.waimai.comtrol.example.UserManager;
import src.cn.edu.zucc.waimai.comtrol.example.UserSjManager;
import src.cn.edu.zucc.waimai.comtrol.example.CMDManager;
import src.cn.edu.zucc.waimai.itf.ICMD;
import src.cn.edu.zucc.waimai.itf.IUserManager;
import src.cn.edu.zucc.waimai.itf.IUserSj;

public class WaiMaiUtil {
	
	public static IUserManager userManager=new UserManager();//需要换成自行设计的实现类
	public static IUserSj userSjManager=new UserSjManager();
	public static ICMD CMDManager=new CMDManager();
}
