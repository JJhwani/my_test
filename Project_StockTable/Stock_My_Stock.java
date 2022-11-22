package Project_StockTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Stock_My_Stock {
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
		System.out.println("                 내ㅇ주식관리                ");
		System.out.println("=========================================");
		System.out.println("=    1.내ㅇ주식보기           2.기존주식구매    =");
		System.out.println("=    3.기존주식판매           4.신규주식구매    =");
		System.out.println("=    5.이전단계이동           6.프로그램종료    =");
		System.out.println("=========================================");
		
		System.out.println("원하시는 메뉴를 입력해주세요.");
		System.out.print("메뉴 번호 : ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		Stock_Main sm = new Stock_Main();
		
		while(true) {
			if(number==1) {
				System.out.println("내ㅇ주식보기로 이동합니다.");
				stock_ms_view();
				break;
			}
			else if(number==2) {
				System.out.println("기존주식구매로 이동합니다.");
				stock_ms_buy();
				break;
			}
			else if(number==3) {
				System.out.println("기존주식판매로 이동합니다.");
				stock_ms_sell();
				break;
			}
			else if(number==4) {
				System.out.println("신규주식구매로 이동합니다.");
				stock_ms_newBuy();
				break;
			}
			else if(number==5) {
				System.out.println("이전단계로 이동합니다.");
				System.out.println("");
				return;
			}
			else if(number==6) {
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
	
	static void stock_ms_view() {
		System.out.println("내 주식보기를 실행합니다.");
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			//내가 보유한 주식이 있는지 확인
			String sql= "SELECT COUNT(*)FROM STOCK_MTABLE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {//내가 보유한 주식이 없으면 작업을 종료
					System.out.println("");
					System.out.println("보유한 주식이 없습니다.");
					System.out.println("작업을 종료합니다.");
					stock_ms_main();
				}
				else {//내가 보유한 주식이 있으면 보유한 주식정보 출력
					try {
						//STOCK_TALBE에서 현재가,전일비,등락률 STOCK_MTABLE로 업데이트
						String sql2 = "UPDATE STOCK_MTABLE SM SET 현재가=(SELECT 현재가 FROM STOCK_TABLE ST WHERE SM.종목코드=ST.종목코드),"
							+"전일비=(SELECT 전일비 FROM STOCK_TABLE ST WHERE SM.종목코드=ST.종목코드),"
							+"등락률=(SELECT 등락률 FROM STOCK_TABLE ST WHERE SM.종목코드=ST.종목코드)";
						pstmt = conn.prepareStatement(sql2);
						pstmt.executeUpdate();
						
						//내가 보유한 주식의 현황을 업데이트하기 위해 변수들을 출력
						String sql3 = "SELECT 종목코드,매입가,보유수량,현재가,매입금액,평가금액 FROM STOCK_MTABLE";
						pstmt = conn.prepareStatement(sql3);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							rs.getString(1);
							rs.getInt(2);
							rs.getInt(3);
							rs.getInt(4);
							rs.getInt(5);
							rs.getInt(6);
							
							int cSum = rs.getInt(4)*rs.getInt(3);
							String upDown=null;
							if(rs.getInt(6)>rs.getInt(5)) {
								upDown = "상승 ";
							}
							else if(rs.getInt(6)<rs.getInt(5)) {
								upDown = "하락 ";
							}
							float rate = ((float)rs.getInt(4)/rs.getInt(2)*100)-100;
							String percent = String.format("%.2f", rate)+"%";
							
							//내가 보유한 주식에서 평가손익,수익률,평가금액 업데이트
							String sql4= "UPDATE STOCK_MTABLE SET 평가손익=?,수익률=?,평가금액=? WHERE 종목코드='"+rs.getString(1)+"'";
							pstmt = conn.prepareStatement(sql4);
							pstmt.setString(1, upDown+(rs.getInt(6)-rs.getInt(5)));
							pstmt.setString(2, percent);
							pstmt.setInt(3, rs.getInt(4)*rs.getInt(3));
							pstmt.executeUpdate();
						}
						//업데이트 된 내가 보유한 주식 출력
						String sql5 = "SELECT * FROM STOCK_MTABLE";
						pstmt = conn.prepareStatement(sql5);
						rs = pstmt.executeQuery();
						
						System.out.println("종목코드        종목명                 매입가    평가손익    수익률    보유수량     현재가      전일비        등락률     매입금액     평가금액");
						while(rs.next()) {
							System.out.printf("%-1s %-25s %-5s %-5s %5s %7s %10s %10s %10s %-8s %10s\n",rs.getString(1),rs.getString(2),rs.getInt(3),
									rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
							System.out.println("========================================================================================================================");
						}
						String sql6 = "SELECT COUNT(*) FROM STOCK_MTABLE";
						pstmt = conn.prepareStatement(sql6);
						rs = pstmt.executeQuery();
						
						//검색된 데이터 건수 표시
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
		stock_ms_main();//작업이 끝나면 stock_ms_main 매서드로 이동
	}
	
	static void stock_ms_buy() {
		System.out.println("기존주식구매를 실행합니다.");
		System.out.println("");
		System.out.println("구매할 주식의 종목코드를 입력하세요.");
		System.out.print("종목코드 : ");
		String stockCode = sc.nextLine();
		System.out.println();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			//구입하려는 주식이 이미 보유한 주식인지 확인
			String sql= "SELECT COUNT(*) FROM STOCK_MTABLE WHERE 종목코드="+stockCode;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {//기존에 보유한 주식이 아니면 stock_ms_newBuy 매서드로 이동
					System.out.println("");
					System.out.println("없는 종목입니다.");
					System.out.println("종목코드를 확인해주세요.");
					stock_ms_newBuy();
				}
				else {
					//기존에 보유한 주식이면 작업실행
					System.out.println("구매가와 수량을 입력하세요.");
					System.out.print("구매가 : ");
					int buyPirce = sc.nextInt();
					System.out.println();
					System.out.print("수 량 : ");
					int buyAmount = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url,user,pwd);
						String sql2= "SELECT 매입가,보유수량,현재가,매입금액,평가금액 FROM STOCK_MTABLE WHERE 종목코드='"+stockCode+"'";
						pstmt = conn.prepareStatement(sql2);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							rs.getInt(1);
							rs.getInt(2);
							rs.getInt(3);
							rs.getInt(4);
							rs.getInt(5);
							
							int newPrice = ((rs.getInt(1)*rs.getInt(2))+(buyPirce*buyAmount))/(rs.getInt(2)+buyAmount);
							int newAmount = rs.getInt(2)+buyAmount;
							int newPurchasePrice = newPrice*newAmount;
							int appraisalPrice = rs.getInt(3)*newAmount;
							String upDown=null;							
							if(appraisalPrice>newPurchasePrice) {
								upDown = "상승 ";
							}
							else if(appraisalPrice<newPurchasePrice) {
								upDown = "하락 ";
							}
							float rate = ((float)rs.getInt(3)/newPrice*100)-100;
							String percent = String.format("%.2f", rate)+"%";
							
							String sql3= "UPDATE STOCK_MTABLE SET 매입가=?,평가손익=?,수익률=?,보유수량=?,매입금액=?,평가금액=? WHERE 종목코드='"+stockCode+"'";
							pstmt = conn.prepareStatement(sql3);
							pstmt.setInt(1, newPrice);
							pstmt.setString(2, upDown+(appraisalPrice-newPurchasePrice));
							pstmt.setString(3, percent);
							pstmt.setInt(4, newAmount);
							pstmt.setInt(5, newPurchasePrice);
							pstmt.setInt(6, rs.getInt(3)*newAmount);
							pstmt.executeUpdate();
						}
						//입력된 데이터를 출력
						String sql4= "SELECT * FROM STOCK_MTABLE";
						pstmt = conn.prepareStatement(sql4);
						rs = pstmt.executeQuery();
						System.out.println("종목코드        종목명                 매입가    평가손익    수익률    보유수량     현재가      전일비        등락률     매입금액     평가금액");
						while(rs.next()) {
							System.out.printf("%-1s %-25s %-5s %-5s %5s %7s %10s %10s %10s %-8s %10s\n",rs.getString(1),rs.getString(2),rs.getInt(3),
									rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
							System.out.println("========================================================================================================================");
						}						
					} catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ms_main();//작업이 끝나면 stock_ms_main 매서드로 이동
	}
	
	static void stock_ms_sell() {
		System.out.println("기존주식판매를 실행합니다.");
		System.out.println("");
		System.out.println("판매할 주식의 종목코드를 입력하세요.");
		System.out.print("종목코드 : ");
		String stockCode = sc.nextLine();
		System.out.println();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);			
			//판매하려는 주식이 기존에 보유한 주식인지 확인
			String sql= "SELECT COUNT(*) FROM STOCK_MTABLE WHERE 종목코드="+stockCode;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {//보유하고 있는 주식이 아니면 stock_ms_sell 매서드 다시 실행
					System.out.println("");
					System.out.println("보유하지 않은 종목입니다.");
					System.out.println("종목코드를 확인해주세요.");
					stock_ms_sell();
				}
				else {//보유한 주식이면 작업실행
					System.out.println("판매가와 수량을 입력하세요.");
					System.out.print("판매가 : ");
					int sellPirce = sc.nextInt();
					System.out.println();
					System.out.print("수 량 : ");
					int sellAmount = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url,user,pwd);
						String sql2= "SELECT 매입가,평가손익,보유수량,현재가,매입금액,평가금액 FROM STOCK_MTABLE WHERE 종목코드='"+stockCode+"'";
						pstmt = conn.prepareStatement(sql2);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							rs.getInt(1);
							rs.getString(2); 
							rs.getInt(3);
							rs.getInt(4);
							rs.getInt(5);
							rs.getInt(6);
							
							if(rs.getInt(3)<sellAmount) {//보유한 수량보다 판매할 수량이 많으면 stock_ms_sell 매서드로 이동
								System.out.println("보유한 수량보다 판매할 수량보다 적습니다.");
								System.out.println("정확한 수량을 입력해주세요.");
								stock_ms_sell();
							}
							else if(rs.getInt(3)==sellAmount) {//보유한 수량과 판매할 수량이 같으면 작업실행
								System.out.println("판매금액 : "+(sellPirce*sellAmount)+"원");
								String sql3= "DELETE FROM STOCK_MTABLE WHERE 종목코드='"+stockCode+"'";
								pstmt = conn.prepareStatement(sql3);
								pstmt.executeUpdate();
							}
							else {//보유한 수량이 판매할 수량보다 작으면 작업실행 후 업데이트
								String sql4= "UPDATE STOCK_MTABLE SET 매입가=?,평가손익=?,수익률=?,보유수량=?,매입금액=?,평가금액=? WHERE 종목코드='"+stockCode+"'";
								
								String upDown=null;
								int totalPrice = sellPirce*sellAmount;
								int newAmount = rs.getInt(3)-sellAmount;
								float rate = ((float)rs.getInt(4)/rs.getInt(1)*100)-100;
								String percent = String.format("%.2f", rate)+"%";
								
								if((rs.getInt(4)*newAmount)>(rs.getInt(1)*newAmount)) {
									upDown = "상승 ";
								}
								else if((rs.getInt(4)*newAmount)<(rs.getInt(1)*newAmount)) {
									upDown = "하락 ";
								}							
								
								pstmt = conn.prepareStatement(sql4);
								pstmt.setInt(1, rs.getInt(1));
								pstmt.setString(2, upDown+((rs.getInt(4)*newAmount)-(rs.getInt(1)*newAmount)));
								pstmt.setString(3, percent);
								pstmt.setInt(4, rs.getInt(3)-sellAmount);
								pstmt.setInt(5, rs.getInt(1)*newAmount);
								pstmt.setInt(6, rs.getInt(4)*newAmount);
								pstmt.executeUpdate();
								
								System.out.println(stockCode+"종목을 "+sellAmount+"개 "+totalPrice+"원 판매하였습니다.");
							}							
						}					
						//입력된 데이터를 출력
						String sql5= "SELECT * FROM STOCK_MTABLE";
						pstmt = conn.prepareStatement(sql5);
						rs = pstmt.executeQuery();
						System.out.println("종목코드        종목명                 매입가    평가손익    수익률    보유수량     현재가      전일비        등락률     매입금액     평가금액");
						while(rs.next()) {
							System.out.printf("%-1s %-25s %-5s %-5s %5s %7s %10s %10s %10s %-8s %10s\n",rs.getString(1),rs.getString(2),rs.getInt(3),
									rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
							System.out.println("========================================================================================================================");
						}					
					} catch(Exception e) {
						e.printStackTrace();
					}					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ms_main();//작업이 끝나면 stock_ms_main 매서드로 이동
	}
	
	static void stock_ms_newBuy() {
		System.out.println("신규주식구매를 실행합니다.");
		System.out.println("");
		System.out.println("구매할 주식의 종목코드를 입력하세요.");
		System.out.print("종목코드 : ");
		String stockCode = sc.nextLine();
		System.out.println();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);			
			//구매하려는 주식이 데이터 베이스에 있는 종목인지와 기존에 보유한 주식인지 확인
			String sql= "SELECT COUNT(*) FROM STOCK_TABLE WHERE 종목코드="+stockCode;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				int cnt = Integer.parseInt(rs.getString("COUNT(*)"));
				if(cnt==0) {//구매하려는 주식이 데이터 베이스에 없으면 stock_ms_newBuy 매서드 다시 실행
					System.out.println("");
					System.out.println("없는 종목입니다.");
					System.out.println("종목코드를 확인해주세요.");
					stock_ms_newBuy();
				}
				else if(cnt==1) {//데이터 베이스에 존재하는 주식이면 내가 이미 보유한 주식인지 확인
					String sql2= "SELECT COUNT(*) FROM STOCK_MTABLE WHERE 종목코드="+stockCode;
					pstmt = conn.prepareStatement(sql2);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						int cnt2 = Integer.parseInt(rs.getString("COUNT(*)"));
						if(cnt2==1) {//내가 이미 보유한 주식이면 stock_ms_buy 매서드로 이동
							System.out.println("");
							System.out.println("이미 보유한 종목입니다.");
							System.out.println("기존주식구매로 이동합니다.");
							stock_ms_buy();							
						}
						else if(cnt2==0) {//데이터 베이스에 존재하고 기존에 보유한 주식이 아니면 작업실행
							System.out.println("구매가와 수량을 입력하세요.");
							System.out.print("구매가 : ");
							int buyPirce = sc.nextInt();
							System.out.println();
							System.out.print("수 량 : ");
							int buyAmount = sc.nextInt();
							sc.nextLine();
							System.out.println();
							
							try {
								Class.forName(driver);
								conn = DriverManager.getConnection(url,user,pwd);
								//STOCK_MTABLE에 자료를 입력하기 위해 STOCK_TABLE에서 종목명,현재가,전일비,등락률 데이터를 가져옴
								String sql3= "SELECT 종목명,현재가,전일비,등락률 FROM STOCK_TABLE WHERE 종목코드='"+stockCode+"'";
								pstmt = conn.prepareStatement(sql3);
								rs = pstmt.executeQuery();
								while(rs.next()) {
									rs.getString(1); 
									rs.getInt(2);
									rs.getString(3);
									rs.getString(4);
									
									String upDown=null;
									if(rs.getInt(2)>buyPirce) {
										upDown = "상승 ";
									}
									else if(rs.getInt(2)<buyPirce) {
										upDown = "하락 ";
									}
									float rate = ((float)rs.getInt(2)/buyPirce*100)-100;
									String percent = String.format("%.2f", rate)+"%";
									
									//가져온 데이터를 STOCK_MTABLE에 입력
									String sql4= "INSERT INTO STOCK_MTABLE VALUES(?,?,?,?,?,?,?,?,?,?,?)";
									pstmt = conn.prepareStatement(sql4);
									pstmt.setString(1, stockCode);
									pstmt.setString(2, rs.getString(1));
									pstmt.setInt(3, buyPirce);
									pstmt.setString(4, upDown+((rs.getInt(2)*buyAmount)-(buyPirce*buyAmount)));
									pstmt.setString(5, percent);
									pstmt.setInt(6, buyAmount);
									pstmt.setInt(7, rs.getInt(2));
									pstmt.setString(8, rs.getString(3));
									pstmt.setString(9, rs.getString(4));
									pstmt.setInt(10, buyPirce*buyAmount);
									pstmt.setInt(11, rs.getInt(2)*buyAmount);
									pstmt.executeUpdate();
								}						
								//입력된 데이터를 출력
								String sql5= "SELECT * FROM STOCK_MTABLE";
								pstmt = conn.prepareStatement(sql5);
								rs = pstmt.executeQuery();
								System.out.println("종목코드        종목명                 매입가    평가손익    수익률    보유수량     현재가      전일비        등락률     매입금액     평가금액");
								while(rs.next()) {
									System.out.printf("%-1s %-25s %-5s %-5s %5s %7s %10s %10s %10s %-8s %10s\n",rs.getString(1),rs.getString(2),rs.getInt(3),
											rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
									System.out.println("========================================================================================================================");
								}					
							} catch(Exception e) {
								e.printStackTrace();
							}					
						}
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		stock_ms_main();//작업이 끝나면 stock_ms_main 매서드로 이동
	}
}
