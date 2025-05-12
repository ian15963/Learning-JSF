package cadastro.empresas.aplicacao.dto;

import cadastro.empresas.aplicacao.model.RamoAtividade;
import cadastro.empresas.aplicacao.model.enums.TipoEmpresa;

public class EmpresaDto {

	private Long id;
	private String razaoSocial;
	private String nomeFantasia;
	private TipoEmpresa tipo;
	private RamoAtividadeDto ramoAtividade;
	
	public EmpresaDto(Long id, String razaoSocial, String nomeFantasia, TipoEmpresa tipo, RamoAtividade ramoAtividade) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
		this.tipo = tipo;
		this.ramoAtividade = new RamoAtividadeDto(ramoAtividade);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEmpresa getTipo() {
		return tipo;
	}

	public void setTipo(TipoEmpresa tipo) {
		this.tipo = tipo;
	}



	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipo;
	}

	public void setTipoEmpresa(TipoEmpresa tipo) {
		this.tipo = tipo;
	}

	public RamoAtividadeDto getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividadeDto ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}
	
	
}
