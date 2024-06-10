package net.cubosoft.csbypassInterprise.entities;

public class ResultadosGrupo {
	  private Integer orden;
	    private String codigoAnalisis;
	    private String obs_externa_orden;
	    private String txt_res;
	    private String resultado;
	    private String FechaResultado;
	    private String vdr_resultado;
	    private Float codigo_parametro;
	    private String sexo_paciente;
	    private String unidad_resultado;


	    

		public ResultadosGrupo(Integer orden, String codigoAnalisis, String obs_externa_orden, String txt_res,
				String resultado, String fechaResultado, String vdr_resultado, Float codigo_parametro,
				String sexo_paciente, String unidad_resultado) {
			super();
			this.orden = orden;
			this.codigoAnalisis = codigoAnalisis;
			this.obs_externa_orden = obs_externa_orden;
			this.txt_res = txt_res;
			this.resultado = resultado;
			FechaResultado = fechaResultado;
			this.vdr_resultado = vdr_resultado;
			this.codigo_parametro = codigo_parametro;
			this.sexo_paciente = sexo_paciente;
			this.unidad_resultado = unidad_resultado;
		}




		public Integer getOrden() {
			return orden;
		}




		public void setOrden(Integer orden) {
			this.orden = orden;
		}




		public String getCodigoAnalisis() {
			return codigoAnalisis;
		}




		public void setCodigoAnalisis(String codigoAnalisis) {
			this.codigoAnalisis = codigoAnalisis;
		}




		public String getObs_externa_orden() {
			return obs_externa_orden;
		}




		public void setObs_externa_orden(String obs_externa_orden) {
			this.obs_externa_orden = obs_externa_orden;
		}




		public String getTxt_res() {
			return txt_res;
		}




		public void setTxt_res(String txt_res) {
			this.txt_res = txt_res;
		}




		public String getResultado() {
			return resultado;
		}




		public void setResultado(String resultado) {
			this.resultado = resultado;
		}




		public String getFechaResultado() {
			return FechaResultado;
		}




		public void setFechaResultado(String fechaResultado) {
			FechaResultado = fechaResultado;
		}




		public String getVdr_resultado() {
			return vdr_resultado;
		}




		public void setVdr_resultado(String vdr_resultado) {
			this.vdr_resultado = vdr_resultado;
		}




		public Float getCodigo_parametro() {
			return codigo_parametro;
		}




		public void setCodigo_parametro(Float codigo_parametro) {
			this.codigo_parametro = codigo_parametro;
		}




		public String getSexo_paciente() {
			return sexo_paciente;
		}




		public void setSexo_paciente(String sexo_paciente) {
			this.sexo_paciente = sexo_paciente;
		}




		public String getUnidad_resultado() {
			return unidad_resultado;
		}




		public void setUnidad_resultado(String unidad_resultado) {
			this.unidad_resultado = unidad_resultado;
		}




		public ResultadosGrupo() {
		}
	    
	    
	
	    
	    /*
	    private String codigoParametro;
	    private String estadoPeticion;
	    private Integer listoWeb;
	    private Integer listo;
	    private Integer acreditado; //Integer 2
	    private String permisoMsp;
	    private String resultado;
	    private String fechaOrden;
	    private String fecha_creacion_orden;
	    private String fecha_creacion_resultado;
	    private String fecha_modificacion_resultado;
	    private String fecha_entrega;
	    private String fecha_muestra;
	    private String fecha_validacion;
	    private String fecha_firmado;
	    private String tipo_resultado;//
	    private String obs_resultado;
	    private String vdr_resultado;//
	    private String metodo_resultado;//
	    private String descripcion_analisis;//
	    private String descripcion_parametro;//
	    private String abr_parametro ;//Varchar 1
	    private String fecha_nacimiento;
	    private String nombre_medico;
	    private String codigo_tipo_analisis;//
	    private String descripcion_tipo_analisis;
	    private String graph_col;//
	    private String orden_parametro;//
	    private String orden_analisis;//
	    private String orden_tipo_analisis;//
	    private String usuario_modificacion;
	    private String usuario_ceracion;//
	    private String alarma_resultado;// varchar 3
	    private String equipo_resultado;//
	    private String id_resultado;//
	    private String cultivo_analisis;//
	    private String texto_anterior;
	    private String texto_posterior;
	    private String unidad_resultado;//
	    private String estado_orden;
	    private String numero_impresiones;//
	    private String show_analisis;//
	    private String campo2_orden;// Varchar 5
	    private String sexo_paciente;//
	    private String nombre_paciente;
	    private String verificado_resultado;//
	    private String dos_columnas_resultado;//
	    private String codigo2_parametro;//
	    private String edad_paciente;//
	    private Integer codigo_medico;////Ingerger 8
	    private String codigo_referencia;//// Varchar 10
	    private String clave_paciente;// Varchar 6
	    private String urgencia_orden;
	    private String numero_externo_orden;//
	    private String muestra_externa_orden;
	    private String obs_externa_orden;
	    private String web_analisis;
	    private String id_paciente;//
	    private Integer codigo_paciente;//Integer 9
	    private String descripcion_referencia;
	    private String grupo_referencia;//
	    private String descripcion_unidad;
	    private String usuario_validacion;//
	    private String texto_previo_analisis;
	    private String texto_posterior_analisis;
	    private String vdr_analisis;	
//	    private String usuario_validacion_peticion;
	    private Integer bold_resultado;//Ingerger 4
	    private String codigo_analisis;// Varchar 7
	    private Integer codigo_parametro;// Varchar 9
		public ResultadosGrupo(String orden, String codigoAnalisis, String codigoParametro, String estadoPeticion,
				Integer listoWeb, Integer listo, Integer acreditado, String permisoMsp, String resultado,
				String fechaOrden, String fecha_creacion_orden, String fecha_creacion_resultado,
				String fecha_modificacion_resultado, String fecha_entrega, String fecha_muestra,
				String fecha_validacion, String fecha_firmado, String tipo_resultado, String obs_resultado,
				String vdr_resultado, String metodo_resultado, String descripcion_analisis,
				String descripcion_parametro, String abr_parametro, String fecha_nacimiento, String nombre_medico,
				String codigo_tipo_analisis, String descripcion_tipo_analisis, String graph_col, String orden_parametro,
				String orden_analisis, String orden_tipo_analisis, String usuario_modificacion, String usuario_ceracion,
				String alarma_resultado, String equipo_resultado, String id_resultado, String cultivo_analisis,
				String texto_anterior, String texto_posterior, String unidad_resultado, String estado_orden,
				String numero_impresiones, String show_analisis, String campo2_orden, String sexo_paciente,
				String nombre_paciente, String verificado_resultado, String dos_columnas_resultado,
				String codigo2_parametro, String edad_paciente, Integer codigo_medico, String codigo_referencia,
				String clave_paciente, String urgencia_orden, String numero_externo_orden, String muestra_externa_orden,
				String obs_externa_orden, String web_analisis, String id_paciente, Integer codigo_paciente,
				String descripcion_referencia, String grupo_referencia, String descripcion_unidad,
				String usuario_validacion, String texto_previo_analisis, String texto_posterior_analisis,
				String vdr_analisis, Integer bold_resultado, String codigo_analisis, Integer codigo_parametro) {
			super();
			this.orden = orden;
			this.codigoAnalisis = codigoAnalisis;
			this.codigoParametro = codigoParametro;
			this.estadoPeticion = estadoPeticion;
			this.listoWeb = listoWeb;
			this.listo = listo;
			this.acreditado = acreditado;
			this.permisoMsp = permisoMsp;
			this.resultado = resultado;
			this.fechaOrden = fechaOrden;
			this.fecha_creacion_orden = fecha_creacion_orden;
			this.fecha_creacion_resultado = fecha_creacion_resultado;
			this.fecha_modificacion_resultado = fecha_modificacion_resultado;
			this.fecha_entrega = fecha_entrega;
			this.fecha_muestra = fecha_muestra;
			this.fecha_validacion = fecha_validacion;
			this.fecha_firmado = fecha_firmado;
			this.tipo_resultado = tipo_resultado;
			this.obs_resultado = obs_resultado;
			this.vdr_resultado = vdr_resultado;
			this.metodo_resultado = metodo_resultado;
			this.descripcion_analisis = descripcion_analisis;
			this.descripcion_parametro = descripcion_parametro;
			this.abr_parametro = abr_parametro;
			this.fecha_nacimiento = fecha_nacimiento;
			this.nombre_medico = nombre_medico;
			this.codigo_tipo_analisis = codigo_tipo_analisis;
			this.descripcion_tipo_analisis = descripcion_tipo_analisis;
			this.graph_col = graph_col;
			this.orden_parametro = orden_parametro;
			this.orden_analisis = orden_analisis;
			this.orden_tipo_analisis = orden_tipo_analisis;
			this.usuario_modificacion = usuario_modificacion;
			this.usuario_ceracion = usuario_ceracion;
			this.alarma_resultado = alarma_resultado;
			this.equipo_resultado = equipo_resultado;
			this.id_resultado = id_resultado;
			this.cultivo_analisis = cultivo_analisis;
			this.texto_anterior = texto_anterior;
			this.texto_posterior = texto_posterior;
			this.unidad_resultado = unidad_resultado;
			this.estado_orden = estado_orden;
			this.numero_impresiones = numero_impresiones;
			this.show_analisis = show_analisis;
			this.campo2_orden = campo2_orden;
			this.sexo_paciente = sexo_paciente;
			this.nombre_paciente = nombre_paciente;
			this.verificado_resultado = verificado_resultado;
			this.dos_columnas_resultado = dos_columnas_resultado;
			this.codigo2_parametro = codigo2_parametro;
			this.edad_paciente = edad_paciente;
			this.codigo_medico = codigo_medico;
			this.codigo_referencia = codigo_referencia;
			this.clave_paciente = clave_paciente;
			this.urgencia_orden = urgencia_orden;
			this.numero_externo_orden = numero_externo_orden;
			this.muestra_externa_orden = muestra_externa_orden;
			this.obs_externa_orden = obs_externa_orden;
			this.web_analisis = web_analisis;
			this.id_paciente = id_paciente;
			this.codigo_paciente = codigo_paciente;
			this.descripcion_referencia = descripcion_referencia;
			this.grupo_referencia = grupo_referencia;
			this.descripcion_unidad = descripcion_unidad;
			this.usuario_validacion = usuario_validacion;
			this.texto_previo_analisis = texto_previo_analisis;
			this.texto_posterior_analisis = texto_posterior_analisis;
			this.vdr_analisis = vdr_analisis;
			this.bold_resultado = bold_resultado;
			this.codigo_analisis = codigo_analisis;
			this.codigo_parametro = codigo_parametro;
		}
		
		
		
		public ResultadosGrupo() {
			super();
		}



		public String getOrden() {
			return orden;
		}
		public void setOrden(String orden) {
			this.orden = orden;
		}
		public String getCodigoAnalisis() {
			return codigoAnalisis;
		}
		public void setCodigoAnalisis(String codigoAnalisis) {
			this.codigoAnalisis = codigoAnalisis;
		}
		public String getCodigoParametro() {
			return codigoParametro;
		}
		public void setCodigoParametro(String codigoParametro) {
			this.codigoParametro = codigoParametro;
		}
		public String getEstadoPeticion() {
			return estadoPeticion;
		}
		public void setEstadoPeticion(String estadoPeticion) {
			this.estadoPeticion = estadoPeticion;
		}
		public Integer getListoWeb() {
			return listoWeb;
		}
		public void setListoWeb(Integer listoWeb) {
			this.listoWeb = listoWeb;
		}
		public Integer getListo() {
			return listo;
		}
		public void setListo(Integer listo) {
			this.listo = listo;
		}
		public Integer getAcreditado() {
			return acreditado;
		}
		public void setAcreditado(Integer acreditado) {
			this.acreditado = acreditado;
		}
		public String getPermisoMsp() {
			return permisoMsp;
		}
		public void setPermisoMsp(String permisoMsp) {
			this.permisoMsp = permisoMsp;
		}
		public String getResultado() {
			return resultado;
		}
		public void setResultado(String resultado) {
			this.resultado = resultado;
		}
		public String getFechaOrden() {
			return fechaOrden;
		}
		public void setFechaOrden(String fechaOrden) {
			this.fechaOrden = fechaOrden;
		}
		public String getFecha_creacion_orden() {
			return fecha_creacion_orden;
		}
		public void setFecha_creacion_orden(String fecha_creacion_orden) {
			this.fecha_creacion_orden = fecha_creacion_orden;
		}
		public String getFecha_creacion_resultado() {
			return fecha_creacion_resultado;
		}
		public void setFecha_creacion_resultado(String fecha_creacion_resultado) {
			this.fecha_creacion_resultado = fecha_creacion_resultado;
		}
		public String getFecha_modificacion_resultado() {
			return fecha_modificacion_resultado;
		}
		public void setFecha_modificacion_resultado(String fecha_modificacion_resultado) {
			this.fecha_modificacion_resultado = fecha_modificacion_resultado;
		}
		public String getFecha_entrega() {
			return fecha_entrega;
		}
		public void setFecha_entrega(String fecha_entrega) {
			this.fecha_entrega = fecha_entrega;
		}
		public String getFecha_muestra() {
			return fecha_muestra;
		}
		public void setFecha_muestra(String fecha_muestra) {
			this.fecha_muestra = fecha_muestra;
		}
		public String getFecha_validacion() {
			return fecha_validacion;
		}
		public void setFecha_validacion(String fecha_validacion) {
			this.fecha_validacion = fecha_validacion;
		}
		public String getFecha_firmado() {
			return fecha_firmado;
		}
		public void setFecha_firmado(String fecha_firmado) {
			this.fecha_firmado = fecha_firmado;
		}
		public String getTipo_resultado() {
			return tipo_resultado;
		}
		public void setTipo_resultado(String tipo_resultado) {
			this.tipo_resultado = tipo_resultado;
		}
		public String getObs_resultado() {
			return obs_resultado;
		}
		public void setObs_resultado(String obs_resultado) {
			this.obs_resultado = obs_resultado;
		}
		public String getVdr_resultado() {
			return vdr_resultado;
		}
		public void setVdr_resultado(String vdr_resultado) {
			this.vdr_resultado = vdr_resultado;
		}
		public String getMetodo_resultado() {
			return metodo_resultado;
		}
		public void setMetodo_resultado(String metodo_resultado) {
			this.metodo_resultado = metodo_resultado;
		}
		public String getDescripcion_analisis() {
			return descripcion_analisis;
		}
		public void setDescripcion_analisis(String descripcion_analisis) {
			this.descripcion_analisis = descripcion_analisis;
		}
		public String getDescripcion_parametro() {
			return descripcion_parametro;
		}
		public void setDescripcion_parametro(String descripcion_parametro) {
			this.descripcion_parametro = descripcion_parametro;
		}
		public String getAbr_parametro() {
			return abr_parametro;
		}
		public void setAbr_parametro(String abr_parametro) {
			this.abr_parametro = abr_parametro;
		}
		public String getFecha_nacimiento() {
			return fecha_nacimiento;
		}
		public void setFecha_nacimiento(String fecha_nacimiento) {
			this.fecha_nacimiento = fecha_nacimiento;
		}
		public String getNombre_medico() {
			return nombre_medico;
		}
		public void setNombre_medico(String nombre_medico) {
			this.nombre_medico = nombre_medico;
		}
		public String getCodigo_tipo_analisis() {
			return codigo_tipo_analisis;
		}
		public void setCodigo_tipo_analisis(String codigo_tipo_analisis) {
			this.codigo_tipo_analisis = codigo_tipo_analisis;
		}
		public String getDescripcion_tipo_analisis() {
			return descripcion_tipo_analisis;
		}
		public void setDescripcion_tipo_analisis(String descripcion_tipo_analisis) {
			this.descripcion_tipo_analisis = descripcion_tipo_analisis;
		}
		public String getGraph_col() {
			return graph_col;
		}
		public void setGraph_col(String graph_col) {
			this.graph_col = graph_col;
		}
		public String getOrden_parametro() {
			return orden_parametro;
		}
		public void setOrden_parametro(String orden_parametro) {
			this.orden_parametro = orden_parametro;
		}
		public String getOrden_analisis() {
			return orden_analisis;
		}
		public void setOrden_analisis(String orden_analisis) {
			this.orden_analisis = orden_analisis;
		}
		public String getOrden_tipo_analisis() {
			return orden_tipo_analisis;
		}
		public void setOrden_tipo_analisis(String orden_tipo_analisis) {
			this.orden_tipo_analisis = orden_tipo_analisis;
		}
		public String getUsuario_modificacion() {
			return usuario_modificacion;
		}
		public void setUsuario_modificacion(String usuario_modificacion) {
			this.usuario_modificacion = usuario_modificacion;
		}
		public String getUsuario_ceracion() {
			return usuario_ceracion;
		}
		public void setUsuario_ceracion(String usuario_ceracion) {
			this.usuario_ceracion = usuario_ceracion;
		}
		public String getAlarma_resultado() {
			return alarma_resultado;
		}
		public void setAlarma_resultado(String alarma_resultado) {
			this.alarma_resultado = alarma_resultado;
		}
		public String getEquipo_resultado() {
			return equipo_resultado;
		}
		public void setEquipo_resultado(String equipo_resultado) {
			this.equipo_resultado = equipo_resultado;
		}
		public String getId_resultado() {
			return id_resultado;
		}
		public void setId_resultado(String id_resultado) {
			this.id_resultado = id_resultado;
		}
		public String getCultivo_analisis() {
			return cultivo_analisis;
		}
		public void setCultivo_analisis(String cultivo_analisis) {
			this.cultivo_analisis = cultivo_analisis;
		}
		public String getTexto_anterior() {
			return texto_anterior;
		}
		public void setTexto_anterior(String texto_anterior) {
			this.texto_anterior = texto_anterior;
		}
		public String getTexto_posterior() {
			return texto_posterior;
		}
		public void setTexto_posterior(String texto_posterior) {
			this.texto_posterior = texto_posterior;
		}
		public String getUnidad_resultado() {
			return unidad_resultado;
		}
		public void setUnidad_resultado(String unidad_resultado) {
			this.unidad_resultado = unidad_resultado;
		}
		public String getEstado_orden() {
			return estado_orden;
		}
		public void setEstado_orden(String estado_orden) {
			this.estado_orden = estado_orden;
		}
		public String getNumero_impresiones() {
			return numero_impresiones;
		}
		public void setNumero_impresiones(String numero_impresiones) {
			this.numero_impresiones = numero_impresiones;
		}
		public String getShow_analisis() {
			return show_analisis;
		}
		public void setShow_analisis(String show_analisis) {
			this.show_analisis = show_analisis;
		}
		public String getCampo2_orden() {
			return campo2_orden;
		}
		public void setCampo2_orden(String campo2_orden) {
			this.campo2_orden = campo2_orden;
		}
		public String getSexo_paciente() {
			return sexo_paciente;
		}
		public void setSexo_paciente(String sexo_paciente) {
			this.sexo_paciente = sexo_paciente;
		}
		public String getNombre_paciente() {
			return nombre_paciente;
		}
		public void setNombre_paciente(String nombre_paciente) {
			this.nombre_paciente = nombre_paciente;
		}
		public String getVerificado_resultado() {
			return verificado_resultado;
		}
		public void setVerificado_resultado(String verificado_resultado) {
			this.verificado_resultado = verificado_resultado;
		}
		public String getDos_columnas_resultado() {
			return dos_columnas_resultado;
		}
		public void setDos_columnas_resultado(String dos_columnas_resultado) {
			this.dos_columnas_resultado = dos_columnas_resultado;
		}
		public String getCodigo2_parametro() {
			return codigo2_parametro;
		}
		public void setCodigo2_parametro(String codigo2_parametro) {
			this.codigo2_parametro = codigo2_parametro;
		}
		public String getEdad_paciente() {
			return edad_paciente;
		}
		public void setEdad_paciente(String edad_paciente) {
			this.edad_paciente = edad_paciente;
		}
		public Integer getCodigo_medico() {
			return codigo_medico;
		}
		public void setCodigo_medico(Integer codigo_medico) {
			this.codigo_medico = codigo_medico;
		}
		public String getCodigo_referencia() {
			return codigo_referencia;
		}
		public void setCodigo_referencia(String codigo_referencia) {
			this.codigo_referencia = codigo_referencia;
		}
		public String getClave_paciente() {
			return clave_paciente;
		}
		public void setClave_paciente(String clave_paciente) {
			this.clave_paciente = clave_paciente;
		}
		public String getUrgencia_orden() {
			return urgencia_orden;
		}
		public void setUrgencia_orden(String urgencia_orden) {
			this.urgencia_orden = urgencia_orden;
		}
		public String getNumero_externo_orden() {
			return numero_externo_orden;
		}
		public void setNumero_externo_orden(String numero_externo_orden) {
			this.numero_externo_orden = numero_externo_orden;
		}
		public String getMuestra_externa_orden() {
			return muestra_externa_orden;
		}
		public void setMuestra_externa_orden(String muestra_externa_orden) {
			this.muestra_externa_orden = muestra_externa_orden;
		}
		public String getObs_externa_orden() {
			return obs_externa_orden;
		}
		public void setObs_externa_orden(String obs_externa_orden) {
			this.obs_externa_orden = obs_externa_orden;
		}
		public String getWeb_analisis() {
			return web_analisis;
		}
		public void setWeb_analisis(String web_analisis) {
			this.web_analisis = web_analisis;
		}
		public String getId_paciente() {
			return id_paciente;
		}
		public void setId_paciente(String id_paciente) {
			this.id_paciente = id_paciente;
		}
		public Integer getCodigo_paciente() {
			return codigo_paciente;
		}
		public void setCodigo_paciente(Integer codigo_paciente) {
			this.codigo_paciente = codigo_paciente;
		}
		public String getDescripcion_referencia() {
			return descripcion_referencia;
		}
		public void setDescripcion_referencia(String descripcion_referencia) {
			this.descripcion_referencia = descripcion_referencia;
		}
		public String getGrupo_referencia() {
			return grupo_referencia;
		}
		public void setGrupo_referencia(String grupo_referencia) {
			this.grupo_referencia = grupo_referencia;
		}
		public String getDescripcion_unidad() {
			return descripcion_unidad;
		}
		public void setDescripcion_unidad(String descripcion_unidad) {
			this.descripcion_unidad = descripcion_unidad;
		}
		public String getUsuario_validacion() {
			return usuario_validacion;
		}
		public void setUsuario_validacion(String usuario_validacion) {
			this.usuario_validacion = usuario_validacion;
		}
		public String getTexto_previo_analisis() {
			return texto_previo_analisis;
		}
		public void setTexto_previo_analisis(String texto_previo_analisis) {
			this.texto_previo_analisis = texto_previo_analisis;
		}
		public String getTexto_posterior_analisis() {
			return texto_posterior_analisis;
		}
		public void setTexto_posterior_analisis(String texto_posterior_analisis) {
			this.texto_posterior_analisis = texto_posterior_analisis;
		}
		public String getVdr_analisis() {
			return vdr_analisis;
		}
		public void setVdr_analisis(String vdr_analisis) {
			this.vdr_analisis = vdr_analisis;
		}
		public Integer getBold_resultado() {
			return bold_resultado;
		}
		public void setBold_resultado(Integer bold_resultado) {
			this.bold_resultado = bold_resultado;
		}
		public String getCodigo_analisis() {
			return codigo_analisis;
		}
		public void setCodigo_analisis(String codigo_analisis) {
			this.codigo_analisis = codigo_analisis;
		}
		public Integer getCodigo_parametro() {
			return codigo_parametro;
		}
		public void setCodigo_parametro(Integer codigo_parametro) {
			this.codigo_parametro = codigo_parametro;
		}
	     	*/
	    
	   
}

  
 