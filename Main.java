package com.ezen.ex02;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("호신사 프로그램에 접속합니다.");
		System.out.println("원하는 작업번호를 입력해주세요.");
		System.out.println("=============================");
		System.out.print("1.자료추가"+"\t"+"2.자료삭제"+"\t"+"3.자료수정 update"+"\n"+"4.전체자료출력"+"\t"+"5.검색"+"\t"+"0.종료"+"\n");
		System.out.println("=============================");
		System.out.print("작업번호 : ");
		
		int num = sc.nextInt();
		
		sc.close();
		if(num==1) {
			
		}
		else if(num==2) {
			
		}
		else if(num==3) {
			
		}
		else if(num==4) {
			
		}
		else if(num==5) {
			
		}
		else if(num==0) {
			System.out.println("프로그램을 종료합니다.");
		}
		else {
			System.out.println("번호를 다시 입력해주세요.");
		}
	}

}
