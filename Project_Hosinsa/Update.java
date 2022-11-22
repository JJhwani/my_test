package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static Scanner sc = new Scanner(System.in);
	static int keyword;
	static String num;
	static int num2;
	static String stock="";
	
	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String user="hosinsa";
	private static String pwd="1234";
	
	static {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	static void updateStart() {
		System.out.print("입/출고할 상품코드(7자리)를 입력해 주세요. >>");
		keyword=sc.nextInt();
		sc.nextLine();
		String sql="SELECT * FROM MUSINSA WHERE PRODUCT_NUM="+keyword;
		try {
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				viewProduct();
			}else {
				System.out.println("= 상품 결과가 없습니다.=");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	static void viewProduct() throws SQLException {
		System.out.println("번호        상품명                            품번         브랜드         카테고리      가격     재고");
		System.out.println("===========================================================================================");
		try{
			System.out.print(rs.getInt("SEQ_NUM")+"\t");
			System.out.print(rs.getString("PRODUCT")+"\t");
			System.out.print(rs.getString("PRODUCT_NUM")+"\t");
			System.out.print(rs.getString("BRAND")+"\t");
			System.out.print(rs.getString("CATEGORY")+"\t");
			System.out.print(rs.getInt("PRICE")+"\t");
			System.out.print(rs.getInt("STOCK")+"\t");
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println();				
		System.out.print("작업을 선택하세요 - 1.입고 2.출고 3.돌아가기 >>");		
		num=sc.nextLine();	
		
		if(num.equals("1")) {
			stock="+";
			updateProduct();
		}else if(num.equals("2")) {
			stock="-";
			updateProduct();
		}else if(num.equals("3")){
		}else {
			System.out.println("올바른 번호를 입력해 주세요.");
			viewProduct();
		}
	}
	static void updateProduct() throws SQLException {	
		System.out.print("수량을 입력하세요.>>");
		num2=sc.nextInt();
		sc.nextLine();
		//출고수량이 현재 재고보다 많을 경우
		if(num.equals("2") && rs.getInt("STOCK")-num2<0) {
			System.out.println("출고 수량이 재고량보다 많습니다. 올바른 수량을 입력해 주세요.");
			System.out.println();
			viewProduct();
		}else {
			String sql2="UPDATE MUSINSA SET STOCK=STOCK"+stock+num2+" WHERE PRODUCT_NUM="+keyword;	
			stmt.executeUpdate(sql2);
			System.out.println("재고가 "+num2+"개 "+((stock=="+")?"추가":"감소")+"되었습니다.");	
			sql2="SELECT STOCK FROM MUSINSA WHERE PRODUCT_NUM="+keyword;
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("현재 재고는 "+rs.getString("STOCK")+"개 입니다.");
			}
		}
	}	
}
