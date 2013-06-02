/**
 * 
 */
package net.shcherbakovs.sb.listener;

import javax.sql.DataSource;

import net.shcherbakovs.sb.domain.Bar;
import net.shcherbakovs.sb.processor.BarItemProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * @author "Sergey Shcherbakov"
 *
 */
public class FileHistoryReadListener {
	private static Logger log = LoggerFactory.getLogger(FileHistoryReadListener.class); 
	private static final String UPDATE_SYMBOL = "UPDATE SYMBOLS SET INGESTED = true WHERE SYMBOL = ?";

	private StepExecution stepExecution;
	private ItemReader<Bar> fileItemReader;
	
	private JdbcOperations template;
	private String prevSymbol = null;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		log.debug( this + ".beforeStep(" + stepExecution.getExecutionContext() + ")");
	}
	
	@AfterRead
	public void afterRead(Bar bar) throws Exception {
		@SuppressWarnings("unchecked")
		MultiResourceItemReader<Bar> mreader = (MultiResourceItemReader<Bar>) ((Advised)fileItemReader).getTargetSource().getTarget();
		
		Resource resource = mreader.getCurrentResource();
		String symbol = resource.getFilename().split("\\.")[0];
		stepExecution.getExecutionContext().put(BarItemProcessor.SYMBOL, symbol);
		
		if(prevSymbol != null && !prevSymbol.equals(symbol)) {
			int rowsUpdated = template.update(UPDATE_SYMBOL, new Object[] { prevSymbol });
			Assert.isTrue(rowsUpdated > 0, String.format("Symbol %s is not in the input data table", prevSymbol));
		}
		prevSymbol = symbol;
		
		log.debug(this + ".afterRead(Bar) " + stepExecution.getExecutionContext() + " symbol: " + stepExecution.getExecutionContext().getString(BarItemProcessor.SYMBOL));
	}

	public void setFileItemReader(ItemReader<Bar> fileItemReader) throws Exception {
		this.fileItemReader = fileItemReader;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
}
