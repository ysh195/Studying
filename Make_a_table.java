package four;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Make_a_table {

	public static void main(String[] args) { // 이제 텍스트 필드 일원화하고 알아서 나눠 가지도록 수정해야 함. 그리고 데이터 추출하는 방법만 구현하면 끝
		
		Dimension dim = new Dimension(800,400);
		
		JFrame frame = new JFrame("테이블 만들기");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		
		String header[] = {"ID","이름","전화번호","등급"};
		String contents[][]= {
				{"13254","김철수","010-0000-0000","브론즈"},
				{"18742","김영희","010-1234-5678","골드"},
				{"39615","이민수","010-9126-7913","플래티넘"}
		};
		
		DefaultTableModel model = new DefaultTableModel(contents,header); // 테이블 수정과 관련된 것
		
		JTable table = new JTable(model); // 테이블 생성.
		// 내용을 모델에 넘기고, 모델을 테이블에 연결.

		JScrollPane scrollpane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		JPanel panelA = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS)); // 이건 진짜 뭔지 모르겠음
		panelA.setLayout(new BoxLayout(panelA,BoxLayout.X_AXIS)); //
		
		JTextField textField = new JTextField(8);
		
		panel.add(textField);
		
		JButton addYBtn = new JButton("Y 추가"); // X 추가도 필요.
		addYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int ColumnCount = table.getColumnCount(); // 가로 몇 칸인지 세고
				String str = textField.getText(); // 텍스트 내용 가져와서

				String[] insertStr = new String[ColumnCount];
				
				if(textField != null) {
					int count=0;
					if(str.contains("|")==false) {
						insertStr[count] = str;
					}
					else {
						do{
							insertStr[count] = str.substring(0, str.indexOf("|")); 
							str = str.substring(str.indexOf("|")+1, str.length());
							count++;
						}while(str.contains("|"));
						insertStr[count] = str;
					}
				}
				else {
					return;
				}
				model.addRow(insertStr);
				
				textField.setText("");
				
			}
			
		});
		
		JButton addXBtn = new JButton("X 추가");
		addXBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.addColumn(textField.getText()); // 모델에서 줄 생성하면서 내용만 추가하는 것과, 그냥 제목만 넣고 추가하는 게 있는데, 서로 이름이 같으니 주의
			}
			
		});
		
		JButton removeYBtn = new JButton("Y 삭제"); // X 삭제는 그냥 없는 기능이라 어쩔 수 없음. model.setColumnCount(0); 로 가능하려나?
		removeYBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}
				else {
					model.removeRow(table.getSelectedRow());
				}
			}
			
		});
		
		JButton removeXBtn = new JButton("X 삭제"); // X 삭제는 그냥 없는 기능이라 어쩔 수 없겠다 싶었는데, 되네?
		removeXBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ColumnCount = table.getColumnCount();
				if(ColumnCount <= 1) {
					return;
				}
				else {
					model.setColumnCount(ColumnCount-1);
				}
			}
			
		});
		
		JButton saveBtn = new JButton("저장");
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // 저장 기능만 구현하면 이제 끝
				model.setColumnCount(0);
			}
			
		});
		
		panel.add(addXBtn);
		panel.add(addYBtn);
		panel.add(removeXBtn);
		panel.add(removeYBtn);		
		
		panelA.add(saveBtn);
		
		// table.setValueAt("200", 2, 1); // 해당 좌표 내의 값 변경
		System.out.println(table.getRowCount()); // 테이블의 세로 길이 표현
		System.out.println(table.getColumnCount()); // 테이블의 세로 길이 표현		
		
		frame.add(scrollpane,BorderLayout.CENTER);		
		
		
		frame.add(panelA,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);

	}

}
