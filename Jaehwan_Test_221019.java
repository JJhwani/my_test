package my_test;

import java.util.Scanner;


public class Jaehwan_Test_221019 {

	public static void main(String[] args) {
		
		System.out.println("===========호신사===========");
		System.out.println("번호  품목  사이즈  가격    재고");
		Subject su1 = new Subject(1,"셔츠","XL",19900,3);
		Subject su2 = new Subject(2,"바지","L",89000,5);
		Subject su3 = new Subject(3,"코트","XL",29900,7);
		Subject su4 = new Subject(4,"양말","m",1000,9);
		String str1 = su1.info();
		String str2 = su2.info();
		String str3 = su3.info();
		String str4 = su4.info();
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		System.out.println("==========================");
		
		Scanner sc = new Scanner(System.in);
		
		int ord = 20;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int x1 = 0;
		int x2 = 0;
		int x3 = 0;
		int x4 = 0;
		int y1 = 0;
		int y2 = 0;
		int y3 = 0;
		int y4 = 0;
		
		int total = 0;
		
		while(ord !=0) {
			System.out.print("원하는 품목의 번호를 입력해주세요. (계산하기->0)");
			ord = sc.nextInt();
			
			if(ord==1 && su1.amount-num1!=0) {
				x1 = su1.price;
				y1++;
				num1++;
				System.out.println("셔츠 가격은"+su1.price+"입니다.");
				System.out.println("셔츠의 재고는"+(su1.amount-num1)+"개 남았습니다.");
			}
			else if(ord==2 && su2.amount-num2!=0) {
				x2 = su2.price;
				y2++;
				num2++;
				System.out.println("바지 가격은"+su2.price+"입니다.");
				System.out.println("바지의 재고는"+(su2.amount-num2)+"개 남았습니다.");
			}
			else if(ord==3 && su3.amount-num3!=0) {
				x3 = su3.price;
				y3++;
				num3++;
				System.out.println("코트 가격은"+su3.price+"입니다.");
				System.out.println("코트의 재고는"+(su3.amount-num3)+"개 남았습니다.");
			}
			else if(ord==4 && su4.amount-num4!=0) {
				x4 = su4.price;
				y4++;
				num4++;
				System.out.println("양말 가격은"+su4.price+"입니다.");
				System.out.println("양말의 재고는"+(su4.amount-num4)+"개 남았습니다.");
			}

		total = (x1*num1)+(x2*num2)+(x3*num3)+(x4*num4);
		}
		System.out.println("=====================================");
		System.out.println("구매 목록");
		System.out.println("셔츠"+y1+"개 "+"바지"+y2+"개 "+"코트"+y3+"개 "+"양말"+y4+"개");
		System.out.println("=====================================");
		System.out.println("총 금액은 "+total+"입니다.");
		System.out.println("이용해주셔서 감사합니다.");
	}
}

class Subject{
	int number;
	String subject;
	String size;
	int price;
	int amount;
	
	Subject(int n, String su, String si, int pr, int am){
		number=n;
		subject=su;
		size=si;
		price=pr;
		amount=am;
	}
	
//	void setAmount(int am) {
//		this.amount = amount;
//		return (int)amount;
//	}
	
	
	
	String info() {
		return number+"    "+subject+"   "+size+"   "+price+"  "+amount;
	}
}
	

