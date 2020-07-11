package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.cn.edu.zucc.waimai.itf.ICMD;
import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class CMDManager implements ICMD {
	@Override
	public BeanCMD login(String username,String pwd)throws BaseException{
		if(username.equals("")) {
			throw new BusinessException("用户名为空！");
		}
		if(pwd.equals("")) {
			throw new BusinessException("密码不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cmd_id,cmd_name,cmd_pwd from cmd_data where cmd_name=? and cmd_pwd=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			java.sql.ResultSet rs= pst.executeQuery();
			if(rs.next()) {
				BeanCMD bu=new BeanCMD();
				bu.setCMD_id(rs.getInt(1));//获取用户编号
				bu.setCMD_name(username);
				bu.setCMD_pwd(pwd);
				pst.close();
				rs.close();
				return bu;
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
	public List<BeanCMD> loadAll()throws BaseException{
		List<BeanCMD> result=new ArrayList<BeanCMD>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from cmd_data";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCMD p=new BeanCMD();
				p.setCMD_id(rs.getInt(1));
				p.setCMD_name(rs.getString(2));
				p.setCMD_pwd(rs.getString(3));
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public BeanCMD reg(String cmdusername,String cmdpwd,String username,String pwd)throws BaseException{
		if(cmdusername.equals("")) {
			throw new BusinessException("管理员名为空！");
		}
		if(cmdpwd.equals("")) {
			throw new BusinessException("管理员密码不能为空！");
		}
		if(username.equals("")) {
			throw new BusinessException("新管理员名为空！");
		}
		if(pwd.equals("")) {
			throw new BusinessException("新管理员密码不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from cmd_data where cmd_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("管理员姓名已存在！");
			}
			rs.close();
			pst.close();
			sql="select cmd_pwd from cmd_data where cmd_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cmdusername);
			rs=pst.executeQuery();
			if(rs.next()) {
				if(!cmdpwd.equals(rs.getString(1))) {
					rs.close();
					pst.close();
					throw new BusinessException("管理员密码认证失败！");
				}
			}else {
				rs.close();
				pst.close();
				throw new BusinessException("管理员账号不存在");
			}
			rs.close();
			pst.close();
			
			sql="insert into cmd_data(cmd_name,cmd_pwd) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			pst.execute();
			pst.close();
			BeanCMD bu=new BeanCMD();
			bu.setCMD_name(username);
			bu.setCMD_pwd(pwd);
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
	public BeanCMD modify(String cmdusername,String cmdpwd,String username,String pwd)throws BaseException{
		if(cmdusername.equals("")) {
			throw new BusinessException("管理员名为空！");
		}
		if(cmdpwd.equals("")) {
			throw new BusinessException("管理员密码不能为空！");
		}
		if(username.equals("")) {
			throw new BusinessException("新管理员名为空！");
		}
		if(pwd.equals("")) {
			throw new BusinessException("新管理员密码不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from cmd_data where cmd_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()&&(!rs.getString(2).equals(cmdusername))) {
				rs.close();
				pst.close();
				throw new BusinessException("管理员姓名已存在！");
			}
			rs.close();
			pst.close();
			sql="select cmd_pwd from cmd_data where cmd_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cmdusername);
			rs=pst.executeQuery();
			if(rs.next()) {
				if(!cmdpwd.equals(rs.getString(1))) {
					rs.close();
					pst.close();
					throw new BusinessException("管理员密码认证失败！");
				}
			}else {
				rs.close();
				pst.close();
				throw new BusinessException("管理员账号不存在");
			}
			rs.close();
			pst.close();
			
			sql="update cmd_data set cmd_name=?,cmd_pwd=? where cmd_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			pst.setString(3, cmdusername);
			pst.execute();
			pst.close();
			BeanCMD bu=new BeanCMD();
			bu.setCMD_name(username);
			bu.setCMD_pwd(pwd);
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