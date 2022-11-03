package JavaMethod;

import java.util.Scanner;

public class Ex3 {
	public static void input() {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt();
		sc.close();
		output(n);
	}
	public static void output(int n) {
		if(n>1 && n<10) {
			for(int i=1; i<10; i++) {
				System.out.println(n+" * "+i+" = "+n*i);
			}
		}
		else {
			System.out.println("2~9까지의 정수를 입력해주세요.");
		}
	}
	public static void main(String[] args) {
		System.out.print("구구단 출력을 원하는 숫자 입력 : ");
		input();
	}
}
