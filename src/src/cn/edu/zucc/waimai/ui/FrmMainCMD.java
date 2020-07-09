package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JMenuItem;

public class FrmMainCMD extends JFrame {

	private JPanel contentPane;
	private JMenuItem menuItem_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMainCMD frame = new FrmMainCMD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMainCMD() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 656);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_1 = new JMenu("查看订单详情");
		menuBar.add(menu_1);
		
		
		JMenu menu_6 = new JMenu("管理用户信息");
		menuBar.add(menu_6);
	
		JMenu menu_5 = new JMenu("管理用户地址");
		menu_5.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu_5);
		
		
		JMenu menu_2 = new JMenu("查看商家");
		menu_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu_2);
	
		JMenu menu_3 = new JMenu("查看持有优惠券");
		menu_3.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("查看集单进度");
		menu_4.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menu_4);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("查看集单进度");
		menu_4.add(mntmNewMenuItem_1_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
	}

}
