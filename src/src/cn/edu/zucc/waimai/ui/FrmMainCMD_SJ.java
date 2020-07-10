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

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmMainCMD_SJ extends JFrame{

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

	private Object tblSjTitles[]=BeanSj.SjtableTitles;//商家信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanSj curSj=null;
	List<BeanSj> allSj=null;
	
	private void reloadSjTable(){
		try {
			allSj=WaiMaiUtil.userSjManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjtableData =new Object[allSj.size()][BeanSj.SjtableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanSj.SjtableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}

	private Object tblSjFLTitles[]=BeanSjFL.leibietableTitles;//商家分栏信息
	private Object SjFLtableData[][];
	private DefaultTableModel tableSjFLModel=new DefaultTableModel();
	private JTable dataTableSjFLJTable =new JTable(tableSjFLModel);
	private BeanSjFL curSjFL=null;
	List<BeanSjFL> allSjFLs=null;
	
	private void reloadSjFLTable(int idx){
		if(idx<0) return;
		curSj=allSj.get(idx);
		try {
			allSjFLs=WaiMaiUtil.userSjManager.loadAlllB(curSj);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjFLtableData =new Object[allSjFLs.size()][BeanSjFL.leibietableTitles.length];
		for(int i=0;i<allSjFLs.size();i++){
			for(int j=0;j<BeanSjFL.leibietableTitles.length;j++)
				SjFLtableData[i][j]=allSjFLs.get(i).getCell(j);
		}
		
		tableSjFLModel.setDataVector(SjFLtableData,tblSjFLTitles);
		this.dataTableSjFLJTable.validate();
		this.dataTableSjFLJTable.repaint();
	}

	private Object tblSpTitles[]=BeanSp.SptableTitles;//商家分栏商品信息
	private Object SptableData[][];
	private DefaultTableModel tableSpModel=new DefaultTableModel();
	private JTable dataTableSpJTable =new JTable(tableSpModel);
	List<BeanSp> allSp=null;
	private void reloadSpTable(int idx){
		if(idx<0) return;
		curSjFL=allSjFLs.get(idx);
		try {
			allSp=WaiMaiUtil.userSjManager.loadAllSp(curSjFL);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SptableData =new Object[allSp.size()][BeanSp.SptableTitles.length];
		for(int i=0;i<allSp.size();i++){
			for(int j=0;j<BeanSp.SptableTitles.length;j++)
				SptableData[i][j]=allSp.get(i).getCell(j);
		}
		
		tableSpModel.setDataVector(SptableData,tblSpTitles);
		this.dataTableSpJTable.validate();
		this.dataTableSpJTable.repaint();
	}
	
	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_1 = new JMenu("查看订单详情");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("查看所有订单");
	private JMenuItem menuItem_7 = new JMenuItem("已取消的订单");
	private JMenuItem menuItem_7_1 = new JMenuItem("正在进行的订单");
	private JMenuItem menuItem = new JMenuItem("已完成的订单");
	private JMenu menu_6 = new JMenu("管理用户信息");
	private JMenuItem menuItem_1 = new JMenuItem("查看用户信息");
	private JMenuItem menuItem_5_1 = new JMenuItem("查看会员信息");
	private JMenuItem menuItem_2 = new JMenuItem("修改密码");
	private JMenuItem menuItem_3 = new JMenuItem("修改手机号码");
	private JMenuItem menuItem_4 = new JMenuItem("修改邮箱");
	private JMenuItem menuItem_5 = new JMenuItem("修改所在城市");
	private JMenu menu_5 = new JMenu("管理用户地址");
	private JMenuItem menuItem_6 = new JMenuItem("添加地址");
	private JMenuItem menuItem_9 = new JMenuItem("查看所有地址");
	private JMenuItem menuItem_9_1_1 = new JMenuItem("修改地址");
	private JMenuItem menuItem_8 = new JMenuItem("删除地址");
	private JMenu menu_2 = new JMenu("查看商家");
	private JMenuItem mntmNewMenuItem = new JMenuItem("查看商家详情");
	private JMenuItem mntmNewMenuItem_2 = new JMenuItem("查看商品分栏");
	private JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("查看商品详情");
	private JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("查看商家满减方案");
	private JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("查看商家优惠券");
	private JMenuItem mntmNewMenuItem_2_1_1_1 = new JMenuItem("查看商家集单政策");
	private JMenu menu_3 = new JMenu("查看持有优惠券");
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("查看持有优惠券");
	private JMenu menu_4 = new JMenu("查看集单进度");
	private JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("查看集单进度");
	private JMenu menu = new JMenu("购买");
	
	
	public FrmMainCMD_SJ() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menu_1.add(menuItem_7);
		menu_1.add(menuItem_7_1);
		menu_1.add(menuItem);
		menuBar.add(menu_6);
		menu_6.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu_6.add(menuItem_5_1);
		menu_6.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyPwd dlg=new FrmYHModifyPwd();
				dlg.setVisible(true);
			}
		});
		menu_6.add(menuItem_3);
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyPhonenum dlg=new FrmYHModifyPhonenum();
				dlg.setVisible(true);
			}
		});
		menu_6.add(menuItem_4);
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyEmail dlg=new FrmYHModifyEmail();
				dlg.setVisible(true);
			}
		});
		menu_6.add(menuItem_5);
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyCity dlg=new FrmYHModifyCity();
				dlg.setVisible(true);
			}
		});
		menuBar.add(menu_5);
		menu_5.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHAddRegister dlg=new FrmYHAddRegister();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_9);
		menu_5.add(menuItem_9_1_1);
		menu_5.add(menuItem_8);
		menuBar.add(menu_2);
		menu_2.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSjTable();
			}
		});
		menu_2.add(mntmNewMenuItem_2);
		menu_2.add(mntmNewMenuItem_2_2);
		menu_2.add(mntmNewMenuItem_2_1);		
		menu_2.add(mntmNewMenuItem_2_1_1);
		menu_2.add(mntmNewMenuItem_2_1_1_1);
		menuBar.add(menu_3);
		menu_3.add(mntmNewMenuItem_1);
		menuBar.add(menu_4);
		menu_4.add(mntmNewMenuItem_1_1);
		menuBar.add(menu);
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmMainCMD_SJ.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainCMD_SJ.this.reloadSjFLTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.CENTER);
		//JScrollPane 滚动条
	    this.dataTableSjFLJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmMainCMD_SJ.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainCMD_SJ.this.reloadSpTable(i);
			}
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableSpJTable), BorderLayout.EAST);
		
		this.reloadSjTable();//初始展现商家信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}