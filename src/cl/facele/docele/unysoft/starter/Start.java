package cl.facele.docele.unysoft.starter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import cl.facele.docele.unysoft.bussines.Transformer;

public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		String pathFileSpf = "D:\\Descargas\\46_NPG_53091602092015.SPF";
		String pathFileContribuyente_ = "D:\\Dropbox\\ENROLAMIENTO\\CONTRIBUYENTES\\HISTORICO\\78206080-5_ AGUA SANTA\\ce_consulta_muestra_e_dwnld_78206080-5.csv";
org.apache.log4j.BasicConfigurator.configure();
		Path pathFile = Paths.get(pathFileSpf);
		Path pathFileContribuyente = Paths.get(pathFileContribuyente_);

		if (Files.notExists(pathFile.toAbsolutePath()))
			System.out.println("No existe....." + pathFile.toString());

		if (Files.notExists(pathFileContribuyente.toAbsolutePath()))
			System.out.println("No existe....." + pathFileContribuyente.toString());
		
		
		try {			
			String contenido1 = Transformer.getTXT(Files.readAllBytes(pathFile), Files.readAllBytes(pathFileContribuyente), pathFile.getFileName().toString());
		
			System.out.println("Contenido: " + contenido1);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		System.out.println("...Fin");
	}

}
