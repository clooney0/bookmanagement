package cn.book.ui;

import java.awt.Color;
//import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.theme.SubstanceCremeTheme;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;

import cn.book.dao.RootDao;
import cn.book.entity.Root;
import javax.swing.SwingConstants;

public class RootFrame {

	public JFrame frame;
	private JTable table;
	private Object[][] obj;
	private RootDao rootdao = new RootDao();
	private Object b;

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
					RootFrame window = new RootFrame();
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
	public RootFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("SimSun", Font.PLAIN, 20));
		frame.setBounds(100, 100, 677, 473);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("img/sys.png");
		frame.setIconImage(image);
		frame.setTitle("书籍管理系统");
		frame.setResizable(false);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		// frame.getContentPane().setBackground(new Color(121,83,46));

		// -----------------------显示管理员信息表---------------

		try {
			List<Root> res = rootdao.showAllInfo();
			obj = new Object[res.size()][2];
			{
				for (int i = 0; i < res.size(); i++) {
					obj[i][0] = res.get(i).getId();
					obj[i][1] = res.get(i).getRoot_name();

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 90, 510, 266);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(obj, new String[] { "管理员编号",
				"管理员用户名" }));
		// --------------------OVER------------------------------
		scrollPane.setViewportView(table);

		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		// addPopup(table, popupMenu);

		//右键菜单子菜单修改  
		JMenuItem modifierItem = new JMenuItem("修改");
		modifierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "确定修改用户信息吗?",
						"修改用户提示", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					b = table.getValueAt(table.getSelectedRow(), 0);

					Object name = table.getValueAt(table.getSelectedRow(), 1);
					String user = String.valueOf(name);
					TempData.temproot = user;
					System.out.println("要修改的管理员值" + user);
					long id = (Long) b;
					TempData.id = id;
					RootModifyFrame window = new RootModifyFrame();
					window.frame.setVisible(true);
					frame.dispose();
				}
			}
		});
		popupMenu.add(modifierItem);

		//右键删除
		JMenuItem deleteItem = new JMenuItem("删除");
		deleteItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "确定删除用户信息吗?",
						"删除用户提示", JOptionPane.YES_NO_OPTION);

				if (n == 0) {
					// try {

					b = table.getValueAt(table.getSelectedRow(), 0);
					System.out.println(b + "-------------");
					long id = (Long) b;

					boolean isOk = rootdao.delRoot(id);
					if (isOk) {
						JOptionPane.showMessageDialog(null, "删除成功！");
						// -----------------------------------------------------
						try {
							List<Root> res = rootdao.showAllInfo();
							obj = new Object[res.size()][3];
							{
								for (int i = 0; i < res.size(); i++) {
									obj[i][0] = res.get(i).getId();
									obj[i][1] = res.get(i).getRoot_name();
									obj[i][2] = res.get(i).getRoot_password();

								}
							}
						} catch (Exception ex) {
							ex.printStackTrace();

						}
						table.setModel(new DefaultTableModel(obj, new String[] {
								"用户ID", "用户姓名", "用户密码" }));
						// -----------------------------------------------------
					} else {
						JOptionPane.showMessageDialog(null, "删除失败！");
					}

					// } catch (Exception e2) {
					// JOptionPane.showMessageDialog(null, "选中一行,右键删除！");
					// }

				}
			}
			// -----------------if}

		});
		popupMenu.add(deleteItem);

		JLabel welcomelabel = new JLabel("欢迎" + TempData.tempname + "管理员");
		welcomelabel.setForeground(Color.RED);
		welcomelabel.setFont(new Font("SimSun", Font.PLAIN, 25));
		welcomelabel.setBounds(228, 13, 268, 47);
		frame.getContentPane().add(welcomelabel);
		// 时间格式化
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EHH:mm:ss");
		String currentime = sdf.format(date);
		final JLabel dataLabel = new JLabel(currentime);
		dataLabel.setBackground(Color.RED);
		dataLabel.setFont(new Font("SimSun", Font.PLAIN, 18));
		dataLabel.setForeground(Color.RED);
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setBounds(377, 369, 230, 35);
		frame.getContentPane().add(dataLabel);

		// A.在jtable上添加一个跟鼠标有关的事件 右键监听
		table.addMouseListener(new MouseAdapter() {
			@Override
			// 适配器模式（Adapter模式）
			public void mouseClicked(MouseEvent e) {// 获取单击事件
				// B.判断鼠标按键：左键、右键
				int button = e.getButton();// 1.左键 2.滚轮 3.右键
				System.out.println("button=" + button);
				if (button == MouseEvent.BUTTON3) { // 右键
					// C.设置选中行的位置
					table.setRowSelectionInterval(
							table.rowAtPoint(e.getPoint()),
							table.rowAtPoint(e.getPoint()));
					// D:在选中的位置，弹出菜单
					popupMenu.show(e.getComponent(), e.getX(), e.getY());

					// E、获取值
					Object object = table.getValueAt(table.getSelectedRow(), 0);
					long id = (Long) object;
					System.out.println(String.valueOf(id));
					// TempData.id=id;
					// Object name= table.getValueAt(table.getSelectedRow(), 1);
					// String user=String.valueOf(name);
					// TempData.temproot=user;
					// System.out.println("要修改的管理员值"+user);
					// ModifyRootFrame window = new ModifyRootFrame();
					// window.frame.setVisible(true);
					// frame.dispose();
				}

			}
		});

		// 获取时间，动态时间
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd EHH:mm:ss");
				String currentime = sdf.format(date);
				dataLabel.setText(currentime);
			}
		}, new Date(), 1000);

	}

	/*
	 * private static void addPopup(Component component, final JPopupMenu popup)
	 * { component.addMouseListener(new MouseAdapter() { public void
	 * mousePressed(MouseEvent e) { if (e.isPopupTrigger()) { showMenu(e); } }
	 * 
	 * public void mouseReleased(MouseEvent e) { if (e.isPopupTrigger()) {
	 * showMenu(e); } }
	 * 
	 * private void showMenu(MouseEvent e) { popup.show(e.getComponent(),
	 * e.getX(), e.getY()); } }); }
	 */
}
