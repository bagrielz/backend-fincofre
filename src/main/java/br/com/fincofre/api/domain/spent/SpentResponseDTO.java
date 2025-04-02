package br.com.fincofre.api.domain.spent;

import br.com.fincofre.api.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpentResponseDTO(

        @NotNull
        Long userId,
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
        String account,
        @NotNull
        Method method

) {
}
