package cl.facele.docele.unysoft.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class IEBean {
	private static Logger logger = Logger.getLogger(Object.class);

	String rutContribuyente;
	String rutEnviador;
	String periodoTributario;
	String tipoOperacion;
	String tipoLibro;
	String tipoEnvio;
	List<IEDetalleBean> detalles = new ArrayList<IEDetalleBean>();
	
	
	
	/**
	 * @return the detalles
	 */
	public List<IEDetalleBean> getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(IEDetalleBean detalle) {
		logger.debug(detalle.getTipoDoc());
		this.detalles.add(detalle);
	}
	/**
	 * @return the rutContribuyente
	 */
	public String getRutContribuyente() {
		return rutContribuyente;
	}
	/**
	 * @param rutContribuyente the rutContribuyente to set
	 */
	public void setRutContribuyente(String rutContribuyente) {
		this.rutContribuyente = rutContribuyente;
	}
	/**
	 * @return the rutEnviador
	 */
	public String getRutEnviador() {
		return rutEnviador;
	}
	/**
	 * @param rutEnviador the rutEnviador to set
	 */
	public void setRutEnviador(String rutEnviador) {
		this.rutEnviador = rutEnviador;
	}
	/**
	 * @return the periodoTributario
	 */
	public String getPeriodoTributario() {
		return periodoTributario;
	}
	/**
	 * @param periodoTributario the periodoTributario to set
	 */
	public void setPeriodoTributario(String periodoTributario) {
		this.periodoTributario = periodoTributario;
	}
	/**
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	/**
	 * @return the tipoLibro
	 */
	public String getTipoLibro() {
		return tipoLibro;
	}
	/**
	 * @param tipoLibro the tipoLibro to set
	 */
	public void setTipoLibro(String tipoLibro) {
		this.tipoLibro = tipoLibro;
	}
	/**
	 * @return the tipoEnvio
	 */
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	/**
	 * @param tipoEnvio the tipoEnvio to set
	 */
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public void metodoX() {
		for(IEDetalleBean d : detalles){
			System.out.println(d.getTipoDoc());
		}
		// TODO Auto-generated method stub
		
	}
	
}
