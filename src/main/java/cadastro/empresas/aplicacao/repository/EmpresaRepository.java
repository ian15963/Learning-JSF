package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.dto.EmpresaDto;
import cadastro.empresas.aplicacao.model.Empresa;

public interface EmpresaRepository {
	
	List<EmpresaDto> findAll();
	Empresa findById(Long id);
	Empresa create(Empresa empresa);
	Empresa update(Empresa empresa);
	void delete(Empresa empresa);
	List<EmpresaDto> search(String razaoSocial);

}
