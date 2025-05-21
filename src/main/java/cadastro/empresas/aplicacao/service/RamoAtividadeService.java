package cadastro.empresas.aplicacao.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.interceptor.transactional.Transactional;
import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;

@Named
@ApplicationScoped
public class RamoAtividadeService {

	@Inject
	private RamoAtividadeRepository repository;
	
	@Transactional
	public List<RamoAtividade> findAll(){
		return repository.findAll();
	}
	
}
