/**
 * 
 */
package net.shcherbakovs.util;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

/**
 * @author "Sergey Shcherbakov"
 *
 */
public class BigDecimalPropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String txt = text.trim();
		if( "-".equals(txt)) {
			this.setValue(null);
		}
		else {
			String strBd = txt.replace(",", "");
			BigDecimal money = new BigDecimal(strBd);
			this.setValue(money);
		}
	}
	
	@Override
	public String getAsText() {
		return this.getValue().toString();
	}
}
