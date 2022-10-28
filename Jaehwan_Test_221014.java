package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221014 {

public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("10자리의 2진수를 입력하세요!!!");
		
		String str = sc.nextLine();
		String [] sc1 = str.split(" ");
		
		System.out.print("입력하신 수는 : [");
				
		for(int i=0; i<sc1.length; i++) {
			System.out.print(sc1[i]+",");
		}
		System.out.print("]");
		System.out.println("");
		
		sc.close();
		
		int [] sc2 = new int[sc1.length];
		
		for(int i=0; i<sc1.length; i++) {
			sc2[i] = Integer.parseInt(sc1[i]);
		}
		
		int [] exp = new int[sc2.length];
		for(int i=0; i<sc2.length; i++) {
			if(i>0) {
				exp[i] = (int)(Math.pow(2, i));
			}
			else if(i==0) {
				exp[i] = 1;
			}
		}
		
		reverse(exp);

		int sum = sumOf(exp, sc2);
		System.out.println("입력하신 수의 10진수 수는: "+sum+"입니다.");

	}

	static void swap(int []exp, int ex1, int ex2) {
		
		int temp = exp[ex1];
		exp[ex1] = exp[ex2];
		exp[ex2] = temp;
	
	}
	
	static void reverse(int []exp) {
		
		for(int i=0; i<exp.length/2; i++) {
			int ex1 = i;
			int ex2 = exp.length-i-1;
			swap(exp, ex1, ex2);
			for(int j=0; j<exp.length; j++) {
			}
		}
	}

	static int sumOf(int exp[], int sc2[]) {
		
		int sum = 0;
		for(int i=0; i<exp.length; i++) {
			sum += sc2[i]*exp[i];
		}
		
		return sum;

	}
}