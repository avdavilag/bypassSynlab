package net.cubosoft.csbypassInterprise.entities;

public class RequestJsonPaciente {
	private String respuesta;
	private String mensaje;
	private String jsonresult;
	
	public RequestJsonPaciente(String respuesta, String mensaje, String jsonresult) {
		
		this.respuesta = respuesta;
		this.mensaje = mensaje;
		this.jsonresult = jsonresult;
	}
	public RequestJsonPaciente() {		
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getJsonresult() {
		return jsonresult;
	}
	public void setJsonresult(String jsonresult) {
		this.jsonresult = jsonresult;
	}
	
	
}
