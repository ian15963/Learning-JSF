package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.model.RamoAtividade;

public interface RamoAtividadeRepository {

	List<RamoAtividade> search(String description);
	List<RamoAtividade> findAll();
}
