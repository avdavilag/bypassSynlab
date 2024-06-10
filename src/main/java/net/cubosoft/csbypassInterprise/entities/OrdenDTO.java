package net.cubosoft.csbypassInterprise.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OrdenDTO {
	 @JsonProperty("id_orden")
	    private int idOrden;

	    @JsonProperty("listo")
	    private int listo;

	

	    public List<TipoAnalisis> getTipoAnalisis() {
	        return this.tipoAnalisis;
	    }
	    @JsonProperty("fecha_orden")
	    private String fechaOrden;

	    @JsonProperty("fecha_creacion_orden")
	    private String fechaCreacionOrden;

	    @JsonProperty("estado_orden")
	    private int estadoOrden;

	    @JsonProperty("campo2_orden")
	    private String campo2Orden;

	    @JsonProperty("urgencia_orden")
	    private int urgenciaOrden;

	    @JsonProperty("numero_externo_orden")
	    private String numeroExternoOrden;

	    @JsonProperty("muestra_externa_orden")
	    private String muestraExternaOrden;

	    @JsonProperty("obs_externa_orden")
	    private String obsExternaOrden;

	    @JsonProperty("numero_impresiones")
	    private String numeroImpresiones;
	    
	    
	    public String getPermisoMsp() {
			return permisoMsp;
		}

		public void setPermisoMsp(String permisoMsp) {
			this.permisoMsp = permisoMsp;
		}

		@JsonProperty("permiso_msp")
	    private String permisoMsp;

	    
	    
	public int getIdOrden() {
			return idOrden;
		}

		public void setIdOrden(int idOrden) {
			this.idOrden = idOrden;
		}

		public int getListo() {
			return listo;
		}

		public void setListo(int listo) {
			this.listo = listo;
		}

		public String getFechaOrden() {
			return fechaOrden;
		}

		public void setFechaOrden(String fechaOrden) {
			this.fechaOrden = fechaOrden;
		}

		public String getFechaCreacionOrden() {
			return fechaCreacionOrden;
		}

		public void setFechaCreacionOrden(String fechaCreacionOrden) {
			this.fechaCreacionOrden = fechaCreacionOrden;
		}

		public int getEstadoOrden() {
			return estadoOrden;
		}

		public void setEstadoOrden(int estadoOrden) {
			this.estadoOrden = estadoOrden;
		}

		public String getCampo2Orden() {
			return campo2Orden;
		}

		public void setCampo2Orden(String campo2Orden) {
			this.campo2Orden = campo2Orden;
		}

		public int getUrgenciaOrden() {
			return urgenciaOrden;
		}

		public void setUrgenciaOrden(int urgenciaOrden) {
			this.urgenciaOrden = urgenciaOrden;
		}

		public String getNumeroExternoOrden() {
			return numeroExternoOrden;
		}

		public void setNumeroExternoOrden(String numeroExternoOrden) {
			this.numeroExternoOrden = numeroExternoOrden;
		}

		public String getMuestraExternaOrden() {
			return muestraExternaOrden;
		}

		public void setMuestraExternaOrden(String muestraExternaOrden) {
			this.muestraExternaOrden = muestraExternaOrden;
		}

		public String getObsExternaOrden() {
			return obsExternaOrden;
		}

		public void setObsExternaOrden(String obsExternaOrden) {
			this.obsExternaOrden = obsExternaOrden;
		}

		public String getNumeroImpresiones() {
			return numeroImpresiones;
		}

		public void setNumeroImpresiones(String numeroImpresiones) {
			this.numeroImpresiones = numeroImpresiones;
		}

	    @JsonProperty("medico")
	    private Medico medico;
	public static class Medico {
		
	    @JsonProperty("nombre_medico")
	    private String nombreMedico;

	    @JsonProperty("fecha_nacimiento")
	    private String fechaNacimiento;

	    @JsonProperty("codigo_medico")
	    private int codigoMedico;

		public String getNombreMedico() {
			return nombreMedico;
		}

		public void setNombreMedico(String nombreMedico) {
			this.nombreMedico = nombreMedico;
		}

		public String getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}

		public int getCodigoMedico() {
			return codigoMedico;
		}

		public void setCodigoMedico(int codigoMedico) {
			this.codigoMedico = codigoMedico;
		}

	    
    }
	
    public Paciente getPaciente() {
        return this.paciente;
    }

    @JsonProperty("paciente")
    private Paciente paciente;
    
    
    public static class Paciente {

        @JsonProperty("sexo_paciente")
        private String sexoPaciente;

        @JsonProperty("nombre_paciente")
        private String nombrePaciente;

        @JsonProperty("edad_paciente")
        private String edadPaciente;

        @JsonProperty("clave_paciente")
        private String clavePaciente;

        @JsonProperty("id_paciente")
        private String idPaciente;

        @JsonProperty("codigo_paciente")
        private String codigoPaciente;

		public String getSexoPaciente() {
			return sexoPaciente;
		}

		public void setSexoPaciente(String sexoPaciente) {
			this.sexoPaciente = sexoPaciente;
		}

		public String getNombrePaciente() {
			return nombrePaciente;
		}

		public void setNombrePaciente(String nombrePaciente) {
			this.nombrePaciente = nombrePaciente;
		}

		public String getEdadPaciente() {
			return edadPaciente;
		}

		public void setEdadPaciente(String edadPaciente) {
			this.edadPaciente = edadPaciente;
		}

		public String getClavePaciente() {
			return clavePaciente;
		}

		public void setClavePaciente(String clavePaciente) {
			this.clavePaciente = clavePaciente;
		}

		public String getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(String idPaciente) {
			this.idPaciente = idPaciente;
		}

		public String getCodigoPaciente() {
			return codigoPaciente;
		}

		public void setCodigoPaciente(String codigoPaciente) {
			this.codigoPaciente = codigoPaciente;
		}
        
    }
    
    @JsonProperty("tipo_analisis")
    private List<TipoAnalisis> tipoAnalisis;
    public static class TipoAnalisis {
    	
        public List<Analisis> getAnalisis() {
	        return this.analisis;
	    }

    	  @JsonProperty("codigo_tipo_analisis")
    	    private String codigoTipoAnalisis;

    	    @JsonProperty("descripcion_tipo_analisis")
    	    private String descripcionTipoAnalisis;

    	    @JsonProperty("orden_tipo_analisis")
    	    private String ordenTipoAnalisis;

    	    @JsonProperty("web_analisis")
    	    private int webAnalisis;


        public String getCodigoTipoAnalisis() {
				return codigoTipoAnalisis;
			}


			public void setCodigoTipoAnalisis(String codigoTipoAnalisis) {
				this.codigoTipoAnalisis = codigoTipoAnalisis;
			}


			public String getDescripcionTipoAnalisis() {
				return descripcionTipoAnalisis;
			}


			public void setDescripcionTipoAnalisis(String descripcionTipoAnalisis) {
				this.descripcionTipoAnalisis = descripcionTipoAnalisis;
			}


			public String getOrdenTipoAnalisis() {
				return ordenTipoAnalisis;
			}


			public void setOrdenTipoAnalisis(String ordenTipoAnalisis) {
				this.ordenTipoAnalisis = ordenTipoAnalisis;
			}


			public int getWebAnalisis() {
				return webAnalisis;
			}


			public void setWebAnalisis(int webAnalisis) {
				this.webAnalisis = webAnalisis;
			}


			  @JsonProperty("analisis")
			  private List<Analisis> analisis;
		public static class Analisis {
		
		    

			  @JsonProperty("peticion")
			  private Peticion peticion;
		public static class Peticion {
			  @JsonProperty("estado_peticion")
			    private String estadoPeticion;

			    @JsonProperty("usuario_validacion_peticion")
			    private String usuarioValidacionPeticion;

			    @JsonProperty("usuario_validacion")
			    private String usuarioValidacion;
			    
				}
		
			  @JsonProperty("codigo_analisis")
			    private String codigoAnalisis;

			    @JsonProperty("descripcion_analisis")
			    private String descripcionAnalisis;

			    @JsonProperty("orden_analisis")
			    private String ordenAnalisis;

			    @JsonProperty("texto_previo_analisis")
			    private String textoPrevioAnalisis;

			    @JsonProperty("texto_posterior_analisis")
			    private String textoPosteriorAnalisis;

			    @JsonProperty("vdr_analisis")
			    private String vdrAnalisis;

			    @JsonProperty("show_analisis")
			    private int showAnalisis;

			    @JsonProperty("cultivo_analisis")
			    private int cultivoAnalisis;

			    @JsonProperty("fecha_entrega")
			    private String fechaEntrega;

			    @JsonProperty("fecha_muestra")
			    private String fechaMuestra;

			    @JsonProperty("fecha_validacion")
			    private String fechaValidacion;

			    @JsonProperty("fecha_firmado")
			    private String fechaFirmado;
			    

            public String getCodigoAnalisis() {
					return codigoAnalisis;
				}


				public void setCodigoAnalisis(String codigoAnalisis) {
					this.codigoAnalisis = codigoAnalisis;
				}


				public String getDescripcionAnalisis() {
					return descripcionAnalisis;
				}


				public void setDescripcionAnalisis(String descripcionAnalisis) {
					this.descripcionAnalisis = descripcionAnalisis;
				}


				public String getOrdenAnalisis() {
					return ordenAnalisis;
				}


				public void setOrdenAnalisis(String ordenAnalisis) {
					this.ordenAnalisis = ordenAnalisis;
				}


				public String getTextoPrevioAnalisis() {
					return textoPrevioAnalisis;
				}


				public void setTextoPrevioAnalisis(String textoPrevioAnalisis) {
					this.textoPrevioAnalisis = textoPrevioAnalisis;
				}


				public String getTextoPosteriorAnalisis() {
					return textoPosteriorAnalisis;
				}


				public void setTextoPosteriorAnalisis(String textoPosteriorAnalisis) {
					this.textoPosteriorAnalisis = textoPosteriorAnalisis;
				}


				public String getVdrAnalisis() {
					return vdrAnalisis;
				}


				public void setVdrAnalisis(String vdrAnalisis) {
					this.vdrAnalisis = vdrAnalisis;
				}


				public int getShowAnalisis() {
					return showAnalisis;
				}


				public void setShowAnalisis(int showAnalisis) {
					this.showAnalisis = showAnalisis;
				}


				public int getCultivoAnalisis() {
					return cultivoAnalisis;
				}


				public void setCultivoAnalisis(int cultivoAnalisis) {
					this.cultivoAnalisis = cultivoAnalisis;
				}


				public String getFechaEntrega() {
					return fechaEntrega;
				}


				public void setFechaEntrega(String fechaEntrega) {
					this.fechaEntrega = fechaEntrega;
				}


				public String getFechaMuestra() {
					return fechaMuestra;
				}


				public void setFechaMuestra(String fechaMuestra) {
					this.fechaMuestra = fechaMuestra;
				}


				public String getFechaValidacion() {
					return fechaValidacion;
				}


				public void setFechaValidacion(String fechaValidacion) {
					this.fechaValidacion = fechaValidacion;
				}


				public String getFechaFirmado() {
					return fechaFirmado;
				}


				public void setFechaFirmado(String fechaFirmado) {
					this.fechaFirmado = fechaFirmado;
				}

				  @JsonProperty("parametros")
				  private List<Parametros> parametros;
					 public List<Parametros> getParametros() {
					        return this.parametros;
					    }
			public static class Parametros {
			
				  @JsonProperty("codigo_parametro")
				    private int codigoParametro;

				    @JsonProperty("descripcion_parametro")
				    private String descripcionParametro;

				    @JsonProperty("abr_parametro")
				    private String abrParametro;

				    @JsonProperty("orden_parametro")
				    private int ordenParametro;

				    @JsonProperty("codigo2_parametro")
				    private String codigo2Parametro;

				    @JsonProperty("usuario_modificacion")
				    private String usuarioModificacion;

				    @JsonProperty("usuario_ceracion")
				    private String usuarioCreacion;

				    @JsonProperty("texto_anterior")
				    private String textoAnterior;

				    @JsonProperty("texto_posterior")
				    private String textoPosterior;


                public int getCodigoParametro() {
						return codigoParametro;
					}


					public void setCodigoParametro(int codigoParametro) {
						this.codigoParametro = codigoParametro;
					}


					public String getDescripcionParametro() {
						return descripcionParametro;
					}


					public void setDescripcionParametro(String descripcionParametro) {
						this.descripcionParametro = descripcionParametro;
					}


					public String getAbrParametro() {
						return abrParametro;
					}


					public void setAbrParametro(String abrParametro) {
						this.abrParametro = abrParametro;
					}


					public int getOrdenParametro() {
						return ordenParametro;
					}


					public void setOrdenParametro(int ordenParametro) {
						this.ordenParametro = ordenParametro;
					}


					public String getCodigo2Parametro() {
						return codigo2Parametro;
					}


					public void setCodigo2Parametro(String codigo2Parametro) {
						this.codigo2Parametro = codigo2Parametro;
					}


					public String getUsuarioModificacion() {
						return usuarioModificacion;
					}


					public void setUsuarioModificacion(String usuarioModificacion) {
						this.usuarioModificacion = usuarioModificacion;
					}


					public String getUsuarioCreacion() {
						return usuarioCreacion;
					}


					public void setUsuarioCreacion(String usuarioCreacion) {
						this.usuarioCreacion = usuarioCreacion;
					}


					public String getTextoAnterior() {
						return textoAnterior;
					}


					public void setTextoAnterior(String textoAnterior) {
						this.textoAnterior = textoAnterior;
					}


					public String getTextoPosterior() {
						return textoPosterior;
					}


					public void setTextoPosterior(String textoPosterior) {
						this.textoPosterior = textoPosterior;
					}

				
					  public Resultado getResultado() {
						    return this.resultado;
						}

					  @JsonProperty("resultado")
					    private Resultado resultado;
					  public static class Resultado {
						
						  
				    @JsonProperty("tipo_resultado")
				    private String tipoResultado;

				    @JsonProperty("obs_resultado")
				    private String obsResultado;

				    @JsonProperty("vdr_resultado")
				    private String vdrResultado;

				    @JsonProperty("metodo_resultado")
				    private String metodoResultado;

				    @JsonProperty("alarma_resultado")
				    private String alarmaResultado;

				    @JsonProperty("equipo_resultado")
				    private String equipoResultado;

				    @JsonProperty("resultado")
				    private String resultado;

				    @JsonProperty("id_resultado")
				    private int idResultado;

				    @JsonProperty("unidad_resultado")
				    private String unidadResultado;

				    @JsonProperty("descripcion_unidad")
				    private String descripcionUnidad;

				    @JsonProperty("bold_resultado")
				    private String boldResultado;

				    @JsonProperty("fecha_creacion_resultado")
				    private String fechaCreacionResultado;

				    @JsonProperty("verificado_resultado")
				    private int verificadoResultado;

				    @JsonProperty("dos_columnas_resultado")
				    private int dosColumnasResultado;

				    @JsonProperty("fecha_modificacion_resultado")
				    private String fechaModificacionResultado;

					public String getTipoResultado() {
						return tipoResultado;
					}

					public void setTipoResultado(String tipoResultado) {
						this.tipoResultado = tipoResultado;
					}

					public String getObsResultado() {
						return obsResultado;
					}

					public void setObsResultado(String obsResultado) {
						this.obsResultado = obsResultado;
					}

					public String getVdrResultado() {
						return vdrResultado;
					}

					public void setVdrResultado(String vdrResultado) {
						this.vdrResultado = vdrResultado;
					}

					public String getMetodoResultado() {
						return metodoResultado;
					}

					public void setMetodoResultado(String metodoResultado) {
						this.metodoResultado = metodoResultado;
					}

					public String getAlarmaResultado() {
						return alarmaResultado;
					}

					public void setAlarmaResultado(String alarmaResultado) {
						this.alarmaResultado = alarmaResultado;
					}

					public String getEquipoResultado() {
						return equipoResultado;
					}

					public void setEquipoResultado(String equipoResultado) {
						this.equipoResultado = equipoResultado;
					}

					public String getResultado() {
						return resultado;
					}

					public void setResultado(String resultado) {
						this.resultado = resultado;
					}

					public int getIdResultado() {
						return idResultado;
					}

					public void setIdResultado(int idResultado) {
						this.idResultado = idResultado;
					}

					public String getUnidadResultado() {
						return unidadResultado;
					}

					public void setUnidadResultado(String unidadResultado) {
						this.unidadResultado = unidadResultado;
					}

					public String getDescripcionUnidad() {
						return descripcionUnidad;
					}

					public void setDescripcionUnidad(String descripcionUnidad) {
						this.descripcionUnidad = descripcionUnidad;
					}

					public String getBoldResultado() {
						return boldResultado;
					}

					public void setBoldResultado(String boldResultado) {
						this.boldResultado = boldResultado;
					}

					public String getFechaCreacionResultado() {
						return fechaCreacionResultado;
					}

					public void setFechaCreacionResultado(String fechaCreacionResultado) {
						this.fechaCreacionResultado = fechaCreacionResultado;
					}

					public int getVerificadoResultado() {
						return verificadoResultado;
					}

					public void setVerificadoResultado(int verificadoResultado) {
						this.verificadoResultado = verificadoResultado;
					}

					public int getDosColumnasResultado() {
						return dosColumnasResultado;
					}

					public void setDosColumnasResultado(int dosColumnasResultado) {
						this.dosColumnasResultado = dosColumnasResultado;
					}

					public String getFechaModificacionResultado() {
						return fechaModificacionResultado;
					}

					public void setFechaModificacionResultado(String fechaModificacionResultado) {
						this.fechaModificacionResultado = fechaModificacionResultado;
					}

                }
            }
        }
    }
	  @JsonProperty("referencia")
	    private Referencia referencia;
    public static class Referencia {

        @JsonProperty("codigo_referencia")
        private String codigoReferencia;

        @JsonProperty("descripcion_referencia")
        private String descripcionReferencia;
        
    }
}
