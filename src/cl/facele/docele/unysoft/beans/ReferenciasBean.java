package cl.facele.docele.unysoft.beans;

public class ReferenciasBean {
	int tipoDocRef ;
	int folioRef ;
	String fechaRef ;
	int codRef ;
	int vacio ;
	
	public int getVacio() {
		return vacio;
	}
	public void setVacio(int vacio) {
		this.vacio = vacio;
	}
	/**
	 * @return the tipoDocRef
	 */
	public int getTipoDocRef() {
		return tipoDocRef;
	}
	/**
	 * @param tipoDocRef the tipoDocRef to set
	 */
	public void setTipoDocRef(int tipoDocRef) {
		this.tipoDocRef = tipoDocRef;
	}
	/**
	 * @return the folioRef
	 */
	public int getFolioRef() {
		return folioRef;
	}
	/**
	 * @param folioRef the folioRef to set
	 */
	public void setFolioRef(int folioRef) {
		this.folioRef = folioRef;
	}
	/**
	 * @return the fechaRef
	 */
	public String getFechaRef() {
		return fechaRef;
	}
	/**
	 * @param fechaRef the fechaRef to set
	 */
	public void setFechaRef(String fechaRef) {
		this.fechaRef = fechaRef;
	}
	/**
	 * @return the codRef
	 */
	public int getCodRef() {
		return codRef;
	}
	/**
	 * @param codRef the codRef to set
	 */
	public void setCodRef(int codRef) {
		this.codRef = codRef;
	}

}
