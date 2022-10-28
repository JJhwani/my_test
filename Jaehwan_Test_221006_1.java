package my_test;

import java.util.Scanner;

public class Jaehwan_Test_221006_1 {

	public static void main(String[] args) {
		
		int answer;
		answer = 89;
		
				
		Scanner sc = new Scanner(System.in);
		
		System.out.println("지금 당신은 쇠사슬에 묶인채로 강물에 빠졌습니다.");
		System.out.println("쇠사슬을 풀어보려 했지만 쇠사슬은 자물쇠에 잠겨있는 상태입니다.");
		System.out.println("다행히 그 자물쇠는 두자리 숫자로 이루어져있습니다.");
		System.out.println("숨이 더 차오기전에 탈출해야합니다.");
		int try1 = sc.nextInt();
		sc.nextLine();
		sc.close();
		
		if(try1==answer) {
			System.out.println("축하합니다."+System.lineSeparator()+"당신은 자물쇠를 풀고 탈출해 성공하였습니다.");
		}
		else {
			System.out.println("자물쇠를 여는데 실패했습니다."+System.lineSeparator()+"숨이 점점 차오릅니다.");
			if(try1<answer) {
				System.out.println("왠지 이 숫자보다 높은 숫자일 것 같습니다.");
			}
			if(try1>answer) {
				System.out.println("왠지 이 숫자보다 낮은 숫자일 것 같습니다.");
			}
								
			int try2 = sc.nextInt();
			sc.nextLine();
			
			if(try2==answer) {
				System.out.println("축하합니다."+System.lineSeparator()+"당신은 자물쇠를 풀고 탈출해 성공하였습니다.");
			}
			else {
				System.out.println("자물쇠를 여는데 실패했습니다."+System.lineSeparator()+"숨이 점점 차오릅니다.");
				if(try2<answer) {
					System.out.println("왠지 이 숫자보다 높은 숫자일 것 같습니다.");
				}
				if(try2>answer) {
					System.out.println("왠지 이 숫자보다 낮은 숫자일 것 같습니다.");
				}
				
				int try3 = sc.nextInt();
				sc.nextLine();
				
				if(try3==answer) {
					System.out.println("축하합니다."+System.lineSeparator()+"당신은 자물쇠를 풀고 탈출해 성공하였습니다.");
				}
				else {
					System.out.println("자물쇠를 여는데 실패했습니다."+System.lineSeparator()+"숨이 막히기 시작합니다.");
					if(try3<answer) {
						System.out.println("왠지 이 숫자보다 높은 숫자일 것 같습니다.");
					}
					if(try3>answer) {
						System.out.println("왠지 이 숫자보다 낮은 숫자일 것 같습니다.");
					}
				
					int try4 = sc.nextInt();
					sc.nextLine();
				
					if(try4==answer) {
						System.out.println("축하합니다."+System.lineSeparator()+"당신은 자물쇠를 풀고 탈출해 성공하였습니다.");
					}
					else {
						System.out.println("자물쇠를 여는데 실패했습니다."+System.lineSeparator()+"당신은 한계에 다다르기 시작합니다.");
						if(try4<answer) {
							System.out.println("왠지 이 숫자보다 높은 숫자일 것 같습니다.");
						}
						if(try4>answer) {
							System.out.println("왠지 이 숫자보다 낮은 숫자일 것 같습니다.");
						}
			
						int try5 = sc.nextInt();
						sc.nextLine();
						
						if(try5==answer) {
							System.out.println("축하합니다."+System.lineSeparator()+"당신은 자물쇠를 풀고 탈출해 성공하였습니다.");
						}
						else {	
							System.out.println("자물쇠를 여는데 실패했습니다."+System.lineSeparator()+"당신은 사망했습니다.");
						}
					}
				}
			}
		}
	}
}
