package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.cn.edu.zucc.waimai.itf.IUserSj;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class UserSjManager implements IUserSj {

	@Override
	public List<BeanSj> loadAll()throws BaseException{
		List<BeanSj> result=new ArrayList<BeanSj>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_data";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanSj p=new BeanSj();
				p.setSj_id(rs.getInt(1));
				p.setSj_name(rs.getString(2));
				p.setSj_xinji(rs.getInt(3));
				p.setAvg_consume(rs.getFloat(4));
				p.setTotal_consume(rs.getInt(5));
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
	public List<BeanSjFL> loadAlllB(BeanSj sj)throws BaseException{
		List<BeanSjFL> result=new ArrayList<BeanSjFL>();
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sp_leibie"
					+ " where sj_id = ? order by leibie_id";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1,sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanSjFL p=new BeanSjFL();
				p.setSj_id(sj.getSj_id());
				p.setLeibie_id(rs.getInt(2));
				p.setLeibie_name(rs.getString(3));
				p.setLeibie_sp_count(rs.getInt(4));
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

}
