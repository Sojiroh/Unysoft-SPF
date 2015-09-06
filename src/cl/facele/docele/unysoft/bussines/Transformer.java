package cl.facele.docele.unysoft.bussines;


import org.apache.log4j.Logger;
import cl.facele.docele.unysoft.beans.DocumentoBean;
import cl.facele.docele.unysoft.beans.EmisorBean;
import cl.facele.docele.unysoft.logica.GeneraTXT;
import cl.facele.docele.unysoft.logica.PropiedadesRead;
import cl.facele.docele.unysoft.logica.SpfRead;
import cl.facele.docele.unysoft.beans.DetalleBean_1;
import cl.facele.docele.unysoft.beans.DocumentoBean2;


public class Transformer {
	private static Logger logger = Logger.getLogger(Object.class);

	public static String getTXT(byte[] infoSPF, byte[] infoContribuyente, String tipoDocumento) throws Exception {
		logger.debug("...Start");
		EmisorBean emisorBean;
		DocumentoBean documentoBean;
                DocumentoBean2 documentoBean2;
		String resultado;
		
		//envia a bean los datos del contribuyente
		try {
			
                        if(tipoDocumento.contains("34")){
                            emisorBean = PropiedadesRead.getBean(infoContribuyente);
                            documentoBean = SpfRead.getBean(infoSPF);
                            documentoBean.setTipoDoc(Integer.parseInt(tipoDocumento));
                            GeneraTXT txt = new GeneraTXT();
                            resultado = txt.generaFacturaExenta(documentoBean, emisorBean);
                        }
                        else if (tipoDocumento.contains("46")){
                            emisorBean = PropiedadesRead.getBean(infoContribuyente);
                            documentoBean2 = SpfRead.getBean46(infoSPF);
                            documentoBean2.setTipoDoc(Integer.parseInt("46"));
                            GeneraTXT txt = new GeneraTXT();
                            resultado = txt.generaFacturaCompra(documentoBean2, emisorBean);
                        }
                        else{
                            emisorBean = PropiedadesRead.getBean(infoContribuyente);
                            documentoBean = SpfRead.getBean(infoSPF);
                            documentoBean.setTipoDoc(getTipoDocumento(tipoDocumento));
                            GeneraTXT txt = new GeneraTXT();
                            resultado = txt.generaFacturaTexto(documentoBean, emisorBean);
                        }
		} catch (Exception e) {
			throw new Exception("ERROR obteniendo datos de contribuyente: " +  e.getMessage(), e);
		}
		return resultado;
	}

	private static int getTipoDocumento(String tipoDocumento) {
		logger.debug("TipoDocumento: " + tipoDocumento);
                int pos=tipoDocumento.indexOf("_");
		tipoDocumento=tipoDocumento.substring(0,pos);
		return Integer.parseInt(tipoDocumento);
	}

}
