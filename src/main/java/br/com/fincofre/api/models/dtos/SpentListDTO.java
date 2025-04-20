package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.entities.spent.Spent;
import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.Status;

public record SpentListDTO(Long id, String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentListDTO(Spent data) {
        this(data.getId(), data.getDate(), data.getSpent(), data.getStatus(), data.getValue(), data.getCategory(), data.getAccount(), data.getMethod());
    }

}
