package Project_StockTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Stock_Interested_Item {
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
	
	static void stock_ii_main() {
		System.out.println("");
		System.out.println("                 관심종목보기                ");
		System.out.println("=========================================");
		System.out.println("=    1.관심종목보기           2.관심종목추가    =");
		System.out.println("=    3.관심종목삭제           4.이전단계이동    =");
		System.out.println("=========================================");
		
		System.out.println("원하시는 메뉴를 입력해주세요.");
		System.out.print("메뉴 번호 : ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		while(true) {
			if(number==1) {
				System.out.println("관심종목보기로 이동합니다.");
				System.out.println("");
				stock_ii_view();
				break;
			}
			else if(number==2) {
				System.out.println("관심종목추가로 이동합니다.");
				System.out.println("");
				stock_ii_add();
				break;
			}
			else if(number==3) {
				System.out.println("관심종목삭제로 이동합니다.");
				System.out.println("");
				stock_ii_delete();
			}
			else if(number==4) {
				System.out.println("이전단계로 이동합니다.");
				System.out.println("");
				return;
			}
			else {
				System.out.println("번호를 다시 입력해주세요.");
				System.out.println("");
				stock_ii_main();
				break;
			}
		}
	}
	
	static void stock_ii_view() {
		System.out.println("관심종목보기를 실행합니다.");
		System.out.println("");
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			String sql= "SELECT COUNT(*)FROM STOCK_ITABLE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {
					System.out.println("");
					System.out.println("등록된 관심종목이 없습니다.");
					System.out.println("작업을 종료합니다.");
					stock_ii_main();
				}
				else {
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url,user,pwd);
						String sql2= "SELECT *FROM STOCK_ITABLE";
						pstmt = conn.prepareStatement(sql2);
						rs = pstmt.executeQuery();
						
						System.out.println("시총순위    종목코드          종목명                현재가      전일비      등락률         거래량    ");
						while(rs.next()) {
							System.out.printf("%4s %10s %-25s %6s %10s %7s %10s",rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)+"\n");
							System.out.println("===============================================================");				
						}
						
						String sql3= "SELECT COUNT(*)FROM STOCK_ITABLE";
						pstmt = conn.prepareStatement(sql3);
						rs = pstmt.executeQuery();
						
						if(rs.next()) {
							System.out.println("- "+rs.getString("COUNT(*)")+"건의 데이터가 검색되었습니다.");
						}
						
					} catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ii_main();
	}
	
	static void stock_ii_add() {
		System.out.println("관심종목추가를 실행합니다.");
		System.out.println("");
		System.out.println("추가할 종목의 종목코드를 입력하세요.");
		System.out.print("종목코드 : ");
		String code = sc.nextLine();
		System.out.println();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			String sql= "SELECT COUNT (*) FROM STOCK_ITABLE WHERE 종목코드 ="+code;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {
					System.out.println("작업을 계속 진행합니다.");
					
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url,user,pwd);
						String sql2= "INSERT INTO STOCK_ITABLE(시총순위,종목코드,종목명,현재가,전일비,등락률,거래량) SELECT ST.시총순위,ST.종목코드,ST.종목명,ST.현재가,ST.전일비,ST.등락률,ST.거래량 FROM STOCK_TABLE ST WHERE ST.종목코드 = "+code;
						pstmt = conn.prepareStatement(sql2);
						rs = pstmt.executeQuery();
																		
						String sql3= "SELECT * FROM STOCK_ITABLE";
						pstmt = conn.prepareStatement(sql3);
						rs = pstmt.executeQuery();
						System.out.println("시총순위    종목코드          종목명                현재가      전일비      등락률         거래량    ");
						
						while(rs.next()) {
							System.out.printf("%4s %10s %-25s %6s %10s %7s %10s",rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)+"\n");
							System.out.println("===============================================================");							
						}
						System.out.println("종목코드 "+code+"을/를 관심종목에 추가하였습니다.");
						stock_ii_main();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				else if (cnt==1 || cnt>1) {
					System.out.println("이미 같은 종목이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 종료합니다.");
					stock_ii_main();
				}
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void stock_ii_delete() {
		System.out.println("관심종목삭제를 실행합니다.");
		System.out.println("");
		System.out.println("삭제할 종목의 종목코드를 입력하세요.");
		System.out.print("종목코드 : ");
		String code = sc.nextLine();
		System.out.println();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			String sql= "SELECT COUNT(*)FROM STOCK_ITABLE WHERE 종목코드 ="+code;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if (cnt==1 || cnt>1) {
					System.out.println("관심종목이 "+cnt+"개 존재합니다.");
					System.out.println("작업을 계속 진행합니다.");
					
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url,user,pwd);
						String sql2= "DELETE FROM STOCK_ITABLE WHERE 종목코드='"+code+"'";
						pstmt = conn.prepareStatement(sql2);
						rs = pstmt.executeQuery();
						
						String sql3= "SELECT * FROM STOCK_ITABLE";
						pstmt = conn.prepareStatement(sql3);
						rs = pstmt.executeQuery();						
						System.out.println("시총순위    종목코드          종목명                현재가      전일비      등락률         거래량    ");
						while(rs.next()) {
							System.out.printf("%4s %10s %-25s %6s %10s %7s %10s",rs.getInt(1),rs.getString(2),rs.getString(3),
									rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7)+"\n");
							System.out.println("===============================================================");
						}
						System.out.println("종목코드 "+code+"을/를 관심종목에서 삭제하였습니다.");
						stock_ii_main();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				else if(cnt==0) {
					System.out.println("등록된 관심종목이 없습니다.");
					System.out.println("작업을 종료합니다.");
					stock_ii_main();
				}
			}			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
