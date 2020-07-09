package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmYHModifyPwd extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYHModifyPwd frame = new FrmYHModifyPwd();
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
	public FrmYHModifyPwd() {
		this.setTitle("用户密码修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("旧密码：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(107, 48, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("新密码：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(107, 112, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("第二次输入新密码：");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(51, 176, 117, 16);
		contentPane.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 43, 170, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(180, 107, 170, 26);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(180, 171, 170, 26);
		contentPane.add(passwordField_2);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					String opwd=new String(passwordField.getPassword());
					String pwd1=new String(passwordField_1.getPassword());
					String pwd2=new String(passwordField_2.getPassword());
					try {
						WaiMaiUtil.userManager.changePwd(BeanUser.currentLoginUser,opwd,pwd1,pwd2);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "修改密码成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		button.setBounds(282, 228, 117, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button_1.setBounds(163, 228, 117, 29);
		contentPane.add(button_1);
	}
}
