package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.util.Transactional;

@ApplicationScoped
public class EmpresaRepositoryImpl implements EmpresaRepository{
	
	@Inject
	private EntityManager entityManager;

	@Override
	public Empresa findById(Long id) {
		return entityManager.find(Empresa.class, id);
	}

	@Transactional
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
		TypedQuery<Empresa> query = entityManager.createQuery("from Empresa WHERE razaoSocial LIKE CONCAT('%', :razaoSocial, '%')", Empresa.class);
		query.setParameter("razaoSocial", razaoSocial);
		return query.getResultList();
	}

	@Override
	public List<Empresa> findAll() {
		Query query = entityManager.createNativeQuery("select * from empresa", Empresa.class);
		return (List<Empresa>) query.getResultList();
	}

}
