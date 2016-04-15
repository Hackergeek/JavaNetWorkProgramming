package ���̶߳˿�ɨ��;

import java.awt.Color; //��ĵ���
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
	// ����������
	public static JFrame mainFrame = new JFrame();
	public static Label labelIP = new Label("����IP");
	public static Label labelPortStart = new Label("��ʼ�˿ڣ�");
	public static Label labelPortEnd = new Label("�����˿ڣ�");
	public static Label labelThread = new Label("�߳�����");
	public static Label labelResult = new Label("ɨ����:");
	public static Label State = new Label("ɨ��״̬��");
	public static Label Scanning = new Label("δ��ʼɨ��");
	public static JTextField hostName = new JTextField("127.0.0.1");
	public static JTextField PortStart = new JTextField("0");
	public static JTextField PortEnd = new JTextField("100");
	public static JTextField ThreadNum = new JTextField("100");
	// �ı�������ʾɨ����
	public static TextArea Result = new TextArea();
	public static Label DLGINFO = new Label("");
	public static JButton Start = new JButton("ɨ��");
	public static JButton Exit = new JButton("�˳�");
	// ������ʾ�Ի���
	public static JDialog DLGError = new JDialog(mainFrame, "����");
	public static JButton OK = new JButton("ȷ��");

	public Test() {

		// ��������������
		mainFrame.setTitle("���̶߳˿�ɨ����");
		// ����������λ�úʹ�С
		mainFrame.setBounds(380, 300, 550, 300);

		// �¼�����
		mainFrame.addWindowListener(new WindowAdapter() {
			/**
			 *
			 * �رմ��壬�˳�����
			 *
			 * */
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		// ���ô�����ʾ��
		Container dPanel = DLGError.getContentPane();
		dPanel.setLayout(null);
		dPanel.add(DLGINFO);
		dPanel.add(OK);
		OK.setActionCommand("ok");
		OK.addActionListener(this);
		// ��������������������
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
		// ����ɨ�谴ť���˳���ť
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
		Result.setBackground(Color.PINK);// ���ô���Ϊ�ۺ�
		State.setBounds(17, 232, 60, 30);
		Scanning.setBounds(80, 232, 120, 30);
		mainFrame.setVisible(true);
	}

	/**
	 * �¼�����
	 *
	 * */
	public void actionPerformed(ActionEvent e) {

		// �õ�������¼�
		String cmd = e.getActionCommand();

		// ��ʼɨ��
		if (cmd.equals("Start")) {
			try {
				Scan.hostAddress = InetAddress.getByName(Test.hostName
						.getText());
			} catch (UnknownHostException e1) {
				DLGError.setBounds(300, 280, 160, 110);
				DLGINFO.setText("�����IP��ַ/����");
				DLGINFO.setBounds(25, 15, 100, 20);
				OK.setBounds(45, 40, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			int minPort;
			int maxPort;
			int threadNum;
			// ��ȡ��������
			try {
				minPort = Integer.parseInt(PortStart.getText());
				maxPort = Integer.parseInt(PortEnd.getText());
				threadNum = Integer.parseInt(ThreadNum.getText());
			} catch (NumberFormatException e1) {
				DLGError.setBounds(300, 280, 299, 120);
				DLGINFO.setText("����Ķ˿ںŻ��߳���!�˿ںź��߳�������Ϊ����!");
				DLGINFO.setBounds(10, 20, 280, 20);
				OK.setBounds(110, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			// ������Ϣ������
			if ((minPort < 0) || (maxPort > 65535) || (minPort > maxPort)) {
				DLGError.setBounds(300, 280, 295, 120);
				DLGINFO.setText("��С�˿ڱ�����0-65535����С�����˿ڵ�����");
				DLGINFO.setBounds(10, 20, 280, 20);
				OK.setBounds(120, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			if ((threadNum > 200) || (threadNum < 0)) {
				DLGError.setBounds(300, 280, 184, 120);
				DLGINFO.setText("������������1-200�е�����");
				DLGINFO.setBounds(10, 20, 200, 20);
				OK.setBounds(55, 50, 60, 30);
				DLGError.setVisible(true);
				return;
			}
			Result.append("����ɨ�� " + hostName.getText() + " �߳���:" + threadNum
					+ "\n");
			Scanning.setText("��ʼɨ�� ...");
			Result.append("��ʼ�˿� " + minPort + " �����˿� " + maxPort + " \n");
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
				Thread.sleep(60);// ���ô���ȴ�ʱ��
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Result.append("ɨ�����!\n");
			Scanning.setText("ɨ����ɣ�");
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
		// ɨ��ָ���˿�
		for (int i = minPort; i < maxPort; i++) {
			Test.Scanning.setText("����ɨ��" + i + "�˿�");
			try {
				// �����������Ͷ˿ںŴ����׽��ֵ�ַ��
				SocketAddress sockaddr = new InetSocketAddress(hostAddress, i);
				Socket scans = new Socket();
				int timeoutMs = 60;
				// �����׽������ӵ�����ָ����ʱֵ�ķ�������
				scans.connect(sockaddr, timeoutMs);
				// �رմ��׽��֡�
				scans.close();

				// ��ӽ����ʾ
				Test.Result.append("����:" + Test.hostName.getText() + " TCP�˿�:"
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
				Test.Result.append(" ����\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
