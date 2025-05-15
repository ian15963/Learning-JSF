package cadastro.empresas.aplicacao.util;

import java.util.Objects;

import org.primefaces.model.SortOrder;

public class Page {

	private int page;
	private int pageSize;
	private int totalElements;
	private String sortBy;
	private SortOrder orderBy;
	
	public Page(int page, int pageSize, int totalElements, String sortBy, SortOrder orderBy) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.sortBy = sortBy;
		this.orderBy = orderBy;
	}
	
	public String buildSortQuery(String query) {
		boolean isAscendingOrder = SortOrder.ASCENDING.equals(orderBy);
		if (Objects.nonNull(sortBy)) {
			query += " ORDER BY e." + this.getSortBy() + (isAscendingOrder ? " ASC" : " DESC");
		}
		return query;
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

	public int getTotalElements() {
		return totalElements;
	}
	
}
