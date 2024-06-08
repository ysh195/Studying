package one;

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

public class Buttons {
	
	private int x, y;
	private boolean isMine;
	
	public Buttons() {}
	
	public Buttons(int x, int y) { set_buttons(x, y); }
	
	void maybe_Mine() {
		int random_num = (int)(Math.random()*100);
		if(random_num <= 10) { isMine = true; }
		else { isMine = false; }
	}
	
	void set_buttons(int x, int y){
		this.x=x;
		this.y=y;
		
		int location_X = (x+1)*50;
		int location_Y = (y+1)*50;
		
		JButton button = new JButton("");
		Screen onlyScreen = Screen.getInstance();
		button.setBounds(location_X, location_Y, 50, 50);
		onlyScreen.contentPane.add(button);
		
		maybe_Mine();
		
		///////////////////////////////////////////////// 액션 리스너
		button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(isMine) {
	            	button.setText("!!!");
	            	onlyScreen.textbox.setText("Boom!!");
	            }
	            else {
	            	button.setText("X");
	            	onlyScreen.textbox.setText("It's safe!");
	            }
	        }
	    });
		//////////////////////////////////////////////////
	}


}
