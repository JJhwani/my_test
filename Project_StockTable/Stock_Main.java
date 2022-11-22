package Project_StockTable;

import java.util.*;

public class Stock_Main {
	public static void main(String[] args) {
		mainMenu();
	}
	
	static void mainMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("         화성가즈아~ 프로그램을 시작합니다.        ");
		System.out.println("=========================================");
		System.out.println("=    1.주식시황보기           2.관심종목관리    =");
		System.out.println("=    3.내ㅇ주식관리           4.프로그램종료    =");
		System.out.println("=========================================");
		
		System.out.println("원하시는 메뉴를 입력해주세요.");
		System.out.print("메뉴 번호 : ");
		int number = sc.nextInt();
		System.out.println();
		
		Stock_Market_Situation smm = new Stock_Market_Situation();
		Stock_Interested_Item sii = new Stock_Interested_Item();
		Stock_My_Stock sms = new Stock_My_Stock();
		
		while(true) {
			
			if(number==1) {
				System.out.println("주식시황보기로 이동합니다.");
				smm.stock_ms_main();
				break;
			}
			else if(number==2) {
				System.out.println("관심종목관리로 이동합니다.");
				sii.stock_ii_main();
				break;
			}
			else if(number==3) {
				System.out.println("내ㅇ주식관리로 이동합니다.");
				sms.stock_ms_main();
				break;
			}
			else if(number==4) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
				System.out.println("");
				mainMenu();
				break;
			}			
		}
		mainMenu();
	}
}