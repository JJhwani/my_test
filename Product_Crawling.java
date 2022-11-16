package com.ezen.ex02;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ezen.ex01.SeoulSights_Info;


public class Product_Crawling {
	static ArrayList<Product_Info> pilist = new ArrayList<Product_Info>();
		
	public static void main(String[] args) {
		Product_Crawling pcraw = new Product_Crawling();
		pcraw.product_Crawling();
	}
	public static ArrayList<Product_Info> product_Crawling() {
		String product;
		String pNumber;
		String brands;
		String categorys;
		int prices;
		int stocks;		
		
		try {
			int k = 1;
			for(int i=1; i<=1; i++) {//100
				Document doc = Jsoup.connect("https://www.musinsa.com/ranking/best?period=now&age=ALL&mainCategory=&subCategory=&leafCategory=&price=&golf=false&kids=false&newProduct=false&exclusive=false&discount=false&soldOut=false&page="+i).get();
				Elements ranking = doc.select("div.box li.li_box p.txt_num_rank");
				Elements img = doc.select("div.list_img img.lazy");
				Elements link = doc.select("div.list_img a");
				Elements productNo = doc.select("div.box li.li_box");
				Elements brand = doc.select("div.box li.li_box p.item_title a");
				Elements productName = doc.select("div.box li.li_box p.list_info a");
				//Elements price = doc.select(".article_info .price");
						
				try {
					for(int j=0; j<img.size(); j++) {
						Document doc2 = Jsoup.connect(link.get(j).attr("href")).get();
						Element category = doc2.select("div.product_info p.item_categories a:eq(0)").get(0);
						Element price = doc2.select("#normal_price").get(0);
					 	//int split=price.get(j).text().split(" ").length;
				        int amount = (int)(Math.random()*100+1);
						
						System.out.println("랭킹 : "+ranking.get(j).text().split("위")[0]);
						System.out.println("제품번호 : "+productNo.get(j).attr("data-goods-no"));
						System.out.println("카테고리 : "+category.text());
						System.out.println("브랜드 : "+brand.get(j).text());
						System.out.println("제품명 : "+productName.get(j).text());
						System.out.println("가격 : "+price.text());
						System.out.println("재고 : "+amount+"개");
						System.out.println("=========================================");
						//System.out.println("가격 : "+price.get(j).text().split(" ")[split-1]);
						
						product = productName.get(j).text();
						pNumber = productNo.get(j).attr("data-goods-no");
						brands = brand.get(j).text();
						categorys = category.text();
						prices = Integer.parseInt(price.text());
						stocks = amount;	
												
						Product_Info pinfo = new Product_Info();
						pinfo.setProduct(product);
						pinfo.setpNumber(pNumber);
						pinfo.setBrands(brands);
						pinfo.setCategorys(categorys);
						pinfo.setPrices(prices);
						pinfo.setStocks(stocks);
						
						pilist.add(pinfo);
						
					}
				} catch(Exception ex) {}
			} 
		} catch(Exception ex) {}
		return pilist;
	}
}
