package fileEditor.storage;

public class Storage { 
	
	private static Storage storage = new Storage(); // 그냥 저장공간 일원화 하고, 알아서 찾아가게 하자
	private Storage() {}
	public static Storage getInstance() {
		return storage;
	}
	
	public static String[][] FileList = {
			{"한글","23.Q1 실적 보고서","AAAAAA"},
			{"엑셀","고객명단","회원 아이디|회원 이름|전화번호|회원등급^13254|김철수|010-0000-0000|브론즈^18742|김영희|010-1234-5678|골드^39615|이민수|010-9126-7913|플래티넘"},
			{"워드","23.Q1 재무제표","619843154"},
			{"쿼리","매출액 계산","회원 아이디|회원 이름|전화번호|사용액|김철수|010-0000-0000|500000"}
			};

	public static void save_in_storage(String[] saveFile) {
		
		int num = FileList.length;
		
		String[][] newTemp = new String[num+1][3];
		for(int i=0; i<num; i++) {
			for(int j=0; j<3; j++) {
				newTemp[i][j] = FileList[i][j];
			}
		}
		
		newTemp[num][0] = saveFile[0];
		newTemp[num][1] = saveFile[1];
		newTemp[num][2] = saveFile[2];
		
		FileList = newTemp;
		
		System.out.println("저장소 내에 새로운 파일이 생성되었습니다.");

	}
	
	public static void delete_in_storage(String sort, String fileName) {
		
		if(FileList.length<=1) {
			System.out.println("저장된 파일의 숫자가 적습니다. 최소 1개 이상으로 유지해주세요.");
			return;
		}
		
		int location = -1;
		for(int i=0; i<FileList.length; i++) {
			if(FileList[i][1].equals(fileName)&&FileList[i][0].equals(sort)) {
				location = i;
			}
		}
		
		if(location == -1) {
			System.out.println("해당 파일을 찾을 수 없습니다.");
			return;
		}
		
		String[][] temp = new String[FileList.length-1][3];
		
		for(int i=0; i<location; i++) {
			temp[i][0] = FileList[i][0];
			temp[i][1] = FileList[i][1];
			temp[i][2] = FileList[i][2];
		}
		
		for(int i=location+1; i<FileList.length; i++) {
			temp[i-1][0] = FileList[i][0];
			temp[i-1][1] = FileList[i][1];
			temp[i-1][2] = FileList[i][2];
		}
		
		FileList = temp;
		System.out.println("저장소에서 삭제되었습니다.");
	}
	
	public static String[][] bring_file(String sort, String name){  // 이거 받을 메서드는 null값에 대한 대비가 필요함.
		
		String[][] bringOut = new String[1][3];
		
		int location = -1;
		for(int i=0; i<FileList.length; i++) {
			if(FileList[i][1].equals(name)&&FileList[i][0].equals(sort)) {
				location = i;
			}
		}
		
		if(location == -1) {
			System.out.println("해당 파일을 찾을 수 없습니다.");
			return null;
		}
		
		bringOut[0][0] = FileList[location][0];
		bringOut[0][1] = FileList[location][1];
		bringOut[0][2] = FileList[location][2];
		
		return bringOut;
	}
	
	
}
