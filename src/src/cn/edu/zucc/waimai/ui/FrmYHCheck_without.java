package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanUser;
import src.cn.edu.zucc.waimai.model.BeanUserBUYCAR;
import src.cn.edu.zucc.waimai.model.BeanUserYHQ;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FrmYHCheck_without extends JFrame {

	private JPanel contentPane;
	public float money=0;
	public int count=0;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYHCheckOut_with frame = new FrmYHCheckOut_with();
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
	public FrmYHCheck_without() {
		setTitle("优惠券结账");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JButton btnNewButton = new JButton("原价下单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String time=new String(textField.getText());
				String add=new String(textField_1.getText());
				String sjid=new String(textField_2.getText());
				try {
					try {
						System.out.println(BeanUser.currentLoginUser);
						money=WaiMaiUtil.userManager.BUY_money(BeanUser.currentLoginUser,Integer.parseInt(sjid));
					} catch (BaseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						count=WaiMaiUtil.userManager.BUY_count(BeanUser.currentLoginUser,Integer.parseInt(sjid));
					} catch (BaseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					WaiMaiUtil.userManager.BUYwithout(BeanUser.currentLoginUser, money, count, time, Integer.parseInt(add), Integer.parseInt(sjid));
					JOptionPane.showMessageDialog(null, "购买成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				} catch (NumberFormatException | BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(189, 129, 117, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(60, 129, 117, 45);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("订单期望送达时间");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(30, 61, 115, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("订单地址编号");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(30, 96, 115, 16);
		contentPane.add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setBounds(157, 56, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(157, 91, 130, 26);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("指定购买商家");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(29, 27, 115, 16);
		contentPane.add(lblNewLabel_2_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(156, 23, 130, 26);
		contentPane.add(textField_2);
	}
}
