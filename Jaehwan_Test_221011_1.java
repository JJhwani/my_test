package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221011_1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("0보다 크고 100보다 작은 정수를 입력하세요.");
		
		int N = sc.nextInt();
		int num1 = N/10; //십의 자리
		int num2 = N%10; //일의 자리
		int sum1 = num1+num2; //자리 수 합계
		int newNum1 = num2*10+sum1%10; //새로운 숫자
		int count1 = 1;
		sc.close();
	
		while(N!=newNum1) {
			num1 = num2;
			num2 = sum1%10;
			sum1 = num1+num2;	
			newNum1 = num2*10+sum1%10;
			count1++;
			if(newNum1==N) {
				break;
			}
		}
		System.out.println("사이클 횟수: "+count1);
		
		int N2 = sc.nextInt();
		int newNum2 = N2;
		int count2 = 0;
	
		while(true) {
			newNum2 = (newNum2%10)*10+(newNum2/10+newNum2%10)%10;
			count2++;
			if(newNum2==N2) {
				break;
			}
		}
		System.out.println("사이클 횟수: "+count2);
		
	}
}
