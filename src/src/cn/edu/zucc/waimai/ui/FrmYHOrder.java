package src.cn.edu.zucc.waimai.ui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanOrder;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;
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

	private Object tblYHOrderTitles[]=BeanOrder.OrdertableTitles;//用户地址信息
	private Object YHOrdertableData[][];
	private DefaultTableModel tableYHOrderModel=new DefaultTableModel();
	private JTable dataTableYHOrderJTable =new JTable(tableYHOrderModel);
	List<BeanOrder> allYHOrder=null;
	
	private void reloadYHOrderTable(){
		try {
			allYHOrder=WaiMaiUtil.userManager.loadAllYHOrder();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		YHOrdertableData =new Object[allYHOrder.size()][BeanOrder.OrdertableTitles.length];
		for(int i=0;i<allYHOrder.size();i++) {
			for(int j=0;j<BeanOrder.OrdertableTitles.length;j++) {
				YHOrdertableData[i][j]=allYHOrder.get(i).getCell(j);
			}
		}
		tableYHOrderModel.setDataVector(YHOrdertableData, tblYHOrderTitles);
		dataTableYHOrderJTable.validate();
		dataTableYHOrderJTable.repaint();
	}

	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_4 = new JMenu("返回");
	private JMenuItem menuItem_2 = new JMenuItem("返回主界面");
	private JMenu menu_5 = new JMenu("管理用户订单");
	private JMenuItem menuItem_7 = new JMenuItem("查看订单（刷新）");
	private JMenuItem menuItem_6 = new JMenuItem("查看取消的订单");
	private JMenuItem menuItem_9_1_1 = new JMenuItem("查看正在进行的订单");
	private JMenuItem menuItem_8 = new JMenuItem("查看已完成的订单");

	
	
	public FrmYHOrder() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-查看订单");
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
				reloadYHOrderTable();
			}
		});
		menu_5.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu_5.add(menuItem_9_1_1);
		menu_5.add(menuItem_8);
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableYHOrderJTable), BorderLayout.CENTER);
		
		
		this.reloadYHOrderTable();//初始展现商家信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}