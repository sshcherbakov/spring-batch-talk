/**
 * 
 */
package net.shcherbakovs.sb.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This class represents a single history quote entry 
 * 
 * @author "Sergey Shcherbakov"
 */
public class Bar {

	private String symbol;
	
	private Date timestamp;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal open;
	private BigDecimal close;
	private Long volume;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "Bar [symbol=" + symbol + ", timestamp=" + timestamp + ", high="
				+ high + ", low=" + low + ", open=" + open + ", close=" + close
				+ ", volume=" + volume + "]";
	}
}
