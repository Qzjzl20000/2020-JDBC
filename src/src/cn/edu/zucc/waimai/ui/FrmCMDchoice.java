package src.cn.edu.zucc.waimai.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmCMDchoice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCMDchoice frame = new FrmCMDchoice();
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
	public FrmCMDchoice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("点击管理骑手");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Frmqs dlg=new Frmqs();
					dlg.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		btnNewButton.setBounds(67, 192, 164, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("点击管理用户");
		btnNewButton_1.setBounds(67, 133, 164, 51);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("点击管理商家");
		btnNewButton_1_1.setBounds(67, 74, 164, 51);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FrmCMDsj dlg=new FrmCMDsj();
					dlg.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("点击查看订单");
		btnNewButton_1_1_1.setBounds(67, 15, 164, 51);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2 = new JButton("点击查看管理员");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FrmCMDcmd dlg=new FrmCMDcmd();
					dlg.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(67, 251, 164, 51);
		contentPane.add(btnNewButton_2);
	}

}
