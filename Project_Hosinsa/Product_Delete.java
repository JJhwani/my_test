package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Product_Delete {
	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String user="hosinsa";
	private static String pwd="1234";
	
	public static void main(String[] args) {

	}
	
	static void stage() {
		Main main = new Main();
		main.play();
	}
	
	public static void productDelete() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("제품삭제 작업을 진행합니다.");
		System.out.println("=============================");
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			
			String sql= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
				
				if (cnt==1 || cnt>1) {
					System.out.println("입력한 제품번호의 제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
					deleteWorkMain();
				}
				else if(cnt==0) {
					System.out.println("=============================");
					System.out.println("작업을 종료합니다.");
					stage();
				}
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void deleteWorkMain() {
		Main main = new Main();
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
										
			String sql = "DELETE FROM HOSINSA WHERE 제품번호 ="+pNumber;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("제품번호 : "+pNumber+" 제품을 삭제하였습니다.");

			} catch(Exception e) {
				e.printStackTrace();
			}
			
		Scanner sc1 = new Scanner(System.in);
		System.out.println();
		System.out.println("작업을 계속하시겠습니까?");
		System.out.print("1:계속   2:이전단계로 이동   3:프로그램 종료"+"\n");
		int no = sc1.nextInt();
		
		if(no==1) {
			productDelete();
		}
		else if(no==2) {
			System.out.println("이전단계로 이동합니다.");
			stage();
		}
		if(no==3) {
			System.out.println("프로그램을 종료합니다.");
		}
	}
}
