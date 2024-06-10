package net.cubosoft.csbypassInterprise.entities;

public class PacienteGetOrdenesDTO {

	private String id_pac;
	private String cod_pac;
	private String nombre;
	private String apellido;
	
	public PacienteGetOrdenesDTO(String id_pac, String cod_pac, String nombre, String apellido) {		
		this.id_pac = id_pac;
		this.cod_pac = cod_pac;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public PacienteGetOrdenesDTO() {		
	}

	public String getId_pac() {
		return id_pac;
	}

	public void setId_pac(String id_pac) {
		this.id_pac = id_pac;
	}

	public String getCod_pac() {
		return cod_pac;
	}

	public void setCod_pac(String cod_pac) {
		this.cod_pac = cod_pac;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
	
	



   }
