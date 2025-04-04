package br.com.fincofre.api.domain.spent;

import jakarta.validation.constraints.NotNull;

public record SpentUpdateDTO(@NotNull Long id, String date, String spent, Status status, String value, Category category, String account, Method method) {
}
