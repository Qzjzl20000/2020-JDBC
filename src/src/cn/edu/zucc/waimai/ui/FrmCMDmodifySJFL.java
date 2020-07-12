package src.cn.edu.zucc.waimai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import src.cn.edu.zucc.waimai.WaiMaiUtil;
import src.cn.edu.zucc.waimai.model.BeanSj;
import src.cn.edu.zucc.waimai.model.BeanSjFL;
import src.cn.edu.zucc.waimai.util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class FrmCMDmodifySJFL extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCMDdeleteSJ frame = new FrmCMDdeleteSJ();
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
	public FrmCMDmodifySJFL() {
		
	}
	public FrmCMDmodifySJFL(BeanSjFL sjfl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("您正在修改分栏："+sjfl.getLeibie_name());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(29, 18, 216, 33);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("确认修改");
		btnNewButton.setBounds(166, 161, 112, 42);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					String sjflname=new String(textField_1.getText());
					try {
						WaiMaiUtil.CMDManager.modifySjFL(sjfl, sjflname);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "修改商家成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
					} catch (BaseException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("放弃操作");
		btnNewButton_1.setBounds(51, 161, 112, 42);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("修改分栏名");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(39, 68, 77, 21);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(128, 65, 130, 26);
		contentPane.add(textField_1);
	}

}
