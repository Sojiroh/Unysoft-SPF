package cl.facele.docele.unysoft.logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cl.facele.docele.unysoft.beans.DetalleBean;
import cl.facele.docele.unysoft.beans.DocumentoBean;
import cl.facele.docele.unysoft.beans.EmisorBean;
import cl.facele.docele.unysoft.beans.ReferenciasBean;
import cl.facele.docele.unysoft.beans.DetalleBean_1;
import cl.facele.docele.unysoft.beans.DocumentoBean2;


public class GeneraTXT {

	private static Logger logger = Logger.getLogger(Object.class);

	
	public String generaFacturaTexto(DocumentoBean documentoBean, EmisorBean cBean) throws Exception {
		int numeroDetalle = 0;
		String txt ="";
		Date fecha = null;
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		ReferenciasBean rBean = documentoBean.getReferencias();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		DetalleBean b = documentoBean.getDetalles().get(0);
		
		String detalle = "";
		String referencia = "";

		if(rBean.getVacio()==0){
		logger.debug("fecha: " + rBean.getFechaRef());
		fecha = formatoDeFecha.parse(rBean.getFechaRef());
		String razonReferencia = b.getNombreItem();
		if (b.getNombreItem().length()>90){
			razonReferencia = b.getNombreItem().substring(0,86) + "...";
		}
		
		referencia += "D;1;"+rBean.getTipoDocRef()+ ";;" +
				rBean.getFolioRef() + ";;" +
					formato.format(fecha) + ";" +
								rBean.getCodRef() + ";"+
									razonReferencia +";";
		
		}
		for (DetalleBean d : documentoBean.getDetalles()) { 
			numeroDetalle++;
			//total = total + Integer.parseInt(b.getNeto());
			
			
			detalle += "B;" +
					numeroDetalle + ";" +
							";" +
									d.getNombreItem() + ";;;;;" +
											d.getCantidad() + ";;;;" +
													d.getPrecioUnitario() + ";;;;" +
														0 + ";" +
																0 + ";;;;" + 
																	d.getTotalLinea() +";" + "\n";
			
	
		}
		
		if (documentoBean.getReceptorDireccion()== null)
			documentoBean.setReceptorDireccion("NO INFORMADO");
		if(documentoBean.getReceptorCiudad()==null)
			documentoBean.setReceptorCiudad("NO INFORMADO");
		if(documentoBean.getReceptorComuna()==null)
			documentoBean.setReceptorComuna("NO INFORMADO");
		fecha = formatoDeFecha.parse(documentoBean.getFechaEmision());
		String encabezado = "A0;;;;;;;;;;;" + "\n";
		encabezado += "A;"+ documentoBean.getTipoDoc() + ";1.0;" +
				documentoBean.getFolio() + ";" +		//caso
				formato.format(fecha) + ";;;;;;"+documentoBean.getFormaPago()+";;;;;;;;" +
						cBean.getRutContribuyente() + ";" +
								cBean.getRazonSocial() + ";" +
										cBean.getGiro() + ";;;" +
										cBean.getCasaMatriz().getProperty("DIRECCION") + ";" +
										cBean.getCasaMatriz().getProperty("COMUNA") + ";" +
												cBean.getCasaMatriz().getProperty("CIUDAD") + ";;;"+
																documentoBean.getReceptorRut() + ";;" +
																documentoBean.getReceptorRazonSocial() + ";" + 
																documentoBean.getReceptorGiro() + ";;" +
																					documentoBean.getReceptorDireccion() + ";" +
																						documentoBean.getReceptorComuna() + ";" + 
																							documentoBean.getReceptorCiudad() + ";;;;;;;;;;" +
																								documentoBean.getMontoNeto().toString() + ";" +
																									documentoBean.getMontoExento() + ";;19;" +
																										documentoBean.getMontoIva().toString() +";;;;;;" +
																											documentoBean.getMontoTotal().toString() + ";;;"+ "\n";
		encabezado += getActeco(cBean.getActeco());
																										
																						
		
		if (encabezado.contains("null"))
			encabezado = encabezado.replaceAll("null", "");
		if (detalle.contains("null"))
			detalle = detalle.replaceAll("null", "");
		
//		logger.debug("\n" + encabezado + detalle+ referencia);
		
	    txt=encabezado+detalle+referencia;
	    return txt;
	}
        
        public String generaFacturaExenta(DocumentoBean documentoBean, EmisorBean cBean) throws Exception {
		int numeroDetalle = 0;
		String txt ="";
		Date fecha = null;
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		ReferenciasBean rBean = documentoBean.getReferencias();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		DetalleBean b = documentoBean.getDetalles().get(0);
		
		String detalle = "";
		String referencia = "";

		if(rBean.getVacio()==0){
		logger.debug("fecha: " + rBean.getFechaRef());
		fecha = formatoDeFecha.parse(rBean.getFechaRef());
		String razonReferencia = b.getNombreItem();
		if (b.getNombreItem().length()>90){
			razonReferencia = b.getNombreItem().substring(0,86) + "...";
		}
		
		referencia += "D;1;"+rBean.getTipoDocRef()+ ";;" +
				rBean.getFolioRef() + ";;" +
					formato.format(fecha) + ";" +
								rBean.getCodRef() + ";"+
									razonReferencia +";";
		
		}
		for (DetalleBean d : documentoBean.getDetalles()) { 
			numeroDetalle++;
			//total = total + Integer.parseInt(b.getNeto());
			
			
			detalle += "B;" +
					numeroDetalle + ";" +
							"1;" +
									d.getNombreItem() + ";;;;;" +
											d.getCantidad() + ";;;;" +
													d.getPrecioUnitario() + ";;;;" +
														0 + ";" +
																0 + ";;;;" + 
																	d.getTotalLinea() +";" + "\n";
			
	
		}
		
		if (documentoBean.getReceptorDireccion()== null)
			documentoBean.setReceptorDireccion("NO INFORMADO");
		if(documentoBean.getReceptorCiudad()==null)
			documentoBean.setReceptorCiudad("NO INFORMADO");
		if(documentoBean.getReceptorComuna()==null)
			documentoBean.setReceptorComuna("NO INFORMADO");
		fecha = formatoDeFecha.parse(documentoBean.getFechaEmision());
		String encabezado = "A0;;;;;;;;;;;" + "\n";
		encabezado += "A;"+ documentoBean.getTipoDoc() + ";1.0;" +
				documentoBean.getFolio() + ";" +		//caso
				formato.format(fecha) + ";;;;;;"+documentoBean.getFormaPago()+";;;;;;;;" +
						cBean.getRutContribuyente() + ";" +
								cBean.getRazonSocial() + ";" +
										cBean.getGiro() + ";;;" +
										cBean.getCasaMatriz().getProperty("DIRECCION") + ";" +
										cBean.getCasaMatriz().getProperty("COMUNA") + ";" +
												cBean.getCasaMatriz().getProperty("CIUDAD") + ";;;"+
																documentoBean.getReceptorRut() + ";;" +
																documentoBean.getReceptorRazonSocial() + ";" + 
																documentoBean.getReceptorGiro() + ";;" +
																					documentoBean.getReceptorDireccion() + ";" +
																						documentoBean.getReceptorComuna() + ";" + 
																							documentoBean.getReceptorCiudad() + ";;;;;;;;;;" +
																								documentoBean.getMontoNeto().toString() + ";" +
																									documentoBean.getMontoExento() + ";;19;" +
																										documentoBean.getMontoIva().toString() +";;;;;;" +
																											documentoBean.getMontoTotal().toString() + ";;;"+ "\n";
		encabezado += getActeco(cBean.getActeco());
																										
																						
		
		if (encabezado.contains("null"))
			encabezado = encabezado.replaceAll("null", "");
		if (detalle.contains("null"))
			detalle = detalle.replaceAll("null", "");
		
//		logger.debug("\n" + encabezado + detalle+ referencia);
		
	    txt=encabezado+detalle+referencia;
	    return txt;
	}
        
        public String generaFacturaCompra(DocumentoBean2 documentoBean, EmisorBean cBean) throws Exception {
		int numeroDetalle = 0;
		String txt ="";
		Date fecha = null;
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		ReferenciasBean rBean = documentoBean.getReferencias();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		DetalleBean_1 b = documentoBean.getDetalles().get(0);
		
		String detalle = "";
		String referencia = "";

		if(rBean.getVacio()==0){
		logger.debug("fecha: " + rBean.getFechaRef());
		fecha = formatoDeFecha.parse(rBean.getFechaRef());
		String razonReferencia = b.getNombreItem();
		if (b.getNombreItem().length()>90){
			razonReferencia = b.getNombreItem().substring(0,86) + "...";
		}
		
		referencia += "D;1;"+rBean.getTipoDocRef()+ ";;" +
				rBean.getFolioRef() + ";;" +
					formato.format(fecha) + ";" +
								rBean.getCodRef() + ";"+
									razonReferencia +";";
		
		}
		for (DetalleBean_1 d : documentoBean.getDetalles()) { 
			numeroDetalle++;
			//total = total + Integer.parseInt(b.getNeto());
			
			detalle += "DET;"+
                                
                        numeroDetalle  +
                                ";;;"+
                                "R;"+
                                ";;;"+
                                d.getNombreItem()+";"+
                                ";;;;"+
                                arreglaFolio(d.getCantidad().replace(",", "."))+";;;;"+
                                d.getPrecioUnitario()+";;;;;"+
                                d.getTotalLinea() +";" + "\n"+
                                "DET-7;41;"+ "\n";
                                
                        
                        
			
	
		}
		
		if (documentoBean.getReceptorDireccion()== null)
			documentoBean.setReceptorDireccion("NO INFORMADO");
		if(documentoBean.getReceptorCiudad()==null)
			documentoBean.setReceptorCiudad("NO INFORMADO");
		if(documentoBean.getReceptorComuna()==null)
			documentoBean.setReceptorComuna("NO INFORMADO");
		fecha = formatoDeFecha.parse(documentoBean.getFechaEmision());
                String encabezado = "A0;;;;;;;;;;;" + "\n";
                encabezado += "ENC;46;"+documentoBean.getFolio()+";"+
                        formato.format(fecha)+";;;;;;;"+
                        2+";;;;;;;;;;;;;;;"+
                        cBean.getRutContribuyente()+";"+
                        cBean.getRazonSocial() + ";" +
                        cBean.getGiro() + ";;;" +
                        "141000;451010;514320;;;;;;;;;;;;;;"+
                        documentoBean.getReceptorRut()+";;"+
                        documentoBean.getReceptorRazonSocial()+";;;;"+
                        documentoBean.getReceptorGiro()+";;;"+
                        documentoBean.getReceptorDireccion() + ";" +
                        documentoBean.getReceptorComuna() + ";" + 
                        documentoBean.getReceptorCiudad() + ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;"+
                        documentoBean.getMontoNeto().toString() + ";" +
                        documentoBean.getMontoExento()+";;;19;"+
                        documentoBean.getMontoIva().toString()+";;;;;;;;;"+
                        documentoBean.getMontoTotal().toString()+";;;;;;;;;;;;;;" + "\n"+
                        "ENC-4;41;19;"+documentoBean.getMontoIva().toString()+";" + "\n";
                
                
		
																										
																						
		
		if (encabezado.contains("null"))
			encabezado = encabezado.replaceAll("null", "");
		if (detalle.contains("null"))
			detalle = detalle.replaceAll("null", "");
		
//		logger.debug("\n" + encabezado + detalle+ referencia);
		
	    txt=encabezado+detalle+referencia;
	    return txt;
	}

	private String getActeco(List<Integer> actecos) {
		String retorna = "";
		int n = 0;
		for (int acteco: actecos) {
			n++;
			retorna += "A1;" + acteco + ";" + "\n";
			if (n > 3)
				break;
		}
		return retorna;
	}
        
        public static String arreglaFolio(String folio){
            int cantidad= folio.length();
            for (int i= 0;i<cantidad;i++){
                if(folio.startsWith("0")){
                    folio=folio.substring(1);
                }
            }
            return folio;
        }
}
