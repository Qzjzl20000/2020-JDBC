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
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmCMDsj extends JFrame{

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

	private Object tblSpTitles[]=BeanSp.SptableTitles;//商品信息
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
	private JMenu menu_1 = new JMenu("返回");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("返回主界面");
	private JMenu menu_2 = new JMenu("管理商家");
	private JMenuItem mntmNewMenuItem_2_1_22 = new JMenuItem("查看商家（刷新）");
	private JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("新建商家");
	private JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("删除商家");
	private JMenuItem mntmNewMenuItem_2_1_1_1 = new JMenuItem("修改商家");
	private JMenu menu_5 = new JMenu("管理商家分栏");
	private JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("新增分栏");
	private JMenuItem mntmNewMenuItem_3_2 = new JMenuItem("删除分栏");
	private JMenuItem mntmNewMenuItem_3_3 = new JMenuItem("修改分栏");
	private JMenu menu_6 = new JMenu("管理商家分栏商品");
	private JMenuItem mntmNewMenuItem_4_1 = new JMenuItem("新增商品");
	private JMenuItem mntmNewMenuItem_4_2 = new JMenuItem("删除商品");
	private JMenuItem mntmNewMenuItem_4_3 = new JMenuItem("修改商品");
	private JMenu menu_3 = new JMenu("查看优惠券");
	private JMenuItem mntmNewMenuItem_1 = new JMenuItem("查看选中商家优惠券");
	private JMenu menu_4 = new JMenu("查看集单进度");
	private JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("查看选中商家集单政策");
	
	public FrmCMDsj() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-管理商家");
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
				FrmCMDaddSJ dlg=new FrmCMDaddSJ();
				dlg.setVisible(true);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1);
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商家", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDdeleteSJ dlg=new FrmCMDdeleteSJ(allSj.get(i));
				dlg.setVisible(true);
			}
		});
		menu_2.add(mntmNewMenuItem_2_1_1_1);
		mntmNewMenuItem_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商家", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDmodifySJ dlg=new FrmCMDmodifySJ(allSj.get(i));
				dlg.setVisible(true);
			}
		});
		menuBar.add(menu_5);
		menu_5.add(mntmNewMenuItem_3_1);
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商家", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDaddSJFL flg=new FrmCMDaddSJFL(allSj.get(i));
				flg.setVisible(true);
			}
		});
		menu_5.add(mntmNewMenuItem_3_2);
		mntmNewMenuItem_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择分栏", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDdeleteSJFL flg=new FrmCMDdeleteSJFL(allSjFLs.get(i));
				flg.setVisible(true);
			}
		});
		menu_5.add(mntmNewMenuItem_3_3);
		mntmNewMenuItem_3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择分栏", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDmodifySJFL flg=new FrmCMDmodifySJFL(allSjFLs.get(i));
				flg.setVisible(true);
			}
		});
		
		menuBar.add(menu_6);
		menu_6.add(mntmNewMenuItem_4_1);
		mntmNewMenuItem_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择分栏", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDaddSP flg=new FrmCMDaddSP(allSjFLs.get(i));
				flg.setVisible(true);
			}
		});
		menu_6.add(mntmNewMenuItem_4_2);
		mntmNewMenuItem_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSpJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDdeleteSP flg=new FrmCMDdeleteSP(allSp.get(i));
				flg.setVisible(true);
			}
		});
		menu_6.add(mntmNewMenuItem_4_3);
		mntmNewMenuItem_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmCMDsj.this.dataTableSpJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCMDmodifySP flg=new FrmCMDmodifySP(allSp.get(i));
				flg.setVisible(true);
			
			}
		});
		
		menuBar.add(menu_3);
		menu_3.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCMDsjYHQ flg=new FrmCMDsjYHQ();
				flg.setVisible(true);
				setVisible(false);
			}
		});
		menuBar.add(menu_4);
		menu_4.add(mntmNewMenuItem_1_1);
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCMDsjMJ flg=new FrmCMDsjMJ();
				flg.setVisible(true);
				setVisible(false);
			
			}
		});
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmCMDsj.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmCMDsj.this.reloadSjFLTable(i);
			}
	    });
		this.getContentPane().add(new JScrollPane(this.dataTableSjFLJTable), BorderLayout.CENTER);
		
		//JScrollPane 滚动条
	    this.dataTableSjFLJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmCMDsj.this.dataTableSjFLJTable.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmCMDsj.this.reloadSpTable(i);
			}
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTableSpJTable), BorderLayout.EAST);
	   

		this.reloadSjTable();//初始展现商家信息
		
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanCMD.currentLoginCMD.getCMD_name()+"管理员！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		
		this.setVisible(true);
		
		
	}
}