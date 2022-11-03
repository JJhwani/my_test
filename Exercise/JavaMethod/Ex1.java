package JavaMethod;

import java.util.Scanner;

public class Ex1 {
	static void makeId() {
		Scanner sc = new Scanner(System.in);
		String id = "abcd";
		System.out.println("사용하실 ID를 입력하세요.");
		String newId = sc.nextLine();
		//sc.close();
		checkId(id,newId);
	}
	static void checkId(String id, String newId){
		if(id.equals(newId)) {
			System.out.println("이미 사용중인 ID 입니다.");
			makeId();
		}
		else {
			System.out.println(newId+"은 사용가능 한 ID 입니다.");
		}
	}
	public static void main(String[] args) {
		makeId();
	}
}
