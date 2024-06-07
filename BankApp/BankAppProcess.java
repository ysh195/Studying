package test;

import java.util.Scanner;

public class BankAppProcess {
	
	boolean work_continue = true;
	int customer_count = 0;
	String[][] customer_list = new String[100][3];
	
	void Hello() {
		System.out.println("=================================================");
		System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");
		System.out.println("=================================================");
	}
	
	void work_process() { // 전체적인 업무 구성입니다.
		
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
		default:
			System.out.println("지원되지 않는 입력입니다.");
			System.out.println("다시 입력해주세요.");
			break;
		}
		
	}
	
	void creat_account() { // 계좌를 개설합니다.
		
		String account_number=""; // 오류를 방지하기 위해 임의로 ""로 설정합니다.
		
		check_dupli : while(true) { // 중복된 계좌번호가 있는지 확인하는 작업입니다.
			
		System.out.println("생성하실 계좌 번호를 입력하여 주십시오.");		
		Scanner sc1 = new Scanner(System.in);
		account_number = sc1.nextLine();
		
		customer_count++; // 현재 저장된 고객의 숫자입니다. 통장을 개설하기 때문에 고객의 숫자가 늘어납니다.
		
		for(int i=0; i<customer_count; i++) { // 현재 저장된 고객의 숫자만큼 중복된 계좌번호 대조 작업을 실행합니다.
			
			if(customer_list[i][0]==null) {
				continue;
			}
			
			else if(customer_list[i][0].equals(account_number)) {
				System.out.println("이미 동일한 계좌번호가 생성되어 있습니다. 다른 번호로 입력해주세요.");
				continue;
			}
			
			else {
					break; // 중복되는 계좌번호를 찾지 못하였습니다. 따라서 정상적으로 계좌 개설이 가능합니다.
				}
			}
		
			break;
			
		}
		
		customer_list[customer_count-1][0] = account_number; // 고객이 입력한 계좌번호가 [][0]에 저장됩니다.
		// [100][3] 사이즈의 2차원 배열로, 각 줄마다 계좌번호, 고객명, 입금액 순으로 저장됩니다.
				
		System.out.println("본인의 이름을 입력하여 주십시오.");
		Scanner sc2 = new Scanner(System.in);
		customer_list[customer_count-1][1] = sc2.nextLine(); // 고객의 이름을 [][1]에 저장합니다.
		
		System.out.println("계좌 생성 후 입금하실 금액을 입력하여 주십시오.");
		Scanner sc3 = new Scanner(System.in);
		long input_money = sc3.nextLong();
		customer_list[customer_count-1][2] = String.valueOf(input_money); // 입금액을 [][2]에 저장합니다.
					
	}
	
	void show_acc_List() { // 저장된 고객들의 정보를 출력합니다.
		
		System.out.println("===============================");
		System.out.println("계좌 목록");
		System.out.println("===============================");
		
		for(int i=0; i<customer_count; i++) {
			System.out.println(customer_list[i][0] + "    " + customer_list[i][1] + "    " + customer_list[i][2]);
			System.out.println("------------------------------");
		}
	}
	
	void increase_money() {
		
		int x = Check_customerInfo(); // 고객이 입력한 계좌번호를 대조하여 어느 배열에 해당 정보가 저장되어 있는지를 출력합니다.
		if(x == (-1)) { // 그러나 계좌번호 입력에 5번 실패하면 Check_customerInfo()는 -1을 출력하여 x = -1 이 됩니다.
			return; // return값이 없는 return을 사용하여 이후의 업무를 진행하지 않고 종료시킵니다.
		}
		
		System.out.println("예금주 : "+customer_list[x][1]);
		
		System.out.println("입금하실 금액을 입력하여 주십시오.");
		Scanner sc2 = new Scanner(System.in);
		long temp1 = sc2.nextLong();
		long temp2 = Long.parseLong(customer_list[x][2]);
		
		System.out.println("예금액 : " + temp1 + "원");
		System.out.println("예금 결과 : " + (temp1+temp2) + "원");
		customer_list[x][2] = String.valueOf((temp1+temp2));
		
	}
	
	void decrease_money() {
		
		int x = Check_customerInfo(); // 고객이 입력한 계좌번호를 대조하여 어느 배열에 해당 정보가 저장되어 있는지를 출력합니다.
		if(x == (-1)) { // 그러나 계좌번호 입력에 5번 실패하면 Check_customerInfo()는 -1을 출력하여 x = -1 이 됩니다.
			return; // return값이 없는 return을 사용하여 이후의 업무를 진행하지 않고 종료시킵니다.
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
		
		System.out.println("출금액 : " + temp1 + "원");
				
		System.out.println("출금 결과 : " + (temp2-temp1) + "원");
		customer_list[x][2] = String.valueOf((temp1-temp2));
	}
	
	int Check_customerInfo() { // 등록된 계좌번호가 맞는지 확인하는 메서드입니다. 현재 업무를 진행 중인 고객이 등록된 배열의 순서를 출력합니다.
		
		int X = 0;
		String y = "";
		int mistake = 0;
		boolean isOurCustomer = true;
		
		customer_check : while(isOurCustomer) { // 이 코드 다른 곳에도 필요
			
			System.out.println("본인 명의의 계좌 번호를 입력하여 주십시오.");
			Scanner sc1 = new Scanner(System.in);
			
			y = sc1.nextLine();
		
			for(int i=0; i<customer_count; i++) { // 배열 내에 해당 계좌번호가 존재하는지 확인합니다.
				if(customer_list[i][0].equals(y)) {
					X = i; // 해당 계좌번호가 존재한다면 대조 작업을 중단하고, X에 해당 고객이 입력된 배열 순서를 저장합니다.
					break customer_check; // while문을 종료시키기 때문에 4번 입력 실수한 고객이 5번째에 성공한다 하더라도, 이후의 업무를 진행하지 않아 mistake가 늘어나지 않습니다.
				}
			}
			
			System.out.println("해당 계좌를 찾을 수 없습니다."); // 대조 결과 해당 계좌번호가 존재하지 않을 경우 도달하게 됩니다.
			mistake++;
			
			if(mistake>=5) { // 그리고 잘못 입력된 횟수가 5번을 넘으면 업무를 종료합니다.
				System.out.println("5회 이상 잘못 입력하셨습니다.");
				System.out.println("처음부터 다시 진행해주세요.");
				X = -1;
				// X는 기본적으로 고객이 저장된 배열의 순서를 출력하는 변수이지만, -1은 배열의 순서상 존재할 수 없는 숫자입니다.
				// X = -1 을 출력함으로써 잘못 입력된 횟수가 5번을 넘었음을 확인할 수 있도록 합니다.
				isOurCustomer = false;
				break customer_check;
			}
		}
		return X; 
	}
	
}
