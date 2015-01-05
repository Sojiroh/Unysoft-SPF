package cl.facele.docele.unysoft.bussines;

import cl.facele.docele.unysoft.beans.IEBean;
import cl.facele.docele.unysoft.beans.IEDetalleBean;
import cl.facele.docele.unysoft.logica.GeneraTXT;
import cl.facele.docele.unysoft.logica.PropiedadesRead;
import cl.facele.docele.unysoft.logica.SpfRead;
import org.apache.log4j.Logger;

public class TransformerIE {
	private static Logger logger = Logger.getLogger(Object.class);


	public static String getTXT(byte[] infoSPF, String tipoDocumento) throws Exception {
		logger.debug("...Start");
		
		String resultado="";
		
		//envia a bean los datos del contribuyente
		try {
			if(tipoDocumento.equals("COMPRA")) {
				resultado = SpfRead.getBeanIECV(infoSPF);
				
			} else if (tipoDocumento.equals("VENTA")) {
				resultado = SpfRead.getBeanVenta(infoSPF);
			}
		} catch (Exception e) {
			throw new Exception("ERROR obteniendo datos de contribuyente: " +  e.getMessage(), e);
		}
		return resultado;
	}

	private static int getTipoDocumento(String tipoDocumento) {
		logger.debug("TipoDocumento: " + tipoDocumento);
		
		return Integer.parseInt(tipoDocumento);
	}
}
