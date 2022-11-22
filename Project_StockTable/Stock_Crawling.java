package Project_StockTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import Project_Hosinsa.Product_Setting;
import Project_Hosinsa.Product_Info;

public class Stock_Crawling {

	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String user="stocktable";
	private static String pwd="1234";
	
	public static void main(String[] args) {
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		ArrayList<Stock_Info> silist = Stock_Setting.stock_Otable();
		
		try {
			Class.forName(driver);
			System.out.println("드라이브 로드 성공");
			conn = DriverManager.getConnection(url,user,pwd);

			
//			String sql = "INSERT INTO STOCK_OTABLE VALUES(SEQ_NUM.NEXTVAL,?,?,?,?,?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			
//			for(Stock_Info sinfo : silist) {
//				pstmt.setInt(1, sinfo.ranking);
//				pstmt.setString(2, sinfo.stockCode);
//				pstmt.setString(3, sinfo.stockName);
//				pstmt.setInt(4,sinfo.cPrice);
//				pstmt.setString(5, sinfo.upDown);
//				pstmt.setString(6, sinfo.fRate);
//				pstmt.setInt(7, sinfo.tVolume);
//				
//				pstmt.executeUpdate();
//			}
//			System.out.println("업로드 성공");
			
			String sql2 = "INSERT INTO STOCK_TABLE VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql2);			
			for(Stock_Info sinfo : silist) {
				pstmt.setInt(1, sinfo.ranking);
				pstmt.setString(2, sinfo.stockCode);
				pstmt.setString(3, sinfo.stockName);
				pstmt.setInt(4,sinfo.cPrice);
				pstmt.setString(5, sinfo.upDown);
				pstmt.setString(6, sinfo.fRate);
				pstmt.setInt(7, sinfo.tVolume);
				
				pstmt.executeUpdate();
			}
			System.out.println("시황업로드 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}