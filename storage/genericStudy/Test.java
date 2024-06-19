package ch13.ex01;

public class Test {

	public static void main(String[] args) {
		
		A<String, Integer, Character, Long> a1 = new A<>("A",1,'a',1L);
		
		A<Long, Character, String, Integer> a2 = new A<>(1L, 'a', "A", 1);

	}

}
