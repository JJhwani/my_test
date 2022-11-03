package JavaMethod;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex1_2 {
	
	static ArrayList<String> idlist = new ArrayList<String>();
	static String y = "y";
	static String n = "n";
	
	static void makeId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("사용하실 ID를 입력하세요.");
		String newId = sc.nextLine();
		checkId(idlist,newId,sc);
	}
	static void checkId(ArrayList<String> idlist, String newId, Scanner sc){
		if(idlist.size()==0) {
			idlist.add(newId);
			System.out.println(newId+"은 사용가능 한 ID 입니다.");
			System.out.println("ID 생성을 계속하시겠습니까?");
			System.out.println("y : 계속    n : 프로그램 종료");
			String choice = sc.nextLine();
			if(choice.equals(y)) {
				makeId();
			}
			else if(choice.equals(n)) {
				System.out.println("ID 생성 종료");
			}
		}
		else if(idlist.contains(newId)) {
			System.out.println("이미 사용중인 ID 입니다.");
			makeId();
		}
		else {
			System.out.println(newId+"은 사용가능 한 ID 입니다.");
			idlist.add(newId);
			System.out.println("ID 생성을 계속하시겠습니까?");
			System.out.println("y : 계속    n : 프로그램 종료");
			String choice = sc.nextLine();
			if(choice.equals(y)) {
				makeId();
			}
			else if(choice.equals(n)) {
				System.out.println("ID 생성 종료");
			}
		}
	}
	public static void main(String[] args) {
		makeId();
		System.out.println(idlist);
	}
}
