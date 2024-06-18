package mainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

	public static void main(String[] args) { // 표 내의 값 클릭 시 해당 row의 값을 콤보와 텍스트필드에 입력
		// 전달 받은 배열을 보기에 알맞게 수정
		// 새로 만들고 저장 파일에 하나 추가. 그리고 새로 만들 것의 편집창 열기
		// 콤보와 텍스트필드에 입력된 것으로 저장된 파일 데이터에서 알맞은 것을 찾아서 열기
		// 삭제 및 저장파일에서 삭제.
		
		Dimension dim = new Dimension(800,400);
		
		JFrame frame = new JFrame("메인 프레임");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		String[][] inputArrStr = {{"엑셀","고객명단"},{"한글","분기 보고서"},{"쿼리","가계부"}};
		
		String[] header = {"파일 유형", "파일 이름"};
		String[][] contents = inputArrStr;
		
		DefaultTableModel model = new DefaultTableModel(contents,header); // 테이블 수정과 관련된 것
		
		JTable table = new JTable(model); // 테이블 생성.
		// 내용을 모델에 넘기고, 모델을 테이블에 연결.
		
		JScrollPane scrollpane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS)); //
		
		JTextField textField = new JTextField(8);

		String[] comboText = {"한글","워드","엑셀","쿼리"};
		JComboBox sortCombo = new JComboBox(comboText);
		sortCombo.setSelectedItem(inputArrStr[0]);
		
		table.getColumn("파일 유형").setPreferredWidth(100);
		table.getColumn("파일 이름").setPreferredWidth(700);
		
		JLabel text_sortName = new JLabel(" 파일 유형 :   ");
		JLabel text_fileName = new JLabel("    파일 이름 :   ");
		
		JButton add_create_new_Btn = new JButton("새로 만들기");
		add_create_new_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		JButton add_del_Btn = new JButton("편집");
		add_del_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
		JButton add_open_file_Btn = new JButton("삭제");
		add_open_file_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
				
		panel.add(text_sortName);
		panel.add(sortCombo);
		panel.add(text_fileName);
		panel.add(textField);
		panel.add(add_create_new_Btn);
		panel.add(add_del_Btn);
		panel.add(add_open_file_Btn);
			
		frame.add(scrollpane,BorderLayout.CENTER);		
		
		frame.add(panel,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);

	}

}
