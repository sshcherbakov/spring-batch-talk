/**
 * 
 */
package net.shcherbakovs.sb.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Scanner;

import net.shcherbakovs.sb.domain.Symbol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.util.Assert;

/**
 * @author "Sergey Shcherbakov"
 *
 */
public class SymbolHistoryItemWriter implements ItemWriter<Symbol> {
	private static final Logger log = LoggerFactory.getLogger(SymbolHistoryItemWriter.class);

	private String urlTemplate;
	
	public void setUrlTemlpate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}

	private StepExecution stepExecution;  

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	public void write(List<? extends Symbol> items) throws Exception {
		File stepDir = new File(stepExecution.getExecutionContext().getString("partition"));
		stepDir.mkdirs();
		for(Symbol sym : items) {
			String url = String.format(urlTemplate, sym.getSymbol());
			log.info(String.format("Retrieving %s history data: %s", sym.getSymbol(), url));

			File of = new File(stepDir, sym.getSymbol() + ".csv");
			of.createNewFile();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(of);
				
				URL website = new URL(url);
			    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
		
			    validateFile(of);
				log.info(String.format("%s data stored in %s", sym.getSymbol(), of.getName()));
			}
			finally {
				if(fos != null) {
					fos.close();
				}
			}
		}
	}
	
	private void validateFile(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		try {
			Assert.isTrue(!s.hasNext("<HTML><HEAD><meta"));
		}
		finally {
			s.close();
		}
	}
}
