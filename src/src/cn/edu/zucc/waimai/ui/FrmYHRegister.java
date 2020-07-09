package src.cn.edu.zucc.waimai.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.itf.IUserManager;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;
import src.cn.edu.zucc.waimai.util.BusinessException;
import src.cn.edu.zucc.waimai.util.DBUtil;
import src.cn.edu.zucc.waimai.util.DbException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FrmYHRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_3;
	private JPasswordField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYHRegister frame = new FrmYHRegister();
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
	public FrmYHRegister() {
		this.setTitle("用户注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 327, 319);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setBounds(53, 21, 61, 16);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("性  别");
		lblNewLabel_2.setBounds(53, 64, 61, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("密  码");
		lblNewLabel_3.setBounds(53, 108, 61, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("再次输入密码");
		lblNewLabel_4.setBounds(40, 151, 86, 16);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("手机号码");
		lblNewLabel_5.setBounds(53, 193, 61, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("邮  箱");
		lblNewLabel_6.setBounds(53, 237, 61, 16);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_7 = new JLabel("所在城市");
		lblNewLabel_7.setBounds(53, 279, 61, 16);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setBounds(142, 16, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 59, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(142, 103, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(142, 232, 130, 26);
		panel.add(textField_3);
		
		textField_4 = new JPasswordField();
		textField_4.setColumns(10);
		textField_4.setBounds(142, 146, 130, 26);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(142, 188, 130, 26);
		panel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(142, 274, 130, 26);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(56, 324, 277, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton Button_1 = new JButton("取消");
		Button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		Button_1.setBounds(23, 6, 117, 29);
		panel_1.add(Button_1);
		
		JButton Button_2 = new JButton("注册");
		Button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==Button_2) {
					String username=new String(textField.getText());
					String usersexS=new String(textField_1.getText());
					int usersex =Integer.parseInt(usersexS);
					String pwd1=new String(textField_2.getPassword());
					String pwd2=new String(textField_4.getPassword());
					String userphonenum=new String(textField_5.getText());
					String usere_mail=new String(textField_3.getText());
					String usercity=new String(textField_6.getText());
					try {
						BeanUser user=WaiMaiUtil.userManager.reg(username,usersex,pwd1,pwd2,userphonenum,
								usere_mail,usercity);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "注册成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		Button_2.setBounds(154, 6, 117, 29);
		panel_1.add(Button_2);
	}
}
