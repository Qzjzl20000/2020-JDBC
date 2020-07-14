package src.cn.edu.zucc.waimai.comtrol.example;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;
import java.util.zip.DataFormatException;

import javax.swing.JOptionPane;

import src.cn.edu.zucc.waimai.itf.IUserManager;
import src.cn.edu.zucc.waimai.model.BeanOrder;
import src.cn.edu.zucc.waimai.model.BeanSjYHQ;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.model.BeanUserBUYCAR;
import src.cn.edu.zucc.waimai.model.BeanUserYHQ;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

public class UserManager implements IUserManager {
	@Override
	public void comment(BeanOrder order,String comment,String qs_star,String addqs_money) throws BaseException{
		float money=0;
		int qs=0;
		if(comment.equals("")) {
			throw new BusinessException("评论内容不能为空！");
		}
		if(!qs_star.equals("")) {
			qs=Integer.parseInt(qs_star);
		}
		if(!addqs_money.equals("")){
			money=Float.parseFloat(addqs_money);
		}
		if(order.getOrder_state().equals("已送达")) {
			java.sql.Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="insert into sp_evaluate(sp_id,sj_id,user_id,sp_evaluate_content,sp_evaluate_time,sp_evaluate_spxinji,sp_evaluate_qsxinji) "
						+ " values(0,?,?,?,now(),3,?)";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, order.getSj_id());
				pst.setInt(2, order.getUser_id());
				pst.setString(3, comment);
				pst.setInt(4, qs);
				pst.execute();
				pst.close();
				
				sql="update qs_bill set qs_getmoney=qs_getmoney+? where order_id=?";
				pst=conn.prepareStatement(sql);
				pst.setFloat(1, money);
				pst.setInt(2, order.getOrder_id());
				pst.execute();
				pst.close();
				
				sql="update order_data set order_state=? where order_id=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, "已完成");
				pst.setInt(2, order.getOrder_id());
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
		else {
			throw new BusinessException("订单状态送达才能评价！");
		}
	}
	@Override
	public void modifyAdd(BeanUserAdd useradd,String province,String city, String area,
			String adddetail,String name,String phoneNum) throws BaseException{
		if(province.equals("")) {
			province=useradd.getUser_province();
		}
		if(city.equals("")) {
			city=useradd.getUser_city();
		}
		if(area.equals("")) {
			area=useradd.getUser_area();
		}
		if(adddetail.equals("")) {
			adddetail=useradd.getUser_add_detail();
		}
		if(name.equals("")) {
			name=useradd.getUser_add_name();
		}
		if(phoneNum.equals("")) {
			phoneNum=useradd.getUser_add_phonenum();
		}
		java.sql.Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update user_address set user_province=?,user_city=?,user_area=?,user_address_detail=?,user_ad_name=?,user_ad_phonenum=? where user_address_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, province);
			pst.setString(2, city);
			pst.setString(3, area);
			pst.setString(4, adddetail);
			pst.setString(5, name);
			pst.setString(6, phoneNum);
			pst.setInt(7, useradd.getUser_add_id());
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
	public void deleteYHAD(BeanUserAdd useradd) throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from user_address where user_address_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, useradd.getUser_add_id());
			pst.execute();
			pst.close();
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
	public int BUY_count(BeanUser user,int sjid)throws BaseException{
		java.sql.Connection conn =null;
		int count=0;
		try {
			conn=DBUtil.getConnection();
			String sql="select sp_count from user_car where user_id=? and sj_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, sjid);
			java.sql.ResultSet rSet=pst.executeQuery();
			while(rSet.next()) {
				count = count+rSet.getInt(1);
			}
			rSet.close();
			pst.close();
			return count;
			
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
	public float BUY_money(BeanUser user,int sjid)throws BaseException{
		java.sql.Connection conn =null;
		float money=0;
		
		try {
			conn=DBUtil.getConnection();
			String sql="select sp_count,sp_one_money from user_car where user_id=? and sj_id=? ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, sjid);
			java.sql.ResultSet rSet=pst.executeQuery();
			while(rSet.next()) {
				money = money+rSet.getInt(1)*rSet.getFloat(2);
			}
			
			rSet.close();
			pst.close();
			return money;
			
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
	public void BUYwithout(BeanUser user,float money,int count,String time,int add,int sj_id)throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into order_data(sj_id,user_id,qs_id,mj_id,order_origin_money,order_final_money,youhuiquan_id,order_set_time,order_set_arrive_time,user_address_id,order_state)"
					+ " values(?,?,0,0,?,?,?,now(),?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, sj_id);
			pst.setInt(2, user.getUser_id());
			pst.setFloat(3, money);
			pst.setFloat(4, money);
			pst.setInt(5, 0);
			pst.setTimestamp(6, Timestamp.valueOf(time));
			pst.setInt(7, add);
			pst.setString(8, "未接单");
			pst.execute();
			pst.close();
			
			sql="delete from user_car where user_id=? and sj_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, sj_id);
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "订单评价成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
			conn.commit();
			
		} catch (SQLException e) {
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
	public void BUY(BeanUser user,float money,int count,String time,int add,BeanUserYHQ yhq)throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into order_data(sj_id,user_id,qs_id,mj_id,order_origin_money,order_final_money,youhuiquan_id,order_set_time,order_set_arrive_time,user_address_id,order_state)"
					+ " values(?,?,0,0,?,?,?,now(),?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, yhq.getSj_id());
			pst.setInt(2, user.getUser_id());
			pst.setFloat(3, money);
			pst.setFloat(4, money-yhq.getYouhui_money());
			pst.setInt(5, yhq.getYouhuiquan_id());
			pst.setTimestamp(6, Timestamp.valueOf(time));
			pst.setInt(7, add);
			pst.setString(8, "未接单");
			pst.execute();
			pst.close();
			
			sql="delete from user_car where user_id=? and sj_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user.getUser_id());
			pst.setInt(2, yhq.getSj_id());
			pst.execute();
			pst.close();
			
			sql="update user_youhuiquan_get set youhuiquan_count=youhuiquan_count-1 where youhuiquan_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, yhq.getYouhuiquan_id());
			pst.execute();
			pst.close();
			
			sql="delete from user_youhuiquan_get where youhuiquan_count<1";
			pst=conn.prepareStatement(sql);
			pst.execute();
			pst.close();
			
			conn.commit();
			
		} catch (SQLException e) {
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
	public List<BeanUserYHQ> loadYHyhq_CanBeUse(BeanUser user)throws BaseException{
		List<BeanUserYHQ> result=new ArrayList<BeanUserYHQ>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			Timestamp timestamp=null;
			String sql="select ADDDATE(now(),interval 0 year)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
				 timestamp=rs.getTimestamp(1);
			
			rs.close();
			pst.close();
			String sql1="select youhuiquan_id,sj_id,youhui_money,youhuiquan_count,youhuiquan_end_time"
					+ " from user_youhuiquan_get"
					+ " where user_id =? and youhuiquan_count>0 ";
			//满足时间要在有效期内
			java.sql.PreparedStatement pst1= conn.prepareStatement(sql1);
			pst1.setInt(1,user.getUser_id());
			java.sql.ResultSet rs1=pst1.executeQuery();
			
			while(rs1.next()){
				if(rs1.getTimestamp(5).after(timestamp)){
					BeanUserYHQ p=new BeanUserYHQ();
					p.setUser_id(user.getUser_id());
					p.setYouhuiquan_id(rs1.getInt(1));
					p.setSj_id(rs1.getInt(2));
					p.setYouhui_money(rs1.getFloat(3));
					p.setYouhuiquan_count(rs1.getInt(4));
					p.setYouhuiquan_end_time(rs1.getTimestamp(5));
					result.add(p);
				}
			}
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
	public void deleteBUYCAR(BeanUserBUYCAR buycar)throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_car where sp_id= ?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1,buycar.getSp_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				sql="delete from user_car where sp_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, buycar.getSp_id());
				pst.execute();
				pst.close();
			}else {
				rs.close();
				pst.close();
				throw new BusinessException("该商品在购物车中已经不存在！");
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
	public List<BeanUserBUYCAR> loadAllBUYCAR(BeanUser user)throws BaseException{
		List<BeanUserBUYCAR> result=new ArrayList<BeanUserBUYCAR>();
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_car where user_id=? ";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1,user.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUserBUYCAR p=new BeanUserBUYCAR();
				p.setSj_id(rs.getInt(1));
				p.setSp_id(rs.getInt(2));
				p.setUser_id(rs.getInt(3));
				p.setSp_count(rs.getInt(4));
				p.setSp_one_money(rs.getFloat(5));
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
	public void YHaddBUYCAR(BeanUser user,BeanSp sp)throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="select * from user_car where sp_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, sp.getSp_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				sql="update user_car set sp_count=sp_count+1 where sp_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, sp.getSp_id());
				pst.execute();
			}
			else {
				rs.close();
				pst.close();
				sql="insert into user_car(sj_id,sp_id,user_id,sp_count,sp_one_money) values(?,?,?,1,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, sp.getSj_id());
				pst.setInt(2, sp.getSp_id());
				pst.setInt(3, user.getUser_id());
				pst.setFloat(4, sp.getSp_price());
				pst.execute();
			}
			pst.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	public void YHgetYHQ(BeanUser user,BeanSjYHQ yhq)throws BaseException{
		java.sql.Connection conn =null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			BeanUserYHQ userYHQ=new BeanUserYHQ();
			String sql="select sj_id,youhuiquan_id,youhui_money,youhuiquan_end_time from sj_youhuiquan"
					+ " where youhuiquan_id=?";
			java.sql.PreparedStatement pst= conn.prepareStatement(sql);
			pst.setInt(1, yhq.getYouhuiquan_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				userYHQ.setSj_id(rs.getInt(1));
				userYHQ.setYouhuiquan_id(rs.getInt(2));
				userYHQ.setYouhui_money(rs.getFloat(3));
				userYHQ.setYouhuiquan_end_time(rs.getTimestamp(4));
				rs.close();
				pst.close();
				sql="select youhuiquan_id from user_youhuiquan_get where youhuiquan_id=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,yhq.getYouhuiquan_id());
				java.sql.ResultSet rs1=pst.executeQuery();
				if(rs1.next()) {
					rs1.close();
					pst.close();
					sql="update user_youhuiquan_get set youhuiquan_count=youhuiquan_count+1 where youhuiquan_id=?";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, yhq.getYouhuiquan_id());
					pst.execute();
			
				}else {
					rs1.close();
					pst.close();
					sql="insert into user_youhuiquan_get(user_id,youhuiquan_id,sj_id,youhui_money,youhuiquan_count,youhuiquan_end_time) "
							+ "values(?,?,?,?,1,?)";
					pst=conn.prepareStatement(sql);
					pst.setInt(1, user.getUser_id());
					pst.setInt(2, yhq.getYouhuiquan_id());
					pst.setInt(3, yhq.getSj_id());
					pst.setFloat(4, yhq.getYouhui_money());
					pst.setTimestamp(5, yhq.getYouhuiquan_end_time());
					pst.execute();
				}
			}
			pst.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			String sql="select user_id,user_province,user_city,user_area,user_address_detail,user_ad_name,user_ad_phonenum,user_address_id"
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
				p.setUser_add_id(rs.getInt(8));
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
