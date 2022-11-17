package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Product_Search {
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
	
	public static void productSearch() {
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("제품검색 작업을 진행합니다.");
		System.out.println("=============================");
		System.out.println("1:전체제품 검색   2:제품번호 검색   3:카테고리 검색   4:브랜드 검색");
		System.out.println("5:제품명 검색    6:가격 검색      7:재고 검색");
		System.out.println("8:이전단계로 이동  9:프로그램 종료");
		System.out.print("작업번호 : ");
		int search = sc.nextInt();
		sc.nextLine();
		
		if(search>=1 && search<=9) {
			if(search==1) {
				System.out.println("전체제품 검색을 실행합니다.");
				searchAll();
			}
			else if(search==2) {
				System.out.println("제품번호 검색을 실행합니다.");
				searchPn();
			}
			else if(search==3) {
				System.out.println("카테고리 검색을 실행합니다.");
				searchCg();
			}
			else if(search==4) {
				System.out.println("브랜드 검색을 실행합니다.");
				searchB();
			}
			else if(search==5) {
				System.out.println("제품명 검색을 실행합니다.");
				searchPname();
			}
			else if(search==6) {
				System.out.println("가격 검색을 실행합니다.");
				searchPrice();
			}
			else if(search==7) {
				System.out.println("재고 검색을 실행합니다.");
				searchStock();
			}
			else if(search==8) {
				System.out.println("이전단계로 이동");
				stage();
			}
			else if(search==9) {
				System.out.println("프로그램 종료");
			}
		}
		else {
			System.out.println("작업번호를 확인하세요.");
			productSearch();
		}
	}
		
	static void searchAll() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
						
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			
			String sql2= "SELECT COUNT(*)FROM HOSINSA";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchPn() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pnumber = sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pnumber;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pnumber;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchCg() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("카테고리 : ");
		String cg = sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 카테고리 LIKE"+"'%"+cg+"%'";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 카테고리 LIKE"+"'%"+cg+"%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchB() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("브랜드 : ");
		String br = sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 브랜드 LIKE "+"'%"+br+"%'";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 브랜드 LIKE"+"'%"+br+"%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchPname() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품명 : ");
		String pn = sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 제품명 LIKE "+"'%"+pn+"%'";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 제품명 LIKE"+"'%"+pn+"%'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchPrice() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("가격의 범위를 입력해주세요.");
		int price1 = sc.nextInt();
		int price2 = sc.nextInt();
		System.out.print(price1+" ~ "+price2);
		
		sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 가격 BETWEEN "+price1+" AND "+price2+" ORDER BY 가격";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 가격 BETWEEN "+price1+" AND "+price2;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
	
	static void searchStock() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("재고의 범위를 입력해주세요.");
		int stock1 = sc.nextInt();
		int stock2 = sc.nextInt();
		System.out.print(stock1+" ~ "+stock2);
		
		sc.nextLine();
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url,user,pwd);
			
			String sql = "SELECT * FROM HOSINSA WHERE 재고 BETWEEN "+stock1+" AND "+stock2+" ORDER BY 재고";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			System.out.println("SEQ_NUM  제품번호  카테고리   브랜드        제품명        가격    재고 ");
			while(rs.next()) {
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.print(rs.getString(5)+"\t");
				System.out.print(rs.getInt(6)+"\t");
				System.out.print(rs.getInt(7)+"\t\n");
				System.out.println("===============================================");				
			}
			String sql2= "SELECT COUNT(*)FROM HOSINSA WHERE 재고 BETWEEN "+stock1+" AND "+stock2;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		productSearch();
	}
}

