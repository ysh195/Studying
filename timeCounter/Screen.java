package scan_mine;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class Screen{
		
	public static JLabel textLabel = new JLabel("");
	
	Screen(){
		Dimension dim = new Dimension(800,400);
		Font font = new Font("Serif",Font.BOLD,50);
		JFrame frame = new JFrame("디지털 시계");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		
		textLabel.setFont(font);

		frame.add(textLabel,BorderLayout.CENTER);		
		
		frame.pack();
		frame.setVisible(true);

	}
	
	void setT(String str) {
		textLabel.setText("             " + str);
	}
		
}

