package net.cubosoft.csbypassInterprise.entities;

public class OrdenProgressDTO {

	
	private String nro_orden;
	private Integer pedientes;
	private Integer peticiones;
	private Integer completos;
	private Integer completados;
	private Integer adj_orden;
	private Integer adj_res;
	
	public OrdenProgressDTO(String nro_orden, Integer pedientes, Integer peticiones, Integer completos,
			Integer completados, Integer adj_orden, Integer adj_res) {
		
		this.nro_orden = nro_orden;
		this.pedientes = pedientes;
		this.peticiones = peticiones;
		this.completos = completos;
		this.completados = completados;
		this.adj_orden = adj_orden;
		this.adj_res = adj_res;
	}

	public OrdenProgressDTO() {
		
	}

	public String getNro_orden() {
		return nro_orden;
	}

	public void setNro_orden(String nro_orden) {
		this.nro_orden = nro_orden;
	}

	public Integer getPedientes() {
		return pedientes;
	}

	public void setPedientes(Integer pedientes) {
		this.pedientes = pedientes;
	}

	public Integer getPeticiones() {
		return peticiones;
	}

	public void setPeticiones(Integer peticiones) {
		this.peticiones = peticiones;
	}

	public Integer getCompletos() {
		return completos;
	}

	public void setCompletos(Integer completos) {
		this.completos = completos;
	}

	public Integer getCompletados() {
		return completados;
	}

	public void setCompletados(Integer completados) {
		this.completados = completados;
	}

	public Integer getAdj_orden() {
		return adj_orden;
	}

	public void setAdj_orden(Integer adj_orden) {
		this.adj_orden = adj_orden;
	}

	public Integer getAdj_res() {
		return adj_res;
	}

	public void setAdj_res(Integer adj_res) {
		this.adj_res = adj_res;
	}
	

	
	
}
 