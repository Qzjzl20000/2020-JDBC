package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.model.BeanUserJD;
import src.cn.edu.zucc.waimai.model.BeanUserYHQ;
import src.cn.edu.zucc.waimai.itf.IUserSj;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSjMJ;
import src.cn.edu.zucc.waimai.model.BeanSjYHQ;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class UserSjManager implements IUserSj {
	@Override
	public List<BeanUserJD> loadMJ(BeanUser user,BeanSj sj)throws BaseException{
		List<BeanUserJD> result=new ArrayList<BeanUserJD>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_youhuiquan_jding where user_id=? and sj_id=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUserJD p=new BeanUserJD();
				p.setUser_id(rs.getInt(1));
				p.setYouhuiquan_id(rs.getInt(2));
				p.setSj_id(rs.getInt(3));
				p.setJidan_least_count(rs.getInt(4));
				p.setOrder_count(rs.getInt(5));
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
	public List<BeanUserYHQ> loadYHQ(BeanUser user,BeanSj sj)throws BaseException{
		List<BeanUserYHQ> result=new ArrayList<BeanUserYHQ>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_youhuiquan_get where user_id=? and sj_id=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUserYHQ p=new BeanUserYHQ();
				p.setUser_id(rs.getInt(1));
				p.setYouhuiquan_id(rs.getInt(2));
				p.setSj_id(rs.getInt(3));
				p.setYouhui_money(rs.getFloat(4));
				p.setYouhuiquan_count(rs.getInt(5));
				p.setYouhuiquan_end_time(rs.getTimestamp(6));
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
	public List<BeanSjMJ> loadAllMJ(BeanSj sj)throws BaseException{
		List<BeanSjMJ> result=new ArrayList<BeanSjMJ>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_manjian where sj_id=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanSjMJ p=new BeanSjMJ();
				p.setSj_id(rs.getInt(1));
				p.setMj_id(rs.getInt(2));
				p.setMj_top_money(rs.getFloat(3));
				p.setMj_discount_money(rs.getFloat(4));
				p.setIf_add_youhuiquan(rs.getInt(5));
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
	public List<BeanSjYHQ> loadAllYHQ(BeanSj sj)throws BaseException{
		List<BeanSjYHQ> result=new ArrayList<BeanSjYHQ>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_youhuiquan where sj_id=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanSjYHQ p=new BeanSjYHQ();
				p.setSj_id(rs.getInt(1));
				p.setYouhuiquan_id(rs.getInt(2));
				p.setYouhui_money(rs.getFloat(3));
				p.setJidan_least_count(rs.getInt(4));
				p.setYouhuiquan_begin_time(rs.getTimestamp(5));
				p.setYouhuiquan_end_time(rs.getTimestamp(6));
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
	public BeanSp addSP(BeanSjFL sjfl ,String spname,String price,String left) throws BaseException{
		if(spname.equals("")) {
			throw new BusinessException("商品名不能为空！");
		}
		if(price.equals("")) {
			throw new BusinessException("价格不能为空！");
		}
		if(left.equals("")) {
			throw new BusinessException("余量不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select sp_name from sp_data where sj_id=? and sp_belong_leibie_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,sjfl.getSj_id());
			pst.setInt(2, sjfl.getLeibie_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(spname)) {
					rs.close();
					pst.close();
					throw new BusinessException("该商家该分栏已存在此商品名！");
				}
			}
			rs.close();
			pst.close();
			sql="insert into sp_data(sj_id,sp_belong_leibie_id,sp_name,sp_price,sp_left_count) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, sjfl.getSj_id());
			pst.setInt(2, sjfl.getLeibie_id());
			pst.setString(3, spname);
			pst.setFloat(4, Float.parseFloat(price));
			pst.setInt(5,Integer.parseInt(left));
			pst.execute();
			pst.close();
			sql="update sp_leibie set sp_count=sp_count+1 where leibie_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, sjfl.getLeibie_id());
			pst.execute();
			pst.close();
			BeanSp bu=new BeanSp();
			bu.setFl_id(sjfl.getLeibie_id());
			bu.setSj_id(sjfl.getSj_id());
			bu.setSp_left_count(Integer.parseInt(left));
			bu.setSp_name(spname);
			bu.setSp_price(Float.parseFloat(price));
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
	public BeanSjFL addSJFL(BeanSj sj ,String flname) throws BaseException{
		if(flname.equals("")) {
			throw new BusinessException("分栏名不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select leibie_name from sp_leibie where sj_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,sj.getSj_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(flname)) {
					rs.close();
					pst.close();
					throw new BusinessException("分栏名已存在！");
				}
			}
			rs.close();
			pst.close();
			sql="insert into sp_leibie(sj_id,leibie_name,sp_count) values(?,?,0)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, sj.getSj_id());
			pst.setString(2, flname);
			pst.execute();
			pst.close();
			BeanSjFL bu=new BeanSjFL();
			bu.setLeibie_name(flname);
			bu.setLeibie_sp_count(0);
			bu.setSj_id(sj.getSj_id());
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
	public BeanSj reg(String sjname,String xinji) throws BaseException{
		if(sjname.equals("")) {
			throw new BusinessException("用户名不能为空！");
		}
		if(xinji.equals("")) {
			throw new BusinessException("星级不能为空！");
		}
	
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int ixinji=Integer.parseInt(xinji);
			String sql="select * from sj_data where sj_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,sjname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())throw new BusinessException("商家名已存在！");
			rs.close();
			pst.close();
			sql="insert into sj_data(sj_name,sj_xinji) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, sjname);
			pst.setInt(2, ixinji);
			pst.execute();
			pst.close();
			BeanSj bu=new BeanSj();
			bu.setAvg_consume(0);
			bu.setSj_name(sjname);
			bu.setSj_xinji(ixinji);
			bu.setTotal_consume(0);
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
	@Override
	public List<BeanSp> loadAllSp(BeanSjFL sjfl)throws BaseException{
		List<BeanSp> result=new ArrayList<BeanSp>();
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sp_data"
					+ " where sj_id = ? and sp_belong_leibie_id=? order by sp_id";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1,sjfl.getSj_id());
			pst.setInt(2, sjfl.getLeibie_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanSp p=new BeanSp();
				p.setSj_id(rs.getInt(1));
				p.setSp_id(rs.getInt(2));
				p.setFl_id(rs.getInt(3));
				p.setSp_name(rs.getString(4));
				p.setSp_price(rs.getFloat(5));
				p.setSp_left_count(rs.getInt(6));
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
