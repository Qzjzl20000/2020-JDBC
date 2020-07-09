package src.cn.edu.zucc.waimai.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserAdd;

import src.cn.edu.zucc.waimai.util.BaseException;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FrmYHAddRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_2;
	private JTextField textField_4;

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
	public FrmYHAddRegister() {
		this.setTitle("地址添加");
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
		
		JLabel lblNewLabel_1 = new JLabel("省");
		lblNewLabel_1.setBounds(53, 21, 61, 16);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("市");
		lblNewLabel_2.setBounds(53, 64, 61, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("区");
		lblNewLabel_3.setBounds(53, 108, 61, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("具体地址");
		lblNewLabel_4.setBounds(40, 151, 86, 16);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("收货人");
		lblNewLabel_5.setBounds(53, 193, 61, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("联系方式");
		lblNewLabel_6.setBounds(53, 237, 61, 16);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setBounds(142, 16, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 59, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(142, 146, 130, 26);
		panel.add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(142, 232, 130, 26);
		panel.add(textField_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(142, 103, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(142, 188, 130, 26);
		panel.add(textField_4);
		
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
		
		JButton Button_2 = new JButton("添加");
		Button_2.setBounds(154, 6, 117, 29);
		Button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==Button_2) {
					String userprovince=new String(textField.getText());
					String usercity=new String(textField_1.getText());
					String userarea=new String(textField_2.getText());
					String useraddDetail=new String(textField_3.getText());
					String useraddname=new String(textField_4.getText());
					String useraddphone=new String(textField_5.getText());
					try {
						BeanUserAdd.currentLoginUserAdd=WaiMaiUtil.userManager.regAdd(BeanUser.currentLoginUser, userprovince, usercity,
								userarea, useraddDetail, useraddname, useraddphone);
						JOptionPane.showMessageDialog(null, "添加地址信息成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		panel_1.add(Button_2);
	}

}
