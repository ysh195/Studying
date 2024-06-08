package one;

public class GamePlay {

	public static void main(String[] args) throws ClassNotFoundException, NullPointerException {
		
		int X_size = 5, Y_size = 5;
		int X_Max = 20, Y_Max = 20;
		
		X_size = Math.min(Math.max(X_size, 1), X_Max); // 사용자에게 사이즈를 입력받을 경우를 대비해 어느 정도 한계를 설정
		Y_size = Math.min(Math.max(Y_size, 1), Y_Max);
		
		Screen onlyScreen = Screen.getInstance(); // 다수 생성되는 버튼도 다 Screen과 연결해주려고 안에 스크린 생성자를 가지고 있음
												// 근데 그런 식으로 하면 Screen 생성자가 너무 많으니 Screen은 싱글톤으로 만듦 
		
		for(int i=0; i<X_size; i++) {
			for(int j=0; j<Y_size; j++) {
				onlyScreen.make_some_buttons(i, j); // Buttons의 생성자는 Buttons의 내부에 있으니 생성자를 만드는 메서드를 쓰려면 일단 한 번 만들어야 함
													// 그래서 그냥 바로 생성자를 써버리면 이런 식으로 메서드를 써서 간소하게 생성하는 게 애매하니까
													// Screen 클래스로 우회함.
			}
		}
		
		onlyScreen.setVisible();

	}

}
