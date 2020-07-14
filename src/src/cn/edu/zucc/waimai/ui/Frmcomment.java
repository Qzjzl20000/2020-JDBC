package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanOrder;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frmcomment extends JFrame {

	private JPanel contentPane;
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
					Frmcomment frame = new Frmcomment();
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
	public Frmcomment() {
		
	}
	public Frmcomment(BeanOrder order){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("评价内容");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(62, 40, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("评价骑手星级");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(37, 85, 86, 16);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(135, 35, 191, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 80, 191, 26);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("打赏骑手");
		label_2.setBounds(62, 125, 61, 16);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(135, 120, 191, 26);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("提交评价");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					String comment=new String(textField.getText());
					String qs_star=new String(textField_1.getText());
					String addqs_money=new String(textField_2.getText());
					try {
						WaiMaiUtil.userManager.comment(order, comment, qs_star, addqs_money);
						setVisible(false);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		btnNewButton.setBounds(238, 171, 117, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(108, 171, 117, 45);
		contentPane.add(btnNewButton_1);
	}
}
