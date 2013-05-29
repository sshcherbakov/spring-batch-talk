/**
 * 
 */
package net.shcherbakovs.util;

import java.beans.PropertyEditorSupport;

/**
 * @author "Sergey Shcherbakov"
 *
 */
public class LongPropertyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String txt = text.trim();
		if( "-".equals(txt)) {
			this.setValue(null);
		}
		else {
			this.setValue(Long.parseLong(txt));
		}
	}
	
	@Override
	public String getAsText() {
		return this.getValue().toString();
	}
}
