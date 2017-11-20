package cn.book.ui;

import java.awt.Color;
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
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import cn.book.dao.RootDao;
import cn.book.entity.Root;
import cn.book.util.StringUtil;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * 登陆界面
 * 
 * @author Administrator
 * 
 */
public class LoginFrame {

	public JFrame frame;
	private JTextField usertextField;
	private JPasswordField passwordField;
	private RootDao rootdao = new RootDao();
	private final JLabel lblNewLabel = new JLabel(" ");

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
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public LoginFrame() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setTitle("书籍管理系统");
		frame.setResizable(false);// 设置窗口不可变
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);
		frame.setLocationRelativeTo(null);

		/**
		 * 登录操作
		 * 
		 * @pramati.username 用户名
		 * @pramati.password 密码
		 */

		JLabel rootLabel = new JLabel("用户名");
		rootLabel.setBounds(114, 124, 80, 18);
		frame.getContentPane().add(rootLabel);
		rootLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		rootLabel.setForeground(SystemColor.desktop);

		JLabel pwdLabel = new JLabel("密码");
		pwdLabel.setBounds(114, 173, 80, 18);
		frame.getContentPane().add(pwdLabel);
		pwdLabel.setForeground(SystemColor.desktop);
		pwdLabel.setFont(new Font("宋体", Font.PLAIN, 15));

		usertextField = new JTextField();
		usertextField.setBounds(204, 118, 135, 30);
		frame.getContentPane().add(usertextField);
		usertextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(204, 167, 135, 30);
		frame.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("登  录");
		btnLogin.setForeground(SystemColor.desktop);
		btnLogin.setBounds(91, 224, 93, 23);
		frame.getContentPane().add(btnLogin);

		//button Register  注册按钮
		JButton btnRegister = new JButton("重  置");
		btnRegister.setForeground(SystemColor.desktop);
		btnRegister.setBounds(243, 224, 93, 23);
		frame.getContentPane().add(btnRegister);
		lblNewLabel.setIcon(new ImageIcon("img/122.jpg"));
		lblNewLabel.setBounds(0, 0, 450, 300);
		frame.getContentPane().add(lblNewLabel);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usertextField.setText("");
				passwordField.setText("");
				// RegisterRoot window = new RegisterRoot();
				// window.frame.setVisible(true);
				// frame.dispose();
			}
		});
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String username = usertextField.getText();
				System.out.println(username);
				char[] password = passwordField.getPassword();
				String pwd = String.valueOf(password);

				System.out.println("username=" + username);
				System.out.println("password=" + pwd);
				// 第三方存存贮用户名和密码
				TempData.tempname = username;
				TempData.temppwd = pwd;

				if (StringUtil.hasLength(username) && StringUtil.hasLength(pwd)) {
					try {
						Root root = rootdao.login(username, pwd);
						System.out.println(root);
						if (root != null) {
							JOptionPane.showMessageDialog(null, "登录成功");

							MainFrame window = new MainFrame();
							window.flag = true;
							window.frame.setVisible(true);

							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "用户名或密码错误");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
				}
			}

		});
	}
}
