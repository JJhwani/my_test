package Project_StockTable;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Stock_Setting {
	static ArrayList<Stock_Info> silist = new ArrayList<Stock_Info>();
	
	public static void main(String[] args) {
		Stock_Setting st =  new Stock_Setting();
		st.stock_Otable();
	}
	
	static ArrayList<Stock_Info> stock_Otable(){
		int ranking;      //시총순위
		String stockCode; //종목코드
		String stockName; //종목명
		int cPrice;       //현재가
		String upDown;    //전일비
		String fRate;     //등락률
		int tVolume;      //거래량
		
		try {
			for(int i=1; i<=4; i++) {//20
				Document doc = Jsoup.connect("https://finance.naver.com/sise/sise_market_sum.naver?&page="+i).get();
				Elements size = doc.select(".box_type_l .no");
				Elements title = doc.select("a.tltle");
				Elements link = doc.select("div.box_type_l table.type_2 tr td:eq(1)  a");
				Elements currentPrice = doc.select("div.box_type_l table.type_2 tr td:eq(2)");
				Elements comparedDay = doc.select("div.box_type_l table.type_2 tr td:eq(3)");
				Elements fluctuationRate = doc.select("div.box_type_l table.type_2 tr td:eq(4)");
				Elements tradingVolume = doc.select("div.box_type_l table.type_2 tr td:eq(9)");
				
				try {
					for(int j=0; j<size.size(); j++) {
						Document doc2 = Jsoup.connect("https://finance.naver.com"+link.get(j).attr("href")).get();
						Elements code = doc2.select("div.wrap_company div.description span.code");
						String upDown1 = fluctuationRate.get(j).text().substring(0,1);
						String upDown2 = null;
												
						if(upDown1.equals("+")) {
							upDown2 = "상승";
						}
						else if(upDown1.equals("-")) {
							upDown2 = "하락";
						}
						else {
							upDown2 = "보합";
						}
						
						String sName = title.get(j).text();
				        String sName2 = null;
				        if(title.get(j).text().length()>20) {
				        	sName2 = sName.substring(0,20);
				        }
				        else if(title.get(j).text().length()<=20) {
				        	sName2 = String.format("%-20s",sName);
				        }
						
						System.out.println("시총순위 : "+size.get(j).text());
						System.out.println("종목코드 : "+code.text());
						System.out.println("종목명  : "+sName2);
						System.out.println("현재가  : "+currentPrice.get(j).text());
						System.out.println("전일비  : "+upDown2+" "+comparedDay.get(j).text());
						System.out.println("등락률  : "+fluctuationRate.get(j).text());
						System.out.println("거래량  : "+tradingVolume.get(j).text());
						System.out.println("=========================================");
								
						ranking = Integer.parseInt(size.get(j).text());
						stockCode = code.text();
						stockName = title.get(j).text();
						cPrice = Integer.parseInt(currentPrice.get(j).text().replace(",",""));
						upDown = upDown2+" "+comparedDay.get(j).text();
						fRate = fluctuationRate.get(j).text();
						tVolume = Integer.parseInt(tradingVolume.get(j).text().replace(",",""));
						
						Stock_Info sinfo = new Stock_Info();
						sinfo.setRanking(ranking);
						sinfo.setStockCode(stockCode);
						sinfo.setStockName(stockName);
						sinfo.setcPrice(cPrice);
						sinfo.setUpDown(upDown);
						sinfo.setfRate(fRate);
						sinfo.settVolume(tVolume);
						
						silist.add(sinfo);
					}
				} catch(Exception ex) {}
			} 
		} catch(Exception ex) {}
		return silist;
	}
}
