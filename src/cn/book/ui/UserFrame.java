package cn.book.ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import cn.book.dao.UserDao;
import cn.book.entity.User;
import cn.book.util.StringUtil;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class UserFrame {

	public JFrame frame;
	private JTable table;
	private JTextField nameField;
	private JButton btnQueryname;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JTextField phoneField;
	private JButton btnQueryphone;
	private JPopupMenu popupMenu;
	private JMenuItem reflushItem;
	private JMenuItem getvalueItem;
	private JLabel genderLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField idField;

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
					UserFrame window = new UserFrame();
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
	 * @throws Exception
	 */
	public UserFrame() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("书籍管理系统");
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 600);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);
		frame.setLocationRelativeTo(null);

		JLabel userLabel = new JLabel("读者信息管理");
		userLabel.setForeground(Color.RED);
		userLabel.setFont(new Font("SimSun", Font.PLAIN, 25));
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(310, 27, 190, 30);
		frame.getContentPane().add(userLabel);

		// table
		table = new JTable();
		UserDao dao = new UserDao();
		Object[][] data = null; // 声明date
		List<User> user = dao.queryUser();
		data = new Object[user.size()][4];
		for (int i = 0; i < user.size(); i++) {
			User u = user.get(i);
			data[i][0] = u.getUser_id();
			data[i][1] = u.getUser_name();
			data[i][2] = u.getUser_gender();
			data[i][3] = u.getUser_phone();

		}
		table.setModel(new DefaultTableModel(data, new String[] { "编号", "名字",
				"性别", "手机号" }));
		// scorll
		final JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(72, 74, 695, 210);
		frame.getContentPane().add(scroll);

		nameLabel = new JLabel("读者姓名");
		nameLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		nameLabel.setBounds(139, 315, 95, 25);
		frame.getContentPane().add(nameLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("SimSun", Font.PLAIN, 18));
		nameField.setBounds(282, 313, 190, 30);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		// button queryByname 姓名查询按钮
		btnQueryname = new JButton("姓名查询");
		btnQueryname.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnQueryname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = nameField.getText();
				// 创建 Dao
				UserDao dao = new UserDao();
				String name = "%" + text + "%";
				Object[][] data = null; // 声明date

				List<User> user = dao.queryUserByname(name);
				data = new Object[user.size()][4];
				for (int i = 0; i < user.size(); i++) {
					User u = user.get(i);
					data[i][0] = u.getUser_id();
					data[i][1] = u.getUser_name();
					data[i][2] = u.getUser_gender();
					data[i][3] = u.getUser_phone();

				}
				table.setModel(new DefaultTableModel(data, new String[] { "编号",
						"名字", "性别", "手机号" }));
				scroll.setViewportView(table);
			}
		});
		btnQueryname.setBounds(529, 312, 122, 30);
		frame.getContentPane().add(btnQueryname);

		phoneLabel = new JLabel("手机号");
		phoneLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		phoneLabel.setBounds(139, 368, 95, 25);
		frame.getContentPane().add(phoneLabel);

		phoneField = new JTextField();
		phoneField.setFont(new Font("SimSun", Font.PLAIN, 18));
		phoneField.setBounds(282, 366, 190, 30);
		frame.getContentPane().add(phoneField);
		phoneField.setColumns(10);

		// button queryByphone 手机查询 按钮
		btnQueryphone = new JButton("手机查询");
		btnQueryphone.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnQueryphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = phoneField.getText();
				// 创建 Dao
				UserDao dao = new UserDao();
				String phone = "%" + text + "%";
				Object[][] data = null; // 声明date

				List<User> user = dao.queryUserByphone(phone);
				data = new Object[user.size()][4];
				for (int i = 0; i < user.size(); i++) {
					User u = user.get(i);
					data[i][0] = u.getUser_id();
					data[i][1] = u.getUser_name();
					data[i][2] = u.getUser_gender();
					data[i][3] = u.getUser_phone();
				}

				table.setModel(new DefaultTableModel(data, new String[] { "编号",
						"名字", "性别", "手机号" }));
				scroll.setViewportView(table);
			}
		});

		btnQueryphone.setBounds(529, 365, 122, 30);
		frame.getContentPane().add(btnQueryphone);

		genderLabel = new JLabel("性别");
		genderLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		genderLabel.setBounds(139, 421, 95, 25);
		frame.getContentPane().add(genderLabel);

		final JRadioButton maleRadio = new JRadioButton("男");
		maleRadio.setSelected(true);
		buttonGroup.add(maleRadio);
		maleRadio.setFont(new Font("SimSun", Font.PLAIN, 18));
		maleRadio.setBounds(282, 421, 56, 25);
		frame.getContentPane().add(maleRadio);

		final JRadioButton femaleRadio = new JRadioButton("女");
		femaleRadio.setFont(new Font("SimSun", Font.PLAIN, 18));
		buttonGroup.add(femaleRadio);
		femaleRadio.setBounds(344, 421, 56, 25);
		frame.getContentPane().add(femaleRadio);

		JLabel idLabel = new JLabel("读者编号");
		idLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		idLabel.setBounds(139, 473, 95, 25);
		frame.getContentPane().add(idLabel);

		idField = new JTextField();
		idField.setEnabled(false);
		idField.setFont(new Font("SimSun", Font.PLAIN, 18));
		idField.setColumns(10);
		idField.setBounds(282, 471, 190, 30);
		frame.getContentPane().add(idField);

		// button modifyUser 修改按钮
		JButton btnUsermodify = new JButton("修改");
		btnUsermodify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 取值
					String strid = idField.getText();
					long id = Long.valueOf(strid); // String-->id
					String name = nameField.getText();
					String phone = phoneField.getText();
					String regex = "^1[3|4|5|7|8]\\d{9}$";

					boolean isNum = phone.matches(regex);
					if (isNum == false) {
						JOptionPane.showMessageDialog(null, "手机格式不正确！");
					} else {

						// 获取男女信息
						String gender = "男";
						if (femaleRadio.isSelected()) {
							gender = "女";
						}

						UserDao dao = new UserDao();
						User obj = new User(id, name, gender, phone);

						boolean isupdate = dao.updateUser(obj);
						if (isupdate) {
							// 判断是否为空
							if (StringUtil.hasLength(name)
									&& StringUtil.hasLength(phone)
									&& StringUtil.hasLength(gender)) {
								JOptionPane.showMessageDialog(null, "修改成功");

								// 刷新table
								Object[][] data = null;
								dao = new UserDao();
								List<User> user = dao.queryUser();
								data = new Object[user.size()][4];
								for (int i = 0; i < user.size(); i++) {
									User u = user.get(i);
									data[i][0] = u.getUser_id();
									data[i][1] = u.getUser_name();
									data[i][2] = u.getUser_gender();
									data[i][3] = u.getUser_phone();
								}
								table.setModel(new DefaultTableModel(
										data,
										new String[] { "编号", "名字", "性别", "手机号" }));
								scroll.setViewportView(table);

							} else {
								JOptionPane.showMessageDialog(null, "输入不能为空");
							}

						}
						/*
						 * else { // 异常在UserDao处理
						 * JOptionPane.showMessageDialog(null, "修改失败！");
						 * //手机号已存在
						 * 
						 * }
						 */

					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "数据不能为空！");
				}

			}

		});
		btnUsermodify.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnUsermodify.setBounds(539, 416, 105, 30);
		frame.getContentPane().add(btnUsermodify);

		// button addUser 添加按钮
		JButton btnUseradd = new JButton("添加");
		btnUseradd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 取值
				String name = nameField.getText();
				String phone = phoneField.getText();
				String regex = "^1[3|4|5|7|8]\\d{9}$";

				boolean isNum = phone.matches(regex);
				if (isNum == false) {
					JOptionPane.showMessageDialog(null, "手机格式不正确！");
				} else {

					// 获取男女信息
					String gender = "男";
					if (femaleRadio.isSelected()) {
						gender = "女";
					}

					UserDao dao = new UserDao();
					User obj = new User(0, name, gender, phone);

					boolean isadd = dao.addUser(obj);
					if (isadd) {
						// 判断是否为空
						if (StringUtil.hasLength(name)
								&& StringUtil.hasLength(phone)
								&& StringUtil.hasLength(gender)) {
							JOptionPane.showMessageDialog(null, "添加成功");
							// 刷新table
							Object[][] data = null;
							dao = new UserDao();
							List<User> user = dao.queryUser();
							data = new Object[user.size()][4];
							for (int i = 0; i < user.size(); i++) {
								User u = user.get(i);
								data[i][0] = u.getUser_id();
								data[i][1] = u.getUser_name();
								data[i][2] = u.getUser_gender();
								data[i][3] = u.getUser_phone();
							}
							table.setModel(new DefaultTableModel(data,
									new String[] { "编号", "名字", "性别", "手机号" }));
							scroll.setViewportView(table);

						} else {
							JOptionPane.showMessageDialog(null, "输入不能为空");
						}

					}
					/*
					 * else { //UserDao处理异常 JOptionPane.showMessageDialog(null,
					 * "添加失败"); }
					 */
				}
			}

		});

		btnUseradd.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnUseradd.setBounds(539, 470, 105, 30);
		frame.getContentPane().add(btnUseradd);

		// 右键菜单
		popupMenu = new JPopupMenu();
		popupMenu.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		addPopup(table, popupMenu);

		// 右键删除
		JMenuItem deleteItem = new JMenuItem("删除");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("删除");
				int isdel = JOptionPane.showConfirmDialog(null, "是否删除", "提示",
						JOptionPane.YES_NO_OPTION);
				System.out.println("isdel==" + isdel);

				if (isdel == 0) {

					try {
						// 确认删除
						Object object = table.getValueAt(
								table.getSelectedRow(), 0);
						UserDao dao = new UserDao();
						boolean b = dao.deleteUser((Long) object); // UserDao
																	// 处理异常
						if (b) {
							JOptionPane.showMessageDialog(null, "删除成功");
							Object[][] data = null; // 声明date
							List<User> user = dao.queryUser();
							data = new Object[user.size()][4];
							for (int i = 0; i < user.size(); i++) {
								User u = user.get(i);
								data[i][0] = u.getUser_id();
								data[i][1] = u.getUser_name();
								data[i][2] = u.getUser_gender();
								data[i][3] = u.getUser_phone();
							}

							table.setModel(new DefaultTableModel(data,
									new String[] { "编号", "名字", "性别", "手机号" }));
							scroll.setViewportView(table);
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "请在表格中选中一行,右键删除！");
					}
				}
			}
		});

		// 右键获取数值
		getvalueItem = new JMenuItem("获取该行数据");
		getvalueItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					long id = (Long) table.getValueAt(table.getSelectedRow(), 0);// get
																					// id
					idField.setText(String.valueOf(id));// set id long--->String
					String name = (String) table.getValueAt(
							table.getSelectedRow(), 1);
					nameField.setText(name); // name赋值到文本框
					String gender = (String) table.getValueAt(
							table.getSelectedRow(), 2);
					if (gender.equals("男")) { // gender 设置被选中
						maleRadio.setSelected(true);
					} else {
						femaleRadio.setSelected(true);
					}

					String phone = (String) table.getValueAt(
							table.getSelectedRow(), 3);
					phoneField.setText(phone); // phone

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请在表格中选中一行,右键取值！");
				}

			}
		});
		popupMenu.add(getvalueItem);

		// 右键刷新
		reflushItem = new JMenuItem("刷新");
		reflushItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] data = null;
				UserDao dao = new UserDao();
				List<User> user = dao.queryUser();
				data = new Object[user.size()][4];
				for (int i = 0; i < user.size(); i++) {
					User u = user.get(i);
					data[i][0] = u.getUser_id();
					data[i][1] = u.getUser_name();
					data[i][2] = u.getUser_gender();
					data[i][3] = u.getUser_phone();
				}
				table.setModel(new DefaultTableModel(data, new String[] { "编号",
						"名字", "性别", "手机号" }));
				scroll.setViewportView(table);
			}
		});
		popupMenu.add(reflushItem);
		popupMenu.add(deleteItem);

	}

	// addPopup
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
