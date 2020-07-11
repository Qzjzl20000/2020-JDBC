package src.cn.edu.zucc.waimai.itf;

import java.util.List;

import src.cn.edu.zucc.waimai.model.BeanOrder;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;

public interface IUserManager {
	/**
	 * 提取当前所有商家信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanUser> loadAll(String name)throws BaseException;
	/**
	 * 提取当前所有用户地址信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanOrder> loadAllYHOrder()throws BaseException;
	/**
	 * 提取当前所有用户地址信息
	 * @return
	 * @throws BaseException
	 */
	public List<BeanUserAdd> loadAllYHadd()throws BaseException;
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
	 * 登陆
	 * 1、如果用户不存在或者密码错误，抛出一个异常
	 * 2、如果认证成功，则返回当前用户信息
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws BaseException
	 */
	public BeanUser login(String username,String pwd)throws BaseException;
	/**
	 * 修改密码
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPwd  原密码
	 * @param newPwd  新密码
	 * @param newPwd2 重复输入的新密码
	 */
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
	/**
	 * 修改电话
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPnum  原号码
	 * @param newPnum  新号码
	 */
	public void changePhonenum(BeanUser user, String oldPnum,String newPnum)throws BaseException;
	/**
	 * 修改邮箱
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPnum  原邮箱
	 * @param newPnum  新邮箱
	 */
	public void changeEmail(BeanUser user, String oldEmail,String newEmail)throws BaseException;
	/**
	 * 修改城市
	 * 如果没有成功修改，则抛出异常
	 * @param user    当前用户
	 * @param oldPnum  原城市
	 * @param newPnum  新城市
	 */
	public void changeCity(BeanUser user, String oldCity,String newCity)throws BaseException;
	/**
	 * 添加：
	 * 要求不能为空
	 * 如果注册失败，则抛出异常
	 * @param userid
	 * @param pwd  密码
	 * @param pwd2 重复输入的密码
	 * @return
	 */
	public BeanUserAdd regAdd(BeanUser user,String province,String city, String area,
			String adddetail,String name,String phoneNum) throws BaseException;
}
