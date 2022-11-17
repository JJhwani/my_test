package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Product_Update {
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
	
	public static void productUpdate() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("제품수정 작업을 진행합니다.");
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
		
		System.out.println("=============================");
		System.out.println("1:제품번호 수정   2:카테고리 수정   3:브랜드 수정");
		System.out.println("4:제품명 수정    5:가격 수정      6:재고 수정");
		System.out.println("7:이전단계로 이동  8:프로그램 종료");
		System.out.print("작업번호 : ");
		int search = sc.nextInt();
		sc.nextLine();
						
		
		if(search>=1 && search<=8) {
			if(search==1) {
				System.out.println("제품번호 수정을 실행합니다.");
				updatePn();
			}
			else if(search==2) {
				System.out.println("카테고리 수정을 실행합니다.");
				updateCg();
			}
			else if(search==3) {
				System.out.println("브랜드 수정을 실행합니다.");
				updateB();
			}
			else if(search==4) {
				System.out.println("제품명 수정을 실행합니다.");
				updatePname();
			}
			else if(search==5) {
				System.out.println("가격 수정을 실행합니다.");
				updatePrice();
			}
			else if(search==6) {
				System.out.println("재고 수정을 실행합니다.");
				updateStock();
			}
			else if(search==7) {
				System.out.println("이전단계로 이동");
				stage();
			}
			else if(search==8) {
				System.out.println("프로그램 종료");
			}
		}
		else {
			System.out.println("작업번호를 확인하세요.");
			productUpdate();
		}
	}
	
	static void updatePn() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("변경 전 제품번호 : ");
		String pNumber1 = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 제품번호 : ");
		String pNumber2 = sc.nextLine();
		System.out.println();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			
			String sql= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber2;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
				
				if(cnt==0) {
					System.out.println("=============================");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if (cnt==1 || cnt>1) {
					System.out.println("이미 같은 제품번호의 제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 종료합니다.");
					updatePn();
				}
			}
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,pwd);
				stmt = conn.createStatement();
				
				String sql2= "UPDATE HOSINSA SET 제품번호 = "+pNumber2+" WHERE 제품번호 = "+pNumber1;
				rs = stmt.executeQuery(sql2);
				System.out.println("제품번호가"+pNumber1+"에서 "+pNumber2+"로 변경되었습니다.");
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber2;
				pstmt = conn.prepareStatement(sql3);
				
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
							
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		stage();
	}
	
	static void updateCg() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 카테고리 : ");
		String cg = sc.nextLine();
		
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
					System.out.println("=============================");
					System.out.println("제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if(cnt==0) {
					System.out.println("작업을 종료합니다.");
					updateCg();
				}
			}
			
			try {
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pwd);
				
				String sql2 = "UPDATE HOSINSA SET 카테고리 = '"+cg+"'"+"WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql3);
				
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
				
				String sql4= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql4);
				if(rs.next()) {
					System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 수정되었습니다.");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		stage();
	}
	
	static void updateB() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 브랜드 : ");
		String br = sc.nextLine();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			
			String sql= "SELECT COUNT(*)FROM HOSINSA WHERE 브랜드 = "+pNumber;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
				
				if (cnt==1 || cnt>1) {
					System.out.println("=============================");
					System.out.println("제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if(cnt==0) {
					System.out.println("작업을 종료합니다.");
					updateB();
				}
			}
			
			try {
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pwd);
				
				String sql2 = "UPDATE HOSINSA SET 브랜드 = '"+br+"'"+"WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql3);
				
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
				
				String sql4= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql4);
				if(rs.next()) {
					System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 수정되었습니다.");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		stage();
	}
	
	static void updatePname() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 제품명 : ");
		String pName = sc.nextLine();
		
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
					System.out.println("=============================");
					System.out.println("제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if(cnt==0) {
					System.out.println("작업을 종료합니다.");
					updatePname();
				}
			}
			
			try {
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pwd);
				
				String sql2 = "UPDATE HOSINSA SET 제품명 = '"+pName+"'"+"WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql3);
				
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
				
				String sql4= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql4);
				if(rs.next()) {
					System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 수정되었습니다.");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		stage();
	}
	
	static void updatePrice() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
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
					System.out.println("=============================");
					System.out.println("제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if(cnt==0) {
					System.out.println("작업을 종료합니다.");
					updatePname();
				}
			}
			
			try {
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pwd);
				
				String sql2 = "UPDATE HOSINSA SET 가격 = '"+price+"'"+"WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql3);
				
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
				
				String sql4= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql4);
				if(rs.next()) {
					System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 수정되었습니다.");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		stage();
	}
	
	static void updateStock() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.println();
		System.out.print("변경 후 재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
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
					System.out.println("=============================");
					System.out.println("제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
				}
				else if(cnt==0) {
					System.out.println("작업을 종료합니다.");
					updatePname();
				}
			}
			
			try {
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url,user,pwd);
				
				String sql2 = "UPDATE HOSINSA SET 재고 = '"+stock+"'"+"WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				
				String sql3 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql3);
				
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
				
				String sql4= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql4);
				if(rs.next()) {
					System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 수정되었습니다.");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		stage();
	}
}
