package br.com.fincofre.api.models.dtos;

import br.com.fincofre.api.models.entities.spent.Spent;
import br.com.fincofre.api.models.enums.Category;
import br.com.fincofre.api.models.enums.Method;
import br.com.fincofre.api.models.enums.SpentType;
import br.com.fincofre.api.models.enums.Status;

import java.math.BigDecimal;

public record SpentDetailsDTO(Long id, Long userId, String date, String spent, Status status, BigDecimal value, Category category, SpentType type, String account, Method method) {

    public SpentDetailsDTO(Spent spent) {
        this(spent.getId(), spent.getUser().getId(), spent.getDate(), spent.getSpent(), spent.getStatus(), spent.getValue(), spent.getCategory(), spent.getType(), spent.getAccount(), spent.getMethod());
    }

}
