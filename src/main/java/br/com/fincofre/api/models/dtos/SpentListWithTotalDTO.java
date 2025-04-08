package br.com.fincofre.api.models.dtos;

import java.util.List;

public record SpentListWithTotalDTO(List<SpentListingDTO> spents, double total) {
}
