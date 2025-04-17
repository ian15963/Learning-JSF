package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private List<Empresa> empresas;
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
		Empresa savedEmpresa = this.repository.create(empresa);
		if(Objects.nonNull(empresas) && !empresas.isEmpty()) {
			empresas.add(savedEmpresa);
		}
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
}
