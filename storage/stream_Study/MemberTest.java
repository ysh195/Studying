package test;

import java.util.ArrayList;
import java.util.List;

public class MemberTest {

	public static void main(String[] args) {
		
		String input_id = "aaab";
		String input_pw = "1234";
		
		Member member1 = new Member("aaaa","1234","ultra guy");
		Member member2 = new Member("bbbb","1234","ultra girl");
		Member member3 = new Member("cccc","1234","ultra boy");
		
		List<Member> memberList = new ArrayList<>();
		
		memberList.add(member1);
		memberList.add(member2);
		memberList.add(member3);
		
		memberList.stream().filter(u -> u.getID().equals(input_id)).filter(s -> s.getPW().equals(input_pw)).forEach(e -> System.out.println(e.say_hello()));
		// 멤버를 전달하는 게 아니라 이전에 계산된 값을 전달하는구나
		// 그럼 맵으로 조건을 판별한 뒤에 전달되는 건 멤버가 아니라 true/false네?
		// 근데 또 필터가 출력하는 건 멤버 자체네?
		// 일치하는 게 없으면 그냥 아무 것도 안 나옴. 오류도 없음 

	}
}
