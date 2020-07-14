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

public class FrmYHCheckOut_with extends JFrame {

	private JPanel contentPane;
	public float money=0;
	public int count=0;
	private JTextField textField;
	private JTextField textField_1;
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
	public FrmYHCheckOut_with() {
		
	}
	public FrmYHCheckOut_with(BeanUserYHQ yhq) {
		setTitle("优惠券结账");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			System.out.println(BeanUser.currentLoginUser);
			money=WaiMaiUtil.userManager.BUY_money(BeanUser.currentLoginUser,yhq.getSj_id());
		} catch (BaseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			count=WaiMaiUtil.userManager.BUY_count(BeanUser.currentLoginUser,yhq.getSj_id());
		} catch (BaseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("您选择了"+money+"元的商品");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 44, 270, 32);
		contentPane.add(lblNewLabel);
		float after=money-yhq.getYouhui_money();
		JLabel lblNewLabel_1 = new JLabel("累计共"+count+"件,优惠后需"+after+"元");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 74, 270, 32);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("使用优惠券并下单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String time=new String(textField.getText());
				String add=new String(textField_1.getText());
				try {
					WaiMaiUtil.userManager.BUY(BeanUser.currentLoginUser, money, count, time, Integer.parseInt(add), yhq);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "购买成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException | BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(200, 208, 117, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(71, 208, 117, 45);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("订单期望送达时间");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(25, 129, 115, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("订单地址编号");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(25, 164, 115, 16);
		contentPane.add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setBounds(152, 124, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(152, 159, 130, 26);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("您选择了"+yhq.getSj_id()+"商家的"+yhq.getYouhui_money()+"元优惠券");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(25, 6, 270, 32);
		contentPane.add(lblNewLabel_3);
	}
}
