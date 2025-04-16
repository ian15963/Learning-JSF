package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;


public class EmpresaRepositoryImpl implements EmpresaRepository{
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Empresa findById(Long id) {
		return entityManager.find(Empresa.class, id);
	}

	@Override
	public Empresa create(Empresa empresa) {
		return entityManager.merge(empresa);
	}

	@Override
	public Empresa update(Empresa empresa, Long id) {
		empresa.setId(id);
		return entityManager.merge(empresa);
	}

	@Override
	public void delete(Empresa empresa) {
		entityManager.remove(empresa);;
	}

	@Override
	public List<Empresa> search(String razaoSocial) {
		TypedQuery<Empresa> query = entityManager.createQuery("FROM Empresa e WHERE e.razaoSocial LIKE CONCAT('%', :razaoSocial, '%')", Empresa.class);
		query.setParameter("nomeEmpresa", razaoSocial);
		return query.getResultList();
	}

}
