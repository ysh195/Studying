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

public class Buttons extends JButton{
	
	private int value;
	private String menu_name;
	
	public Buttons() {}
	
	void setContent(String menu_name, int value) {
		this.menu_name = menu_name;
		this.value = value;
	
		setFont(MainFrame.font);
		setText(this.menu_name + " " + String.valueOf(this.value) + "원");
		
	}
	
	
}
