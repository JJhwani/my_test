package Project_Hosinsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;


public class Product_Setting {
	private static String driver="oracle.jdbc.driver.OracleDriver";
	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static String user="hosinsa";
	private static String pwd="1234";
	
	public static void main(String[] args) {
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;
		
		ArrayList<Product_Info> pilist = Product_Crawling.product_Crawling();
		
		try {
			Class.forName(driver);
			System.out.println("드라이브 로드 성공");
			conn = DriverManager.getConnection(url,user,pwd);

			
			String sql = "INSERT INTO HOSINSA VALUES(SEQ_NUM.NEXTVAL,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for(Product_Info pinfo : pilist) {
				pstmt.setString(1, pinfo.pNumber);
				pstmt.setString(2, pinfo.categorys);
				pstmt.setString(3, pinfo.brands);
				pstmt.setString(4,pinfo.product);
				pstmt.setInt(5, pinfo.prices);
				pstmt.setInt(6, pinfo.stocks);
				
				pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
