package br.univel;
import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReportJdbc {

	private String arq = "C:\\reports\\Blank_A4.jasper";

	public SimpleReportJdbc() {
		
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(arq, null, getConnection());
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() {
		
		// TODO
		
		return null;
	}

	public static void main(String[] args) {
		new SimpleReportJdbc();
	}
}