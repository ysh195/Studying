package collection;

import java.util.*;
	
class MyStack{
		
	private ArrayList<String> arrayStack = new ArrayList<String>();
		
	public void push(String data) { // 뒤에서부터 추가하는 메서드
		arrayStack.add(data);
	}
		
	public String pop() { // 뒤에서부터 제거하는 메서드
		int len = arrayStack.size();
		if(len==0) {
			System.out.println("스택이 비었습니다.");
			return null;
		}
			
		return (arrayStack.remove(len-1));
	}
}	

public class StackTest { // 후입/선출
	public static void main(String[] args) {
		MyStack stack = new MyStack(); // 상자에 물건 넣는 것처럼 나중에 들어간 게 먼저 나옴
		stack.push("A");
		stack.push("B");
		stack.push("C");
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		

	}

}
