package four;

import fileEditor.storage.Storage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel; // 테이블 수정에 관련된 패키지
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame {
	
	public static void main(String[] args) {
		// 콤보와 텍스트필드에 입력된 것으로 저장된 파일 데이터에서 알맞은 것을 찾아서 열기		
		Dimension dim = new Dimension(800,400);
		
		JFrame frame = new JFrame("문서 편집기");
		frame.setLocation(200, 400);
		frame.setPreferredSize(dim);
		
		String[][] inputArrStr = new String[Storage.getInstance().FileList.length][2];
		
		for(int i=0; i<Storage.getInstance().FileList.length; i++) {
			for(int j=0; j<2; j++) {
				inputArrStr[i][j] = Storage.getInstance().FileList[i][j];
			}
		}	
		String[][] contents = inputArrStr;
		String[] header = {"파일 유형", "파일 이름"};
		DefaultTableModel model = new DefaultTableModel(contents,header); // 테이블 수정과 관련된 것
		JTable table = new JTable(model); // 테이블 생성.
		JScrollPane scrollpane = new JScrollPane(table);
		// 내용을 모델에 넘기고, 모델을 테이블에 연결.
		
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
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				sortCombo.setSelectedItem(table.getValueAt(row, 0));
				textField.setText((String) table.getValueAt(row, 1));
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
		JButton add_create_new_Btn = new JButton("새로 만들기");
		add_create_new_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if((textField.getText() == null) | (sortCombo.getSelectedItem() == null)) {
					System.out.println("유형 또는 제목을 입력하세요.");
					return;
				}
				else {
					for(int i=0; i<inputArrStr.length; i++) {
						if(inputArrStr[i][0].equals((String)sortCombo.getSelectedItem())&&inputArrStr[i][1].equals(textField.getText())) {
							System.out.println("유형과 이름이 중복되는 파일이 존재합니다.");
							return;
						}
						
					}
				}
				
				String[] new_save = new String[3];
				new_save[0] = String.valueOf((String)sortCombo.getSelectedItem()) ;
				new_save[1] = textField.getText();
				new_save[2] = "";
				
				Storage.getInstance().save_in_storage(new_save);
				System.out.println("새로운 파일이 생성되었습니다.");
				System.out.println("파일 유형 : " + new_save[0] + ", 파일 이름 : " + new_save[1]);
				
				String[] insertStr = new String[2];
				insertStr[0] = new_save[0];
				insertStr[1] = new_save[1];
				
				model.addRow(insertStr);
			}
			
		});
		
		JButton add_del_Btn = new JButton("삭제");
		add_del_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if((textField.getText() != null) && (sortCombo.getSelectedItem() != null)) {
					String del_sort = String.valueOf(sortCombo.getSelectedItem());
					String del_name = textField.getText();
					Storage.getInstance().delete_in_storage(del_sort,del_name);
					int ySzie = model.getRowCount();

					for(int i=0; i<ySzie; i++) {
						if(model.getValueAt(i, 0).equals(sortCombo.getSelectedItem())&&model.getValueAt(i, 1).equals(textField.getText())) {
							model.removeRow(i);
							System.out.println("파일 목록에서 삭제되었습니다.");
							break;
						}
					}

				}
				else {
					System.out.println("삭제할 파일을 지정 또는 선택하세요.");
					return;
				}		
			}
			
		});
		
		JButton add_open_file_Btn = new JButton("편집");
		add_open_file_Btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int location = -1;
				
				if((textField.getText() == null) | (sortCombo.getSelectedItem() == null)) { // 해당 파일이 실제로 존재하는지 체크
					System.out.println("유형 또는 제목을 입력하세요.");
					return;
				}
				
				for(int i=0; i<inputArrStr.length; i++) {
					if(Storage.getInstance().FileList[i][0].equals((String)sortCombo.getSelectedItem())&&Storage.getInstance().FileList[i][1].equals(textField.getText())) {
						location = i;
						break;
					}						
				}
				
				if(location <= -1) {
					System.out.println("해당 파일이 존재하지 않습니다.");
					return;
				}
				else {
					System.out.println("존재하는 파일입니다.");
				}
				
				String sortType = String.valueOf(sortCombo.getSelectedItem());
				
				if(sortType.equals("엑셀")||sortType.equals("쿼리")) { // 유형에 맞는 편집창 열기
					Make_a_table openedFile = new Make_a_table(Storage.bring_file(location));
					// make a table 생성자가 변수를 전달 받도록 해야 함.
					// 편집창에 데이터 보내주기
					// 테이블이 데이터 처리 빡세니까 조건 맞을 때만 이걸로 열기
				}
				else {
					Make_a_text openedFile = new Make_a_text(Storage.bring_file(location));
					// 마찬가지
				}		
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
