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

public class FrmYHOrder extends JFrame{

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
	
	
	
	public FrmYHOrder() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-查看订单");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu_1.add(menuItem_7);
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu_1.add(menuItem_7_1);
		menuItem_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu_1.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmYHOrder.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmYHOrder.this.reloadSjFLTable(i);
			}
	    });
		
		this.reloadSjTable();//初始展现商家信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}