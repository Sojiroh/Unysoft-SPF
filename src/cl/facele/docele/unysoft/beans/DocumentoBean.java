package cl.facele.docele.unysoft.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DocumentoBean {
	int folio;
	int tipoDoc;
	BigDecimal montoNeto;
	BigDecimal montoExento;
	BigDecimal montoIva ;
	BigDecimal MontoTotal ;
	int indicadorTraslado;
	
	String mail ;
	String receptorRut ;
	String receptorRazonSocial ;
	String receptorDireccion ;
	String receptorComuna ;
	String receptorCiudad ;
	String receptorGiro ;
	String fechaEmision ;
	String fechaVencimiento ;
	List<DetalleBean> detalles = new ArrayList<DetalleBean>();
	ReferenciasBean referencias;
	String formaPago;
	
	
	
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * @return the caso
	 */
	public int getFolio() {
		return folio;
	}

	/**
	 * @param caso the caso to set
	 */
	public void setFolio(int folio){
		System.out.println("hola");
		this.folio = folio;
	}

	/**
	 * @return the detalles
	 */
	public List<DetalleBean> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalle the detalles to set
	 */
	public void setDetalles(DetalleBean detalle) {
		this.detalles.add(detalle);
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the receptorRut
	 */
	public String getReceptorRut() {
		return receptorRut;
	}

	/**
	 * @param receptorRut the receptorRut to set
	 */
	public void setReceptorRut(String receptorRut) {
		this.receptorRut = receptorRut;
	}

	/**
	 * @return the receptorRazonSocial
	 */
	public String getReceptorRazonSocial() {
		return receptorRazonSocial;
	}

	/**
	 * @param receptorRazonSocial the receptorRazonSocial to set
	 */
	public void setReceptorRazonSocial(String receptorRazonSocial) {
		this.receptorRazonSocial = receptorRazonSocial;
	}

	/**
	 * @return the receptorDireccion
	 */
	public String getReceptorDireccion() {
		return receptorDireccion;
	}

	/**
	 * @param receptorDireccion the receptorDireccion to set
	 */
	public void setReceptorDireccion(String receptorDireccion) {
		this.receptorDireccion = receptorDireccion;
	}

	/**
	 * @return the receptorComuna
	 */
	public String getReceptorComuna() {
		return receptorComuna;
	}

	/**
	 * @param receptorComuna the receptorComuna to set
	 */
	public void setReceptorComuna(String receptorComuna) {
		this.receptorComuna = receptorComuna;
	}

	/**
	 * @return the receptorCiudad
	 */
	public String getReceptorCiudad() {
		return receptorCiudad;
	}

	/**
	 * @param receptorCiudad the receptorCiudad to set
	 */
	public void setReceptorCiudad(String receptorCiudad) {
		this.receptorCiudad = receptorCiudad;
	}

	/**
	 * @return the receptorGiro
	 */
	public String getReceptorGiro() {
		return receptorGiro;
	}

	/**
	 * @param receptorGiro the receptorGiro to set
	 */
	public void setReceptorGiro(String receptorGiro) {
		this.receptorGiro = receptorGiro;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
		
	public ReferenciasBean getReferencias(){
		return referencias;
	}
	
	public void setReferencias(ReferenciasBean referencias) {
		this.referencias =referencias;
	}

	public int getTipoDoc(){
		return tipoDoc;
	}
	
	public void setTipoDoc(int tipoDoc){
		this.tipoDoc = tipoDoc;
	}

	public int getIndicadorTraslado() {
		return indicadorTraslado;
	}

	public void setIndicadorTraslado(int indicadorTraslado) {
		this.indicadorTraslado = indicadorTraslado;
	}

	public void setDetalles(List<DetalleBean> detalles) {
		this.detalles = detalles;
	}

	public BigDecimal getMontoNeto() {
		return montoNeto;
	}

	public void setMontoNeto(BigDecimal montoNeto) {
		this.montoNeto = montoNeto;
	}

	public BigDecimal getMontoExento() {
		return montoExento;
	}

	public void setMontoExento(BigDecimal montoExento) {
		this.montoExento = montoExento;
	}

	public BigDecimal getMontoIva() {
		return montoIva;
	}

	public void setMontoIva(BigDecimal montoIva) {
		this.montoIva = montoIva;
	}

	public BigDecimal getMontoTotal() {
		return MontoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		MontoTotal = montoTotal;
	}

}
