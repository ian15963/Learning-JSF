package cadastro.empresas.aplicacao.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyDataModelUtils {

	public static <R> LazyDataModel<R> createLazyDataModel(Function<Page, List<R>> lazyDataModelFetch, int totalElements){
		return new LazyDataModel<R>() {
			private static final long serialVersionUID = 1L;
			
			public List<R> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				Page page = new Page(first, pageSize, totalElements, sortField, sortOrder);
				this.setRowCount(page.getTotalElements());
				return lazyDataModelFetch.apply(page);

            }
        };
	}
	
}
