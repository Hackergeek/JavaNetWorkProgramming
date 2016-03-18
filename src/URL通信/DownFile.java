/**
 * 
 */
package URLͨ��;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 3.5URLConnection���Ӧ��
 * @author skyward
 *
 */
public class DownFile implements ActionListener {
	
	private JFrame mainJFrame;
	private Container con;
	private JLabel msgLbl;
	private JTextField urlText;
	private JButton btn;

	DownFile() {
		mainJFrame = new JFrame("�ļ�����");
		con = mainJFrame.getContentPane();
		msgLbl = new JLabel("������Ҫ���ص��ļ���ַ������");
		urlText = new JTextField();
		urlText.setColumns(15);
		btn = new JButton("����");
		btn.addActionListener(this);
		con.setLayout(new FlowLayout());
		con.add(msgLbl);
		con.add(urlText);
		con.add(btn);
		mainJFrame.setSize(300, 200);
		mainJFrame.setVisible(true);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DownFile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			URL url = new URL(urlText.getText());
			URLConnection connection = url.openConnection();
			int c;
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("temp.dat"));
			while((c = br.read()) != -1) {
				bw.write(c);
			}
			br.close();
			bw.close();
			JOptionPane.showMessageDialog(mainJFrame, "���سɹ�");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
