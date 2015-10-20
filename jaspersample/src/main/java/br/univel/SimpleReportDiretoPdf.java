package br.univel;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReportDiretoPdf {

	private static final String OUT_PDF = "out.pdf";
	private String arq = "C:\\reports\\simples.jasper";

	public SimpleReportDiretoPdf() {

		TableModel tableModel = getTableModel();

		JasperPrint jp = null;
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("empresa", "Petrobras");
			map.put("telefone", "123pim567pim");

			jp = JasperFillManager.fillReport(arq, map, new JRTableModelDataSource(tableModel));

			// para exportar direto para PDF não precisa de um JasperView
			// JasperViewer jasperViewer = new JasperViewer(jp);
			//
			// jasperViewer.setBounds(50, 50, 320, 240);
			// jasperViewer.setLocationRelativeTo(null);
			// jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
			//
			// jasperViewer.setVisible(true);

			JasperExportManager.exportReportToPdfFile(jp, OUT_PDF);

			String msg = "<html>Arquivo exportado para PDF!<br> O SW vai abrir oarquivo.";
			JOptionPane.showMessageDialog(null, msg);

			Desktop.getDesktop().open(new File(OUT_PDF));

		} catch (JRException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TableModel getTableModel() {
		String[] columnNames = { "Id", "Nome", "Departamento", "Email" };

		String[][] data = { { "1", "Hugo", "Financeiro", "hugo@email.br" },
				            { "2", "Jose", "Comercial",  "jose@email.br" },
				            { "3", "Luiz", "Contabil",   "luiz@email.br" } };

		return new DefaultTableModel(data, columnNames);
	}

	public static void main(String[] args) {
		new SimpleReportDiretoPdf();
	}
}