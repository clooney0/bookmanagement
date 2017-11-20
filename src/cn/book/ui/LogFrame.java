package cn.book.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import cn.book.dao.BookDao;
import cn.book.dao.LogDao;
import cn.book.entity.Book;
import cn.book.entity.Log;
import cn.book.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import javax.swing.SwingConstants;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import java.awt.Font;
import java.awt.Color;

public class LogFrame {

	public JFrame frame;
	private JTextField usernameField;
	private JTextField borrowtimeField;
	private JTextField booknameField;
	private JTextField returntimeField;
	private String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm")
			.format(new Date());

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
					LogFrame window = new LogFrame();
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
	public LogFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("图书借阅日志");
		frame.setResizable(false);
		frame.setTitle("书籍借阅日志");
		frame.setBounds(100, 100, 850, 600);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setLocationRelativeTo(null); // 窗口居中

		JLabel logLabel = new JLabel("借阅信息管理");
		logLabel.setForeground(Color.RED);
		logLabel.setFont(new Font("SimSun", Font.PLAIN, 25));
		logLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logLabel.setBounds(310, 27, 190, 30);
		frame.getContentPane().add(logLabel);

		// table 表格
		final JTable table = new JTable();
		Object[][] data = null;// 声明data变量
		LogDao dao = new LogDao();

		List<Log> list = dao.showAllLog();
		data = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) {
			Log log = list.get(i);
			data[i][0] = log.getId();
			data[i][1] = log.getLog_username();
			data[i][2] = log.getLog_bkname();
			data[i][3] = log.getLog_bkstock();
			data[i][4] = log.getLog_brotime();
			data[i][5] = log.getLog_rtntime();
		}

		table.setModel(new DefaultTableModel(data, new String[] { "日志编号",
				"读者姓名", "书名", "书籍库存", "借书时间", "归还时间" }));

		// scroll 滚动面板
		final JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(64, 84, 710, 210);
		table.setFillsViewportHeight(true);

		frame.getContentPane().add(scroll);

		JLabel usernameLabel = new JLabel("借阅人姓名:");
		usernameLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		usernameLabel.setBounds(64, 327, 108, 25);
		frame.getContentPane().add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font("SimSun", Font.PLAIN, 18));
		usernameField.setBounds(177, 325, 156, 30);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		JLabel booknameLabel = new JLabel("书名:");
		booknameLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		booknameLabel.setBounds(361, 328, 55, 22);
		frame.getContentPane().add(booknameLabel);

		booknameField = new JTextField();
		booknameField.setFont(new Font("SimSun", Font.PLAIN, 18));
		booknameField.setColumns(10);
		booknameField.setBounds(430, 325, 337, 30);
		frame.getContentPane().add(booknameField);

		JLabel borrowtimeLabel = new JLabel("借书时间:");
		borrowtimeLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		borrowtimeLabel.setBounds(64, 386, 95, 25);
		frame.getContentPane().add(borrowtimeLabel);

		borrowtimeField = new JTextField(currentTime);
		borrowtimeField.setFont(new Font("SimSun", Font.PLAIN, 18));
		borrowtimeField.setColumns(10);
		borrowtimeField.setBounds(161, 384, 317, 30);
		frame.getContentPane().add(borrowtimeField);

		JLabel returntimeLabel = new JLabel("归还时间:");
		returntimeLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		returntimeLabel.setBounds(64, 443, 95, 25);
		frame.getContentPane().add(returntimeLabel);

		returntimeField = new JTextField();
		returntimeField.setFont(new Font("SimSun", Font.PLAIN, 18));
		returntimeField.setBounds(161, 441, 317, 30);
		frame.getContentPane().add(returntimeField);
		returntimeField.setColumns(10);

		// button Borrow Record 借阅登记按钮
		JButton btnBorrow = new JButton("借阅登记");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText(); // 取值
				String bookname = booknameField.getText();
				String borrowtime = borrowtimeField.getText();
				if (borrowtime.equals("")) {
					int confirmtime = JOptionPane.showConfirmDialog(null,
							"借阅时间默认" + currentTime + "?", "提示",
							JOptionPane.YES_NO_OPTION);
					if (confirmtime == JOptionPane.YES_OPTION) {
						borrowtime = currentTime;
					}
				}

				if (StringUtil.hasLength(username)
						&& StringUtil.hasLength(bookname)
						&& StringUtil.hasLength(borrowtime)) {

					LogDao logdao = new LogDao();

					Log log = new Log(0, username, bookname, 0, borrowtime,
							null);
					boolean isaddLog = logdao.addLog(log); // LogDao 里处理异常
					System.out.println("添加日志===" + isaddLog);
					if (isaddLog) { // 添加 username,bookname,borrowtime 到t_log表成功
						boolean isReduceStock = logdao
								.reduceBookStock(bookname);
						System.out.println("库存-1 ？" + isReduceStock);
						if (isReduceStock) { // t_book 库存-1 成功
							BookDao bookdao = new BookDao();
							List<Book> booklist = bookdao
									.queryBookByBookname(bookname);

							long bookStock = booklist.get(0).getBook_stock();
							// long bookId = booklist.get(0).getBook_id();

							System.out.println("bookStock ==="
									+ String.valueOf(bookStock));

							log = new Log(0, null, bookname, bookStock, null,
									null);
							// 赋值到t_log 表的stock图书库存
							boolean isSetValue = logdao.updateLogStock(log);
							System.out.println("bookstock赋值到t_log ?"
									+ isSetValue);
							if (isSetValue) {
								JOptionPane.showMessageDialog(null,
										"借阅登记成功,库存-1");
								Object[][] data = null;
								List<Log> list = logdao.showAllLog();
								data = new Object[list.size()][6];
								for (int i = 0; i < list.size(); i++) {
									log = list.get(i);
									data[i][0] = log.getId();
									data[i][1] = log.getLog_username();
									data[i][2] = log.getLog_bkname();
									data[i][3] = log.getLog_bkstock();
									data[i][4] = log.getLog_brotime();
									data[i][5] = log.getLog_rtntime();
								}

								table.setModel(new DefaultTableModel(data,
										new String[] { "日志编号", "读者姓名", "书名",
												"书籍库存", "借书时间", "归还时间" }));
								scroll.setViewportView(table);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "数据不能为空");
				}

			}
		});
		btnBorrow.setFont(new Font("SimSun", Font.PLAIN, 18));

		btnBorrow.setBounds(521, 383, 115, 30);
		frame.getContentPane().add(btnBorrow);

		// button Return Record 归还登记 按钮
		JButton btnReturn = new JButton("归还登记");
		btnReturn.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long id = (Long) table.getValueAt(table.getSelectedRow(), 0);
					String username = usernameField.getText();
					String bookname = booknameField.getText();
					String borrowtime = borrowtimeField.getText();
					String returntime = returntimeField.getText();

					int isupdate = JOptionPane.showConfirmDialog(null, "是否归还?",
							"提示", JOptionPane.YES_NO_OPTION);
					if (isupdate == JOptionPane.YES_OPTION) {
						if (StringUtil.hasLength(username)
								&& StringUtil.hasLength(bookname)
								&& StringUtil.hasLength(borrowtime)
								&& StringUtil.hasLength(returntime)) {

							LogDao dao = new LogDao();

							Log log = new Log(id, username, bookname, 0,
									borrowtime, returntime);
							System.out.println(log);

							boolean flag = dao.updateLog(log);
							System.out.println("归还成功?===" + flag);
							if (flag) {

								boolean isAddStock = dao.addBookStock(bookname);

								// 添加库存到 t_book
								System.out.println("添加库存+1?" + isAddStock);

								dao.updateLogStock(log);

								if (isAddStock) {
									BookDao bookdao = new BookDao();
									List<Book> booklist = bookdao
											.queryBookByBookname(bookname);

									long bookStock = booklist.get(0)
											.getBook_stock();// 获取t_book里的库存
									System.out.println("t_book库存=="
											+ String.valueOf(bookStock));

									log = new Log(id, null, bookname,
											bookStock, null, null);// id,bookStock
									System.out.println("bookStock ==="
											+ String.valueOf(bookStock));

									LogDao logdao = new LogDao();
									// Object obj =
									// logdao.queryStockNull(username,
									// bookname);

									boolean isSetValue = logdao
											.updateLogStock(log);

									System.out.println("isSetValue?"
											+ isSetValue);

									if (isSetValue) { // t_log 库存更新成功

										JOptionPane.showMessageDialog(null,
												"归还成功,库存+1");

										// table刷新
										Object[][] data = null;// 声明data变量
										List<Log> list = dao.showAllLog();
										data = new Object[list.size()][6];
										for (int i = 0; i < list.size(); i++) {
											log = list.get(i);
											data[i][0] = log.getId();
											data[i][1] = log.getLog_username();
											data[i][2] = log.getLog_bkname();
											data[i][3] = log.getLog_bkstock();
											data[i][4] = log.getLog_brotime();
											data[i][5] = log.getLog_rtntime();
										}

										table.setModel(new DefaultTableModel(
												data, new String[] { "日志编号",
														"读者姓名", "书名", "书籍库存",
														"借书时间", "归还时间" }));
										scroll.setViewportView(table);

									}

								}

							}
						} else {
							JOptionPane.showMessageDialog(null, "数据不能为空！");
						}
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据不能为空！");
				}

			}
		});
		btnReturn.setBounds(521, 440, 115, 30);
		frame.getContentPane().add(btnReturn);

		// button Modify 修改按钮
		JButton btnModify = new JButton("修改");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long id = (Long) table.getValueAt(table.getSelectedRow(), 0);
					String username = usernameField.getText();
					String bookname = booknameField.getText();
					String borrowtime = borrowtimeField.getText();
					String returntime = returntimeField.getText();

					int isupdate = JOptionPane.showConfirmDialog(null, "是否修改?",
							"提示", JOptionPane.YES_NO_OPTION);
					if (isupdate == JOptionPane.YES_OPTION) {
						if (StringUtil.hasLength(username)
								&& StringUtil.hasLength(bookname)
								&& StringUtil.hasLength(borrowtime)) {

							LogDao dao = new LogDao();

							Log log = new Log(id, username, bookname, 0,
									borrowtime, returntime);
							boolean flag = dao.updateLog(log);// 修改日志
							System.out.println("isupdate?" + flag);
							if (flag) { // flag == true
								JOptionPane.showMessageDialog(null, "修改成功");
								// table刷新
								Object[][] data = null;// 声明data变量
								List<Log> list = dao.showAllLog();
								data = new Object[list.size()][6];
								for (int i = 0; i < list.size(); i++) {
									log = list.get(i);
									data[i][0] = log.getId();
									data[i][1] = log.getLog_username();
									data[i][2] = log.getLog_bkname();
									data[i][3] = log.getLog_bkstock();
									data[i][4] = log.getLog_brotime();
									data[i][5] = log.getLog_rtntime();
								}

								table.setModel(new DefaultTableModel(data,
										new String[] { "日志编号", "读者姓名", "书名",
												"书籍库存", "借书时间", "归还时间" }));
								scroll.setViewportView(table);
							} else {
								JOptionPane.showMessageDialog(null, "修改失败！！");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "数据不能为空！");
					}
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "数据不能为空！");
				}

			}

		});
		btnModify.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnModify.setBounds(659, 411, 95, 30);
		frame.getContentPane().add(btnModify);

		// button Query 按钮借阅人查询
		JButton btnQueryusername = new JButton("姓名查询\r\n");
		btnQueryusername.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnQueryusername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getusername = usernameField.getText();
				if (getusername.equals("")) {
					JOptionPane.showMessageDialog(null, "借阅人不能为空！");
				} else {
					String username = "%" + getusername + "%";

					LogDao dao = new LogDao();
					Log log = new Log(0, username, null, 0, null, null);
					Object[][] data = null;
					List<Log> list = dao.queryLogByusername(log);

					data = new Object[list.size()][6];
					for (int i = 0; i < list.size(); i++) {
						log = list.get(i);
						data[i][0] = log.getId();
						data[i][1] = log.getLog_username();
						data[i][2] = log.getLog_bkname();
						data[i][3] = log.getLog_bkstock();
						data[i][4] = log.getLog_brotime();
						data[i][5] = log.getLog_rtntime();
					}

					table.setModel(new DefaultTableModel(data, new String[] {
							"日志编号", "读者姓名", "书名", "书籍库存", "借书时间", "归还时间" }));
					scroll.setViewportView(table);

				}
			}
		});
		btnQueryusername.setBounds(521, 495, 115, 30);
		frame.getContentPane().add(btnQueryusername);

		// button QuerybyBookname 按钮书名查询
		JButton btnQuerybookname = new JButton("书名查询\r\n");
		btnQuerybookname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getbookname = booknameField.getText();
				if (getbookname.equals("")) {
					JOptionPane.showMessageDialog(null, "书名不能为空！");
				} else {

					String bookname = "%" + getbookname + "%";

					LogDao dao = new LogDao();
					Log log = new Log(0, null, bookname, 0, null, null);
					Object[][] data = null;
					List<Log> list = dao.queryLogBybookname(log);

					data = new Object[list.size()][6];
					for (int i = 0; i < list.size(); i++) {
						log = list.get(i);
						data[i][0] = log.getId();
						data[i][1] = log.getLog_username();
						data[i][2] = log.getLog_bkname();
						data[i][3] = log.getLog_bkstock();
						data[i][4] = log.getLog_brotime();
						data[i][5] = log.getLog_rtntime();
					}

					table.setModel(new DefaultTableModel(data, new String[] {
							"日志编号", "读者姓名", "书名", "书籍库存", "借书时间", "归还时间" }));
					scroll.setViewportView(table);

				}
			}
		});
		btnQuerybookname.setFont(new Font("SimSun", Font.PLAIN, 18));
		btnQuerybookname.setBounds(659, 495, 115, 30);
		frame.getContentPane().add(btnQuerybookname);

		// 右键菜单
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		// 右键取值
		JMenuItem getvalueItem = new JMenuItem("获取该行数据"); // 取值
		getvalueItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = (String) table.getValueAt(
							table.getSelectedRow(), 1); // 获取表内的名字
					usernameField.setText(username); // 把名字赋值给文本框
					String bookname = (String) table.getValueAt(
							table.getSelectedRow(), 2);
					booknameField.setText(bookname);
					String borrowtime = (String) table.getValueAt(
							table.getSelectedRow(), 4);
					borrowtimeField.setText(borrowtime);
					String returntime = (String) table.getValueAt(
							table.getSelectedRow(), 5);
					if (returntime.equals("")) {
						returntimeField.setText(currentTime);
					} else {
						returntimeField.setText(returntime);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "表格中选中一行,右键取值！");
				}
			}

		});
		popupMenu.add(getvalueItem);

		// 右键删除
		JMenuItem deleteItem = new JMenuItem("删除");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isdel = JOptionPane.showConfirmDialog(null, "是否删除?", "提示",
						JOptionPane.YES_NO_OPTION);

				if (isdel == JOptionPane.YES_OPTION) {
					try {

						Object obj = table.getValueAt(table.getSelectedRow(), 0);
						System.out.println("logid===" + obj);
						LogDao dao = new LogDao();

						boolean flag = dao.deleteLog((Long) obj);
						if (flag) {
							JOptionPane.showMessageDialog(null, "删除成功");
							// ---------数据库 table刷新数据开始--------------
							Object[][] data = null;// 声明data变量

							List<Log> list = dao.showAllLog();
							data = new Object[list.size()][6];
							for (int i = 0; i < list.size(); i++) {
								Log log = list.get(i);
								data[i][0] = log.getId();
								data[i][1] = log.getLog_username();
								data[i][2] = log.getLog_bkname();
								data[i][3] = log.getLog_bkstock();
								data[i][4] = log.getLog_brotime();
								data[i][5] = log.getLog_rtntime();
							}

							table.setModel(new DefaultTableModel(data,
									new String[] { "日志编号", "读者姓名", "书名",
											"书籍库存", "借书时间", "归还时间" }));

							scroll.setViewportView(table);

						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "表格中选中一行,右键删除！");
					}
				}
			}
		});

		// 右键刷新
		JMenuItem reflushItem = new JMenuItem("刷新");
		reflushItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object[][] data = null;// 声明data变量
				LogDao dao = new LogDao();

				List<Log> list = dao.showAllLog();
				data = new Object[list.size()][6];
				for (int i = 0; i < list.size(); i++) {
					Log log = list.get(i);
					data[i][0] = log.getId();
					data[i][1] = log.getLog_username();
					data[i][2] = log.getLog_bkname();
					data[i][3] = log.getLog_bkstock();
					data[i][4] = log.getLog_brotime();
					data[i][5] = log.getLog_rtntime();
				}

				table.setModel(new DefaultTableModel(data, new String[] {
						"日志编号", "读者姓名", "书名", "书籍库存", "借书时间", "归还时间" }));
				scroll.setViewportView(table);

			}
		});
		popupMenu.add(reflushItem);
		popupMenu.add(deleteItem);
		
		JLabel dataLabel = new JLabel(currentTime);
		dataLabel.setForeground(Color.RED);
		dataLabel.setFont(new Font("SimSun", Font.PLAIN, 20));
		dataLabel.setBounds(161, 495, 317, 30);
		frame.getContentPane().add(dataLabel);

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
