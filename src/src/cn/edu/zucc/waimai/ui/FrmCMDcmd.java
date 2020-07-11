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
import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import javax.swing.JMenuItem;
import javax.swing.JTable;

public class FrmCMDcmd extends JFrame{

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

	private Object tblCMDTitles[]=BeanCMD.CMDtableTitles;//商家信息
	private Object CMDtableData[][];
	private DefaultTableModel tableCMDModel=new DefaultTableModel();
	private JTable dataTableCMDJTable =new JTable(tableCMDModel);
	List<BeanCMD> allCMD=null;
	
	private void reloadCMDTable(){
		try {
			allCMD=WaiMaiUtil.CMDManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		CMDtableData =new Object[allCMD.size()][BeanCMD.CMDtableTitles.length];
		for(int i=0;i<allCMD.size();i++) {
			for(int j=0;j<BeanCMD.CMDtableTitles.length;j++) {
				CMDtableData[i][j]=allCMD.get(i).getCell(j);
			}
		}
		tableCMDModel.setDataVector(CMDtableData, tblCMDTitles);
		dataTableCMDJTable.validate();
		dataTableCMDJTable.repaint();
	}

	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu_4 = new JMenu("返回");
	private JMenuItem menuItem_1 = new JMenuItem("返回主界面");
	private JMenu menu_6 = new JMenu("管理员信息");
	private JMenuItem menuItem_2 = new JMenuItem("修改当前管理员");
	private JMenuItem menuItem_4 = new JMenuItem("添加管理员");
	private JMenuItem menuItem_5 = new JMenuItem("删除管理员");
	
	
	public FrmCMDcmd() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("管理员查看");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		menuBar.add(menu_4);
		menu_4.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCMDchoice dlg=new FrmCMDchoice();
				dlg.setVisible(true);
				setVisible(false);
			}
		});
		menuBar.add(menu_6);
		menu_6.add(menuItem_2);
		menuItem_2.addActionListener((e)-> {
			FrmCMDmodifycmd dlg=new FrmCMDmodifycmd();
			dlg.setFm(this);
			dlg.setVisible(true);
			setEnabled(false);
		});
		
		menu_6.add(menuItem_4);
		menuItem_4.addActionListener((e)-> {
			FrmCMDRegister dlg=new FrmCMDRegister();
			dlg.setFm(this);
			dlg.setVisible(true);
			setEnabled(false);
		});
		menu_6.add(menuItem_5);
		menuItem_5.addActionListener((e)-> {
			
		});
		
		this.getContentPane().add(new JScrollPane(this.dataTableCMDJTable), BorderLayout.WEST);
		//JScrollPane 滚动条
	   
	
		this.reloadCMDTable();//初始展现信息
		//状态栏
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label=new JLabel("欢迎您，尊敬的"+BeanCMD.currentLoginCMD.getCMD_name()+"管理员！");
		contentPane.add(label);
		this.getContentPane().add(contentPane,BorderLayout.SOUTH);
		

		this.setVisible(true);
		
		
	}
}