package net.cubosoft.csbypassInterprise.entities;

public class Request {

	private String name;
	private Object param;

	public Request(String name, Object param) {
		this.name = name;
		this.param = param;
	}
	public Request() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}


	
	
}
