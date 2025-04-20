package br.com.fincofre.api.models.dtos;

import java.util.List;

public record SpentsListWithTotalDTO(List<SpentListDTO> spents, double total) {
}
