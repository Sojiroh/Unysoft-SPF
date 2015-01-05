package cl.facele.docele.unysoft.beans;

public class DetalleBean {
	int exento;
	int montoExento;
	int neto;
	int totalLinea;
	String tipoDoc ;
	int linea ;
	String nombreItem ;
	int cantidad;
	int precioUnitario;
	
	
	/**
	 * @return the exento
	 */
	
	public int getTotalLinea(){
		return totalLinea;
	}
	
	public void setTotalLinea(int totalLinea){
		this.totalLinea = totalLinea;
	}
	
	public int getCantidad(){
		return cantidad;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	
	public int getPrecioUnitario(){
		return precioUnitario;
	}
	
	public void setPrecioUnitario(int precioUnitario){
		this.precioUnitario = precioUnitario;
	}
	
	public int getExento() {
		return exento;
	}
	/**
	 * @param exento the exento to set
	 */
	public void setExento(int exento) {
		this.exento = exento;
	}

	public int getMontoExento(){
		return montoExento;
	}
	
	public void setMontoExento(int montoExento){
		this.montoExento = montoExento;
	}
	
	public int getNeto(){
		return neto;
	}
	
	public void setNeto(int neto){
		this.neto = neto;
	}

	public String getTipoDoc(){
		return tipoDoc;
	}
	
	public void setTipoDoc(String tipoDoc){
		this.tipoDoc = tipoDoc;
	}
	
	public int getLinea(){
		return linea;
	}
	
	public void setLinea(int linea){
		this.linea = linea;
	} 
	
	public String getNombreItem(){
		return nombreItem;
	}
	
	public void setNombreItem(String glosa){
		this.nombreItem = glosa;
	}

}
