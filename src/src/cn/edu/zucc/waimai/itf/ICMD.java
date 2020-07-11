package src.cn.edu.zucc.waimai.itf;


import java.util.List;

import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface ICMD {
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