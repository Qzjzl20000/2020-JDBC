package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import src.cn.edu.zucc.waimai.itf.IUserManager;
import src.cn.edu.zucc.waimai.model.BeanOrder;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class UserManager implements IUserManager {
	@Override
	public List<BeanUser> loadAll()throws BaseException{
		List<BeanUser> result=new ArrayList<BeanUser>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_sex,user_pwd,user_phonenum,user_email,user_city,user_register_time,user_vip_end_time"
					+ " from user_data where user_id=? ";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUser p=new BeanUser();
				p.setUser_id(rs.getInt(1));
				p.setUser_name(rs.getString(2));
				p.setUser_sex(rs.getInt(3));
				p.setUser_pwd(rs.getString(4));
				p.setUser_phonenum(rs.getString(5));
				p.setUser_email(rs.getString(6));
				p.setUser_city(rs.getString(7));
				p.setUser_register_time(rs.getTimestamp(8));
				p.setUser_vip_end_time(rs.getTimestamp(9));
				result.add(p);
			}
			rs.close();
			pst.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void BuyVIP(String year)throws BaseException{
		java.sql.Connection conn =null;
		Timestamp timestamp = null;
		try {
			int Intyear=Integer.parseInt(year);
			if(BeanUser.currentLoginUser.getUser_vip_end_time().before(Timestamp.valueOf("2010-01-01 00:00:00"))) {
				conn=DBUtil.getConnection();
				String sql="select ADDDATE(now(),interval ? year)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, Intyear);
				java.sql.ResultSet rs=pst.executeQuery();
				while(rs.next())
					 timestamp=rs.getTimestamp(1);
				rs.close();
				pst.close();
				sql="update user_data set user_vip_end_time=? where user_id=?";
				pst=conn.prepareStatement(sql);
				pst.setTimestamp(1, timestamp);
				pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
				pst.execute();
				pst.close();
			}else {
				conn=DBUtil.getConnection();
				String sql="select ADDDATE(?,interval ? year)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setTimestamp(1, BeanUser.currentLoginUser.getUser_vip_end_time());
				pst.setInt(2, Intyear);
				java.sql.ResultSet rs=pst.executeQuery();
				while(rs.next())
					 timestamp=rs.getTimestamp(1);
				rs.close();
				pst.close();
				sql="update user_data set user_vip_end_time=? where user_id=?";
				pst=conn.prepareStatement(sql);
				pst.setTimestamp(1, timestamp);
				pst.setInt(2, BeanUser.currentLoginUser.getUser_id());
				pst.execute();
				pst.close();
			}
				
				
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public List<BeanOrder> loadAllYHOrder()throws BaseException{
		List<BeanOrder> result=new ArrayList<BeanOrder>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select *"
					+ " from order_data where user_id=? order by order_id";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrder p=new BeanOrder();
				p.setOrder_id(rs.getInt(1));
				p.setSj_id(rs.getInt(2));
				p.setUser_id(rs.getInt(3));
				p.setQs_id(rs.getInt(4));
				p.setOrder_origin_money(rs.getFloat(5));
				p.setOrder_final_money(rs.getFloat(6));
				p.setMj_id(rs.getInt(7));
				p.setYouhuiquan_id(rs.getInt(8));
				p.setOrder_set_time(rs.getTimestamp(9));
				p.setOrder_set_arrive_time(rs.getTimestamp(10));
				p.setUser_address_id(rs.getInt(11));
				p.setOrder_state(rs.getString(12));
				result.add(p);
			}
			rs.close();
			pst.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public List<BeanUserAdd> loadAllYHadd()throws BaseException{
		List<BeanUserAdd> result=new ArrayList<BeanUserAdd>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_province,user_city,user_area,user_address_detail,user_ad_name,user_ad_phonenum"
					+ " from user_address where user_id=? order by user_address_id";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUserAdd p=new BeanUserAdd();
				p.setUser_id(rs.getInt(1));
				p.setUser_province(rs.getString(2));
				p.setUser_city(rs.getString(3));
				p.setUser_area(rs.getString(4));
				p.setUser_add_detail(rs.getString(5));
				p.setUser_add_name(rs.getString(6));
				p.setUser_add_phonenum(rs.getString(7));
				result.add(p);
			}
			rs.close();
			pst.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public BeanUser reg(String username,int usersex, String pwd,String pwd2,String userphonenum,
			String usere_mail,String usercity) throws BaseException{
		if(username.equals("")) {
			throw new BusinessException("用户名不能为空！");
		}
		if(usersex!=0 && usersex!=1) {
			throw new BusinessException("性别请输入0（女性）或1（男性）！");
		}
		if(pwd.equals("")) {
			throw new BusinessException("密码不能为空！");
		}
		if(pwd2.equals("")) {
			throw new BusinessException("请第二次输入密码！");
		}
		if(!(pwd.equals(pwd2))) {
			throw new BusinessException("两次密码不一致！");
		}
		if(userphonenum.equals("")) {
			throw new BusinessException("手机号码不能为空！");
		}
		if(usere_mail.equals("")) {
			throw new BusinessException("邮箱不能为空！");
		}
		if(usercity.equals("")) {
			throw new BusinessException("所在城市不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_data where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("姓名已存在！");
			rs.close();
			pst.close();
			sql="insert into user_data(user_name,user_sex,user_pwd,user_phonenum,"
					+ "user_email,user_city,user_register_time) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setInt(2, usersex);
			pst.setString(3, pwd);
			pst.setString(4, userphonenum);
			pst.setString(5, usere_mail);
			pst.setString(6, usercity);
			pst.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.execute();
			pst.close();
			BeanUser bu=new BeanUser();
			bu.setUser_name(username);
			bu.setUser_sex(usersex);
			bu.setUser_pwd(pwd);
			bu.setUser_phonenum(userphonenum);
			bu.setUser_email(usere_mail);
			bu.setUser_city(usercity);
			bu.setUser_register_time(new java.sql.Timestamp(System.currentTimeMillis()));
			bu.setUser_vip_end_time(null);
			return bu;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if(conn!=null)
				try {
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	
	@Override
	public BeanUser login(String username, String pwd) throws BaseException {
		if(username.equals("")) {
			throw new BusinessException("用户名为空！");
		}
		if(pwd.equals("")) {
			throw new BusinessException("密码不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_sex,user_pwd,user_phonenum,user_email,user_city,user_register_time,user_vip_end_time"
					+ " from user_data where user_name=? and user_pwd=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			java.sql.ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				BeanUser p=new BeanUser();
				p.setUser_id(rs.getInt(1));
				p.setUser_name(rs.getString(2));
				p.setUser_sex(rs.getInt(3));
				p.setUser_pwd(rs.getString(4));
				p.setUser_phonenum(rs.getString(5));
				p.setUser_email(rs.getString(6));
				p.setUser_city(rs.getString(7));
				p.setUser_register_time(rs.getTimestamp(8));
				p.setUser_vip_end_time(rs.getTimestamp(9));
				pst.close();
				rs.close();
				return p;
			}else {
				throw new BusinessException("账号或密码错误");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		if(oldPwd.equals("")) {
			throw new BusinessException("旧密码不能为空！");
		}
		if(newPwd.equals("")) {
			throw new BusinessException("新密码不能为空！");
		}
		if(newPwd2.equals("")) {
			throw new BusinessException("第二次输入新密码不能为空！");
		}
		if(!newPwd.equals(newPwd2)) {
			throw new BusinessException("新密码输入不一致！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_name,user_pwd from user_data where user_name = ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!oldPwd.equals(BeanUser.currentLoginUser.getUser_pwd()))
					throw new BusinessException("旧密码错误！");
				sql="update user_data set user_pwd=? where user_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPwd);
				pst.setString(2, BeanUser.currentLoginUser.getUser_name());
				pst.execute();
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void changePhonenum(BeanUser user, String oldPnum, String newPnum) throws BaseException {
		if(oldPnum.equals("")) {
			throw new BusinessException("旧手机不能为空！");
		}
		if(newPnum.equals("")) {
			throw new BusinessException("新手机不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_phonenum from user_data where user_name = ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!oldPnum.equals(rs.getString(1)))
					throw new BusinessException("旧手机号认证错误！");
				sql="update user_data set user_phonenum=? where user_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPnum);
				pst.setString(2, BeanUser.currentLoginUser.getUser_name());
				pst.execute();
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void changeEmail(BeanUser user, String oldEmail,String newEmail)throws BaseException {
		if(oldEmail.equals("")) {
			throw new BusinessException("旧邮箱不能为空！");
		}
		if(newEmail.equals("")) {
			throw new BusinessException("新邮箱不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_email from user_data where user_name = ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!oldEmail.equals(rs.getString(1)))
					throw new BusinessException("旧邮箱认证错误！");
				sql="update user_data set user_email=? where user_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newEmail);
				pst.setString(2, BeanUser.currentLoginUser.getUser_name());
				pst.execute();
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void changeCity(BeanUser user, String oldCity,String newCity)throws BaseException {
		if(oldCity.equals("")) {
			throw new BusinessException("旧城市不能为空！");
		}
		if(newCity.equals("")) {
			throw new BusinessException("新城市不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_city from user_data where user_name = ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, BeanUser.currentLoginUser.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!oldCity.equals(rs.getString(1)))
					throw new BusinessException("旧城市输入错误！");
				sql="update user_data set user_city=? where user_name=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newCity);
				pst.setString(2, BeanUser.currentLoginUser.getUser_name());
				pst.execute();
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public BeanUserAdd regAdd(BeanUser user, String province,String city, String area,String adddetail,String name,
			String phoneNum) throws BaseException{
		if(province.equals("")) {
			throw new BusinessException("省不能为空！");
		}
		if(city.equals("")) {
			throw new BusinessException("市不能为空！");
		}
		if(area.equals("")) {
			throw new BusinessException("区不能为空！");
		}
		if(adddetail.equals("")) {
			throw new BusinessException("地址详情不能为空！");
		}
		if(name.equals("")) {
			throw new BusinessException("联系人姓名不能为空！");
		}
		if(phoneNum.equals("")) {
			throw new BusinessException("联系电话不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into user_address(user_id,user_province,user_city,user_area,user_address_detail,user_ad_name,user_ad_phonenum) values(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, BeanUser.currentLoginUser.getUser_id());
			pst.setString(2, province);
			pst.setString(3, city);
			pst.setString(4, area);
			pst.setString(5, adddetail);
			pst.setString(6, name);
			pst.setString(7, phoneNum);
			pst.execute();
			pst.close();
			BeanUserAdd bu=new BeanUserAdd();
			bu.setUser_add_id(BeanUser.currentLoginUser.getUser_id());
			bu.setUser_province(province);
			bu.setUser_city(city);
			bu.setUser_area(area);
			bu.setUser_add_detail(adddetail);
			bu.setUser_add_name(name);
			bu.setUser_add_phonenum(phoneNum);
			return bu;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			if(conn!=null)
				try {
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
