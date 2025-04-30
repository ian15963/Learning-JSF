package cadastro.empresas.aplicacao.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.mysql.cj.util.StringUtils;

import cadastro.empresas.aplicacao.converter.RamoAtividadeConverter;
import cadastro.empresas.aplicacao.model.Empresa;
import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;
import cadastro.empresas.aplicacao.repository.EmpresaRepository;
import cadastro.empresas.aplicacao.repository.RamoAtividadeRepository;
import cadastro.empresas.aplicacao.util.CustomFacesMessage;

@Named
@ViewScoped
public class EmpresaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository repository;
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	private List<Empresa> empresas;
	private Empresa empresa;
	private String razaoSocial;
	private Converter<RamoAtividade> converter;
	
	public void pesquisarEmpresa() {
		empresas = repository.search(razaoSocial);
		if(empresas.isEmpty()) {
			CustomFacesMessage.info("Nenhuma empresa encontrada");
		}
	}
	
	public void prepararEmpresa() {
		empresa = new Empresa();
	}
	
	public void prepararEdicao() {
		converter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo){
		List<RamoAtividade> ramos = ramoAtividadeRepository.search(termo);
		
		converter = new RamoAtividadeConverter(ramos);
		
		return ramos;
	}

	public void listarEmpresas() {
		empresas = repository.findAll();
	}
	
	private void salvar() {
		this.repository.create(empresa);
		
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
		
		CustomFacesMessage.info("Empresa adicionada com sucesso!");
		
		RequestContext.getCurrentInstance()
			.update(Arrays
					.asList("formulario:tabelaEmpresa", "formulario:globalMessage"));
	}
	
	private void update() {
		this.repository.update(empresa);
		
		if(isRazaoSocialBlank()) {
			listarEmpresas();
		}else{
			pesquisarEmpresa();
		}
		
		CustomFacesMessage.info("Empresa atualizada com sucesso!");
		
		RequestContext.getCurrentInstance()
			.update(Arrays
					.asList("formulario:tabelaEmpresa", "formulario:globalMessage"));
	}
	
	public void buttonAction() {
		if(Objects.nonNull(empresa) && Objects.nonNull(empresa.getId())) {
			this.update();
		}else {
			this.salvar();
		}
	}
	
	private boolean isRazaoSocialBlank() {
		return StringUtils.isNullOrEmpty(razaoSocial);
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public boolean isEmpresaSelected() {
		return Objects.nonNull(empresa) && Objects.nonNull(empresa.getId());
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

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
	
	
	
}
