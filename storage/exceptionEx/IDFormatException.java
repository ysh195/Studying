package exceptionEx;

public class IDFormatException extends Exception { // Exception도 클래스였구나. 그리고 Exception을 상속해서 커스터마이징한 예외처리가 가능

	public IDFormatException(String message) {
		super(message);

	}
	

}
