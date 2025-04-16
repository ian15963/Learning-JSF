package cadastro.empresas.aplicacao.repository;

import java.util.List;

import cadastro.empresas.aplicacao.model.Empresa;

public interface EmpresaRepository {
	
	List<Empresa> findAll();
	Empresa findById(Long id);
	Empresa create(Empresa empresa);
	Empresa update(Empresa empresa, Long id);
	void delete(Empresa empresa);
	List<Empresa> search(String razaoSocial);

}
