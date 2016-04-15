package 多线程端口扫描;

import java.awt.Color; //类的调用
import java.awt.Container;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Test implements ActionListener {
	// 创建主窗口
	public static JFrame mainFrame = new JFrame();
	public static Label labelIP = new Label("主机IP");
	public static Label labelPortStart = new Label("起始端口：");
	public static Label labelPortEnd = new Label("结束端口：");
	public static Label labelThread = new Label("线程数：");
	public static Label labelResult = new Label("扫描结果:");
	public static Label State = new Label("扫描状态：");
	public static Label Scanning = new Label("未开始扫描");
	public static JTextField hostName = new JTextField("127.0.0.1");
	public static JTextField PortStart = new JTextField("0");
	public static JTextField PortEnd = new JTextField("100");
	public static JTextField ThreadNum = new JTextField("100");
	// 文本区域，显示扫描结果
	public static TextArea Result = new TextArea();
	public static Label DLGINFO = new Label("");
	public static JButton Start = new JButton("扫描");
	public static JButton Exit = new JButton("退出");
	// 错误提示对话框
	public static JDialog DLGError = new JDialog(mainFrame, "错误");
	public static JButton OK = new JButton("确定");

	public Test() {

		// 设置主窗体名称
		mainFrame.setTitle("多线程端口扫描器");
		// 设置主窗体位置和大小
		mainFrame.setBounds(380, 300, 550, 300);

		// 事件监听
		mainFrame.addWindowListener(new WindowAdapter() {
			/**
			 *
			 * 关闭窗体，退出程序
			 *
			 * */
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		// 设置错误提示框
		Container dPanel = DLGError.getContentPane();
		dPanel.setLayout(null);
		dPanel.add(DLGINFO);
		dPanel.add(OK);
		OK.setActionCommand("ok");
		OK.addActionListener(this);
		// 在主窗体中添加其他组件
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		mainFrame.add(Start);
		mainFrame.add(Exit);
		mainFrame.add(labelIP);
		mainFrame.add(hostName);
		mainFrame.add(labelPortStart);
		mainFrame.add(labelPortEnd);
		mainFrame.add(PortStart);
		mainFrame.add(PortEnd);
		mainFrame.add(labelThread);
		mainFrame.add(ThreadNum);
		mainFrame.add(labelResult);
		mainFrame.add(Result);
		mainFrame.add(State);
		mainFrame.add(Scanning);
		// 设置扫描按钮和退出按钮
		Start.setBounds(405, 232, 60, 30);
		Start.setActionCommand("Start");
		Start.addActionListener(this);
		Exit.setBounds(475, 232, 60, 30);
		Exit.setActionCommand("Exit");
		Exit.addActionListener(this);
		labelIP.setBounds(17, 13, 50, 20);
		hostName.setBounds(67, 10, 92, 25);
		hostName.setHorizontalAlignment(JTextField.CENTER);

		labelPortStart.setBounds(162, 13, 60, 20);
		PortStart.setBounds(227, 10, 45, 25);
		PortStart.setHorizontalAlignment(JTextField.CENTER);

		labelPortEnd.setBounds(292, 13, 60, 20);
		PortEnd.setBounds(357, 10, 45, 25);
		PortEnd.setHorizontalAlignment(JTextField.CENTER);

		labelThread.setBounds(422, 13, 50, 20);
		ThreadNum.setBounds(477, 10, 45, 25);
		ThreadNum.setHorizontalAlignment(JTextField.CENTER);

		labelResult.setBounds(1, 45, 55, 20);
		Result.setBounds(1, 65, 542, 160);
		Result.setEditable(false);
		Result.setBackground(Color.PINK);// 设置窗口为粉红
		State.setBounds(17, 232, 60, 30);
		Scanning.setBounds(80, 232, 120, 30);
		mainFrame.setVisible(true);
	}

	/**
	 * 事件处理
	 *
	 * */
	public void actionPerformed(ActionEvent e) {

		// 得到命令处理事件
		String cmd = e.getActionCommand();

		// 开始扫描
		if (cmd.equals("Start")) {
			try {
				Scan.hostAddress = InetAddress.getByName(Test.hostName
						.getText());
			} catch (UnknownHostException e1) {
				DLGError.setBounds(300, 280, 160, 110);
				DLGINFO.setText("错误的IP地址/域名");
				DLGINFO.setBounds(25, 15, 100, 20);
				OK.setBounds(45, 40, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			int minPort;
			int maxPort;
			int threadNum;
			// 获取输入数据
			try {
				minPort = Integer.parseInt(PortStart.getText());
				maxPort = Integer.parseInt(PortEnd.getText());
				threadNum = Integer.parseInt(ThreadNum.getText());
			} catch (NumberFormatException e1) {
				DLGError.setBounds(300, 280, 299, 120);
				DLGINFO.setText("错误的端口号或线程数!端口号和线程数必须为整数!");
				DLGINFO.setBounds(10, 20, 280, 20);
				OK.setBounds(110, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			// 输入信息错误处理
			if ((minPort < 0) || (maxPort > 65535) || (minPort > maxPort)) {
				DLGError.setBounds(300, 280, 295, 120);
				DLGINFO.setText("最小端口必须是0-65535并且小于最大端口的整数");
				DLGINFO.setBounds(10, 20, 280, 20);
				OK.setBounds(120, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			if ((threadNum > 200) || (threadNum < 0)) {
				DLGError.setBounds(300, 280, 184, 120);
				DLGINFO.setText("进程数必须是1-200中的整数");
				DLGINFO.setBounds(10, 20, 200, 20);
				OK.setBounds(55, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			Result.append("正在扫描 " + hostName.getText() + " 线程数:" + threadNum
					+ "\n");
			Scanning.setText("开始扫描 ...");
			Result.append("开始端口 " + minPort + " 结束端口 " + maxPort + " \n");
			for (int i = minPort; i <= maxPort;) {
				if ((i + threadNum) <= maxPort) {
					new Scan(i, i + threadNum).start();
					i += threadNum;
				} else {
					new Scan(i, maxPort).start();
					i += threadNum;
				}
			}
			try {
				Thread.sleep(60);// 设置处理等待时间
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Result.append("扫描完成!\n");
			Scanning.setText("扫描完成！");
		} else if (cmd.equals("ok")) {
			DLGError.dispose();
		} else if (cmd.equals("Exit")) {
			System.exit(1);
		}
	}

	public static void main(String[] args) {

		new Test();

	}

}

class Scan extends Thread {
	int maxPort, minPort;
	public static InetAddress hostAddress;

	Scan(int minPort, int maxPort) {
		this.minPort = minPort;
		this.maxPort = maxPort;
	}

	public void run() {
		// 扫描指定端口
		for (int i = minPort; i < maxPort; i++) {
			Test.Scanning.setText("正在扫描" + i + "端口");
			try {
				// 根据主机名和端口号创建套接字地址。
				SocketAddress sockaddr = new InetSocketAddress(hostAddress, i);
				Socket scans = new Socket();
				int timeoutMs = 60;
				// 将此套接字连接到具有指定超时值的服务器。
				scans.connect(sockaddr, timeoutMs);
				// 关闭此套接字。
				scans.close();

				// 添加结果显示
				Test.Result.append("主机:" + Test.hostName.getText() + " TCP端口:"
						+ i);
				switch (i) {
				case 20:
					Test.Result.append("(FTP Data)");
					break;
				case 21:
					Test.Result.append("(FTP Control)");
					break;
				case 23:
					Test.Result.append("(TELNET)");
					break;
				case 25:
					Test.Result.append("(SMTP)");
					break;
				case 38:
					Test.Result.append("(RAP)");
					break;
				case 53:
					Test.Result.append("(DNS)");
					break;
				case 79:
					Test.Result.append("FINGER");
					break;
				case 80:
					Test.Result.append("(HTTP)");
					break;
				case 110:
					Test.Result.append("(POP)");
					break;
				case 161:
					Test.Result.append("(SNMP)");
					break;
				case 443:
					Test.Result.append("(HTTPS)");
					break;
				case 1433:
					Test.Result.append("(SqlServer)");
					break;
				case 3306:
					Test.Result.append("(MySql)");
					break;
				case 8000:
					Test.Result.append("(QQ)");
					break;
				}
				Test.Result.append(" 开放\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
