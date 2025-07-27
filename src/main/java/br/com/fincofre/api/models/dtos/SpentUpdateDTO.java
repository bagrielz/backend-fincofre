package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.SpentType;
import br.com.fincofre.api.models.enums.Status;
import jakarta.validation.constraints.NotNull;

public record SpentUpdateDTO(String date, String spent, Status status, String value, Category category, SpentType type, String account, Method method) {
}
