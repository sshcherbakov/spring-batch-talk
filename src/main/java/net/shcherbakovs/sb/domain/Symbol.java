/**
 * 
 */
package net.shcherbakovs.sb.domain;


/**
 * This class represents a single history quote entry 
 * 
 * @author "Sergey Shcherbakov"
 */
public class Symbol {

	private String symbol;
	private String companyName;
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}
	
	public void setName(String name) {
		this.companyName = name;
	}

	@Override
	public String toString() {
		return "Symbol [symbol=" + symbol + "]";
	}

}
