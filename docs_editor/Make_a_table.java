package four;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지

import fileEditor.storage.Storage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Make_a_table {

	public Make_a_table(String[] input) { // 그리고 데이터 추출하는 방법만 구현하면 끝
		
		Dimension dim = new Dimension(800,400);
		
		JFrame frame = new JFrame("테이블 만들기");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		String[] inputArrStr = input;
		int y_tellingSign_count = (inputArrStr[2].length() - inputArrStr[2].replace("^", "").length())+1; // 찾는 기호를 모두 ""로 바꿔버리고 바꾸기 전의 문자열 길이와 바꾼 후의 문자열 길이를 비교하면 몇 개인지 알 수 있음

		int x_tellingSign_count = 0;
		String temp1 = inputArrStr[2];
		while(temp1.contains("^")) { // 이렇게 하면 ^가 없는 마지막줄은 빠짐
			String temp2 = temp1.substring(0, temp1.indexOf("^"));
			x_tellingSign_count = Math.max(x_tellingSign_count, temp2.length()-temp2.replace("|", "").length());
			temp1 = temp1.substring(temp1.indexOf("^")+1, temp1.length());
		}
		
		String temp2 = temp1.substring(0, temp1.length()); // 마지막 줄에 대한 걸 한 번 더 해서 보완. 
		x_tellingSign_count = Math.max(x_tellingSign_count, temp2.length()-temp2.replace("|", "").length())+1;
		
		String[][] contents = new String[y_tellingSign_count][x_tellingSign_count];

		seperating_process : for(int i=0; i<y_tellingSign_count; i++) { // 이건 구조가 좀 복잡하겠네. 마지막에 ^가 없으면 -1되고 보정해도 0이니까 계산이 안됨
			for(int j=0; j<x_tellingSign_count; j++) {
				
				int sepX = inputArrStr[2].indexOf("|");
				int sepY = inputArrStr[2].indexOf("^");
				
				if((sepX == -1) && (sepY == -1)) {
					contents[i][j] = inputArrStr[2].substring(0, inputArrStr[2].length());
					break seperating_process;
				}
				else {
					int tempX = Math.max(sepX, 0);
					int tempY = Math.max(sepY, 0);
					
					if(((tempX <= 0) || (tempY <= tempX))&&(tempY >= 1)) { // 이렇게 가도 tempX가 0인데 
						contents[i][j] = inputArrStr[2].substring(0, tempY);
						inputArrStr[2] = inputArrStr[2].substring(tempY+1, inputArrStr[2].length());
					}
					else {
						contents[i][j] = inputArrStr[2].substring(0, tempX);
						inputArrStr[2] = inputArrStr[2].substring(tempX+1, inputArrStr[2].length());
					}
				}
				
			}
		}
		
		String[] header = new String[x_tellingSign_count];
		
		DefaultTableModel model = new DefaultTableModel(contents,header); // 테이블 수정과 관련된 것
		
		JTable table = new JTable(model); // 테이블 생성.
		// 내용을 모델에 넘기고, 모델을 테이블에 연결.
		table.setTableHeader(null);
		JScrollPane scrollpane = new JScrollPane(table);
		
		JPanel panel = new JPanel();
		JPanel panelA = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS)); // 이건 진짜 뭔지 모르겠음
		panelA.setLayout(new BoxLayout(panelA,BoxLayout.X_AXIS)); //
		
		JTextField textField = new JTextField(8);
		
		JTextField sort_textField = new JTextField(inputArrStr[0],3);
		JTextField title_textField = new JTextField(inputArrStr[1],3);
		
		String[] comboText = {"한글","워드","엑셀","쿼리"};
		JComboBox sortCombo = new JComboBox(comboText);
		sortCombo.setSelectedItem(inputArrStr[0]);
		
		JLabel text_sortName = new JLabel(" 파일 유형 :   ");
		JLabel text_fileName = new JLabel("    파일 이름 :   ");
		
		
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
				model.addColumn(""); // 모델에서 줄 생성하면서 내용만 추가하는 것과, 그냥 제목만 넣고 추가하는 게 있는데, 서로 이름이 같으니 주의
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
		
		JButton saveBtn = new JButton("저장"); // return 해야 하나
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 저장 기능만 구현하면 이제 끝
				int size_x = model.getColumnCount();
				int size_y = model.getRowCount();
				String[] result = new String[3];
				if(title_textField.getText().length() <= 0) { // 이유는 모르겠지만 ""이나 null이 아닌 걸로 인식함. 그래서 글자 길이로 판단
					title_textField.setText("NoName");
				}
				result[0] = sortCombo.getSelectedItem().toString(); // 현재 선택된 것. 이걸 텍스트로 가져오려면 toString이 필요하다고 함
				result[1] = title_textField.getText();
				result[2] = "";
				
				for(int i=0; i<size_y; i++) {
					for(int j=0; j<size_x; j++) {
						result[2] += (String.valueOf(model.getValueAt(i, j)).length() <= 0) ? " " : model.getValueAt(i, j);
						if(j != size_x-1) {
							result[2] += "|";
						}
					}
					if(i != size_y-1) {
						result[2] += "^";
					}
				}
				Storage.getInstance().save_in_storage(result);
				System.out.println("편집 내용 저장 완료");
			}
			
		});
		panel.add(addXBtn);
		panel.add(addYBtn);
		panel.add(removeXBtn);
		panel.add(removeYBtn);		
		
		panelA.add(text_sortName);
		panelA.add(sortCombo);
		panelA.add(text_fileName);
		panelA.add(title_textField);
		panelA.add(saveBtn);
			
		frame.add(scrollpane,BorderLayout.CENTER);		
		
		
		frame.add(panelA,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);

	}

}
