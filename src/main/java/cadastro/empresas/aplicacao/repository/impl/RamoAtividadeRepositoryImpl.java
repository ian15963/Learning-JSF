package cadastro.empresas.aplicacao.repository.impl;

import java.util.List;

import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class RamoAtividadeRepositoryImpl implements RamoAtividadeRepository{

	@Inject
	private EntityManager entityManager;
	
	@Override
	public List<RamoAtividade> search(String description) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RamoAtividade> query = cb.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = query.from(RamoAtividade.class);
		query.select(root);
		query.where(cb.like(cb.upper(root.get("description")), description.toUpperCase() + "%"));
		
		TypedQuery<RamoAtividade> result =  entityManager.createQuery(query);
		return result.getResultList();
	}

}
