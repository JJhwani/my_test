package JavaMethod;

import java.util.Scanner;

public class Ex6 {
	public static void input() {
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		sc.close();
		years(y);
	}
	public static void years(int y) {
		String str1="년도는 윤년입니다.";
		String str2="년도는 윤년이 아닙니다.";
		if(y%4==0 && y%100!=0) {
			System.out.println(y+str1);
		}
		else {
			System.out.println(y+str2);
		}
	}
	public static void main(String[] args) {
		System.out.print("년도 입력 : ");
		input();
	}

}
