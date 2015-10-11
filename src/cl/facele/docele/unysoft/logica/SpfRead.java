package cl.facele.docele.unysoft.logica;

import java.io.*; 
import java.math.BigDecimal;

import org.apache.log4j.Logger;

import cl.facele.docele.unysoft.logica.ToTXTIECV;
import cl.facele.docele.unysoft.beans.IEBean;
import cl.facele.docele.unysoft.beans.IEDetalleBean;
import cl.facele.docele.unysoft.beans.DetalleBean;
import cl.facele.docele.unysoft.beans.DetalleBean_1;
import cl.facele.docele.unysoft.beans.DocumentoBean2;
import cl.facele.docele.unysoft.beans.DocumentoBean;
import cl.facele.docele.unysoft.beans.ReferenciasBean;
 
public class SpfRead {
	private static Logger logger = Logger.getLogger(Object.class);
	private static DocumentoBean bean;
        private static DocumentoBean2 bean2;
	private static ReferenciasBean rBean;
	private static DetalleBean detalle;
        private static DetalleBean_1 detalle2;
	private static IEBean iecv;
	private static IEDetalleBean deta;
	private static ToTXTIECV gen;
	
	
	private static String arreglaString(String dato){
		if (dato.contains(","))
			dato = dato.replace(',', '.');
		else if (dato.equals(" "))
			dato = "0";
		else if (dato.contains("."))
			dato = dato.replace('.', '0');
		return dato;
	}
	
	private static String arreglaString2(String dato){
		if (dato.contains(","))
			dato = dato.replace(',', '.');
		else if (dato.equals(" "))
			dato = "2";
		else if (dato.contains("."))
			dato = dato.replace('.', '0');
		return dato;
	}

	public static DocumentoBean getBean(byte[] infoSPF) throws Exception {
		logger.debug("Start... ");
		BufferedReader br = null;

	     try{
	    	br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(infoSPF), "ISO-8859-1"));
	        String linea="";
	        linea = br.readLine();
	        linea = br.readLine();
	        linea = br.readLine();
	        bean = new DocumentoBean();
	        rBean = new ReferenciasBean();
	        
	        logger.debug("Linea ecabezado: " + linea);
	        logger.debug("Esta es la linea 3 " + linea.substring(188,205));
	        bean.setFolio(Integer.parseInt(linea.substring(1,11).trim()));
	        logger.debug("Flag");
	        bean.setFechaEmision(linea.substring(11, 21).trim());
	        bean.setFormaPago(arreglaString2(linea.substring(25,26)));
	        bean.setFechaVencimiento(linea.substring(26,35).trim());
	        logger.debug("Fecha Vencimiento:" + bean.getFechaVencimiento());
	        bean.setReceptorRut(linea.substring(125,137).trim());
	        logger.debug("Rut Receptor:" + bean.getReceptorRut());
	        bean.setReceptorRazonSocial(linea.substring(166,265).trim());
	        logger.debug("Razon Social Receptor:" + bean.getReceptorRazonSocial());
	        bean.setReceptorGiro(linea.substring(265,304).trim());
	        logger.debug("Giro Receptor:" + bean.getReceptorGiro());
	        bean.setReceptorDireccion(linea.substring(386,447).trim());
	        logger.debug("Direccion Receptor:" + bean.getReceptorDireccion());
	        linea = br.readLine();
	        bean.setReceptorComuna(linea.substring(0,20).trim());
	        logger.debug("Comuna Receptor:" + bean.getReceptorComuna());
	        bean.setReceptorCiudad(linea.substring(20,40).trim());
//	        logger.debug("Esta es la linea 4 " + linea.substring(340,358));
	        bean.setMontoNeto(new BigDecimal(linea.substring(158,176).trim()));
	        logger.debug("Monto Neto:" + bean.getMontoNeto());
                logger.debug("errororroror" +linea.substring(176,194).trim());
	        bean.setMontoExento(new BigDecimal(linea.substring(176,194).trim()));
	        logger.debug("Monto Exento:" + bean.getMontoExento());
	        bean.setMontoIva(new BigDecimal(arreglaString(linea.substring(199,217).trim())));
	        logger.debug("Monto Iva:" + bean.getMontoIva());
	        bean.setMontoTotal(new BigDecimal(arreglaString(linea.substring(340,358).trim())));
	        logger.debug("Monto Total:" + bean.getMontoTotal());
	        logger.debug("-----------------------------Ahora se procedera a leer el detalle de la factura-----------------------------");
	        for(int i=4;i<=32;i++){
	        	detalle = new DetalleBean();
	        	linea = br.readLine();
//	        	logger.debug("detalle: " + linea);
	        	if((linea.length()!=0)&&(!linea.equals(" "))){
//	        	logger.debug("REVISAR AQUI: " + linea.substring(523,541));
	            detalle.setLinea(Integer.parseInt(linea.substring(0,34).trim()));
	            logger.debug("Linea detalle:" + detalle.getLinea());
	            detalle.setNombreItem(linea.substring(36,116).trim());
	            logger.debug("Nombre Item:" + detalle.getNombreItem());
	            detalle.setCantidad(Integer.parseInt(linea.substring(416,434).trim()));
	            logger.debug("Cantidad:" + detalle.getCantidad());
	            detalle.setPrecioUnitario(Integer.parseInt(linea.substring(438,456).trim()));
	            logger.debug("Precio Unitario:" + detalle.getPrecioUnitario());
	            detalle.setTotalLinea(Integer.parseInt(linea.substring(523,541).trim())); 
	            logger.debug("Total:" + detalle.getTotalLinea());
	            bean.setDetalles(detalle);
	            
	        	}
	        }
	        linea = br.readLine();
	        if(linea.length()!=0){
	        logger.debug("Esta es la linea 6 " + linea.substring(32,33));
	        rBean.setTipoDocRef(Integer.parseInt(linea.substring(0,3).trim()));
	        rBean.setFolioRef(Integer.parseInt(linea.substring(4,22).trim()));
	        rBean.setFechaRef(linea.substring(22,32).trim());
	        rBean.setCodRef(Integer.parseInt(linea.substring(32,33).trim()));
	        rBean.setVacio(0);
//			if (!getContenido(filas.getCell(columnaExento)).equals("0"))
//				detalle.setExento("1");
	        bean.setReferencias(rBean);
	        
	        } else {
	        	rBean.setVacio(1);
	        	bean.setReferencias(rBean);
	        }
	     }
	     catch(Exception e){
	    	 logger.error(e,e);
	    	 throw new Exception("Error obteniendo informacion de archivo SPF: " + e.getMessage());
	     } finally {
	    	 br.close();
	     }
	     return bean;
		
	}
        
            public static DocumentoBean2 getBean46(byte[] infoSPF) throws Exception {
            logger.debug("Start... ");
            BufferedReader br = null;

         try{
            br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(infoSPF), "ISO-8859-1"));
            String linea="";
            linea = br.readLine();
            linea = br.readLine();
            linea = br.readLine();
            bean2 = new DocumentoBean2();
            rBean = new ReferenciasBean();

            logger.debug("Linea ecabezado: " + linea);
            logger.debug("Esta es la linea 3 " + linea.substring(188,205));
            bean2.setFolio(Integer.parseInt(linea.substring(1,11).trim()));
            logger.debug("Flag");
            bean2.setFechaEmision(linea.substring(11, 21).trim());
            bean2.setFormaPago(arreglaString2(linea.substring(25,26)));
            bean2.setFechaVencimiento(linea.substring(26,35).trim());
            logger.debug("Fecha Vencimiento:" + bean2.getFechaVencimiento());
            bean2.setReceptorRut(linea.substring(125,137).trim());
            logger.debug("Rut Receptor:" + bean2.getReceptorRut());
            bean2.setReceptorRazonSocial(linea.substring(166,265).trim());
            logger.debug("Razon Social Receptor:" + bean2.getReceptorRazonSocial());
            bean2.setReceptorGiro(linea.substring(265,304).trim());
            logger.debug("Giro Receptor:" + bean2.getReceptorGiro());
            bean2.setReceptorDireccion(linea.substring(386,447).trim());
            logger.debug("Direccion Receptor:" + bean2.getReceptorDireccion());
            linea = br.readLine();
            bean2.setReceptorComuna(linea.substring(0,20).trim());
            logger.debug("Comuna Receptor:" + bean2.getReceptorComuna());
            bean2.setReceptorCiudad(linea.substring(20,40).trim());
//	        logger.debug("Esta es la linea 4 " + linea.substring(340,358));
            bean2.setMontoNeto(new BigDecimal(linea.substring(158,176).trim()));
            logger.debug("Monto Neto:" + bean2.getMontoNeto());
            bean2.setMontoExento(new BigDecimal(linea.substring(176,194).trim()));
            logger.debug("Monto Exento:" + bean2.getMontoExento());
            bean2.setMontoIva(new BigDecimal(arreglaString(linea.substring(199,217).trim())));
            logger.debug("Monto Iva:" + bean2.getMontoIva());
            bean2.setMontoTotal(new BigDecimal(arreglaString(linea.substring(340,358).trim())));
            logger.debug("Monto Total:" + bean2.getMontoTotal());
            logger.debug("-----------------------------Ahora se procedera a leer el detalle de la factura-----------------------------");
            for(int i=4;i<=32;i++){
                    detalle2 = new DetalleBean_1();
                    linea = br.readLine();
//	        	logger.debug("detalle: " + linea);
                    if((linea.length()!=0)&&(!linea.equals(" "))){
//	        	logger.debug("REVISAR AQUI: " + linea.substring(523,541));
                detalle2.setLinea(Integer.parseInt(linea.substring(0,34).trim()));
                logger.debug("Linea detalle:" + detalle2.getLinea());
                detalle2.setNombreItem(linea.substring(36,116).trim());
                logger.debug("Nombre Item:" + detalle2.getNombreItem());
                detalle2.setCantidad(linea.substring(416,434).trim());
                logger.debug("Cantidad:" + detalle2.getCantidad());
                detalle2.setPrecioUnitario(Integer.parseInt(linea.substring(438,456).trim()));
                logger.debug("Precio Unitario:" + detalle2.getPrecioUnitario());
                detalle2.setTotalLinea(Integer.parseInt(linea.substring(523,541).trim())); 
                logger.debug("Total:" + detalle2.getTotalLinea());
                bean2.setDetalles(detalle2);

                    }
            }
            linea = br.readLine();
            if(linea.length()!=0){
            logger.debug("Esta es la linea 6 " + linea.substring(32,33));
            rBean.setTipoDocRef(Integer.parseInt(linea.substring(0,3).trim()));
            rBean.setFolioRef(Integer.parseInt(linea.substring(4,22).trim()));
            rBean.setFechaRef(linea.substring(22,32).trim());
            rBean.setCodRef(Integer.parseInt(linea.substring(32,33).trim()));
            rBean.setVacio(0);
//			if (!getContenido(filas.getCell(columnaExento)).equals("0"))
//				detalle.setExento("1");
            bean2.setReferencias(rBean);

            } else {
                    rBean.setVacio(1);
                    bean2.setReferencias(rBean);
            }
         }
         catch(Exception e){
             logger.error(e,e);
             throw new Exception("Error obteniendo informacion de archivo SPF: " + e.getMessage());
         } finally {
             br.close();
         }
         return bean2;

    }
	
	public static String getBeanIECV(byte[] infoSPF) throws Exception {
		logger.debug("Start... ");
		String texto = "";
		BufferedReader br = null;
	   

	     try{
	    	 br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(infoSPF), "ISO-8859-1"));
	        String linea="";
	        linea = br.readLine();
	        iecv = new IEBean();
	        gen = new ToTXTIECV();
	        
	        
	        logger.debug("Esta es la linea 1 " + linea.substring(27,33));
	        
	        gen.setCaratula("rutContribuyente",linea.substring(0,10));
	        gen.setCaratula("periodoTributario",linea.substring(20,27));
	        String periodo = linea.substring(20,27);
	        gen.setCaratula("tipoOperacion",linea.substring(27,33));
	        gen.setCaratula("tipoLibro", linea.substring(33,41));
	        gen.setCaratula("tipoEnvio", linea.substring(41,48));
	        linea = br.readLine();
	        
	        while((linea = br.readLine())!=null){
	        	logger.debug("Esta es la linea 3 " + linea.substring(239,257));
	        	gen.setDetalle("periodoTributario", periodo);
	        	String tipodocumento = arreglaString(linea.substring(0,3));
	        	int fechadoc = Integer.parseInt(linea.substring(19,29).substring(3,5));
	    		int periodo2 = Integer.parseInt(periodo.substring(0,2));
	    		int periodo3 = Integer.parseInt(periodo.substring(3,7));
	    		int agnodoc = Integer.parseInt(linea.substring(19,29).substring(6,10));
	    		
	    		if (tipodocumento.equals("030")){
	    			if ((((periodo2-fechadoc)>=3))||(((periodo2-fechadoc)>=3)&&(periodo3-agnodoc>0)))
	    				tipodocumento = "032";
	    			
	    			
	    		}
	    		
//	    		if (tipodocumento.equals("033")){
//	    			if ((((periodo2-fechadoc)>=3))||(((periodo2-fechadoc)>=3)&&(periodo3-agnodoc>0)))
//	    				tipodocumento = "034";
//	    			
//	    			
//	    		} 
//	        	
	        	gen.setDetalle("tipoDoc", tipodocumento);
	        	gen.setDetalle("folio", linea.substring(3,13));
	        	gen.setDetalle("folioAnulado", arreglaString(linea.substring(13,14)));
	        	//deta.setTasaImpuesto(Float.parseFloat(arreglaString(linea.substring(14,19))));
	        	gen.setDetalle("fechaDoc", linea.substring(19,29));
	        	gen.setDetalle("rutCliente", linea.substring(29,39));
	        	gen.setDetalle("razonSocial", linea.substring(39,89).trim());
	        	gen.setDetalle("montoExento", linea.substring(89,107));
	        	gen.setDetalle("montoNeto", linea.substring(107,125));
	        	gen.setDetalle("montoIva", linea.substring(125,143));
	        	gen.setDetalle("montoActivo", linea.substring(143,161));
	        	
	        	if(!linea.substring(161,179).equals("000000000000000000"))
	        		gen.setDetalle("montoIvaActivo", linea.substring(161,179));
	        	gen.setDetalle("codigoIvaNoRecuperable", arreglaString(linea.substring(179,180)));
	        	if(!linea.substring(180,198).equals("000000000000000000"))
	        		gen.setDetalle("montoIvaNoRecuperable", linea.substring(180,198).trim());
	        	gen.setDetalle("codigoImpuesto", linea.substring(198,216).trim());
	        	gen.setDetalle("tasaImpuesto2", linea.substring(216,221));
	        	logger.debug("lalalalal" + linea.substring(180,198));
	        	if(!linea.substring(221,239).equals("000000000000000000"))
	        		gen.setDetalle("valorImpuesto", linea.substring(221,239));
	        	gen.setDetalle("montoTotal", linea.substring(239,257));
	        	gen.flushItem("lala");
	        }
	        texto = gen.getTxt();
	        
	        
	     }
	     catch(Exception e){
	    	 logger.error(e,e);
	     }
	     
	     return texto;

		}
	
	public static String getBeanVenta(byte[] infoSPF) throws Exception {
		logger.debug("Start... ");
		String texto = "";
		BufferedReader br = null;

	     try{
	    	 br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(infoSPF), "ISO-8859-1"));
	        String linea="";
	        linea = br.readLine();
	        iecv = new IEBean();
	        gen = new ToTXTIECV();
	        
	        
	        logger.debug("Esta es la linea 1 " + linea.substring(27,33));
	        
	        gen.setCaratula("rutContribuyente",linea.substring(0,10));
	        gen.setCaratula("periodoTributario",linea.substring(20,27));
	        gen.setCaratula("tipoOperacion",linea.substring(27,33));
	        gen.setCaratula("tipoLibro", linea.substring(33,41).trim());
	        gen.setCaratula("tipoEnvio", linea.substring(41,48));
	        linea = br.readLine();
	        
	        while((linea = br.readLine())!=null){
	        	//logger.debug("Esta es la linea 3 " + linea.substring(239,257));
	        	gen.setDetalle("tipoDoc", arreglaString(linea.substring(0,3)));
	        	gen.setDetalle("folio", linea.substring(3,13));
	        	logger.debug("Folio: " + linea.substring(3,13));
	        	gen.setDetalle("folioAnulado", arreglaString(linea.substring(13,14)));
	        	//deta.setTasaImpuesto(Float.parseFloat(arreglaString(linea.substring(14,19))));
	        	gen.setDetalle("fechaDoc", linea.substring(19,29));
	        	gen.setDetalle("rutCliente", linea.substring(29,39));
	        	gen.setDetalle("razonSocial", linea.substring(39,89).trim());
	        	gen.setDetalle("montoExento", linea.substring(102,120));
	        	gen.setDetalle("montoNeto", linea.substring(120,138));
	        	gen.setDetalle("montoIva", linea.substring(138,156));
//	        	deta.setMontoActivo(Long.parseLong(linea.substring(143,161)));
//	        	deta.setMontoIvaActivo(Long.parseLong(linea.substring(161,179)));
//	        	gen.setDetalle("codigoIvaNoRecuperable", arreglaString(linea.substring(179,180)));
//	        	gen.setDetalle("montoIvaNoRecuperable", linea.substring(180,198).trim());
//	        	deta.setCodigoImpuesto(Long.parseLong(linea.substring(198,216).trim()));
//	        	deta.setTasaImpuesto2(Integer.parseInt(linea.substring(216,221)));
//	        	deta.setValorImpuesto(Long.parseLong(linea.substring(221,239)));
	        	gen.setDetalle("montoTotal", linea.substring(192,210));
	        	gen.flushItem("lala");
	        }
	        texto = gen.getTxt();
	        
	        
	     }
	     catch(Exception e){
	    	 logger.error(e,e);
	     }
	     
	     return texto;

		}

}			