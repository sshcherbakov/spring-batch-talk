package net.shcherbakovs.sb.processor;

import net.shcherbakovs.sb.domain.Bar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

public class BarItemProcessor implements ItemProcessor<Bar, Bar> {
	private static Logger log = LoggerFactory.getLogger(BarItemProcessor.class); 

	public static final String SYMBOL = "symbol";
	
	private StepExecution stepExecution;
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		log.debug(this + ".beforeStep(" + stepExecution.getExecutionContext() + ")");
	}
	
	@Override
	public Bar process(Bar bar) throws Exception {
		log.debug(this + ".process(Bar) " + stepExecution.getExecutionContext() + " symbol: " + stepExecution.getExecutionContext().getString(BarItemProcessor.SYMBOL));

		bar.setSymbol(stepExecution.getExecutionContext().getString(SYMBOL));
		return bar;
	}
}
