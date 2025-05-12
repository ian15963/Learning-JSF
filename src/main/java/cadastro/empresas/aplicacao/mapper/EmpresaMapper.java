package cadastro.empresas.aplicacao.mapper;

import java.util.Objects;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.RamoAtividade;

public class EmpresaMapper {

	public static Empresa toEntity(EmpresaDto dto) {
		if(Objects.isNull(dto)) {
			return null;
		}
		
		Empresa empresa = new Empresa();
		RamoAtividade ramoAtividade = new RamoAtividade(dto.getRamoAtividade());
		
		empresa.setId(dto.getId());
		empresa.setCnpj(dto.getCnpj());
		empresa.setDataFundacao(dto.getDataFundacao());
		empresa.setFaturamento(dto.getFaturamento());
		empresa.setNomeFantasia(dto.getNomeFantasia());
		empresa.setRazaoSocial(dto.getRazaoSocial());
		empresa.setTipo(dto.getTipo());
		empresa.setRamoAtividade(ramoAtividade);
		
		return empresa;
	}
	
	public static EmpresaDto toDto(Empresa entity) {
		if(Objects.isNull(entity)) {
			return null;
		}
		
		EmpresaDto dto = new EmpresaDto();
		RamoAtividadeDto ramoAtividadeDto = new RamoAtividadeDto(entity.getRamoAtividade());
		dto.setId(entity.getId());
		dto.setCnpj(entity.getCnpj());
		dto.setDataFundacao(entity.getDataFundacao());
		dto.setFaturamento(entity.getFaturamento());
		dto.setNomeFantasia(entity.getNomeFantasia());
		dto.setRazaoSocial(entity.getRazaoSocial());
		dto.setTipo(entity.getTipo());
		dto.setRamoAtividade(ramoAtividadeDto);
		return dto;
	}
	
}
