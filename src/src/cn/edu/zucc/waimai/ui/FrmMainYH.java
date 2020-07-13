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

public class FrmMainYH extends JFrame{

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
	private BeanSp curSp=null;
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
	private JMenu menu_6 = new JMenu("管理用户信息");
	private JMenuItem menuItem_1 = new JMenuItem("查看用户信息");
	private JMenu menu_5 = new JMenu("管理用户地址");
	private JMenuItem menuItem_9 = new JMenuItem("查看所有地址");
	private JMenu menu_2 = new JMenu("查看商家");
	private JMenuItem mntmNewMenuItem_2_1_22 = new JMenuItem("查看商家（刷新）");
	private JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("查看商家满减方案");
	private JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("查看商家优惠券");
	private JMenu menu_3 = new JMenu("查看持有优惠券");
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("查看持有优惠券");
	private JMenu menu = new JMenu("购物车");
	private JMenuItem menuItem_10 =new JMenuItem("加入购物车");
	private JMenuItem menuItem_133 =new JMenuItem("查看购物车");
	private JMenu menu1 =new JMenu("退出登陆");
	private JMenuItem menuItem_12 =new JMenuItem("返回主界面");
	
	public FrmMainYH() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统");
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener((e)-> {
			FrmYHOrder dlg=new FrmYHOrder();
			dlg.setVisible(true);
			setVisible(false);
		});
		
		menuBar.add(menu_6);
		menu_6.add(menuItem_1);
		menuItem_1.addActionListener((e)-> {
			FrmYHyh dlg=new FrmYHyh();
			dlg.setVisible(true);
			setVisible(false);
		});
		
		menuBar.add(menu_5);
		menu_5.add(menuItem_9);
		menuItem_9.addActionListener((e)-> {
			FrmYHadd dlg=new FrmYHadd();
			dlg.setVisible(true);
			setVisible(false);
		});
		menuBar.add(menu_2);
		menu_2.add(mntmNewMenuItem_2_1_22);
		mntmNewMenuItem_2_1_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSjTable();
			}
		});
		menu_2.add(mntmNewMenuItem_2_1);
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHsjMJ dlg=new FrmYHsjMJ();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1);
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHsjYHQ dlg=new FrmYHsjYHQ();
				dlg.setVisible(true);
				setVisible(false);
			}
		});

		menuBar.add(menu_3);//查看持有优惠券
		menu_3.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHgetYHQ dlg=new FrmYHgetYHQ();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		
		
		menuBar.add(menu);
		menu.add(menuItem_10);//加购物车
		menuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmMainYH.this.dataTableSpJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择加入购物车商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					WaiMaiUtil.userManager.YHaddBUYCAR(BeanUser.currentLoginUser, allSp.get(i));
					JOptionPane.showMessageDialog(null, "添加购物车成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menu.add(menuItem_133);//查看购物车
		menuItem_133.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FrmYHBUY dlg=new FrmYHBUY();
				dlg.setVisible(true);
			}
		});
		
		menuBar.add(menu1);
		menu1.add(menuItem_12);
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FrmLogin dlg=new FrmLogin();
				dlg.setVisible(true);
			}
		});
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmMainYH.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainYH.this.reloadSjFLTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.CENTER);
		//JScrollPane 滚动条
	    this.dataTableSjFLJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmMainYH.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainYH.this.reloadSpTable(i);
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