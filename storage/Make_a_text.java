package four;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Make_a_text {

	public static void main(String[] args) { // 그리고 데이터 추출하는 방법만 구현하면 끝
		
		Dimension dim = new Dimension(800,400);
		
		JFrame frame = new JFrame("텍스트 에어리어 만들기");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		String[] inputArrStr = {"한글","23.Q1 실적 보고서","AAAAAA"};
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollpane = new JScrollPane(textArea);
		textArea.setText(inputArrStr[2]);
		
		JPanel panelA = new JPanel();
		
		panelA.setLayout(new BoxLayout(panelA,BoxLayout.X_AXIS)); //
		
		JTextField textField = new JTextField(8);
		
		JTextField sort_textField = new JTextField(inputArrStr[0],3);
		JTextField title_textField = new JTextField(inputArrStr[1],3);
		
		String[] comboText = {"한글","워드","엑셀","쿼리"};
		JComboBox sortCombo = new JComboBox(comboText);
		sortCombo.setSelectedItem(inputArrStr[0]);
		
		JLabel text_sortName = new JLabel(" 파일 유형 :   ");
		JLabel text_fileName = new JLabel("    파일 이름 :   ");
		
		JButton saveBtn = new JButton("저장"); // return 해야 하나
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 저장 기능만 구현하면 이제 끝
				String[] result = new String[3];
				result[0] = sortCombo.getSelectedItem().toString(); // 현재 선택된 것. 이걸 텍스트로 가져오려면 toString이 필요하다고 함
				result[1] = title_textField.getText();
				result[2] = textArea.getText();
				
				for(String s : result) {
					System.out.println(s);
				}
				
			}
			
		});

		panelA.add(text_sortName);
		panelA.add(sortCombo);
		panelA.add(text_fileName);
		panelA.add(title_textField);
		panelA.add(saveBtn);
			
		frame.add(scrollpane,BorderLayout.CENTER);		
		
		
		frame.add(panelA,BorderLayout.NORTH);
		
		frame.pack();
		frame.setVisible(true);

	}

}
