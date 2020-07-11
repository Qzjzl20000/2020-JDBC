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
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmYHyh extends JFrame{

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

	private Object tblYHyhTitles[]=BeanUser.UsertableTitles;//用户地址信息
	private Object YHyhtableData[][];
	private DefaultTableModel tableYHyhModel=new DefaultTableModel();
	private JTable dataTableYHyhJTable =new JTable(tableYHyhModel);
	List<BeanUser> allYHyh=null;
	
	private void reloadYHyhTable(){
		try {
			
			allYHyh=WaiMaiUtil.userManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		YHyhtableData =new Object[allYHyh.size()][BeanUser.UsertableTitles.length];
		
		for(int i=0;i<allYHyh.size();i++) {
			for(int j=0;j<BeanUser.UsertableTitles.length;j++) {
				YHyhtableData[i][j]=allYHyh.get(i).getCell(j);
				System.out.println(allYHyh.get(i).getCell(j));
			}
		}
		tableYHyhModel.setDataVector(YHyhtableData, tblYHyhTitles);
		dataTableYHyhJTable.validate();
		dataTableYHyhJTable.repaint();

		
	}

	
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_4 = new JMenu("返回");
	private JMenuItem menuItem_2 = new JMenuItem("返回主界面");
	private JMenu menu_5 = new JMenu("查看用户信息");
	private JMenuItem menuItem_7 = new JMenuItem("查看会员信息");
	private JMenuItem menuItem_6 = new JMenuItem("修改密码");
	private JMenuItem menuItem_9_1_1 = new JMenuItem("修改手机号");
	private JMenuItem menuItem_8 = new JMenuItem("修改邮箱");
	private JMenuItem menuItem_9 = new JMenuItem("修改城市");
	
	
	public FrmYHyh() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-查看用户信息");
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
				reloadYHyhTable();
			}
		});
		menu_5.add(menuItem_6);
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyPwd dlg=new FrmYHModifyPwd();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_9_1_1);
		menuItem_9_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyPhonenum dlg=new FrmYHModifyPhonenum();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_8);
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyEmail dlg=new FrmYHModifyEmail();
				dlg.setVisible(true);
			}
		});
		menu_5.add(menuItem_9);
		menuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmYHModifyCity dlg=new FrmYHModifyCity();
				dlg.setVisible(true);
			}
		});
		
		this.getContentPane().add(new JScrollPane(this.dataTableYHyhJTable), BorderLayout.CENTER);
		
		
		this.reloadYHyhTable();//初始展现用户信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanUser.currentLoginUser.getUser_name()+"用户！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}