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
import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.model.BeanQs;
import src.cn.edu.zucc.waimai.model.BeanQsbill;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmQS extends JFrame{

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

	private Object tblSjTitles[]=BeanQs.QStableTitles;//骑手信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanQs curSj=null;
	List<BeanQs> allSj=null;
	
	private void reloadQsTable(){
		try {
			allSj=WaiMaiUtil.CMDManager.loadAllQS();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjtableData =new Object[allSj.size()][BeanQs.QStableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanQs.QStableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}

	private Object tblSjFLTitles[]=BeanQsbill.QSbilltableTitles;//骑手账单
	private Object SjFLtableData[][];
	private DefaultTableModel tableSjFLModel=new DefaultTableModel();
	private JTable dataTableSjFLJTable =new JTable(tableSjFLModel);
	List<BeanQsbill> allSjFLs=null;
	
	private void reloadQsbillTable(int idx){
		if(idx<0) return;
		curSj=allSj.get(idx);
		try {
			allSjFLs=WaiMaiUtil.CMDManager.loadAllQSbill(curSj);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjFLtableData =new Object[allSjFLs.size()][BeanQsbill.QSbilltableTitles.length];
		for(int i=0;i<allSjFLs.size();i++){
			for(int j=0;j<BeanQsbill.QSbilltableTitles.length;j++)
				SjFLtableData[i][j]=allSjFLs.get(i).getCell(j);
		}
		
		tableSjFLModel.setDataVector(SjFLtableData,tblSjFLTitles);
		this.dataTableSjFLJTable.validate();
		this.dataTableSjFLJTable.repaint();
	}
	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_4 = new JMenu("返回");
	private JMenuItem menuItem_2 = new JMenuItem("返回主界面");
	private JMenu menu_5 = new JMenu("管理骑手");
	private JMenuItem menuItem_7 = new JMenuItem("查看骑手（刷新）");
	private JMenuItem menuItem_6 = new JMenuItem("添加骑手");
	private JMenuItem menuItem_9_1_1 = new JMenuItem("修改骑手");
	private JMenuItem menuItem_8 = new JMenuItem("删除骑手");

	
	
	public FrmQS() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-骑手管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_4);
		menuBar.add(menu_5);
		
		menu_4.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCMDchoice dlg=new FrmCMDchoice();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		
		menu_5.add(menuItem_7);
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadQsTable();
			}
		});
		menu_5.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCMDaddQS dlg=new FrmCMDaddQS();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_9_1_1);//修改
		menuItem_9_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmQS.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDmodifyQS dlg=new FrmCMDmodifyQS(allSj.get(i));
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmQS.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDdeleteQS dlg=new FrmCMDdeleteQS(allSj.get(i));
				dlg.setVisible(true);
			}
		});
		

		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmQS.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmQS.this.reloadQsbillTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.CENTER);
		
		
		
		this.reloadQsTable();//初始展现商家信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanCMD.currentLoginCMD.getCMD_name()+"管理员！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);

		this.setVisible(true);
		
		
	}
}