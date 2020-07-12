package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import src.cn.edu.zucc.waimai.itf.ICMD;
import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.model.BeanQs;
import src.cn.edu.zucc.waimai.model.BeanQsbill;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSjMJ;
import src.cn.edu.zucc.waimai.model.BeanSjYHQ;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class CMDManager implements ICMD {
	@Override
	public void modifyQS(BeanQs qs,String name,String grade) throws BaseException{
		if(name.equals("")) {
			throw new BusinessException("骑手姓名不能为空！");
		}
		if(grade.equals("")) {
			throw new BusinessException("骑手等级不能为空！");
		}
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from qs_data where qs_id="+qs.getQs_id();//查找骑手
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				
				sql="update qs_data set qs_name=?,qs_grade=? where qs_id= ? ";//更新操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, grade);
				pst.setInt(3, qs.getQs_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该骑手已不存在");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteQS(BeanQs qs) throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from qs_data where qs_id="+qs.getQs_id();//检查是否存在商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="delete from qs_data where qs_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, qs.getQs_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该骑手已经不存在");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void regQS(String name,String grade)throws BaseException{
		if(name.equals("")) {
			throw new BusinessException("骑手姓名不能为空！");
		}
		if(grade.equals("")) {
			throw new BusinessException("骑手等级不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into qs_data(qs_name,qs_join_date,"
					+ "qs_grade) values(?,now(),?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, grade);
			pst.execute();
			pst.close();
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
	public void modifyMJ(BeanSjMJ sjmj,String top,String count,String ifmj) throws BaseException{
		if(top.equals("")) {
			throw new BusinessException("满减金额不能为空！");
		}
		if(count.equals("")) {
			throw new BusinessException("优惠金额不能为空！");
		}
		if(ifmj.equals("")) {
			throw new BusinessException("是否与优惠券叠加不能为空！");
		}
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_manjian where mj_id="+sjmj.getMj_id();//查找满减
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				
				sql="update sj_manjian set mj_top_money=?,mj_discount_money=?,if_add_youhuiquan=? where mj_id= ? ";//更新操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setFloat(1, Float.parseFloat(top));
				pst.setFloat(2, Float.parseFloat(count));
				pst.setInt(3,Integer.parseInt(ifmj));
				pst.setInt(4,sjmj.getMj_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该商家分栏已经不存在该商品了");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteMJ(BeanSjMJ sjmj) throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_manjian where mj_id="+sjmj.getMj_id();//检查是否存在商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="delete from sj_manjian where mj_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, sjmj.getMj_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("已经不存在该满减政策");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void regMJ(BeanSj sj,String top,String count,String ifmj)throws BaseException{
		if(top.equals("")) {
			throw new BusinessException("满减金额不能为空！");
		}
		if(count.equals("")) {
			throw new BusinessException("优惠金额不能为空！");
		}
		if(ifmj.equals("")) {
			throw new BusinessException("是否与优惠券叠加不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into sj_manjian(sj_id,mj_top_money,"
					+ "mj_discount_money,if_add_youhuiquan) values(?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst=conn.prepareStatement(sql);
			pst.setInt(1, sj.getSj_id());
			pst.setFloat(2, Float.parseFloat(top));
			pst.setFloat(3, Float.parseFloat(count));
			pst.setInt(4, Integer.parseInt(ifmj));
			pst.execute();
			pst.close();
			
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
	public void modifyYHQ(BeanSjYHQ sjyhq,String youhui_money,String jidan,String days) throws BaseException{
		if(youhui_money.equals("")) {
			throw new BusinessException("优惠金额不能为空！");
		}
		if(jidan.equals("")) {
			throw new BusinessException("集单要求不能为空！");
		}
		if(days.equals("")) {
			throw new BusinessException("增加天数不能为空！");
		}
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_youhuiquan where youhuiquan_id="+sjyhq.getYouhuiquan_id();//查找优惠券
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				
				Timestamp longend =new Timestamp(0);
				sql="select ADDDATE(?,interval ? day)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setTimestamp(1, sjyhq.getYouhuiquan_end_time());
				pst.setInt(2,Integer.parseInt(days));
				rs=pst.executeQuery();
				while(rs.next())
					longend=rs.getTimestamp(1);
				pst.close();
				rs.close();
				
				sql="update sj_youhuiquan set youhui_money=?,jidan_least_count=?,youhuiquan_end_time=? where youhuiquan_id= ? ";//更新操作
				pst=conn.prepareStatement(sql);
				pst.setFloat(1, Float.parseFloat(youhui_money));
				pst.setInt(2, Integer.parseInt(jidan));
				pst.setTimestamp(3, longend);
				pst.setInt(4, sjyhq.getYouhuiquan_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该商家分栏已经不存在该商品了");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteYHQ(BeanSjYHQ sjyhq) throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sj_youhuiquan where youhuiquan_id="+sjyhq.getYouhuiquan_id();//检查是否存在商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="delete from sj_youhuiquan where youhuiquan_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, sjyhq.getYouhuiquan_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("已经不存在该优惠券");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void regYHQ(BeanSj sj,String youhui_money,String jidan,String days)throws BaseException{
		if(youhui_money.equals("")) {
			throw new BusinessException("优惠金额不能为空！");
		}
		if(jidan.equals("")) {
			throw new BusinessException("集单要求不能为空！");
		}
		if(days.equals("")) {
			throw new BusinessException("活动天数不能为空！");
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Timestamp begin=new Timestamp(0);
			Timestamp end=new Timestamp(0);
			String sql="select ADDDATE(now(),interval 0 day)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
				begin=rs.getTimestamp(1);
			pst.close();
			rs.close();
			
			sql="select ADDDATE(now(),interval ? day)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Integer.parseInt(days));
			rs=pst.executeQuery();
			while(rs.next())
				end=rs.getTimestamp(1);
			pst.close();
			rs.close();
			
			sql="insert into sj_youhuiquan(sj_id,youhui_money,"
					+ "jidan_least_count,youhuiquan_begin_time,youhuiquan_end_time) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, sj.getSj_id());
			pst.setFloat(2, Float.parseFloat(youhui_money));
			pst.setInt(3, Integer.parseInt(jidan));
			pst.setTimestamp(4, begin);
			pst.setTimestamp(5, end);
			pst.execute();
			pst.close();
			
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
	public void modifySP(BeanSp sp,String name,String price,String left) throws BaseException{
		java.sql.Connection conn =null;
		try {
			int sp_id=sp.getSp_id();
			conn=DBUtil.getConnection();
			String sql="select * from sp_data where sp_id="+sp_id;//查找商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="update sp_data set sp_name=?,sp_price=?,sp_left_count=? where sp_id= ? ";//更新操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setFloat(2, Float.parseFloat(price));
				pst.setInt(3, Integer.parseInt(left));
				pst.setInt(4, sp_id);
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该商家分栏已经不存在该商品了");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteSP(BeanSp sp) throws BaseException{
		java.sql.Connection conn =null;
		try {
			int sp_id=sp.getSp_id();
			int fl_id=sp.getFl_id();
			conn=DBUtil.getConnection();
			String sql="select * from sp_data where sp_id="+sp_id;//检查是否存在商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="delete from sp_data where sp_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, sp_id);
				pst.execute();
				pst.close();
				sql="update sp_leibie set sp_count=sp_count-1 where leibie_id=?";//
				pst=conn.prepareStatement(sql);
				pst.setInt(1, fl_id);
				pst.execute();
				pst.close();
				
			}else {
				rs.close();
				st.close();
				throw new BusinessException("已经不存在该商品");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void modifySjFL(BeanSjFL sjfl,String name) throws BaseException{
		java.sql.Connection conn =null;
		try {
			int sjfl_id=sjfl.getLeibie_id();
			conn=DBUtil.getConnection();
			String sql="select * from sp_leibie where leibie_id="+sjfl_id;//查找商家类别
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="update sp_leibie set leibie_name=? where leibie_id= ? ";//更新操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, sjfl_id);
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("该商家已经不存在该分栏");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteSjFL(BeanSjFL sjfl) throws BaseException{
		java.sql.Connection conn =null;
		try {
			int sjfl_id=sjfl.getLeibie_id();
			conn=DBUtil.getConnection();
			String sql="select * from sp_data where sp_belong_leibie_id="+sjfl_id;//检查是否存在商品
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				rs.close();
				st.close();
				throw new BusinessException("该商家分栏中仍存在商品，不能直接删除");
			}
			rs.close();
			
			sql="select * from sp_leibie where leibie_id="+sjfl_id;//查找商家分栏
			rs=st.executeQuery(sql);
			if(rs.next()&&sjfl.getLeibie_sp_count()==0) {
				rs.close();
				st.close();
				sql="delete from sp_leibie where leibie_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, sjfl_id);
				pst.execute();
				pst.close();
				
			}else {
				if(sjfl.getLeibie_sp_count()!=0) {
					rs.close();
					st.close();
					throw new BusinessException("该商家分栏中商品数不为0，请检查");
				}else {
					rs.close();
					st.close();
					throw new BusinessException("已经不存在该商家");
				}
				
			}
			
//			conn.commit();//结束事务
		} 
//		catch (BaseException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void modifySj(BeanSj sj,String name,String xinji) throws BaseException{
		java.sql.Connection conn =null;
		try {
			int sj_id=sj.getSj_id();
			conn=DBUtil.getConnection();
			String sql="select * from sj_data where sj_id="+sj_id;//查找商家
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="update sj_data set sj_name=?,sj_xinji=? where sj_id= ? ";//更新操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setInt(2, Integer.parseInt(xinji));
				pst.setInt(3, sj_id);
				pst.execute();
				pst.close();
			}else {
				rs.close();
				st.close();
				throw new BusinessException("已经不存在该商家");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	@Override
	public void deleteSj(BeanSj sj) throws BaseException {
		java.sql.Connection conn =null;
		try {
			int sj_id=sj.getSj_id();
			conn=DBUtil.getConnection();
//			conn.setAutoCommit(false);//开启事务
			String sql="select * from sp_leibie where sj_id="+sj_id;//检查是否存在分栏
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				rs.close();
				st.close();
				throw new BusinessException("该商家仍存在分栏，不能直接删除");
			}
			rs.close();
			
			sql="select * from sj_data where sj_id="+sj_id;//查找商家
			rs=st.executeQuery(sql);
			if(rs.next()) {
				rs.close();
				st.close();
				sql="delete from sj_data where sj_id=?";//删除操作
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, sj_id);
				pst.execute();
				pst.close();
				
			}else {
				rs.close();
				st.close();
				throw new BusinessException("已经不存在该商家");
			}
			
//			conn.commit();//结束事务
		} 
//		catch (BaseException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new DbException(e);
		} finally {
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		

	}

	@Override
	public List<BeanUserAdd> loadAllYHadd(BeanUser user)throws BaseException{
		List<BeanUserAdd> result=new ArrayList<BeanUserAdd>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_province,user_city,user_area,user_address_detail,user_ad_name,user_ad_phonenum"
					+ " from user_address where user_id=? order by user_address_id";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
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
	public List<BeanUser> loadAllYH()throws BaseException{
		List<BeanUser> result=new ArrayList<BeanUser>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id,user_name,user_sex,user_pwd,user_phonenum,user_email,user_city,user_register_time,user_vip_end_time"
					+ " from user_data";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			
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
	public List<BeanQs> loadAllQS()throws BaseException{
		List<BeanQs> result=new ArrayList<BeanQs>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from qs_data";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanQs p=new BeanQs();
				p.setQs_id(rs.getInt(1));
				p.setQs_name(rs.getString(2));
				p.setQs_join_date(rs.getTimestamp(3));
				p.setQs_grade(rs.getString(4));
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
	public List<BeanQsbill> loadAllQSbill(BeanQs qs)throws BaseException{
		List<BeanQsbill> result=new ArrayList<BeanQsbill>();
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from qs_bill"
					+ " where qs_id = ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1,qs.getQs_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanQsbill p=new BeanQsbill();
				p.setQs_id(rs.getInt(1));
				p.setOrder_id(rs.getInt(2));
				p.setQs_getmoney_time(rs.getTimestamp(3));
				p.setQs_getmoney(rs.getFloat(4));
				p.setSp_evaluate_qsxinji(rs.getInt(5));
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