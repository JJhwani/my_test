package JavaMethod;

import java.util.Scanner;

public class Ex2 {
	static void search() {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색하실 주소를 입력하세요.");
		String address = sc.nextLine();
		//sc.close();
		zipCode(address);
	}
	static void zipCode(String address){
		String zc1 = "111-111";
		String zc2 = "121-121";
		String zc3 = "131-131";
		String zc4 = "123-123";
		if(address.equals("경기 수원시 팔달구 매산로1가")) {
			System.out.println("검색하신 주소 "+address+"의 우편번호는");
			System.out.println(zc1+" 입니다.");
		}
		else if(address.equals("경기 수원시 팔달구 인계동")) {
			System.out.println("검색하신 주소 "+address+"의 우편번호는");
			System.out.println(zc2+" 입니다.");
		}
		else if(address.equals("경기 수원시 권선구 권선동")) {
			System.out.println("검색하신 주소 "+address+"의 우편번호는");
			System.out.println(zc3+" 입니다.");
		}
		else if(address.equals("경기 수원시 권선구 세류동")) {
			System.out.println("검색하신 주소 "+address+"의 우편번호는");
			System.out.println(zc4+" 입니다.");
		}
		else {
			System.out.println("검색하신 주소의 우편번호를 찾을 수 없습니다.");
			System.out.println("다시 입력해주세요.");
			search();
		}

	}
	public static void main(String[] args) {
		search();
	}
}
