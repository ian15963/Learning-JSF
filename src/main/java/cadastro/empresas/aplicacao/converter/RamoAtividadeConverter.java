package cadastro.empresas.aplicacao.converter;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import cadastro.empresas.aplicacao.dto.RamoAtividadeDto;

public class RamoAtividadeConverter implements Converter<RamoAtividadeDto>{

	private List<RamoAtividadeDto> ramos;
	
	public RamoAtividadeConverter(List<RamoAtividadeDto> ramos) {
		super();
		this.ramos = ramos;
	}

	@Override
	public RamoAtividadeDto getAsObject(FacesContext context, UIComponent component, String value) {
		if(Objects.isNull(value)) {
			return null;
		}
		
		long ramoId = Long.valueOf(value);
		
		return ramos.stream()
						.filter(ramo -> ramo.getId()
						.equals(ramoId))
						.findFirst()
						.orElse(null);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, RamoAtividadeDto value) {
		return Optional.ofNullable(value)
						.map(ramo -> ramo.getId().toString())
						.orElse(null);
	}

}
