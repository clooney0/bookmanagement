package cn.book.ui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import cn.book.dao.BookDao;
import cn.book.entity.Book;
import cn.book.util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;
import java.awt.Color;

public class BookFrame {

	public JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField booknameField;
	private JLabel bookstockLabel;
	private JTextField bookstockField;
	private JLabel categoryLabel;
	private JTextField categoryField;
	private JLabel locationLabel;
	private JTextField locationField;
	private JLabel label;
	private JScrollPane textScroll;
	private JMenuItem deleteItem;

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
					BookFrame window = new BookFrame();
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
	public BookFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("书籍管理系统");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1032, 698);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);

		frame.setLocationRelativeTo(null);

		JLabel bookLabel = new JLabel("书籍详情管理");
		bookLabel.setForeground(Color.RED);
		bookLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookLabel.setFont(new Font("SimSun", Font.PLAIN, 25));
		bookLabel.setBounds(392, 24, 207, 45);
		frame.getContentPane().add(bookLabel);

		final JScrollPane scroll = new JScrollPane();
		scroll.setBounds(70, 82, 878, 217);
		frame.getContentPane().add(scroll);

		table = new JTable();
		table.setFont(new Font("SimSun", Font.PLAIN, 16));
		// 创建BookDao包
		BookDao dao = new BookDao();
		Object[][] data = null; // 声明date
		List<Book> book = dao.queryallbook();
		data = new Object[book.size()][6];
		for (int i = 0; i < book.size(); i++) {
			Book u = book.get(i);
			data[i][0] = u.getBook_id();
			data[i][1] = u.getBook_name();
			data[i][2] = u.getBook_stock();
			data[i][3] = u.getBook_category();
			data[i][4] = u.getBook_detail();
			data[i][5] = u.getBook_location();

		}
		table.setModel(new DefaultTableModel(data, new String[] { "编号", "书名",
				"库存", "类型", "详情", "位置" }));
		scroll.setViewportView(table);

		textField = new JTextField();
		textField.setFont(new Font("SimSun", Font.PLAIN, 18));
		textField.setText("请输入关键字查询");
		textField.setBounds(70, 338, 200, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		// button Query 查询按钮
		JButton btnQuery = new JButton(" 查 询");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BookDao dao = new BookDao();
				String input = textField.getText();// 获取输入框字段
				Object[][] data = null; // 声明date
				List<Book> list = null;
				String name = "%" + input + "%";
				list = dao.queryBookByLike(name);
				data = new Object[list.size()][6];
				for (int i = 0; i < list.size(); i++) {
					Book u = list.get(i);
					data[i][0] = u.getBook_id();
					data[i][1] = u.getBook_name();
					data[i][2] = u.getBook_stock();
					data[i][3] = u.getBook_category();
					data[i][4] = u.getBook_detail();
					data[i][5] = u.getBook_location();

				}
				table.setModel(new DefaultTableModel(data, new String[] { "编号",
						"书名", "库存", "类型", "详情", "位置" }));
				scroll.setViewportView(table);
			}
		});

		btnQuery.setFont(new Font("宋体", Font.BOLD, 20));
		btnQuery.setBounds(313, 335, 107, 35);
		frame.getContentPane().add(btnQuery);

		JLabel booknameLabel = new JLabel("书名");
		booknameLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		booknameLabel.setBounds(70, 415, 69, 25);
		frame.getContentPane().add(booknameLabel);

		booknameField = new JTextField();
		booknameField.setFont(new Font("SimSun", Font.PLAIN, 18));
		booknameField.setBounds(157, 412, 200, 32);
		frame.getContentPane().add(booknameField);
		booknameField.setColumns(10);

		bookstockLabel = new JLabel("库存");
		bookstockLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		bookstockLabel.setBounds(70, 473, 69, 25);
		frame.getContentPane().add(bookstockLabel);

		bookstockField = new JTextField();
		bookstockField.setFont(new Font("SimSun", Font.PLAIN, 18));
		bookstockField.setColumns(10);
		bookstockField.setBounds(157, 470, 200, 32);
		frame.getContentPane().add(bookstockField);

		categoryLabel = new JLabel("类型");
		categoryLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		categoryLabel.setBounds(70, 529, 69, 25);
		frame.getContentPane().add(categoryLabel);

		categoryField = new JTextField();
		categoryField.setFont(new Font("SimSun", Font.PLAIN, 18));
		categoryField.setColumns(10);
		categoryField.setBounds(157, 526, 200, 32);
		frame.getContentPane().add(categoryField);

		locationLabel = new JLabel("位置");
		locationLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		locationLabel.setBounds(70, 585, 69, 25);
		frame.getContentPane().add(locationLabel);

		locationField = new JTextField();
		locationField.setFont(new Font("SimSun", Font.PLAIN, 18));
		locationField.setColumns(10);
		locationField.setBounds(157, 582, 200, 32);
		frame.getContentPane().add(locationField);

		label = new JLabel("详情");
		label.setFont(new Font("SimSun", Font.PLAIN, 18));
		label.setBounds(392, 415, 69, 25);
		frame.getContentPane().add(label);

		textScroll = new JScrollPane();
		textScroll.setBounds(443, 416, 367, 180);
		frame.getContentPane().add(textScroll);

		final JTextArea detailTextArea = new JTextArea();
		detailTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textScroll.setViewportView(detailTextArea);

		// button Add 添加按钮
		JButton btnAddbook = new JButton("添加");
		btnAddbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// 取值
					String bookname = booknameField.getText();
					String strbookstock = bookstockField.getText();
					if (strbookstock.equals("")) {
						JOptionPane.showMessageDialog(null, "数据不能为空！");
					} else {
						long bookstock = Long.parseLong(strbookstock);
						String category = categoryField.getText();
						String detail = detailTextArea.getText();
						String location = locationField.getText();

						if (StringUtil.hasLength(bookname) && bookstock > 0
								&& StringUtil.hasLength(category)
								&& StringUtil.hasLength(detail)
								&& StringUtil.hasLength(location)) {

							Book book = new Book(0, bookname, bookstock,
									category, detail, location);
							BookDao dao = new BookDao();
							boolean flag = dao.addBook(book);
							if (flag) {
								JOptionPane.showMessageDialog(null, "添加成功");
								// reflush
								Object[][] data = null; // 声明date
								List<Book> list = dao.queryallbook();
								data = new Object[list.size()][6];
								for (int i = 0; i < list.size(); i++) {
									Book u = list.get(i);
									data[i][0] = u.getBook_id();
									data[i][1] = u.getBook_name();
									data[i][2] = u.getBook_stock();
									data[i][3] = u.getBook_category();
									data[i][4] = u.getBook_detail();
									data[i][5] = u.getBook_location();

								}
								table.setModel(new DefaultTableModel(data,
										new String[] { "编号", "书名", "库存", "类型",
												"详情", "位置" }));
								scroll.setViewportView(table);

							} else {
								JOptionPane.showMessageDialog(null, "添加失败");
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"数据不能为空,库存不能<=0！");
						}
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "库存只能为数字！");
				}
			}
		});
		btnAddbook.setFont(new Font("SimSun", Font.BOLD, 20));
		btnAddbook.setBounds(841, 415, 107, 35);
		frame.getContentPane().add(btnAddbook);

		// button Modify 修改按钮
		JButton btnModifybook = new JButton("修改");
		btnModifybook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// 取值
					long id = (Long) table.getValueAt(table.getSelectedRow(), 0);// id
					String bookname = booknameField.getText();
					String strbookstock = bookstockField.getText();
					long bookstock = Long.parseLong(strbookstock);
					String category = categoryField.getText();
					String detail = detailTextArea.getText();
					String location = locationField.getText();

					if (bookstock <= 0) {
						JOptionPane.showMessageDialog(null, "库存必须>0!");
					} else {

						Book book = new Book(id, bookname, bookstock, category,
								detail, location);
						BookDao dao = new BookDao();
						boolean isupdate = dao.updateBook(book);
						if (isupdate == true) {
							JOptionPane.showMessageDialog(null, "修改成功");
							// reflush
							Object[][] data = null; // 声明date
							List<Book> list = dao.queryallbook();
							data = new Object[list.size()][6];
							for (int i = 0; i < list.size(); i++) {
								Book u = list.get(i);
								data[i][0] = u.getBook_id();
								data[i][1] = u.getBook_name();
								data[i][2] = u.getBook_stock();
								data[i][3] = u.getBook_category();
								data[i][4] = u.getBook_detail();
								data[i][5] = u.getBook_location();

							}
							table.setModel(new DefaultTableModel(data,
									new String[] { "编号", "书名", "库存", "类型",
											"详情", "位置" }));
							scroll.setViewportView(table);

						} else {
							JOptionPane.showMessageDialog(null, "修改失败！");
						}
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "数据不能为空！");
				}

			}
		});
		btnModifybook.setFont(new Font("SimSun", Font.BOLD, 20));
		btnModifybook.setBounds(841, 493, 107, 35);
		frame.getContentPane().add(btnModifybook);

		// 右键菜单
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);

		// 右键获取数据
		JMenuItem getvalueItem = new JMenuItem("获取该行数据");
		getvalueItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String bookname = (String) table.getValueAt(
							table.getSelectedRow(), 1); // bookname
					booknameField.setText(bookname);
					long bookstock = (Long) table.getValueAt(
							table.getSelectedRow(), 2);
					bookstockField.setText(String.valueOf(bookstock));
					String bookcategory = (String) table.getValueAt(
							table.getSelectedRow(), 3);
					categoryField.setText(bookcategory);
					String bookdetail = (String) table.getValueAt(
							table.getSelectedRow(), 4);
					detailTextArea.setText(bookdetail);
					String booklocation = (String) table.getValueAt(
							table.getSelectedRow(), 5);
					locationField.setText(booklocation);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "表格中选中一行,右键取值！");
				}
			}
		});
		popupMenu.add(getvalueItem);

		// 右键刷新
		JMenuItem reflushItem = new JMenuItem("刷新");
		reflushItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookDao dao = new BookDao();
				Object[][] data = null; // 声明date
				List<Book> book = dao.queryallbook();
				data = new Object[book.size()][6];
				for (int i = 0; i < book.size(); i++) {
					Book u = book.get(i);
					data[i][0] = u.getBook_id();
					data[i][1] = u.getBook_name();
					data[i][2] = u.getBook_stock();
					data[i][3] = u.getBook_category();
					data[i][4] = u.getBook_detail();
					data[i][5] = u.getBook_location();

				}
				table.setModel(new DefaultTableModel(data, new String[] { "编号",
						"书名", "库存", "类型", "详情", "位置" }));
				scroll.setViewportView(table);

			}
		});
		popupMenu.add(reflushItem);

		// 右键删除
		deleteItem = new JMenuItem("删除");
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isdel = JOptionPane.showConfirmDialog(null, "是否删除?", "提示",
						JOptionPane.YES_NO_OPTION);
				if (isdel == JOptionPane.YES_OPTION) {
					try {
						Object obj = table.getValueAt(table.getSelectedRow(), 0);
						System.out.println(obj);
						BookDao dao = new BookDao();

						boolean flag = dao.deleteBook((Long) obj);
						if (flag) {
							JOptionPane.showMessageDialog(null, "删除成功");
							// 刷新table
							Object[][] data = null;// 声明data变量

							List<Book> book = dao.queryallbook();
							data = new Object[book.size()][6];
							for (int i = 0; i < book.size(); i++) {
								Book u = book.get(i);
								data[i][0] = u.getBook_id();
								data[i][1] = u.getBook_name();
								data[i][2] = u.getBook_stock();
								data[i][3] = u.getBook_category();
								data[i][4] = u.getBook_detail();
								data[i][5] = u.getBook_location();
							}
							table.setModel(new DefaultTableModel(data,
									new String[] { "编号", "书名", "库存", "类型",
											"详情", "位置" }));
							scroll.setViewportView(table);

						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}

					} catch (Exception ex) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "表格中选中一行,右键删除！");
					}
				}
			}
		});
		popupMenu.add(deleteItem);

	}

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
