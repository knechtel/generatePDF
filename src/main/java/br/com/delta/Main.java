package br.com.delta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;

public class Main {

	public static void main(String args[]) {

		System.out.print("mais um teste ");
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loja", "root", "123");

			HashMap parametros = new HashMap();

			if (args.length == 0) {
				parametros.put("ID", new Integer(1));
			} else {
				parametros.put("ID", new Integer(args[0]));
			}
			parametros.put("image", System.getProperty("user.dir") + "/image/Delta-Arrow-logo.png");

			JasperReport jasperReport = JasperCompileManager
					.compileReport(System.getProperty("user.dir") + "/report/Blank_A4.jrxml");

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
			System.out.println("Done! filling Jasper Report ");
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					System.getProperty("user.dir") + "/pdf/dynamic1.pdf");
			System.out.println("Done!!!exporting the pdf report");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
