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
import src.cn.edu.zucc.waimai.model.BeanUserBUYCAR;
import src.cn.edu.zucc.waimai.model.BeanUserYHQ;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmYHsjBUYCAR extends JFrame{

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

	private Object tblSjTitles[]=BeanUserBUYCAR.BUYCARtableTitles;//商家信息
	private Object SjtableData[][];
	private DefaultTableModel tableSjModel=new DefaultTableModel();
	private JTable dataTableSjJTable =new JTable(tableSjModel);
	private BeanUserBUYCAR curSj=null;
	List<BeanUserBUYCAR> allSj=null;
	
	private void reloadSjTable(){
		try {
			allSj=WaiMaiUtil.userManager.loadAllBUYCAR(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		SjtableData =new Object[allSj.size()][BeanUserBUYCAR.BUYCARtableTitles.length];
		for(int i=0;i<allSj.size();i++) {
			for(int j=0;j<BeanUserBUYCAR.BUYCARtableTitles.length;j++) {
				SjtableData[i][j]=allSj.get(i).getCell(j);
			}
		}
		tableSjModel.setDataVector(SjtableData, tblSjTitles);
		dataTableSjJTable.validate();
		dataTableSjJTable.repaint();
	}


	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_1 = new JMenu("返回");
	private JMenuItem menuItem_7_1_1 = new JMenuItem("返回主界面");
	private JMenu menu_2 = new JMenu("管理购物车");
	private JMenuItem mntmNewMenuItem_2_1_2 = new JMenuItem("查看购物车（刷新）");
	private JMenuItem mntmNewMenuItem_2_1_3 = new JMenuItem("购买");
	private JMenuItem mntmNewMenuItem_2_1_4 = new JMenuItem("删除该记录");
	public FrmYHsjBUYCAR() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统-管理商家优惠券");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_1);
		menu_1.add(menuItem_7_1_1);
		menuItem_7_1_1.addActionListener((e)-> {
			FrmMainYH dlg=new FrmMainYH();
			dlg.setVisible(true);
			setVisible(false);
		});
		
		menuBar.add(menu_2);
		menu_2.add(mntmNewMenuItem_2_1_2);
		menu_2.add(mntmNewMenuItem_2_1_3);
		menu_2.add(mntmNewMenuItem_2_1_4);
		mntmNewMenuItem_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSjTable();
			}
		});
		mntmNewMenuItem_2_1_3.addActionListener(new ActionListener() {//购买
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mntmNewMenuItem_2_1_4.addActionListener(new ActionListener() {//删除选中
			public void actionPerformed(ActionEvent e) {
				int i=FrmYHsjBUYCAR.this.dataTableSjJTable.getSelectedRow();
				if(i<0) {
					JOptionPane.showMessageDialog(null, "未选中购物车记录", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
			}
		});
		
		
		
		this.getContentPane().add(new JScrollPane(this.dataTableSjJTable), BorderLayout.CENTER);
		//JScrollPane 滚动条
	    this.dataTableSjJTable.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {//鼠标点击动作，列出右边的列表
				int i=FrmYHsjBUYCAR.this.dataTableSjJTable.getSelectedRow();
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