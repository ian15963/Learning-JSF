package cadastro.empresas.aplicacao.util;

import org.primefaces.model.SortOrder;

public class Page {

	private int page;
	private int pageSize;
	private String sortBy;
	private SortOrder orderBy;
	
	public Page(int page, int pageSize, String sortBy, SortOrder orderBy) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.sortBy = sortBy;
		this.orderBy = orderBy;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public SortOrder getOrderBy() {
		return orderBy;
	}
	
}
