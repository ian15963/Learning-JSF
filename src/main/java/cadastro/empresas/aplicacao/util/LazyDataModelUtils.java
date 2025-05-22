package cadastro.empresas.aplicacao.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyDataModelUtils {

	public static <R> LazyDataModel<R> createLazyDataModel(Function<Page, List<R>> lazyDataModelFetch, int totalElements){
		return new LazyDataModel<R>() {
			private static final long serialVersionUID = 1L;
			private List<R> loadedData = new ArrayList<R>();
			
			public List<R> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				Page page = new Page(first, pageSize, totalElements, sortField, sortOrder);
				this.setRowCount(page.getTotalElements());
				List<R> fetchedData = lazyDataModelFetch.apply(page);
				loadedData = fetchedData;
				return fetchedData;
			}

			public R getRowData(String rowKey) {
				List<R> dataList = loadedData.stream().filter(data -> this.getRowKey(data).toString().equals(rowKey)).toList();
				return dataList.isEmpty() ? null : dataList.get(0);
			}

			public Object getRowKey(R object) {
				Object rowKey = getRowKeyValue(object);
				validateRowKeyValue(rowKey);
				return rowKey;
			}
			
			private void validateRowKeyValue(Object rowKey) {
				if(Objects.isNull(rowKey)) {
					throw new IllegalArgumentException("Rowkey must not be null");
				}
			}
			
			private Object getRowKeyValue(R object) {
				Field[] objectFields = object.getClass().getDeclaredFields();
				for(Field field: objectFields) {
					if(this.isRowKey(field)) {
						return getFieldValue(field, object);
					}
				}
				return null;
			}

			private boolean isRowKey(Field field) {
				return field.isAnnotationPresent(LazyDataModelIdentifier.class);
			}
			
			private Object getFieldValue(Field field, Object object) {
				try {
					field.setAccessible(true);
					object = field.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}finally {
					field.setAccessible(false);
				}
				return object;
			}
			
        };
	}
	
}
