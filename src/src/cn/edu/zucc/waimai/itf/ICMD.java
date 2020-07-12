package src.cn.edu.zucc.waimai.itf;


import java.util.List;

import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.model.BeanQs;
import src.cn.edu.zucc.waimai.model.BeanQsbill;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface ICMD {
	/**
	 * 修改商家
	 * 仅能修改商家名和星级
	 */
	public void modifySj(BeanSj sj,String name,String xinji) throws BaseException;
	/**
	 * 删除商家
	 * 需要无分栏
	 */
	public void deleteSj(BeanSj sj) throws BaseException;
	/**
	 * 提取当前所有用户地址信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanUserAdd> loadAllYHadd(BeanUser user)throws BaseException;
	/**
	 * 注册：
	 * 要求用户名不能重复，不能为空
	 * 两次输入的密码必须一致，密码不能为空
	 * 如果注册失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 * @throws BaseException
	 */
	public BeanUser reg(String username,int usersex, String pwd,String pwd2,String userphonenum,
			String usere_mail,String usercity) throws BaseException;
	/**
	 * 提取当前所有用户信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanUser> loadAllYH()throws BaseException;
	/**
	 * 提取当前所有骑手信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanQs> loadAllQS()throws BaseException;
	/**
	 * 提取当前所有骑手账单信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanQsbill> loadAllQSbill(BeanQs qs)throws BaseException;
	/**
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanCMD login(String username,String pwd)throws BaseException;
	/**
	 * 提取当前所有管理员信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanCMD> loadAll()throws BaseException;
	/**
	 * 新增
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanCMD reg(String cmdusername,String cmdpwd,String username,String pwd)throws BaseException;
	/**
	 * 修改
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanCMD modify(String cmdusername,String cmdpwd,String username,String pwd)throws BaseException;
}