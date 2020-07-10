package src.cn.edu.zucc.waimai.itf;

import java.util.List;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface IUserSj {
	/**
	 * 提取当前所有商家信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanSj> loadAll()throws BaseException;
	/**
	 * 提取当前所有商家\分栏类别信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanSjFL> loadAlllB(BeanSj sj)throws BaseException;
	/**
	 * 提取当前所有商家\分栏类别\商品信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanSp> loadAllSp(BeanSj sj,BeanSjFL sjfl)throws BaseException;
}