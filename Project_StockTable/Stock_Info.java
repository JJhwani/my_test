package Project_StockTable;

class Stock_Info {
	int ranking;
	String stockCode;
	String stockName;
	int cPrice;
	String upDown;
	String fRate;
	int tVolume;
	
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getcPrice() {
		return cPrice;
	}
	public void setcPrice(int cPrice) {
		this.cPrice = cPrice;
	}
	public String getUpDown() {
		return upDown;
	}
	public void setUpDown(String upDown) {
		this.upDown = upDown;
	}
	public String getfRate() {
		return fRate;
	}
	public void setfRate(String fRate) {
		this.fRate = fRate;
	}
	public int gettVolume() {
		return tVolume;
	}
	public void settVolume(int tVolume) {
		this.tVolume = tVolume;
	}	
}
