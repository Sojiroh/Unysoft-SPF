package cl.facele.docele.unysoft.logica;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class ToTXTIECV {
	private static Logger logger = Logger.getLogger(ToTXTIECV.class);
	private Map<String, String> caratula = new HashMap<String, String>();
	private Map<String, BigDecimal> resumen = new HashMap<String, BigDecimal>();
	private Map<String, String> m = new HashMap<String, String>();
	private Map<String, Map<String,String>> detalle = new HashMap<String, Map<String,String>>();
	ArrayList<String> arrTipoDocs = new ArrayList<String>();
	ArrayList<String> arrTipoIVANoRecuperable = new ArrayList<String>();
	ArrayList<String> arrTipoImpuestoAdicional = new ArrayList<String>();
	
	private int numLineaDetalle=1;
	private String str;
	
	/**
	 redaIEVCCaratulaOperacion
	 redaIEVCCaratulaPeriodo
	 redaIEVCCaratulaTipoLibro
	 redaIEVCCaratulaTipoeEnvio
	 redaIEVCCaratulaNumeroSegmento
	 redaIEVCCaratulaNumeroNotificacion
	 redaIEVCCaratulaCodigoAutorizacion
	 
	 redaIEVCDetalleAnulado
	 redaIEVCDetalleTipoDocRegistro
	 redaIEVCDetalleFolioRegistro
	 redaIEVCDetalleFechaRegistro
	 redaIEVCDetalleRutRegistro
	 redaIEVCDetalleRznSocialRegistro
	 redaIEVCDetalleExentoRegistro
	 redaIEVCDetalleNetoRegistro
	 redaIEVCDetalleMntIVARegistro
	 redaIEVCDetalleTotalRegistro
	 redaIEVCDetalleIVARetenidoRegistro
	 redaIEVCDetalleIVARetParcialRegistro
	 redaIEVCDetalleLey18211Registro
	 redaIEVCDetalleIvaNoRecCodigo
	 redaIEVCDetalleIvaNoRecMonto
	 redaIEVCDetalleImpRecCodigoRegistro
	 redaIEVCDetalleImpRecTasaRegistro*
	 redaIEVCDetalleImpRecMontoRegistro 
	 ***/
	
	public void setCaratula(String K, String V) {
		if (V.contains(";")) V = V.replace(";", ",").trim();
		caratula.put(K, V.trim().toUpperCase());
	}
	
	public void setDetalle(String K, String V) {
		if (V.contains(";")) 
			V = V.replace(";", ",").trim();
		m.put(K, V.trim().toUpperCase());
	}
	
	public void flushItem(String nombreItem) throws Exception {
		if (!m.containsKey("tipoDoc"))
			throw new Exception("Falta definir el tipo de documento.");

		
		String tipoDocumento = m.get("tipoDoc");
		logger.debug(tipoDocumento);
		
		
		
		if (!resumen.containsKey("TipoDoc_" + tipoDocumento)) {
			arrTipoDocs.add(tipoDocumento);
			resumen.put("TipoDoc_" + tipoDocumento, new BigDecimal(tipoDocumento));
			resumen.put("Cantidad_" + tipoDocumento, new BigDecimal(0));
			resumen.put("Anulados_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoNeto_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoExento_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoIVA_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoTotal_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoRetencionParcial_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoRetencionTotal_" + tipoDocumento, new BigDecimal(0));
			resumen.put("MontoLey18211_" + tipoDocumento, new BigDecimal(0));				
		}
		
		BigDecimal cantidadDocs = new BigDecimal(1);
		cantidadDocs = cantidadDocs.add(resumen.get("Cantidad_" + tipoDocumento));
		resumen.put("Cantidad_" + tipoDocumento, cantidadDocs);
		
		if (m.containsKey("folioAnulado") && m.get("folioAnulado").equals("A")) {
			BigDecimal cantidadAnulados = new BigDecimal(1);
			cantidadAnulados = cantidadAnulados.add(resumen.get("Anulados_" + tipoDocumento));
			resumen.put("Anulados_" + tipoDocumento, cantidadAnulados);
		}
		if (m.containsKey("montoNeto")) {
			BigDecimal montoNeto = new BigDecimal(m.get("montoNeto"));
			montoNeto = montoNeto.add(resumen.get("MontoNeto_" + tipoDocumento));
			resumen.put("MontoNeto_" + tipoDocumento, montoNeto);
		}
		if (m.containsKey("montoExento")) {
			BigDecimal montoExento = new BigDecimal(m.get("montoExento"));
			logger.debug("Monto exento item: " + montoExento.toString());
			montoExento = montoExento.add(resumen.get("MontoExento_" + tipoDocumento));
			logger.debug("Monto exento acumulado_arter: " + montoExento.toString());
			resumen.put("MontoExento_" + tipoDocumento, montoExento);
		}
		if(m.containsKey("montoIvaActivo")){
			BigDecimal montoIvaFijo = new BigDecimal(m.get("montoIvaActivo"));
			logger.debug("Monto IVA Activo: " + montoIvaFijo.toString());
			montoIvaFijo = montoIvaFijo.add(resumen.get("MontoIvaActivo_" + tipoDocumento));
			logger.debug("Monto IVA Activo acumulado_arter: " + montoIvaFijo.toString());
			resumen.put("MontoIvaActivo_" + tipoDocumento, montoIvaFijo);
		}
		
		if (m.containsKey("montoIva")) {
			BigDecimal montoIVA = new BigDecimal(m.get("montoIva"));
			logger.debug("Monto IVA item: " + montoIVA.toString());
			montoIVA = montoIVA.add(resumen.get("MontoIVA_" + tipoDocumento));
			logger.debug("Monto IVA acumulado_arter: " + montoIVA.toString());
			resumen.put("MontoIVA_" + tipoDocumento, montoIVA);
		}
		if (m.containsKey("montoTotal")) {
			BigDecimal montoTotal = new BigDecimal(m.get("montoTotal"));
			montoTotal =  montoTotal.add(resumen.get("MontoTotal_" + tipoDocumento));
			resumen.put("MontoTotal_" + tipoDocumento, montoTotal);			
		}
		if (m.containsKey("redaIEVCDetalleIVARetParcialRegistro")) {
			BigDecimal montoRetenidoParcial = new BigDecimal(m.get("redaIEVCDetalleIVARetParcialRegistro"));
			montoRetenidoParcial = montoRetenidoParcial.add(resumen.get("MontoRetencionParcial_" + tipoDocumento));
			resumen.put("MontoRetencionParcial_" + tipoDocumento, montoRetenidoParcial);
		}
		if (m.containsKey("redaIEVCDetalleIVARetenidoRegistro")) {
			BigDecimal montoRetenidoTotal = new BigDecimal(m.get("redaIEVCDetalleIVARetenidoRegistro"));
			montoRetenidoTotal = montoRetenidoTotal.add(resumen.get("MontoRetencionTotal_" + tipoDocumento));
			resumen.put("MontoRetencionTotal_" + tipoDocumento, montoRetenidoTotal);
		}
		if (m.containsKey("redaIEVCDetalleLey18211Registro")) {
			BigDecimal montoLey18211 = new BigDecimal(m.get("redaIEVCDetalleLey18211Registro"));
			montoLey18211 = montoLey18211.add(resumen.get("MontoLey18211_" + tipoDocumento));
			resumen.put("MontoLey18211_" + tipoDocumento, montoLey18211);
		}
		if (m.containsKey("montoIvaNoRecuperable")) {
			BigDecimal montoIVANoRecuperable = new BigDecimal(m.get("montoIvaNoRecuperable"));
			String str = "IVANoRecuperabe_" + tipoDocumento + "_" + m.get("codigoIvaNoRecuperable");
			if (!resumen.containsKey(str + "_monto")) {
				arrTipoImpuestoAdicional.add(str);
				resumen.put(str + "_monto", montoIVANoRecuperable);
				resumen.put(str + "_cantidad", new BigDecimal(1));
				resumen.put(str + "_codigo", new BigDecimal(m.get("codigoIvaNoRecuperable")));
			} else {
				montoIVANoRecuperable = montoIVANoRecuperable.add(resumen.get(str + "_monto"));
				resumen.put(str + "_monto", montoIVANoRecuperable);
				BigDecimal cantOperacionesIvaNoRec = new BigDecimal(1);
				cantOperacionesIvaNoRec = cantOperacionesIvaNoRec.add(resumen.get(str + "_cantidad"));
				resumen.put(str + "_cantidad", cantOperacionesIvaNoRec);
			}			
		}
		if (m.containsKey("valorImpuesto")) {
			BigDecimal montoImpuestoRecargo = new BigDecimal(m.get("valorImpuesto"));
			String str = "ImpuestoAdicional_" + tipoDocumento + "_" + m.get("codigoImpuesto");
			if (!resumen.containsKey(str + "_monto")) {
				arrTipoImpuestoAdicional.add(str);
				resumen.put(str + "_monto", montoImpuestoRecargo);
				resumen.put(str + "_cantidad", new BigDecimal(1));
				resumen.put(str + "_codigo", new BigDecimal(m.get("codigoImpuesto")));
				//	resumen.put(str + "_factor", new BigDecimal(m.get("tasaImpuesto2")));
			} else {
				montoImpuestoRecargo = montoImpuestoRecargo.add(resumen.get(str + "_monto"));
				resumen.put(str + "_monto", montoImpuestoRecargo);
				BigDecimal cantidadOperacionesImpuestoAdicionales =  new BigDecimal(1);
				cantidadOperacionesImpuestoAdicionales = cantidadOperacionesImpuestoAdicionales.add(resumen.get(str + "_cantidad"));
				resumen.put(str + "_cantidad", cantidadOperacionesImpuestoAdicionales);
			}
		}

		Map <String,String> mapaux = new HashMap<String,String>();
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			mapaux.put(e.getKey().toString(), e.getValue().toString());
		};
		detalle.put("numeroLinea_" + numLineaDetalle, mapaux);
		m.clear();
		numLineaDetalle++;
		return;
	
	}

	public String getTxt() throws ParseException {
		String result ="";
		Date fecha = null;
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat caratulaOrigen = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat caratulaTxt = new SimpleDateFormat("yyyy-MM");

		//caraturla
		String caratu = "";
		for (int i=0; i<10; i++) {
			fecha = caratulaOrigen.parse(caratula.get("periodoTributario"));
			switch (i) {
			case 0:
				caratu = "A" + ";";
				break;
			case 1:
				caratu +=  caratula.get("tipoOperacion") + ";";
				break;
			case 2:
				caratu +=  caratula.get("tipoLibro") + ";";
				break;
			case 3:
				caratu +=  caratula.get("rutContribuyente") + ";";
				break;
			case 4:
				caratu +=  caratulaTxt.format(fecha) + ";";
				break;
			case 5:
				caratu +=  caratula.get("tipoEnvio") + ";";
				break;
			case 6:
//				caratu +=  caratula.get("redaIEVCCaratulaNumeroSegmento") + ";";
				caratu +=  ";";
				break;
			case 7:
//				caratu +=  caratula.get("redaIEVCCaratulaNumeroNotificacion") + ";";
				caratu +=  ";";
				break;
			case 8:
//				caratu +=  caratula.get("redaIEVCCaratulaCodigoAutorizacion") + ";";
				caratu +=  ";";
				break;
			case 9:
				caratu +=  "\n";
				break;
			}
		}
		caratu = caratu.replace("null", "");
		
		//resumen
		String resu = "";
		for (int j=0; j<arrTipoDocs.size(); j++) {
			String tipoDocumento = arrTipoDocs.get(j);			
			for (int i=0; i<40 ; i++) {
				
				switch (i) {
				case 0:
					resu += "B" + ";";
					break;
				case 1:
					resu += resumen.get("TipoDoc_" + tipoDocumento) + ";";
					break;
				case 3:
					resu += resumen.get("Cantidad_" + tipoDocumento) + ";";
					break;
				case 5:
					resu += resumen.get("MontoExento_" + tipoDocumento) + ";";
					break;
				case 6:
					resu += resumen.get("MontoNeto_" + tipoDocumento) + ";";
					break;
				case 8:
					resu += resumen.get("MontoIVA_" + tipoDocumento) + ";";
					break;
				case 10:
					resu += resumen.get("MontoIvaActivo_" + tipoDocumento)+ ";";
					break;
				case 15:
//					resu += resumen.get("MontoLey18211_" + tipoDocumento) + ";";
					resu += ";";
					break;
				case 18:
//					resu += resumen.get("MontoRetencionTotal_" + tipoDocumento) + ";";
					resu +=  ";";
					break;
				case 20:
//					resu += resumen.get("MontoRetencionParcial_" + tipoDocumento) + ";";
					resu += ";";

					break;
				case 23:
					resu += resumen.get("MontoTotal_" + tipoDocumento) + ";";
					break;
				case 35:
					resu += resumen.get("Anulados_" + tipoDocumento) + ";";
					break;
				case 39:
					resu += "\n";
					break;
				default:
					resu += ";";
					break;
				}
			}
			
			//impuesto Adicional
			for (int i=0; i<arrTipoImpuestoAdicional.size(); i++) {
				if (arrTipoImpuestoAdicional.get(i).contains("_" + tipoDocumento + "_")) {
					resu += "B1" + ";";
					resu += resumen.get(arrTipoImpuestoAdicional.get(i) + "_codigo") + ";";
					resu += resumen.get(arrTipoImpuestoAdicional.get(i) + "_monto") + ";";
					resu += resumen.get(arrTipoImpuestoAdicional.get(i) + "_factor") + ";";
					resu += ";";
					resu += "\n";
				}
			}
			
			//IVa no recuperable
			for (int i=0; i<arrTipoIVANoRecuperable.size(); i++) {
				if(arrTipoIVANoRecuperable.get(i).contains("_" + tipoDocumento + "_")) {
					resu += "B2" + ";";
					resu += resumen.get(arrTipoIVANoRecuperable.get(i) + "_codigo") + ";";
					resu += resumen.get(arrTipoIVANoRecuperable.get(i) + "_cantidad") + ";";
					resu += resumen.get(arrTipoIVANoRecuperable.get(i) + "_monto") + ";";
					resu += "\n";
				}
			}
		}
		
		resu = resu.replace("null", "");		
		  
		//detalle
		String deta = "";
		int numLinea = 1;
		while (true) {
			if (detalle.containsKey("numeroLinea_" + numLinea)) {
				m.clear();
				m = detalle.get("numeroLinea_" + numLinea);
				
				for (int i=0; i<43; i++) {
					fecha = formatoDeFecha.parse(m.get("fechaDoc"));
					switch (i) {
					case 0:
						deta += "C" + ";";
						break;
					case 1:
						deta += m.get("tipoDoc") + ";";
						break;
					case 2:
						deta += m.get("folio") + ";";
						break;
					case 3:
						str = "";
						if (m.get("folioAnulado").equals("A"))
							str = "A";
						deta += str + ";";
						break;
					case 6:
						deta += "19" + ";";
						break;
					case 10:
						deta += formato.format(fecha) + ";";
						break;
					case 11:
//						deta += m.get("redaIEVCDetalleCodigoSucursalRegistro") + ";";
						deta +=  ";";

						break;
					case 12:
						deta += m.get("rutCliente") + ";";
						break;
					case 13:
						deta += m.get("razonSocial") + ";";
						break;
					case 16:
						deta += m.get("montoExento") + ";";	
						break;
					case 17:
						deta += m.get("montoNeto") + ";";
						break;
					case 18:
						deta += m.get("montoIva") + ";";
						break;
					case 19:
						deta += m.get("montoIvaActivo") + ";";
						break;
					case 22:
						str = "";
//						if (m.containsKey("redaIEVCDetalleLey18211Registro") && m.get("redaIEVCDetalleLey18211Registro").equals("0"))
						if (!m.containsKey("redaIEVCDetalleLey18211Registro"))
							str = "";
						else
							str = m.get("redaIEVCDetalleLey18211Registro");
						deta += str + ";";
						break;
					case 24:
						str = "";
//						if (m.containsKey("redaIEVCDetalleIVARetenidoRegistro") && m.get("redaIEVCDetalleIVARetenidoRegistro").equals("0"))
						if (!m.containsKey("redaIEVCDetalleIVARetenidoRegistro"))
							str = "";
						else
							str = m.get("redaIEVCDetalleIVARetenidoRegistro");
						deta += str + ";";
						break;
					case 25:
						str = "";
//						if (m.containsKey("redaIEVCDetalleIVARetParcialRegistro") && m.get("redaIEVCDetalleIVARetParcialRegistro").equals("0"))
						if (!m.containsKey("redaIEVCDetalleIVARetParcialRegistro"))

							str = "";
						else
							str = m.get("redaIEVCDetalleIVARetParcialRegistro");
						deta += str + ";";
						break;
					case 28:
						deta += Long.parseLong(m.get("montoTotal")) + ";";
						break;
					case 36:
						if(m.get("montoActivo")==null)
							deta += "" + ";";
						else
						deta += Long.parseLong(m.get("montoActivo")) + ";";
						break;
					case 42:
						deta += "\n";
						break;
					default:
						deta += ";";
						break;
					}
				}
				
				//impuesto adicional
				if (m.containsKey("valorImpuesto")) {
					if(new BigDecimal(m.get("valorImpuesto")).signum()!=0){
					deta += "C1" + ";";
					deta += m.get("codigoImpuesto") + ";";
					deta += m.get("tasaImpuesto2").replace(',', '.') + ";";
					deta += m.get("valorImpuesto") + ";";
					deta += "\n";
					}
				}
//				
				//Iva no recuperable
				if (m.containsKey("montoIvaNoRecuperable")) {
					if(new BigDecimal(m.get("montoIvaNoRecuperable")).signum()!=0){
					deta += "C2" + ";";
					deta += m.get("codigoIvaNoRecuperable") + ";";
					deta += m.get("montoIvaNoRecuperable") + ";";
					deta += "\n";
					}
				}
			} else break;
			
			numLinea ++;			
		}
		deta = deta.replace("null", "");
		
		result =  caratu + resu + deta;
		
		return result;
	}
}
