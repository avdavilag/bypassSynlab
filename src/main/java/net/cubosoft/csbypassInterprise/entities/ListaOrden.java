package net.cubosoft.csbypassInterprise.entities;

public class ListaOrden {

	private String nro_Orden;
	private String nro_ext;
	private String obs_ext;
	public ListaOrden(String nro_Orden, String nro_ext, String obs_ext) {		
		this.nro_Orden = nro_Orden;
		this.nro_ext = nro_ext;
		this.obs_ext = obs_ext;
	}
	public ListaOrden() {
		
	}
	public String getNro_Orden() {
		return nro_Orden;
	}
	public void setNro_Orden(String nro_Orden) {
		this.nro_Orden = nro_Orden;
	}
	public String getNro_ext() {
		return nro_ext;
	}
	public void setNro_ext(String nro_ext) {
		this.nro_ext = nro_ext;
	}
	public String getObs_ext() {
		return obs_ext;
	}
	public void setObs_ext(String obs_ext) {
		this.obs_ext = obs_ext;
	}

	
	
	
	
	
	
	
	
	
}
