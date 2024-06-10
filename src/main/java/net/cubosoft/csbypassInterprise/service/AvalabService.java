package net.cubosoft.csbypassInterprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cubosoft.csbypassInterprise.entities.ListaOrden;
import net.cubosoft.csbypassInterprise.entities.MuestrasxAnaDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenProgressDTO;
import net.cubosoft.csbypassInterprise.entities.PacienteGetOrdenesDTO;
import net.cubosoft.csbypassInterprise.entities.PacientesDTO;
import net.cubosoft.csbypassInterprise.entities.ReferenciasDTO;
import net.cubosoft.csbypassInterprise.entities.ResultadosGrupo;
import net.cubosoft.csbypassInterprise.repository.AvalabRepository;

@Service
public class AvalabService {

	@Autowired
	AvalabRepository avalabRepository;
	
	
	public ReferenciasDTO getReferenciabyLogin(String user, String pass) {
		return avalabRepository.getReferenciabyLogin(user, pass);
		
	}
	public ReferenciasDTO checkUID(String UIID) {
		return avalabRepository.checkUID(UIID);
		
	}
	public ReferenciasDTO insert(String json) {
		return avalabRepository.insert(json);
		
	}
	public PacientesDTO checkCedula(String cedula) {
		return avalabRepository.checkCedula(cedula);
		
	}
	public String insertPac(String json_pac) {
		return avalabRepository.insertPac(json_pac);
		
	}	
	public Object insertOrden(String json_envio) {
		return avalabRepository.insertOrden(json_envio);
		
	}	
	public MuestrasxAnaDTO MuestrasxAna(String cod_ana) {
		return avalabRepository.MuestrasxAna(cod_ana);
		
	}
	
	public Boolean VerificarOrden(String nro_ord,String cod_ref) {
		return avalabRepository.VerificarOrden(nro_ord,cod_ref);
		
	}
	
	public String getPdfxOrden(String nro_ord) {
		return avalabRepository.getPdfxOrden(nro_ord);
		
	}
	
	public OrdenProgressDTO check_Res_P_Validación(String nro_ord) {
		return avalabRepository.check_Res_P_Validación(nro_ord);
		
	}
	
	public List<Object[]> VerificarOrden_Resultados(String nro_ord) {
		return avalabRepository.VerificarOrden_Resultados(nro_ord);
		
	}
	public OrdenDTO getResultadosxOrden(String nro_ord) {
		return avalabRepository.getResultadosxOrden(nro_ord);
		
	}
	public ReferenciasDTO getReferenciabyUID(String id_ref) {
		return avalabRepository.getReferenciabyUID(id_ref);
		
	}
	
	public List<ResultadosGrupo>  Verificar_grupo(Integer grupo_referencia, String codigo_referencia) {
		return avalabRepository.Verificar_grupo(grupo_referencia,codigo_referencia);
		
	}
	
	public List<PacienteGetOrdenesDTO>  getCedulaPaciente(String id_pac) {
		return avalabRepository.getCedulaPaciente(id_pac);
		
	}
	
	
	public List<ListaOrden> getOrdenByFecha(String fechaInicio,String fechaFin ,String historiaClinica,String getCod_ref){
		return avalabRepository.getOrdenByFecha(fechaInicio,fechaFin,historiaClinica,getCod_ref);
		
	}
	
	
	public Boolean CheckFechaAntes15Dias(String fechaInicio, String fechaFin) {
		return avalabRepository.CheckFechaAntes15Dias(fechaInicio,fechaFin);
	}
	
	
	public List<ReferenciasDTO>  getReferenciabyCod(Integer grupos) {
		return avalabRepository.getReferenciabyCod(grupos);
	}
	
	
	
	

	
}
