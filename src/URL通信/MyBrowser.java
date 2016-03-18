/**
 * 
 */
package URLͨ��;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * 3.3URL���Ӧ�á�����������������
 * @author skyward
 *
 */
public class MyBrowser implements ActionListener,HyperlinkListener{
//������Ҫʵ��HyperlinkListener�ӿڣ�����Ӧ�û�����������¼�
	
	JLabel msgLbl;
	JTextField urlText;			//���û�����URL
	JEditorPane content;		//��ʾ��ҳ����
	JScrollPane JSPanel;
	JPanel panel;
	Container con;
	JFrame mainJFrame;
	//���췽�������ڳ������Ĳ���
	public MyBrowser() {
		mainJFrame = new JFrame("�ҵ������");
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con = mainJFrame.getContentPane();
		msgLbl = new JLabel("�����ַ�� ");
		urlText = new JTextField();
		urlText.setColumns(20);
		urlText.addActionListener(this);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(msgLbl);
		panel.add(urlText);
		content = new JEditorPane();
		content.setEditable(false);
		content.addHyperlinkListener(this);			//Ϊcontent��ӳ����Ӽ�����
		JSPanel = new JScrollPane(content);
		con.add(panel, BorderLayout.NORTH);
		con.add(JSPanel, BorderLayout.CENTER);
		mainJFrame.setSize(1600, 900);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MyBrowser();
	}

	//ʵ��hyperlinkUpdate���������û������ҳ�ϵ�����ʱ��ϵͳ�����ô˷���
	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {				
				URL url = e.getURL();
				content.setPage(url);
				urlText.setText(e.getURL().toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	//���û����»س����󣬵��ô˷���
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			URL url = new URL(urlText.getText());
			content.setPage(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
