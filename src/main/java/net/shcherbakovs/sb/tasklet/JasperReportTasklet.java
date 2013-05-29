/**
 * 
 */
package net.shcherbakovs.sb.tasklet;

import java.util.HashMap;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

/**
 * @author "Sergey Shcherbakov"
 *
 */
public class JasperReportTasklet implements Tasklet {

	private Resource reportFile;
	private String outputPdfFile;
	private DataSource dataSource;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		JasperReport jasperReport = JasperCompileManager.compileReport(reportFile.getInputStream());
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource.getConnection());
	    JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdfFile);
	    return RepeatStatus.FINISHED;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Resource getReportFile() {
		return reportFile;
	}

	public void setReportFile(Resource reportFile) {
		this.reportFile = reportFile;
	}

	public String getOutputPdfFile() {
		return outputPdfFile;
	}

	public void setOutputPdfFile(String outputPdfFile) {
		this.outputPdfFile = outputPdfFile;
	}

}
