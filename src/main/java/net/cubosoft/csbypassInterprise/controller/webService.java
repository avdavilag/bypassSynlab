package net.cubosoft.csbypassInterprise.controller;

import java.security.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.bytebuddy.asm.Advice.Return;
import net.cubosoft.csbypassInterprise.entities.ListaOrden;
import net.cubosoft.csbypassInterprise.entities.MuestrasxAnaDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO.Referencia;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO.TipoAnalisis;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO.TipoAnalisis.Analisis;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO.TipoAnalisis.Analisis.Parametros;
import net.cubosoft.csbypassInterprise.entities.OrdenProgressDTO;
import net.cubosoft.csbypassInterprise.entities.PacienteGetOrdenesDTO;
import net.cubosoft.csbypassInterprise.entities.PacientesDTO;
import net.cubosoft.csbypassInterprise.entities.ReferenciasDTO;
import net.cubosoft.csbypassInterprise.entities.Request;
import net.cubosoft.csbypassInterprise.entities.RequestJsonPaciente;
import net.cubosoft.csbypassInterprise.entities.ResultadosGrupo;
import net.cubosoft.csbypassInterprise.service.AvalabService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@RestController
public class webService {
	
	@Autowired
	AvalabService avalabService;
	Boolean Flag_bandera= false;
	String token_verificacion="";
	String cod_pac;
	ReferenciasDTO referencia;
	Map<String, String> tokens = new HashMap<>();

	@CrossOrigin
	@GetMapping("test")
	public String test() {
		
		ReferenciasDTO referenciasDTO= avalabService.getReferenciabyLogin("andy", "1234");
		return "Hola";
	}
/*
	public Object pdfFromIntra(@RequestHeader("Authorization") String authorizationHeader,@RequestParam String orden, @RequestParam String unidadMedica) {
		if(authorizationHeader!=null &&authorizationHeader.equals(authorizationToken)) {
		*/
	@CrossOrigin
	
	@PostMapping(value="WebServiceV3",produces = MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity WebServiceV3(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody Request request) {
	    JSONObject jsonResponse = new JSONObject();
	    	System.out.println("authorizationHeaderauthorizationHeader22: "+authorizationHeader);
	    	
	    switch(request.getName()) {
	        case "generarToken":
	            Map<Object, String> params = (Map<Object, String>) request.getParam();
	            String email = params.get("email");
	            String pass = params.get("pass");	            
	            tokens.put("id_ref", "");
	             referencia = avalabService.getReferenciabyLogin(email, pass);	            	           
	            if(referencia!=null) {	            	
	            	System.out.println("lock Codigo: "+referencia.getLock_synlab());
	            	if(referencia.getLock_synlab()!=0) {
	            		   JSONObject jsonResult = new JSONObject();
				            JSONObject jsonComplete = new JSONObject();			           
				            jsonComplete.put("status", 500);
				            jsonComplete.put("result", "No contiene el permiso permitido");
				            jsonResponse.put("response", jsonComplete);
		            	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	            	}else {
	            		token_verificacion=referencia.getId_ref();	            	
		            	tokens.put("id_ref",token_verificacion);
			            JSONObject jsonResult = new JSONObject();
			            JSONObject jsonComplete = new JSONObject();
			            jsonResult.put("token", referencia.getId_ref());
			            jsonComplete.put("status", 200);
			            jsonComplete.put("result", jsonResult);
			            jsonResponse.put("response", jsonComplete);
			            System.out.println("token en verificiacion: "+token_verificacion);
			            return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	            	}
	            }else {
	            	   JSONObject jsonResult = new JSONObject();
			            JSONObject jsonComplete = new JSONObject();			           
			            jsonComplete.put("status", 500);
			            jsonComplete.put("result", "Usuario no Encontrado");
			            jsonResponse.put("response", jsonComplete);
	            	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	            }	             	
	        case "getResultadosV2":	            
	        	if(authorizationHeader != null) {	             	               
	                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
	                String nombre = vector_authorizacionHeader[0];
	                String authorizacion = vector_authorizacionHeader[1];                	                
	                if(nombre.equals("Synlab")) {   	                	                   	                               	                                
	                    		   ReferenciasDTO referenciaDto = avalabService.checkUID(authorizacion.toString());	 
	                    	          if(referenciaDto!=null) {	                    	        	   	                    	        	  
	                              		   System.out.println("22222222222222: "+ referenciaDto);
	    	                    		   if(referenciaDto.getLock_synlab()!=0) {
	    	                         		   JSONObject jsonResult = new JSONObject();
	    	       				            JSONObject jsonComplete = new JSONObject();			           
	    	       				            jsonComplete.put("status", 500);
	    	       				            jsonComplete.put("result", "No contiene el permiso permitido");
	    	       				            jsonResponse.put("response", jsonComplete);
	    	       		            	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	    	                    		   }else {
	    	                    			      Map<Object, String> params2 = (Map<Object, String>) request.getParam();
	    		   	   	   				            JSONObject jsonComplete = new JSONObject();						            				            				            
	    		   	   	   				            for (Map.Entry<Object, String> entry : params2.entrySet()) {
	    		   	   	   				                jsonComplete.put(entry.getKey().toString(), entry.getValue());
	    		   	   	   				            }				    
	    		   	   	   				            String ordnumero = jsonComplete.get("ordnumero").toString();
	    		   	   	   				            String cdgo = jsonComplete.get("cdgo").toString();		   	   	   				            
	    		   	   	   				            System.err.println("cdgo en verificar: "+cdgo);	
	    		   	   	   				            System.err.println("cdgo en checkUid: "+referenciaDto.getCod_ref());	
	    		   	   	   				            if(referenciaDto.getCod_ref().equals(cdgo)) {
	    		   	   	   				            	System.err.println("<--Tiene permiso:---> "+cdgo);
	    		   	   	   				            	//Aqui quede
	    		   	   	   				       Boolean flag_check_ord_ref=  avalabService.VerificarOrden(ordnumero, cdgo);	   	   				          				          			  					        
	    		   	   	   				          if(flag_check_ord_ref) {			
	    		   	   	   				              List<Object[]> ord_res=  avalabService.VerificarOrden_Resultados(ordnumero);
	    		   	   	   						         Object[] numero_ord_avalab = ord_res.get(0);
	    		   	   	   						       String  ordnumero2=numero_ord_avalab[0].toString();
	    		   	   	   				        	  OrdenProgressDTO Progresion= avalabService.check_Res_P_Validación(ordnumero);				        	  
	    		   	   	   				        	 if(Progresion.getCompletados()<100) {
	    		   	   	   				                 JSONObject results_flag = new JSONObject();
	    		   	   	   						            JSONObject result_doc = new JSONObject();
	    		   	   	   						            result_doc.put("results","Resultados Pendientes de validación");
	    		   	   	   						            result_doc.put("tipo",2);
	    		   	   	   						            results_flag.put("results",result_doc);
	    		   	   	   						            results_flag.put("status",500);
	    		   	   	   					        	  jsonResponse.put("response", results_flag);	        			        		        		
	    		   	   	   					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	    		   	   	   				        	 }else {
	    		   	   	   				        		 System.out.println("Aqui esta numero 2"+ ordnumero2);
	    		   	   	   				        		    String base64 = avalabService.getPdfxOrden(ordnumero);
	    		   	   	   					        		 if(base64 == null) {
	    		   	   	   					        			 JSONObject results_flag = new JSONObject();
	    		   	   	   							        	    JSONObject result_doc = new JSONObject();
	    		   	   	   							        	    JSONObject base64_pdf = new JSONObject();
	    		   	   	   							        	    base64_pdf.put("doc", "No Cuenta con base 64"); 
	    		   	   	   							        	    result_doc.put("result", base64_pdf);
	    		   	   	   							        	    results_flag.put("response", result_doc);
	    		   	   	   							        	    results_flag.put("status", 200);
	    		   	   	   							        	    return ResponseEntity.status(HttpStatus.OK).body(results_flag.toString());		
	    		   	   	   					        		 }else {
	    		   	   	   					        		  	    JSONObject results_flag = new JSONObject();
	    		   	   	   							        	    JSONObject result_doc = new JSONObject();
	    		   	   	   							        	    JSONObject base64_pdf = new JSONObject();
	    		   	   	   							        	    base64_pdf.put("doc", base64); 
	    		   	   	   							        	    result_doc.put("result", base64_pdf);
	    		   	   	   							        	    results_flag.put("response", result_doc);
	    		   	   	   							        	    results_flag.put("status", 200);
	    		   	   	   							        	    return ResponseEntity.status(HttpStatus.OK).body(results_flag.toString());		
	    		   	   	   					        		 }	   	   	   					      		        		
	    		   	   	   				        	 }				        	 				         
	    		   	   	   				          }else {
	    		   	   	   					            JSONObject results_flag = new JSONObject();
	    		   	   	   					            JSONObject result_doc = new JSONObject();
	    		   	   	   					            result_doc.put("results","Orden no Registrada o referencia erronea");
	    		   	   	   					            result_doc.put("tipo",3);
	    		   	   	   					            results_flag.put("results",result_doc);
	    		   	   	   					            results_flag.put("status",500);

	    		   	   	   				        	  jsonResponse.put("response", results_flag);	        			        		        		
	    		   	   	   				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());		   	   	    
	    		   	   	   				          }	    		   	   	   				            	
	    		   	   	   				            }else {
	    		   	   	   					            JSONObject results_flag = new JSONObject();
	    		   	   	   					            JSONObject result_doc = new JSONObject();
	    		   	   	   					            result_doc.put("results","No tiene permiso");
	    		   	   	   					            result_doc.put("tipo",3);
	    		   	   	   					            results_flag.put("results",result_doc);
	    		   	   	   					            results_flag.put("status",500);

	    		   	   	   				        	  jsonResponse.put("response", results_flag);	        			        		        		
	    		   	   	   				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());		
	    		   	   	   				            }
	    	                    		   }	                    	        	   	   	   	   				      	   	   	   				   
	   	   	   			            }   	   	   			            	                    			  	                    		                      		   	   	   			 	                	                    		                    	                    	                
	                }else {
	                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	                }
		       
		            	
	        	}else {
	        		jsonResponse.put("response", "No esta permitido");
	        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	        	}
	        	
	        	break;
	        		 
	        case "insertar":
	            System.out.println("authorizationHeaderauthorizationHeader en inserat: "+authorizationHeader);
	        	if(authorizationHeader != null) {
	                System.out.println("Authorizacion: inserat"+authorizationHeader);	                
	                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
	                String nombre = vector_authorizacionHeader[0];
	                String authorizacion = vector_authorizacionHeader[1]; 
		            JSONObject jsonPacientePost = new JSONObject();
		            String cod_pac = "";

		            JSONObject jsonRespuestaInsertarantes = new JSONObject();
		            System.out.println("Nombre revisa por favor: "+nombre);
		            	
	                if(nombre.equals("Synlab")) {   	                	
	                	 ReferenciasDTO referenciaDto = avalabService.checkUID(authorizacion.toString());
         	 	        	System.err.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");			
	                	 	        System.err.println("Referencia DTO - Errores - ID Plan : "+referenciaDto.getId_plan());
	                	 	       System.err.println("Referencia DTO - Errores - id_ori: "+referenciaDto.getCod_ori());
	                	 	      System.err.println("---------------------------------------------------------------------------");
							            if(referenciaDto!=null) {
				                     		  if(referenciaDto.getLock_synlab()!=0) {
				                        		   JSONObject jsonResult = new JSONObject();
				      				            JSONObject jsonComplete = new JSONObject();			           
				      				            jsonComplete.put("status", 500);
				      				            jsonComplete.put("result", "No contiene el permiso permitido");
				      				            jsonResponse.put("response", jsonComplete);
				      		            	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
				                     		  }else {	
				                     			 System.err.println("authorizacion.authorizacion: "+authorizacion.toString());				                     			
				                     			ReferenciasDTO codigo_ref=avalabService.getReferenciabyUID(referenciaDto.getCod_ref());
				                     			System.err.println("Codigo de referencia Id referencia: "+codigo_ref.getId_ref());				                     				                     							                     							                     			
				                     			  if(referenciaDto.getId_ref().equals(authorizacion.toString())){
				                     				 System.err.println("Logeado correctamente +++");
				                     			    Map<Object, String> params2 = (Map<Object, String>) request.getParam();
										            JSONObject jsonComplete = new JSONObject();						            				            				            
										            for (Map.Entry<Object, String> entry : params2.entrySet()) {
										                jsonComplete.put(entry.getKey().toString(), entry.getValue());
										            }								            				            				            				            		           				           				           
										            
										            String sexo_post = jsonComplete.getString("sexo");				            				            							            
											            JSONObject jsonEnvio_Orden = new JSONObject();
											            JSONObject jsonOrden_General = new JSONObject();
											            JSONObject jsonEnvio_Muestras1 = new JSONObject();
														            
											            ObjectMapper mapper = new ObjectMapper();
											            ObjectNode jsonObject = mapper.createObjectNode();
											            JSONObject jsonEnvio_Muestras =  new JSONObject();
											            String exacodigo = jsonComplete.getString("exacodigo");
											            String[] codes = exacodigo.split("\\|");
											            ArrayNode jsonArray = mapper.createArrayNode();
											            String cedula = jsonComplete.getString("hc");
											            
											           System.out.println("Cedulaaa: "+cedula);
											           PacientesDTO vectorpac = new PacientesDTO();
											             vectorpac=avalabService.checkCedula(cedula);
											             System.out.println("vectorpac ::::: "+vectorpac);
											            if(vectorpac==null) {
											            	String sexo_string;						           
												        	if(sexo_post.equals("1")){					            		
												        		sexo_string="M";	
											            	}else {
											            		sexo_string="F";					           
											            		}
												            System.out.println("Crea un nuevo paciente por favior");
												            jsonPacientePost.put("apellido", jsonComplete.getString("apellido"));
												            jsonPacientePost.put("fecha_nacimiento", jsonComplete.getString("fecha_nacimiento"));
												            jsonPacientePost.put("celular", jsonComplete.getString("celular"));
												            jsonPacientePost.put("hc", jsonComplete.getString("hc"));
												            jsonPacientePost.put("sexo", sexo_string);
												            jsonPacientePost.put("nombre", jsonComplete.getString("nombre"));
												            jsonPacientePost.put("email", jsonComplete.getString("email"));						            						            									           						    							          
													           try {
													        	    String postPac = avalabService.insertPac(jsonPacientePost.toString());
													        	    System.out.println("Vector Paciente en Post: " + postPac);
													        	    ObjectMapper mapper2 = new ObjectMapper();
													        	    JsonNode responseNode = mapper.readTree(postPac);
													        	    String jsonresultString = responseNode.get("jsonresult").asText().replace("[", "").replace("]", "");
													        	    JsonNode jsonresultNode = mapper.readTree(jsonresultString);
													        	     cod_pac = jsonresultNode.get("cod_pac").toString();
													        	    System.out.println("cod_pac: " + cod_pac); // 560
													        	} catch (JsonProcessingException e) {
													        	    e.printStackTrace();
													        	}
												           
											            }else {						           
												            cod_pac=vectorpac.getId_pac();
											            }					            
											            for (String code : codes) {
											                try {
											                    ObjectNode jsonObject2 = mapper.createObjectNode();
											                    jsonObject2.put("codigo_analisis", code);
											                    jsonObject2.put("nombre_analisis", "");
											                    System.out.println("Json Object: "+jsonObject);
											                    jsonArray.add(jsonObject2);
											                } catch (NumberFormatException e) {
											                }
											            }
											            if(Flag_bandera==false) {				
											            	String sexo_string;						           
												        	if(sexo_post.equals("1")){					            		
												        		sexo_string="M";	
											            	}else {
											            		sexo_string="F";					           
											            		}					            						            	
											                ObjectNode ordenObject = mapper.createObjectNode();					                
											                ordenObject.put("apellidos", jsonComplete.getString("apellido"));//apellidos
											                ordenObject.put("nombres", jsonComplete.getString("nombre"));//nombres
											                ordenObject.put("medico_solicitante",".");	//medico_solicitante 				                
											                ordenObject.put("cod_paciente", cod_pac);//cod_paciente
											                ordenObject.put("correo_paciente", jsonComplete.getString("email"));//correo_paciente	
											                ordenObject.put("dir_paciente", "");//dir_paciente					            			            
											                ordenObject.put("doc_paciente", jsonComplete.getString("hc"));//doc_paciente
											                ordenObject.put("fecha_orden", LocalDate.now().toString());//fecha_orden					            
											                ordenObject.put("habitacion_cama", "");//habitacion_cama					            					           					            
											                ordenObject.put("nac_paciente",jsonComplete.getString("fecha_nacimiento"));	//nac_paciente				            
											                ordenObject.put("nacio_paciente", "");//nacio_paciente					                
											                ordenObject.put("numero_orden", jsonComplete.getString("ordnumero"));					               					               			              
											                ordenObject.put("servicio","");//servicio
											                ordenObject.put("sexo_paciente", sexo_string);//tel_paciente
											                ordenObject.put("tel_paciente", jsonComplete.getString("celular"));
											                ordenObject.put("prioridad","");
											                ordenObject.put("cod_ref",referenciaDto.getCod_ref());					                								
											                ordenObject.put("id_plan",referenciaDto.getId_plan());				
											                ordenObject.put("cod_ori",referenciaDto.getCod_ori());				

											                ordenObject.put("observaciones", jsonComplete.getString("observacion")+ " / " + jsonComplete.getString("diagnostico")+ " / " + jsonComplete.getString("datos_clinicos"));						                
											                ObjectNode mainObject = mapper.createObjectNode();//tel_paciente
											                ordenObject.put("analisis", jsonArray);//prioridad
											               // mainObject.set("orden", ordenObject);//observaciones
											                String json_envio = mainObject.toString();
											                System.err.println("verifica el json porfavor ahorita echo:"+ordenObject);
											            //    Object llegada_datos=null;
											                Object llegada_datos= avalabService.insertOrden(ordenObject.toString());
											                System.err.println("verifica el json llegada_datos llegada_datos llegada_datos:"+llegada_datos);

											                if(llegada_datos != null) {
											                //	System.out.println("llegada_datos: Verifica"+llegada_datos.toString().trim());
											                	JSONObject jsonObj = new JSONObject(llegada_datos.toString());
											                	JSONObject response = jsonObj.getJSONObject("response");
											                	int code = response.getInt("code");
											                	String codeString = String.valueOf(code);
											                	
											                	System.out.println("Code: " + code);
													            jsonRespuestaInsertarantes.put("result","Insertado correctamente");
													            jsonRespuestaInsertarantes.put("orden",codeString);
													            jsonRespuestaInsertarantes.put("orden_ext",jsonComplete.getString("ordnumero"));
													            jsonResponse.put("status",200);
												            	jsonResponse.put("response", jsonRespuestaInsertarantes);	        			        		        		
												        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
											                }else {
														            jsonRespuestaInsertarantes.put("result","Error al ingresar la orden");
														            System.out.println("verifica orden: "+llegada_datos);
														            jsonResponse.put("status",500);
														          	jsonResponse.put("response", jsonRespuestaInsertarantes);	        			        		        		
													        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
											                }				                
												            }
				                     			  }else {
				                     				 jsonRespuestaInsertarantes.put("result","Error en verificacion de datos");
											            jsonResponse.put("status",500);
											          	jsonResponse.put("response", jsonRespuestaInsertarantes);	        			        		        		
										        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
				                     			  }
				                     			  
				                     									            				            				            				         									            				 				                     			  
				                     		  }
								        			  			       
							            }else {
							            	jsonResponse.put("response", "No esta permitido error de Token");	        			        		        		
							        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
							            }
		                    
		                   
		                    
	          
	                }else {
	                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	                }
		       
		            
	        	}else {
	        		jsonResponse.put("response", "No esta permitido");
	        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	        	}
	        	break;
	        	
	        case "getResultados":	        
	            System.out.println("authorizationHeaderauthorizationHeader: "+authorizationHeader);
	            	            
	        	if(authorizationHeader != null) {
	        		
	                System.out.println("Authorizacion: getResultados - "+authorizationHeader);	                
	                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
	                String nombre = vector_authorizacionHeader[0];
	                String authorizacion = vector_authorizacionHeader[1]; 		            
	                if(nombre.equals("Synlab")) {   	                    		                	                    
	                    ReferenciasDTO referenciaD = avalabService.checkUID(authorizacion.toString());
	                          if(referenciaD!=null) {
	                            if(referenciaD.getLock_synlab()!=0) {
	     	               		   JSONObject results_flag = new JSONObject();
	     					            JSONObject result_doc = new JSONObject();
	     					            result_doc.put("results","No contiene el permiso permitido.");
	     					            result_doc.put("tipo",3);
	     					            results_flag.put("results",result_doc);
	     					            results_flag.put("status",500);
	     				        	  jsonResponse.put("response", results_flag);	        			        		        		
	     				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	               		                    	
	     	                    }else {
	     	                    	
	     	                    	 Map<Object, String> params2 = (Map<Object, String>) request.getParam();
	 					            String ordnumero=params2.get("ordnumero");				
	 					            String ordnumero_jsonenviar=params2.get("ordnumero");
	 					         List<Object[]> ord_res=  avalabService.VerificarOrden_Resultados(ordnumero);
	 				
	 					        
	 					         if(ord_res==null) {
	 					        	  JSONObject results_flag = new JSONObject();
	 						            JSONObject result_doc = new JSONObject();
	 						            result_doc.put("results","No existe la orden requerida");
	 						            result_doc.put("tipo",2);
	 						            results_flag.put("results",result_doc);
	 						            results_flag.put("status",500);
	 					        	  jsonResponse.put("response", results_flag);	        			        		        		
	 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	 					         }
	 						      
		 					        Object[] numero_ord_avalab2 = ord_res.get(0);
		 					        String variable_autetificacion=numero_ord_avalab2[16].toString();
		 					        System.err.println("numero_ord_avalab2 notar que este variable_autetificacion: "+variable_autetificacion);
		 					        System.err.println("checkUid verircarfff : "+referenciaD.getCod_ref());

		 					        if(variable_autetificacion.equals(referenciaD.getCod_ref())) {
		 					           Object[] numero_ord_avalab = ord_res.get(0);				         				            				            			           				           				           
		 						          if(ord_res != null && !ord_res.isEmpty()) {		
		 						        	  ordnumero=numero_ord_avalab[0].toString();
		 						        	  System.err.println("ordnumero - ordnumero- ordnumero - : "+ordnumero);   	  
		 						        	  OrdenProgressDTO Progresion= avalabService.check_Res_P_Validación(ordnumero);				        	  
		 						        	     	 System.err.println("Progresion: "+Progresion.getCompletados());				        	  
		 						        	 if(Progresion.getCompletados()<100) {
		 						                 JSONObject results_flag = new JSONObject();
		 								            JSONObject result_doc = new JSONObject();
		 								            result_doc.put("results","Resultados Pendientes de validación");
		 								            result_doc.put("tipo",2);
		 								            results_flag.put("results",result_doc);
		 								            results_flag.put("status",500);
		 							        	  jsonResponse.put("response", results_flag);	        			        		        		
		 							        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
		 						        	 }else {
		 						      		    String base64 = avalabService.getPdfxOrden(ordnumero);
		 						      		  if(base64 == null) {
		 						      		    base64 = "No existe en base64"; 
		 						      		}

		 						      		  	OrdenDTO vector_avalab = avalabService.getResultadosxOrden(ordnumero);					        		 
		 						        		 JSONArray parametrosArray = new JSONArray();					        		
		 			        		             JSONObject parametroOrdenDatos = new JSONObject();		        		            
		 	 					        		     parametroOrdenDatos.put("tipo",1);
		 						        		     parametroOrdenDatos.put("doc",base64);
		 							        	     parametroOrdenDatos.put("year_orden",vector_avalab.getIdOrden());
		 				        		             parametroOrdenDatos.put("orden_synlab",ordnumero_jsonenviar);
		 				        		             parametroOrdenDatos.put("hc",vector_avalab.getPaciente().getIdPaciente());
		 				        		             String obsExternaOrden = vector_avalab.getObsExternaOrden();
		 				        		             if (obsExternaOrden == null || obsExternaOrden.isEmpty()) {
		 				        		                 parametroOrdenDatos.put("observaciones", JSONObject.NULL);
		 				        		             } else {
		 				        		                 parametroOrdenDatos.put("observaciones", obsExternaOrden);
		 				        		             }
		 						        		 for (TipoAnalisis tipoAnalisis : vector_avalab.getTipoAnalisis()) {
		 						        		     for (Analisis analisis : tipoAnalisis.getAnalisis()) {
		 						        		         for (Parametros parametro : analisis.getParametros()) {
		 						        		             JSONObject parametroJson = new JSONObject();					        		             					        		    					        		             
		 						        		             parametroJson.put("exacodigo", parametro.getCodigoParametro());
		 						        		             parametroJson.put("Resultado", parametro.getResultado().getResultado());
		 						        		             String referenciaMinima = parametro.getResultado().getVdrResultado();

		 						        		             if (referenciaMinima != null) {
		 						        		                 String[] partes = referenciaMinima.split("-");
		 						        		                 String minimo = partes[0];
		 						        		                 String maximo = partes[1];	
		 						        		                 parametroJson.put("refminima", minimo);
		 						        		                 parametroJson.put("refmaxima", maximo);
		 						        		             } else {
		 						        		            	  parametroJson.put("refminima", JSONObject.NULL);
		 							        		                 parametroJson.put("refmaxima", JSONObject.NULL);
		 						        		             }
		 						        		            String sexo = vector_avalab.getPaciente().getSexoPaciente();
		 						        		            System.out.println("Sexoo: "+sexo);
		 						        		            if(sexo!=null) {
		 						        		            	  String sexo_send_json="";
		 								        		             if(sexo.equals("M")) {
		 								        		           	 sexo_send_json="Masculino";
		 								        		             }else {
		 								        		            	 sexo_send_json="Femenino";
		 								        		           }
		 								        		             parametroJson.put("sexo", sexo_send_json);
		 						        		            }else {
		 							        		             parametroJson.put("sexo", JSONObject.NULL);
		 						        		            }
		 						        		          
		 						        		             parametroJson.put("Unidad", parametro.getResultado().getUnidadResultado());
		 						        		             
		 						        		             String comentRes=parametro.getResultado().getObsResultado();
		 						        		             if(comentRes!=null) {
		 							        		             parametroJson.put("ComentarioResultado", parametro.getResultado().getObsResultado());
		 						        		             }else {
		 							        		             parametroJson.put("ComentarioResultado", JSONObject.NULL);
		 						        		             }					        		             					        		             
		 						        		             parametroJson.put("comentarioOrden", JSONObject.NULL);
		 						        		             parametroJson.put("Observaciones", JSONObject.NULL);
		 						        		             parametroJson.put("validado", JSONObject.NULL);
		 						        		             parametroJson.put("edadminima", JSONObject.NULL);
		 						        		             parametroJson.put("edadmaxima", JSONObject.NULL);
		 						        		             parametroJson.put("PanicoMinimo",JSONObject.NULL);
		 						        		             parametroJson.put("PanicoMaximo", JSONObject.NULL);	
		 						        		            parametroJson.put("exacodigo",parametro.getCodigoParametro());
		 						        		            parametroJson.put("descripcion",parametro.getDescripcionParametro());
		 						        		           //parametroJson.put("descripcion", analisis.getDescripcionAnalisis());	
		 						        		            System.out.println("Nombre del análisis: " + tipoAnalisis.getDescripcionTipoAnalisis());
		 						        		             String date_send_json = parametro.getResultado().getFechaModificacionResultado();
		 						        		             SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			        		             
		 						        		             java.util.Date utilDate = null;
		 						        		             try {
		 						        		                 utilDate = inputFormat.parse(date_send_json);
		 						        		             } catch (ParseException e) {
		 						        		                 e.printStackTrace();
		 						        		             }					        		            
		 						        		             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());					        		            
		 						        		             SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss:SSSa");					        		            
		 						        		             String formattedDate = outputFormat.format(sqlDate);
		 						        		             System.out.println("date_send_json: " + formattedDate);					        		             					        		            
		 						        		             parametroJson.put("validado", 1);					        		             
		 						        		             parametroJson.put("FechaResultado", formattedDate);					        		             
		 						        		             parametrosArray.put(parametroJson);					        		             					        		            
		 						        		         }
		 						        		     }
		 						        		 }
		 			        		              
		 			        		             JSONObject JsonResponseFinal = new JSONObject();
		 			        		             
		 			        		             parametroOrdenDatos.put("0", parametrosArray);     
		 						        		 jsonResponse.put("result", parametroOrdenDatos);     
		 						        		// jsonResponse.put("0", parametrosArray);
		 						        		 JsonResponseFinal.put("response",jsonResponse);
		 						        		 JsonResponseFinal.put("status",200);
		 							        return ResponseEntity.status(HttpStatus.OK).body(JsonResponseFinal.toString());		 

		 						        	 }				        	 				         
		 						          }else {
		 							            JSONObject results_flag = new JSONObject();
		 							            JSONObject result_doc = new JSONObject();
		 							            result_doc.put("results","Orden no Registrada");
		 							            result_doc.put("tipo",3);
		 							            results_flag.put("results",result_doc);
		 							            results_flag.put("status",500);
		 						        	  jsonResponse.put("response", results_flag);	        			        		        		
		 						        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());		 
		 						          }
		 					        }else {
		 					        	 JSONObject results_flag = new JSONObject();
	 							            JSONObject result_doc = new JSONObject();
	 							            result_doc.put("results","Orden no Registrada");
	 							            result_doc.put("tipo",3);
	 							            results_flag.put("results",result_doc);
	 							            results_flag.put("status",500);
	 						        	  jsonResponse.put("response", results_flag);	        			        		        		
	 						        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
		 					        }
		 					        
	 					   	
	     	                    }
					           				          
						        //jsonResponse.put("response en getV2", jsonComplete);	        			        		        		
				        		//return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
				        		
				            }else {
				            	jsonResponse.put("response", "No esta permitido error de Token");	        			        		        		
				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
				            }
			                    
	                    
	                    
		                    
	                }else {
	                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	                }
		       
		            
	        	}else {
	        		jsonResponse.put("response", "No esta permitido");
	        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	        	}
	        	
	           	
	        case "getResultadosParciales":	        
	            System.out.println("authorizationHeaderauthorizationHeader: "+authorizationHeader);
	            	            
	        	if(authorizationHeader != null) {
	        		
	                System.out.println("Authorizacion: getResultados - "+authorizationHeader);	                
	                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
	                String nombre = vector_authorizacionHeader[0];
	                String authorizacion = vector_authorizacionHeader[1]; 		            
	                if(nombre.equals("Synlab")) {   	                    		                	                    
	                    ReferenciasDTO referenciaD = avalabService.checkUID(authorizacion.toString());
	                          if(referenciaD!=null) {
	                            if(referenciaD.getLock_synlab()!=0) {
	     	               		   JSONObject results_flag = new JSONObject();
	     					            JSONObject result_doc = new JSONObject();
	     					            result_doc.put("results","No contiene el permiso permitido.");
	     					            result_doc.put("tipo",3);
	     					            results_flag.put("results",result_doc);
	     					            results_flag.put("status",500);
	     				        	  jsonResponse.put("response", results_flag);	        			        		        		
	     				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	               		                    	
	     	                    }else {
	     	                    	
	     	                    	 Map<Object, String> params2 = (Map<Object, String>) request.getParam();
	 					            String ordnumero=params2.get("ordnumero");				
	 					            String ordnumero_jsonenviar=params2.get("ordnumero");
	 					         List<Object[]> ord_res=  avalabService.VerificarOrden_Resultados(ordnumero);
	 				
	 					        
	 					         if(ord_res==null) {
	 					        	  JSONObject results_flag = new JSONObject();
	 						            JSONObject result_doc = new JSONObject();
	 						            result_doc.put("results","No existe la orden requerida");
	 						            result_doc.put("tipo",2);
	 						            results_flag.put("results",result_doc);
	 						            results_flag.put("status",500);
	 					        	  jsonResponse.put("response", results_flag);	        			        		        		
	 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	 					         }
	 						      
		 					        Object[] numero_ord_avalab2 = ord_res.get(0);
		 					        String variable_autetificacion=numero_ord_avalab2[16].toString();
		 					        System.err.println("numero_ord_avalab2 notar que este variable_autetificacion: "+variable_autetificacion);
		 					        System.err.println("checkUid verircarfff : "+referenciaD.getCod_ref());

		 					        if(variable_autetificacion.equals(referenciaD.getCod_ref())) {
		 					           Object[] numero_ord_avalab = ord_res.get(0);				         				            				            			           				           				           
		 						          if(ord_res != null && !ord_res.isEmpty()) {		
		 						        	  ordnumero=numero_ord_avalab[0].toString();
		 						        	  System.err.println("ordnumero - ordnumero- ordnumero - : "+ordnumero);   	  
		 						        	  OrdenProgressDTO Progresion= avalabService.check_Res_P_Validación(ordnumero);				        	  
		 						        	     	 System.err.println("Progresion: "+Progresion.getCompletados());				        	  
		 						        	 if(Progresion.getCompletados()<0) {
		 						                 JSONObject results_flag = new JSONObject();
		 								            JSONObject result_doc = new JSONObject();
		 								            result_doc.put("results","Resultados Pendientes de validación");
		 								            result_doc.put("tipo",2);
		 								            results_flag.put("results",result_doc);
		 								            results_flag.put("status",500);
		 							        	  jsonResponse.put("response", results_flag);	        			        		        		
		 							        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
		 						        	 }else {
		 						      		    String base64 = avalabService.getPdfxOrden(ordnumero);
		 						      		  if(base64 == null) {
		 						      		    base64 = "No existe en base64"; 
		 						      		}
		 						      		  	OrdenDTO vector_avalab = avalabService.getResultadosxOrden(ordnumero);					        		 
		 						      		  	System.err.println("----vector_avalab---"+vector_avalab);
		 						      		  	
		 						      		  	if(vector_avalab==null) {
		 						      		        JSONObject results_flag = new JSONObject();
			 							            JSONObject result_doc = new JSONObject();
			 							            result_doc.put("results","Orden en estado Pediente");
			 							            result_doc.put("tipo",3);
			 							            results_flag.put("results",result_doc);
			 							            results_flag.put("status",500);
			 						        	  jsonResponse.put("response", results_flag);	        			        		        		
			 						        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
		 						      		  	}
		 						      		  	
		 						        		 JSONArray parametrosArray = new JSONArray();					        		
		 			        		             JSONObject parametroOrdenDatos = new JSONObject();		        		            
		 	 					        		     parametroOrdenDatos.put("tipo",1);
		 						        		     parametroOrdenDatos.put("doc",base64);
		 							        	     parametroOrdenDatos.put("year_orden",vector_avalab.getIdOrden());
		 				        		             parametroOrdenDatos.put("orden_synlab",ordnumero_jsonenviar);
		 				        		             parametroOrdenDatos.put("hc",vector_avalab.getPaciente().getIdPaciente());
		 				        		             String obsExternaOrden = vector_avalab.getObsExternaOrden();
		 				        		             if (obsExternaOrden == null || obsExternaOrden.isEmpty()) {
		 				        		                 parametroOrdenDatos.put("observaciones", JSONObject.NULL);
		 				        		             } else {
		 				        		                 parametroOrdenDatos.put("observaciones", obsExternaOrden);
		 				        		             }
		 						        		 for (TipoAnalisis tipoAnalisis : vector_avalab.getTipoAnalisis()) {
		 						        		     for (Analisis analisis : tipoAnalisis.getAnalisis()) {
		 						        		         for (Parametros parametro : analisis.getParametros()) {
		 						        		             JSONObject parametroJson = new JSONObject();					        		             					        		    					        		             
		 						        		             parametroJson.put("exacodigo", parametro.getCodigoParametro());
		 						        		             parametroJson.put("Resultado", parametro.getResultado().getResultado());
		 						        		             String referenciaMinima = parametro.getResultado().getVdrResultado();

		 						        		             if (referenciaMinima != null) {
		 						        		                 String[] partes = referenciaMinima.split("-");
		 						        		                 String minimo = partes[0];
		 						        		                 String maximo = partes[1];	
		 						        		                 parametroJson.put("refminima", minimo);
		 						        		                 parametroJson.put("refmaxima", maximo);
		 						        		             } else {
		 						        		            	  parametroJson.put("refminima", JSONObject.NULL);
		 							        		                 parametroJson.put("refmaxima", JSONObject.NULL);
		 						        		             }
		 						        		            String sexo = vector_avalab.getPaciente().getSexoPaciente();
		 						        		            System.out.println("Sexoo: "+sexo);
		 						        		            if(sexo!=null) {
		 						        		            	  String sexo_send_json="";
		 								        		             if(sexo.equals("M")) {
		 								        		           	 sexo_send_json="Masculino";
		 								        		             }else {
		 								        		            	 sexo_send_json="Femenino";
		 								        		           }
		 								        		             parametroJson.put("sexo", sexo_send_json);
		 						        		            }else {
		 							        		             parametroJson.put("sexo", JSONObject.NULL);
		 						        		            }
		 						        		          
		 						        		             parametroJson.put("Unidad", parametro.getResultado().getUnidadResultado());
		 						        		             
		 						        		             String comentRes=parametro.getResultado().getObsResultado();
		 						        		             if(comentRes!=null) {
		 							        		             parametroJson.put("ComentarioResultado", parametro.getResultado().getObsResultado());
		 						        		             }else {
		 							        		             parametroJson.put("ComentarioResultado", JSONObject.NULL);
		 						        		             }					        		             					        		             
		 						        		             parametroJson.put("comentarioOrden", JSONObject.NULL);
		 						        		             parametroJson.put("Observaciones", JSONObject.NULL);
		 						        		             parametroJson.put("validado", JSONObject.NULL);
		 						        		             parametroJson.put("edadminima", JSONObject.NULL);
		 						        		             parametroJson.put("edadmaxima", JSONObject.NULL);
		 						        		             parametroJson.put("PanicoMinimo",JSONObject.NULL);
		 						        		             parametroJson.put("PanicoMaximo", JSONObject.NULL);					        		             
		 						        		             String date_send_json = parametro.getResultado().getFechaModificacionResultado();
		 						        		             SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			        		             
		 						        		             java.util.Date utilDate = null;
		 						        		             try {
		 						        		                 utilDate = inputFormat.parse(date_send_json);
		 						        		             } catch (ParseException e) {
		 						        		                 e.printStackTrace();
		 						        		             }					        		            
		 						        		             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());					        		            
		 						        		             SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss:SSSa");					        		            
		 						        		             String formattedDate = outputFormat.format(sqlDate);
		 						        		             System.out.println("date_send_json: " + formattedDate);					        		             					        		            
		 						        		             parametroJson.put("validado", 1);					        		             
		 						        		             parametroJson.put("FechaResultado", formattedDate);					        		             
		 						        		             parametrosArray.put(parametroJson);					        		             					        		            
		 						        		         }
		 						        		     }
		 						        		 }
		 			        		              
		 			        		             JSONObject JsonResponseFinal = new JSONObject();
		 			        		             
		 			        		             parametroOrdenDatos.put("0", parametrosArray);     
		 						        		 jsonResponse.put("result", parametroOrdenDatos);     
		 						        		// jsonResponse.put("0", parametrosArray);
		 						        		 JsonResponseFinal.put("response",jsonResponse);
		 						        		 JsonResponseFinal.put("status",200);
		 							        return ResponseEntity.status(HttpStatus.OK).body(JsonResponseFinal.toString());		 

		 						        	 }				        	 				         
		 						          }else {
		 							            JSONObject results_flag = new JSONObject();
		 							            JSONObject result_doc = new JSONObject();
		 							            result_doc.put("results","Orden no Registrada");
		 							            result_doc.put("tipo",3);
		 							            results_flag.put("results",result_doc);
		 							            results_flag.put("status",500);
		 						        	  jsonResponse.put("response", results_flag);	        			        		        		
		 						        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());		 
		 						          }
		 					        }else {
		 					        	 JSONObject results_flag = new JSONObject();
	 							            JSONObject result_doc = new JSONObject();
	 							            result_doc.put("results","Orden no Registrada");
	 							            result_doc.put("tipo",3);
	 							            results_flag.put("results",result_doc);
	 							            results_flag.put("status",500);
	 						        	  jsonResponse.put("response", results_flag);	        			        		        		
	 						        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
		 					        }
		 					        
	 					   	
	     	                    }
					           				          
						        //jsonResponse.put("response en getV2", jsonComplete);	        			        		        		
				        		//return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
				        		
				            }else {
				            	jsonResponse.put("response", "No esta permitido error de Token");	        			        		        		
				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
				            }
			                    
	                    
	                    
		                    
	                }else {
	                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	                }
		       
		            
	        	}else {
	        		jsonResponse.put("response", "No esta permitido");
	        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	        	}
	        	
	        	
	        	//////Codigo que comienza el getListaOrdenes/////
	        	
	        	
	        case "getListaOrdenes":	        
	            System.out.println("authorizationHeaderauthorizationHeader: "+authorizationHeader);
	            	            
	        	if(authorizationHeader != null) {
	        		
	                System.out.println("Authorizacion: getResultados - "+authorizationHeader);	                
	                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
	                String nombre = vector_authorizacionHeader[0];
	                String authorizacion = vector_authorizacionHeader[1]; 		            
	                if(nombre.equals("Synlab")) {   	                    		                	                    
	                    ReferenciasDTO referenciaD = avalabService.checkUID(authorizacion.toString());
	                    System.err.println("Referencia dto: "+referenciaD);
	                          if(referenciaD!=null) {
	                            if(referenciaD.getLock_synlab()!=0) {
	     	               		   JSONObject results_flag = new JSONObject();
	     					            JSONObject result_doc = new JSONObject();
	     					            result_doc.put("results","No contiene el permiso permitido.");
	     					            result_doc.put("tipo",3);
	     					            results_flag.put("results",result_doc);
	     					            results_flag.put("status",500);
	     				        	  jsonResponse.put("response", results_flag);	        			        		        		
	     				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	               		                    	
	     	                    }else {
	     	                    	
	     	                    	Map<Object, String> params2 = (Map<Object, String>) request.getParam();
	 					            String historiaClinica=params2.get("historiaClinica");	 					           	 					          
	 					           List<PacienteGetOrdenesDTO> getCedula=  avalabService.getCedulaPaciente(historiaClinica);	 					          
	 					         if(getCedula==null) {
	 					        	  JSONObject results_flag = new JSONObject();
	 						            JSONObject result_doc = new JSONObject();
	 						            result_doc.put("results","No existe el Paciente, por favor ingresa otra historia clinica.");
	 						            result_doc.put("tipo",2);
	 						            results_flag.put("results",result_doc);
	 						            results_flag.put("status",500);
	 					        	  jsonResponse.put("response", results_flag);	        			        		        		
	 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	 					         }else {	 					        	 					        	
	 					        	String fechaInicio=params2.get("fechaInicio");
	 					        	String fechaFin=params2.get("fechaFin"); 					      	 					        		 					        		 					        
	 					       	Boolean Check15Antes = avalabService.CheckFechaAntes15Dias(fechaInicio,fechaFin);
	 					        	if(Check15Antes) {	 					        			 					        		
		 					        	List<ListaOrden> list_orden= avalabService.getOrdenByFecha(fechaInicio,fechaFin,getCedula.get(0).getCod_pac(),referenciaD.getCod_ref());
		 					        	//System.out.println("st_orden.get(i).getNro_Orden() true: "+list_orden.get(0).getNro_Orden());
	 					        		System.out.println("Check15Antes true: "+Check15Antes);	 					        			
	 					        		if (list_orden != null && !list_orden.isEmpty()) {
	 					   	        	JSONArray jsonArray = new JSONArray();
		 					        	for(int i=0;i<list_orden.size();i++) {	 					        	    
		 					              List<Object[]> ord_res=  avalabService.VerificarOrden_Resultados(list_orden.get(i).getNro_Orden());	 					        	   
		 					              Object[] numero_ord_avalab = ord_res.get(0);
	 	   	   						      String  ordnumero2=numero_ord_avalab[0].toString();
	 	   	   						  OrdenProgressDTO Progresion= avalabService.check_Res_P_Validación(list_orden.get(i).getNro_Orden());
		 					        	    System.err.println("ordnumero2: "+ordnumero2);
		 					        	   if(Progresion.getCompletados()<100) {
		   	   	   				                 JSONObject results_flag = new JSONObject();
		   	   	   						            JSONObject result_doc = new JSONObject();
		   	   	   						            result_doc.put("doc","Resultados Pendientes de validación");
		   	   	   						            result_doc.put("orden_synlab", list_orden.get(i).getNro_Orden());
		   	   	   						            result_doc.put("year_orden", list_orden.get(i).getNro_ext() != null ? list_orden.get(i).getNro_ext() : JSONObject.NULL);
		   	   	   						   			result_doc.put("hc", getCedula.get(0).getId_pac());
		   	   	   						   			result_doc.put("observaciones", list_orden.get(i).getObs_ext());		   	   	   						            
		   	   	   						            results_flag.put("results",result_doc);
		   	   	   						            results_flag.put("status",200);
		   	   	   					        	  jsonResponse.put("response", results_flag);	
		   	   	   					        	  jsonArray.put(results_flag);
		   	   	   				        	 }else {
		   	   	   				        		 System.out.println("Aqui esta numero 2---*-*"+ ordnumero2);		   	   	   				        		
		   	   	   				        	String archivo;
		   	   	   				        	  //String base64 = "----";		   	   	   					        
		   	   	   					        		  	    JSONObject results_flag = new JSONObject();
		   	   	   							        	    JSONObject result_doc = new JSONObject();
		   	   	   							        	    JSONObject base64_pdf = new JSONObject();			   	   	   							        	   
		   	   	   							        	    
		   		   	   	   				        		 //System.out.println("list_orden.get(i).getNro_Orden()-*-*"+ list_orden.get(i).getNro_Orden());
		   		   	   	   				        		 //System.out.println("getCedula.get(i).getApellido()*-*"+getCedula.get(0).getApellido());
		   		   	   	   				        		// System.out.println("getCedula.get(i).getNombre()-*-*"+ getCedula.get(i).getNombre());

		   	   	   							        	    archivo=list_orden.get(i).getNro_Orden()+"_"+getCedula.get(0).getApellido()+"_"+getCedula.get(0).getNombre()+".pdf";
		   	   	   							        	    		
		   	   	   			        	    				base64_pdf.put("orden_synlab", list_orden.get(i).getNro_Orden());
		   	   	   			        	    				base64_pdf.put("year_orden", list_orden.get(i).getNro_ext());
		   	   	   			        	    				base64_pdf.put("hc", getCedula.get(0).getId_pac());
		   	   	   			        	    				base64_pdf.put("observaciones", list_orden.get(i).getObs_ext());
		   	   	   			        	    				base64_pdf.put("archivo", archivo);
		   	   	   							        	    //base64_pdf.put("doc", base64);
		   	   	   							        	    result_doc.put("result", base64_pdf);
		   	   	   							        	    result_doc.put("status", 200);
		   	   	   							        	    results_flag.put("response", result_doc);	   	   	   							       
		   	   	   							        	    jsonArray.put(results_flag);	   	   	   							        	 		
		   	   	   					        		 
		   	   	   				        	 	}	 					        	    	  					        	 					        	  	 					        	   	 					        	    		 					        	
		 					        		}

		 					        	jsonResponse.put("0", jsonArray);
		 					        	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	 	
	 					        		}else {
	 					        			JSONObject results_flag = new JSONObject();
		 						            JSONObject result_doc = new JSONObject();
		 						            result_doc.put("results","!! No existe ordenes, en ese rango de Fechas !!");
		 						            result_doc.put("tipo",2);
		 						            results_flag.put("results",result_doc);
		 						            results_flag.put("status",500);
		 					        	  jsonResponse.put("response", results_flag);	        			        		        		
		 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	 					        		}
	 					        		
		 				
	 					        	}else {
	 					        		  JSONObject results_flag = new JSONObject();
		 						            JSONObject result_doc = new JSONObject();
		 						            result_doc.put("results","!! Error por favor verifica, el filtro de fechas 15 dias maximo!!");
		 						            result_doc.put("tipo",2);
		 						            results_flag.put("results",result_doc);
		 						            results_flag.put("status",500);
		 					        	  jsonResponse.put("response", results_flag);	        			        		        		
		 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
	 					        	}
	 					       	
	 					        				        	 					        
	 					         }
		
	     	                    }					           				         
						     
				            }else {
				            	jsonResponse.put("response", "No esta permitido error de Token");	        			        		        		
				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
				            }
			                    
	                    
	                    
		                    
	                }else {
	                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	                }
		       
		            
	        	}else {
	        		jsonResponse.put("response", "No esta permitido");
	        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
	        	}
	        	

	        	
	        	//////Codigo que comienza el getListaOrdenesUsuariosAd/////
	        	
	        	
		        case "getListaOrdenesUsuariosAd":	        
		            System.out.println("authorizationHeaderauthorizationHeader: "+authorizationHeader);
		            	            
		        	if(authorizationHeader != null) {
		        		
		                String[] vector_authorizacionHeader = authorizationHeader.split(" ", 2);
		                String nombre = vector_authorizacionHeader[0];
		                String authorizacion = vector_authorizacionHeader[1]; 		            
		                if(nombre.equals("Synlab")) {    	                    		                	                    
		                    ReferenciasDTO referenciaD = avalabService.checkUID(authorizacion.toString());
		                    System.err.println("Referencia dto: "+referenciaD);
		                          if(referenciaD!=null) {
		                            if(referenciaD.getLock_synlab()!=0) {
		     	               		   JSONObject results_flag = new JSONObject();
		     					            JSONObject result_doc = new JSONObject();
		     					            result_doc.put("results","No contiene el permiso permitido.");
		     					            result_doc.put("tipo",3);
		     					            results_flag.put("results",result_doc);
		     					            results_flag.put("status",500);
		     				        	  jsonResponse.put("response", results_flag);	        			        		        		
		     				        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	               		                    	
		     	                    }else {
		     	                    	
		     	                    	List<ReferenciasDTO> grupo = avalabService.getReferenciabyCod(referenciaD.getGrupo());
		     	                    	
		     	                    	if(grupo==null && grupo.isEmpty()) {		     	                    		
		     	                    		JSONObject results_flag = new JSONObject();
		 						            JSONObject result_doc = new JSONObject();
		 						            result_doc.put("results","No existen grupos en la refencia");
		 						            result_doc.put("tipo",2);
		 						            results_flag.put("results",result_doc);
		 						            results_flag.put("status",500);
		 					        	  jsonResponse.put("response", results_flag);	        			        		        		
		 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());			     	                    		
		     	                    	}else {
		     	                    		JSONArray jsonArray = new JSONArray();
		     	                    		for(int i=0;i<grupo.size();i++) {
		     	                    			Map<Object, String> params2 = (Map<Object, String>) request.getParam();
				 					            String historiaClinica=params2.get("historiaClinica");	 					           	 					          
				 					           List<PacienteGetOrdenesDTO> getCedula=  avalabService.getCedulaPaciente(historiaClinica);
				 					          if(getCedula==null) {
				 					        	  JSONObject results_flag = new JSONObject();
				 						            JSONObject result_doc = new JSONObject();
				 						            result_doc.put("results","No existe el Paciente, por favor ingresa otra historia clinica.");
				 						            result_doc.put("tipo",2);
				 						            results_flag.put("results",result_doc);
				 						            results_flag.put("status",500);
				 					        	  jsonResponse.put("response", results_flag);
				 					        	 jsonArray.put(results_flag);	
				 					        		//return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
				 					         }else {
				 					        	String fechaInicio=params2.get("fechaInicio");
				 					        	String fechaFin=params2.get("fechaFin");
				 					        	
				 					       	Boolean Check15Antes = avalabService.CheckFechaAntes15Dias(fechaInicio,fechaFin);
				 					       	System.err.println("Check15Antes BOOLEANANOO: "+Check15Antes);
				 					        	if(Check15Antes) {	 					 					        		
				 					        		System.out.println("Aqui llego en positivo");
				 					        		List<ListaOrden> list_orden= avalabService.getOrdenByFecha(fechaInicio,fechaFin,getCedula.get(0).getCod_pac(),grupo.get(i).getCod_ref());
				 					        		
				 					        		if (list_orden != null && !list_orden.isEmpty()) {
					 					        		System.out.println("Aqui llego en positivo: "+list_orden.size());

				 					   	        	for(int j=0;j<list_orden.size();j++) {	 					 					   	        		
				 					   	        	  List<Object[]> ord_res=  avalabService.VerificarOrden_Resultados(list_orden.get(j).getNro_Orden());	 					        	   
					 					              Object[] numero_ord_avalab = ord_res.get(0);
				 	   	   						      String  ordnumero2=numero_ord_avalab[0].toString();
				 	   	   						      				 	   	   						      				 	   	   						    
				 	   	   						  OrdenProgressDTO Progresion= avalabService.check_Res_P_Validación(list_orden.get(j).getNro_Orden());
					 					        	    System.err.println("ordnumero2: "+ordnumero2);
					 					        	   System.err.println("Progresion: "+Progresion.getCompletados());
					 					        	   System.err.println("-------list_orden.get(j).getNro_Orden()------------: "+list_orden.get(j).getNro_Orden());					 					        	
					 					        	   if(Progresion.getCompletados()<100) {
					 					        		   System.out.println("Entra al ifffff");
					   	   	   				                 JSONObject results_flag = new JSONObject();
					   	   	   						            JSONObject result_doc = new JSONObject();
					   	   	   						            JSONObject base64_pdf = new JSONObject();	
					   	   	   						            base64_pdf.put("doc","Resultados Pendientes de validación");
					   	   	   						   			base64_pdf.put("orden_synlab", list_orden.get(j).getNro_Orden());
					   	   	   									base64_pdf.put("year_orden", list_orden.get(j).getNro_ext() != null ? list_orden.get(j).getNro_ext() : JSONObject.NULL);
					   	   	   									base64_pdf.put("hc", getCedula.get(0).getId_pac());
					   	   	   									base64_pdf.put("observaciones", list_orden.get(j).getObs_ext() !=null ? list_orden.get(j).getObs_ext(): JSONObject.NULL);		   	   	   						            					   	   	   						   			
					   	   	   						   			result_doc.put("results",base64_pdf);
					   	   	   						   			result_doc.put("status",200);
			   	   	   							        	    results_flag.put("response", result_doc);	   	   	   							       
					   	   	   					        	 jsonArray.put(results_flag);
					   	   	   				        	 }else {
					   	   	   				        System.out.println("Aqui esta numero 2---*- ++++*"+ ordnumero2);
		   	   	   				        		   //String base64 = avalabService.getPdfxOrden(list_orden.get(i).getNro_Orden());
		   	   	   				        		    		String archivo;
		   	   	   				     	   					JSONObject results_flag = new JSONObject();
		   	   	   							        	    JSONObject result_doc = new JSONObject();
		   	   	   							        	    JSONObject base64_pdf = new JSONObject();			   	   	   							        	   		   	   	   							        	    
		   	   	   							        	   archivo=ordnumero2+"_"+getCedula.get(0).getApellido()+"_"+getCedula.get(0).getNombre()+".pdf";		   	   	   							        	    		
		   	   	   			        	    				base64_pdf.put("orden_synlab", list_orden.get(j).getNro_Orden());
		   	   	   			        	    				base64_pdf.put("year_orden", list_orden.get(j).getNro_ext());
		   	   	   			        	    				base64_pdf.put("year_orden", list_orden.get(j).getNro_ext() != null ? list_orden.get(j).getNro_ext() : JSONObject.NULL);
		   	   	   			        	    				base64_pdf.put("observaciones", list_orden.get(j).getObs_ext() !=null ? list_orden.get(j).getObs_ext(): JSONObject.NULL);
		   	   	   			        	    				base64_pdf.put("archivo", archivo);
		   	   	   							        	    //base64_pdf.put("doc", base64);
		   	   	   							        	    result_doc.put("result", base64_pdf);
		   	   	   							        	    result_doc.put("status", 200);
		   	   	   							        	    results_flag.put("response", result_doc);	   	   	   							       
		   	   	   							        	    jsonArray.put(results_flag);	   	   	   							        	 		
		   	   	   					        		
					   	   	   				        	 }
				 					   	        	}
				 					        		}else {
				 					        	
				 					        			System.err.println("Verificar error en else vector vacio");
				 					        			
				 					        		}
				 					        						 					        		
				 					        	}else {
				 				        			JSONObject results_flag = new JSONObject();
				 						            JSONObject result_doc = new JSONObject();
				 						            result_doc.put("results","!! No existe ordenes, en ese rango de Fechas!!");
				 						            result_doc.put("tipo",2);
				 						            results_flag.put("results",result_doc);
				 						            results_flag.put("status",500);
				 					        	  jsonResponse.put("response", results_flag);
				 					        	 jsonArray.put(results_flag);	
				 					        		//return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
				 					        	}
				 					         }
		     	                    			
		     	                    			
		     	                    		}
		     	                    		System.err.println("Json array en longitud :"+jsonArray.length());
		     	                    		if(jsonArray.length()==0) {
		     	                    			JSONObject results_flag = new JSONObject();
			 						            JSONObject result_doc = new JSONObject();
			 						            result_doc.put("results","!! No existe ordenes, en ese rango de Fechas!!");
			 						            result_doc.put("tipo",2);
			 						            results_flag.put("results",result_doc);
			 						            results_flag.put("status",500);
			 					        	  //jsonResponse.put("response", results_flag);
			 					        	 jsonArray.put(results_flag);	
		     	                    			jsonResponse.put("0", jsonArray);
				 					        	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	 
		     	                    		}else {
		     	                    			jsonResponse.put("0", jsonArray);
				 					        	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	 
		     	                    		}
		     	                    	
		     	                    		
			     	                    	
			     	                   	/*			          
			 					     else {	 					        	 					        	
			 					       				        			 					        		
				 					       
			 					   	        	
				 					        		 					        	    
													else {
				   	   	   				        		 System.out.println("Aqui esta numero 2---*-*"+ ordnumero2);
				   	   	   				        		    String base64 = avalabService.getPdfxOrden(list_orden.get(i).getNro_Orden());
				   	   	   				        	String archivo;
				   	   	   				        	  //String base64 = "----";
				   	   	   					        		 if(base64 == null) {
				   	   	   					        			 JSONObject results_flag = new JSONObject();
				   	   	   							        	    JSONObject result_doc = new JSONObject();
				   	   	   							        	    JSONObject base64_pdf = new JSONObject();
				   	   	   							        	    base64_pdf.put("doc", "No Cuenta con base 64");  
				   	   	   							        	    result_doc.put("result", base64_pdf);
				   	   	   							        	    results_flag.put("response", result_doc);
				   	   	   							        	    results_flag.put("status", 200);
				   	   	   							        	    jsonArray.put(results_flag);
				   	   	   					        		 }else {
				   	   	   					        		  	    JSONObject results_flag = new JSONObject();
				   	   	   							        	    JSONObject result_doc = new JSONObject();
				   	   	   							        	    JSONObject base64_pdf = new JSONObject();			   	   	   							        	   
				   	   	   							        	    
				   		   	   	   				        		 //System.out.println("list_orden.get(i).getNro_Orden()-*-*"+ list_orden.get(i).getNro_Orden());
				   		   	   	   				        		 //System.out.println("getCedula.get(i).getApellido()*-*"+getCedula.get(0).getApellido());
				   		   	   	   				        		// System.out.println("getCedula.get(i).getNombre()-*-*"+ getCedula.get(i).getNombre());

				   	   	   							        	    archivo=list_orden.get(i).getNro_Orden()+"_"+getCedula.get(0).getApellido()+"_"+getCedula.get(0).getNombre()+".pdf";
				   	   	   							        	    		
				   	   	   			        	    				base64_pdf.put("orden_synlab", list_orden.get(i).getNro_Orden());
				   	   	   			        	    				base64_pdf.put("year_orden", list_orden.get(i).getNro_ext());
				   	   	   			        	    				base64_pdf.put("hc", getCedula.get(0).getId_pac());
				   	   	   			        	    				base64_pdf.put("observaciones", list_orden.get(i).getObs_ext());
				   	   	   			        	    				base64_pdf.put("archivo", archivo);
				   	   	   							        	    base64_pdf.put("doc", base64);
				   	   	   							        	    result_doc.put("result", base64_pdf);
				   	   	   							        	    result_doc.put("status", 200);
				   	   	   							        	    results_flag.put("response", result_doc);	   	   	   							       
				   	   	   							        	    jsonArray.put(results_flag);	   	   	   							        	 		
				   	   	   					        		 }	
				   	   	   				        	 	}	 					        	    	  					        	 					        	  	 					        	   	 					        	    		 					        	
				 					        		}

				 					        	jsonResponse.put("0", jsonArray);
				 					        	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	 	
			 					        		}else {
			 					        			JSONObject results_flag = new JSONObject();
				 						            JSONObject result_doc = new JSONObject();
				 						            result_doc.put("results","!! No existe ordenes, en ese rango de Fechas !!");
				 						            result_doc.put("tipo",2);
				 						            results_flag.put("results",result_doc);
				 						            results_flag.put("status",500);
				 					        	  jsonResponse.put("response", results_flag);	        			        		        		
				 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
			 					        		}
			 					        		
				 				
			 					        	}else {
			 					        		  JSONObject results_flag = new JSONObject();
				 						            JSONObject result_doc = new JSONObject();
				 						            result_doc.put("results","!! Error por favor verifica, el filtro de fechas 15 dias maximo!!");
				 						            result_doc.put("tipo",2);
				 						            results_flag.put("results",result_doc);
				 						            results_flag.put("status",500);
				 					        	  jsonResponse.put("response", results_flag);	        			        		        		
				 					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());	
			 					        	}
			 					       	
			 					        				        	 					        
			 					         }
			 					     
			 					     */
		     	                    	}
		     	                    
			
		     	                    }					           				         
							     
					            }else {
					            	jsonResponse.put("response", "No esta permitido error de Token");	        			        		        		
					        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
					            }
				                    
		                    
		                    
			                    
		                }else {
		                	jsonResponse.put("response", "Error en la authorizacion");	        			        		        		
			        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
		                }
			       
			            
		        	}else {
		        		jsonResponse.put("response", "No esta permitido");
		        		return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
		        	}
		        	
		        	/* Para complementar los datos
		           JSONObject results_flag = new JSONObject();
        	    JSONObject result_doc = new JSONObject();
        	    result_doc.put("orden_synlab",list_orden.get(i).getNro_Orden());
        	    result_doc.put("tipo",2);
        	    results_flag.put("results",result_doc);
        	    results_flag.put("status",500);
        	    jsonArray.put(results_flag);
		        	/*
		        	
		        	
		        	 /*
		        	Integer nro_ord = grupo_res.get(0).getOrden();
		        	JSONArray ordenesArray = new JSONArray();
		        	JSONArray codigosAnalisisArray = new JSONArray();
		        	for (int i = 0; i < grupo_res.size(); i++) {
		        	    ResultadosGrupo resultadosGrupo = grupo_res.get(i);	 					        	    	 					        	  
		        	    if(!resultadosGrupo.getOrden().equals(nro_ord)) {
		        	        JSONObject ordenObject = new JSONObject();
		        	        ordenObject.put("nro_orden", nro_ord);
		        	        ordenObject.put("results", codigosAnalisisArray);
		        	        ordenObject.put("tipo", 1);

		        	        ordenesArray.put(ordenObject);
		        	        nro_ord = resultadosGrupo.getOrden();
		        	        codigosAnalisisArray = new JSONArray(); 
		        	    }
		        	    JSONObject codigoAnalisisObject = new JSONObject();
		        	  codigoAnalisisObject.put("exacodigo", resultadosGrupo.getCodigo_parametro());
		        	   codigoAnalisisObject.put("comentarioOrden", JSONObject.NULL);
		        	  codigoAnalisisObject.put("ComentarioResultado", resultadosGrupo.getTxt_res() != null ? resultadosGrupo.getTxt_res() : JSONObject.NULL);
		        	  codigoAnalisisObject.put("PanicoMinimo",  JSONObject.NULL);
		        	  codigoAnalisisObject.put("FechaResultado", resultadosGrupo.getFechaResultado() != null ? resultadosGrupo.getFechaResultado() : JSONObject.NULL);	 					        	 			 					        	  	 					        	   					       
		        	  codigoAnalisisObject.put("Resultado", resultadosGrupo.getResultado() != null ? resultadosGrupo.getResultado() : JSONObject.NULL);
		        	 codigoAnalisisObject.put("Observaciones", resultadosGrupo.getObs_externa_orden() != null ? resultadosGrupo.getObs_externa_orden() : JSONObject.NULL);
		        	 codigoAnalisisObject.put("validado", 1);			        		             				        		            
		        	 String date_send_json = resultadosGrupo.getFechaResultado();
	             SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			        		             
	             java.util.Date utilDate = null;
	             try {
	                 utilDate = inputFormat.parse(date_send_json);
	             } catch (ParseException e) {
	                 e.printStackTrace();
	             }					        		            
	             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());					        		            
	             SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss:SSSa");					        		            
	             String formattedDate = outputFormat.format(sqlDate);		        		             					        	 
	             codigoAnalisisObject.put("FechaResultado",formattedDate);
	             
	             String referenciaMinima = resultadosGrupo.getVdr_resultado();
	             		        		             
		             if (referenciaMinima != null) {
		                 String[] partes = referenciaMinima.split("-");
		                 String minimo = partes[0];
		                 String maximo = partes[1];	
		                 codigoAnalisisObject.put("refminima", minimo);
		                 codigoAnalisisObject.put("refmaxima", maximo);
		             } else {
		            	 codigoAnalisisObject.put("refminima", JSONObject.NULL);
		            	 codigoAnalisisObject.put("refmaxima", JSONObject.NULL);
		             }		        		             
		         codigoAnalisisObject.put("PanicoMaximo",JSONObject.NULL);	 					       	

		            String sexo = resultadosGrupo.getSexo_paciente();
    		            System.out.println("Sexoo: "+sexo);
    		            if(sexo!=null) {
    		            	  String sexo_send_json="";
	        		             if(sexo.equals("M")) {
	        		           	 sexo_send_json="Masculino";
	        		             }else {
	        		            	 sexo_send_json="Femenino";
	        		           }
	        		             codigoAnalisisObject.put("sexo", sexo_send_json);
    		            }else {
    		            	codigoAnalisisObject.put("sexo", JSONObject.NULL);
    		            }
    		            
        		      codigoAnalisisObject.put("Unidad",resultadosGrupo.getUnidad_resultado() != null ? resultadosGrupo.getUnidad_resultado() : JSONObject.NULL);
        		      codigoAnalisisObject.put("edadminima", JSONObject.NULL);	 					       	
        		      codigoAnalisisObject.put("edadmaxima",JSONObject.NULL);	 					       	
        		      codigosAnalisisArray.put(codigoAnalisisObject);
		        	}

		        	if (codigosAnalisisArray.length() > 0) {
		        	    JSONObject ordenObject = new JSONObject();
		        	    ordenObject.put("nro_orden", nro_ord);
		        	    ordenObject.put("exacodigo", grupo_res.get(grupo_res.size() - 1).getCodigo_parametro());
		        	    ordenObject.put("comentarioOrden", grupo_res.get(grupo_res.size() - 1).getObs_externa_orden() != null ? grupo_res.get(grupo_res.size() - 1).getObs_externa_orden(): JSONObject.NULL);
		        	   ordenObject.put("ComentarioResultado",JSONObject.NULL);
		        	   ordenObject.put("Resultado", grupo_res.get(grupo_res.size() - 1).getTxt_res() != null ? grupo_res.get(grupo_res.size() - 1).getTxt_res(): JSONObject.NULL);
		        	   ordenObject.put("FechaResultado", grupo_res.get(grupo_res.size() - 1).getFechaResultado() != null ? grupo_res.get(grupo_res.size() - 1).getFechaResultado(): JSONObject.NULL);
		        	  ordenObject.put("Observaciones", grupo_res.get(grupo_res.size() - 1).getObs_externa_orden() != null ? grupo_res.get(grupo_res.size() - 1).getObs_externa_orden(): JSONObject.NULL);	 					        	   	 					        	  
		        	  String date_send_json = grupo_res.get(grupo_res.size() - 1).getFechaResultado();
		             SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");			        		             
		             java.util.Date utilDate = null;
		             try {
		                 utilDate = inputFormat.parse(date_send_json);
		             } catch (ParseException e) {
		                 e.printStackTrace();
		             }					        		            
		             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());					        		            
		             SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy hh:mm:ss:SSSa");					        		            
		             String formattedDate = outputFormat.format(sqlDate);		        		             					        	 
		             ordenObject.put("FechaResultado",formattedDate);	 					       	

		             String referenciaMinima = grupo_res.get(grupo_res.size() - 1).getVdr_resultado();
    		             if (referenciaMinima != null) {
    		                 String[] partes = referenciaMinima.split("-");
    		                 String minimo = partes[0];
    		                 String maximo = partes[1];	
    		                 ordenObject.put("refminima", minimo);
    		                 ordenObject.put("refmaxima", maximo);
    		             } else {
    		            	 ordenObject.put("refminima", JSONObject.NULL);
    		            	 ordenObject.put("refmaxima", JSONObject.NULL);
    		             }			        		             
    		           ordenObject.put("PanicoMaximo",JSONObject.NULL);	 					       	
    		           ordenObject.put("validado", 1);		
    		           
    		           String sexo = grupo_res.get(grupo_res.size() - 1).getSexo_paciente();
    		            System.out.println("Sexoo: "+sexo);
    		            if(sexo!=null) {
    		            	  String sexo_send_json="";
	        		             if(sexo.equals("M")) {
	        		           	 sexo_send_json="Masculino";
	        		             }else {
	        		            	 sexo_send_json="Femenino";
	        		           }
	        		             ordenObject.put("sexo", sexo_send_json);
    		            }else {
    		            	ordenObject.put("sexo", JSONObject.NULL);
    		            }
		            	ordenObject.put("Unidad", grupo_res.get(grupo_res.size() - 1).getUnidad_resultado() != null ? grupo_res.get(grupo_res.size() - 1).getUnidad_resultado(): JSONObject.NULL);
		            	ordenObject.put("edadminima", JSONObject.NULL);	 					       	
		            	ordenObject.put("edadmaxima",JSONObject.NULL);	 					       	
		             ordenesArray.put(ordenObject);	 					 					        	 
		        	}	 					        	
		        	JSONObject results_flag = new JSONObject();
		        	JSONObject result_doc = new JSONObject();
		        	result_doc.put("results","Comienza aqui por favor");
		        	result_doc.put("tipo",2);
		        	result_doc.put("0", ordenesArray); // Agrega la lista de ordenes al JSON
		        	results_flag.put("result",result_doc);
		        	results_flag.put("status",500);
		        	jsonResponse.put("response", results_flag);	        			        		        		
		        	return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
		        	*/
		        	/////
	    }

	    // If no cases match, return a default response
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse.toString());
	}
	
	

	
}
