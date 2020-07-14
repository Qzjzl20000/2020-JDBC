package src.cn.edu.zucc.waimai.ui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.dom4j.bean.BeanMetaData;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSjMJ;
import src.cn.edu.zucc.waimai.model.BeanSjYHQ;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserBUYCAR;
import src.cn.edu.zucc.waimai.model.BeanUserYHQ;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmYHBUY extends JFrame{

	private JPanel contentPane=new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Object tblSjTitles[]=BeanUserBUYCAR.BUYCARtableTitles;//购物车信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanUserBUYCAR curSj=null;
	List<BeanUserBUYCAR> allSj=null;
	
	private void reloadSjTable(){
		try {
			allSj=WaiMaiUtil.userManager.loadAllBUYCAR(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjtableData =new Object[allSj.size()][BeanUserBUYCAR.BUYCARtableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanUserBUYCAR.BUYCARtableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}

	private Object tblSjFLTitles[]=BeanUserYHQ.UserYHQtableTitles;//优惠券信息
	private Object SjFLtableData[][];
	private DefaultTableModel tableSjFLModel=new DefaultTableModel();
	private JTable dataTableSjFLJTable =new JTable(tableSjFLModel);
	List<BeanUserYHQ> allSjFLs=null;
	
	private void reloadSjFLTable(){
		
		try {
			allSjFLs=WaiMaiUtil.userManager.loadYHyhq_CanBeUse(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjFLtableData =new Object[allSjFLs.size()][BeanUserYHQ.UserYHQtableTitles.length];
		for(int i=0;i<allSjFLs.size();i++){
			for(int j=0;j<BeanUserYHQ.UserYHQtableTitles.length;j++)
				SjFLtableData[i][j]=allSjFLs.get(i).getCell(j);
			
		}
		
		tableSjFLModel.setDataVector(SjFLtableData,tblSjFLTitles);
		this.dataTableSjFLJTable.validate();
		this.dataTableSjFLJTable.repaint();
	}
	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_1 = new JMenu("返回");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("返回上一界面");
	private JMenu menu_2 = new JMenu("管理购物车");
	private JMenuItem mntmNewMenuItem_2_1_2 = new JMenuItem("查看购物车（刷新）");
	private JMenuItem mntmNewMenuItem_2_1_4 = new JMenuItem("删除该记录");
	private JMenu menu_6 = new JMenu("优惠结算");
	private JMenuItem menuItem_1 = new JMenuItem("与选择的优惠券一起结算");
	private JMenuItem menuItem_2 =new JMenuItem("不使用优惠券结算");
	
	public FrmYHBUY() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-下单");
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMainYH dlg=new FrmMainYH();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		menuBar.add(menu_2);
		menu_2.add(mntmNewMenuItem_2_1_2);
		menu_2.add(mntmNewMenuItem_2_1_4);
		mntmNewMenuItem_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSjTable();
			}
		});
		
		mntmNewMenuItem_2_1_4.addActionListener(new ActionListener() {//删除选中
			public void actionPerformed(ActionEvent e) {
				int i=FrmYHBUY.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选中购物车记录", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					WaiMaiUtil.userManager.deleteBUYCAR(allSj.get(i));
					JOptionPane.showMessageDialog(null, "删除购物车记录成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menuBar.add(menu_6);
		menu_6.add(menuItem_1);//与选择的优惠券一起结算
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmYHBUY.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选中优惠券记录", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmYHCheckOut_with dlg=new FrmYHCheckOut_with(allSjFLs.get(i));
				dlg.setVisible(true);
			}
		});
		menu_6.add(menuItem_2);//不使用优惠券结算
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHCheck_without dlg=new FrmYHCheck_without();
				dlg.setVisible(true);
			}
		});
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.CENTER);
	
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.EAST);
	
		
		this.reloadSjTable();//初始展现商家信息
		this.reloadSjFLTable();//初始展现商家信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}