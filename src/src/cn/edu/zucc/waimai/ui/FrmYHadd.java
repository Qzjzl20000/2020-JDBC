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
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmYHadd extends JFrame{

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

	private Object tblSjTitles[]=BeanUserAdd.UserAddtableTitles;//用户地址信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanUserAdd curSj=null;
	List<BeanUserAdd> allSj=null;
	
	private void reloadSjTable(){
		try {
			allSj=WaiMaiUtil.userManager.loadAllYHadd();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		SjtableData =new Object[allSj.size()][BeanUserAdd.UserAddtableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanUserAdd.UserAddtableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}
	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_4 = new JMenu("返回");
	private JMenuItem menuItem_2 = new JMenuItem("返回主界面");
	private JMenu menu_5 = new JMenu("管理用户地址");
	private JMenuItem menuItem_7 = new JMenuItem("查看地址（刷新）");
	private JMenuItem menuItem_6 = new JMenuItem("添加地址");
	private JMenuItem menuItem_9_1_1 = new JMenuItem("修改地址");
	private JMenuItem menuItem_8 = new JMenuItem("删除地址");

	
	
	public FrmYHadd() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-查看地址");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_4);
		menuBar.add(menu_5);
		
		menu_4.add(menuItem_2);
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMainYH dlg=new FrmMainYH();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		
		menu_5.add(menuItem_7);
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSjTable();
			}
		});
		menu_5.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHAddRegister dlg=new FrmYHAddRegister();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_9_1_1);
		menuItem_9_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmYHadd.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择地址", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmYHmodifyAdd dlg=new FrmYHmodifyAdd(allSj.get(i));
//				System.out.println(allSj.get(i).getUser_add_id());
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=FrmYHadd.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选择地址", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					WaiMaiUtil.userManager.deleteYHAD(allSj.get(i));
					JOptionPane.showMessageDialog(null, "删除成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.CENTER);
		//JScrollPane 滚动条
		this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
		@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmYHadd.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					return;
				}
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