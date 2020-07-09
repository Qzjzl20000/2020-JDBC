package src.cn.edu.zucc.waimai.ui;


import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.ui.FrmLogin;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmMainYH extends JFrame implements ActionListener{

	private JPanel contentPane;
	
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
	/**
	 * Create the frame.
	 */
	
	
	private Object tblSjTitles[]=BeanSj.SjtableTitles;//商家信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
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
	
	private Object tblYHTitles[]=BeanUser.UsertableTitles;//用户信息
	private Object YHtableData[][];
	private DefaultTableModel tableYHModel=new DefaultTableModel();
	private JTable dataTableYHJTable =new JTable(tableYHModel);
	List<BeanUser> allYH=null;
	private void reloadYHTable() {
		try {
			allYH=WaiMaiUtil.userManager.loadAll(BeanUser.currentLoginUser.getUser_name());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		YHtableData=new Object[allYH.size()][BeanUser.UsertableTitles.length];
		for(int i=0;i<allYH.size();i++) {
			for(int j=0;j<BeanUser.UsertableTitles.length;j++) {
				YHtableData[i][j]=allYH.get(i).getCell(j);
			}
		}
		tableYHModel.setDataVector(YHtableData, tblYHTitles);
		dataTableYHJTable.validate();
		dataTableYHJTable.repaint();
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
	
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel panel = new JPanel();
	private JLabel lblNewLabel = null;

	public FrmMainYH() {
		setTitle("外卖管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setBounds(100, 100, 1000, 800);
		
		
		setJMenuBar(menuBar);
		
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menu_1.add(menuItem_7);
		menu_1.add(menuItem_7_1);
		menu_1.add(menuItem);
		
		
		menuBar.add(menu_6);
		menu_6.add(menuItem_1);
		menu_6.add(menuItem_5_1);
		menu_6.add(menuItem_2);
		menu_6.add(menuItem_3);
		menu_6.add(menuItem_4);
		menu_6.add(menuItem_5);
		
		
		menuBar.add(menu_5);
		menu_5.add(menuItem_6);
		
		menu_5.add(menuItem_9);
		menu_5.add(menuItem_9_1_1);
		menu_5.add(menuItem_8);
		
		
		menuBar.add(menu_2);
		menu_2.add(mntmNewMenuItem);
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
		
		
		
		this.getContentPane().add(scrollPane);
		scrollPane.setBounds(6, 6, 990, 725);

//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		this.setContentPane(contentPane);
//		contentPane.setLayout(null);
		

		//状态栏
		this.getContentPane().add(panel);
		panel.setBounds(6, 730, 400, 20);
		panel.setLayout(null);
		
//		信息
		lblNewLabel=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
//		JOptionPane.showMessageDialog(null, "2", "系统提示",JOptionPane.INFORMATION_MESSAGE);
		this.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(6, 6, 400, 16);

//		dataTableSjJTable.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				int i=FrmMainYH.this.dataTableSjJTable.getSelectedRow();
//				if(i<0)return;
//			}
//		});
		
//		reloadYHTable();
		
//		reloadSjTable();//初始展现商家信息
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mntmNewMenuItem) {
			reloadSjTable();
			scrollPane.add(dataTableSjJTable);
		}else if(e.getSource()==menuItem_1) {
			reloadYHTable();
			scrollPane.add(dataTableYHJTable);
		}else if (e.getSource()==menuItem_2) {
			FrmYHModifyPwd dlg=new FrmYHModifyPwd();
			dlg.setVisible(true);
		}else if (e.getSource()==menuItem_3) {
			FrmYHModifyPhonenum dlg=new FrmYHModifyPhonenum();
			dlg.setVisible(true);
		}else if (e.getSource()==menuItem_4) {
			FrmYHModifyEmail dlg=new FrmYHModifyEmail();
			dlg.setVisible(true);
		}else if (e.getSource()==menuItem_5) {
			FrmYHModifyCity dlg=new FrmYHModifyCity();
			dlg.setVisible(true);
		}else if (e.getSource()==menuItem_6) {
			FrmYHAddRegister dlg=new FrmYHAddRegister();
			dlg.setVisible(true);
		}
//		else if (e.getSource()==) {
//			
//		}else if (e.getSource()==) {
//			
//		}else if (e.getSource()==) {
//			
//		}else if (e.getSource()==) {
//			
//		}
		else {
			
		}

	}
}

