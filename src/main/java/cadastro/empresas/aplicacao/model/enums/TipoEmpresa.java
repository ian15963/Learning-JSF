package cadastro.empresas.aplicacao.model.enums;

public enum TipoEmpresa {
	MEI("Microempreendedor Individual"), 
	EIRELI("Empresa Individual de Responsabilidade Limitada"), 
	LTDA("Sociedade Limitada"), 
	SA("Sociedade Anônima");
	
	private final String descricao;
	
	TipoEmpresa(String descricao) {
		this.descricao = descricao;
	}
	
}
