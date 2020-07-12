package src.cn.edu.zucc.waimai.itf;

import java.util.List;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface IUserSj {
	/**
	 * 新建商品：
	 * 要求商品名不能重复，不能为空
	 * 如果新建失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanSp addSP(BeanSjFL sjfl ,String spname,String price,String left) throws BaseException;
	/**
	 * 新建商家分栏：
	 * 要求分栏名不能重复，不能为空
	 * 如果新建失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanSjFL addSJFL(BeanSj sj ,String flname) throws BaseException;
	/**
	 * 新建商家：
	 * 要求商家名不能重复，不能为空
	 * 如果新建失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanSj reg(String sjname,String xinji) throws BaseException;
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
	public List<BeanSp> loadAllSp(BeanSjFL sjfl)throws BaseException;
}