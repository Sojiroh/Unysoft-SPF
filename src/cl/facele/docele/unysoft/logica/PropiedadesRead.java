package cl.facele.docele.unysoft.logica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import cl.facele.docele.unysoft.beans.EmisorBean;


public class PropiedadesRead{
	private static Logger logger = Logger.getLogger(Object.class);

	private static List<Integer> getActecos(BufferedReader reader) throws Exception {
		List<Integer> retorna = new ArrayList<>();
		String line;
		while (true) {
			line = reader.readLine();
			if (line.trim().isEmpty())
				break;
			try {
				int acteco = Integer.parseInt(line.split(";")[0]);
				retorna.add(acteco);
			} catch (Exception e) {
				logger.debug("No es un entero por lo que no es una actividad economica: " +  e.getMessage());
			}
		}
		return retorna;
	}
	
	private static Properties getCasaMatriz(String line) {
		Properties retorna = new Properties();	
		boolean comunaFlag = false;
		boolean ciudadFlag = false;
		
		if (line.contains("Comuna")) comunaFlag = true;
		if (line.contains("Ciudad")) ciudadFlag = true;
		
		int n = 0;
		for (String elemento : line.split(",")) {
			n++;
			if (n == 1)	{	//Direcci�n de casa matriz
				String direccion = elemento.trim();			
				// TODO se debe hacer algunos tratamientos a este contenido.
				retorna.setProperty("DIRECCION", direccion);
			} else {
				if (elemento.contains("Comuna")) {
					String comuna = elemento.replace("Comuna", "").trim();
					retorna.setProperty("COMUNA", comuna);
					
					if (!ciudadFlag)
						retorna.setProperty("CIUDAD", comuna);
				} else if (elemento.contains("Ciudad")) {
					String ciudad = elemento.replace("Ciudad", "").trim();
					retorna.setProperty("CIUDAD", ciudad);
					
					if (!comunaFlag)
						retorna.setProperty("COMUNA", ciudad);
				}
			}
		}
		return retorna;
	}

	public static EmisorBean getBean(byte[] infoContribuyente) throws Exception {
		logger.debug("...Start");
		EmisorBean bean = new EmisorBean();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(infoContribuyente), "ISO-8859-1"));
			String line ="";
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty())
					continue;
				logger.debug("Linea: " + line);
				if (line.contains(":")) {
					logger.debug("Es un key: " + line);
					if (line.contains("Rut Contribuyente")) {
						bean.setRutContribuyente(reader.readLine().trim());				
					} else if (line.contains("Nombre o Razón Social")) {
						bean.setRazonSocial(reader.readLine().trim());
					} else if (line.contains("Dirección de la Empresa")) {
						bean.setCasaMatriz(getCasaMatriz(reader.readLine()));
					} else if (line.contains("Actividades Económicas")) {
						bean.setActeco(getActecos(reader));
					} else if (line.contains("Glosa Descriptiva")) {
						bean.setGiro(reader.readLine().trim());
					} else {
						logger.info("KEY no definido en l�gica de bean: " + line);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e, e);
			throw new Exception("Error obteniendo datos de emisor: " + e.getMessage());
		} finally {
			reader.close();
		}
		return bean;
	}
}