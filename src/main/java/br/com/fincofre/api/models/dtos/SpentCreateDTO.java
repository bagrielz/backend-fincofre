package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.SpentType;
import br.com.fincofre.api.models.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpentCreateDTO(

        @NotBlank
        String date,
        @NotBlank
        String spent,
        @NotNull
        Status status,
        @NotBlank
        String value,
        @NotNull
        Category category,
        @NotBlank
        SpentType type,
        @NotBlank
        String account,
        @NotNull
        Method method

) {
}
