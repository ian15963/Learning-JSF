package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.util.Page;

public interface EmpresaRepository {
	
	List<EmpresaDto> findAll(Page pageInfo);
	Empresa findById(Long id);
	Empresa create(Empresa empresa);
	Empresa update(Long id, Empresa empresa);
	void delete(Empresa empresa);
	List<EmpresaDto> search(Page page, String razaoSocial);
	int totalElements();
	int totalElementsFromSearch(String razaoSocial);
}
