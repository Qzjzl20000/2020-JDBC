package src.cn.edu.zucc.waimai.ui;

import java.awt.EventQueue;


import javax.swing.border.EmptyBorder;



import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanCMD;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {
	private JPanel contentPane;
	private JTextField textField_2;
	private JPasswordField textField_1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin fmLogin=new FrmLogin();
					fmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FrmLogin() {
		
		setTitle("登陆系统");
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 310);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 0, 437, 284);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("密         码");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(83, 163, 87, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("账         号");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(83, 116, 87, 16);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(211, 111, 136, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(211, 158, 136, 26);
		panel.add(textField_1);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(284, 230, 117, 29);
		panel.add(button_1);
		
		JButton button_2 = new JButton("用户登陆");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid=textField_2.getText();
				String pwd=new String(textField_1.getPassword());
				try {
					BeanUser.currentLoginUser= WaiMaiUtil.userManager.login(userid, pwd);
					FrmMainYH dlg=new FrmMainYH();
					dlg.setVisible(true);
					JOptionPane.showMessageDialog(null, "登陆成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		button_2.setBounds(165, 216, 117, 29);
		panel.add(button_2);
		
		JButton button_2_1 = new JButton("管理员登陆");
		button_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userid=textField_2.getText();
				String pwd=new String(textField_1.getPassword());
				try {
					BeanCMD.currentLoginCMD=WaiMaiUtil.CMDManager.login(userid, pwd);
					FrmCMDchoice dlg=new FrmCMDchoice();
					dlg.setVisible(true);
					JOptionPane.showMessageDialog(null, "登陆成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		button_2_1.setBounds(165, 241, 117, 29);
		panel.add(button_2_1);
		
		JButton button_3 = new JButton("用户注册");
		button_3.addActionListener((e)-> {
				FrmYHRegister dlg=new FrmYHRegister();
				dlg.setFm(this);
				dlg.setVisible(true);
				setEnabled(false);
		});
		button_3.setBounds(43, 232, 117, 29);
		panel.add(button_3);
		
		JLabel lblNewLabel = new JLabel("欢迎使用菜地地外卖系统");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(84, 20, 263, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("ZUCC 金哲仑 31801104");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(lblNewLabel_4.getFont().deriveFont(lblNewLabel_4.getFont().getSize() - 3f));
		lblNewLabel_4.setBounds(137, 53, 156, 16);
		panel.add(lblNewLabel_4);
		
		
		
		this.setVisible(true);
		
	}
}
