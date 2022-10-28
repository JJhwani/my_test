package my_test;

import java.util.Random;

public class Jaehwan_Test_221011_2 {

	public static void main(String[] args) {
		
		Random rd = new Random();
		int N = rd.nextInt(100);
		System.out.println("시작 숫자는 "+N+"입니다.");
		
		int num1 = N/10; //십의 자리
		int num2 = N%10; //일의 자리
		int sum1 = num1+num2; //자리 수 합계
		int newNum1 = num2*10+sum1%10; //새로운 숫자
		int count1 = 1;
		int temp = N;
	
		while(true) {
			num1 = num2;
			num2 = sum1%10;
			sum1 = num1+num2;	
			newNum1 = num2*10+sum1%10;
			count1++;
			System.out.println("");
			System.out.println(N);
			System.out.println(num1+"+"+num2+" = "+sum1+"입니다.");
			System.out.println("새로운 숫자는 "+newNum1+"입니다.");
			System.out.println("");
			N = newNum1;
			
			if(newNum1==temp) {
				break;
			}
		}
		System.out.println("정답까지 총 반복횟수는 "+count1+"입니다.");
		
//		int N2 = rd.nextInt();
//		int newNum2 = N2;
//		int count2 = 0;
//	
//		while(true) {
//			newNum2 = (newNum2%10)*10+(newNum2/10+newNum2%10)%10;
//			count2++;
//			if(newNum2==N2) {
//				break;
//			}
//		}
//		System.out.println("사이클 횟수: "+count2);
		
	}
}
