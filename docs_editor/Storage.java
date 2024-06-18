package fileEditor.storage;

public class Storage { 
	
	private static Storage storage = new Storage(); // 그냥 저장공간 일원화 하고, 알아서 찾아가게 하자
	private Storage() {}
	public static Storage getInstance() {
		return storage;
	}
	
	static String[][] FileList = {{"","",""}}; // 또는 new String[1][3];
	
	void save_in_storage(String[][] saveFile) {
		
		int num = FileList.length;
		
		for(int i=0; i<num; i++) { // 타입과 이름이 모두 같은 파일이 있는지 체크
			if(FileList[i][0].equals(saveFile[0][0])&&FileList[i][1].equals(saveFile[0][1])) {
				System.out.println("이미 있는 파일입니다. 다른 이름 혹은 형식으로 수정해주세요.");
				return; // 있으면 중단, 없으면 그대로 진행
			}			
		}
		
		String[][] newTemp = new String[num+1][3]; // 
		for(int i=0; i<num; i++) {
			for(int j=0; j<3; j++) {
				newTemp[i][j] = FileList[i][j];
			}
		}
		
		newTemp[num+1][0] = saveFile[0][0];
		newTemp[num+1][1] = saveFile[0][1];
		newTemp[num+1][2] = saveFile[0][2];
		
		FileList = newTemp;

	}
	
	void delete_in_storage(String sort, String fileName) {
		
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
	}
	
	String[][] bring_file(String sort, String name){  // 이거 받을 메서드는 null값에 대한 대비가 필요함.
		
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
