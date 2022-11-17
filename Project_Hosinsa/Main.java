package Project_Hosinsa;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		play();
	}	
	public static void play() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=============================");
		System.out.println("호신사 프로그램에 접속합니다.");
		System.out.println("원하는 작업번호를 입력해주세요.");
		System.out.println("=============================");
		System.out.print("1.자료추가"+"\t"+"2.자료삭제"+"\t"+"3.자료수정 update"+"\n"+"4.검색"+"\t"+"0.종료"+"\n");
		System.out.println("=============================");
		System.out.print("작업번호 : ");
		
		int num = sc.nextInt();
		
		Product_Add pAdd = new Product_Add();
		Product_Delete pDel = new Product_Delete();
		Product_Update pUd = new Product_Update();
		Product_Search pSh = new Product_Search();
		
		
		while(true) {
			if(num==1) {			
				pAdd.productAdd();
				break;
			}
			else if(num==2) {
				pDel.productDelete();
				break;
			}
			else if(num==3) {
				pUd.productUpdate();
				break;
			}
			else if(num==4) {
				pSh.productSearch();
				break;
			}
			else if(num==0) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
				break;
			}
		}
		if(num!=0) {
			repeat();
		}
	}
	
	public static void repeat() {
		play();
	}
}