package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221005 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("이름을 입력하세요"); //System.lineSeparator() 줄바꿈
		String input1 = scanner.nextLine();
		//System.out.println("이름 : "+input1);
		
		System.out.println("나이를 입력하세요");
		int input2 = scanner.nextInt();
		
		System.out.println("연락처를 입력하세요");
		String input3 = scanner.nextLine();
		String str = input3; //대상 문자열

				
		System.out.println("취미를 입력하세요");
		String input4 = scanner.nextLine();

		scanner.close();
		
		System.out.println("이 름 : "+input1);
		System.out.println("나 이 : "+input2);
		
		System.out.println("연락처 : "+str.substring(0, 3)+"-"+str.substring(3, 7)+
				"-"+str.substring(7, 11));
		System.out.println("취 미 : "+input4);
		
	}

}
