package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmCMDRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCMDRegister frame = new FrmCMDRegister();
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
	public FrmCMDRegister() {
		this.setTitle("系统管理员注册");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NewLabel_1 = new JLabel("管理员认证：");
		NewLabel_1.setBounds(27, 23, 96, 16);
		contentPane.add(NewLabel_1);
		
		JLabel NewLabel_1_1 = new JLabel("管理员名");
		NewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		NewLabel_1_1.setBounds(83, 56, 61, 16);
		contentPane.add(NewLabel_1_1);
		
		JLabel NewLabel_1_2 = new JLabel("管理员密码");
		NewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		NewLabel_1_2.setBounds(71, 97, 73, 16);
		contentPane.add(NewLabel_1_2);
		
		JLabel NewLabel_2 = new JLabel("新建管理员：");
		NewLabel_2.setBounds(27, 149, 96, 16);
		contentPane.add(NewLabel_2);
		
		JLabel NewLabel_1_1_1 = new JLabel("新管理员名");
		NewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		NewLabel_1_1_1.setBounds(71, 182, 73, 16);
		contentPane.add(NewLabel_1_1_1);
		
		JLabel NewLabel_1_2_1 = new JLabel("新管理员密码");
		NewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		NewLabel_1_2_1.setBounds(48, 223, 96, 16);
		contentPane.add(NewLabel_1_2_1);
		
		textField = new JTextField();
		textField.setBounds(154, 51, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(154, 92, 130, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(154, 177, 130, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(154, 218, 130, 26);
		contentPane.add(textField_3);
		
		JButton btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setBounds(202, 266, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.setBounds(83, 266, 117, 29);
		contentPane.add(btnNewButton_2);
	}

}
