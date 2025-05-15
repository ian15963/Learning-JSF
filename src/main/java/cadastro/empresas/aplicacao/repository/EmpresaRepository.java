package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.util.Page;

public interface EmpresaRepository {
	
	List<EmpresaDto> findAll(Page pageInfo);
	Empresa findById(Long id);
	Empresa create(Empresa empresa);
	Empresa update(Empresa empresa);
	void delete(Empresa empresa);
	List<EmpresaDto> search(String razaoSocial);
	int totalElements();

}
