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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
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

public class RootModifyFrame {

	public JFrame frame;
	private JTextField newrootField;
	private JPasswordField newpwdField;
	private RootDao rootdao = new RootDao();
	private JPasswordField passwordField;
	private JTextField oldrootField;
	private JPasswordField oldpwdField;

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
					RootModifyFrame window = new RootModifyFrame();
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
	public RootModifyFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 677, 473);
		frame.setTitle("书籍管理系统");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);
		// frame.getContentPane().setBackground(new Color(121, 83, 46));
		frame.setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(118, 45, 1, 1);
		frame.getContentPane().add(layeredPane);

		JTabbedPane tablePane = new JTabbedPane(JTabbedPane.TOP);
		tablePane.setBounds(81, 77, 498, 294);
		frame.getContentPane().add(tablePane);

		JPanel rootnamePanel = new JPanel();
		tablePane.addTab("修改用户名", null, rootnamePanel, null);
		rootnamePanel.setLayout(null);

		newrootField = new JTextField();
		newrootField.setFont(new Font("SimSun", Font.PLAIN, 18));
		newrootField.setBounds(227, 116, 150, 30);
		rootnamePanel.add(newrootField);
		newrootField.setColumns(10);

		JLabel newrootLabel = new JLabel("新用户名");
		newrootLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		newrootLabel.setBounds(116, 117, 75, 25);
		rootnamePanel.add(newrootLabel);

		JLabel oldrootLabel = new JLabel("原用户名");
		oldrootLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		oldrootLabel.setBounds(116, 57, 75, 25);
		rootnamePanel.add(oldrootLabel);

		oldrootField = new JTextField();
		oldrootField.setFont(new Font("SimSun", Font.PLAIN, 18));
		oldrootField.setText(TempData.temproot);// /
		oldrootField.setEditable(false);
		oldrootField.setBounds(227, 56, 150, 30);

		// oldrootField.setText(TempData.temproot);
		rootnamePanel.add(oldrootField);

		oldrootField.setColumns(10);
		
		//button 提交
		JButton btnRootSubmit = new JButton("提 交");
		btnRootSubmit.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnRootSubmit.setBounds(203, 194, 93, 30);
		rootnamePanel.add(btnRootSubmit);
		btnRootSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rootname = newrootField.getText();
				System.out.println("重置的管理员名字：" + rootname);

				if (rootname != null && !("".equals(rootname))) {
					Root root = rootdao.queryByName(rootname);
					if (root == null) {

						boolean ismodify = rootdao.modifyRoot(rootname,
								TempData.id);
						if (ismodify) {
							JOptionPane.showMessageDialog(null, rootname+"用户名修改成功!");
							RootFrame window = new RootFrame();
							window.frame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, rootname+"用户名修改失败!");
						}
					} else {
						JOptionPane.showMessageDialog(null, rootname+"已存在!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "管理员名字不能为空!");
				}

			}
		});

		JPanel rootpwdPanel = new JPanel();
		tablePane.addTab("修改密码", null, rootpwdPanel, null);
		rootpwdPanel.setLayout(null);

		JLabel newpwdLabel = new JLabel("新密码");
		newpwdLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		newpwdLabel.setBounds(102, 79, 75, 25);
		rootpwdPanel.add(newpwdLabel);

		newpwdField = new JPasswordField();
		newpwdField.setFont(new Font("SimSun", Font.PLAIN, 18));
		newpwdField.setBounds(210, 78, 150, 30);
		rootpwdPanel.add(newpwdField);

		JButton btnPwdSubmit = new JButton("提 交");
		btnPwdSubmit.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnPwdSubmit.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.
			 * ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {

				char[] oldpwd = oldpwdField.getPassword();
				String oldpwd1 = String.valueOf(oldpwd);

				char[] newpwd = newpwdField.getPassword();
				String pwd = String.valueOf(newpwd);
				char[] respwd = passwordField.getPassword();
				String respwd1 = String.valueOf(respwd);

				Root root = rootdao.queryByID(TempData.id);
				System.out.println("第三方传过来的tempID的值：" + TempData.id);

				String root_password = root.getRoot_password();
				System.out.println(root_password + "用户密码");
				// if(rootdao.queryByName(TempData.temproot))
				if (root_password.equals(oldpwd1)) {
					if (StringUtil.hasLength(pwd)
							&& StringUtil.hasLength(respwd1)) {
						if (pwd.equals(respwd1)) {
							boolean isOk = rootdao.modifyPwd(pwd, TempData.id);

							if (isOk) {
								JOptionPane.showMessageDialog(null, "密码修改成功!");
								RootFrame window = new RootFrame();
								window.frame.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "密码修改失败!");
							}// /
						} else {
							JOptionPane.showMessageDialog(null,
									"密码不一致，请重新输入！");
						}// /
					} else {
						JOptionPane.showMessageDialog(null, "密码不能为空！");
					}
				} else {
					JOptionPane.showMessageDialog(null, "原始密码不正确，请重新输入!");
				}

			}
		});
		btnPwdSubmit.setBounds(200, 192, 93, 30);
		rootpwdPanel.add(btnPwdSubmit);

		JLabel confirmLabel = new JLabel("确认密码");
		confirmLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		confirmLabel.setBounds(102, 122, 75, 25);
		rootpwdPanel.add(confirmLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SimSun", Font.PLAIN, 18));
		passwordField.setBounds(210, 121, 150, 30);
		rootpwdPanel.add(passwordField);

		oldpwdField = new JPasswordField();
		oldpwdField.setFont(new Font("SimSun", Font.PLAIN, 18));
		oldpwdField.setBounds(210, 32, 150, 30);
		rootpwdPanel.add(oldpwdField);

		JLabel oldpwdLabel = new JLabel("原密码");
		oldpwdLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		oldpwdLabel.setBounds(102, 33, 75, 25);
		rootpwdPanel.add(oldpwdLabel);
		System.out.println("TempData.id======" + TempData.id);

		JLabel rootmodifyLabel = new JLabel("管理员信息修改");
		rootmodifyLabel.setForeground(Color.RED);
		rootmodifyLabel.setFont(new Font("SimSun", Font.BOLD, 25));
		rootmodifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rootmodifyLabel.setBounds(228, 27, 189, 37);
		frame.getContentPane().add(rootmodifyLabel);
	}
}
