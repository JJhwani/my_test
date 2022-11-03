package JavaMethod;

import java.util.Scanner;

public class Ex4 {
	public static void input() {
		Scanner sc = new Scanner(System.in);
		int n= sc.nextInt();
		sc.close();
		swap(n);
	}
	public static void swap(int n) {
		String swap = Integer.toBinaryString(n);
		StringBuffer sb = new StringBuffer(swap);
		if(sb.length()<16) {
			for(int i=0;i<16-sb.length();) {
				sb.insert(0, "0");
			}
		}
		for(int i=4;i<24;i+=5) {
			sb.insert(i," ");
		}
		System.out.println(sb);
		
	}
	public static void main(String[] args) {
		System.out.print("0~32767까지의 정수 입력 : ");
		input();
	}
}