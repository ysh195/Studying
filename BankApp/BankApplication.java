package test;

import java.util.Scanner;

public class BankApplication{
	
	
	public static void main(String[] args) {
						
		System.out.println("안녕하세요? 진행하실 업무를 선택해주세요.");
		
		BankAppProcess bap = new BankAppProcess();
		
		while(bap.work_continue) {
			
			bap.Hello();
			
			bap.work_process();
		}
	} 
	
	
	
	
	
	
	
	
	
	
	

	

}