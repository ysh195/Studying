package fileEditor.storage;

public class Divider {
	
	public Divider () {}
	
	/* 
	 1. 이게 실행되면 일단 저장소에 있는 파일을 불러와서 유형에 알맞게 분배
	 2. 그 이후에는 사용자로부터 받는 신호에 따라 각 기능을 실행
		- 내용 수정
		- 분배 
	// 테이블아웃 전환
	// 테이블인 전환
	 */
	String[][] create_temp(String[][] selected_file) {
		
		String[][] temp = new String[1][3]; // 기존 파일과 주소 겹치지 말라고 new 씀 
		temp = selected_file;
		
		return temp;
		
		}
	
	String[][] create_new_file(String sort, String name){
		String[][] save = new String[1][3];
		save[0][0] = sort;
		save[0][1] = name;
		save[0][2] = ""; // null 안 나오게 하려고
		
		Storage.getInstance().save_in_storage(save);
		
		return save;
	}
	
	void express_on_screen() {
		
	}
}
