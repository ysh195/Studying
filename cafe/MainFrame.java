package cafe;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MainFrame {
	
	JFrame frame = new JFrame();
	Container contentPane;
	public static Font font = new Font("Serif",Font.BOLD,17);
	
	public MainFrame() {
		frame.setTitle("카페 키오스크 만들기"); // 제목
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLocationRelativeTo(null);	
		frame.setSize(1800,900); // 화면 사이즈
		contentPane = frame.getContentPane();
		contentPane.setLayout(null);
	}
	
	void setTextLabel(String str, int x, int y) {
		JLabel textbox = new JLabel(str);
		textbox.setFont(font);
		textbox.setBounds(x, y, 100, 100);
		contentPane.add(textbox);
	}
	
	void setButton(int location_X, int location_Y, String menu_name, int value) {
		Buttons button = new Buttons();
		button.setBounds(location_X, location_Y, 200, 100);
		button.setContent(menu_name, value);
		button.setFont(font);		
		contentPane.add(button);
	}
		
	void setFrameVisible() {
		frame.setVisible(true);
	}
	
	void ChangeContainer(MainFrame mainframe) {
		this.contentPane = mainframe.contentPane;
	}
	

}
