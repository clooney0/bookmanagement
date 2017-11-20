package cn.book.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 主界面
 * 
 * @author Administrator
 * 
 */
public class MainFrame {

	public JFrame frame;
	public boolean flag;

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
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();

		frame.getContentPane().setEnabled(false);
		frame.setTitle("书籍管理系统");
		frame.setResizable(false);// 设置不可拉动
		frame.setBounds(100, 100, 800, 500);
		frame.getContentPane().setLayout(null);// absolute layout

		frame.setLocationRelativeTo(null);// 居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);

		String path = "img/mainbk.jpg"; // 背景图片的路径
		ImageIcon background = new ImageIcon(path); // 背景图片
		JLabel label = new JLabel(background); // 把背景图片显示在一个标签里面
		label.setBounds(0, 0, this.frame.getWidth(), this.frame.getHeight());// 把标签的大小位置设置为图片刚好填充整个面板
		JPanel imagePanel = (JPanel) this.frame.getContentPane();// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明

		JLabel lblNewLabel = new JLabel("书籍管理系统");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 50));
		lblNewLabel.setBounds(199, 75, 375, 67);
		frame.getContentPane().add(lblNewLabel);

		// button UserManage 读者管理 按钮
		ImageIcon icoUser = new ImageIcon("img/mainuser.png");
		final JButton btnuser = new JButton("读者管理", icoUser);
		btnuser.setEnabled(false); 			//未登录,设置为不可用
		btnuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame window;
				try {
					window = new UserFrame();
					window.frame.setVisible(true);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}
		});
		btnuser.setFocusPainted(false);
		btnuser.setBackground(Color.PINK);
		btnuser.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnuser.setVerticalTextPosition(JButton.BOTTOM);
		btnuser.setHorizontalTextPosition(JButton.CENTER);
		btnuser.setBounds(140, 240, 125, 100);
		frame.getContentPane().add(btnuser);

		// button BookManage 书籍管理按钮
		ImageIcon icoBk = new ImageIcon("img/mainbook.png");
		final JButton btnbook = new JButton("书籍管理", icoBk);
		btnbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookFrame window = new BookFrame();
				window.frame.setVisible(true);
			}
		});
		btnbook.setEnabled(false);
		btnbook.setFocusPainted(false);
		btnbook.setBackground(Color.PINK);
		btnbook.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnbook.setVerticalTextPosition(JButton.BOTTOM);
		btnbook.setHorizontalTextPosition(JButton.CENTER);
		btnbook.setBounds(330, 240, 125, 100);
		frame.getContentPane().add(btnbook);

		// button LogManage 按钮借阅详情
		ImageIcon icoLog = new ImageIcon("img/mainlog.png");
		final JButton btnlog = new JButton("借阅详情", icoLog);
		btnlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LogFrame window = new LogFrame();
				window.frame.setVisible(true);

			}
		});
		btnlog.setEnabled(false);
		btnlog.setFocusPainted(false);
		btnlog.setBackground(Color.PINK);
		btnlog.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		btnlog.setVerticalTextPosition(JButton.BOTTOM);
		btnlog.setHorizontalTextPosition(JButton.CENTER);
		btnlog.setBounds(520, 240, 125, 100);
		frame.getContentPane().add(btnlog);

		imagePanel.setOpaque(false);
		this.frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点关闭按钮时退出

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu sysMenu = new JMenu("系统");
		sysMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(sysMenu);

		final JMenuItem loginMenuItem = new JMenuItem("登录");
		loginMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame window = new LoginFrame();
				window.frame.setVisible(true);

				frame.dispose();

			}
		});

		final JMenuItem queryItem = new JMenuItem("查看管理员");
		queryItem.setEnabled(false);

		JMenuItem exitItem = new JMenuItem("退出系统");
		exitItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.ALT_MASK));
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		queryItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.ALT_MASK));
		queryItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RootFrame window = new RootFrame();
				window.frame.setVisible(true);

			}
		});

		loginMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.ALT_MASK));
		sysMenu.add(loginMenuItem);

		final JMenuItem RegisterMenuItem = new JMenuItem("注册");
		RegisterMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				RootRegister window = new RootRegister();
				window.frame.setVisible(true);
				frame.dispose();

			}
		});
		RegisterMenuItem.setEnabled(false);
		RegisterMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				InputEvent.ALT_MASK));
		sysMenu.add(RegisterMenuItem);
		sysMenu.add(queryItem);

		final JMenuItem switchItem = new JMenuItem("切换管理员");
		switchItem.setEnabled(false);
		switchItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.ALT_MASK));
		switchItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame window = new LoginFrame();
				window.frame.setVisible(true);

				frame.dispose();

			}
		});
		sysMenu.addSeparator();// 分割线
		sysMenu.add(switchItem);
		sysMenu.addSeparator();// 分割线
		sysMenu.add(exitItem);

		JMenu helpMenu = new JMenu("帮助");
		helpMenu.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		menuBar.add(helpMenu);

		final JMenuItem aboutItem = new JMenuItem("关于");
		aboutItem.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.ALT_MASK));
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "书籍管理系统v1\nCopyright(C)2017\n\n";
				String title = "图书管理系统";
				JOptionPane.showMessageDialog(aboutItem, msg, title,
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		helpMenu.add(aboutItem);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "请先登录！");
				} else {
					loginMenuItem.setEnabled(false);
					queryItem.setEnabled(flag);
					RegisterMenuItem.setEnabled(flag);
					switchItem.setEnabled(flag);
					btnuser.setEnabled(flag);
					btnbook.setEnabled(flag);
					btnlog.setEnabled(flag);
				}
			}
		});

	}
}
