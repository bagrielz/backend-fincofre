package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.SpentType;
import br.com.fincofre.api.models.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SpentUpdateDTO(LocalDate date, String spent, Status status, BigDecimal value, Category category, SpentType type, String account, Method method) {
}
