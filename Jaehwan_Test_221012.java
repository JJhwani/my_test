package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221012 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("원하시는 정수를 입력하세요. --> ");
		int N = sc.nextInt();
		System.out.println("==========================");
		sc.close();
		
		for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                System.out.print(" ");
            }
            for(int j = 0; j < i; j++){
                System.out.print("*");
            }
            for(int j = 0; j < i-1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
 

        for(int i = 0; i < N; i++){
            for(int j = 0; j < i; j++){
                System.out.print(" ");
            }
            for(int j = i; j < N; j++){
                System.out.print("*");
            }
            for(int j = i+1; j < N; j++){
                System.out.print("*");
            }
            System.out.println();
		}

		
		
	}
}
