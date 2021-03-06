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
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.model.BeanSp;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmCMDallYH extends JFrame{

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

	private Object tblSjTitles[]=BeanUser.UsertableTitles;//用户信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanUser curSj=null;
	List<BeanUser> allSj=null;
	
	private void reloadSjTable(){
		try {
			allSj=WaiMaiUtil.CMDManager.loadAllYH();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		SjtableData =new Object[allSj.size()][BeanUser.UsertableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanUser.UsertableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}

	private Object tblSjFLTitles[]=BeanUserAdd.UserAddtableTitles;//商家分栏信息
	private Object SjFLtableData[][];
	private DefaultTableModel tableSjFLModel=new DefaultTableModel();
	private JTable dataTableSjFLJTable =new JTable(tableSjFLModel);
	private BeanUserAdd curSjFL=null;
	List<BeanUserAdd> allSjFLs=null;
	
	private void reloadSjFLTable(int idx){
		if(idx<0) return;
		curSj=allSj.get(idx);
		try {
			allSjFLs=WaiMaiUtil.CMDManager.loadAllYHadd(curSj);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjFLtableData =new Object[allSjFLs.size()][BeanUserAdd.UserAddtableTitles.length];
		for(int i=0;i<allSjFLs.size();i++){
			for(int j=0;j<BeanUserAdd.UserAddtableTitles.length;j++)
				SjFLtableData[i][j]=allSjFLs.get(i).getCell(j);
		}
		
		tableSjFLModel.setDataVector(SjFLtableData,tblSjFLTitles);
		this.dataTableSjFLJTable.validate();
		this.dataTableSjFLJTable.repaint();
	}
	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_1 = new JMenu("返回");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("返回主界面");
	private JMenu menu_2 = new JMenu("管理用户");
	private JMenuItem mntmNewMenuItem_2_1_22 = new JMenuItem("查看用户（刷新）");
	private JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("新建用户");
	private JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("不能删除用户");
	private JMenuItem mntmNewMenuItem_2_1_1_1 = new JMenuItem("不能修改用户");
	private JMenu menu_3 = new JMenu("管理用户地址");
	private JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("不能设置用户地址！");
	
	
	public FrmCMDallYH() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-管理用户");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener((e)-> {
			FrmCMDchoice dlg=new FrmCMDchoice();
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
				FrmCMDYHregister dlgCmdyHregister=new FrmCMDYHregister();
				dlgCmdyHregister.setVisible(true);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1);
		menu_2.add(mntmNewMenuItem_2_1_1_1);
		menuBar.add(menu_3);
		menu_3.add(mntmNewMenuItem_3_1);
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.CENTER);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmCMDallYH.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmCMDallYH.this.reloadSjFLTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.EAST);

		this.reloadSjTable();//初始展现用户信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanCMD.currentLoginCMD.getCMD_name()+"管理员！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}
}