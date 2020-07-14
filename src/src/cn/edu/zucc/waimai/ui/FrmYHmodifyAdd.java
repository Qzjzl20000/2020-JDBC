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

public class FrmYHmodifyAdd extends JFrame {

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
	public FrmYHmodifyAdd() {
		
	}
	public FrmYHmodifyAdd(BeanUserAdd add) {
		this.setTitle("地址修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 414, 319);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("省");
		lblNewLabel_1.setBounds(30, 22, 61, 16);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("市");
		lblNewLabel_2.setBounds(30, 65, 61, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("区");
		lblNewLabel_3.setBounds(30, 109, 61, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_4 = new JLabel("具体地址");
		lblNewLabel_4.setBounds(17, 152, 86, 16);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("收货人");
		lblNewLabel_5.setBounds(30, 194, 61, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("联系方式");
		lblNewLabel_6.setBounds(30, 238, 61, 16);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setBounds(256, 17, 130, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(256, 60, 130, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(256, 147, 130, 26);
		panel.add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(256, 233, 130, 26);
		panel.add(textField_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(256, 104, 130, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(256, 189, 130, 26);
		panel.add(textField_4);
		
		JLabel lblNewLabel = new JLabel(add.getUser_province());
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(125, 22, 113, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel(add.getUser_city());
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(125, 65, 113, 16);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel(add.getUser_area());
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_1.setBounds(125, 109, 113, 16);
		panel.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_1_1 = new JLabel(add.getUser_add_detail());
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_1_1.setBounds(125, 152, 113, 16);
		panel.add(lblNewLabel_7_1_1);
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel(add.getUser_add_name());
		lblNewLabel_7_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_1_1_1.setBounds(125, 194, 113, 16);
		panel.add(lblNewLabel_7_1_1_1);
		
		JLabel lblNewLabel_7_1_1_1_1 = new JLabel(add.getUser_add_phonenum());
		lblNewLabel_7_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7_1_1_1_1.setBounds(125, 238, 113, 16);
		panel.add(lblNewLabel_7_1_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(135, 324, 288, 39);
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
		
		JButton Button_2 = new JButton("修改");
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
						WaiMaiUtil.userManager.modifyAdd(add, userprovince, usercity,
								userarea, useraddDetail, useraddname, useraddphone);
						JOptionPane.showMessageDialog(null, "修改地址信息成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
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
