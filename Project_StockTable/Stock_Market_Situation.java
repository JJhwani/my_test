package Project_StockTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Stock_Market_Situation {
	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String user="stocktable";
	private static String pwd="1234";

	static Connection conn;
	static Statement stmt;
	static PreparedStatement pstmt;
	static ResultSet rs;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

	}
	
	static void stock_ms_main() {
		System.out.println("");
		System.out.println("                 주식시황보기                ");
		System.out.println("=========================================");
		System.out.println("=    1.시황업데이트           2.주식시황보기    =");
		System.out.println("=    3.이전단계이동           4.프로그램종료    =");
		System.out.println("=========================================");
		
		System.out.println("원하시는 메뉴를 입력해주세요.");
		System.out.print("메뉴 번호 : ");
		int number = sc.nextInt();
		System.out.println();
		
		Stock_Main sm = new Stock_Main();
		
		while(true) {
			if(number==1) {
				System.out.println("시황업데이트로 이동합니다.");
				stock_ms_update();
				break;
			}
			else if(number==2) {
				System.out.println("주식시황보기로 이동합니다.");
				stock_ms_view();
				break;
			}
			else if(number==3) {
				System.out.println("이전단계로 이동합니다.");
				System.out.println("");
				return;
			}
			else if(number==4) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
				System.out.println("");
				stock_ms_main();
				break;
			}
		}
	}
	
	static void stock_ms_update() {
		System.out.println("");
		System.out.println("시황업데이트를 실행합니다.");
		ArrayList<Stock_Info> silist = Stock_Setting.stock_Otable();
		
		try {
			Class.forName(driver);
			System.out.println("드라이브 로드 성공");
			conn = DriverManager.getConnection(url,user,pwd);
			stmt = conn.createStatement();
			
			for(Stock_Info sinfo : silist) {
				String sql="SELECT COUNT(*)FROM STOCK_TABLE WHERE 종목코드='"+sinfo.stockCode+"'";
				String scode=sinfo.stockCode;
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
					if(cnt==1) {
						String sql2 = "UPDATE STOCK_TABLE SET 시총순위=?, 종목코드=?, 종목명=?, 현재가=?, 전일비=?"
								+", 등락률=?, 거래량=?"
								+"WHERE 종목코드="+scode;
						
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, sinfo.ranking);
						pstmt.setString(2, sinfo.stockCode);
						pstmt.setString(3, sinfo.stockName);
						pstmt.setInt(4,sinfo.cPrice);
						pstmt.setString(5, sinfo.upDown);
						pstmt.setString(6, sinfo.fRate);
						pstmt.setInt(7, sinfo.tVolume);
						pstmt.executeUpdate();
					}
				}
			}
			System.out.println("시황업데이트 작업을 완료하였습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ms_main();
	}
	
	static void stock_ms_view() {
		System.out.println("");
		System.out.println("주식시황보기를 실행합니다.");

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			String sql = "SELECT * FROM STOCK_TABLE";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();	
			
			System.out.println("시총순위    종목코드          종목명                현재가      전일비      등락률         거래량    ");
			while(rs.next()) {
				System.out.printf("%4s %10s %-25s %6s %10s %7s %10s",rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)+"\n");
				System.out.println("===================================================================================");				
			}
			
			String sql2= "SELECT COUNT(*)FROM STOCK_TABLE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql2);
			if(rs.next()) {
				System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ms_main();
	}
}
