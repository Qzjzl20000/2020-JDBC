package src.cn.edu.zucc.waimai.itf;

import java.util.List;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface IUserSj {
	/**
	 * 提取当前所有商家信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanSj> loadAll()throws BaseException;
}