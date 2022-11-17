package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Product_Add {
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
	
	public static void productAdd() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("자료추가 작업을 진행합니다.");
		System.out.println("=============================");
		System.out.println("추가할 제품번호를 입력해주세요.");
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			
			String sql= "SELECT COUNT(*)FROM HOSINSA WHERE 제품번호 = "+pNumber;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
				
				if(cnt==0) {
					System.out.println("=============================");
					System.out.println("작업을 계속 진행합니다.");
					addWorkMain();
				}
				else if (cnt==1 || cnt>1) {
					System.out.println("이미 같은 제품번호의 제품이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 종료합니다.");
					stage();
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	static void addWorkMain() {
		Main main = new Main();
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("제품번호 : ");
		String pNumber = sc.nextLine();
		System.out.print("카테고리 : ");
		String category = sc.nextLine();
		System.out.print("브랜드 : ");
		String brand = sc.nextLine();
		System.out.print("제품명 : ");
		String pName = sc.nextLine();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		System.out.print("재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		System.out.println();
		System.out.println("입력하신 제품의 내용");
		System.out.println("제품번호 : "+pNumber);
		System.out.println("카테고리 : "+category);
		System.out.println("브랜드 : "+brand);
		System.out.println("제품명 : "+pName);
		System.out.println("가격 : "+price);
		System.out.println("재고 : "+stock);
		System.out.print("위 내용으로 제품을 추가하시겠습니까?"+"\n"+"y:ok \t"+"n:no \n");
		System.out.println();
		
		String yes = sc.nextLine();
		
		if(yes.equalsIgnoreCase("y")) {
			Connection conn = null;
			//Statement stmt = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,pwd);
											
				String sql = "INSERT INTO HOSINSA VALUES(SEQ_NUM.NEXTVAL,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, pNumber);
				pstmt.setString(2, category);
				pstmt.setString(3, brand);
				pstmt.setString(4, pName);
				pstmt.setInt(5, price);
				pstmt.setInt(6, stock);
				
				rs = pstmt.executeQuery();
				
				String sql2 = "SELECT * FROM HOSINSA WHERE 제품번호 = "+pNumber;
				pstmt = conn.prepareStatement(sql2);
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
				
				System.out.println(pNumber+" 제품을 추가하였습니다.");

			} catch(Exception e) {
				e.printStackTrace();
			}
			
			Scanner sc1 = new Scanner(System.in);
			System.out.println();
			System.out.println("작업을 계속하시겠습니까?");
			System.out.print("1:계속   2:프로그램종료   3:이전단계로 이동"+"\n");
			int no = sc1.nextInt();
			
			if(no==1) {
				productAdd();
			}
			else if(no==2) {
				System.out.println("프로그램을 종료합니다.");
			}
			if(no==1) {
				System.out.println("이전단계로 이동합니다.");
				main.play();
			}
		}
		else if(yes.equalsIgnoreCase("n")) {
			addWorkMain();
		}
	}
}
