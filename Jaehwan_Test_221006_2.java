package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221006_2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수 두 개를 입력해주세요.");
		int num1 = sc.nextInt();
		sc.nextLine();
		int num2 = sc.nextInt();
		sc.nextLine();
		
		System.out.println(
				"원하시는 사칙연산을 선택해주세요."+System.lineSeparator()+"1:더하기"+System.lineSeparator()
				+"2:빼기"+System.lineSeparator()+"3:곱하기"+System.lineSeparator()+"4:나누기");
		
		int cho = sc.nextInt();
		sc.nextLine();
		sc.close();
		
		if(cho==1)
			System.out.println("답 : "+(int)(num1+num2));
		else if(cho==2)
			System.out.println("답 : "+(int)(num1-num2));
		else if(cho==3)
			System.out.println("답 : "+(int)(num1*num2));
		else if(cho==4)
			if(num1%num2==0)
				System.out.println("답 : "+(int)(num1/num2));
			else if(num1%num2>0)
				System.out.println("답 : "+(double)num1/(double)num2);
	}

}
