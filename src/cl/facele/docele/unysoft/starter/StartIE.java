package cl.facele.docele.unysoft.starter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.facele.docele.unysoft.bussines.TransformerIE;

public class StartIE {
	
	Logger logger = Logger.getLogger(Object.class);

	/**
	 * @param args
	 * @throws Exception 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		String pathFileSpf = "D:\\Descargas\\LC_NPG_49080919082015.SPF";

		Path pathFile = Paths.get(pathFileSpf);

		if (Files.notExists(pathFile.toAbsolutePath()))
			System.out.println("No existe....." + pathFile.toString());

		
		
		try {			
			String contenido1 = TransformerIE.getTXT(Files.readAllBytes(pathFile), "COMPRA");
		
			System.out.println("Contenido: " + contenido1);
			DateFormat dateFormat = new SimpleDateFormat("HH_mm_ss_yyyy_MM_dd");
			   //get current date time with Date()
			Date date = new Date();
			System.out.println("Nombre file: " + pathFile.getParent().toString() +"\\IECV_" + dateFormat.format(date) + ".txt");
			   
			BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile.getParent().toString() +"\\IECV_" + dateFormat.format(date) + ".txt"));
			bw.write(contenido1 );
			bw.close();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		System.out.println("...Fin");

}
}
