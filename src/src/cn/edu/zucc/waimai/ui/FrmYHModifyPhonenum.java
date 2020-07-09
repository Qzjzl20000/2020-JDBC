package src.cn.edu.zucc.waimai.ui;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmYHModifyPhonenum extends JFrame {

	private JPanel contentPane;
	private JTextField TextField;
	private JTextField TextField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmYHModifyPhonenum frame = new FrmYHModifyPhonenum();
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
	public FrmYHModifyPhonenum() {
		this.setTitle("用户手机修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("旧手机：");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(107, 48, 61, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("新手机：");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(107, 112, 61, 16);
		contentPane.add(label_1);
		
		TextField = new JTextField();
		TextField.setBounds(180, 43, 170, 26);
		contentPane.add(TextField);
		
		TextField_1 = new JTextField();
		TextField_1.setBounds(180, 107, 170, 26);
		contentPane.add(TextField_1);
		
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					String opwd=new String(TextField.getText());
					String pwd1=new String(TextField_1.getText());
					try {
						WaiMaiUtil.userManager.changePhonenum(BeanUser.currentLoginUser,opwd,pwd1);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "修改手机号码成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
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
