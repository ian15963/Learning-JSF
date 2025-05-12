package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;
import cadastro.empresas.aplicacao.model.RamoAtividade;

public interface RamoAtividadeRepository {

	List<RamoAtividadeDto> search(String description);
	List<RamoAtividade> findAll();
}
