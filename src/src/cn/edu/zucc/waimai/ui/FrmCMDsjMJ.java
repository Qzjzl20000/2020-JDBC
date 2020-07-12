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
import src.cn.edu.zucc.waimai.model.BeanSjMJ;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmCMDsjMJ extends JFrame{

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

	private Object tblSjFLTitles[]=BeanSjMJ.SjMJtableTitles;//商家分栏信息
	private Object SjFLtableData[][];
	private DefaultTableModel tableSjFLModel=new DefaultTableModel();
	private JTable dataTableSjFLJTable =new JTable(tableSjFLModel);
	private BeanSjMJ curSjFL=null;
	List<BeanSjMJ> allSjFLs=null;
	
	private void reloadSjFLTable(int idx){
		if(idx<0) return;
		curSj=allSj.get(idx);
		try {
			allSjFLs=WaiMaiUtil.userSjManager.loadAllMJ(curSj);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjFLtableData =new Object[allSjFLs.size()][BeanSjMJ.SjMJtableTitles.length];
		for(int i=0;i<allSjFLs.size();i++){
			for(int j=0;j<BeanSjMJ.SjMJtableTitles.length;j++)
				SjFLtableData[i][j]=allSjFLs.get(i).getCell(j);
		}
		
		tableSjFLModel.setDataVector(SjFLtableData,tblSjFLTitles);
		this.dataTableSjFLJTable.validate();
		this.dataTableSjFLJTable.repaint();
	}

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_1 = new JMenu("返回");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("返回主界面");
	private JMenu menu_2 = new JMenu("管理满减");
	private JMenuItem mntmNewMenuItem_2_1_22 = new JMenuItem("查看满减（刷新）");
	private JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("新建商家满减");
	private JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("删除商家满减");
	private JMenuItem mntmNewMenuItem_2_1_1_1 = new JMenuItem("修改商家满减");
	
	public FrmCMDsjMJ() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-管理商家优惠券");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener((e)-> {
			FrmCMDsj dlg=new FrmCMDsj();
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
				int i=FrmCMDsjMJ.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商家", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDaddSJMJ dlg=new FrmCMDaddSJMJ(allSj.get(i));
				dlg.setVisible(true);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1);
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsjMJ.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择满减项目", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDdeleteSjMJ dlg=new FrmCMDdeleteSjMJ(allSjFLs.get(i));
				dlg.setVisible(true);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1_1);
		mntmNewMenuItem_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsjMJ.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择满减项目", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDmodifySjMJ dlg=new FrmCMDmodifySjMJ(allSjFLs.get(i));
				dlg.setVisible(true);
			}
		});
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmCMDsjMJ.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmCMDsjMJ.this.reloadSjFLTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.CENTER);
		
		
	   

		this.reloadSjTable();//初始展现商家信息
		
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanCMD.currentLoginCMD.getCMD_name()+"管理员！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		
	}
}