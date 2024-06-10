package net.cubosoft.csbypassInterprise.repository;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import net.cubosoft.csbypassInterprise.entities.ListaOrden;
import net.cubosoft.csbypassInterprise.entities.MuestrasxAnaDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenProgressDTO;
import net.cubosoft.csbypassInterprise.entities.PacienteGetOrdenesDTO;
import net.cubosoft.csbypassInterprise.entities.PacientesDTO;
import net.cubosoft.csbypassInterprise.entities.ReferenciasDTO;
import net.cubosoft.csbypassInterprise.entities.RequestJsonPaciente;
import net.cubosoft.csbypassInterprise.entities.ResultadosGrupo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AvalabRepositoryImpl implements AvalabRepository {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public ReferenciasDTO getReferenciabyLogin(String user, String pass){
	    String nativeQuery="SELECT cod_ref, mail_ref, pwd_ref, CONVERT(VARCHAR(60), uid_ref) as 'uid_ref',lock_synlab,id_plan,cod_ori,grupo FROM ava_lisref WHERE cod_ref = ? AND pwd_ref = ? ORDER BY cod_ref DESC";    
	    try {
	        Query q = em.createNativeQuery(nativeQuery);
	        q.setParameter(1,user);
	        q.setParameter(2,pass);
	        List<Object[]> data = q.getResultList();

	        if(data!=null) {
	            List<ReferenciasDTO> referencias = new ArrayList<>();
	            for (Object[] row : data) {
	                Integer grupo = row[7] != null ? ((Byte) row[7]).intValue() : null;
	                Integer row5Value = row[5] != null ? ((BigDecimal) row[5]).intValue() : null;

	                ReferenciasDTO referencia = new ReferenciasDTO(
	                    (String) row[0], 
	                    (String) row[1],
	                    (String) row[2],
	                    (String) row[3],
	                    row[4] != null ? ((BigDecimal) row[4]).intValue() : null,
	                    row5Value,
	                    (String) row[6],
	                    grupo
	                );

	                referencias.add(referencia);
	            }
	            System.err.println("Refencias get: "+referencias.get(0).getCod_ref());

	            return referencias.get(0);                 
	        }else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // Maneja las excepciones adecuadamente
	    } 
	};
	
	@Override
	//@Transactional
	public ReferenciasDTO getReferenciabyUID(String id_ref ){
		//String nativeQuery = "SELECT TOP 500 * FROM \"dba\".\"ava_lispac\" where id_his is null and id_pac is not null";
		   String nativeQuery="SELECT cod_ref, mail_ref, pwd_ref, CONVERT(VARCHAR(60), uid_ref) as 'uid_ref',lock_synlab,id_plan,cod_ori,grupo FROM ava_lisref WHERE cod_ref = ? ORDER BY cod_ref DESC";	
		try {
			Query q = em.createNativeQuery(nativeQuery);
			q.setParameter(1,id_ref);
			 List<Object[]> data = q.getResultList();
			 if(data!=null) {
				  List<ReferenciasDTO> referencias = new ArrayList<>();
			        for (Object[] row : data) {
		                   Integer grupo = row[7] != null ? ((Byte) row[7]).intValue() : null;

			           ReferenciasDTO referencia = new ReferenciasDTO(
			            	(String) row[0], (String) row[1],(String) row[2],(String) row[3],((BigDecimal) row[4]).intValue(),((BigDecimal) row[5]).intValue(),(String) row[6],grupo);			          
			            referencias.add(referencia);
			        }
			        System.err.println("Refencias get: "+referencias.get(0).getCod_ref());

			        return referencias.get(0);			     
			 }else {
				 return null;
			 }
		      
	   
		} catch (Exception e) {
			e.printStackTrace();
			//
			return null; // Maneja las excepciones adecuadamente
		} 
	};
	
	@Override
	//@Transactional
	public ReferenciasDTO checkUID(String uuid){
		//String nativeQuery = "SELECT TOP 500 * FROM \"dba\".\"ava_lispac\" where id_his is null and id_pac is not null";
		  String nativeQuery="SELECT cod_ref, mail_ref, pwd_ref, CONVERT(VARCHAR(60), uid_ref) as 'uid_ref',lock_synlab,id_plan,cod_ori,grupo FROM ava_lisref WHERE uid_ref = ? ORDER BY cod_ref DESC";
		//String nativeQuery="SELECT cod_ref, mail_ref, pwd_ref, CONVERT(VARCHAR(60), uid_ref) as 'uid_ref',lock_synlab FROM ava_lisref WHERE cod_ref = ? AND pwd_ref = ? ORDER BY cod_ref DESC";
		
		try {
			Query q = em.createNativeQuery(nativeQuery);
			q.setParameter(1,uuid);
			
			 List<Object[]> data = q.getResultList();
			 System.out.println("Entra aqui primeramente verificar por favor");
			 if(data!=null) {
				 List<ReferenciasDTO> referencias = new ArrayList<>();
			        for (Object[] row : data) {
		                   Integer grupo = row[7] != null ? ((Byte) row[7]).intValue() : null;

			            ReferenciasDTO referencia = new ReferenciasDTO(
				            	(String) row[0], (String) row[1],(String) row[2],(String) row[3],((BigDecimal) row[4]).intValue(),((BigDecimal) row[5]).intValue(),(String) row[6],grupo);			          

			            		//(String) row[0], (String) row[1],(String) row[2],(String) row[3],((BigDecimal) row[4]).intValue());
			          
			            referencias.add(referencia);
			        }
			        return referencias.get(0);
			    
			 }else {
				 return null;
			 }
		        
	   
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		} 	};
	

		
		
		///Rescate de codigo referenciasss
		
		
		@Override
		//@Transactional
		public List<ReferenciasDTO> getReferenciabyCod(Integer grupos){
		    String nativeQuery="SELECT cod_ref, mail_ref, pwd_ref, CONVERT(VARCHAR(60), uid_ref) as 'uid_ref',lock_synlab,id_plan,cod_ori,grupo FROM ava_lisref WHERE grupo = ? ORDER BY cod_ref DESC";    
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1,grupos);
		        
		        List<Object[]> data = q.getResultList();

		        if (data == null || data.isEmpty()) {
		            return null;
		        }

		        List<ReferenciasDTO> referencias = new ArrayList<>();
		        for (Object[] row : data) {
		            Integer grupo = row[7] != null ? ((Byte) row[7]).intValue() : null;
		            ReferenciasDTO referencia = new ReferenciasDTO(
		                (String) row[0], (String) row[1],(String) row[2],(String) row[3],((BigDecimal) row[4]).intValue(),((BigDecimal) row[5]).intValue(),(String) row[6],grupo);                  
		            referencias.add(referencia);
		        }
		        System.err.println("Refencias get: "+referencias.get(0).getCod_ref());

		        return referencias;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return null; // Maneja las excepciones adecuadamente
		    } 
		};
		
		@Transactional
		public PacientesDTO checkCedula(String cedula) {
		    String nativeQuery = "select * from ava_lispac where id_pac = ? ORDER BY cod_pac DESC";
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, cedula);
		        List<Object[]> data = q.getResultList();

		        if(data != null && !data.isEmpty()) {
		            System.out.println("Entro al if pilas DE PILAS");
		            List<PacientesDTO> pacientes = new ArrayList<>();
		            for (Object[] row : data) {
		                Object value = row[47];
		                System.out.println("Entro al if pilas DE PILAS value: "+row[9]);
		                if(value==null) {
		                	value=row[9];
		                }
		                if (value instanceof BigDecimal) {
		                    PacientesDTO paciente = new PacientesDTO(((BigDecimal) value).toString());
		                    pacientes.add(paciente);
		                } else if (value instanceof String) {
		                    try {
		                        new BigDecimal((String) value);
		                        PacientesDTO paciente = new PacientesDTO((String) value);
		                        pacientes.add(paciente);
		                    } catch (NumberFormatException e) {
		                        // El valor no puede ser convertido a un decimal
		                    }
		                }
		            }
		            return pacientes.get(0);
		        } else {
		            System.out.println("Entro al else pilasss");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	

	@Override
	@Transactional
	public String insertPac(String json_pac) {
		System.out.println("Json Paciente: "+json_pac);
		 String nativeQuery="CALL mob_addpac_synlab(?)";
			try {
				Query q = em.createNativeQuery(nativeQuery);
				q.setParameter(1,json_pac);
				 List<Object[]> data = q.getResultList();
				 if(data!=null) {
					 List<RequestJsonPaciente> requestpacientes = new ArrayList<>();
				        for (Object[] row : data) {
				        	RequestJsonPaciente requestpaciente = new RequestJsonPaciente(
				            		(String) row[0],(String) row[1],(String) row[2]);
				          
				        	requestpacientes.add(requestpaciente);
				        	
				        }
				        ObjectMapper mapper = new ObjectMapper();
				        String jsonResult = mapper.writeValueAsString(requestpacientes.get(0));
				        System.out.println("Pacientesss: " + jsonResult);
				        return jsonResult;
				    
				 }else {
					 return null;
				 }
			        	   
			} catch (Exception e) {
				e.printStackTrace();
				return null; 
			} 
	}
	


	@Override
	//@Transactional
	public Object insertOrden(String json_envio){		
System.out.println("Json ENvion :"+json_envio);
	
			Object verificar = sendJson(json_envio);
			System.out.println("Verificarssssss: "+verificar);
			 if (verificar != null) {
				return verificar;
			    } else {
			        return null;
			    }
}

	@Transactional
	public MuestrasxAnaDTO MuestrasxAna(String cod_ana) {
		String nativeQuery = "select * from mob_analisis_mstrs where cod_ana= ? ORDER BY cod_ana DESC";
		try {
			Query q = em.createNativeQuery(nativeQuery);
			q.setParameter(1, cod_ana);
			List<Object[]> data = q.getResultList();
			System.out.println("datas de Muestas verificar por favro: "+data);

	        if(data != null && !data.isEmpty()) {
				System.out.println("Entro al if pilas");
				List<MuestrasxAnaDTO> muestras = new ArrayList<>();
				for (Object[] row : data) {
					MuestrasxAnaDTO muestra = new MuestrasxAnaDTO(
							(String) row[0],							
							(String) row[7]);
					muestras.add(muestra);
					System.out.println("Muestras aquuda aqui: "+muestras.toString());
				
				}
				return muestras.get(0);
			} else {
				System.err.println("Entro al else pilas");
	            return new MuestrasxAnaDTO("?", "?");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new MuestrasxAnaDTO("?", "?");
		}

	}
	
	
	@Override
	public ReferenciasDTO insert(String UIID) {
		// TODO Auto-generated method stub
		return null;
	};

	
	@Value("${app.URL.insertar_orden}")
	String url;
public Object sendJson(Object jsonEnvio_Oreden_Completa) {
    System.out.println("URL: " + url);
    RestTemplate restTemplate = new RestTemplate();

    // Add the StringHttpMessageConverter to handle text/plain responses
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Object> entity = new HttpEntity<>(jsonEnvio_Oreden_Completa, headers);
    System.out.println("jsonEnvio_Oreden_Completa: " + jsonEnvio_Oreden_Completa);

    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
    return responseEntity.getBody();
}



		public Boolean VerificarOrden(String nro_ord,String cod_ref) {
			System.out.println("Entro por favo aqui");
			//select *  from ava_lisord where nro_ord=1 and cod_ref='3142'
			String nativeQuery = "select * from ava_lisord where nro_ord= ? and cod_ref=? ORDER BY nro_ord DESC";
			try {
				Query q = em.createNativeQuery(nativeQuery);
				q.setParameter(1, nro_ord);
				q.setParameter(2, cod_ref);
				List<Object[]> data = q.getResultList();
				System.out.println("datas orden verifica por favro: "+data);						
		        if(data != null && !data.isEmpty()) {
							
					return true;
				} else {
					System.err.println("Entro al else pilas");
		            return false;

				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
	}
		
		public List<Object[]> VerificarOrden_Resultados(String nro_ord) {
		    System.out.println("Entro por favo aqui");
		    String nativeQuery = "select * from ava_lisord where nro_ord= ? ORDER BY nro_ord DESC";
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, nro_ord);                
		        List<Object[]> data = q.getResultList();
		        System.out.println("datas orden verifica por favro: "+data);                        
		        if(data != null && !data.isEmpty()) {
		            return data;
		        } else {
		            System.err.println("Entro al else pilas");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }       
		}
		
		

		
		public Boolean CheckFechaAntes15Dias(String fechaInicio, String fechaFin) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
		    LocalDate fin = LocalDate.parse(fechaFin, formatter);

		    System.out.println("Inicio: "+inicio);
		    System.out.println("fin: "+fin);
		    
		    LocalDate inicioMas15Dias = inicio.plusDays(15);
		    System.err.println("inicioMas15Dias: " + inicioMas15Dias);
		    System.err.println("fechaFin - Dias: " + fin); 
		    return fin.isAfter(inicio) && fin.isBefore(inicioMas15Dias) || fin.isEqual(inicioMas15Dias);
		}
		
		/*
		public ResultadosGrupo Verificar_grupo(Integer grupo_referencia, String codigo_referencia) {
		    System.out.println("Entro por favo aqui");
		    
		    String nativeQuery = "SELECT * FROM dba.ava_misanalisis_report where grupo_referencia= ? a	+-+-wq
		     
		     +xcd codigo_referencia =? ORDER BY orden DESC";
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, grupo_referencia);            
		        q.setParameter(2, codigo_referencia);            
		        List<Object[]> data = q.getResultList();
		        System.out.println("datas orden codigo de referencia: "+data);                        
		        if(data != null && !data.isEmpty()) {
		            return data;
		        } else {
		            System.err.println("Entro al else pilas");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }       
		}
		*/

		public List<ResultadosGrupo> Verificar_grupo(Integer grupo_referencia, String codigo_referencia) {
		    System.out.println("grupo_referencia:  "+grupo_referencia);
		    System.out.println("codigo_referencia:  "+codigo_referencia);
		    String nativeQuery = "SELECT orden,codigo_analisis,obs_externa_orden,obs_resultado,resultado,fecha_validacion,vdr_resultado,codigo_parametro,sexo_paciente,unidad_resultado FROM dba.ava_misanalisis_report where grupo_referencia= ? and codigo_referencia =?";
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, grupo_referencia);            
		        q.setParameter(2, codigo_referencia);            
		        List<Object[]> data = q.getResultList();
		       if(!data.isEmpty()) {
		            List<ResultadosGrupo> resultadosGrupos = new ArrayList<>();
		            for (Object[] row : data) {        
		                ResultadosGrupo resultadosGrupo = new ResultadosGrupo(
		                 ((BigDecimal) row[0]).intValue(),(String) row[1],(String) row[2],(String) row[3],(String)row[4],((java.sql.Timestamp)row[5]).toString(),(String)row[6],((BigDecimal)row[7]).floatValue(),(String) row[8].toString(),(String) row[9]);                        
		                resultadosGrupos.add(resultadosGrupo);
		            } 
		            return resultadosGrupos;
		       }else {
		           return null;         
		       }

		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }       
		}
		
		public List<PacienteGetOrdenesDTO> getCedulaPaciente(String id_pac) {
		    System.out.println("Id paciente: "+id_pac);
		    String nativeQuery = "SELECT id_pac,cod_pac,nom_pac,ape_pac FROM ava_lispac where id_pac=?";        
		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, id_pac);
		        List<Object[]> data = q.getResultList();

		        if(data != null && !data.isEmpty()) {
		            System.out.println("Entro al if pilas verificar");
		            List<PacienteGetOrdenesDTO> pacientes = new ArrayList<>();
		            for (Object[] row : data) {
		            	PacienteGetOrdenesDTO paciente = new PacienteGetOrdenesDTO(
		            			(String) row[0],((BigDecimal) row[1]).toString(),(String) row[2],(String) row[3]);
		                pacientes.add(paciente);
		                System.out.println("Progresos aqui quedo en  0+++: "+pacientes.get(0));                    
		            }
		            return pacientes;
		        } else {
		            System.err.println("Entro al else pilas");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}
		
		
		public List<ListaOrden> getOrdenByFecha(String fechaInicio,String fechaFin ,String historiaClinica,String getCod_ref) {
		    System.err.println("fechaInicio: "+fechaInicio);
		    System.err.println("fechaFin: "+fechaFin);
		    
		    System.err.println("historiaClinica: "+historiaClinica);
		    System.err.println("getCod_ref: "+getCod_ref);
		    
		    String nativeQuery = "SELECT nro_ord,nro_ext,obs_ext FROM ava_lisord WHERE CONVERT(datetime, fec_ord, 103) BETWEEN CONVERT(datetime, ?, 103) AND CONVERT(datetime, ?, 103) and cod_pac=? and cod_ref=?";
		               
		    List<ListaOrden> ordenes = new ArrayList<>(); // Inicializa la lista aquí

		    try {
		        Query q = em.createNativeQuery(nativeQuery);
		        q.setParameter(1, fechaInicio);
		        q.setParameter(2, fechaFin);
		        q.setParameter(3, historiaClinica);
		        q.setParameter(4, getCod_ref);
		        List<Object[]> data = q.getResultList();

		        if(data != null && !data.isEmpty()) {
		            System.out.println("Entro al if pilas verificar+++");
		            for (Object[] row : data) {
		                String nro_ord = row[0] != null ? ((BigDecimal) row[0]).toString() : null;
		                String nro_ext = row[1] != null ? ((BigDecimal) row[1]).toString() : null;
		                String obs_ext = (String) row[2];
		                ListaOrden orden = new ListaOrden(nro_ord, nro_ext, obs_ext);
		                
		                System.out.println("Orden verifica **: "+orden.getNro_Orden());
		                ordenes.add(orden);
		                
		            }
		        } else {
		            System.err.println("Entro al else pilas en error");
		            return null;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }

		    return ordenes; // Devuelve la lista, que puede estar vacía pero nunca será null
		}
		
		
		
		
		public OrdenProgressDTO check_Res_P_Validación(String nro_ord) {
			String nativeQuery = "SELECT * FROM ava_lisord_progress where nro_ord=?";			
			try {
				Query q = em.createNativeQuery(nativeQuery);
				q.setParameter(1, nro_ord);
				List<Object[]> data = q.getResultList();
				System.out.println("datas de ava_lisord_progress verificar por favro: "+data);

		        if(data != null && !data.isEmpty()) {
					System.out.println("Entro al if pilas verificar");
					List<OrdenProgressDTO> progresos = new ArrayList<>();
					for (Object[] row : data) {
						OrdenProgressDTO progreso = new OrdenProgressDTO(
								 ((BigDecimal)row[0]).toString(),(Integer) row[1],(Integer)row[2],(Integer)row[3],(Integer)row[4],(Integer)row[5],(Integer)row[6]);
						progresos.add(progreso);
						System.out.println("Progresos aqui quedo en  0: "+progresos.get(0));					
					}
					return progresos.get(0);
				} else {
					System.err.println("Entro al else pilas");
		            return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		


		
		@Value("${app.URL.intranet}")
		String url_get_pdf;
		public String getPdfxOrden(String nro_ord) {		
		    try {
		        String url_envio = url_get_pdf + "/intranet/getPdfSigner?nro_ord=" + nro_ord;	
		        System.err.println("url_envio --: "+url_envio);
		        RestTemplate restTemplate = new RestTemplate();
		        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        HttpEntity<String> entity = new HttpEntity<>(nro_ord, headers);
		        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url_envio, entity, byte[].class);
		        JSONObject jsonObject = new JSONObject(new String(responseEntity.getBody()));
		        String base64Value = jsonObject.getString("base64");
		        if(base64Value!="") {
		            return base64Value;
		        } else {
		            return null;
		        }
		    } catch (HttpServerErrorException e) {
		        // Manejar la excepción
		       // e.printStackTrace();
		        return null;
		    }
		}
		

		@Value("${app.URL.intranet}")
		public String url_get_pdf_resultados;
		public OrdenDTO getResultadosxOrden(String nro_ord) {       
		    String url_envio = url_get_pdf_resultados + "/intranet/orden?orden=" + nro_ord;   

		    RestTemplate restTemplate = new RestTemplate();
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<String> entity = new HttpEntity<>(nro_ord, headers);

		    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url_envio, entity, String.class);

		    if (responseEntity.getStatusCodeValue() == 200) {
		        ObjectMapper mapper = new ObjectMapper();
		        try {
		            OrdenDTO orden = mapper.readValue(responseEntity.getBody(), OrdenDTO.class);
		            System.out.println("Orden: " + orden);
		            return orden;
		        } catch (Exception e) {
		            System.err.println("Error al convertir la respuesta a JSON: " + e.getMessage());
		            return null;
		        }
		    } else {
		        System.err.println("Error al obtener los resultados: " + responseEntity.getStatusCode());
		        return null;
		    }
		}
		
		
		
}

