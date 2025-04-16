package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository repository;	
	private List<Empresa> empresas = new ArrayList();
	private Empresa empresa = new Empresa();

	public void listarEmpresas() {
		empresas = repository.findAll();
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return empresas;
	}
	
	public void salvar() {
		this.repository.create(empresa);
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
}
