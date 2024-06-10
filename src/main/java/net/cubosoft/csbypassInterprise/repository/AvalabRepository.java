package net.cubosoft.csbypassInterprise.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.cubosoft.csbypassInterprise.entities.ListaOrden;
import net.cubosoft.csbypassInterprise.entities.MuestrasxAnaDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenDTO;
import net.cubosoft.csbypassInterprise.entities.OrdenProgressDTO;
import net.cubosoft.csbypassInterprise.entities.PacienteGetOrdenesDTO;
import net.cubosoft.csbypassInterprise.entities.PacientesDTO;
import net.cubosoft.csbypassInterprise.entities.ReferenciasDTO;
import net.cubosoft.csbypassInterprise.entities.ResultadosGrupo;

@Repository
public interface AvalabRepository {

	public ReferenciasDTO getReferenciabyLogin(String user, String pass);
	public ReferenciasDTO checkUID(String UIID);
	public ReferenciasDTO insert(String UIID);
	public PacientesDTO checkCedula(String cedula);
	public String insertPac(String json_pac);
	public Object insertOrden(String json_envio);
	public MuestrasxAnaDTO MuestrasxAna(String cod_ana);
	public Boolean VerificarOrden(String nro_ord,String cod_ref);
	public String getPdfxOrden(String nro_ord);
	public OrdenProgressDTO check_Res_P_Validación(String nro_ord);
	//Get Resultados
	public List<Object[]> VerificarOrden_Resultados(String nro_ord);
	public OrdenDTO getResultadosxOrden(String nro_ord);
	public ReferenciasDTO getReferenciabyUID(String id_ref);
	public List<ResultadosGrupo> Verificar_grupo(Integer grupo_referencia, String codigo_referencia);
	public List<PacienteGetOrdenesDTO> getCedulaPaciente(String id_pac);
	public List<ListaOrden> getOrdenByFecha(String fechaInicio,String fechaFin ,String historiaClinica,String getCod_ref);
	public Boolean CheckFechaAntes15Dias(String fechaInicio, String fechaFin);
	public List<ReferenciasDTO> getReferenciabyCod(Integer grupos);
}

