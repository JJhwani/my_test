package JavaMethod;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Ex5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정렬전:");
		String str = sc.nextLine();
		sc.close();
		String[] arr = str.split(" ");
		
		arrayUpSort(arr);
		arrayDownSort(arr);
	}
	static void arrayUpSort(String[] arr) {
		Arrays.sort(arr);
		System.out.println("오름차순 정렬:");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	static void arrayDownSort(String[] arr) {
		Arrays.sort(arr, Comparator.reverseOrder());
		System.out.println("내림차순 정렬:");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}
