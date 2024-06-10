package net.cubosoft.csbypassInterprise.entities;

public class ReferenciasDTO {

	private String cod_ref;
	private String mail_ref;
	private String pwd_ref;
	private String id_ref;
	private Integer lock_synlab;
	private Integer id_plan;
	private String cod_ori;
	private Integer grupo;




	public ReferenciasDTO(String cod_ref, String mail_ref, String pwd_ref, String id_ref, Integer lock_synlab,
			Integer id_plan, String cod_ori, Integer grupo) {
		super();
		this.cod_ref = cod_ref;
		this.mail_ref = mail_ref;
		this.pwd_ref = pwd_ref;
		this.id_ref = id_ref;
		this.lock_synlab = lock_synlab;
		this.id_plan = id_plan;
		this.cod_ori = cod_ori;
		this.grupo = grupo;
	}


	public ReferenciasDTO() {
	}
	public String getCod_ref() {
		return cod_ref;
	}


	public void setCod_ref(String cod_ref) {
		this.cod_ref = cod_ref;
	}


	public String getMail_ref() {
		return mail_ref;
	}


	public void setMail_ref(String mail_ref) {
		this.mail_ref = mail_ref;
	}


	public String getPwd_ref() {
		return pwd_ref;
	}


	public void setPwd_ref(String pwd_ref) {
		this.pwd_ref = pwd_ref;
	}


	public String getId_ref() {
		return id_ref;
	}


	public void setId_ref(String id_ref) {
		this.id_ref = id_ref;
	}


	public Integer getLock_synlab() {
		return lock_synlab;
	}


	public void setLock_synlab(Integer lock_synlab) {
		this.lock_synlab = lock_synlab;
	}


	public Integer getId_plan() {
		return id_plan;
	}


	public void setId_plan(Integer id_plan) {
		this.id_plan = id_plan;
	}


	public String getCod_ori() {
		return cod_ori;
	}


	public void setCod_ori(String cod_ori) {
		this.cod_ori = cod_ori;
	}


	public Integer getGrupo() {
		return grupo;
	}


	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}



}
