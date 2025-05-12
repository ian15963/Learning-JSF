package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ApplicationScoped
public class RamoAtividadeRepositoryImpl implements RamoAtividadeRepository{

	@Inject
	private EntityManager entityManager;
	
	@Override
	public List<RamoAtividade> search(String description) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RamoAtividade> query = cb.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = query.from(RamoAtividade.class);
		query.select(root);
		query.where(cb.like(cb.upper(root.get("descricao")), description.toUpperCase() + "%"));
		
		TypedQuery<RamoAtividade> result =  entityManager.createQuery(query);
		return result.getResultList();
	}

	@Override
	public List<RamoAtividade> findAll() {
		TypedQuery<RamoAtividade> query = entityManager.createQuery("FROM RamoAtividade", RamoAtividade.class);
		return query.getResultList();
	}

}
