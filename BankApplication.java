package pro20;

import java.util.Scanner;

public class BankApplication{
	public static boolean work_continue = true;
	public static int customer_count = 0;
	public static String[][] customer_list = new String[100][3];
	
	public static void main(String[] args) {
						
		System.out.println("안녕하세요? 진행하실 업무를 선택해주세요.");
		
		while(work_continue) {
			
			Hello();
			
			work_process();
		}
	} // 메인에 모든 메소드를 다 몰아주고 전역변수로 처리했는데, 차라리 상속을 하고, 대부분의 메소드에 중요 메소드를 모두 입력하면서 가는 게 깔끔하지 않나. 
	
	
	
	
	
	
	
	
	
	
	
	public static void Hello() {
		System.out.println("=================================================");
		System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");
		System.out.println("=================================================");
	}
	
	public static void work_process() {
		
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		
		switch(input) {
		case 1:
			creat_account();
			break;
		case 2:
			show_acc_List();
			break;
		case 3:
			increase_money();
			break;
		case 4:
			decrease_money();
			break;
		case 5:
			System.out.println("업무를 종료합니다. 안녕히 가십시오.");
			work_continue=false;
			break;
		}
		
	}
	
	public static void creat_account() {
		
		String account_number="";
		
		check_dupli : while(true) {
		System.out.println("생성하실 계좌 번호를 입력하여 주십시오.");
		Scanner sc1 = new Scanner(System.in);
		account_number = sc1.nextLine();
		
		for(int i=0; i<customer_count; i++) {
			if(customer_list[i][0].equals(account_number)) {
				System.out.println("이미 동일한 계좌번호가 생성되어 있습니다. 다른 번호로 입력해주세요.");
			}
			else {
				customer_list[customer_count][0] = account_number;
				break check_dupli;
			}
		}
		}
				
		System.out.println("본인의 이름을 입력하여 주십시오.");
		Scanner sc2 = new Scanner(System.in);
		customer_list[customer_count][1] = sc2.nextLine();
		
		System.out.println("계좌 생성 후 입금하실 금액을 입력하여 주십시오.");
		Scanner sc3 = new Scanner(System.in);
		long input_money = sc3.nextLong();
		customer_list[customer_count][2] = String.valueOf(input_money);
				
		customer_count++;
					
	}
	
	public static void show_acc_List() {
		
		System.out.println("-----------");
		System.out.println("계좌 목록");
		System.out.println("-----------");
		
		for(int i=0; i<customer_count; i++) {
			System.out.println(customer_list[i][0] + "    " + customer_list[i][1] + "    " + customer_list[i][2]);
		}
	}
	
	public static void increase_money() {
		
		int x = 0;
		String y = "";
		
		System.out.println("본인 명의의 계좌 번호를 입력하여 주십시오.");
		Scanner sc1 = new Scanner(System.in);
		
		y = sc1.nextLine();
		
		for(int i=0; i<customer_count; i++) {
			if(customer_list[i][0].equals(y)) {
				break;
			}
			else {
				x++;
			}
		}
		
		System.out.println("예금주 : "+customer_list[x][1]);
		
		System.out.println("입금하실 금액을 입력하여 주십시오.");
		Scanner sc2 = new Scanner(System.in);
		long temp1 = sc2.nextLong();
		long temp2 = Long.parseLong(customer_list[x][2]);
		
		System.out.println("예금액 : " + temp1);
		System.out.println("예금 결과 : " + (temp1+temp2));
		customer_list[x][2] = String.valueOf((temp1+temp2));
		
	}
	
	public static void decrease_money() {
		
		int x = 0;
		String y = "";
		
		System.out.println("본인 명의의 계좌 번호를 입력하여 주십시오.");
		Scanner sc1 = new Scanner(System.in);
		
		y = sc1.nextLine();
		
		for(int i=0; i<customer_count; i++) {
			if(customer_list[i][0].equals(y)) {
				break;
			}
			else {
				x++;
			}
		}
		
		System.out.println("예금주 : "+customer_list[x][1]);
		long temp2 = Long.parseLong(customer_list[x][2]);
		long temp1 = 0;
		boolean remain = true; 
		
		do{	
		System.out.println("출금하실 금액을 입력하여 주십시오.");
		Scanner sc2 = new Scanner(System.in);
		temp1 = sc2.nextLong();
		remain = ((temp2-temp1)>=0) ? false : true;
		if(remain) {
			System.out.println("현재 입금된 잔액보다 출금액이 더 많아 출금을 진행할 수 없습니다.");
		}
		}while(remain);
		
		System.out.println("출금액 : " + temp1);
				
		System.out.println("출금 결과 : " + (temp2-temp1));
		customer_list[x][2] = String.valueOf((temp1-temp2));
	}
	
	

}
