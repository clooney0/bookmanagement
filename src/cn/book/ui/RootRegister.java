package cn.book.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import cn.book.dao.RootDao;
import cn.book.entity.Root;
import cn.book.util.StringUtil;
import java.awt.Color;

public class RootRegister {

	public JFrame frame;
	private JTextField usertextField;
	private JPasswordField pwdField;
	private JPasswordField comformPwdfield;
	private RootDao rootdao = new RootDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true); // frame
			JDialog.setDefaultLookAndFeelDecorated(true); // dialog
			SubstanceLookAndFeel.setCurrentTheme(new SubstanceCremeTheme()); // theme
			SubstanceLookAndFeel.setSkin(new CremeSkin()); // skin
			SubstanceLookAndFeel
					.setCurrentWatermark(new SubstanceBinaryWatermark()); // watermark
			SubstanceLookAndFeel
					.setCurrentButtonShaper(new StandardButtonShaper()); // button

			// SubstanceLookAndFeel.setCurrentBorderPainter(new
			// StandardBorderPainter());
			// SubstanceLookAndFeel.setCurrentGradientPainter(new
			// StandardGradientPainter());
			// SubstanceLookAndFeel.setCurrentTitlePainter(new
			// FlatTitePainter());
		} catch (Exception e) {
			System.err.println("Something went wrong!");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RootRegister window = new RootRegister();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RootRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 677, 473);
		frame.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);
		frame.setLocationRelativeTo(null);
		frame.setTitle("注册");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// frame.getContentPane().setBackground(new Color(121,83,46));
		JLabel registerLabel = new JLabel("管理员注册");
		registerLabel.setForeground(Color.RED);
		registerLabel.setBounds(248, 13, 149, 44);
		frame.getContentPane().add(registerLabel);
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setFont(new Font("SimSun", Font.BOLD, 25));

		JLabel rootLabel = new JLabel("用户名");
		rootLabel.setBounds(178, 97, 75, 25);
		frame.getContentPane().add(rootLabel);
		rootLabel.setFont(new Font("SimSun", Font.PLAIN, 18));

		usertextField = new JTextField();
		usertextField.setFont(new Font("SimSun", Font.PLAIN, 18));
		usertextField.setBounds(297, 95, 150, 30);
		frame.getContentPane().add(usertextField);
		usertextField.setColumns(10);

		JLabel pwdLabel = new JLabel("密  码");
		pwdLabel.setBounds(178, 166, 75, 25);
		frame.getContentPane().add(pwdLabel);
		pwdLabel.setFont(new Font("SimSun", Font.PLAIN, 18));

		pwdField = new JPasswordField();
		pwdField.setFont(new Font("SimSun", Font.PLAIN, 18));
		pwdField.setBounds(297, 164, 150, 30);
		frame.getContentPane().add(pwdField);

		JLabel comformLabel = new JLabel("确认密码");
		comformLabel.setBounds(178, 227, 75, 25);
		frame.getContentPane().add(comformLabel);
		comformLabel.setFont(new Font("SimSun", Font.PLAIN, 18));

		comformPwdfield = new JPasswordField();
		comformPwdfield.setFont(new Font("SimSun", Font.PLAIN, 18));
		comformPwdfield.setBounds(297, 225, 150, 30);
		frame.getContentPane().add(comformPwdfield);

		JButton btnSubmit = new JButton("提 交");
		btnSubmit.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnSubmit.setBounds(190, 334, 96, 30);
		frame.getContentPane().add(btnSubmit);

		//button 重置
		JButton btnReset = new JButton("重 置");
		btnReset.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnReset.setBounds(360, 334, 96, 30);
		frame.getContentPane().add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usertextField.setText("");
				pwdField.setText("");
				comformPwdfield.setText("");
			}
		});
		
		//button Submit  提交按钮
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usertextField.getText();
				char[] password = pwdField.getPassword();
				char[] password1 = comformPwdfield.getPassword();
				String pwd1 = String.valueOf(password1);
				String pwd = String.valueOf(password);
				if (pwd.equals(pwd1)) {
					if (StringUtil.hasLength(username)
							&& StringUtil.hasLength(pwd)) {
						try {
							Root root = rootdao.queryByName(username);
							if (root == null) {
								boolean isOk = rootdao.addRoot(username, pwd);
								if (isOk) {
									JOptionPane.showMessageDialog(null, "注册成功");
									MainFrame window = new MainFrame();
									window.frame.setVisible(true);
									frame.dispose();
								} else {
									JOptionPane.showMessageDialog(null, "注册失败");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"用户名已存在，请重新输入");
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
					}
				} else {
					JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入");
				}
			}
		});
	}

}
