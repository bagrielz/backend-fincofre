package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.entities.spent.Spent;
import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.Status;

public record SpentDetailsDTO(Long id, Long userId, String date, String spent, Status status, String value, Category category, String account, Method method) {

    public SpentDetailsDTO(Spent spent) {
        this(spent.getId(), spent.getUser().getId(), spent.getDate(), spent.getSpent(), spent.getStatus(), spent.getValue(), spent.getCategory(), spent.getAccount(), spent.getMethod());
    }

}
