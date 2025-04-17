package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.util.CustomFacesMessage;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository repository;	
	private List<Empresa> empresas;
	private Empresa empresa = new Empresa();
	private String razaoSocial;
	
	public void pesquisarEmpresa() {
		empresas = repository.search(razaoSocial);
		if(empresas.isEmpty()) {
			CustomFacesMessage.info("Nenhuma empresa encontrada");
		}
	}

	public void listarEmpresas() {
		empresas = repository.findAll();
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

	

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<Empresa> getEmpresas(){
		return empresas;
	}
	
}
