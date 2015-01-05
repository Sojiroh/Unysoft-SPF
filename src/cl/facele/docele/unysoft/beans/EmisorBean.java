package cl.facele.docele.unysoft.beans;

import java.util.List;
import java.util.Properties;

public class EmisorBean {
	String rutContribuyente;
	String razonSocial;
	String giro;
	Properties casaMatriz = new Properties();
	/*
	 * CIUDAD, COMUNA, DIRECCION
	 */
	String direccion;
	String comuna;
	String ciudad;
	List<Integer> acteco;
	
	public Properties getCasaMatriz() {
		return casaMatriz;
	}
	/**
	 * @param sucursales the sucursales to set
	 */
	public void setCasaMatriz(Properties sucursales) {
		this.casaMatriz = sucursales;
	}
	
	public String getDireccion(){
		return direccion;
	}
	
	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	
	public String getComuna(){
		return comuna;
	}
	
	public void setComuna(String comuna){
		this.comuna = comuna;
	}
	
	public String getCiudad(){
		return ciudad;
	}
	
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
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
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}
	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the giro
	 */
	public String getGiro() {
		return giro;
	}
	/**
	 * @param giro the giro to set
	 */
	public void setGiro(String giro) {
		this.giro = giro;
	}

	/**
	 * @return the acteco
	 */
	public List<Integer> getActeco() {
		return acteco;
	}
	/**
	 * @param acteco the acteco to set
	 */
	public void setActeco(List<Integer> acteco) {
		this.acteco = acteco;
	}

}
