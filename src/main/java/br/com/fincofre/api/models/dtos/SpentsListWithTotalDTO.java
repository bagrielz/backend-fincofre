package br.com.fincofre.api.models.dtos;

import java.math.BigDecimal;
import java.util.List;

public record SpentsListWithTotalDTO(List<SpentListDTO> spents, BigDecimal total) {
}
